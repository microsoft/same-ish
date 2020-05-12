(defproject same/ish "0.1.4-SNAPSHOT"
  :description "A Clojure library for approximate comparison of floating point numbers in tests."

  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}

  :url "https://github.com/Microsoft/same-ish"

  :scm {:name "git"
        :url "https://github.com/Microsoft/same-ish"}

  :deploy-branches ["master"]

  :dependencies []

  :profiles
  {:dev
   {:dependencies [[org.clojure/clojure "1.10.1"]
                   [org.clojure/clojurescript "1.10.758"]
                   [viebel/codox-klipse-theme "0.0.5"]
                   [org.clojure/core.rrb-vector "0.1.1"]]}
   :1.7  {:dependencies [[org.clojure/clojure "1.7.0"]]}
   :1.8  {:dependencies [[org.clojure/clojure "1.8.0"]]}
   :1.9  {:dependencies [[org.clojure/clojure "1.9.0"]]}
   :1.10 {:dependencies [[org.clojure/clojure "1.10.1"]]}}
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

  :plugins [;; Nice test output
            [venantius/ultra "0.6.0" :exclusions [org.clojure/core.rrb-vector]]
            [org.clojure/core.rrb-vector "0.1.1"]

            ;; Clojurescript tests
            [lein-doo "0.1.11"]

            ;; Code coverage
            [lein-cloverage "1.1.2"]

            ;; Documentation
            [lein-codox "0.10.7"]

            ;; Run shell commands for doc generation
            [lein-shell "0.5.0"]

            ;; Code/style checks
            [jonase/eastwood "0.3.5"]
            [lein-cljfmt "0.6.4"]]

  :middleware [ultra.plugin/middleware]

  :aliases {"checks" ["do" "check" ["cljfmt" "check"] "eastwood"]
            "tests" ["with-profile" "+1.10:+1.9:+1.8:+1.7" "test"]
            "docs" ["do"
                    ["shell" "dev-resources/prepare-docs.sh" "target/docs"]
                    "codox"
                    ["shell" "dev-resources/finalise-docs.sh" "target/docs/${:version}"]]
            "deploy-docs" ["do"
                           ["shell" "git" "-C" "target/docs" "add" "."]
                           ["shell" "git" "-C" "target/docs" "commit" "-m" "Documentation for ${:version}"]
                           ["shell" "git" "-C" "target/docs" "push"]]}

  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag"]
                  ["docs"]
                  ["deploy"]
                  ["deploy-docs"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]

  :deploy-repositories [["releases" {:url "https://repo.clojars.org"
                                     :creds :gpg}]]

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
          :output-path "target/docs/{VERSION}"
          :html {:namespace-list :flat}
          :themes
          [:default
           [:klipse
            {:klipse/external-libs
             "https://raw.githubusercontent.com/Microsoft/same-ish/{VERSION}/src"
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

(def project (-> project
                 (update-in [:codox :output-path]
                            clojure.string/replace "{VERSION}" (project :version))
                 (update-in [:codox :themes 1 1 :klipse/external-libs]
                            clojure.string/replace "{VERSION}" (project :version))))
