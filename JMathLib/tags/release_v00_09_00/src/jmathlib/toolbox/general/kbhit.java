package jmathlib.toolbox.general;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import java.awt.event.*;
import jmathlib.ui.common.*;

/**An external function for emitting a beep sound*/
public class kbhit extends ExternalFunction implements KeyListener
{
    
    boolean lock = false;
    
	/** waits until a key is hit on the keyboard*/
	public OperandToken evaluate(Token[] operands)
	{
        
	    lock =  true;
  ((Console)(getInterpreter().getOutputPanel())).addKeyListener(this);  // this = null?
  ((Console)getInterpreter().getOutputPanel()).displayText("asdfasdf");
  
  
  
while(lock)
{        try {        
            Thread.sleep(500);
            debugLine("kbhit s");
        }
        catch (InterruptedException e)
        {
            debugLine("kbhit E");
        }
}

		return null;
	}
    
    public void keyPressed(KeyEvent event)
    {
        lock = false;
        debugLine("kbhit");
    }
    public void keyReleased(KeyEvent event)
    {
        lock = false;
        debugLine("kbhit");
    }
    public void keyTyped(KeyEvent event)
    {
        lock = false;
        debugLine("kbhit");
    }



}

/*
@GROUP
general
@SYNTAX
beep();
@DOC
this sounds an audible beep
@NOTES
@EXAMPLES
beep();
@SEE
*/
