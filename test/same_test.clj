;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same-test
  (:require [clojure.test :refer [deftest is testing]]
            [same :refer [ish? zeroish? not-zeroish? with-max-diff]]))

(defn- about
  [x]
  (+ x (Math/ulp (double x))))

(defn- about-
  [x]
  (- x (Math/ulp (double x))))

(deftest scalar-test
  (is (ish? 1.0 1.0))
  (is (ish? 1.0 (about 1)))
  (is (not (ish? 1.0 1.01))))

(deftest multi-test
  (is (ish? 1.0 1.0 1.0))
  (is (ish? 1.0 (about 1) (about- 1)))
  (is (not (ish? 1.0 (about 1) 1.01))))

(deftest vector-test
  (is (ish? [1.0 2.0] [1.0 2.0]))
  (is (ish? [1.0 2.0] [(about 1) 2.0]))
  (is (not (ish? [1.0 2.0] [1.01 2.0]))))

(deftest set-test
  (testing "Sets of doubles"
    (is (ish? #{1.0 2.0} #{1.0 2.0}))
    (is (ish? #{1.0 2.0} #{(about 1) 2.0}))
    (is (not (ish? #{1.0 2.0} #{1.01 2.0}))))
  (testing "Mixed sets"
    (is (ish? #{1.0 2 \b "c" :d} #{1.0 2 \b "c" :d}))
    (is (ish? #{1.0 2 \b "c" :d} #{(about 1) 2 \b "c" :d}))
    (is (not (ish? #{1.0 2 \b "c" :d} #{1.01 2 \b "c" :d}))))
  (testing "Java sets"
    (is (ish? (java.util.HashSet. [1.0 2 \b "c" :d])
              (java.util.HashSet. [1.0 2 \b "c" :d])))
    (is (ish? (java.util.HashSet. [1.0 2 \b "c" :d])
              (java.util.HashSet. [(about 1) 2 \b "c" :d])))
    (is (not (ish? (java.util.HashSet. [1.0 2 \b "c" :d])
                   (java.util.HashSet. [1.01 2 \b "c" :d]))))))

(deftest map-test
  (testing "Maps of keyword-double"
    (is (ish? {:a 1.0 :b 2.0} {:a 1.0 :b 2.0}))
    (is (ish? {:a 1.0 :b 2.0} {:a (about 1) :b 2.0}))
    (is (not (ish? {:a 1.0 :b 2.0} {:a 1.01 :b 2.0}))))
  (testing "Maps with double-type keys"
    (is (ish? {1.0 2.0 3.0 4.0} {1.0 2.0 3.0 4.0}))
    (is (ish? {1.0 2.0 3.0 4.0} {(about 1) 2.0 3.0 4.0}))
    (is (not (ish? {1.0 2.0 3.0 4.0} {1.01 2.0 3.0 4.0}))))
  (testing "Maps with mixed key types"
    (is (ish? {1.0 2 :a "b"} {1.0 2 :a "b"}))
    (is (ish? {1.0 2 :a "b"} {(about 1) 2 :a "b"}))
    (is (not (ish? {1.0 2 :a "b"} {1.01 2 :a "b"}))))
  (testing "Java maps"
    (is (ish? (java.util.HashMap. {1.0 2 :a "b"})
              (java.util.HashMap. {1.0 2 :a "b"})))
    (is (ish? (java.util.HashMap. {1.0 2 :a "b"})
              (java.util.HashMap. {(about 1) 2 :a "b"})))
    (is (not (ish? (java.util.HashMap. {1.0 2 :a "b"})
                   (java.util.HashMap. {1.01 2 :a "b"}))))))

(deftest array-test
  (testing "Arrays of doubles"
    (is (ish? (into-array [1.0 2.0]) (into-array [1.0 2.0])))
    (is (ish? (into-array [1.0 2.0]) (into-array [(about 1) 2.0])))
    (is (not (ish? (into-array [1.0 2.0]) (into-array [1.01 2.0])))))
  (testing "Arrays of mixed types"
    (is (ish? (to-array [1.0 2 \b "c" :d])
              (to-array [1.0 2 \b "c" :d])))
    (is (ish? (to-array [1.0 2 \b "c" :d])
              (to-array [(about 1) 2 \b "c" :d])))
    (is (not (ish? (to-array [1.0 2 \b "c" :d])
                   (to-array [1.01 2 \b "c" :d]))))))

(deftest edge-cases
  (is (not (ish? {0 nil} [nil])))
  (is (not (ish? {nil 0} [nil])))
  (is (not (ish? [1.0 2 :foo] :bar)))
  (is (not (ish? #{1.0 2 :foo} :bar)))
  (is (not (ish? {"a" 1.0 :foo 2} :bar)))
  (is (not (ish? {:a 1.0 :b 2.0 :c 3.0 1.0 :d}
                 {:a 1.0 :b (about 2) :c 3.1 (about 1) :d}))))

(deftest zeroish-test
  (is (zeroish? (- 2.0 (* (Math/sqrt 2.0) (Math/sqrt 2.0)))))
  (is (not (zeroish? (- 2e12 (* (Math/sqrt 2e12) (Math/sqrt 2e12))))))
  (is (zeroish? (- 2e12 (* (Math/sqrt 2e12) (Math/sqrt 2e12)))
                :max-diff 2e12))
  (is (zeroish? (float (- 50.0 (* (float (Math/sqrt 50.0))
                                  (float (Math/sqrt 50.0)))))
                :max-diff 50.0))
  (is (not-zeroish? (double (- 50.0 (* (float (Math/sqrt 50.0))
                                       (float (Math/sqrt 50.0)))))
                    :max-diff 50.0)))

(deftest ^:slow equal-ish
  ;; test that `=` and `ish?` are consistent for lots of types/values
  (let [vals [nil "a" "b" \a \b :a :b 1 2 1.0 (about 1) 2 [] '() #{} {} (into-array [])]
        vals (reduce into
                     vals
                     [(mapcat (fn [v]
                                [[v] (list v) #{v} (into-array [v])])
                              vals)
                      (mapcat (fn [[v1 v2]]
                                (into [[v1 v2] (list v1 v2) {v1 v2} (to-array [v1 v2])]
                                      (if (= v1 v2)
                                        []
                                        [#{v1 v2}])))
                              (for [v1 vals
                                    v2 vals]
                                [v1 v2]))])]
    (doseq [a vals
            b vals]
      (testing (str "Checking " a ", " b)
        (when (= a b)
          (is (ish? a b)))
        (when (not (ish? a b))
          (is (not= a b)))))))

(deftest ^:fail fail-double
  (is (ish? 2.0 3.0)))

(deftest ^:fail fail-vector
  (is (ish? [1 :foo "bar" 1.0 2.0]
            [1 :foo "bar" (about 1) 3.0])))

(deftest ^:fail fail-set
  (is (ish? #{1 :foo "bar" 1.0 2.0}
            #{1 :foo "bar" (about 1) 3.0})))

(deftest ^:fail fail-map
  (is (ish? {:a 1.0 :b 2.0 :c 3.0 1.0 :d}
            {:a 1.0 :b (about 2) :c 3.1 (about 1) :d})))

(deftest ^:fail fail-nildiff
  (is (= [1 nil] [1]))
  (is (ish? [1 nil] [1])))

(deftest ^:fail fail-zeroish
  (is (zeroish? (- 10 10.1))))
