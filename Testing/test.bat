@ECHO OFF
CLS
ECHO 1.Queue
ECHO 2.List
ECHO 3.Queue

CHOICE /C 123 /M "Enter your choice:"

:: Note - list ERRORLEVELS in decreasing order
IF ERRORLEVEL 3 GOTO Queue
IF ERRORLEVEL 2 GOTO List
IF ERRORLEVEL 1 GOTO Queue

:Queue
ECHO Queue
./gradlew.bat test >> out.txt
GOTO End

:List
ECHO List
./gradlew.bat test >> out.txt
GOTO End

:Queue
ECHO Queue
./gradlew.bat test >> out.txt
GOTO End

:End
