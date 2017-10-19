(ns same-test
  (:require [clojure.test :refer :all]
            [same :refer [ish?]]))

(deftest equal
  (is (ish? 1.0 1.0))
  (is (ish? [1.0 2.0] [1.0 2.0]))
  (is (ish? #{1.0 2.0} #{1.0 2.0}))
  (is (ish? #{1.0 2 \b "c" :d} #{1.0 2 \b "c" :d}))
  (is (ish? (java.util.HashSet. [1.0 2 \b "c" :d])
            (java.util.HashSet. [1.0 2 \b "c" :d])))
  (is (ish? {:a 1.0 :b 2.0} {:a 1.0 :b 2.0}))
  (is (ish? {1.0 2.0 3.0 4.0} {1.0 2.0 3.0 4.0}))
  (is (ish? {1.0 2 :a "b"} {1.0 2 :a "b"}))
  (is (ish? (java.util.HashMap. {1.0 2 :a "b"})
            (java.util.HashMap. {1.0 2 :a "b"})))
  (is (ish? (into-array [1.0 2.0]) (into-array [1.0 2.0])))
  (is (ish? (to-array [1.0 2 \b "c" :d])
            (to-array [1.0 2 \b "c" :d]))))

(deftest approx
  (is (ish? 1.0 1.00001))
  (is (ish? [1.0 2.0] [1.00001 2.0]))
  (is (ish? #{1.0 2.0} #{1.00001 2.0}))
  (is (ish? #{1.0 2 \b "c" :d} #{1.00001 2 \b "c" :d}))
  (is (ish? (java.util.HashSet. [1.0 2 \b "c" :d])
            (java.util.HashSet. [1.00001 2 \b "c" :d])))
  (is (ish? {:a 1.0 :b 2.0} {:a 1.00001 :b 2.0}))
  (is (ish? {1.0 2.0 3.0 4.0} {1.00001 2.0 3.0 4.0}))
  (is (ish? {1.0 2 :a "b"} {1.00001 2 :a "b"}))
  (is (ish? (java.util.HashMap. {1.0 2 :a "b"})
            (java.util.HashMap. {1.00001 2 :a "b"})))
  (is (ish? (into-array [1.0 2.0]) (into-array [1.00001 2.0])))
  (is (ish? (to-array [1.0 2 \b "c" :d]) (to-array [1.00001 2 \b "c" :d]))))

(deftest notequal
  (is (not (ish? 1.0 1.01)))
  (is (not (ish? [1.0 2.0] [1.01 2.0])))
  (is (not (ish? #{1.0 2.0} #{1.01 2.0})))
  (is (not (ish? #{1.0 2 \b "c" :d} #{1.01 2 \b "c" :d})))
  (is (not (ish? (java.util.HashSet. [1.0 2 \b "c" :d])
                 (java.util.HashSet. [1.01 2 \b "c" :d]))))
  (is (not (ish? {:a 1.0 :b 2.0} {:a 1.01 :b 2.0})))
  (is (not (ish? {1.0 2.0 3.0 4.0} {1.01 2.0 3.0 4.0})))
  (is (not (ish? {1.0 2 :a "b"} {1.01 2 :a "b"})))
  (is (not (ish? (java.util.HashMap. {1.0 2 :a "b"})
                 (java.util.HashMap. {1.01 2 :a "b"}))))
  (is (not (ish? (into-array [1.0 2.0]) (into-array [1.01 2.0]))))
  (is (not (ish? (to-array [1.0 2 \b "c" :d])
                 (to-array [1.01 2 \b "c" :d]))))

  (is (not (ish? {0 nil} [nil])))
  (is (not (ish? {nil 0} [nil])))
  (is (not (ish? [1.0 2 :foo] :bar)))
  (is (not (ish? #{1.0 2 :foo} :bar)))
  (is (not (ish? {"a" 1.0 :foo 2} :bar))))

#_(deftest equal-ish
    (let [vals [nil "a" "a" "b" \a \a \b :a :a :b 1 1 2 1.0 1.0 1.00001 2 [] '() #{} {} (into-array [])]
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
                                      v2 vals
                                      :when (not= v1 v2)]
                                  [v1 v2]))])]
      (doseq [a vals
              b vals]
        (when (= a b)
          (is (ish? a b)))
        (when (not (ish? a b))
          (is (not= a b))))))

#_(deftest fail
  ;; Uncomment this to see what test failures look like
  (is (ish? 2.0 3.0))
  (is (ish? [1 :foo "bar" 1.0 2.0]
            [1 :foo "bar" 1.00001 3.0]))
  (is (ish? #{1 :foo "bar" 1.0 2.0}
            #{1 :foo "bar" 1.00001 3.0})))
