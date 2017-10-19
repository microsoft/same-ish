(ns same
  (require [clojure.test :refer [assert-expr do-report]]
           [same.ish :as si]
           [same.diff :as sd]))

(def ish? si/ish?)

(defmethod assert-expr 'ish? [msg [_ expected actual]]
  `(let [expected# ~expected
         actual# ~actual
         result# (si/ish? expected# actual#)]
     (if result#
       (do-report {:type :pass :message ~msg
                   :expected expected# :actual actual#})
       (do-report {:type :fail :message ~msg
                   :expected expected# :actual actual#
                   :diffs [[actual# (sd/diff expected# actual#)]]}))
     result#))
