(ns same.test-helpers
  (:require [clojure.test]))

(defn about
  [x & [op]]
  ((or op +) (double x) (Math/ulp (double x))))

(defn java-set
  [& coll]
  (java.util.HashSet. ^java.util.Collection coll))

(defn java-map
  [& {:as map}]
  (java.util.HashMap. ^java.util.Map map))

(def ^:private cloverage*
  (delay
   (try (eval '(var cloverage.coverage/*covered*))
        true
        (catch Throwable _
          false))))

(defn- cloverage?
  []
  @cloverage*)

(defn- deftest-no-cloverage [name body]
  (when-not (cloverage?)
    `(clojure.test/deftest ~name ~@body)))

(defmacro deftest-slow [name & body]
  (deftest-no-cloverage (with-meta name {:slow true}) body))

(defmacro deftest-fail [name & body]
  (deftest-no-cloverage (with-meta name {:fail true}) body))
