;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same
  "Main public API namespace."
  (:require #?(:clj [clojure.test :refer [assert-expr do-report]])
            [same.compare :refer [near-zero]]
            [same.diff :refer [diff]]
            [same.ish :as ish :refer [ish]]))

(defn ish?
  "Compare two or more values, returning true if they are the same-ish.
  ~~~klipse
  (let [two (Math/pow (Math/sqrt 2) 2)]
    [(== 2 two) (ish? 2 two)])
  ~~~
  ~~~klipse
  (ish? {:a 1 :b [1.99999999999999 3]}
        {:a 1.00000000000001 :b [2 3.0]})
  ~~~"
  [left & rights]
  {:pre [(not-empty rights)]}
  (every? (partial ish left) rights))

(defn zeroish?
  "Compare a numeric value to zero, returning true if close.
  ~~~klipse
  (zeroish? 0.0000000001
            :max-diff 1e6)
  ~~~"
  [val & {:keys [max-diff] :or {max-diff 1000.0}}]
  (near-zero val max-diff))

(defn not-zeroish?
  "Compare a numeric value to zero, returning true if not close. Equivalent to `(not (zeroish? ...))`.
  ~~~klipse
  (not-zeroish? 3 :max-diff 1e6)
  ~~~"
  [val & {:keys [max-diff] :or {max-diff 1000.0}}]
  (not (near-zero val max-diff)))

(defn set-comparator!
  "Set the default comparator.
  ~~~klipse
  (set-comparator! (compare-ulp 2.0 100))
  (ish? 0.1 (-> 2 Math/sqrt (Math/pow 2) (- 1.9)))
  ~~~"
  [comparator]
  #?(:clj (alter-var-root #'ish/*comparator* (constantly comparator))
     :cljs (set! ish/*comparator* comparator))
  nil)

(defmacro with-comparator
  "Temporarily replace the default comparator.
  ~~~klipse
  (with-comparator (compare-ulp 100.0 1e9)
    (ish? 1.0 0.9999999))
  ~~~
  ~~~klipse
  (with-comparator ==
    (ish? 1.0 0.9999999999999))
  ~~~"
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
