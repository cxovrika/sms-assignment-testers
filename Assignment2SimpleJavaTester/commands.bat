del work\Assignment2\acm.jar
copy acm.jar work\Assignment2
copy AssignmentTester.java work\Assignment2

javac -cp work\Assignment2\acm.jar work\Assignment2\AssignmentTester.java work\Assignment2\FindRange.java work\Assignment2\Hailstone.java work\Assignment2\ProgramHierarchy.java work\Assignment2\Pyramid.java work\Assignment2\PythagoreanTheorem.java work\Assignment2\Target.java

java -classpath "%JAVA_HOME%\jre1.8.0_181\lib\resources.jar;%JAVA_HOME%\jre1.8.0_181\lib\rt.jar;%JAVA_HOME%\jre1.8.0_181\lib\jsse.jar;%JAVA_HOME%\jre1.8.0_181\lib\jce.jar;%JAVA_HOME%\jre1.8.0_181\lib\charsets.jar;%JAVA_HOME%\jre1.8.0_181\lib\jfr.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\access-bridge-32.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\cldrdata.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\dnsns.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\jaccess.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\jfxrt.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\localedata.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\nashorn.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\sunec.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\sunjce_provider.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\sunmscapi.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\sunpkcs11.jar;%JAVA_HOME%\jre1.8.0_181\lib\ext\zipfs.jar;.\work\Assignment2;.\work\Assignment2\acm.jar" AssignmentTester code=AssignmentTester.class