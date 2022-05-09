#!/bin/sh

SERVER_NAME='exam-ning-springcloud-auth.jar'
JAVA_MEM_OPTS=" -server -Xmx512m -Xms512m -Xmn256m -XX:PermSize=128m -Xss512k -XX:LargePageSizeInBytes=128m -XX:+UnlockExperimentalVMOptions -XX:+UseZGC "
echo "Starting the $SERVER_NAME ...\c"
exec java $JAVA_MEM_OPTS -jar $SERVER_NAME
