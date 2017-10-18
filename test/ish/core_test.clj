(ns ish.core-test
  (:require [clojure.test :refer :all]
            [ish.core :refer :all]))

(deftest equal
  (is (ish? 1.0 1.0))
  (is (ish? [1.0 2.0] [1.0 2.0]))
  (is (ish? #{1.0 2.0} #{1.0 2.0}))
  (is (ish? #{1.0 2 \b "c" :d} #{1.0 2 \b "c" :d}))
  (is (ish? {:a 1.0 :b 2.0} {:a 1.0 :b 2.0}))
  (is (ish? {1.0 2.0 3.0 4.0} {1.0 2.0 3.0 4.0}))
  (is (ish? {1.0 2 :a "b"} {1.0 2 :a "b"})))

(deftest approx
  (is (ish? 1.0 1.00001))
  (is (ish? [1.0 2.0] [1.00001 2.0]))
  (is (ish? #{1.0 2.0} #{1.00001 2.0}))
  (is (ish? #{1.0 2 \b "c" :d} #{1.00001 2 \b "c" :d}))
  (is (ish? {:a 1.0 :b 2.0} {:a 1.00001 :b 2.0}))
  (is (ish? {1.0 2.0 3.0 4.0} {1.00001 2.0 3.0 4.0}))
  (is (ish? {1.0 2 :a "b"} {1.00001 2 :a "b"})))

(deftest diff
  (is (not (ish? 1.0 1.01)))
  (is (not (ish? [1.0 2.0] [1.01 2.0])))
  (is (not (ish? #{1.0 2.0} #{1.01 2.0})))
  (is (not (ish? #{1.0 2 \b "c" :d} #{1.01 2 \b "c" :d})))
  (is (not (ish? {:a 1.0 :b 2.0} {:a 1.01 :b 2.0})))
  (is (not (ish? {1.0 2.0 3.0 4.0} {1.01 2.0 3.0 4.0})))
  (is (not (ish? {1.0 2 :a "b"} {1.01 2 :a "b"})))

  (is (not (ish? [1.0 2 :foo] :bar)))
  (is (not (ish? #{1.0 2 :foo} :bar)))
  (is (not (ish? {"a" 1.0 :foo 2} :bar))))

#_(deftest fail
  ;; Uncomment this to see what test failures look like
  (is (ish? [1 :foo "bar" 1.0 2.0]
            [1 :foo "bar" 1.00001 3.0])))
