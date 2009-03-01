/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   stefan@held-mueller.de
 * (c) 2006-2009   
 */
package jmathlib.ui.common;

import java.awt.event.*;

/**Class for handling key events
It only works with the Console class*/
public class ConsoleKeyHandler implements KeyListener
{
	public ConsoleKeyHandler()
	{
	}
	
    public void keyTyped(KeyEvent e){}

	/**Interpret key presses*/
    public void keyPressed(KeyEvent e)
    {
    	Console input = ((Console)e.getSource());
    	int keyValue = e.getKeyCode();

		if((keyValue == KeyEvent.VK_UP)  || 
           (keyValue == KeyEvent.VK_DOWN)   )
		{    	
			//consume the key event so the cursor doesn't move
			e.consume();

	    	if(keyValue == KeyEvent.VK_UP)			//up cursor
	    		input.prevCommand();
	    	else if(keyValue == KeyEvent.VK_DOWN)	//down cursor
	    		input.nextCommand();
	    	
		}
		else if(keyValue == KeyEvent.VK_LEFT) //left cursor
		{
			//check the cursor isn't moving off the current line
			input.checkPosition();
		}
		else if(keyValue == KeyEvent.VK_ENTER)
		{
			//stop the enter from working
			e.consume();
		}
    }

	
    public void keyReleased(KeyEvent e)
    {
    	Console input = ((Console)e.getSource());
    	int keyValue = e.getKeyCode();

		if(keyValue == KeyEvent.VK_ENTER)
			input.interpretLine();
		else if(keyValue == KeyEvent.VK_HOME)
			input.home();
		else if((keyValue == KeyEvent.VK_UP)   ||
                (keyValue == KeyEvent.VK_DOWN)    )
			input.end();
    }	
}
