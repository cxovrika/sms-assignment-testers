source ./java.sh

rm -f $1/acm.jar
cp $2/acm.jar $1/
cp $2/AssignmentTester.java $1/
cd $1

classpath="$JAVA_HOME/jre/lib/resources.jar:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/jre/lib/jsse.jar:$JAVA_HOME/jre/lib/jce.jar:$JAVA_HOME/jre/lib/charsets.jar:$JAVA_HOME/jre/lib/jfr.jar:$JAVA_HOME/jre/lib/ext/access-bridge-32.jar:$JAVA_HOME/jre/lib/ext/cldrdata.jar:$JAVA_HOME/jre/lib/ext/dnsns.jar:$JAVA_HOME/jre/lib/ext/jaccess.jar:$JAVA_HOME/jre/lib/ext/jfxrt.jar:$JAVA_HOME/jre/lib/ext/localedata.jar:$JAVA_HOME/jre/lib/ext/nashorn.jar:$JAVA_HOME/jre/lib/ext/sunec.jar:$JAVA_HOME/jre/lib/ext/sunjce_provider.jar:$JAVA_HOME/jre/lib/ext/sunmscapi.jar:$JAVA_HOME/jre/lib/ext/sunpkcs11.jar:$JAVA_HOME/jre/lib/ext/zipfs.jar:$1:$1/acm.jar"
files="$1/AssignmentTester.java $1/FindRange.java $1/Hailstone.java $1/ProgramHierarchy.java $1/Pyramid.java $1/PythagoreanTheorem.java $1/Target.java"
$javac -cp $classpath $files 2>$1/errors.txt \
&& $java -cp $classpath AssignmentTester code=AssignmentTester.class > $1/output.txt
