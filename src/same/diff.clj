;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.diff
  (:require [clojure.data :as data]
            [clojure.set :as set]
            [same.ish :refer [ish? split-floats]]))

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
    (data/diff this that))

  Object
  (diff [this that]
    (if (ish? this that)
      [nil nil that]
      [this that nil])))
