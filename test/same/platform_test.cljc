;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.platform-test
  (:require [clojure.test :refer [deftest is]]
            [same.platform :as p]
            [same.test-helpers :refer [infinity nan unconstant]]))

(deftest is-array?-test
  (is (p/is-array? (unconstant (into-array [1 2 3]))))
  (is (p/is-array? (unconstant (to-array [1 :a "b"]))))
  (is (not (p/is-array? [1 2 3]))))

(deftest nan?-test
  (is (p/nan? nan))
  (is (not (p/nan? 0.0)))
  (is (not (p/nan? infinity))))

(deftest sign-test
  (is (=  1.0 (p/sign 10.0)))
  (is (=  0.0 (p/sign 0.0)))
  (is (= -1.0 (p/sign -10.0)))

  (is (=  1.0 (p/sign 1e400)))
  (is (= -1.0 (p/sign -1e400))))

(deftest is-infinite?-test
  (is (not (p/is-infinite? 0.0)))
  (is (not (p/is-infinite? 1e300)))
  (is (not (p/is-infinite? 1e-300)))
  (is (unconstant (p/is-infinite? 1e400)))
  (is (unconstant (p/is-infinite? -1e400))))

(deftest ulp-test
  (doseq [[u v]
          [[4.9e-324                0.0] ;; zero
           [4.9e-324                4.9e-324] ;; smallest non-zero
           [1.2689709186578246e-116 1e-100]
           [1.1102230246251565e-16  0.9999999999999999] ;; largest < 1
           [2.220446049250313e-16   1.0]
           [1.942668892225729e84    1e100]
           [1.9958403095347198e292  1.7976931348623157e308] ;; largest finite
           [infinity                infinity]]]
    (is (= u (p/ulp v)))
    (is (= u (p/ulp (- v)))))
  (is (p/nan? (p/ulp nan))))

(deftest bit-diff-double-test
  (is (zero? (p/bit-diff-double 1.0 1.0)))
  (is (= 1 (p/bit-diff-double 1.0 1.0000000000000002)))
  (is (= 1 (p/bit-diff-double 1.0000000000000002 1.0)))
  (is (= 0x8000000000000 (p/bit-diff-double 1.0 1.5)))
  (is (= 0x8000000000000 (p/bit-diff-double 1.5 1.0)))
  (is (= 0x10000000000000 (p/bit-diff-double 1.0 2.0)))
  (is (= 0x10000000000000 (p/bit-diff-double 2.0 1.0)))
  (is (= 0x100000000000000 (p/bit-diff-double 1.0 65536.0)))
  (is (= 0x100000000000000 (p/bit-diff-double 65536.0 1.0))))

(deftest bit-diff-float-test
  (is (zero? (p/bit-diff-float 1.0 1.0)))
  (is (= 1 (p/bit-diff-float 1.0 1.0000001)))
  (is (= 1 (p/bit-diff-float 1.0000001 1.0)))
  (is (= 0x400000 (p/bit-diff-float 1.0 1.5)))
  (is (= 0x400000 (p/bit-diff-float 1.5 1.0)))
  (is (= 0x800000 (p/bit-diff-float 1.0 2.0)))
  (is (= 0x800000 (p/bit-diff-float 2.0 1.0)))
  (is (= 0x8000000 (p/bit-diff-float 1.0 65536.0)))
  (is (= 0x8000000 (p/bit-diff-float 65536.0 1.0))))
