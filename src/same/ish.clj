;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.ish)

(def ^:dynamic *max-diff*
  "The maximum value used in a subtraction (to deal with catastrophic cancellation)."
  100.0)

(def ^:dynamic *max-ulps*
  "The maximum number of ULPs difference before two numbers are not the same-ish."
  2)

(defn- double-ish
  "Check whether two doubles are close enough to be considered the same-ish."
  [f1 f2]
  (let [f1 (double f1)
        f2 (double f2)]
    (cond
      ;; First check absolute difference (in case we are near zero).
      (<= (Math/abs (- f1 f2)) (Math/ulp (double *max-diff*)))
      true

      ;; Either they have different signs, or at least one is NaN.
      (not= (Math/signum f1) (Math/signum f2))
      false

      ;; Only one is infinite, it cannot be close to any finite value.
      (not= (Double/isInfinite f1) (Double/isInfinite f2))
      false

      ;; Otherwise check relative difference in ULPs.
      :else
      (<= ^long (Math/abs (- (Double/doubleToLongBits f1)
                             (Double/doubleToLongBits f2)))
          (long *max-ulps*)))))

(defn- float-ish
  "Check whether two floats are close enough to be considered the same-ish."
  [f1 f2]
  (let [f1 (float f1)
        f2 (float f2)]
    ;; Same logic as double-ish, but for (single-precision) floats.
    (cond
      (<= (Math/abs (- f1 f2)) (Math/ulp (float *max-diff*)))
      true

      (not= (Math/signum f1) (Math/signum f2))
      false

      (not= (Float/isInfinite f1) (Float/isInfinite f2))
      false

      :else
      (<= ^long (Math/abs (- (Float/floatToIntBits f1)
                             (Float/floatToIntBits f2)))
          (long *max-ulps*)))))

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
         (double-ish this that)))

  Float
  (ish [this that]
    (and (float? that)
         (float-ish this that)))

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
