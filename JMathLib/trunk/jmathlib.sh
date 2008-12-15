#!/bin/sh

BD=`dirname $0`
CP=$BD/bin

echo java -cp "$CP" jmathlib.ui.awt.GUI

java -cp "$CP" jmathlib.ui.awt.GUI
