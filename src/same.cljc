;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same
  "Main public API namespace."
  (:require [clojure.test :refer [assert-expr do-report]]
            [same.compare :refer [near-zero]]
            [same.diff :refer [diff]]
            [same.ish :as ish :refer [ish]]))

(defn ish?
  [left & rights]
  {:pre [(not-empty rights)]}
  (every? (partial ish left) rights))

(defn zeroish?
  [val & {:keys [max-diff] :or {max-diff 1000.0}}]
  (near-zero val max-diff))

(defn not-zeroish?
  [val & {:keys [max-diff] :or {max-diff 1000.0}}]
  (not (near-zero val max-diff)))

(defn set-comparator!
  [comparator]
  #?(:clj (alter-var-root #'ish/*comparator* (constantly comparator))
     :cljs (set! ish/*comparator* comparator)))

#?(:clj
   (defmacro with-comparator
     "Temporarily replace the comparator."
     [comparator & body]
     `(binding [ish/*comparator* ~comparator]
        ~@body)))

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
