package jmathlib.toolbox.jmathlib.ui;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import java.awt.*;
import java.awt.event.*;


public class questdlg extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{
		
        // exactly one operand
        if (getNArgIn(operands) != 1)
			throwMathLibException("fix: number of arguments != 1");
        
		// only works on numbers
		if(!(operands[0] instanceof DoubleNumberToken))
			throwMathLibException("fix: only works on numbers");
		
        Frame f = new Frame();
        ModalDialog m = new ModalDialog(f,"kk","What do you want?",new String[]{"ok","not ok","maybe"});
        
        
        //f.show();
        m.show();

        debugLine("questdlg: "+m.str);
        
		DoubleNumberToken matrix = ((DoubleNumberToken)operands[0]);
		OperandToken temp  = ((OperandToken)matrix.clone());
					
		double[][] reValues = matrix.getValuesRe();
		double[][] imValues = matrix.getValuesIm();
		for(int y = 0; y < matrix.getSizeY(); y++)
		{
			for(int x = 0; x < matrix.getSizeX(); x++)
			{
				if(reValues[y][x] >=0 )
				{	
					// e.g. fix(3.2) => 3
					reValues[y][x] = java.lang.Math.floor(reValues[y][x]);
				}
				else
				{
					// e.g. fix(-3.2) => -3
					reValues[y][x] = java.lang.Math.ceil(reValues[y][x]);
				}
				if(imValues[y][x] >=0 )
				{	
					// e.g. fix(3.2i) => 3
					imValues[y][x] = java.lang.Math.floor(imValues[y][x]);
				}
				else
				{
					// e.g. fix(-3.2i) => -3
					imValues[y][x] = java.lang.Math.ceil(imValues[y][x]);
				}
			}
		}
		return new DoubleNumberToken(reValues, imValues);
		
	}	
    
    class ModalDialog extends Dialog implements ActionListener
    {
        public String str = "XX";
        
        public ModalDialog(Frame owner, String title, String msg, String[] buttons)
        {
            super(owner, title, true);
            
            setBackground(Color.lightGray);
            setLayout(new BorderLayout());
            setResizable(false);
            add(new Label(msg), BorderLayout.CENTER);

            Panel panel = new Panel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            
            for (int i=0; i<buttons.length; i++)
            {
                Button button = new Button(buttons[i]);
                button.addActionListener(this);
                panel.add(button);
            }
            
            
            add(panel, BorderLayout.SOUTH);
            pack();
            
            
        }

        public void actionPerformed(ActionEvent event)
        {
            str = event.getActionCommand();
            setVisible(false);
            dispose();
        }
        
    }
}

/*
@GROUP
ui
@SYNTAX
button = questdlg(question, title, button1, button2, options);
@DOC
Rounds the element to the nearest element towards zero.
@EXAMPLES
<programlisting>
button = questdlg("do you like JMathLib","What about JMathLib","yes","even mor","yes");
</programlisting>
@NOTES
@SEE

**/

