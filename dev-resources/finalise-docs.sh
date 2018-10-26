#!/bin/bash

# Unofficial Bash Strict Mode:
set -euo pipefail
IFS=$'\n\t'

die() {
    echo $1 >&2
    exit 1
}

DOCDIR=$1
[[ -d $DOCDIR ]] || die "Not a directory: $DOCDIR"

DOCROOT=$(dirname $DOCDIR)
DOCVER=$(basename $DOCDIR)

echo "Updating latest link"
rm $DOCROOT/latest
ln -s $DOCVER $DOCROOT/latest

PREAMBLE="(require '[same :refer [set-comparator!]] '[same.ish :refer [default-comparator]]) (set-comparator! default-comparator)"

echo "Inserting data-preambles"
sed -i '' -e 's/\(code class="klipse"\)/\1 data-preamble="'"$PREAMBLE"'"/' $DOCDIR/*.html

echo "Copying images"
cp doc/*.png $DOCDIR/

echo "Generating clojurescript cache"
mkdir $DOCDIR/cache-cljs
lumo -k $DOCDIR/cache-cljs -c $(lein classpath) -e "$(lumo dev-resources/klipse-require.cljs)"

echo "Updating version list landing page"
VERSIONS=$(git tag --list --format='%(refname:short) %(creatordate:short)' --sort=-creatordate)
VERLIST=$(sed -E 's|([^ ]+) ([^ ]+)|<tr><td><a href="\1">\1</a></td><td>\2</td></tr>|' <<< "$VERSIONS" | awk -v RS="" '{gsub (/\n/,"\\n")}1')
awk -v verlist="$VERLIST" '/%%VERSION_LIST_START%%/{p=1;print;print verlist}/%%VERSION_LIST_END%%/{p=0}!p' $DOCROOT/index.html > $DOCROOT/index_new.html
mv $DOCROOT/index_new.html $DOCROOT/index.html
