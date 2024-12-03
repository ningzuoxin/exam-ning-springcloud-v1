#!/bin/sh
JAR_PATH='exam-ning-springcloud-system-user.jar'
JVM_OPTS=" -Xmx256m -Xms256m -XX:+UseG1GC "
echo "Starting the $JAR_PATH ...\c"
exec java -jar $JVM_OPTS $JAR_PATH