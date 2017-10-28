;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.diff
  (:require [clojure.set :as set]
            [same.ish :refer [ish split-floats]]
            [same.platform :as p]))

(defn- un-array
  [a]
  (if (p/is-array? a)
    (vec a)
    a))

(defn- result-vec
  [n res & next]
  (if (empty? next)
    res
    (-> (or res [])
        (into (take (- ^long n (count res)) (repeat nil)))
        (into next))))

(defprotocol Diff
  (diff [this that]))

(defn- diff-seq
  [this that]
  (loop [l nil
         r nil
         c nil
         n 0
         left this
         right that]
    (if (or (empty? left) (empty? right))
      [(apply result-vec n l left) (apply result-vec n r right) c]
      (let [[l0 & lm] left
            [r0 & rm] right]
        (if (ish l0 r0)
          (recur l r (result-vec n c r0) (inc n) lm rm)
          (let [[dl dr dc] (diff l0 r0)]
            (recur (result-vec n l dl)
                   (result-vec n r dr)
                   (if (nil? dc) c (result-vec n c dc))
                   (inc n) lm rm)))))))

(defn- update-common-keys
  [acc lmap rmap keys]
  (reduce (fn [m k]
            (let [lv (get lmap k)
                  rv (get rmap k)]
              (if (ish lv rv)
                (assoc-in m [:c k] rv)
                (let [[dl dr dc] (diff lv rv)]
                  (cond-> m
                    (not (every? nil? [dl dr])) (assoc-in [:l k] dl)
                    (not (every? nil? [dl dr])) (assoc-in [:r k] dr)
                    (not (nil? dc))             (assoc-in [:c k] dc))))))
          acc
          keys))

(defn- update-float-keys
  [acc lmap rmap lkeys rkeys]
  (loop [a acc
         lk lkeys
         rk rkeys]
    (cond
      (and (empty? lk) (empty? rk))
      a

      (empty? lk)
      (reduce #(assoc-in %1 [:r %2] (get rmap %2))
              a
              rk)

      (empty? rk)
      (reduce #(assoc-in %1 [:l %2] (get lmap %2))
              a
              lk)

      :else
      (let [[lk0 & lkr] lk
            [rk0 & rkr] rk
            lv (get lmap lk0)
            rv (get rmap rk0)]
        (cond
          (ish lk0 rk0)
          (if (ish lv rv)
            (recur (assoc-in a [:c rk0] rv)
                   lkr
                   rkr)
            (let [[dl dr dc] (diff lv rv)]
              (recur (cond-> a
                       (not (every? nil? [dl dr])) (assoc-in [:l lk0] dl)
                       (not (every? nil? [dl dr])) (assoc-in [:r rk0] dr)
                       (not (nil? dc))             (assoc-in [:c rk0] dc))
                     lkr
                     rkr)))

          (< ^double lk0 ^double rk0)
          (recur (assoc-in a [:l lk0] lv) lkr rk)

          :else
          (recur (assoc-in a [:r rk0] rv) lk rkr))))))

#?(:clj
   (extend-protocol Diff
     nil
     (diff [this that]
       [this that nil])

     clojure.lang.Sequential
     (diff [this that]
       (cond
         (ish this that)    [nil nil that]
         (sequential? that) (diff-seq this that)
         :else              [this that nil]))

     java.util.Set
     (diff [this that]
       (cond
         (ish this that)
         [nil nil that]

         (instance? java.util.Set that)
         (let [[this-floats this-rest] (split-floats this)
               [that-floats that-rest] (split-floats that)]
           (loop [l (set/difference this-rest that-rest)
                  r (set/difference that-rest this-rest)
                  c (set/intersection this-rest that-rest)
                  left  (sort this-floats)
                  right (sort that-floats)]
             (if (or (empty? left) (empty? right))
               (mapv not-empty [(into l left) (into r right) c])
               (let [[vl & rl] left
                     [vr & rr] right]
                 (cond
                   (ish vl vr)
                   (recur l r (conj c vr) rl rr)

                   (< ^double vl ^double vr)
                   (recur (conj l vl) r c rl right)

                   :else
                   (recur l (conj r vr) c left rr))))))

         :else
         [this that nil]))

     java.util.Map
     (diff [this that]
       (cond
         (ish this that)
         [nil nil that]

         (instance? java.util.Map that)
         (let [[this-floats this-rest] (split-floats (keys this))
               [that-floats that-rest] (split-floats (keys that))
               extract (juxt :l :r :c)]
           (-> {:l (select-keys this (set/difference this-rest that-rest))
                :r (select-keys that (set/difference that-rest this-rest))
                :c {}}
               (update-common-keys this that (set/intersection this-rest that-rest))
               (update-float-keys this that (sort this-floats) (sort that-floats))
               extract
               (->> (mapv not-empty))))

         :else
         [this that nil]))

     Object
     (diff [this that]
       (if (ish this that)
         [nil nil (un-array that)]
         (if (p/is-array? that)
           (diff-seq this that)
           [(un-array this) (un-array that) nil]))))

   :cljs
   (extend-protocol Diff
     nil
     (diff [this that]
       [nil that nil])

     number
     (diff [this that]
       (if (ish this that)
         [nil nil that]
         [this that nil]))

     boolean
     (diff [this that]
       (if (ish this that)
         [nil nil that]
         [this that nil]))

     string
     (diff [this that]
       (if (ish this that)
         [nil nil that]
         [this that nil]))

     array
     (diff [this that]
       (if (p/is-array? that)
         (diff-seq this that)
         [(un-array this) that nil]))

     object
     (diff [this that]
       (cond
         (ish this that)
         [nil nil (un-array that)]

         (and (sequential? this) (sequential? that))
         (diff-seq this that)

         (and (map? this) (map? that))
         (let [[this-floats this-rest] (split-floats (keys this))
               [that-floats that-rest] (split-floats (keys that))
               extract (juxt :l :r :c)]
           (-> {:l (select-keys this (set/difference this-rest that-rest))
                :r (select-keys that (set/difference that-rest this-rest))
                :c {}}
               (update-common-keys this that (set/intersection this-rest that-rest))
               (update-float-keys this that (sort this-floats) (sort that-floats))
               extract
               (->> (mapv not-empty))))

         (and (set? this) (set? that))
         (let [[this-floats this-rest] (split-floats this)
               [that-floats that-rest] (split-floats that)]
           (loop [l (set/difference this-rest that-rest)
                  r (set/difference that-rest this-rest)
                  c (set/intersection this-rest that-rest)
                  left  (sort this-floats)
                  right (sort that-floats)]
             (if (or (empty? left) (empty? right))
               (mapv not-empty [(into l left) (into r right) c])
               (let [[vl & rl] left
                     [vr & rr] right]
                 (cond
                   (ish vl vr)
                   (recur l r (conj c vr) rl rr)

                   (< ^double vl ^double vr)
                   (recur (conj l vl) r c rl right)

                   :else
                   (recur l (conj r vr) c left rr))))))

         :else
         [this (un-array that) nil]))))
