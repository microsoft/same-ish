;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.test-helpers
  (:require [same.platform :as p]))

(defn unconstant
  "When Eastwood warns about constant test, wrap value in `unconstant` to prevent warning."
  [x]
  x)

(defn about
  [x & [op]]
  ((or op +) (double x) (p/ulp (double x))))

(def infinity
  #?(:clj Double/POSITIVE_INFINITY
     :cljs js/Infinity))

(def nan
  #?(:clj Double/NaN
     :cljs js/NaN))
