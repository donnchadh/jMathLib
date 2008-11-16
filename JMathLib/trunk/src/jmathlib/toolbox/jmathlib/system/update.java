package jmathlib.toolbox.jmathlib.system;

import java.io.*;
import java.net.*;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;

/**An external function for updating JMathLib*/
public class update extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        String lineFile = "";
        URL url = null;
 		    
        getInterpreter().displayText("UPDATING JMathLib\n");

        // get update site and local version of jmathlib
        String updateSiteS   = getInterpreter().prefs.getLocalProperty("update.site.primary");
        String localVersionS = getInterpreter().prefs.getLocalProperty("jmathlib.version");
        localVersionS = localVersionS.replaceAll("/", ".");
        String updateVersionS = localVersionS;
        getInterpreter().displayText("update site: "+updateSiteS);
        getInterpreter().displayText("current version: "+localVersionS);

        
        // first check to which version an update is possible
        getInterpreter().displayText("Checking to which version an update is possible");
        try {
            url = new URL(updateSiteS+"updates/?jmathlib_version="+localVersionS);
            BufferedReader inR = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            while ((s = inR.readLine()) != null  )
            {
                //getInterpreter().displayText("version"+s);
                if (s.startsWith("update.toversion="))
                {
                    // read a file from the server and place it on the local disc
                    updateVersionS = s.substring(17).trim();
                    getInterpreter().displayText("updating to version >"+updateVersionS+"< \n");
                }
            }
        }
        catch (Exception e) {
            throwMathLibException("update: could not receive version for update");
        }          
               
        
        
        
        // download new files from server 
        updateSiteS += "updates/jmathlib_" + updateVersionS + "/";
        
        //getInterpreter().displayText("updating path: "+updateSiteS);
        try
        {
            url = new URL(updateSiteS);
        }
        catch (Exception e)
        {
            throwMathLibException("update: malformed url");
        }          
       
        BufferedReader inR=null;
        try
        {
            inR = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = inR.readLine();
            while (s != null  )
            {
                //getInterpreter().displayText("update "+s);
                
                if (s.startsWith("file"))
                {
                    // read a file from the server and place it on the local disc
                    String fileS = s.substring(5).trim();
                    getInterpreter().displayText("new file: >"+fileS+"<");
                    
                    // open URL to file on update server
                    URL fileURL = new URL(updateSiteS+fileS);
                    InputStream in = fileURL.openStream();

                    // open file on local disc
                    File file = new File(getWorkingDirectory(),fileS);
                    OutputStream out = new FileOutputStream(file);

                    byte[] cbuf = new byte[4096]; 
                    int len;
                    while ((len = in.read(cbuf)) != -1)
                    {
                        out.write(cbuf,0,len);
                    }
                    
                    in.close();
                    out.close();
               
                    
                }
                else if (s.startsWith("dir"))
                {
                    // create a directory on the local disc
                    String dirS = s.substring(4).trim();
                    getInterpreter().displayText("new directory: >"+dirS+"<");
                    File file = new File(getWorkingDirectory(),dirS);
                    file.mkdir();
                    
                }
                else if (s.startsWith("del"))
                {
                    // delete a file/directory on the local disc
                    String delS = s.substring(4).trim();
                    getInterpreter().displayText("delete file/dir: >"+delS+"<");
                    File file = new File(getWorkingDirectory(),delS);
                    file.delete();
                }
                else if (s.startsWith("prop"))
                {
                    // delete a file/directory on the local disc
                    String propS = s.substring(5).trim();
                    getInterpreter().displayText("new property: >"+propS+"<");
                    String[] p = propS.split("=");
                    getInterpreter().prefs.setLocalProperty(p[0],p[1]);
                }
                else
                {
                    // empty line or unknown command or comment
                }
                
                
                // read next line from server
                s = inR.readLine();
            }

        }
        catch (Exception e)
        {
            throwMathLibException("update: open or reading stream");
        }          
        
        
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
@EXAMPLE
<programlisting>
update
</programlisting>
@NOTES
The script first checks the webserver in order to determine, if 
it is possible to update the current version and if this which 
version is next.<br>
It also updates local properties of JMathlib
@SEE
checkforupdates
*/
