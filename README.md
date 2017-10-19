# same/ish

A Clojure library for approximate comparison of floating point types in tests

## Usage

```clojure
(ns foo-test
  (:require [same :refer [ish?]]))

(deftest foo-test
  (is (ish? 1.0 0.99999)))
```
