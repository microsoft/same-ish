# same/ish - floating point comparisons for tests.

> Equality may perhaps be a right, but no power on earth can ever turn it into a fact.\
> &nbsp; &nbsp; &nbsp; &nbsp; — *Honore de Balzac*

[![Travis CI](https://img.shields.io/travis/Microsoft/same-ish.svg)](https://travis-ci.org/Microsoft/same-ish)
[![Codecov](https://img.shields.io/codecov/c/github/Microsoft/same-ish.svg)](https://codecov.io/gh/Microsoft/same-ish)
[![Dependencies Status](https://versions.deps.co/Microsoft/same-ish/status.svg)](https://versions.deps.co/Microsoft/same-ish)
[![Downloads](https://img.shields.io/clojars/dt/same/ish.svg)](https://clojars.org/same/ish)

You have some functions that return doubles or floats and you want to write some tests.
How do you compare your results to the expected values?
You can't use equality because of rounding errors,
so you have to check if the values are *near* each other.
But how near is near enough?
And what if your numbers are buried inside data structures?

`same/ish` is designed to help with these situations.

## Usage

See the [API docs](https://microsoft.github.io/same-ish/latest) for more detailed instructions
and interactive examples. A brief summary is provided below.

In Leiningen, add the following to your `:dependencies` in `project.clj`:

[![Clojars Project](https://clojars.org/same/ish/latest-version.svg)](https://clojars.org/same/ish)

Then in your test namespace(s), require `same`:
```clojure
(ns foo-test
  (:requre [clojure.test :refer :all]
           [same :refer [ish? zeroish?]]))
```

To compare two numbers, instead of using `=` or `==`, use `ish?`:

```clojure
(defn f [x]
  (* (/ 1. x) x)))

(deftest f-test
  (is (ish? 1.0 (f 49.0))))
```

You can also compare data structures, and they will be compared element-wise, using `ish?`
for floating point types, `==` for other numbers, and `=` for anything else:

```clojure
(defn g [x]
  {:a x
   :b [(* x x) (Math/sqrt x)]))

(deftest g-test
  (is (ish? {:a 23 :b [529 4.7958315233]}
            (g 23.0))))
```

## Requirements

Clojure 1.7.0 or higher is required due to the reader conditionals
required for ClojureScript support.

## References

- [Comparing Floating Point Numbers, 2012 Edition](https://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/)
- [What Every Computer Scientist Should Know About Floating-Point Arithmetic](https://docs.oracle.com/cd/E19957-01/806-3568/ncg_goldberg.html)
- [Floating Point Demystified, Part 1](http://blog.reverberate.org/2014/09/what-every-computer-programmer-should.html)

## Changelog

See [CHANGELOG.md](CHANGELOG.md).

## Contributing

Contributions are welcomed, see [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## License

Copyright (c) Microsoft Corporation. All rights reserved.\
Licensed under the [MIT License](LICENSE).
