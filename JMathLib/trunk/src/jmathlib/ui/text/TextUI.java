package jmathlib.ui.text;

import jmathlib.core.interpreter.*;
import jmathlib.core.interfaces.RemoteAccesible;
import jmathlib.ui.common.Console;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import java.util.Vector;

public class textUI implements RemoteAccesible, MathLibOutput
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
    
    public textUI()
    {
        interactiveMode = true;
        functionCode = "";
        exiting = false;
        interpreter = new Interpreter(true, null);
        interpreter.setOutputPanel((MathLibOutput)this);
        interpreter.setDebug(false);
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
        MathLibTUI tui = new MathLibTUI();
        if(args.length == 0)
        {            
            tui.run();
        }
        else
        {
            String filename = args[0];
            tui.interpretLine(filename);
        }
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
                if(line.equalsIgnoreCase("exit") || line.equalsIgnoreCase("quit"))
                {
                    close();
                }
                else
                {
                    String answerString = interpreter.executeExpression(line);
            
                    displayText(answerString);
                    displayPrompt();		        
                }
	        }
	   	}
	   	else
	   	{   		
	   		if(line.equalsIgnoreCase("END"))
	   		{
		   		String answerString = "";

	   			//process the function
	   			answerString = interpreter.readFunction(functionCode, true, false);
	   			
	   			interactiveMode = true;

		        displayText(answerString);
	   		}		    
	   		else
		   		functionCode += line;
	   		
	        displayPrompt();
	   	}
    }
    
    public void displayText(String text)
    {
        System.out.println(text);
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
