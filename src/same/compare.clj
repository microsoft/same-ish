;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.compare)

(defn- near-zero-double
  [f max-diff]
  (<= (double f) (Math/ulp (double max-diff))))

(defn- near-zero-float
  [f max-diff]
  (<= (float f) (Math/ulp (float max-diff))))

(defn near-zero
  [f max-diff]
  (if (instance? Float f)
    (near-zero-float f max-diff)
    (near-zero-double f max-diff)))

(defn- compare-ulp-double
  [f1 f2 max-abs max-ulp]
  (let [f1 (double f1)
        f2 (double f2)]
    (cond
      ;; First check absolute difference (in case we are near zero).
      (<= (Math/abs (- f1 f2)) (double max-abs))
      true

      ;; Either they have different signs, or at least one is NaN.
      (not= (Math/signum f1) (Math/signum f2))
      false

      ;; Only one is infinite, it cannot be close to any finite value.
      (not= (Double/isInfinite f1) (Double/isInfinite f2))
      false

      ;; Otherwise check relative difference in ULPs.
      :else
      (<= ^long (Math/abs ^long (- (Double/doubleToLongBits f1)
                                   (Double/doubleToLongBits f2)))
          (long max-ulp)))))

(defn- compare-ulp-float
  [f1 f2 max-abs max-ulp]
  (let [f1 (float f1)
        f2 (float f2)]
    ;; Same logic as compare-ulp-double, but for (single-precision) floats.
    (cond
      (<= (Math/abs (- f1 f2)) (double max-abs))
      true

      (not= (Math/signum f1) (Math/signum f2))
      false

      (not= (Float/isInfinite f1) (Float/isInfinite f2))
      false

      :else
      (<= ^long (Math/abs (- (Float/floatToIntBits f1)
                             (Float/floatToIntBits f2)))
          (long max-ulp)))))

(defn compare-ulp
  [max-diff max-ulp]
  (let [max-abs-double (Math/ulp (double max-diff))
        max-abs-float  (Math/ulp (float max-diff))]
    (fn [f1 f2]
      (if (instance? Float f1)
        (compare-ulp-float  f1 f2 max-abs-double max-ulp)
        (compare-ulp-double f1 f2 max-abs-float  max-ulp)))))
