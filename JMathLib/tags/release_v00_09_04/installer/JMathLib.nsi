; Java Launcher
;--------------
 
;You want to change the next four lines
Name "JMathLib"
Caption "JMathLib"
Icon "jmathlib.ico"
OutFile "..\upload\jmathlib\JMathLib.exe"
 
SilentInstall silent
AutoCloseWindow true
ShowInstDetails nevershow
 
;You want to change the next two lines too
!define CLASSPATH ".;lib;lib\myJar;bin"
!define CLASS "jmathlib.ui.awt.GUI"
 

;----------------------------------------------------------
; Splash Screen at startup
Function .onInit

# the plugins dir is automatically deleted when the installer exits
InitPluginsDir
File /oname=$PLUGINSDIR\splash.bmp "jmathlib04.bmp" #"${NSISDIR}\Contrib\Graphics\Header\nsis.bmp"

advsplash::show 200 300 200 0x000000 $PLUGINSDIR\splash

Pop $0          ; $0 has '1' if the user closed the splash screen early,
                ; '0' if everything closed normally, and '-1' if some error occurred.

Delete $PLUGINSDIR\splash.bmp
FunctionEnd


Section ""
  Call GetJRE
  Pop $R0
 
  ; change for your purpose (-jar etc.)
  StrCpy $0 '"$R0" -classpath "${CLASSPATH}" ${CLASS}'
  ;StrCpy $0 '"$R0" ${CLASS}'
  
 
  SetOutPath $EXEDIR
  Exec $0
SectionEnd
 
Function GetJRE
;
;  Find JRE (javaw.exe)
;  1 - in .\jre directory (JRE Installed with application)
;  2 - in JAVA_HOME environment variable
;  3 - in the registry
;  4 - assume javaw.exe in current dir or PATH
 
  Push $R0
  Push $R1
 
  ClearErrors
  StrCpy $R0 "$EXEDIR\jre\bin\javaw.exe"
  IfFileExists $R0 JreFound
  StrCpy $R0 ""
 
  ClearErrors
  ReadEnvStr $R0 "JAVA_HOME"
  StrCpy $R0 "$R0\bin\javaw.exe"
  IfErrors 0 JreFound
 
  ClearErrors
  ReadRegStr $R1 HKLM "SOFTWARE\JavaSoft\Java Runtime Environment" "CurrentVersion"
  ReadRegStr $R0 HKLM "SOFTWARE\JavaSoft\Java Runtime Environment\$R1" "JavaHome"
  StrCpy $R0 "$R0\bin\javaw.exe"
 
  IfErrors 0 JreFound
  StrCpy $R0 "javaw.exe"
        
 JreFound:
  Pop $R1
  Exch $R0
FunctionEnd