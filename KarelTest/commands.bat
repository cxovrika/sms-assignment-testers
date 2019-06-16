del work\Assignment1\karel.jar
copy KarelTester.java .\work\Assignment1\
copy CollectNewspaperKarelTest.java .\work\Assignment1\
copy StoneMasonKarelTest.java .\work\Assignment1\
copy CheckerBoardKarelTest.java .\work\Assignment1\
copy MidpointFindingKarelTest.java .\work\Assignment1\
copy karel.jar .\work\Assignment1\
copy junit-4.12.jar .\work\Assignment1\
copy hamcrest-core-1.3.jar .\work\Assignment1\
copy acm.jar .\work\Assignment1\
cd work
cd Assignment1
javac -cp karel.jar;junit-4.12.jar;hamcrest-core-1.3.jar;acm.jar CollectNewspaperKarel.java StoneMasonKarel.java CheckerBoardKarel.java MidpointFindingKarel.java KarelTester.java CollectNewspaperKarelTest.java StoneMasonKarelTest.java CheckerBoardKarelTest.java MidpointFindingKarelTest.java
java -cp .;karel.jar;junit-4.12.jar;hamcrest-core-1.3.jar;acm.jar KarelTester