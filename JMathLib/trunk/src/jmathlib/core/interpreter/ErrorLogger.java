package jmathlib.core.interpreter;

import java.io.*;

/**write error messages + debug information to a log file*/
public class ErrorLogger extends RootObject
{
    /**stores the size of indent for the next line*/ 
    private static int indentSize = 0;


    /**flags wether text should be indented*/
    private static boolean displayIndent = false;

    /**display a debug line to the standard output and the file MathLib.log
       @param text = the text to display*/
    public static void debugLine(String text)
    {

        if(getDebug())
        {
            //if(displayIndent)
            //    text = text + ";" + indentSize;
                
            for (int i=1; i<indentSize; i++) text= " "+text;	
    
            try
            {
                RandomAccessFile output = new RandomAccessFile("JMathLib.log", "rw");
                output.seek(output.length());
                output.writeBytes(text + "\n");
                output.close();
            }
            catch(IOException error) 
            {
            }
            catch(SecurityException error)
            {
            }
    
            System.out.println(text);
        }
    }

	/**display an integer to the standard output
	@param value = the number to display*/
	public static void debugLine(int value)
	{
		debugLine(new Integer(value).toString());
	}

	/**display a real value to the standard output
	@param value = the number to display*/
	public static void debugLine(double value)
	{
		debugLine(new Double(value).toString());
	}

	/**Increases the level of indent*/
	public static void increaseIndent()
	{
		indentSize++;
	}

	/**Decreases the level of indent*/
	public static void decreaseIndent()
	{
		if (indentSize>=0) indentSize--;
	}

    /**Sets whether the indent value should be displayed
       @param _displayIndent = true if indent should be displayed*/
    public static void setDisplayIndent(boolean _displayIndent)
    {
    	displayIndent = _displayIndent;
    }

    /**Prints the current execution stack trace, the list of functions that have been called up to the current one.
       @param message = The message to display before the stack trace*/
    public static void displayStackTrace(String message)
    {
        new Exception(message).printStackTrace();           
    }
}






