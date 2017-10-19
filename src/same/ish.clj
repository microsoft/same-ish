;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.ish)

(def ^:dynamic *abs-ish* 1e-6)
(def ^:dynamic *rel-ish* 1e-3)

(defn- ish*
  [f1 f2]
  (let [d (Math/abs (- f1 f2))]
    (or (<= d *abs-ish*)
        (<= d (* *rel-ish* (Math/max (Math/abs f1) (Math/abs f2)))))))

(defn- split-floats
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
  (ish? [this that]))

(extend-protocol Approximate
  nil
  (ish? [this that]
    (nil? that))

  Double
  (ish? [this that]
    (and (float? that)
         (ish* this (double that))))

  Float
  (ish? [this that]
    (and (float? that)
         (ish* (double this) (double that))))

  clojure.lang.Sequential
  (ish? [this that]
    (or (= this that)
        (and (sequential? that)
             (= (count this) (count that))
             (every? (partial apply ish?)
                     (map vector this that)))))

  java.util.Set
  (ish? [this that]
    (or (= this that)
        (and (instance? java.util.Set that)
             (= (count this) (count that))
             (let [[this-floats this-rest] (split-floats this)
                   [that-floats that-rest] (split-floats that)]
               (and (= this-rest that-rest)
                    (ish? (sort this-floats) (sort that-floats)))))))

  java.util.Map
  (ish? [this that]
    (or (= this that)
        (and (instance? java.util.Map that)
             (= (count this) (count that))
             (let [[this-floats this-rest] (split-floats (keys this))
                   [that-floats that-rest] (split-floats (keys that))]
               (and (= this-rest that-rest)
                    (every? #(ish? (get this %) (get that %)) this-rest)
                    (every? identity
                            (map #(and (ish? %1 %2)
                                       (ish? (get this %1) (get that %2)))
                                 (sort this-floats)
                                 (sort that-floats))))))))

  Object
  (ish? [this that]
    (if (and (.isArray (type this))
             (.isArray (type that)))
      (and (= (alength this) (alength that))
           (every? #(ish? (aget this %) (aget that %))
                   (range (alength this))))
      (= this that))))
