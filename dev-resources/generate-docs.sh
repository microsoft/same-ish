#!/bin/bash
# Unofficial Bash Strict Mode:
set -euo pipefail
IFS=$'\n\t'

DIR=target/docs

echo "Cleaning up any old docs"
rm -rf $DIR

echo "Generating docs"
lein codox

echo "Inserting data-preambles"
sed -i '' -e 's/\(code class="klipse"\)/\1 data-preamble="(set-comparator! default-comparator)"/' $DIR/*.html

echo "Copying images"
cp doc/*.png $DIR/

echo "Generating clojurescript cache"
mkdir $DIR/cache-cljs
lumo -k $DIR/cache-cljs -c $(lein classpath) -e "$(lumo dev-resources/klipse-require.cljs)"

echo "Done"
