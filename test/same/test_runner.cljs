;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.

;; This test runner is intended to be run from the command line
(ns same.test-runner
  (:require [figwheel.main.testing :refer [run-tests-async]]
            ;; require all the namespaces that you want to test
            [same.core-test]
            [same.diff-test]
            [same.platform-test]))

(defn -main [& _args]
  (run-tests-async 5000))
