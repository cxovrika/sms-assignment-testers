del work\Assignment3\acm.jar
copy acm.jar work\Assignment3
copy BreakoutTester.java work\Assignment3

javac -cp work\Assignment3\acm.jar work\Assignment3\Breakout.java work\Assignment3\BreakoutTester.java

java -classpath "%JAVA_HOME%\jre1.8.0_181\lib\resources.jar;%JAVA_HOME%\jre1.8.0_181\lib\rt.jar;%JAVA_HOME%\jre1.8.0_181\lib\jsse.jar;%JAVA_HOME%\jre1.8.0_181\lib\jce.jar;%JAVA_HOME%\jre1.8.0_181\lib\charsets.jar;%JAVA_HOME%\jre1.8.0_181\lib\jfr.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\access-bridge-32.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\cldrdata.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\dnsns.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\jaccess.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\jfxrt.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\localedata.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\nashorn.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\sunec.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\sunjce_provider.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\sunmscapi.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\sunpkcs11.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\zipfs.jar;.\work\Assignment3;.\work\Assignment3\acm.jar" BreakoutTester code=BreakoutTester.class > %1\output.txt