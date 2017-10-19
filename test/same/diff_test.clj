;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.diff-test
  (require [clojure.test :refer [deftest is]]
           [same.diff :as sd]))

(deftest diff-test
  (is (= [nil nil :a]      (sd/diff :a :a)))
  (is (= [:a  :b  nil]     (sd/diff :a :b)))
  (is (= [1.0 2.0 nil]     (sd/diff 1.0 2.0)))
  (is (= [nil nil 1.00001] (sd/diff 1.0 1.00001)))

  (is (= [nil nil nil] (sd/diff nil nil)))
  (is (= [nil :a  nil] (sd/diff nil :a)))
  (is (= [:a  nil nil] (sd/diff :a nil)))

  (is (= [nil nil [1.0 2.00001 2.99999]]
         (sd/diff [1.0 2.0 3.0] [1.0 2.00001 2.99999])))

  (is (= [nil nil [1.0 2.00001 2.99999]]
         (sd/diff (into-array [1.0 2.0 3.0]) (into-array [1.0 2.00001 2.99999]))))

  (is (= [[nil nil 3.0] [nil nil 3.1] [1.0 2.00001 nil]]
         (sd/diff [1.0 2.0 3.0] [1.0 2.00001 3.1])))

  (is (= [nil [nil nil nil 4.0] [1.0 2.00001 2.99999]]
         (sd/diff [1.0 2.0 3.0] [1.0 2.00001 2.99999 4.0])))

  (is (= [nil nil #{1.0 2.00001 2.99999}]
         (sd/diff #{1.0 2.0 3.0} #{1.0 2.00001 2.99999})))

  (is (= [#{3.0} #{3.1} #{1.0 2.00001}]
         (sd/diff #{1.0 2.0 3.0} #{1.0 2.00001 3.1})))

  (is (= [{:c 3.0} {:c 3.1} {:a 1.0 :b 2.00001 1.00001 :d}]
         (sd/diff {:a 1.0 :b 2.0 :c 3.0 1.0 :d}
                  {:a 1.0 :b 2.00001 :c 3.1 1.00001 :d}))))
