;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same
  {:deprecated "0.1.5" :superseded-by "same.core"}
  (:require [same.core]))

(def ish? same.core/ish?)
(def zeroish? same.core/zeroish?)
(def not-zeroish? same.core/not-zeroish?)
(def set-comparator! same.core/set-comparator!)
(defmacro with-comparator
  [& args]
  `(same.core/with-comparator ~@args))
