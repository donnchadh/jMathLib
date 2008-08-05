 fop-0.92beta-bin-jdk1.4.zip     
 
This is my setup in Eclipse:
create a java project and download the sources from the SVN-repository

Menu Project/Properties
then select "Java Build Path"

click on "Source"
"Source folders on build path:" i have one entry "JMathLib/src"
"Default output folder" i have "JMathLib/bin"

click on "Libraries"
click on "Add JARs" and select "dynamicjava.jar" from the file selection

That should do the job.
 

Generation of javadoc
C:\workspace\JMathLib>javadoc -sourcepath src -exclude jmathlibtests -d c:\works
pace\jmathlib\docbook -subpackages jmathlib