;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.compare
  "Default comparator functions."
  (:require [same.platform :as p]))

(defn- near-zero-double
  [f scale]
  (<= (double f) (double (p/ulp (double scale)))))

#?(:clj
   (defn- near-zero-float
     [f scale]
     (<= (float f) (float (p/ulp (float scale))))))

(defn near-zero
  "Test if a number is near zero."
  [f scale]
  #?(:clj (if (instance? Float f)
            (near-zero-float f scale)
            (near-zero-double f scale))
     :cljs (near-zero-double f scale)))

(defn- compare-ulp-double
  [f1 f2 max-abs max-ulp]
  (let [f1 (double f1)
        f2 (double f2)]
    (cond
      ;; First check absolute difference (in case we are near zero).
      (<= (Math/abs (- f1 f2)) (double max-abs))
      true

      ;; Either they have different signs, or at least one is NaN.
      (not= (p/sign f1) (p/sign f2))
      false

      ;; Only one is infinite, it cannot be close to any finite value.
      (not= (p/is-infinite? f1) (p/is-infinite? f2))
      false

      ;; Otherwise check relative difference in ULPs.
      :else
      (<= ^long (p/bit-diff-double f1 f2)
          (long max-ulp)))))

#?(:clj
   (defn- compare-ulp-float
     [f1 f2 max-abs max-ulp]
     (let [f1 (float f1)
           f2 (float f2)]
       ;; Same logic as compare-ulp-double, but for (single-precision) floats.
       (cond
         (<= (Math/abs (- f1 f2)) (double max-abs))
         true

         (not= (p/sign f1) (p/sign f2))
         false

         (not= (p/is-infinite? f1) (p/is-infinite? f2))
         false

         :else
         (<= ^long (p/bit-diff-float f1 f2)
             (long max-ulp))))))

(defn compare-ulp
  "Create a comparator function that compares numbers by ULPs."
  [scale max-ulp]
  #?(:clj
     (let [max-abs-double (p/ulp (double scale))
           max-abs-float  (p/ulp (float scale))]
       (fn [f1 f2]
         (if (instance? Float f1)
           (compare-ulp-float  f1 f2 max-abs-float  max-ulp)
           (compare-ulp-double f1 f2 max-abs-double max-ulp))))
     :cljs
     (let [max-abs (p/ulp scale)]
       (fn [f1 f2]
         (compare-ulp-double f1 f2 max-abs max-ulp)))))
