/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   stefan@held-mueller.de
 * (c) 2008-2009   
 */
package jmathlib.ui.text;

import jmathlib.core.interpreter.*;
import jmathlib.core.interfaces.RemoteAccessible;
import jmathlib.core.interfaces.JMathLibOutput;

import java.io.*;

/** text GUI for JMathLib */
public class TextUI implements RemoteAccessible, JMathLibOutput
{
    /**store whether executing lines or entering a function def*/
    private boolean interactiveMode;
    
    /**store the current function definition*/
    private String functionCode;

    /**stores whether the program is closing*/
    private boolean exiting;

    /**store a reference to the interpreter object*/
    private Interpreter interpreter;

    /**stores the input stream*/
    private DataInputStream input;
    
    public TextUI()
    {
        interactiveMode = true;
        functionCode = "";
        exiting = false;

        interpreter = new Interpreter(true);
        interpreter.setOutputPanel(this);
        interpreter.executeExpression("startup");

        input = new DataInputStream(System.in);
    }

    public void run()
    {
        displayPrompt();
        while(!exiting)
        {
            String command = readLine();
            interpretLine(command);    
        }
    }

    public static void main(String[] args)
    {
        TextUI tui = new TextUI();
        if(args.length != 0)
        {
            String filename = args[0];
            tui.interpretLine(filename);
        }

        tui.run();
    }
    
    public void close()
    {
        exiting = true;
    }
    
    public void interpretLine(String line)
    {
    	if(interactiveMode)
    	{        
		//check to see if this is the beginning of a user function
        	if(line.length() > 7 && line.substring(0, 8).equalsIgnoreCase("FUNCTION"))
        	{
        		functionCode = line;
        		interactiveMode = false;	
		
				displayPrompt();
        	}
        	else
        	{
                    String answerString = interpreter.executeExpression(line);
            
                    displayText(answerString);
                    displayPrompt();		        
	        }
	   	}
	   	else
	   	{   		
	   		if(line.equalsIgnoreCase("END"))
	   		{
		   		String answerString = "";

	   			//process the function
	   	//answerString = interpreter.readFunction(functionCode, true, false);
	   			
	   			interactiveMode = true;

		        displayText(answerString);
	   		}		    
	   		else
		   		functionCode += line;
	   		
	        displayPrompt();
	   	}
    }
    
    /**
     * method for the interpreter to display its outpout
     */
    public void displayText(String text)
    {
        System.out.println(text);
    }    
    
    /**
     * display status
     * @param status message
     */
    public void setStatusText(String status)
    {
        
    }
    
    public void displayPrompt()
    {
        System.out.print("> ");
    }        
    
    private String readLine()
    {
        try
        {
            return input.readLine();
        }
        catch(IOException error)
        {
            System.out.println("IO Exception");
        }
        return "";
    }
}
