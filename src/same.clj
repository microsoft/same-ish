;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same
  (require [clojure.test :refer [assert-expr do-report]]
           [same.ish :as ish :refer [ish]]
           [same.diff :refer [diff]]))

(defmacro with-max-diff
  [maxval & body]
  `(binding [ish/*max-diff* (double ~maxval)]
     ~@body))

(defmacro with-max-ulps
  [maxval & body]
  `(binding [ish/*max-ulps* (long ~maxval)]
     ~@body))

(defn ish?
  [left & rights]
  {:pre [(not-empty rights)]}
  (every? (partial ish left) rights))

(defn zeroish?
  [val & {:keys [max-diff] :or {max-diff 1000.0}}]
  (with-max-diff max-diff
    (ish (if (instance? Float val)
           (float 0.0)
           0.0)
         val)))

(defn not-zeroish?
  [val & {:keys [max-diff] :or {max-diff 1000.0}}]
  (with-max-diff max-diff
    (not
     (ish (if (instance? Float val)
            (float 0.0)
            0.0)
          val))))

(defn set-max-diff!
  [maxval]
  (alter-var-root #'ish/*max-diff* (constantly (double maxval))))

(defn set-max-ulps!
  [maxulps]
  (alter-var-root #'ish/*max-ulps* (constantly (long maxulps))))

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
     result#))
