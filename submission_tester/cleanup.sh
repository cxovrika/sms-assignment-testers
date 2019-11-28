#!/usr/bin/env bash

# if no $1, exit
assignment_dir=$1
if [ -z "$1" ]; then
    echo "argument expected"
    exit
fi
cd $assignment_dir
mkdir -p submission
mv * submission
assignment_file=Breakout.java
found_file=$(find -name $assignment_file | grep -v .git | grep -v .class | head -1)

# error control

if [ -z "$found_file" ]
then
    echo "couldn't find file"
else
    found_dir=$(dirname $found_file)
    echo $found_dir
    run_dir=run
    mkdir -p $run_dir
    cp -r $found_dir/* $run_dir
    rm $run_dir/acm.jar > /dev/null 2>&1
fi
