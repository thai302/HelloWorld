#!/bin/sh

APP_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
PID_FILE=$APP_HOME/.runtime/instance.pid

if [ $JAVA_HOME ]
then
	echo "JAVA_HOME found at $JAVA_HOME"
	RUN_JAVA=$JAVA_HOME/bin/java
else
	echo "JAVA_HOME environment variable not available."
    RUN_JAVA=`which java 2>/dev/null`
fi

if [ -z $RUN_JAVA ]
then
    echo "JAVA could not be found in your system."
    echo "please install Java 1.8 or higher!!!"
    exit 1
fi

#### you can enable following variables by uncommenting them

#### minimum heap size
# MIN_HEAP_SIZE=1G

#### maximum heap size
# MAX_HEAP_SIZE=1G


if [ "x$MIN_HEAP_SIZE" != "x" ]; then
	JAVA_OPTS="$JAVA_OPTS -Xms${MIN_HEAP_SIZE}"
fi

if [ "x$MAX_HEAP_SIZE" != "x" ]; then
	JAVA_OPTS="$JAVA_OPTS -Xmx${MAX_HEAP_SIZE}"
fi

CLASSPATH="lib/*:settings"

echo "########################################"
echo "# RUN_JAVA=$RUN_JAVA"
echo "# JAVA_OPTS=$JAVA_OPTS"
echo "# CLASSPATH= $CLASSPATH"
echo "# starting now...."
echo "########################################"

mkdir -p $APP_HOME/.runtime

if [ -f ${PID_FILE} ]; then
    PID=$(cat "${PID_FILE}");
fi

if [ -z "${PID}" ]; then
    echo "Process id for instance is written to file: $PID_FILE"
    java -cp $CLASSPATH vn.viettel.vcc.portal.asite.apis.AdminApisApplication > /dev/null 2>&1 &
    echo $! > ${PID_FILE}
else
    echo "Another instance is already started in this folder. To start a new instance, please run in a new folder."
    exit 0
fi
