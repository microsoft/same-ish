;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.platform-test
  (:require [clojure.test :refer [deftest is]]
            [same.platform :as p]))

(deftest is-array?-test
  (is (p/is-array? (into-array [1 2 3])))
  (is (p/is-array? (to-array [1 :a "b"])))
  (is (not (p/is-array? [1 2 3]))))

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
  (is (p/is-infinite? 1e400))
  (is (p/is-infinite? -1e400)))

(deftest bit-diff-double-test
  (is (= 1 (p/bit-diff-double 1.0 1.0000000000000002)))
  (is (= 1 (p/bit-diff-double 1.0000000000000002 1.0))))
