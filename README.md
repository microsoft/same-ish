# same/ish - floating point comparisons for tests.

> Equality may perhaps be a right, but no power on earth can ever turn it into a fact.\
> &nbsp; &nbsp; &nbsp; &nbsp; — *Honore de Balzac*

[![cljdoc](https://cljdoc.org/badge/same/ish)](https://cljdoc.org/d/same/ish)
[![Build status](https://github.com/microsoft/same-ish/actions/workflows/build.yml/badge.svg?branch=main&event=push)](https://github.com/microsoft/same-ish/actions/workflows/build.yml)
[![Coverage](https://coveralls.io/repos/github/microsoft/same-ish/badge.svg?branch=main)](https://coveralls.io/github/microsoft/same-ish?branch=main)
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

See the full API docs are available on [cljdoc](https://cljdoc.org/d/same/ish) for more detailed instructions and examples.
A brief summary is provided below.

In Leiningen, add the following to your `:dependencies` in `project.clj`:

[![Clojars Project](https://clojars.org/same/ish/latest-version.svg)](https://clojars.org/same/ish)

Then in your test namespace(s), require `same.core`:
```clojure
(ns foo-test
  (:requre [clojure.test :refer :all]
           [same.core :refer [ish? zeroish?]]))
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
- [Floating Point Demystified, Part 2](https://blog.reverberate.org/2016/02/06/floating-point-demystified-part2.html)

## Changelog

See [CHANGELOG.md](CHANGELOG.md).

## Contributing

Contributions are welcomed, see [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## License

Copyright © Microsoft Corporation. All rights reserved.\
Licensed under the [MIT License](LICENSE).
