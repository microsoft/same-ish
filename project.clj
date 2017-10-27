(defproject same/ish "0.1.1-SNAPSHOT"
  :description "A Clojure library for approximate comparison of floating point numbers in tests."
  :url "https://github.com/Microsoft/same-ish"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies []
  :profiles
  {:dev
   {:dependencies [[org.clojure/clojure "1.8.0"]
                   [org.clojure/clojurescript "1.9.946"]
                   [viebel/codox-klipse-theme "0.0.5"]]}}
  :cljsbuild {:builds {:test
                       {:source-paths ["src" "test"]
                        :compiler {:output-to "target/test.js"
                                   :source-map "target/test.js.map"
                                   :output-dir "target/js"
                                   :main same.test-runner
                                   :optimizations :advanced}}
                       :node-test
                       {:source-paths ["src" "test"]
                        :compiler {:output-to "target/test.js"
                                   :source-map "target/test.js.map"
                                   :output-dir "target/js"
                                   :main same.test-runner
                                   :optimizations :advanced
                                   :target :nodejs}}}}
  :plugins [[venantius/ultra "0.5.1" :exclusions [org.clojure/clojure]]
            [lein-cloverage "1.0.9" :exclusions [org.clojure/clojure]]
            [lein-doo "0.1.8" :exclusions [org.clojure/clojure]]
            [lein-codox "0.10.3" :exclusions [org.clojure/clojure]]]
  :test-selectors {:default (complement (some-fn :slow :fail))
                   :most    (complement :fail)
                   :slow    :slow
                   :fail    :fail}
  :doo {:build "test"
        :paths {:slimer "./node_modules/.bin/slimerjs"}
        :alias {:default [:phantom]
                :browsers [:chrome :chrome-canary :chrome-headless :safari]
                :all [:phantom :slimer :rhino :nashorn :browsers]}}
  :codox {:project {:name "same/ish"}
          :metadata {:doc/format :markdown
                     :doc "**FIXME:** write docs"}
          :source-uri "https://github.com/Microsoft/same-ish/blob/{version}/{filepath}#L{line}"
          :output-path "docs"
          :html {:namespace-list :flat}
          :themes [:default [:klipse
                             {:klipse/external-libs
                              #_"https://raw.githubusercontent.com/Microsoft/same-ish/cljs/src"
                              "http://localhost:8000/src"
                              :klipse/require-statement
                              "(ns same-ish.demo
          (:require [same :refer [ish?]]))"}]]})
