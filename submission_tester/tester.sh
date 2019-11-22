tester=BreakoutTester # change this

resultsdir=results

script_location=$(dirname $BASH_SOURCE)
source $script_location/java.sh
source $script_location/unall.sh


unarchive zip
unarchive rar

if [ "$1" == "unzip" ]
then
	exit
fi

text_format="\e[7m" # invert output
tester_location=$(pwd)/$tester
for submission in $(/bin/ls $resultsdir)
do
	output=$resultsdir/$submission/output.txt
	if [ -s $output ]
	then
		echo "$submission: output already exists, delete file to test again"
	else
		submission_location=$(pwd)/$resultsdir/$submission
		echo "$submission: started testing"
		sh $tester/commands-modified.sh $submission_location $tester_location \
			&& echo "$submission: finished testing"  \
			|| echo -e "$text_format$submission\e[0m: compile error. see errors.txt and rerun this script after fixing it" \

	fi
done
