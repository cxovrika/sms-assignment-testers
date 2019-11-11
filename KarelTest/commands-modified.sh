rm -f ls $1/karel.jar
cp $2/KarelTester.java $1/
cp $2/CollectNewspaperKarelTest.java $1/
cp $2/StoneMasonKarelTest.java $1/
cp $2/CheckerboardKarelTest.java $1/
cp $2/MidpointFindingKarelTest.java $1/
cp $2/karel.jar $1/
cp $2/junit-4.12.jar $1/
cp $2/hamcrest-core-1.3.jar $1/
cp $2/acm.jar $1/
cd $1
javac -cp $1/karel.jar:$1/junit-4.12.jar:$1/hamcrest-core-1.3.jar:$1/acm.jar $1/CollectNewspaperKarel.java $1/StoneMasonKarel.java $1/CheckerboardKarel.java $1/MidpointFindingKarel.java $1/KarelTester.java $1/CollectNewspaperKarelTest.java $1/StoneMasonKarelTest.java $1/CheckerboardKarelTest.java $1/MidpointFindingKarelTest.java 2>$1/errors.txt \
&& java -cp "$1:$1/karel.jar:$1/junit-4.12.jar:$1/hamcrest-core-1.3.jar:$1/acm.jar" KarelTester > $1/output.txt 