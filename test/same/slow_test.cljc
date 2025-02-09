;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.slow-test
  (:require [clojure.data :as data]
            [clojure.test :refer [is testing deftest]]
            [same.core :refer [ish?]]
            [same.diff :as sd]
            [same.test-helpers :refer [about]]))

(deftest ^:slow equal-ish
  ;; test that `=`, `==`, `ish?` and  `diff` are consistent for various types/values
  (let [vals [nil "a" "b" \a \b :a :b 1 2 1.0 (about 1) 2.0 [] '() #{} {} (into-array [])]
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
            b vals
            :let [d (sd/diff a b)]]
      (testing (str "Checking " a ", " b)
        (if (ish? a b)
          (do ;; same-ish, should not be diff
            (is (nil? (first d)))
            (is (nil? (second d))))
          (do ;; not same-ish, should be some diff
            (is (or (not (nil? (first d)))
                    (not (nil? (second d)))))
            ;; shouldn't be equal either
            (is (not= a b))
            (when (and (number? a) (number? b))
              (is (not (== a b))))))
        ;; when equal, should also be same-ish
        (when (= a b)
          (is (ish? a b)))
        (when (and (number? a) (number? b) (== a b))
          (is (ish? a b)))))))

(deftest ^:slow data-diff
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
