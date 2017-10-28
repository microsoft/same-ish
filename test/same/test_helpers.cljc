;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.test-helpers
  (:require [clojure.test :refer [deftest is]]
            [same.platform :as p]))

(defn unconstant
  "When Eastwood warns about constant test, wrap value in `unconstant` to prevent warning."
  [x]
  x)

(defn about
  [x & [op]]
  ((or op +) (double x) (p/ulp (double x))))

(defn java-set
  [& coll]
  (java.util.HashSet. ^java.util.Collection coll))

(defn java-map
  [& {:as map}]
  (java.util.HashMap. ^java.util.Map map))

(def ^:private cloverage*
  (delay
   #?(:clj (try (eval '(var cloverage.coverage/*covered*))
                true
                (catch Throwable _
                  false))
      :cljs true)))

(defn- cloverage?
  []
  @cloverage*)

(defn- deftest-no-cloverage [name body]
  #?(:clj (when-not (cloverage?)
            `(deftest ~name ~@body))))

(defmacro deftest-slow [name & body]
  (deftest-no-cloverage (with-meta name {:slow true}) body))

(defmacro deftest-fail [name & body]
  (deftest-no-cloverage (with-meta name {:fail true}) body))
