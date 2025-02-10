# Change Log
All notable changes to this project will be documented in this file.
The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## Versions

## [Unreleased]

## [0.1.7] - 2025-02-09
- Remove deprecated single-segment namespace
- Fix accidental inclusion of compiled artifacts in jar

## [0.1.6] - 2023-01-23
- Mark deprecated single-segment namespace with ^:deprecated metadata
- Move clojurescript build to Figwheel.main
- Remove need for `:include-macros true` in cljs (thanks to [sritchie](https://github.com/sritchie))

## [0.1.5] - 2022-07-16
- Deprecate single-segment `same` namespace in favour of `same.core`
- Fix `zeroish?` so it doesn't treat all negative numbers as zero-ish (thanks [mjmeintjes](//github.com/mjmeintjes) for noticing)

## [0.1.4] - 2020-05-13
- Update dependencies
- Rename the confusing `max-diff` parameter to `scale` (BREAKING if you used it, but most people probably didn't)

## [0.1.3] - 2019-10-29
- Fix `ulp` of zero (no effect on normal usage)
- Update to Clojure(script) 1.10 and fix tests on JDK9+ (thanks to [@dl1ely](//github.com/dl1ely))

## [0.1.2] - 2018-10-26
- Updated plugins and dependencies to latest versions
- Fixed documentation typos (thanks to [@dl1ely](//github.com/dl1ely) and [@baumandm](//github.com/baumandm))

## [0.1.1] - 2018-05-24
- ClojureScript support
- Interactive documentation (with Codox + Klipse)

## [0.1.0] - 2017-10-25
Initial release.

[Unreleased]: https://github.com/Microsoft/same-ish/compare/0.1.7...main
[0.1.7]: https://github.com/Microsoft/same-ish/compare/0.1.6...0.1.7
[0.1.6]: https://github.com/Microsoft/same-ish/compare/0.1.5...0.1.6
[0.1.5]: https://github.com/Microsoft/same-ish/compare/0.1.4...0.1.5
[0.1.4]: https://github.com/Microsoft/same-ish/compare/0.1.3...0.1.4
[0.1.3]: https://github.com/Microsoft/same-ish/compare/0.1.2...0.1.3
[0.1.2]: https://github.com/Microsoft/same-ish/compare/0.1.1...0.1.2
[0.1.1]: https://github.com/Microsoft/same-ish/compare/0.1.0...0.1.1
[0.1.0]: https://github.com/Microsoft/same-ish/compare/initial...0.1.0
