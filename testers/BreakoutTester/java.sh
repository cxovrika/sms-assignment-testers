try1=/usr/lib/jvm/java-8-openjdk
try2=/usr/lib/jvm/open-jdk
if [ ! -z "${JAVA_HOME}" ]
then
    echo "java home properly set"
elif [ -d $try2 ]
then
    JAVA_HOME=$try2
elif [ -d $try1 ]
then
    JAVA_HOME=$try1
else
    echo "JAVA_HOME variable not properly set, all attempts failed"
    echo "look up where JAVA_HOME is on your system"
    exit
fi
export JAVA_HOME
echo $JAVA_HOME
