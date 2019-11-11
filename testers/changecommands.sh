#!/usr/bin/env bash

replacesymbols=(
 # this needs to be first so second %/$ replaces remaining %symbols
 # TODO: not sure how JAVA_HOME will work in docker check
    '%JAVA_HOME%/$JAVA_HOME'
    '%/$'
    'del/rm'
    'copy/cp'
    ';/:'
    '\\/\/'
)

for s in "${replacesymbols[@]}"
do
    sed -i "s/$s/g" $1
done
