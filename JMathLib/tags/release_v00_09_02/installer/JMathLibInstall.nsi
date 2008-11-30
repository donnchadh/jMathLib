; JMathLib installer with NSI-installer
;
;Written by Stefan Mueller (stefan@held-mueller.de)

;--------------------------------
;Include Modern UI

  !include "MUI2.nsh"

;--------------------------------
; the VERSION information will be set by ant as an environment variable
!define VERSION $%JMathLibVersion%

;--------------------------------
;General

;Name 
Name     "JMathLib ${VERSION}"
Caption  "JMathLib  Installation"
!define  MUI_ICON   "jmathlib.ico"
;!define MUI_HEADERIMAGE_BITMAP bmp_file (size150*57)
  
  
;Name of installer-executable
OutFile "../upload/JMathLibInstall_${VERSION}.exe"

;Default installation folder
InstallDir "$PROGRAMFILES\JMathLib"
  
;Get installation folder from registry if available
InstallDirRegKey HKCU "Software\JMathLib" ""

;Request application privileges for Windows Vista
RequestExecutionLevel user

;define MUI_INSTFILESPAGE_PROGRESSBAR

;----------------------------------------------------------
; Splash Screen at startup
Function .onInit

# the plugins dir is automatically deleted when the installer exits
InitPluginsDir
File /oname=$PLUGINSDIR\splash.bmp "jmathlib04.bmp" #"${NSISDIR}\Contrib\Graphics\Header\nsis.bmp"
#optional
#File /oname=$PLUGINSDIR\splash.wav "C:\myprog\sound.wav"

#MessageBox MB_OK "Fading"

advsplash::show 900 300 500 0x000000 $PLUGINSDIR\splash

Pop $0          ; $0 has '1' if the user closed the splash screen early,
                ; '0' if everything closed normally, and '-1' if some error occurred.

 #MessageBox MB_OK "Transparency"
 #File /oname=$PLUGINSDIR\splash.bmp "${NSISDIR}\Contrib\Graphics\Wizard\orange-uninstall.bmp"
 #advsplash::show 2000 0 0 0x1856B1 $PLUGINSDIR\splash
 #Pop $0 

 #MessageBox MB_OK "Transparency/Fading"
 #File /oname=$PLUGINSDIR\splash.bmp "images\jmathlib02.bmp" #"${NSISDIR}\Contrib\Graphics\Wizard\llama.bmp"
 #advsplash::show 1000 600 400 0x04025C $PLUGINSDIR\splash
 #Pop $0 

 Delete $PLUGINSDIR\splash.bmp
 FunctionEnd

;--------------------------------
;Variables

  Var MUI_TEMP
  Var STARTMENU_FOLDER

;--------------------------------
;Interface Settings for aborting installation

  !define MUI_ABORTWARNING
  !define MUI_ABORTWARNING_TEXT "You are about to terminate the Installation of JMathLib. Are you sure?"
  !define MUI_ABORTWARNING_CANCEL_DEFAULT
  
;--------------------------------
;Pages

  ;!define MUI_WELCOMEPAGE_TITLE
  ;!define MUI_WELCOMEPAGE_TITLE_3LINES
  ;!define MUI_WELCOMEPAGE_TEXT 

  !insertmacro MUI_PAGE_WELCOME
   
  !define MUI_LICENSEPAGE_CHECKBOX
  
  !insertmacro MUI_PAGE_LICENSE "../upload/jmathlib/license.txt"
  
  !insertmacro MUI_PAGE_COMPONENTS
  
  !insertmacro MUI_PAGE_DIRECTORY
  
  ;Start Menu Folder Page Configuration
  !define MUI_STARTMENUPAGE_REGISTRY_ROOT "HKCU" 
  !define MUI_STARTMENUPAGE_REGISTRY_KEY "Software\JMathLib" 
  !define MUI_STARTMENUPAGE_REGISTRY_VALUENAME "Start Menu Folder"
  
  !insertmacro MUI_PAGE_STARTMENU Application $STARTMENU_FOLDER
  
  !insertmacro MUI_PAGE_INSTFILES
  
  !define MUI_FINISHPAGE_RUN  "$INSTDIR\JMathLib.exe"
  !define MUI_FINISHPAGE_RUN_TEXT "Run JMathLib now"
  
  ;add a link to the website of JMathLib
  !define MUI_FINISHPAGE_LINK "check more about JMathLib at its website"
  !define MUI_FINISHPAGE_LINK_LOCATION http://www.jmathlib.de

  ;changelog to read
  !define MUI_FINISHPAGE_SHOWREADME "$INSTDIR\ChangeLog.txt"
  !define MUI_FINISHPAGE_SHOWREADME_TEXT "View changelog for JMathLib ${VERSION}"
  !define MUI_FINISHPAGE_SHOWREADME_NOTCHECKED

  !insertmacro MUI_PAGE_FINISH 
  
  !insertmacro MUI_UNPAGE_CONFIRM
  !insertmacro MUI_UNPAGE_INSTFILES

;--------------------------------
;Languages
 
  !insertmacro MUI_LANGUAGE "English"

;--------------------------------
;Installer Sections

Section "Installer Section" SecInstall

  ; call startup-exe-generator for JMathLib
  !execute 'makensis.exe /V4 JMathLib.nsi'
  
  ;Class Files
  SetOutPath "$INSTDIR\bin"
  File /r "..\upload\jmathlib\bin\*.*"

  SetOutPath "$INSTDIR\handbook"
  File /r "..\upload\jmathlib\handbook\*.*"
  
  SetOutPath "$INSTDIR"
  File "..\upload\jmathlib\JMathLib.exe"
  File "..\upload\jmathlib\JMathLibSwing.exe"
  File "..\upload\jmathlib\JMathLib.properties"
  File "..\upload\jmathlib\JMathLib.local.properties"
  File "..\upload\jmathlib\ChangeLog.txt"
  File "..\upload\jmathlib\JMathLib.html"
  File "..\upload\jmathlib\license.txt"
  ;File "..\upload\mathlib\JMathLibSmallApplet.jar"
  ;File "..\upload\jmathlib\readme.txt"
  
  ;Store installation folder
  WriteRegStr HKCU "Software\JMathLib" "" $INSTDIR
  
  ;Create uninstaller
  WriteUninstaller "$INSTDIR\Uninstall.exe"
  
  !insertmacro MUI_STARTMENU_WRITE_BEGIN Application
    
  ;Create shortcuts
  CreateDirectory "$SMPROGRAMS\$STARTMENU_FOLDER"
  CreateShortCut "$SMPROGRAMS\$STARTMENU_FOLDER\JMathLib.lnk"  "$INSTDIR\JMathLib.exe" ""
  CreateShortCut "$SMPROGRAMS\$STARTMENU_FOLDER\JMathLibManual.lnk" "$INSTDIR\JMathLibManual_${VERSION}.pdf" ""
  CreateShortCut "$SMPROGRAMS\$STARTMENU_FOLDER\Uninstall.lnk" "$INSTDIR\Uninstall.exe" ""
  CreateShortCut "$DESKTOP\JMathLib ${VERSION}.lnk" "$INSTDIR\JMathLib.exe" ""
  
  !insertmacro MUI_STARTMENU_WRITE_END

SectionEnd

;-----------------------------------------
Section "Source Code" SecSourceInstall

  ;Source Files
  SetOutPath "$INSTDIR\src"
  File /r /x CVS "..\upload\jmathlib\src\*.*"

SectionEnd

;-----------------------------------------
Section "Documentation" SecDocInstall

  ;Dokumentation Files
  SetOutPath "$INSTDIR"
  File "..\upload\JMathLibManual_${VERSION}.pdf"

SectionEnd


;--------------------------------
;Descriptions

  ;Language strings
  LangString DESC_SecInstall       ${LANG_ENGLISH} "Installation section."
  LangString DESC_SecSourceInstall ${LANG_ENGLISH} "source code section."
  LangString DESC_SecDocInstall    ${LANG_ENGLISH} "documentation section."

  ;Assign language strings to sections
  !insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
  !insertmacro MUI_DESCRIPTION_TEXT ${SecInstall}       $(DESC_SecInstall)
  !insertmacro MUI_DESCRIPTION_TEXT ${SecSourceInstall} $(DESC_SecSourceInstall)
  !insertmacro MUI_DESCRIPTION_TEXT ${SecDocInstall}    $(DESC_SecDocInstall)
  !insertmacro MUI_FUNCTION_DESCRIPTION_END
 
;--------------------------------
;Uninstaller Section

Section "Uninstall"

  ;ADD YOUR OWN FILES HERE...

  Delete "$INSTDIR\Uninstall.exe"
  RMDir /r "$INSTDIR\bin"
  RMDir /r "$INSTDIR\src"
  RMDir /r "$INSTDIR\handbook"
  Delete "$INSTDIR\license.txt"
  Delete "$INSTDIR\JMathLib.properties"
  Delete "$INSTDIR\JMathLib.local.properties"
  Delete "$INSTDIR\JMathLib.log"
  Delete "$INSTDIR\license.txt"
  Delete "$INSTDIR\ChangeLog.txt"
  Delete "$INSTDIR\JMathLib.exe"
  Delete "$INSTDIR\JMathLibSwing.exe"
  Delete "$INSTDIR\JMathLibManual_${VERSION}.pdf"
  Delete "$INSTDIR\JMathLib.html"
  Delete "$INSTDIR\JMathLibSmallApplet.jar"
  Delete "$INSTDIR\JMathLibSmallApplet.zip"

  RMDir "$INSTDIR"
  
  !insertmacro MUI_STARTMENU_GETFOLDER Application $MUI_TEMP
  
  ;remove links in the startmenue  
  Delete "$SMPROGRAMS\$MUI_TEMP\JMathLib.lnk"
  Delete "$SMPROGRAMS\$MUI_TEMP\JMathLibManual.lnk"
  Delete "$SMPROGRAMS\$MUI_TEMP\Uninstall.lnk"
  Delete "$DESKTOP\JMathLib ${VERSION}.lnk"
  
  ;Delete empty start menu parent diretories
  StrCpy $MUI_TEMP "$SMPROGRAMS\$MUI_TEMP"
 
  startMenuDeleteLoop:
	ClearErrors
    RMDir $MUI_TEMP
    GetFullPathName $MUI_TEMP "$MUI_TEMP\.."
    
    IfErrors startMenuDeleteLoopDone
  
    StrCmp $MUI_TEMP $SMPROGRAMS startMenuDeleteLoopDone startMenuDeleteLoop 
  startMenuDeleteLoopDone:

  DeleteRegKey /ifempty HKCU "Software\JMathLib"

SectionEnd