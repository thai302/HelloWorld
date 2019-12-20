#!/bin/sh

APP_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo 'current dir: ' $APP_HOME
CLASSPATH="lib/*:settings"
echo 'classpath: ' $CLASSPATH
java -cp $CLASSPATH vn.viettel.vcc.portal.asite.apis.AdminApisApplication