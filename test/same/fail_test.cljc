;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.fail-test
  (:require [clojure.test :refer [is]]
            [same :refer [ish? zeroish?]]
            [same.test-helpers :refer [about deftest-fail]]))

(deftest-fail fail-double
  (is (ish? 2.0 3.0)))

(deftest-fail fail-vector
  (is (ish? [1 :foo "bar" 1.0 2.0]
            [1 :foo "bar" (about 1) 3.0])))

(deftest-fail fail-nested-vector
  (is (ish? [1 :foo ["bar" 1.0]       2.0       4.4]
            [1 :foo ["bar" (about 1)] (about 2) 3.0])))

(deftest-fail fail-set
  (is (ish? #{1 :foo "bar" 1.0 2.0}
            #{1 :foo "bar" (about 1) 3.0})))

(deftest-fail fail-map
  (is (ish? {:a 1.0 :b 2.0 :c 3.0 1.0 :d}
            {:a 1.0 :b (about 2) :c 3.1 (about 1) :d})))

(deftest-fail fail-nildiff
  (is (= [1 nil] [1]))
  (is (ish? [1 nil] [1])))

(deftest-fail fail-zeroish
  (is (zeroish? (- 10 10.1))))
