cp $1/FacePamphletTester.java $1/work/Assignment7/
cp $1/FacePamphletTest.java $1/work/Assignment7/
cp $1/FacePamphletCanvasTest.java $1/work/Assignment7/
cp $1/FacePamphletProfileTest.java $1/work/Assignment7/
cp $1/FacePamphletDatabaseTest.java $1/work/Assignment7/
cp $1/junit-4.12.jar $1/work/Assignment7/
cp $1/hamcrest-core-1.3.jar $1/work/Assignment7/
cd work
cd Assignment7
javac -cp $1/work/Assignment7/:$1/work/Assignment7/junit-4.12.jar:$1/work/Assignment7/hamcrest-core-1.3.jar:$1/work/Assignment7/acm.jar $1/work/Assignment7/FacePamphlet.java $1/work/Assignment7/FacePamphletConstants.java $1/work/Assignment7/FacePamphletCanvas.java $1/work/Assignment7/FacePamphletProfile.java $1/work/Assignment7/FacePamphletDatabase.java $1/work/Assignment7/FacePamphletTester.java $1/work/Assignment7/FacePamphletTest.java $1/work/Assignment7/FacePamphletCanvasTest.java $1/work/Assignment7/FacePamphletProfileTest.java $1/work/Assignment7/FacePamphletDatabaseTest.java
java -cp "$1/work/Assignment7/:$1/work/Assignment7/junit-4.12.jar:$1/work/Assignment7/hamcrest-core-1.3.jar:$1/work/Assignment7/acm.jar" FacePamphletTester > $1/output.txt