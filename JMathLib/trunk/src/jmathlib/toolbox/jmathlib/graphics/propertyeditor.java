package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.graphics.HandleObject;
import jmathlib.core.graphics.properties.Property;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

public class propertyeditor extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands)
	{
        
        //if (getNArgIn(operands) != 0)
		//	throwMathLibException("gca: number of arguments != 0");

        // get handle to axes
        //double n = getGraphicsManager().getCurrentFigure().getCurrentAxes().getHandle();
        
	    double x=0;
	    
        if (getNArgIn(operands) >= 1)
        {
            if (!(operands[0] instanceof DoubleNumberToken)) 
            throwMathLibException("get: argument must be a number");

            x = ((DoubleNumberToken)operands[0]).getValueRe();
        }
        
        Object obj = null;
        try {
            obj = HandleObject.getHandleObject((int)x);
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        
        PropertyEditorFrame propeditor = new PropertyEditorFrame(obj);
        propeditor.setLocation(100,100);
        propeditor.setSize(300,600);
        propeditor.setVisible(true);
        
		return new DoubleNumberToken(1.0);
	}
}


class PropertyEditorFrame extends JFrame 
{
    Object obj = null;
    
    public PropertyEditorFrame(Object _obj)
    {
        super("Property Editor");
        
        this.obj = _obj;
        
        //addWindowListener(new WindowClosingAdapter(true));
        //String[][] data = {{"1","2"}, {"1","2"}, {"1","2"}, {"1","2"}};
        String[] colheads ={"property", "value"};
        
        int l = ((HandleObject)obj).values().size();
        
        String[][] data = new String[l][2];
        
        Iterator it = ((HandleObject)obj).values().iterator();
        int i=0;
        while (it.hasNext())
        {
            Property p = (Property)it.next();
            System.out.println("show  " + p.getName() + " = " + p);
            data[i][0]= p.getName();
            data[i][1]= p.toString();
            i++;
        }
        
        JTable table = new JTable( data, colheads);
        
        Container cp = getContentPane();
        
        cp.add(new Label("Graphical properties"), BorderLayout.NORTH);
        cp.add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
}


/*
@GROUP
graphics
@SYNTAX
gca
@DOC
get handle to current axis
@EXAMPLES
.
@NOTES
@SEE
gcf, gco, gcbo, gcbf
*/

