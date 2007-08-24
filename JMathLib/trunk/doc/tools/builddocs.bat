rem
cd doc\tools\

echo "building function docs"
perl createdocs.pl ../functions/ ../../src/jmathlib/toolbox

echo "creating alphabetical list"
perl createdoclist_xml.pl ../src ../functions

rem echo "creating bug/ to-do and history list"
rem perl createtdbh.pl

cd ..\..\..
