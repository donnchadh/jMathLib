package jmathlib.core.graphics;

import java.awt.*;
//import java.awt.print.PageFormat;
//import java.awt.print.PrinterException;
//import java.util.Vector;
import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.graphics.properties.*;

/** created and holds the axes of a plot*/
public class AxesObject extends GraphicalObject implements PropertyListener
{
	protected class AxesComponent extends Component
	{
		private AxesObject parent;

		AxesComponent(AxesObject parent)
		{
			this.parent = parent;
		}

		public Dimension getPreferredSize()
		{
			return getMinimumSize();
		}

		public Dimension getMinimumSize()
		{
			return parent.getMinimumSize();
		}

		public void update(Graphics g)
		{
			paint(g);
		}

		public void paint(Graphics g)
		{
			parent.paint(g);
		}
	}

	// size of component;
	int dyFrame = 400;
	int dxFrame = 400;

	/**title of this axis*/
	protected TextObject title = null;

	/**name of x-axis*/
	protected TextObject xLabel = null;

	/**name of y-axis*/
	protected TextObject yLabel = null;

	/**name of z-axis*/
	protected TextObject zLabel = null;

	/**double-buffering data*/
	protected Image     backBuffer;
	protected Graphics  backGC = null;
	protected Dimension backSize;
	protected boolean   backEnabled = true;

	/**internal component*/
	protected AxesComponent component;

	/**axes properties*/
    protected DoubleStructProperty ALimP = new DoubleStructProperty(this, "ALim", new String[] {"x","y"}, new double[] {-1 , 1});

    protected BooleanProperty BoxP = new BooleanProperty(this, "Box", true);

    protected ColorProperty ColorP = new ColorProperty(this, "Color", Color.white);

    protected RadioProperty XAxisLocationP = new RadioProperty(this, "XAxisLocation", new String[] {"top", "bottom"}, "bottom");
    protected RadioProperty YAxisLocationP = new RadioProperty(this, "YAxisLocation", new String[] {"left", "right"}, "left");
    protected RadioProperty ZAxisLocationP = new RadioProperty(this, "ZAxisLocation", new String[] {"left", "right"}, "left");

    protected DoubleArrayProperty XLimP = new DoubleArrayProperty(this, "XLim", new double[] {0.0, 1.0}, 2);
    protected DoubleArrayProperty YLimP = new DoubleArrayProperty(this, "YLim", new double[] {0.0, 1.0}, 2);
    protected DoubleArrayProperty ZLimP = new DoubleArrayProperty(this, "ZLim", new double[] {-0.5, 0.5}, 2);

    protected RadioProperty XLimModeP = new RadioProperty(this, "XLimMode", new String[] {"auto", "manual"}, "auto");
    protected RadioProperty YLimModeP = new RadioProperty(this, "YLimMode", new String[] {"auto", "manual"}, "auto");
    protected RadioProperty ZLimModeP = new RadioProperty(this, "ZLimMode", new String[] {"auto", "manual"}, "auto");

    protected BooleanProperty XGridP = new BooleanProperty(this, "XGrid", false);
    protected BooleanProperty YGridP = new BooleanProperty(this, "YGrid", false);
    protected BooleanProperty ZGridP = new BooleanProperty(this, "ZGrid", false);

    protected BooleanProperty XMinorGridP = new BooleanProperty(this, "XMinorGrid", false);
    protected BooleanProperty YMinorGridP = new BooleanProperty(this, "YMinorGrid", false);
    protected BooleanProperty ZMinorGridP = new BooleanProperty(this, "ZMinorGrid", false);

    protected LineStyleProperty XGridStyleP = new LineStyleProperty(this, "XGridStyle", ":");
    protected LineStyleProperty YGridStyleP = new LineStyleProperty(this, "YGridStyle", ":");
    protected LineStyleProperty ZGridStyleP = new LineStyleProperty(this, "ZGridStyle", ":");

    protected ColorProperty XColorP = new ColorProperty(this, "XColor", Color.black);
    protected ColorProperty YColorP = new ColorProperty(this, "YColor", Color.black);
    protected ColorProperty ZColorP = new ColorProperty(this, "ZColor", Color.black);

    protected RadioProperty XScaleP = new RadioProperty(this, "XScale", new String[] {"linear", "log"}, "linear");
    protected RadioProperty YScaleP = new RadioProperty(this, "YScale", new String[] {"linear", "log"}, "linear");
    protected RadioProperty ZScaleP = new RadioProperty(this, "ZScale", new String[] {"linear", "log"}, "linear");

    protected DoubleArrayProperty XTickP = new DoubleArrayProperty(this, "XTick", new double[0], -1);
    protected DoubleArrayProperty YTickP = new DoubleArrayProperty(this, "YTick", new double[0], -1);
    protected DoubleArrayProperty ZTickP = new DoubleArrayProperty(this, "ZTick", new double[0], -1);

    protected RadioProperty XTickModeP = new RadioProperty(this, "XTickMode", new String[] {"auto", "manual"}, "auto");
    protected RadioProperty YTickModeP = new RadioProperty(this, "YTickMode", new String[] {"auto", "manual"}, "auto");
    protected RadioProperty ZTickModeP = new RadioProperty(this, "ZTickMode", new String[] {"auto", "manual"}, "auto");

    protected StringArrayProperty XTickLabelP = new StringArrayProperty(this, "XTickLabel", new String[0]);
    protected StringArrayProperty YTickLabelP = new StringArrayProperty(this, "YTickLabel", new String[0]);
    protected StringArrayProperty ZTickLabelP = new StringArrayProperty(this, "ZTickLabel", new String[0]);

    protected RadioProperty XTickLabelModeP = new RadioProperty(this, "XTickLabelMode", new String[] {"auto", "manual"}, "auto");
    protected RadioProperty YTickLabelModeP = new RadioProperty(this, "YTickLabelMode", new String[] {"auto", "manual"}, "auto");
    protected RadioProperty ZTickLabelModeP = new RadioProperty(this, "ZTickLabelMode", new String[] {"auto", "manual"}, "auto");

    protected RadioProperty NextPlotP = new RadioProperty(this, "NextPlot", new String[] {"replace", "add"}, "replace");
    
    protected HandleObjectListProperty ChildrenP = new HandleObjectListProperty(this, "Children", -1);

    
    
	public AxesObject()
   	{
		component = new AxesComponent(this);

		autoTick();

        ALimP.addPropertyListener(this);
        BoxP.addPropertyListener(this);
        ColorP.addPropertyListener(this);
		XLimP.addPropertyListener(this);
		YLimP.addPropertyListener(this);
		ZLimP.addPropertyListener(this);
		XLimModeP.addPropertyListener(this);
		YLimModeP.addPropertyListener(this);
		ZLimModeP.addPropertyListener(this);
        XGridP.addPropertyListener(this);
        YGridP.addPropertyListener(this);
        ZGridP.addPropertyListener(this);
        XGridStyleP.addPropertyListener(this);
        YGridStyleP.addPropertyListener(this);
        ZGridStyleP.addPropertyListener(this);
		XTickP.addPropertyListener(this);
		YTickP.addPropertyListener(this);
		ZTickP.addPropertyListener(this);
		XTickModeP.addPropertyListener(this);
		YTickModeP.addPropertyListener(this);
		ZTickModeP.addPropertyListener(this);
		XTickLabelP.addPropertyListener(this);
		YTickLabelP.addPropertyListener(this);
		ZTickLabelP.addPropertyListener(this);
		XTickLabelModeP.addPropertyListener(this);
		YTickLabelModeP.addPropertyListener(this);
		ZTickLabelModeP.addPropertyListener(this);
   	}

	public Component getComponent()
	{
		return component;
	}

	private synchronized void newBackBuffer() {
		backBuffer = component.createImage(getSize().width, getSize().height);
		if (backGC != null) {
			backGC.dispose();
		}	
		if (backBuffer != null)
			backGC = backBuffer.getGraphics();
		backSize = getSize();
	}

	protected Graphics initBackBuffer(Graphics g)
	{
		if (backEnabled)
		{
			if (backGC == null)
			{
				backBuffer = component.createImage(getSize().width, getSize().height);
				backGC = backBuffer.getGraphics();
				backSize = getSize();
			}
			else if (!backSize.equals(getSize()))
				newBackBuffer();
			backGC.setColor(component.getBackground());
			backGC.fillRect(0, 0, getSize().width, getSize().height);
			return backGC;
		}
		else
		{
			//g.clearRect(0, 0, getSize().width, getSize().height);
			return g;
		}
	}

	protected void flushBackBuffer(Graphics g, Graphics backGC)
	{
		if (backEnabled)
			g.drawImage(backBuffer, 0, 0, component);
	}

	/** add a line to the current plot */
	public void addLine(double[] _x, double[] _y)
	{
		addLine(_x, _y, "r", "-", "none");
	}

	public void addLine(double[] _x, double[] _y, String color, String lineStyle, String marker)
	{
		if (NextPlotP.is("replace"))
			ChildrenP.removeAllElements();

        if (lineStyle.equals(" "))
            lineStyle = "-";

        if (marker.equals(" "))
            marker = "none";

		LineObject line = new LineObject(_x, _y, color, lineStyle, marker);
		line.setParent(this);
		ChildrenP.addElement(line);
		autoScale();
        repaint();
	}

	/** add lines to the current plot */
	public void addLines(double[] _x, double[][] _y, String color, String lineStyle, String marker)
	{
		if (NextPlotP.is("replace"))
            ChildrenP.removeAllElements();

        if (lineStyle.equals(" "))
            lineStyle = "-";

        if (marker.equals(" "))
            marker = "none";

		for (int n=0; n<_y.length; n++)
		{
			LineObject line = new LineObject(_x, _y[n], color, lineStyle, marker);
			line.setParent(this);
            ChildrenP.addElement(line);
		}
		autoScale();
        repaint();
	}

	/** add a title to the current axes */
	public void setTitle(String _title)
	{
		title = new TextObject(_title, TextObject.H_CENTER, TextObject.V_BOTTOM);
	}

	/** add text to the x-axis of this axes*/
	public void setXLabel(String _xLabel)
	{
		xLabel = new TextObject(_xLabel, TextObject.H_CENTER, TextObject.V_TOP);
	}

	/** add text to the y-axis of this axes*/
	public void setYLabel(String _yLabel)
	{
		yLabel = new TextObject(_yLabel, TextObject.H_CENTER, TextObject.V_BOTTOM);
		yLabel.setRotation(-90);
	}

	/** add text to the z-axis of this axes*/
	public void setZLabel(String _zLabel)
	{
		zLabel = new TextObject(_zLabel, TextObject.H_CENTER, TextObject.V_BOTTOM);
		zLabel.setRotation(-90);
	}


	/** Indicate wether calls to addLine() will add lines or
        clear the axes before adding new lines                 */
	public void setHold(boolean _holdSwitch)
	{
		NextPlotP.update(_holdSwitch ? "add" : "replace");
	}

	public Dimension getMinimumSize()
	{
        //ErrorLogger.debugLine("AxesObject getMinimumSize");
		return new Dimension(dyFrame,dyFrame);
	}

	public Dimension getSize()
	{
		return component.getSize();
	}

	public void repaint() 
	{
		component.repaint();
	}

	public void setBackBuffer(boolean flag)
	{
		backEnabled = flag;
	}

	protected void autoScale()
	{
		autoScaleX();
		autoScaleY();
		autoScaleZ();
	}

	protected void autoScaleX()
	{
		double xmin, xmax;
		if (XLimModeP.is("auto") && ChildrenP.size() > 0)
		{
			xmin = ((GraphicalObject)ChildrenP.elementAt(0)).getXMin();
			xmax = ((GraphicalObject)ChildrenP.elementAt(0)).getXMax();
			for (int i=1; i<ChildrenP.size(); i++)
			{
				double _xmin = ((GraphicalObject)ChildrenP.elementAt(i)).getXMin();
				double _xmax = ((GraphicalObject)ChildrenP.elementAt(i)).getXMax();
				if (_xmin < xmin) xmin = _xmin;
				if (_xmax > xmax) xmax = _xmax;
			}

			double dx = xmax-xmin;
			if (dx > 10)
			{
				xmin = Math.floor(xmin);
				xmax = Math.ceil(xmax);
			}
			else if (dx == 0)
			{
				xmin--;
				xmax++;
			}

			XLimP.update(new double[] {xmin, xmax});
			autoTickX();
		}
	}

	protected void autoScaleY()
	{
		double ymin, ymax;
		if (YLimModeP.is("auto") && ChildrenP.size() > 0)
		{
			ymin = ((GraphicalObject)ChildrenP.elementAt(0)).getYMin();
			ymax = ((GraphicalObject)ChildrenP.elementAt(0)).getYMax();
			for (int i=1; i<ChildrenP.size(); i++)
			{
				double _ymin = ((GraphicalObject)ChildrenP.elementAt(i)).getYMin();
				double _ymax = ((GraphicalObject)ChildrenP.elementAt(i)).getYMax();
				if (_ymin < ymin) ymin = _ymin;
				if (_ymax > ymax) ymax = _ymax;
			}
		
			double dy = ymax-ymin;
			if (dy > 10) 
			{
				// nice numbers on axis
				ymin = Math.floor(ymin);
				ymax = Math.ceil(ymax);
			}
			else if (dy == 0)
			{
				ymin--;
				ymax++;
			}

			YLimP.update(new double[] {ymin, ymax});
			autoTickY();
		}
	}

	protected void autoScaleZ()
	{
		double zmin, zmax;
		if (ZLimModeP.is("auto") && ChildrenP.size() > 0)
		{
			zmin = ((GraphicalObject)ChildrenP.elementAt(0)).getZMin();
			zmax = ((GraphicalObject)ChildrenP.elementAt(0)).getZMax();
			for (int i=1; i<ChildrenP.size(); i++)
			{
				double _zmin = ((GraphicalObject)ChildrenP.elementAt(i)).getZMin();
				double _zmax = ((GraphicalObject)ChildrenP.elementAt(i)).getZMax();
				if (_zmin < zmin) zmin = _zmin;
				if (_zmax > zmax) zmax = _zmax;
			}

			double dz = zmax-zmin;
			if (dz > 10)
			{
				zmin = Math.floor(zmin);
				zmax = Math.ceil(zmax);
			}
			else if (dz == 0)
			{
				zmin--;
				zmax++;
			}

			ZLimP.update(new double[] {zmin, zmax});
			autoTickZ();
		}
	}

	protected void autoTick()
	{
		autoTickX();
		autoTickY();
		autoTickZ();
	}

	protected void autoTickX()
	{
		if (XTickModeP.is("auto"))
		{
			double xmin = XLimP.getArray()[0];
            double xmax = XLimP.getArray()[1];
			double[] ticks = new double[5];
			for (int i=0; i<ticks.length; i++)
				ticks[i] = xmin + i * (xmax - xmin) / (ticks.length - 1);
			XTickP.update(ticks);
		}
		autoTickLabelX();
	}

	protected void autoTickY()
	{
		if (YTickModeP.is("auto"))
		{
			double ymin = YLimP.getArray()[0];
            double ymax = YLimP.getArray()[1];
			double[] ticks = new double[5];
			for (int i=0; i<ticks.length; i++)
				ticks[i] = ymin + i * (ymax - ymin) / (ticks.length - 1);
			YTickP.update(ticks);
		}
		autoTickLabelY();
	}

	protected void autoTickZ()
	{
		if (ZTickModeP.is("auto"))
		{
			double zmin = ZLimP.getArray()[0];
            double zmax = ZLimP.getArray()[1];
			double[] ticks = new double[5];
			for (int i=0; i<ticks.length; i++)
				ticks[i] = zmin + i * (zmax - zmin) / (ticks.length - 1);
			ZTickP.update(ticks);
		}
		autoTickLabelZ();
	}

	protected void autoTickLabel()
	{
		autoTickLabelX();
		autoTickLabelY();
		autoTickLabelZ();
	}

	protected void autoTickLabelX()
	{
		double[] ticks = XTickP.getArray();
		String[] labels = new String[ticks.length];
		for (int i=0; i<ticks.length; i++)
		{
			double val = ((double)Math.round(ticks[i]*100))/100;
			labels[i] = new Double(val).toString();
		}
		XTickLabelP.update(labels);
	}

	protected void autoTickLabelY()
	{
		double[] ticks = YTickP.getArray();
		String[] labels = new String[ticks.length];
		for (int i=0; i<ticks.length; i++)
		{
			double val = ((double)Math.round(ticks[i]*100))/100;
			labels[i] = new Double(val).toString();
		}
		YTickLabelP.update(labels);
	}

	protected void autoTickLabelZ()
	{
		double[] ticks = ZTickP.getArray();
		String[] labels = new String[ticks.length];
		for (int i=0; i<ticks.length; i++)
		{
			double val = ((double)Math.round(ticks[i]*100))/100;
			labels[i] = new Double(val).toString();
		}
		ZTickLabelP.update(labels);
	}

	public void paint(Graphics _g) 
	{
	    ErrorLogger.debugLine("AxesObject: paint");
        
		Graphics g = initBackBuffer(_g);

		Graphics2D g2d = (Graphics2D)g;
		//g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		//g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

		//setBounds(72,72,451,697);
		//System.out.println("width = "+this.getSize().width+" height = "+this.getSize().height);
		dyFrame = this.getSize().height;
		dxFrame = this.getSize().width;

		//ErrorLogger.debugLine("AxesObject dxFrame="+dxFrame+" dyFframe="+dyFrame);
		//ErrorLogger.debugLine("AxesObject getX="+this.getX()+" getY="+this.getY());
		//ErrorLogger.debugLine("AxesObject getLocation.x="+this.getLocation().x);

		// if these axes hold no data -> do nothing
		//if (axesElements.size() == 0) return;

		// size of curves
		int dyCurves = (int)(dyFrame*3/4);
		int dxCurves = (int)(dxFrame*3/4);

		// Origin of curves
		int dyOrig = (dyFrame-dyCurves)/2;
		int dxOrig = (dxFrame-dxCurves)/2;


		FontMetrics fM  = g.getFontMetrics();
		int    sAscent  = fM.getAscent();
		int    sDescent = fM.getDescent();


		// Find range of x-axis and y-axis
		double xmin, xmax, ymin, ymax, dx, dy;
		// X axis
		xmin = XLimP.getArray()[0];
		xmax = XLimP.getArray()[1];
		dx = xmax-xmin;
		// Y axis
		ymin = YLimP.getArray()[0];
		ymax = YLimP.getArray()[1];
		dy = ymax-ymin;

		// add white rectangle as background
		g.setColor( ((ColorProperty)getProperty("Color")).getColor() );
		g.fillRect(dxOrig, dyOrig, dxCurves, dyCurves);
		
		Stroke normS = g2d.getStroke();

		int yLabMinX = dxOrig;
		int xLabMinY = dyOrig+dxCurves;

		// X Grid
		boolean  doXGrid     = XGridP.isSet() && !XGridStyleP.is("none");
		double[] xticks      = XTickP.getArray();
		String[] xticklabels = XTickLabelP.getArray();
		Stroke   xS          = XGridStyleP.getStroke(1);
		g2d.setColor(XColorP.getColor());

        ErrorLogger.debugLine("doXGrid xticks.length " + xticks.length);
		for (int i=0; i<xticks.length; i++)
		{
			int xt = dxOrig + (int)((xticks[i] - xmin) / (xmax - xmin) * dxCurves);

			if (xt < dxOrig || xt > (dxFrame-dxOrig))
				continue;

			// grid line
			if (doXGrid)
			{
				g2d.setStroke(xS);
				g.drawLine(xt, dyOrig, xt, dyOrig+dyCurves);
				g2d.setStroke(normS);
			}

			// tick mark
			g.drawLine(xt, dyOrig, xt, dyOrig+5);
			g.drawLine(xt, dyOrig+dyCurves, xt, dyOrig+dyCurves-5);

			// tick text
			if (i < xticklabels.length)
			{
				int    sXWidth = fM.stringWidth(xticklabels[i]);
				g.drawString( xticklabels[i], 
					          xt-sXWidth/2, 
					          dyFrame-dyOrig+5+sAscent);
			}
		}

		// Y Grid
		boolean doYGrid = YGridP.isSet() && !YGridStyleP.is("none");
		double[] yticks = YTickP.getArray();
		String[] yticklabels = YTickLabelP.getArray();
		Stroke yS = YGridStyleP.getStroke(1);
		g2d.setColor(YColorP.getColor());
		for (int i=0; i<yticks.length; i++)
		{
			int yt = dyFrame - dyOrig - (int)((yticks[i] - ymin) / (ymax - ymin) * dyCurves);

			if (yt < dyOrig || yt > (dyFrame-dyOrig))
				continue;

			// grid line
			if (doYGrid)
			{
				g2d.setStroke(yS);
				g.drawLine(dxOrig, yt, dxOrig+dxCurves, yt);
				g2d.setStroke(normS);
			}

			// tick mark
			g.drawLine(dxOrig, yt, dxOrig+5, yt);
			g.drawLine(dxOrig+dxCurves, yt, dxOrig+dxCurves-5, yt);

			// tick text
			if (i < yticklabels.length)
			{
				int    sYWidth = fM.stringWidth(yticklabels[i]);
				g.drawString( yticklabels[i],
						dxOrig - 5 - sYWidth, 
						(int)(yt+(sAscent/2)) );
				if (dxOrig-5-sYWidth < yLabMinX)
					yLabMinX = dxOrig-5-sYWidth;
			}
		}

		// draw borders
        if (BoxP.isSet())
        {
            g.setColor(XColorP.getColor());
    		g.drawLine( dxOrig,          dyOrig+dyCurves, dxOrig+dxCurves, dyOrig+dyCurves);
    		g.drawLine( dxOrig,          dyOrig,          dxOrig+dxCurves, dyOrig);
    		g.setColor(YColorP.getColor());
    		g.drawLine( dxOrig+dxCurves, dyOrig+dyCurves, dxOrig+dxCurves, dyOrig);
    		g.drawLine( dxOrig,          dyOrig,          dxOrig,          dyOrig+dyCurves);
    		g.setColor(Color.black);
        }
        
		// Add title
		if (title != null)
		{
			title.setPlotArea(dxOrig+dxCurves/2, dyOrig-5, 0, 0);
			title.paint(g);
		}

		// Add label of x-axis
		if (xLabel != null)
		{
			xLabel.setPlotArea(dxOrig+dxCurves/2, dyFrame-dyOrig+5+sAscent+sDescent, 0, 0);
			xLabel.paint(g);
		}

		// Add label of y-axis
		if (yLabel != null)
		{
			yLabel.setPlotArea(yLabMinX-5, dyFrame/2, 0, 0);
			yLabel.paint(g);
		}


		// plot line objects
		Shape clip  = g.getClip();
		g.clipRect(dxOrig+1, dyOrig+1, dxCurves-1, dyCurves-1);
		for(int n = 0; n < ChildrenP.size(); n++)
		{
			((LineObject)ChildrenP.elementAt(n)).setAxesBoundaries(xmin, xmax, ymin, ymax);
			((LineObject)ChildrenP.elementAt(n)).setPlotArea(dxOrig,dyFrame-dyOrig,dxCurves,dyCurves);
			((LineObject)ChildrenP.elementAt(n)).paint(g);
		}
		g.setClip(clip);

		flushBackBuffer(_g, g);

	}

	public void propertyChanged(Property p)
	{
        ErrorLogger.debugLine("Axesobject property changed: "+ p.getName());
        
        if (p == XLimP)
		{
			XLimModeP.update("manual");
			autoTickX();
		}
		else if (p == YLimP)
		{
			YLimModeP.update("manual");
			autoTickY();
		}
		else if (p == ZLimP)
		{
			ZLimModeP.update("manual");
			autoTickZ();
		}
		else if (p == XLimModeP)
		{
			if (XLimModeP.is("auto"))
				autoScaleX();
		}
		else if (p == YLimModeP)
		{
			if (YLimModeP.is("auto"))
				autoScaleY();
		}
		else if (p == ZLimModeP)
		{
			if (ZLimModeP.is("auto"))
				autoScaleZ();
		}
		else if (p == XTickP)
			XTickModeP.update("manual");
		else if (p == YTickP)
			YTickModeP.update("manual");
		else if (p == ZTickP)
			ZTickModeP.update("manual");
		else if (p == XTickModeP)
		{
			if (XTickModeP.is("auto"))
				autoTickX();
		}
		else if (p == YTickModeP)
		{
			if (YTickModeP.is("auto"))
				autoTickY();
		}
		else if (p == ZTickModeP)
		{
			if (ZTickModeP.is("auto"))
				autoTickZ();
		}
		else if (p == XTickLabelP)
			XTickLabelModeP.update("manual");
		else if (p == YTickLabelP)
			YTickLabelModeP.update("manual");
		else if (p == ZTickLabelP)
			ZTickLabelModeP.update("manual");
		else if (p == XTickLabelModeP)
		{
			if (XTickLabelModeP.is("auto"))
				autoTickLabelX();
		}
		else if (p == YTickLabelModeP)
		{
			if (YTickLabelModeP.is("auto"))
				autoTickLabelY();
		}
		else if (p == ZTickLabelModeP)
		{
			if (ZTickLabelModeP.is("auto"))
				autoTickLabelZ();
		}
        
        repaint();
        
	}
    
    public void clearAxes()
    {
        ChildrenP.removeAllElements();
    }
}




