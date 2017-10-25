(defproject same/ish "0.1.1-SNAPSHOT"
  :license {:name "MIT License"}
  :description "Floating point comparisons for tests"
  :url "https://github.com/Microsoft/same-ish"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :profiles {:dev {:plugins [[venantius/ultra "0.5.1" :exclusions [org.clojure/clojure]]
                             [lein-cloverage "1.0.9" :exclusions [org.clojure/clojure]]]}}
  :test-selectors {:default (complement (some-fn :slow :fail))
                   :most    (complement :fail)
                   :slow    :slow
                   :fail    :fail})
