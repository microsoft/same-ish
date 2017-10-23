;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.ish
  (:require [same.compare :refer [compare-ulp]]))

(def ^:dynamic *comparator*
  "The function for comparing individual floats/doubles."
  (compare-ulp 100.0 2))

(defn split-floats
  "Split a collection into a vector of floating point values (of type Float or Double),
  and a set of all other values."
  [coll]
  (reduce (fn [[floats rest] v]
            (if (float? v)
              [(conj floats v) rest]
              [floats (conj rest v)]))
          [[] #{}]
          coll))

(defprotocol Approximate
  (ish [this that]))

(extend-protocol Approximate
  nil
  (ish [this that]
    (nil? that))

  Double
  (ish [this that]
    (and (float? that)
         (*comparator* this that)))

  Float
  (ish [this that]
    (and (float? that)
         (*comparator* this that)))

  clojure.lang.Sequential
  (ish [this that]
    (or (= this that)
        (and (sequential? that)
             (= (count this) (count that))
             (every? (partial apply ish)
                     (map vector this that)))))

  java.util.Set
  (ish [this that]
    (or (= this that)
        (and (instance? java.util.Set that)
             (= (count this) (count that))
             (let [[this-floats this-rest] (split-floats this)
                   [that-floats that-rest] (split-floats that)]
               (and (= this-rest that-rest)
                    (ish (sort this-floats) (sort that-floats)))))))

  java.util.Map
  (ish [this that]
    (or (= this that)
        (and (instance? java.util.Map that)
             (= (count this) (count that))
             (let [[this-floats this-rest] (split-floats (keys this))
                   [that-floats that-rest] (split-floats (keys that))]
               (and (= this-rest that-rest)
                    (every? #(ish (get this %) (get that %)) this-rest)
                    (every? identity
                            (map #(and (ish %1 %2)
                                       (ish (get this %1) (get that %2)))
                                 (sort this-floats)
                                 (sort that-floats))))))))

  Object
  (ish [this that]
    (if (and that
             (.isArray ^Class (type this))
             (.isArray ^Class (type that)))
      (and (= (count this) (count that))
           (every? identity
                   (map ish this that)))
      (= this that))))
