(ns ish.diff
  (:require [clojure.data :as data]))

(defn diff
  [a b]
  (data/diff a b))
