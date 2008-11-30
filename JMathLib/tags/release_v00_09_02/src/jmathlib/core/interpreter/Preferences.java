package jmathlib.core.interpreter;

//import MathLib.Tokens.*;
import java.util.*;
import java.io.*;

/**
 * 
 */
public class Preferences 
{

    Properties globalProps = new Properties();  // global properties
    Properties localProps  = new Properties();  // local properties
    
    //SwingUI KEYS
    public static final String MFUNCTION_FILE_HISTORY = "MathLib.UI.Swing.MFunctionFileHistory";
   /**
    * 
    */
   public Preferences()
   {
   }

   /**
    * 
    *
    */
   public void loadPropertiesFromFile()
   {
        
       // load globa properties from disc 
       try 
       {
            globalProps.load(new FileInputStream(new File("JMathLib.properties")));
       }
       catch (Exception e)
       {
           System.out.println("Properties error");    
       }
       //System.out.println("Properties loaded");    

       // display properties
       Enumeration globalPropnames = globalProps.propertyNames();
       //while (globalPropnames.hasMoreElements())
       //{
       //    String propname = (String)globalPropnames.nextElement();
       //    System.out.println("Property: "+propname+" = "+globalProps.getProperty(propname));
       //}
        
        
       // load local properties from disc 
       try 
       {
    	   localProps.load(new FileInputStream(new File("JMathLib.local.properties")));
       }
       catch (Exception e)
       {
           System.out.println("Properties local error");    
       }

       // display properties
       Enumeration localPropnames = localProps.propertyNames();
       //while (localPropnames.hasMoreElements())
       //{
       //    String propname = (String)localPropnames.nextElement();
       //    System.out.println("Property (local): "+propname+" = "+localProps.getProperty(propname));
       //}
        
       // check if all global properties are also set in local properties
       //  -> in case global property not available in local property, copy global to local property
       globalPropnames = globalProps.propertyNames();
       while (globalPropnames.hasMoreElements())
       {
           String propname = (String)globalPropnames.nextElement();
           if (localProps.getProperty(propname)== null)
           {
               System.out.println("global Property: "+propname+" not local yet");
               localProps.setProperty(propname, (String)globalProps.getProperty(propname));
           }
       }

   }

   /**
    * 
    */
   public void storeLocalPropertiesToFile()
   {
       
       // only store local properties back to file
	   try
       {
    	   localProps.store(new FileOutputStream(new File("JMathLib.local.properties")), 
                            "JMathLib local property file" );
       }
       catch (Exception e)
       {
           System.out.println("Property: Error");
       }
   }
   
   public String getGlobalProperty(String property)
   {
       return globalProps.getProperty(property);
   }

   public void setGlobalProperty(String property, String value)
   {
	   globalProps.setProperty(property, value);
   }

   public String getLocalProperty(String property)
   {
       return localProps.getProperty(property);
   }

   public void setLocalProperty(String property, String value)
   {
	   localProps.setProperty(property, value);
   }

}