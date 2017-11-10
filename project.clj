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
                                   :optimizations :advanced
                                   :checked-arrays :warn}}
                       :node-test
                       {:source-paths ["src" "test"]
                        :compiler {:output-to "target/test.js"
                                   :source-map "target/test.js.map"
                                   :output-dir "target/js"
                                   :main same.test-runner
                                   :optimizations :advanced
                                   :checked-arrays :warn
                                   :target :nodejs}}}}
  :plugins [[venantius/ultra "0.5.1" :exclusions [org.clojure/clojure]]
            [jonase/eastwood "0.2.5" :exclusions [org.clojure/clojure]]
            [lein-cloverage "1.0.9" :exclusions [org.clojure/clojure]]
            [lein-doo "0.1.8" :exclusions [org.clojure/clojure]]
            [lein-codox "0.10.3" :exclusions [org.clojure/clojure]]
            [lein-pprint "1.2.0" :exclusions [org.clojure/clojure]]]
  :test-selectors {:default (complement (some-fn :slow :fail))
                   :most    (complement :fail)
                   :slow    :slow
                   :fail    :fail}
  :doo {:build "test"
        :paths {:slimer "./node_modules/.bin/slimerjs"}
        :alias {:default [:phantom]
                :browsers [:chrome :chrome-canary :chrome-headless :safari]
                :all [:phantom #_:slimer :rhino :nashorn :browsers]}}
  :eastwood {:linters [:all]
             :exclude-linters [:keyword-typos
                               :non-clojure-file]}
  :codox {:project {:name "same/ish"}
          :metadata {:doc/format :markdown
                     :doc "**FIXME:** write docs"}
          :source-uri "https://github.com/Microsoft/same-ish/blob/{version}/{filepath}#L{line}"
          :output-path "target/docs"
          :html {:namespace-list :flat}
          :themes
          [:default
           [:klipse
            {:klipse/external-libs
             #_"https://raw.githubusercontent.com/Microsoft/same-ish/{VERSION}/src"
             "http://localhost:8000/src"
             :klipse/cached-macro-ns-regexp #"/same|same\..*/"
             :klipse/cached-ns-regexp #"/same|same\..*/"
             :klipse/bundled-ns-ignore-regexp #"/same|same\..*/"
             :klipse/cached-ns-root "./cache-cljs"
             :klipse/require-statement
             "(ns same.klipse
          (:require-macros [same :refer [with-comparator]])
          (:require [same :refer [ish? zeroish? not-zeroish? set-comparator!]]
                    [same.compare :refer [compare-ulp]]
                    [same.ish :refer [default-comparator]]))"}]]})
(def project (update-in project [:codox :themes 1 1 :klipse/external-libs]
                        clojure.string/replace "{VERSION}" (project :version)))
