rm $1/work/Assignment1/karel.jar
cp $1/KarelTester.java $1/work/Assignment1/
cp $1/CollectNewspaperKarelTest.java $1/work/Assignment1/
cp $1/StoneMasonKarelTest.java $1/work/Assignment1/
cp $1/CheckerBoardKarelTest.java $1/work/Assignment1/
cp $1/MidpointFindingKarelTest.java $1/work/Assignment1/
cp $1/karel.jar $1/work/Assignment1/
cp $1/junit-4.12.jar $1/work/Assignment1/
cp $1/hamcrest-core-1.3.jar $1/work/Assignment1/
cp $1/acm.jar $1/work/Assignment1/
cd work
cd Assignment1
javac -cp $1/work/Assignment1/karel.jar:$1/work/Assignment1/junit-4.12.jar:$1/work/Assignment1/hamcrest-core-1.3.jar:$1/work/Assignment1/acm.jar $1/work/Assignment1/CollectNewspaperKarel.java $1/work/Assignment1/StoneMasonKarel.java $1/work/Assignment1/CheckerBoardKarel.java $1/work/Assignment1/MidpointFindingKarel.java $1/work/Assignment1/KarelTester.java $1/work/Assignment1/CollectNewspaperKarelTest.java $1/work/Assignment1/StoneMasonKarelTest.java $1/work/Assignment1/CheckerBoardKarelTest.java $1/work/Assignment1/MidpointFindingKarelTest.java
java -cp "$1/work/Assignment1:$1/work/Assignment1/karel.jar:$1/work/Assignment1/junit-4.12.jar:$1/work/Assignment1/hamcrest-core-1.3.jar:$1/work/Assignment1/acm.jar" KarelTester > $1/output.txt