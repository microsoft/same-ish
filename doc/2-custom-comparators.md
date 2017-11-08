# Custom Comparators

By default, `same/ish` uses a combination of absolute difference, for numbers near zero,
and difference in units in the last place (ULP) for larger numbers.

Ths can be overridden by changing the comparator using `with-comparator` or `set-somparator!`.
