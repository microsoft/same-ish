(ns ish.core
  (require [clojure.test :refer [assert-expr do-report]]
           [ish.diff :refer [diff]]))

(def ^:dynamic *abs-ish* 1e-6)
(def ^:dynamic *rel-ish* 1e-3)

(defn- ish*
  [f1 f2]
  (let [d (Math/abs (- f1 f2))]
    (or (<= d *abs-ish*)
        (<= d (* *rel-ish* (Math/max (Math/abs f1) (Math/abs f2)))))))

(defn- split-floats
  "Split a collection into a vector of floating point values (of type Float or Double),
  and a set of all other values."
  [coll]
  (reduce (fn [[floats rest] v]
            (if (float? v)
              [(conj floats v) rest]
              [floats (conj rest v)]))
          [[] #{}]
          coll))

(defprotocol Approximate
  (ish? [this that]))

(extend-protocol Approximate
  Double
  (ish? [this that]
    (ish* this that))

  Float
  (ish? [this that]
    (ish* this that))

  clojure.lang.Sequential
  (ish? [this that]
    (or (= this that)
        (and (sequential? that)
             (= (count this) (count that))
             (every? (partial apply ish?)
                     (map vector this that)))))

  clojure.lang.IPersistentSet
  (ish? [this that]
    (or (= this that)
        (and (set? that)
             (= (count this) (count that))
             (let [[this-floats this-rest] (split-floats this)
                   [that-floats that-rest] (split-floats that)]
               (and (= this-rest that-rest)
                    (ish? (sort this-floats) (sort that-floats)))))))

  clojure.lang.Associative
  (ish? [this that]
    (or (= this that)
        (and (instance? clojure.lang.Associative that)
             (= (count this) (count that))
             (let [[this-floats this-rest] (split-floats (keys this))
                   [that-floats that-rest] (split-floats (keys that))]
               (and (= this-rest that-rest)
                    (every? #(ish? (get this %) (get that %)) this-rest)
                    (every? identity
                            (map #(and (ish? %1 %2)
                                       (ish? (get this %1) (get that %2)))
                                 (sort this-floats)
                                 (sort that-floats))))))))

  Object
  (ish? [this that]
    (= this that)))

(defmethod assert-expr 'ish? [msg [_ expected actual]]
  `(let [expected# ~expected
         actual# ~actual
         result# (ish? expected# actual#)]
     (if result#
       (do-report {:type :pass :message ~msg
                   :expected expected# :actual actual#})
       (do-report {:type :fail :message ~msg
                   :expected expected# :actual actual#
                   :diffs [[actual# (diff expected# actual#)]]}))
     result#))
