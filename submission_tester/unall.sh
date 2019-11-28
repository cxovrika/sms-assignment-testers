#!/usr/bin/env bash

default_destination=unarchived
if [ -z "$resultsdir" ]
then
    if [ -z "$1" ]
    then
        echo "no arguments were passed"
        mkdir -p $default_destination
        resultsdir=$default_destination
    else
        resultsdir=$1
    fi
fi

# echo $resultsdir
function unrar_command() {
	unrar x $1 $2
}

function unzip_command() {
	unzip $1 -d $2
}

function unarchive() {
	format=$1
	for file in $(/bin/ls *.$format 2> /dev/null)
	do
		id=$(basename $file .$format)
		destination=$resultsdir/$id
		if [ -d $destination ]
		then
			echo "skipping un$format for $id, remove destionation dir to change this"
		else
			mkdir $destination
#			echo "un$format $file $destination"
			command=un"$format"_command
		    $command $file $destination > /dev/null 2>&1
		fi
	done
}
