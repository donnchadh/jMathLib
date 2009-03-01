/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   stefan@held-mueller.de
 * (c) 2008-2009   
 */
package jmathlib.ui.applet;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.core.interfaces.RemoteAccessible;
import jmathlib.core.interfaces.JMathLibOutput;
import jmathlib.ui.common.Console;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/**applet version of JMathLib*/
public class JMathLibGUI extends Applet implements RemoteAccessible, JMathLibOutput
{
	/**Flag storing whether the program is running as an application or an applet*/
    //boolean 		runningStandalone;

	/**The area used for user input and where the answers are displayed*/
    Console 		answer;

	/**The interpreter*/
    Interpreter		interpreter;

    /**The container used by the applet / application*/
    Container 		container;

    /**string used for defining user functions*/
    //String 			function;

    /**Flag storing whether each line input should be executed straight away*/
    //boolean 		interactiveMode = true;

    /**Layout manager used for the components*/
    BorderLayout layout;

    /**Event Handler for intercepting window events*/
    WindowListener handler;

    /**Temporary store for function code*/
    //String functionCode;
    
    /**
     * Constructor of the applet
     */
    public JMathLibGUI()
    {
    	container         = this;
    }

    /**
     * Initialize the applet
     */
    public void init()
    {
        
        container.setSize(700,400);

        layout = new BorderLayout();
        container.setLayout(layout);
        
        answer          = new Console(this);

        container.add("Center", answer);

        interpreter = new Interpreter(false);        
        
        interpreter.setOutputPanel(this);
        interpreter.executeExpression("startup");
        
        // get parameter for background color (e.g. ff00cc)
        try { 
            Color color = new Color(Integer.parseInt(this.getParameter("bgcolor"),16));
            if (color!=null) 
                answer.setBackground(color);
        }
        catch (NumberFormatException e){ }

        // get parameter for foreground color (e.g. ffddff)
        try { 
            Color color = new Color(Integer.parseInt(this.getParameter("fgcolor"),16));
            if (color!=null) 
                answer.setForeground(color);
        }
        catch (NumberFormatException e){ }  
        
    } // end init
    
    /**
     * display text
     */
    public void displayText(String text)
    {
        answer.displayText(text);
    }
    

    /**
     * display status message
     */
    public void setStatusText(String status)
    {
        
    }

    /**
     * start the applet
     */
    public void start()
    {
    	answer.displayPrompt();
        answer.requestFocus();

        // check if there is an initial command to send to the interpreter
        String startWithS = getParameter("startup");
        if (startWithS!=null)
            interpreter.executeExpression(startWithS);

        answer.displayPrompt();
        answer.requestFocus();

    }

   
    /**
     * Interpret the last command line entered
     */
    public void interpretLine(String line)
    {
        String answerString = interpreter.executeExpression(line);
        answer.displayText(answerString);
        answer.displayPrompt();
    }
    
    /**
     * Function called when the GUI is being close
     */
    public void close()
    {

    }
}
