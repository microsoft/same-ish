#!/bin/bash

# Unofficial Bash Strict Mode:
set -euo pipefail
IFS=$'\n\t'

die() {
    echo $1 >&2
    exit 1
}

DOCROOT=$1
[[ -z $DOCROOT ]] && die "usage: $0 docroot"

echo "Cleaning up any old docs"
rm -rf $DOCROOT

echo "Cloning gh-pages branch into docs dir"
git clone --branch gh-pages https://github.com/Microsoft/same-ish.git $DOCROOT
