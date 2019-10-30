# Getting Started

## Comparing values

To compare two doubles, instead of using `=` or `==`, use `ish?`:

```clojure
(ns demo.ish-test
  (:require [clojure.test :refer :all]
            [same :refer [ish?]]))

(deftest ish-test
  (let [one-ish (-> 1.0 (/ 49) (* 49))]
    (is (==   one-ish 1.0)) ;; fails
    (is (ish? one-ish 1.0)))) ;; succeeds!

(ish-test)
```

You can also compare data structures, and they will be compared element-wise, using `ish?`
for floating point types, `==` for other numbers, and `=` for anything else:

```clojure
(ns demo.matrix-test
  (:require [clojure.core.matrix :as m]
            [clojure.test :refer :all]
            [same :refer [ish?]]))

(deftest matrix-test
  (is (ish? [[-17/24   7/24  1/24]
             [  1/8    1/8  -1/8]
             [ 29/24 -19/24 11/24]]
            (m/inverse [[1 4 1]
                        [5 9 2]
                        [6 5 3]]))))
```

Notice that the expected value uses `Ratio`s, but since the result of the matrx inverse is doubles,
they are compared taking rounding errors into account.

## Comparing to zero

Comparing to zero is a special case, because relative error doesn't work anymore,
so we have to use absolute differences.
Results near zero are often the result of subtracting similar numbers,
and the rounding error is proportional to the size of these original numbers, not the result.

A function is also provided for comparing numbers to zero, where you can specify the size
of the largest number used in the subtraction, so that the absolute difference can be set appropriately:

```clojure
(is (zeroish? (something 123 1e6)
              :scale 10000))
```

There is also a negated version `not-zeroish?` for convenience, which is equivalent to `(not (zeroish? ...))`.
