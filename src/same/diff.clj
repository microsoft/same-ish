(ns same.diff
  (:require [clojure.data :as data]
            [same.ish :refer [ish?]]))

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
    (data/diff this that))

  java.util.Map
  (diff [this that]
    (data/diff this that))

  Object
  (diff [this that]
    (if (ish? this that)
      [nil nil that]
      [this that nil])))
