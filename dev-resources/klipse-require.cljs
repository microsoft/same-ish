(require '[lumo.io :refer [slurp]]
         '[cljs.tools.reader :refer [read-string]])

#_(require '[planck.core :refer [eval slurp]]
         '[cljs.tools.reader :refer [read-string]])

(defn quote-ns
  [[n & _]]
  `(quote ~(symbol n)))

(doseq [form (->> "project.clj"
                  slurp
                  read-string
                  (drop 3)
                  (apply hash-map)
                  :codox
                  :themes
                  second
                  second
                  :klipse/require-statement
                  read-string
                  (drop 2))]
  (println (cons (-> form first name symbol)
                 (map quote-ns (rest form)))))
