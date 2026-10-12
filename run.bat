
@echo off
cd /d "%~dp0"
java -cp ".;lib\jlayer-1.0.1.jar" Calcultor
if errorlevel 1 pause
