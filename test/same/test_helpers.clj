(ns same.test-helpers)

(defn about
  [x & [op]]
  ((or op +) (double x) (Math/ulp (double x))))

(defn java-set
  [& coll]
  (java.util.HashSet. ^java.util.Collection coll))

(defn java-map
  [& {:as map}]
  (java.util.HashMap. ^java.util.Map map))
