@echo off
SETLOCAL ENABLEDELAYEDEXPANSION
for /f "delims=," %%a in ('dir *.*  /b') do (
   set x=%%a
   set y=!x:~0,7!
   if "!y!"=="shiying" (
     cd !x!
     call mvn clean install package -Dmaven.test.skip=true
     cd ../
   )
)
pause