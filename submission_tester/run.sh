#!/usr/bin/env bash

# TODO symlink java.sh and run.sh in each
source ./java.sh

# if no argument
file=Breakout
javac -cp $(pwd)/acm.jar $file.java
java -cp $(pwd):$(pwd)/acm.jar $file
