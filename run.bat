@echo off
rem setup classpath
echo set _CP=%%_CP%%;%%1> cp.bat
set _CP=.;.\bin
for %%i in (src\lib\*.jar) do call cp.bat %%i
set CLASSPATH=%_CP%
del cp.bat
echo %CLASSPATH%

set JAVA_HOME=D:\Software\Java\jdk1.8.0_91
set JAVA_OPTION=-Dfile.encoding=GBK -Xmx256M -Xms64M
set RUN_CLASS=wow.pet.PetFighting
@echo on
"%JAVA_HOME%\bin\java" %JAVA_OPTION% -classpath %CLASSPATH% %RUN_CLASS% >> E:\workspace\eclipse-2020\wzh\java\wow\log.txt

pause



  