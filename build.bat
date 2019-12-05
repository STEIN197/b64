@ECHO off
IF EXIST "b64.jar" (
	DEL b64.jar
)
ECHO Building a jar archive...
CD bin
jar -cfe ..\b64.jar b64.Application .\*
CD ..
ECHO Finished
