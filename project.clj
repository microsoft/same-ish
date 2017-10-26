(defproject same/ish "0.1.1-SNAPSHOT"
  :description "A Clojure library for approximate comparison of floating point numbers in tests."
  :url "https://github.com/Microsoft/same-ish"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies []
  :profiles
  {:dev
   {:dependencies [[org.clojure/clojure "1.8.0"]]}}
  :plugins [[venantius/ultra "0.5.1" :exclusions [org.clojure/clojure]]
            [lein-cloverage "1.0.9" :exclusions [org.clojure/clojure]]]
  :test-selectors {:default (complement (some-fn :slow :fail))
                   :most    (complement :fail)
                   :slow    :slow
                   :fail    :fail})
