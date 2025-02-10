(defproject same/ish "0.1.8-SNAPSHOT"
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
   {:dependencies [[org.clojure/clojure "1.12.0"]
                   [org.clojure/clojurescript "1.11.132"]
                   [com.bhauman/figwheel-main "0.2.20"]
                   [org.slf4j/slf4j-nop "2.0.16"]
                   [com.bhauman/rebel-readline-cljs "0.1.5"]
                   #_[org.clojure/core.rrb-vector "0.2.0"]]
    :resource-paths ["target"]
    ;; need to add the compiled assets to the :clean-targets
    :clean-targets ^{:protect false} ["target"]}
   :1.7  {:dependencies [[org.clojure/clojure "1.7.0"]]}
   :1.8  {:dependencies [[org.clojure/clojure "1.8.0"]]}
   :1.9  {:dependencies [[org.clojure/clojure "1.9.0"]]}
   :1.10 {:dependencies [[org.clojure/clojure "1.10.3"]]}
   :1.11 {:dependencies [[org.clojure/clojure "1.11.4"]]}
   :1.12 {:dependencies [[org.clojure/clojure "1.12.0"]]}
   :antq {:dependencies [[com.github.liquidz/antq "RELEASE"]]}
   :clj-kondo {:dependencies [[clj-kondo "2025.01.16"]
                              [com.fasterxml.jackson.core/jackson-core "2.18.2"]]}}

  :plugins [;; Nice test output
            #_[venantius/ultra "0.6.0" :exclusions [org.clojure/core.rrb-vector]]
            #_[org.clojure/core.rrb-vector "0.1.2"]

            ;; Code coverage
            [lein-cloverage "1.2.4"]

            ;; Code/style checks
            [lein-cljfmt "0.9.2"]]

  ;;:middleware [ultra.plugin/middleware]

  :aliases {"checks" ["do" "check" ["cljfmt" "check"] "clj-kondo"]
            "clj-kondo" ["with-profile" "+clj-kondo" "run" "-m" "clj-kondo.main" "--lint" "src" "test"]
            "tests" ["with-profile" "+1.12:+1.11:+1.10:+1.9:+1.8:+1.7" "test"]
            "outdated"       ["with-profile" "antq" "run" "-m" "antq.core"]
            "fig:build"      ["trampoline" "run" "-m" "figwheel.main" "--build" "dev" "--repl"]
            "fig:test"       ["run" "-m" "figwheel.main" "-co" "test.cljs.edn" "-m" "same.test-runner"]
            "fig:min"        ["run" "-m" "figwheel.main" "-O" "advanced" "-bo" "dev"]
            "fig:build-once" ["trampoline" "run" "-m" "figwheel.main" "--build-once" "dev"]
            "fig:ci-test"    ["run" "-m" "figwheel.main" "-co" "test-ci.cljs.edn" "-m" "same.test-runner"]}

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

  :cloverage {:selector [:default]
              :lcov? true})
