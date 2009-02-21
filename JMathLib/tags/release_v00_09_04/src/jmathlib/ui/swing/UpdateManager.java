package jmathlib.ui.swing;

import java.io.*;
import javax.swing.*;

/**
 * SwingGUI package update manager
 *
 * This class connects to the mathlib's "official" CVS repository,
 * downloads the files and restart SwingGUI.
 *
 * See relese notes - General notes - GN0011.
 * @version 1.0
 */
public class UpdateManager extends JFrame implements Runnable
{
    private String module;



    /**
     * Constructor to fetch the SwingGUI run-time classes (*.class)
     */
    public UpdateManager()
    {
        this("MathLib/UI/Swing");
    }



    /**
     * Constructor to fetch a specified module
     * @param module CVS module to fetch
     */
    public UpdateManager(String module)
    {
        this.module = module;
        Thread updMgrThread = new Thread(this);
        updMgrThread.setPriority(Thread.currentThread().getPriority() + 1);
        updMgrThread.start();
    }



    /**
     * Thread entry-point.
     *
     * This method runs cvs, and update the named modules.
     * The behavior is a bit "dangerous".
     * See DN0011.
     */
    public void run()
    {
        String modules = this.module;
        SwingGUI.runningReference.dispose();
        try
        {
            Process p;
            InputStream ins;
            int errorLevel = -1;

            String cmd = System.getProperty("UPDATEMANAGER_CMD");
            String params1 = System.getProperty("UPDATEMANAGER_PARAMS");
            String params2 = System.getProperty("UPDATEMANAGER_CHECKOUT_PARAMS");
            String command = "\"" + cmd + "\" " + params1 + " " + params2 + " " + module;
            p = Runtime.getRuntime().exec(command);
/*
            ins = p.getInputStream();
            String logString = "";
            while (errorLevel == -1)
            {
                while (ins.available() > 0)
                {
                    logString += (char) ins.read();
                }
                p.getOutputStream().write((""+'\n').getBytes());
                try {  errorLevel = p.exitValue(); } catch (IllegalThreadStateException itse) {}
            }
 */
            errorLevel = p.waitFor();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        try
        {
            Class.forName("MathLib.UI.Swing.SwingGUI").newInstance();
        }
        catch (ClassNotFoundException cnfe) { ; }
        catch (InstantiationException ie) { ; }
        catch (IllegalAccessException iae) { ; }
//        SwingGUI.main(args);
    }
}