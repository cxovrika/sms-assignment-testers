cp $1/HangmanTest.java $1/work/Assignment4/
cp $1/acm.jar $1/work/Assignment4/
javac -Xlint:unchecked -cp $1/work/Assignment4/acm.jar $1/work/Assignment4/HangmanTest.java $1/work/Assignment4/Hangman.java $1/work/Assignment4/HangmanCanvas.java $1/work/Assignment4/HangmanLexicon.java
cd work/Assignment4/
java -cp "$1/work/Assignment4/:$1/work/Assignment4/acm.jar" HangmanTest > $1/output.txt