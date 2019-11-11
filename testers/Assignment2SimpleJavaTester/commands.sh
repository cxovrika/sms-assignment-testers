source ./java.sh

rm work/Assignment2/acm.jar
cp acm.jar work/Assignment2
cp AssignmentTester.java work/Assignment2
cd $1/work/Assignment2
javac=$JAVA_HOME/bin/javac
java=$JAVA_HOME/bin/java
classpath="$JAVA_HOME/jre/lib/resources.jar:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/jre/lib/jsse.jar:$JAVA_HOME/jre/lib/jce.jar:$JAVA_HOME/jre/lib/charsets.jar:$JAVA_HOME/jre/lib/jfr.jar:$JAVA_HOME/jre/lib/ext/access-bridge-32.jar:$JAVA_HOME/jre/lib/ext/cldrdata.jar:$JAVA_HOME/jre/lib/ext/dnsns.jar:$JAVA_HOME/jre/lib/ext/jaccess.jar:$JAVA_HOME/jre/lib/ext/jfxrt.jar:$JAVA_HOME/jre/lib/ext/localedata.jar:$JAVA_HOME/jre/lib/ext/nashorn.jar:$JAVA_HOME/jre/lib/ext/sunec.jar:$JAVA_HOME/jre/lib/ext/sunjce_provider.jar:$JAVA_HOME/jre/lib/ext/sunmscapi.jar:$JAVA_HOME/jre/lib/ext/sunpkcs11.jar:$JAVA_HOME/jre/lib/ext/zipfs.jar:$1/work/Assignment2:$1/work/Assignment2/acm.jar"
$javac -cp $classpath $1/work/Assignment2/AssignmentTester.java $1/work/Assignment2/FindRange.java $1/work/Assignment2/Hailstone.java $1/work/Assignment2/ProgramHierarchy.java $1/work/Assignment2/Pyramid.java $1/work/Assignment2/PythagoreanTheorem.java $1/work/Assignment2/Target.java
$java -cp $classpath AssignmentTester code=AssignmentTester.class > $1/output.txt
