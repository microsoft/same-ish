# same/ish - floating point comparisons for tests.

> Equality may perhaps be a right, but no power on earth can ever turn it into a fact.  
> &nbsp; &nbsp; &nbsp; &nbsp; — *Honore de Balzac*

You have some functions that return doubles or floats and you want to write some tests.
How do you compare your results to the expected values?
You can't use equality because of rounding errors,
so you have to check if the values are *near* each other.
But how near is near enough?
And what if your numbers are buried inside data structures?

`same/ish` is designed to help with these questions.

## Usage

### Installation

In Leiningen, add the following to your `:dependencies` in `project.clj`:
```clojure
[same/ish "0.1.0"]
```

Then in your test namespace(s), require `same`:
```clojure
(ns foo-test
  (:requre [clojure.test :refer :all]
           [same :refer [ish? zeroish?]]))
```

### Requirements



### `ish?`

To compare two doubles, instead of using `=` or `==`, use `ish?`:

```clojure
(deftest foo-test
  (is (==   1.0 (* (/ 1. 49.) 49.)))) ;; fails
  (is (ish? 1.0 (* (/ 1. 49.) 49.)))) ;; succeeds!
```

You can also compare data structures, and they will be comapared element-wise, using `ish?`
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

Notice that tha expected value uses `Ratio`s, but since the result of the matrx inverse is doubles,
they are compared taking rounding errors into account.

### `zeroish?` / `not-zeroish?`

Comparing to zero is a special case, because relative error doesn't work anymore,
so we have to use absolute differences.
Results near zero are often the result of subtracting similar numbers,
and the rounding error is proportional to the size of these original numbers, not the result.

A function is also provided for comparing numbers to zero, where you can specify the size
of the largest number used in the subtraction, so that the absolute difference can be set appropriately:

```clojure
(is (zeroish? (something 123 1e6)
              :max-diff 10000))
```

There is also a negated version `not-zeroish?` for convenience, which is equivalent to `(not (zeroish? ...))`.

### Custom comparators

By default, `same/ish` uses a combination of absolute difference, for numbers near zero,
and difference in units in the last place (ULP) for larger numbers.

Ths can be overridden by changing the comparator using `with-comparator` or `set-somparator!`.

### Test output

If you use [ultra](https://github.com/venantius/ultra)
or [humane-test-output](https://github.com/pjstadig/humane-test-output)
to prettify your test outputs, you will also get diff outputs for tests using `ish?`
as well as those using `=`, e.g.:

<img src="docs/diff.png" width="400">

## Changelog

See [CHANGELOG.md](CHANGELOG.md).

## Contributing

Contributions are welcomed, see [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## License

Copyright (c) Microsoft Corporation. All rights reserved.  
Licensed under the [MIT License](LICENSE).
