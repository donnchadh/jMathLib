package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

import java.net.*;
import java.util.*;

// OPEN: Also send current information about toolboxes and 
//       installed version to server

/**An external function for checking for updates over the network*/
public class checkforupdates extends ExternalFunction
{
    GlobalValues globals = null;
    
	public OperandToken evaluate(Token[] operands, GlobalValues _globals)
	{
	    
	    globals = _globals;

		String s           = "";
        String lineFile    = "";
        String updateSiteS = "http://www.jmathlib.de/checkForUpdates/";
 		boolean silentB    = false;
        
        s = globals.getInterpreter().prefs.getLocalProperty("update.site.primary");
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
                    globals.getInterpreter().displayText("Update Site "+updateSiteS);
                }
        	}
        }

        if (!silentB)
            globals.getInterpreter().displayText("Checking for Updates at "+updateSiteS);
        
        
        String[] lastUpdateS = globals.getInterpreter().prefs.getLocalProperty("update.date.last").split("/");
        int year  = Integer.parseInt(lastUpdateS[0]);
        int month = Integer.parseInt(lastUpdateS[1])-1;
        int day   = Integer.parseInt(lastUpdateS[2]);
        //getInterpreter().displayText("check:"+year+"/"+month+"/"+day);
                
        int intervall = Integer.parseInt(globals.getInterpreter().prefs.getLocalProperty("update.intervall"));

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
            
            String s;
            
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

            String[] localVersionS  = globals.getInterpreter().prefs.getLocalProperty("jmathlib.version").replace("/",".").split("\\.");
            String[] webVersionS    = props.getProperty("jmathlib.version").replace("/",".").split("\\.");

            // build version number of local version
            // e.g. 2.6.7 -> 20607
            int localVersion =0;
            
            if (localVersionS.length==3)
            {   
                localVersion    += Integer.parseInt(localVersionS[0]) * 100 * 100;
                localVersion    += Integer.parseInt(localVersionS[1]) * 100;
                localVersion    += Integer.parseInt(localVersionS[2]);
            }
                
            // build version number of web version
            // e.g. 2.6.7 -> 20607
            int webVersion=0;
            
            if (webVersionS.length==3)
            {
                webVersion      += Integer.parseInt(webVersionS[0])  * 100 * 100;
                webVersion      += Integer.parseInt(webVersionS[1])  * 100;
                webVersion      += Integer.parseInt(webVersionS[2]);
            }
            
            // check version number of web version against local version
            if (webVersion > localVersion)
            {

                // set marker for next startup
                globals.getInterpreter().prefs.setLocalProperty("update.newversionavailable","yes");
                
                s = props.getProperty("update.newversionavailable.message01");
                if (s!=null)
                {
                    globals.getInterpreter().prefs.setLocalProperty("update.newversionavailable.message01", s);
                    globals.getInterpreter().displayText(s);
                }
                else
                    globals.getInterpreter().displayText("There is a new version of JMathLib available");

                
                s = props.getProperty("update.newversionavailable.message02");
                if (s!=null)
                {
                    globals.getInterpreter().prefs.setLocalProperty("update.newversionavailable.message02", s);
                    globals.getInterpreter().displayText(s);
                }
            }
            else if (webVersion < localVersion)
            {
                globals.getInterpreter().displayText("Funny! The version of JMathLib on the web is older than your local version");
            }
            else
            {
                if (!silentB)
                {
                    globals.getInterpreter().displayText("The local version of JMathLib is up to date");

                    s=props.getProperty("update.uptodate.message01");
                    if (s!=null) 
                        globals.getInterpreter().displayText(s);

                    s=props.getProperty("update.uptodate.message02");
                    if (s!=null) 
                        globals.getInterpreter().displayText(s);
                }
            }
            

            debugLine("checkForUpdates: web:" +webVersion +" local:"+ localVersion);
            
            // set current date for property "update.date.last"
            Calendar cal   = Calendar.getInstance();
            String checkedDate  = Integer.toString(cal.get(Calendar.YEAR))     + "/"
                                + Integer.toString(cal.get(Calendar.MONTH)+1)  + "/"
                                + Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
            globals.getInterpreter().prefs.setLocalProperty("update.date.last", checkedDate);

        
            // update link to primary update-site
            s= props.getProperty("update.site.primary");
            if (s!=null)
                globals.getInterpreter().prefs.setLocalProperty("update.site.primary",s);

            // update link to backup update-site
            s= props.getProperty("update.site.backup");
            if (s!=null)
                globals.getInterpreter().prefs.setLocalProperty("update.site.backup",s);

            // update message of the day
            s= props.getProperty("message.of.the.day");
            if (s!=null)
                globals.getInterpreter().prefs.setLocalProperty("message.of.the.day",s);

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
