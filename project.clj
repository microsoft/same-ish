(defproject same/ish "0.1.6-SNAPSHOT"
  :description "A Clojure library for approximate comparison of floating point numbers in tests."

  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}

  :url "https://github.com/Microsoft/same-ish"

  :scm {:name "git"
        :url "https://github.com/Microsoft/same-ish"}

  :deploy-branches ["main"]

  :dependencies []

  :profiles
  {:dev
   {:dependencies [[org.clojure/clojure "1.11.1"]
                   [org.clojure/clojurescript "1.11.60"]
                   [org.clojure/core.rrb-vector "0.1.2"]]}
   :1.7  {:dependencies [[org.clojure/clojure "1.7.0"]]}
   :1.8  {:dependencies [[org.clojure/clojure "1.8.0"]]}
   :1.9  {:dependencies [[org.clojure/clojure "1.9.0"]]}
   :1.10 {:dependencies [[org.clojure/clojure "1.10.1"]]}
   :1.11 {:dependencies [[org.clojure/clojure "1.11.1"]]}
   :clj-kondo {:dependencies [[clj-kondo "2022.06.22"]
                              [com.fasterxml.jackson.core/jackson-core "2.13.3"]]}}

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
            [org.clojure/core.rrb-vector "0.1.2"]

            ;; Clojurescript tests
            [lein-doo "0.1.11"]

            ;; Code coverage
            [lein-cloverage "1.2.4"]

            ;; Code/style checks
            [jonase/eastwood "1.2.4"]
            [lein-cljfmt "0.8.2"]]

  :middleware [ultra.plugin/middleware]

  :aliases {"checks" ["do" "check" ["cljfmt" "check"] "clj-kondo" "eastwood"]
            "clj-kondo" ["with-profile" "+clj-kondo" "run" "-m" "clj-kondo.main" "--lint" "src" "test"]
            "tests" ["do" "with-profile" "+1.11:+1.10:+1.9:+1.8:+1.7" "test," "test" ":slow"]}

  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]

  :deploy-repositories [["releases" {:url "https://repo.clojars.org"
                                     :creds :gpg}]]

  :test-selectors {:default (complement :slow)
                   :slow    :slow}

  :doo {:build "test"
        :paths {:lumo   "./node_modules/.bin/lumo"
                :slimer "./node_modules/.bin/slimerjs"}
        :alias {:default [:lumo]
                :browsers [:chrome :chrome-canary :chrome-headless :safari]
                :all [:default :planck :browsers]
                :broken [:phantom :slimer :rhino :nashorn :node]}}

  :cloverage {:selector [:default]
              :codecov? true}

  :eastwood {:linters [:all]
             :exclude-linters [:keyword-typos
                               :non-clojure-file]})
