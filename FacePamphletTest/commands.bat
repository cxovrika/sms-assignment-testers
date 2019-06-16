copy FacePamphletTester.java .\work\Assignment7\
copy FacePamphletTest.java .\work\Assignment7\
copy FacePamphletCanvasTest.java .\work\Assignment7\
copy FacePamphletProfileTest.java .\work\Assignment7\
copy FacePamphletDatabaseTest.java .\work\Assignment7\
copy junit-4.12.jar .\work\Assignment7\
copy hamcrest-core-1.3.jar .\work\Assignment7\
cd work
cd Assignment7
javac -cp junit-4.12.jar;hamcrest-core-1.3.jar;acm.jar FacePamphlet.java FacePamphletConstants.java FacePamphletCanvas.java FacePamphletProfile.java FacePamphletDatabase.java FacePamphletTester.java FacePamphletTest.java FacePamphletCanvasTest.java FacePamphletProfileTest.java FacePamphletDatabaseTest.java
java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar;acm.jar FacePamphletTester