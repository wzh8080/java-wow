#!/bin/sh

echo 开始运行程序...

JAVA_HOME=/app/jdk1.8.0_241
if [ -z "$JAVA_HOME" ]; then
echo "Please configure the JAVA_HOME!"
exit
fi

export PATH

CLASSPATH=.:${JAVA_HOME}/jre/lib/rt.jar:./bin
export CLASSPATH

JLIBDIR=./lib
export JLIBDIR

for LL in `ls $JLIBDIR/*.jar`
do
CLASSPATH=$CLASSPATH:$LL
export CLASSPATH
done

JAVA_OPTION="-Dfile.encoding=UTF-8 -Xmx256M -Xms64M"
RUN_CLASS=wow.pet.PetFighting
 
${JAVA_HOME}/bin/java ${JAVA_OPTION} -classpath ${CLASSPATH} ${RUN_CLASS}

echo "程序运行结束！"
