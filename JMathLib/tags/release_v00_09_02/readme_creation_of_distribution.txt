*******************  JMathLib readme  *************************

How to create a distribution:

- make sure the testsuite runs ok

- change file: build.properties
  and set the new version in the  line "version=x.x.x"

- change file: doc/src/doc.xml
  and set <pubdate>
  and set <releaseinfo>
  to the new version

- run JMathLib and call createFunctionsList()

- change file: JMathLib.properties
  and change to the release date 
  and change to the new versions

- change file: JMathLib.local.properties
  and change to the release date to be the same as in JMathLib.properties
  and change to the new version  to be the same as in JMathLib.properties

- change file: ChangeLog.txt

- run the ant tasks: fulldist  (this creates all doc-files and the installer)

- go to to ../upload and find all required files
  upload JMathLibInstall_x.x.x.exe to sourceforge.net
  upload JMathLibManual_x.x.x.pdf  to sourceforge.net
  upload JMathLib_x.x.x.zip        to sourceforge.net
  
- change the website in mathlib.sourceforge.net/CheckForUpdates/
  and change to new version
  and change to new date
  
- update the website

- send an email to the list mathlib-update@lists.sourceforge.net

- tag all files in SVN with new a new tag "v0_x_x"

- wait for user feedback


