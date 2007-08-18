package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import java.net.*;
import java.util.*;

// OPEN: Also send current information about toolboxes and 
//       installed version to server

/**An external function for checking for updates over the network*/
public class checkforupdates extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

		String s           = "";
        String lineFile    = "";
        String updateSiteS = "http://mathlib.sourceforge.net/checkForUpdates/";
 		boolean silentB    = false;
        
        s = getInterpreter().prefs.getLocalProperty("update.site.primary");
        if (s != null)
            updateSiteS = s;
        
        		
        if (getNArgIn(operands) == 1)
        {    
        	if ((operands[0] instanceof CharToken))
        	{
                String st = ((CharToken)operands[0]).toString();
                if (st.equals("-silent"))
                {
                    // silent check for updates is requested
                    silentB = true;
                }
                else
                {
                    updateSiteS = s; 
                    getInterpreter().displayText("Update Site "+updateSiteS);
                }
        	}
        }

        if (!silentB)
            getInterpreter().displayText("Checking for Updates at "+updateSiteS);
        
        
        String[] lastUpdateS = getInterpreter().prefs.getLocalProperty("update.date.last").split("/");
        int year  = Integer.parseInt(lastUpdateS[0]);
        int month = Integer.parseInt(lastUpdateS[1])-1;
        int day   = Integer.parseInt(lastUpdateS[2]);
        //getInterpreter().displayText("check:"+year+"/"+month+"/"+day);
                
        int intervall = Integer.parseInt(getInterpreter().prefs.getLocalProperty("update.intervall"));

        GregorianCalendar calFile = new GregorianCalendar(year,month,day);
        GregorianCalendar calCur  = new GregorianCalendar();
        
        //getInterpreter().displayText("check: "+calCur.toString());

        //getInterpreter().displayText("check: "+calFile.toString());

        calFile.add(Calendar.DATE,intervall);

        //getInterpreter().displayText("check: "+calFile.toString());
        
        //getInterpreter().displayText("calFile "+calFile);
        //getInterpreter().displayText("calCur "+calCur);
        
                
        //if (calCur.after(calFile))
        //    getInterpreter().displayText("AFTER: TRUE" );
        //else
        //    getInterpreter().displayText("AFTER: FALSE" );

        if (silentB)
        {
            if (calCur.after(calFile))
            {
                checkForUpdatesThread ch = new checkForUpdatesThread(updateSiteS, silentB);
            }
        }
        else
        {
            checkForUpdatesThread ch = new checkForUpdatesThread(updateSiteS, silentB);
        }
        return null; 		

    }
    
    // create separate thread for checking the update site, because this may take
    //  some time 
    public class checkForUpdatesThread extends Thread
    {
        
        String updateSiteS = "";
        boolean silentB = false;
        
        public checkForUpdatesThread(String _updateSiteS, boolean _silentB)
        {
        
            updateSiteS = _updateSiteS;
            silentB     = _silentB;
            
            Thread runner = new Thread(this);
            runner.start();
            
            System.out.println("checkForUpdates: constructor");
        }
        
        public synchronized void run()
        { 
            
            // open URL
            URL url = null;
            try
            {
                url = new URL(updateSiteS);
            }
            catch (Exception e)
            {
                throwMathLibException("checkForUpdates: malformed url");
            }          
            
            // load information from the update server
            Properties props = new Properties();
            try 
            {
                 props.load(url.openStream() );
            }
            catch (Exception e)
            {
                System.out.println("checkForUpdates: Properties error");    
            }

            
            String[] localVersionS  = getInterpreter().prefs.getLocalProperty("jmathlib.version").split("/");
            String[] webVersionS    = props.getProperty("jmathlib.version").split("/");

            // build version number of local version
            // e.g. 2.6.7 -> 20607
            int localVersion = Integer.parseInt(localVersionS[0]) * 100 * 100;
            localVersion    += Integer.parseInt(localVersionS[1]) * 100;
            localVersion    += Integer.parseInt(localVersionS[2]);

            // build version number of web version
            // e.g. 2.6.7 -> 20607
            int webVersion   = Integer.parseInt(webVersionS[0])  * 100 * 100;
            webVersion      += Integer.parseInt(webVersionS[1])  * 100;
            webVersion      += Integer.parseInt(webVersionS[2]);
            
            // check version number of web version against local version
            if (webVersion > localVersion)
            {
                getInterpreter().displayText("There is a new version of JMathLib available");
            }
            else if (webVersion < localVersion)
            {
                getInterpreter().displayText("Funny! The version of JMathLib on the web is older than your local version");
            }
            else
            {
                if (!silentB)
                    getInterpreter().displayText("The local version of JMathLib is up to date");
            }
            

            debugLine("checkForUpdates: web:" +webVersion +" local:"+ localVersion);
            
            // set current date for property "update.date.last"
            Calendar cal   = Calendar.getInstance();
            String checkedDate  = Integer.toString(cal.get(Calendar.YEAR))     + "/"
                                + Integer.toString(cal.get(Calendar.MONTH)+1)  + "/"
                                + Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
            getInterpreter().prefs.setLocalProperty("update.date.last", checkedDate);
        }
        
    }
}

/*
@GROUP
system
@SYNTAX
checkForUpdates()
checkForUpdates(site)
checkForUpdates("-silent")
@DOC
@EXAMPLE
@NOTES
This functions checks via network if the current 
installation of JMathLib is up to date.

This functions is also called during startup of JMathLib's GUI.
@SEE
*/
