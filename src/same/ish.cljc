;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.ish
  "Comparing different types for same-ish-ness."
  (:require [same.compare :refer [compare-ulp]]
            [same.platform :as p]))

(def default-comparator
  "The default comparator. Stored in a separate var to make it easier to reset after
  modifying with [[set-comparator!]]"
  (compare-ulp 100.0 2))

(def ^:dynamic *comparator*
  "The function for comparing individual floats/doubles.
  Can be overridden using [[with-comparator]] or [[set-comparator!]]."
  default-comparator)

;; Only public so that it can be called from same.diff
(defn ^:no-doc split-floats
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
  "Protocol for approximately comparing any types (using [[*comparator*]] for floating point parts)."
  (ish [this that]
    "Return true if the two arguments are approximately equal."))

#?(:clj
   (extend-protocol Approximate
     nil
     (ish [_ that]
       (nil? that))

     Double
     (ish [this that]
       (and (number? that)
            (*comparator* this that)))

     Float
     (ish [this that]
       (and (number? that)
            (*comparator* this that)))

     Number
     (ish [this that]
       (cond (float? that)  (*comparator* (double this) that)
             (number? that) (== ^Number this ^Number that)
             :else          (= this that)))

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
           (and (instance? #?(:clj java.util.Set :cljs ISet) that)
                (= (count this) (count that))
                (let [[this-floats this-rest] (split-floats this)
                      [that-floats that-rest] (split-floats that)]
                  (and (= this-rest that-rest)
                       (ish (sort this-floats) (sort that-floats)))))))

     java.util.Map
     (ish [this that]
       (or (= this that)
           (and (instance? #?(:clj java.util.Map :cljs IMap) that)
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
       (if (and that (p/is-array? this) (p/is-array? that))
         (and (= (count this) (count that))
              (every? identity
                      (map ish this that)))
         (= this that))))

   :cljs
   (extend-protocol Approximate
     nil
     (ish [this that]
       (nil? that))

     number
     (ish [this that]
       (if (number? that)
         (*comparator* this that)
         (= this that)))

     boolean
     (ish [this that]
       (= this that))

     array
     (ish [this that]
       (and (p/is-array? that)
            (= (count this) (count that))
            (every? identity
                    (map ish this that))))

     string
     (ish [this that]
       (= this that))

     object
     (ish [this that]
       (cond
         (= this that)
         true

         (and (sequential? this) (sequential? that))
         (and (= (count this) (count that))
              (every? (partial apply ish)
                      (map vector this that)))

         (and (map? this) (map? that))
         (and (= (count this) (count that))
              (let [[this-floats this-rest] (split-floats (keys this))
                    [that-floats that-rest] (split-floats (keys that))]
                (and (= this-rest that-rest)
                     (every? #(ish (get this %) (get that %)) this-rest)
                     (every? identity
                             (map #(and (ish %1 %2)
                                        (ish (get this %1) (get that %2)))
                                  (sort this-floats)
                                  (sort that-floats))))))

         (and (set? this) (set? that))
         (and (= (count this) (count that))
              (let [[this-floats this-rest] (split-floats this)
                    [that-floats that-rest] (split-floats that)]
                (and (= this-rest that-rest)
                     (ish (sort this-floats) (sort that-floats)))))

         :else
         false))))
