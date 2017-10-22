;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.diff-test
  (require [clojure.test :refer [deftest is]]
           [same.diff :as sd]
           [same.test-helpers :refer [about]]))

(deftest diff-test
  (is (= [nil nil :a]      (sd/diff :a :a)))
  (is (= [:a  :b  nil]     (sd/diff :a :b)))
  (is (= [1.0 2.0 nil]     (sd/diff 1.0 2.0)))
  (is (= [nil nil (about 1)] (sd/diff 1.0 (about 1))))

  (is (= [nil nil nil] (sd/diff nil nil)))
  (is (= [nil :a  nil] (sd/diff nil :a)))
  (is (= [:a  nil nil] (sd/diff :a nil)))

  (is (= [nil nil [1.0 (about 2) (about 3)]]
         (sd/diff [1.0 2.0 3.0] [1.0 (about 2) (about 3)])))

  (is (= [nil nil [1.0 (about 2) (about 3)]]
         (sd/diff (into-array [1.0 2.0 3.0]) (into-array [1.0 (about 2) (about 3)]))))

  (is (= [[nil nil 3.0] [nil nil 3.1] [1.0 (about 2) nil]]
         (sd/diff [1.0 2.0 3.0] [1.0 (about 2) 3.1])))

  (is (= [nil [nil nil nil 4.0] [1.0 (about 2) (about 3)]]
         (sd/diff [1.0 2.0 3.0] [1.0 (about 2) (about 3) 4.0])))

  (is (= [nil nil #{1.0 (about 2) (about 3)}]
         (sd/diff #{1.0 2.0 3.0} #{1.0 (about 2) (about 3)})))

  (is (= [#{3.0} #{3.1} #{1.0 (about 2)}]
         (sd/diff #{1.0 2.0 3.0} #{1.0 (about 2) 3.1})))

  (is (= [{:c 3.0} {:c 3.1} {:a 1.0 :b (about 2) (about 1) :d}]
         (sd/diff {:a 1.0 :b 2.0 :c 3.0 1.0 :d}
                  {:a 1.0 :b (about 2) :c 3.1 (about 1) :d}))))
