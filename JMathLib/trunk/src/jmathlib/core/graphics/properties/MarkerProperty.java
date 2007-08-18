package jmathlib.core.graphics.properties;

import java.awt.*;
import jmathlib.core.graphics.*;

public class MarkerProperty extends RadioProperty
{
	public MarkerProperty(PropertySet parent, String name, String marker)
	{
		super(parent, name, new String[] {
			"o", "s", "x", "+", "*", "d",
			"^", ">", "<", "v", ".", "none"}, marker);
	}

	public void drawMarker(Graphics g, int x, int y, double markerSize)
	{
		int size = (int)markerSize, size2 = (int)(markerSize/2);
		int size3 = (int)(markerSize/3), size23 = (int)((markerSize*2)/3);

		switch (getValue().charAt(0))
		{
		case 'o':
			g.drawOval(x-size2, y-size2, size-1, size-1);
			break;
		case 's':
			g.drawRect(x-size2, y-size2, size-1, size-1);
			break;
		case 'x':
			g.drawLine(x-size2, y-size2, x+size2, y+size2);
			g.drawLine(x+size2, y-size2, x-size2, y+size2);
			break;
		case '+':
			g.drawLine(x-size2, y, x+size2, y);
			g.drawLine(x, y-size2, x, y+size2);
			break;
		case '*':
			g.drawLine(x-size2, y, x+size2, y);
			g.drawLine(x, y-size2, x, y+size2);
			g.drawLine(x-size3, y-size3, x+size3, y+size3);
			g.drawLine(x+size3, y-size3, x-size3, y+size3);
			break;
		case 'd':
			g.drawPolygon(
				new int[] {x-size2, x, x+size2, x},
				new int[] {y, y-size2, y, y+size2},
				4);
			break;
		case '^':
			g.drawPolygon(
				new int[] {x-size2, x, x+size2},
				new int[] {y+size3, y-size23, y+size3},
				3);
			break;
		case '>':
			g.drawPolygon(
				new int[] {x-size3, x+size23, x-size3},
				new int[] {y-size2, y, y+size2},
				3);
			break;
		case '<':
			g.drawPolygon(
				new int[] {x-size23, x+size3, x+size3},
				new int[] {y, y-size2, y+size2},
				3);
			break;
		case 'v':
			g.drawPolygon(
				new int[] {x-size2, x, x+size2},
				new int[] {y-size3, y+size23, y-size3},
				3);
			break;
		case '.':
			g.fillOval(x-1, y-1, 3, 3);
			break;
		}
	}

	public boolean isSet()
	{
		return !is("none");
	}

	public void setMarker(String s)
	{
		try { set(s); }
		catch (PropertyException e) { }
	}
}
