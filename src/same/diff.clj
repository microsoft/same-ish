;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.diff
  (:require [clojure.set :as set]
            [same.ish :refer [ish? split-floats]]))

(defn- update-common-keys
  [acc lmap rmap keys]
  (reduce (fn [m k]
            (let [lv (get lmap k)
                  rv (get rmap k)]
              (if (ish? lv rv)
                (assoc-in m [:c k] rv)
                (-> m
                    (assoc-in [:l k] lv)
                    (assoc-in [:r k] rv)))))
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
          (ish? lk0 rk0)
          (if (ish? lv rv)
            (recur (assoc-in a [:c rk0] rv)
                   lkr
                   rkr)
            (recur (-> a
                       (assoc-in [:l lk0] lv)
                       (assoc-in [:r rk0] rv))
                   lkr
                   rkr))

          (< lk0 rk0)
          (recur (assoc-in a [:l lk0] lv) lkr rk)

          :else
          (recur (assoc-in a [:r rk0] rv) lk rkr))))))

(defprotocol Diff
  (diff [this that]))

(extend-protocol Diff
  nil
  (ish? [this that]
    [nil that nil])

  clojure.lang.Sequential
  (diff [this that]
    (cond
      (ish? this that)
      [nil nil that]

      (sequential? that)
      (reduce (fn [[cl cr cc] [vl vr vc]]
                [(conj cl vl)
                 (conj cr vr)
                 (conj cc vc)])
              [[] [] []]
              (map diff this that))

      :else
      [this that nil]))

  java.util.Set
  (diff [this that]
    (cond
      (ish? this that)
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
                (ish? vl vr)
                (recur l r (conj c vr) rl rr)

                (< vl vr)
                (recur (conj l vl) r c rl right)

                :else
                (recur l (conj r vr) c left rr))))))

      :else
      [this that nil]))

  java.util.Map
  (diff [this that]
    (cond
      (ish? this that)
      [nil nil that]

      (instance? java.util.Map that)
      (let [[this-floats this-rest] (split-floats (keys this))
            [that-floats that-rest] (split-floats (keys that))
            extract (juxt :l :r :c)]
        (-> {:l (select-keys this (set/difference this-rest that-rest))
             :r (select-keys this (set/difference that-rest this-rest))
             :c {}}
            (update-common-keys this that (set/intersection this-rest that-rest))
            (update-float-keys this that (sort this-floats) (sort that-floats))
            extract))

      :else
      [this that nil]))

  Object
  (diff [this that]
    (if (ish? this that)
      [nil nil that]
      [this that nil])))
