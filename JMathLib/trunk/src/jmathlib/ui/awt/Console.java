package jmathlib.ui.awt;

import jmathlib.core.interfaces.RemoteAccesible;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * Class implementing a console style window
 * It needs the ConsoleKeyHandler class to work
 */
public class Console extends TextArea 
{
	int commandNo;

    /**store for previous commands*/
    Vector 			previousCommands;
	
	/**The position of the start of the line*/
	int 			lineStart;

	/**The applet containing the console*/	
	RemoteAccesible callerClass;
	
    /**Event Handler used for handling key events*/
    //public KeyListener keyHandler;

    /**Construct the console*/
	public Console(RemoteAccesible _callerClass)
	{
		
	    commandNo = 0;
        previousCommands = new Vector(10, 10);
		lineStart = getText().length() + 1;
		
		callerClass = _callerClass;
		
		KeyListener keyHandler = new ConsoleKeyHandler();
		
		addKeyListener(keyHandler);
	}	
	
    public void addKeyListener(KeyListener keyHandler)
    {
        //System.out.println("KEYLISTENER");
        System.out.println("KEY "+keyHandler);
        super.addKeyListener(keyHandler);
    }
    
	/**display the previous command in the list*/
	public void prevCommand()
	{
		commandNo--;
    	
    	String text = "";
    	if(commandNo >= 0 && commandNo < previousCommands.size())
    	{
    		text = ((String)previousCommands.elementAt(commandNo)).trim();
    	}
    	else if(commandNo < 0)
    	{
    		text = "";
    		commandNo = -1;
    	}

    	// replace current command with previous command
    	String textArea = getText();
    	int    pos1     = textArea.lastIndexOf("> ") + 2;
    	setText(textArea.substring(0,pos1)+text);
    	
    	// set cursor at the end of the text area
		setCaretPosition(getText().length());
	}	
	
	/**display the next command in the list*/
	public void nextCommand()
	{
		commandNo++;

    	String text = "";
    	if(commandNo >= 0 && commandNo < previousCommands.size())
    	{
    		text = ((String)previousCommands.elementAt(commandNo)).trim();
    	}
    	else if(commandNo >= previousCommands.size())
    	{
    		text = "";
    		commandNo = previousCommands.size();
    	}		    	

    	// replace current command with next command
    	String textArea = getText();
    	int    pos1     = textArea.lastIndexOf("> ") + 2;
    	setText(textArea.substring(0,pos1)+text);
    	
    	// set cursor at the end of the text area
		setCaretPosition(getText().length());
	}

	/**Check that the cursor hasn't moved beyond the start 
	of the line*/	
	public void checkPosition()
	{
		if(getCaretPosition() < lineStart)
			setCaretPosition(lineStart);
	}
	
	/**Go to the home position*/
	public void home()
	{
		setCaretPosition(lineStart - 1);
	}

	/**Go to the end of the line*/
	public void end()
	{
		setCaretPosition(getText().length());
	}
	
	/**Interpret the current line*/
	public void interpretLine()
	{
		//get the text on the current line
        String text = getText();
        String inputString = text.substring(text.lastIndexOf("> ") + 2, text.length());

		/* exit application */
      /*  if(inputString.equals("quit") || 
           inputString.equals("exit")    )
		{
			callerClass.close();
		}*/


        append("\n");
    	callerClass.interpretLine(inputString);
        previousCommands.addElement(inputString);
        commandNo = previousCommands.size();
	}
	
	/**Display the command prompt*/
	public void displayPrompt()
	{
        append("> ");
        lineStart = getText().length() + 1;
		setCaretPosition(lineStart);
	}
	
	//Display a new line*/
	public void newLine()
	{
		append("\n");
	}
	
	/**Display some text on a new line*/
	public void displayText(String text)
	{
		append( text + "\n" );
	}

}
