/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   stefan@held-mueller.de
 * (c) 2008-2009   
 */
package jmathlib.toolbox.jmathlib.system;

import java.io.*;
import java.net.*;
import java.util.Properties;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for updating JMathLib*/
public class update extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

        String lineFile = "";
 		boolean successB = true;    
        
 		globals.getInterpreter().displayText("UPDATING JMathLib\n");

        // get update site of jmathlib
        String updateSiteS   = globals.getProperty("update.site.primary");
        globals.getInterpreter().displayText("update site: "+updateSiteS);

        // get local version of jmathlib
        String localVersionS = globals.getProperty("jmathlib.version");
        globals.getInterpreter().displayText("current version: "+localVersionS);

        
        // first check to which version an update is possible
        globals.getInterpreter().displayText("Checking to which version an update is possible");
        
        
        // url of the update site including the request for the version to update to
        URL  url = null;
        try
        {
            url = new URL(updateSiteS+"?jmathlib_version="+localVersionS+"&command=update");
        }
        catch (Exception e)
        {
            throwMathLibException("update: malformed url for getting update version");
            successB = false;
        }          
        
        // load information from the update server
        Properties props = new Properties();
        try 
        {
             props.load(url.openStream() );
        }
        catch (Exception e)
        {
            System.out.println("updates: Properties error");    
            successB = false;
        }

                
        // Check return properties for "update-to-version"
        // Update site could send a lot of properties
        String updateVersionS = props.getProperty("update.toversion");
        String updateActionS  = props.getProperty("update.action");

        // reaction on the response from the update server
        if (updateActionS.equals("VERSION_UNKNOWN"))
        {
            globals.getInterpreter().displayText("Your version of JMathLib is not known by the update server.");
            return null; 
        }
        else if (updateVersionS.equals("NO_UPDATE_AVAILABLE") ||
                 updateActionS.equals("NO_ACTION")               )
        {
            globals.getInterpreter().displayText("No update available right now.");
            return null;
        }
        else if (updateActionS.equals("FULL_DOWNLOAD_REQUIRED"))
        {
            globals.getInterpreter().displayText("\n");
            globals.getInterpreter().displayText("Full download required in order to update!");
            globals.getInterpreter().displayText("Please visit www.jmathlib.de for details.");
            globals.getInterpreter().displayText("\n");
            String urlS   = props.getProperty("update.full_file_url");
            String fileS  = props.getProperty("update.file_name");
            globals.getInterpreter().displayText("url:  "+urlS);
            globals.getInterpreter().displayText("file: "+fileS);
            
            if ((urlS==null) || (fileS==null))
                return null;
            
            
  // put a message box here

            // open URL to file on update server
            try 
            {
                globals.getInterpreter().displayText("Downloading ...");
                URL fileURL = new URL(urlS);
                InputStream in = fileURL.openStream();

                // open file on local disc
                File         file = new File(globals.getWorkingDirectory(),fileS);
                OutputStream out  = new FileOutputStream(file);
                byte[]       cbuf = new byte[4096]; 
                int          len  = -1;
                
                while ((len = in.read(cbuf)) != -1)
                {
                    out.write(cbuf,0,len);
                }
                
                in.close();
                out.close();
                globals.getInterpreter().displayText("Downloading done.");
            }
            catch (Exception e)
            {
                successB = false;
                globals.getInterpreter().displayText("update: problem downloading "+fileS);    
            }
            
            
            // run the downloaded file
            try
            {   
                globals.getInterpreter().displayText("Running installer ...");
                Runtime.getRuntime().exec(fileS);
                globals.getInterpreter().displayText("Please exit JMathLib");
            }
            catch(IOException exception)
            {
                throwMathLibException("SystemCommand");         
            }
            

            return null;
        }
        else if (updateActionS.equals("INCREMENTAL_DOWNLOAD"))
        {

            globals.getInterpreter().displayText("updating to version >"+updateVersionS+"< \n");

        
            // download new files from server 
            updateSiteS += "jmathlib_" + updateVersionS + "/";
            try
            {
                url = new URL(updateSiteS);
            }
            catch (Exception e)
            {
                throwMathLibException("update: malformed url");
                successB = false;
            }          
           
            try
            {
                BufferedReader inR = new BufferedReader(new InputStreamReader(url.openStream()));
                String s = null; 
                
                // read next line from server
                while ((s = inR.readLine()) != null  )
                {
                    //getInterpreter().displayText("update "+s);
                    
                    if (s.startsWith("file"))
                    {
                        // read a file from the server and place it on the local disc
                        String fileS = s.substring(5).trim();
                        globals.getInterpreter().displayText("new file: >"+fileS+"<");
                        
                        // open URL to file on update server
                        try 
                        {
                            URL fileURL = new URL(updateSiteS+fileS);
                            InputStream in = fileURL.openStream();
        
                            // open file on local disc
                            File         file = new File(globals.getWorkingDirectory(),fileS);
                            OutputStream out  = new FileOutputStream(file);
                            byte[]       cbuf = new byte[4096]; 
                            int          len  = -1;
                            
                            while ((len = in.read(cbuf)) != -1)
                            {
                                out.write(cbuf,0,len);
                            }
                            
                            in.close();
                            out.close();
                        }
                        catch (Exception e)
                        {
                            successB = false;
                            globals.getInterpreter().displayText("update: problem downloading "+fileS);    
                        }
                        
                    }
                    else if (s.startsWith("dir"))
                    {
                        // create a directory on the local disc
                        String dirS = s.substring(4).trim();
                        globals.getInterpreter().displayText("new directory: >"+dirS+"<");
                        try
                        {
                            File file = new File(globals.getWorkingDirectory(),dirS);
                            file.mkdir();
                        }
                        catch (Exception e)
                        {
                            successB = false;
                            globals.getInterpreter().displayText("update: problem creating directory "+dirS);    
                        }
                          
                    }
                    else if (s.startsWith("del"))
                    {
                        // delete a file/directory on the local disc
                        String delS = s.substring(4).trim();
                        globals.getInterpreter().displayText("delete file/dir: >"+delS+"<");
                        try
                        {
                            File   file = new File(globals.getWorkingDirectory(),delS);
                            file.delete();
                        }
                        catch (Exception e)
                        {
                            successB = false;
                            globals.getInterpreter().displayText("update: problem deleting "+delS);    
                        }
                    }
                    else if (s.startsWith("prop"))
                    {
                        // delete a file/directory on the local disc
                        String propS = s.substring(5).trim();
                        globals.getInterpreter().displayText("new property: >"+propS+"<");
                        String[]   p = propS.split("=");
                        globals.setProperty(p[0],p[1]);
                    }
                    else
                    {
                        // empty line or unknown command or comment
                    }
                    
                    // in case something went wrong terminate
                    if (!successB)
                        break;
                    
                }
    
            }
            catch (Exception e)
            {
                throwMathLibException("update: open or reading stream");
            }          
            
            
    	}
        else
        {
            throwMathLibException("update: unknown action");  
        }

        // notifiy user
        if (!successB)
            globals.getInterpreter().displayText("\n Update was not successful. Repeat again later on or inform the admin.");
                    
        return null;        

	}
}

/*
@GROUP
system
@SYNTAX
update
@DOC
updates JMathLib over the web
@EXAMPLES
<programlisting>
update
</programlisting>
@NOTES
The script first checks the webserver in order to determine, if 
it is possible to update the current version and if yes which 
version is next.
It also updates local properties of JMathlib.
@SEE
checkforupdates
*/
