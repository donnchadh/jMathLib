rem

cd tmp
mkdir functions
cd ..

cd doc\


cd tools\

echo "building function docs"
perl createdocs.pl ../../tmp/functions/ ../../src/jmathlib/toolbox

echo "creating alphabetical list"
perl createdoclist_xml.pl ../../tmp ../../tmp/functions

rem echo "creating bug/ to-do and history list"
rem perl createtdbh.pl

cd ..\..\..
