package jmathlib.ui.awt;

import java.awt.*;
import java.awt.event.*;

/**Class for handling window events
@deprecated gui uses an anomynous inner class instead*/
public class WindowHandler extends WindowAdapter
{
        public WindowHandler(){}

        /**Trap the close window icon being clicked*/
        public void windowClosing(WindowEvent e)
        {
                Window originator = e.getWindow();
                originator.dispose();
                System.exit(0);
        }
}
