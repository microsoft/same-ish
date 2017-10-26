;; Copyright (c) Microsoft Corporation. All rights reserved.
;; Licensed under the MIT License.
(ns same.test-runner
  (:require [doo.runner :refer-macros [doo-tests doo-all-tests]]
            [same-test]
            [same.diff-test]
            [same.platform-test]
            [same.test-helpers]))

(doo-all-tests)
