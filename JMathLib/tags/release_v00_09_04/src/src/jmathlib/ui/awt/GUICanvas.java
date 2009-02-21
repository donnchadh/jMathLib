package jmathlib.ui.awt;

import java.awt.*;

/**Simple Canvas extension*/
public class GUICanvas extends Canvas
{
	//private int width;
	//private int height;

	protected GUICanvas()
	{
		super();
		this.setVisible(false);
		this.setBackground(new Color(255,255,255));
		this.setSize(200, 200);
		this.setLocation(100,100);
		this.setEnabled(true);
		this.setVisible(true);
	}
	
	public void paint(Graphics g)
	{
		//recalculateBounds();
		g.setColor(new Color(0,0,0));
		g.drawLine(0,0,10,10);
		g.drawRect(10,10,20,20);
	}

	//private void recalculateBounds()
	//{
	//	width  = this.getBounds().width;
	//	height = this.getBounds().height;
	//}

	/**Returns the canvas class to draw*/
	public GUICanvas getCanvas()
	{
		return this;
	}

	/**The main MenuBar initializer*/
	public void draw(int x, int y)
	{
		draw(x, y, new Color(0));
	}

	/**The main MenuBar initializer*/
	public void draw(int x, int y, Color color)
	{
	}
}
