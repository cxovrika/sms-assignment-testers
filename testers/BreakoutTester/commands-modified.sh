submission_dir=$1
tester_dir=$2
rm -f $submission_dir/acm.jar
cp $tester_dir/acm.jar $submission_dir
cp $tester_dir/BreakoutTester.java $submission_dir

cd $submission_dir
libs="$JAVA_HOME/jre1.8.0_181/lib/resources.jar:$JAVA_HOME/jre1.8.0_181/lib/rt.jar:$JAVA_HOME/jre1.8.0_181/lib/jsse.jar:$JAVA_HOME/jre1.8.0_181/lib/jce.jar:$JAVA_HOME/jre1.8.0_181/lib/charsets.jar:$JAVA_HOME/jre1.8.0_181/lib/jfr.jar:$JAVA_HOME/jre1.8.0_181/lib/ext/access-bridge-32.jar:$JAVA_HOME/jre1.8.0_181/lib/ext/cldrdata.jar:$JAVA_HOME/jre1.8.0_181/lib/ext/dnsns.jar:$JAVA_HOME/jre1.8.0_181/lib/ext/jaccess.jar:$JAVA_HOME/jre1.8.0_181/lib/ext/jfxrt.jar:$JAVA_HOME/jre1.8.0_181/lib/ext/localedata.jar:$JAVA_HOME/jre1.8.0_181/lib/ext/nashorn.jar:$JAVA_HOME/jre1.8.0_181/lib/ext/sunec.jar:$JAVA_HOME/jre1.8.0_181/lib/ext/sunjce_provider.jar:$JAVA_HOME/jre1.8.0_181/lib/ext/sunmscapi.jar:$JAVA_HOME/jre1.8.0_181/lib/ext/sunpkcs11.jar:$JAVA_HOME/jre1.8.0_181/lib/ext/zipfs.jar:$submission_dir:$submission_dir/acm.jar"

javac -Xlint:deprecation -cp  $submission_dir/acm.jar $submission_dir/Breakout.java $submission_dir/BreakoutTester.java 2>$1/errors.txt \
&& java -classpath $libs BreakoutTester code=BreakoutTester.class > $1/output.txt
