tester=Assignment2SimpleJavaTester # change this

resultsdir=results
if [ -d $resultsdir ]
then
	echo "skipping unrar, delete $resultsdir directory to change this"
else
	mkdir -p $resultsdir
	for file in $(/bin/ls *.rar)
	do
		id=$(basename $file .rar)
		mkdir $resultsdir/$id
		unrar x $file $resultsdir/$id/
	done
	for file in $(/bin/ls *.zip)
	do
		id=$(basename $file .zip)
		mkdir $resultsdir/$id
		unzip $file -d $resultsdir/$id/
	done
fi

format="\e[7m" # invert output
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
		|| echo -e "$format$submission\e[0m: compile error. see errors.txt and rerun this script after fixing it" \
		 
	fi
done



	
