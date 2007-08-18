package jmathlib.core.graphics.properties;

import java.awt.*;
import jmathlib.core.graphics.*;

public class LineStyleProperty extends RadioProperty
{
	public LineStyleProperty(PropertySet parent, String name, String style)
	{
		super(parent, name, new String[] {"-", ":", "--", "-.", "none"}, style);
	}

	public Stroke getStroke(float width)
	{
		String ls = getValue();
		if (ls.equals(":"))
			return new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f,
					new float[] {2.0f, 3.0f}, 0.0f);
		else if (ls.equals("-"))
			return new BasicStroke(width);
		else if (ls.equals("--"))
			return new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f,
					new float[] {10.0f, 5.0f}, 0.0f);
		else if (ls.equals("-."))
			return new BasicStroke(width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f,
					new float[] {5.0f, 5.0f, 1.0f, 5.0f}, 0.0f);
		else
			return null;
	}

	public Stroke getStroke()
	{
		return getStroke(0.0f);
	}

	public void setStyle(String s)
	{
		try { set(s); }
		catch (PropertyException e) { }
	}
}
