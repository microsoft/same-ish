;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.diff-test
  (require [clojure.data :as data]
           [clojure.test :refer [deftest is testing]]
           [same.diff :as sd]
           [same.test-helpers :refer [about deftest-slow]]))

(deftest diff-scalar
  (is (= [nil nil :a]      (sd/diff :a :a)))
  (is (= [:a  :b  nil]     (sd/diff :a :b)))
  (is (= [1.0 2.0 nil]     (sd/diff 1.0 2.0)))
  (is (= [nil nil (about 1)] (sd/diff 1.0 (about 1))))

  (is (= [nil nil nil] (sd/diff nil nil)))
  (is (= [nil :a  nil] (sd/diff nil :a)))
  (is (= [:a  nil nil] (sd/diff :a nil)))

  (is (= [nil false nil]
         (sd/diff nil false))))

(deftest diff-vector
  (is (= [nil nil [1.0 (about 2) (about 3)]]
         (sd/diff [1.0 2.0 3.0] [1.0 (about 2) (about 3)])))

  (is (= [[nil nil 3.0] [nil nil 3.1] [1.0 (about 2)]]
         (sd/diff [1.0 2.0 3.0] [1.0 (about 2) 3.1])))

  (is (= [nil [nil nil nil 4.0] [1.0 (about 2) (about 3)]]
         (sd/diff [1.0 2.0 3.0] [1.0 (about 2) (about 3) 4.0])))

  (is (= [nil [nil nil] [:a]]
         (sd/diff [:a] [:a nil])))

  (is (= [[nil] [false] nil]
         (sd/diff [nil] [false]))))

(deftest diff-nested-vector
  (is (= [[nil [nil nil 6.0] 3.0 [nil 8.0]]
          [nil [nil nil 6.1] 3.1 [nil 8.1]]
          [1.0 [(about 2) 4.0] nil [7.0]]]
         (sd/diff [1.0 [2.0 4.0 6.0] 3.0 [7.0 8.0]]
                  [1.0 [(about 2) 4.0 6.1] 3.1 [7.0 8.1]]))))

(deftest diff-array
  (is (= [nil nil [1.0 (about 2) (about 3)]]
         (sd/diff (into-array [1.0 2.0 3.0]) (into-array [1.0 (about 2) (about 3)])))))

(deftest diff-set
  (is (= [nil nil #{1.0 (about 2) (about 3)}]
         (sd/diff #{1.0 2.0 3.0} #{1.0 (about 2) (about 3)})))

  (is (= [#{3.0} #{3.1} #{1.0 (about 2)}]
         (sd/diff #{1.0 2.0 3.0} #{1.0 (about 2) 3.1})))

  (is (= [#{3.0} nil #{1.0 (about 2)}]
         (sd/diff #{1.0 2.0 3.0} #{1.0 (about 2)})))

  (is (= [#{2.0} #{3.0} #{1.0 4.0}]
         (sd/diff #{1.0 2.0 4.0} #{1.0 3.0 4.0})))

  (is (= [#{1.5} nil #{1.0 (about 2)}]
         (sd/diff #{1.0 2.0 1.5} #{1.0 (about 2)}))))

(deftest diff-map
  (is (= [nil nil {:a 1}]
         (sd/diff {:a 1} {:a 1})))

  (is (= [{:c 3.0} {:c 3.1} {:a 1.0 :b (about 2) (about 1) :d}]
         (sd/diff {:a 1.0 :b 2.0 :c 3.0 1.0 :d}
                  {:a 1.0 :b (about 2) :c 3.1 (about 1) :d})))

  (is (= [{:c 3.0} nil {:a 1.0 :b (about 2) (about 1) :d}]
         (sd/diff {:a 1.0 :b 2.0 :c 3.0 1.0 :d}
                  {:a 1.0 :b (about 2) (about 1) :d})))

  (is (= [nil {3.0 :c} {1.0 :a (about 2) :b}]
         (sd/diff {1.0 :a 2.0 :b} {1.0 :a (about 2) :b 3.0 :c})))

  (is (= [{3.0 :c} nil {1.0 :a (about 2) :b}]
         (sd/diff {1.0 :a 2.0 :b 3.0 :c} {1.0 :a (about 2) :b})))

  (is (= [nil {1.5 :c} {1.0 :a (about 2) :b}]
         (sd/diff {1.0 :a 2.0 :b} {1.0 :a (about 2) :b 1.5 :c})))

  (is (= [{1.5 :c} nil {1.0 :a (about 2) :b}]
         (sd/diff {1.0 :a 2.0 :b 1.5 :c} {1.0 :a (about 2) :b})))

  (is (= [{:b false} {:b nil} {:a 1.0}]
         (sd/diff {:a 1.0 :b false}
                  {:a 1.0 :b nil})))

  (is (= [{2.0 false} {2.0 nil} {1.0 :a}]
         (sd/diff {1.0 :a 2.0 false}
                  {1.0 :a 2.0 nil})))

  (is (= [{{} {}} {:a nil} nil]
         (sd/diff {{} {}} {:a nil}))))

(deftest diff-nested-map
  (is (= [{:c 3.0 :d {:f 2.0}}
          {:c 3.1 :d {:f 3.0}}
          {:a 1.0 :b (about 2) :d {:e (about 1)}}]
         (sd/diff {:a 1.0 :b 2.0 :c 3.0 :d {:e 1.0 :f 2.0}}
                  {:a 1.0 :b (about 2) :c 3.1 :d {:e (about 1) :f 3.0}})))

  (is (= [{:c 3.0 4.0 {:f 2.0}}
          {:c 3.1 4.0 {:f 3.0}}
          {:a 1.0 :b (about 2) 4.0 {:e (about 1)}}]
         (sd/diff {:a 1.0 :b 2.0 :c 3.0 4.0 {:e 1.0 :f 2.0}}
                  {:a 1.0 :b (about 2) :c 3.1 4.0 {:e (about 1) :f 3.0}}))))

(deftest diff-array
  (is (= [nil nil [1.0]]
         (sd/diff (into-array [1.0]) (into-array [1.0]))))
  (is (= [[nil nil 3.1] [nil nil 3.0] [1.0 (about 2)]]
         (sd/diff (into-array [1.0 2.0 3.1]) (into-array [1.0 (about 2) 3.0])))))

(deftest diff-mixed
  (is (= [[1] 1 nil]
         (sd/diff [1] 1)))
  (is (= [#{1} 1 nil]
         (sd/diff #{1} 1)))
  (is (= [{:a 1} 1 nil]
         (sd/diff {:a 1} 1))))

(deftest-slow data-diff
  ;; test that our diff behaves the same as clojure.data/diff for non-floats
  (let [vals [nil :a :b [] '() #{} {} [nil] '(nil) #{nil} [:a] '(:a) #{:a} {nil nil} {:a :b}]
        vals (reduce into
                     vals
                     [(mapcat (fn [v]
                                [[v] (list v) #{v}
                                 [v v] (list v v)])
                              vals)
                      (mapcat (fn [[v1 v2]]
                                (into [[v1 v2] (list v1 v2) {v1 v2}]
                                      (if (= v1 v2)
                                        []
                                        [#{v1 v2}])))
                              (for [v1 vals
                                    v2 vals]
                                [v1 v2]))])]
    (doseq [a vals
            b vals]
      (testing (str "Checking " a ", " b)
        (is (= (data/diff a b)
               (sd/diff a b)))))))
