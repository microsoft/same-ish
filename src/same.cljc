;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same
  "Main public API namespace."
  (:require #?(:clj [clojure.test :refer [assert-expr do-report]])
            [same.compare :refer [near-zero]]
            [same.diff :refer [diff]]
            [same.ish :as ish :refer [ish]]))

(defn ish?
  "Compare one or more values to an expected value, returning true if they are the same-ish.
  The values can be numbers:
  ```klipse
  (let [two (Math/pow (Math/sqrt 2) 2)]
    [(== 2 two) (ish? 2 two)])
  ```
  or data structures:
  ```klipse
  (ish? {:a 1 :b [1.99999999999999 3]}
        {:a 1.00000000000001 :b [2 3.0]})
  ```
  you can also compare more than one value to the expected value:
  ```klipse
  (ish? 1 1.0 0.99999999999999 1.00000000000001 1)
  ```"
  [expected & actuals]
  {:pre [(not-empty actuals)]}
  (every? (partial ish expected) actuals))

(defn zeroish?
  "Compare a numeric value to zero, returning true if close.
  ```klipse
  (zeroish? 0.0000000001
            :scale 1e6)
  ```"
  [val & {:keys [scale] :or {scale 1000.0}}]
  (near-zero val scale))

(defn not-zeroish?
  "Compare a numeric value to zero, returning true if not close. Equivalent to `(not (zeroish? ...))`.
  ```klipse
  (not-zeroish? 3 :scale 1e6)
  ```"
  [val & {:keys [scale] :or {scale 1000.0}}]
  (not (near-zero val scale)))

(defn set-comparator!
  "Set the default comparator.
  ```klipse
  (set-comparator! (compare-ulp 2.0 100))
  (ish? 0.1 (-> 2 Math/sqrt (Math/pow 2) (- 1.9)))
  ```"
  [comparator]
  #?(:clj (alter-var-root #'ish/*comparator* (constantly comparator))
     :cljs (set! ish/*comparator* comparator))
  nil)

(defmacro with-comparator
  "Temporarily replace the default comparator.
  ```klipse
  (with-comparator (compare-ulp 100.0 1e9)
    (ish? 1.0 0.9999999))
  ```
  ```klipse
  (with-comparator ==
    (ish? 1.0 0.9999999999999))
  ```"
  [comparator & body]
  `(binding [ish/*comparator* ~comparator]
     ~@body))

#?(:clj
   (defmethod assert-expr 'ish? [msg [_ expected & actuals]]
     `(let [expected# ~expected
            actuals# ~(vec actuals)
            result# (apply ish? expected# actuals#)]
        (if result#
          (do-report {:type :pass :message ~msg
                      :expected expected# :actual (if (= 1 (count actuals#))
                                                    (first actuals#)
                                                    actuals#)})
          (do-report {:type :fail :message ~msg
                      :expected expected# :actual (if (= 1 (count actuals#))
                                                    (first actuals#)
                                                    actuals#)
                      :diffs (mapv #(vector % (diff expected# %))
                                   actuals#)}))
        result#)))
