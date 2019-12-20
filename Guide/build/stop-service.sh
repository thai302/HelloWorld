#!/bin/sh

APP_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
PID_FILE=$APP_HOME/.runtime/instance.pid

if [ ! -f "${PID_FILE}" ]; then
    echo "No instance is running."
    exit 0
fi

PID=$(cat "${PID_FILE}");
if [ -z "${PID}" ]; then
    echo "No instance is running."
    exit 0
else
   kill -15 "${PID}"
   rm "${PID_FILE}"
   echo "Instance with PID ${PID} shutdown."
   exit 0
fi
