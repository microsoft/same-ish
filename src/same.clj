;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same
  (require [clojure.test :refer [assert-expr do-report]]
           [same.ish :refer [ish]]
           [same.diff :refer [diff]]))

(defn ish?
  [left & rights]
  {:pre [(not-empty rights)]}
  (every? (partial ish left) rights))

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
