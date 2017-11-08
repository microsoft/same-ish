;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.test-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [same-test]
            [same.diff-test]
            [same.platform-test]))

(doo-tests 'same-test
           'same.diff-test
           'same.platform-test)
