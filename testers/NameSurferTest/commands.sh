cp $1/NameSurferTester.java $1/work/Assignment6/
cp $1/NameSurferTest.java $1/work/Assignment6/
cp $1/NameSurferGraphTest.java $1/work/Assignment6/
cp $1/NameSurferGraphTester.java $1/work/Assignment6/
cp $1/NameSurferEntryTest.java $1/work/Assignment6/
cp $1/NameSurferDataBaseTest.java $1/work/Assignment6/
cp $1/karel.jar $1/work/Assignment6/
cp $1/junit-4.12.jar $1/work/Assignment6/
cp $1/hamcrest-core-1.3.jar $1/work/Assignment6/
cp $1/names-data-small.txt $1/work/Assignment6/
cd work
cd Assignment6
javac -cp $1/work/Assignment6/junit-4.12.jar:$1/work/Assignment6/hamcrest-core-1.3.jar:$1/work/Assignment6/acm.jar $1/work/Assignment6/NameSurfer.java $1/work/Assignment6/NameSurferGraph.java $1/work/Assignment6/NameSurferEntry.java $1/work/Assignment6/NameSurferDataBase.java $1/work/Assignment6/NameSurferConstants.java $1/work/Assignment6/NameSurferTester.java $1/work/Assignment6/NameSurferTest.java $1/work/Assignment6/NameSurferGraphTest.java $1/work/Assignment6/NameSurferGraphTester.java $1/work/Assignment6/NameSurferEntryTest.java $1/work/Assignment6/NameSurferDataBaseTest.java 
java -cp "$1/work/Assignment6/:$1/work/Assignment6/junit-4.12.jar:$1/work/Assignment6/hamcrest-core-1.3.jar:$1/work/Assignment6/acm.jar" NameSurferTester > $1/output.txt