package jmathlib.core.graphics.properties;

import java.awt.Color;
import java.text.DecimalFormat;
import jmathlib.core.graphics.*;

public class ColorProperty extends Property
{
	private Color color;

	public ColorProperty(PropertySet parent, String name, Color color)
	{
		super(parent, name);
		this.color = color;
	}

	public void set(Object value) throws PropertyException
	{
		Color c = null;

		if (value instanceof Color)
			c = (Color)value;
		else if (value instanceof String)
			c = decodeColor((String)value);
		else
		{
			try
			{
				double[] cv = (double[])value;
				if (cv.length == 3)
					c = new Color((float)cv[0], (float)cv[1], (float)cv[2]);
			}
			catch (ClassCastException e) { }
			if (c == null)
				throw new PropertyException("invalid color value - " + value.toString());
		}
		setColorInternal(c);
	}

	public Object get()
	{
		return getColor();
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color c)
	{
		setColorInternal(c);
	}

	public void setColor(String s)
	{
		try { setColor(decodeColor(s)); }
		catch (PropertyException e) { }
	}

	public void setColor(char c)
	{
		setColor(new String(new char[] {c}));
	}

	private void setColorInternal(Color c)
	{
		color = c;
		valueChanged();
	}

	private Color decodeColor(String name) throws PropertyException
	{
		if (name.length() == 1)
        {
			switch (name.charAt(0))
			{
			case 'r': return Color.red;
			case 'c': return Color.cyan;
			case 'y': return Color.yellow;
			case 'g': return Color.green;
			case 'm': return Color.magenta;
			case 'k': return Color.black;
			case 'b': return Color.blue;
			case 'w': return Color.white;
			}
        }

        throw new PropertyException("invalid color name - " + name);
	}

	public String toString()
	{
		DecimalFormat fmt = new DecimalFormat("0.0000 ");
		
        return (
			"[ " +
			fmt.format(color.getRed()/255.0) +
			fmt.format(color.getGreen()/255.0) +
			fmt.format(color.getBlue()/255.0) +
			"]");
	}
}
