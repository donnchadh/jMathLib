package jmathlib.core.graphics.axes;

import java.awt.*;
import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.graphics.*;
import jmathlib.core.graphics.properties.*;
import jmathlib.core.graphics.axes.coreObjects.*;


/** created and holds the axes of a plot*/
public class AxesObject extends FigureObject implements PropertyListener
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
	protected int dyFrame = 400;
	protected int dxFrame = 400;

	/**title of this axis*/
	protected TextObject title = null;

	/**double-buffering data*/
	protected Image     backBuffer;
	protected Graphics  backGC = null;
	protected Dimension backSize;
	protected boolean   backEnabled = true;

	/**internal component*/
	protected AxesComponent component;

	/**axes properties*/
    public RadioProperty ActivePositionPropertyP = new RadioProperty(this, "ActivePositionProperty", new String[] {"outerposition", "position"}, "outerposition");
    public DoubleStructProperty ALimP = new DoubleStructProperty(this, "ALim", new String[] {"x","y"}, new double[] {-1 , 1});
    public RadioProperty ALimModeP = new RadioProperty(this, "ALimMode", new String[] {"auto", "manual"}, "auto");
    public ColorProperty AmbientLightColorP = new ColorProperty(this, "AmbientLightColor", Color.white);
    public BooleanProperty BoxP = new BooleanProperty(this, "Box", true);
    public DoubleVectorProperty CameraPositionP = new DoubleVectorProperty(this, "CameraPosition", new double[3], 3);
    public RadioProperty CameraPositionModeP = new RadioProperty(this, "CameraPositionMode", new String[] {"auto", "manual"}, "auto");
    public DoubleVectorProperty CameraTargetP = new DoubleVectorProperty(this, "CameraTarget", new double[3], 3);
    public RadioProperty CameraTargetModeP = new RadioProperty(this, "CameraTargetMode", new String[] {"auto", "manual"}, "auto");
    public DoubleVectorProperty CameraUpVectorP = new DoubleVectorProperty(this, "CameraUpVector", new double[3], 3);
    public RadioProperty CameraUpVectorModeP = new RadioProperty(this, "CameraUpVectorMode", new String[] {"auto", "manual"}, "auto");
    public DoubleProperty CameraViewAngleP = new DoubleProperty(this, "CameraViewAngle", 1.0);
    public RadioProperty CameraViewAngleModeP = new RadioProperty(this, "CameraViewAngleMode", new String[] {"auto", "manual"}, "auto");
    public HandleObjectListProperty ChildrenP = new HandleObjectListProperty(this, "Children", -1);
    public DoubleVectorProperty CLimP = new DoubleVectorProperty(this, "CLim", new double[] {0.0, 1.0}, 2);
    public RadioProperty CLimModeP = new RadioProperty(this, "CLimMode", new String[] {"auto", "manual"}, "auto");
    public ColorProperty ColorP = new ColorProperty(this, "Color", Color.white);
    public ColormapProperty ColorOrderP = new ColormapProperty(this, "Colormap");
    public DoubleVectorProperty CurrentPointP = new DoubleVectorProperty(this, "CurrentPoint", new double[2], 2);
    public DoubleVectorProperty DataAspectRatioP = new DoubleVectorProperty(this, "DataAspectRatio", new double[3], 3);
    public RadioProperty DataAspectRatioModeP = new RadioProperty(this, "DataAspectRatioMode", new String[] {"auto", "manual"}, "auto");
    public RadioProperty DrawModeP = new RadioProperty(this, "DrawMode", new String[] {"normal", "fast"}, "normal");
    public RadioProperty FontAngleP = new RadioProperty(this, "FontAngle", new String[] {"normal", "italic", "oblique"}, "normal");
    public StringProperty FontNameP = new StringProperty(this, "FontName", "");
    public IntegerProperty FontSizeP = new IntegerProperty(this, "FontSize", 12);
    public RadioProperty FontUnitsP = new RadioProperty(this, "FontUnits", new String[] {"points", "normalized", "inches", "centimeters", "pixels"}, "points");
    public RadioProperty FontWeightP = new RadioProperty(this, "FontWeight", new String[] {"normal", "bold", "light", "demi"}, "normal");
    public RadioProperty GridLineStyleP = new RadioProperty(this, "GridLineStyle", new String[] {"-", "- -", ":", "-.", "none"}, "-");
    public RadioProperty LayerP = new RadioProperty(this, "Layer", new String[] {"bottom", "top"}, "bottom");
    // LineStyleOrder  needs LineSpec-Property
    public DoubleProperty LineWidthP = new DoubleProperty(this, "LineWidth", 1.0);
    public RadioProperty MinorGridLineStyleP = new RadioProperty(this, "MinorGridLineStyle", new String[] {"-", "- -", ":", "-.", "none"}, "-");
    public DoubleVectorProperty OuterPositionP = new DoubleVectorProperty(this, "OuterPosition", new double[3], 3);
    public DoubleVectorProperty PlotBoxAspectRatioP = new DoubleVectorProperty(this, "PlotBoxAspectRatio", new double[3], 3);
    public RadioProperty PlotBoxAspectRatioModeP = new RadioProperty(this, "PlotBoxAspectRatioMode", new String[] {"auto", "manual"}, "auto");
    public RadioProperty ProjectionP = new RadioProperty(this, "Projection", new String[] {"orthographic", "perspective"}, "orthographic");
    public RadioProperty TickDirP = new RadioProperty(this, "TickDir", new String[] {"in", "out"}, "in");
    public RadioProperty TickDirModeP = new RadioProperty(this, "TickDirMode", new String[] {"auto", "manual"}, "auto");
    public DoubleVectorProperty TickLengthP = new DoubleVectorProperty(this, "TickLength", new double[2], 2);
    public DoubleVectorProperty TightInsetP = new DoubleVectorProperty(this, "TightInset", new double[4], 4);
    public StringProperty TitleP = new StringProperty(this, "Title", "");
    // UIContextMenuP
    // UserDataP       
    
    public RadioProperty XAxisLocationP = new RadioProperty(this, "XAxisLocation", new String[] {"top", "bottom"}, "bottom");
    public RadioProperty YAxisLocationP = new RadioProperty(this, "YAxisLocation", new String[] {"left", "right"}, "left");
    public RadioProperty ZAxisLocationP = new RadioProperty(this, "ZAxisLocation", new String[] {"left", "right"}, "left");

    public ColorProperty XColorP = new ColorProperty(this, "XColor", Color.black);
    public ColorProperty YColorP = new ColorProperty(this, "YColor", Color.black);
    public ColorProperty ZColorP = new ColorProperty(this, "ZColor", Color.black);

    public RadioProperty XDirP = new RadioProperty(this, "XDir", new String[] {"normal", "reverse"}, "normal");
    public RadioProperty YDirP = new RadioProperty(this, "YDir", new String[] {"normal", "reverse"}, "normal");
    public RadioProperty ZDirP = new RadioProperty(this, "ZDir", new String[] {"normal", "reverse"}, "normal");

    public BooleanProperty XGridP = new BooleanProperty(this, "XGrid", false);
    public BooleanProperty YGridP = new BooleanProperty(this, "YGrid", false);
    public BooleanProperty ZGridP = new BooleanProperty(this, "ZGrid", false);

    public TextObject xLabel = null;
    public TextObject yLabel = null;
    public TextObject zLabel = null;

    public DoubleVectorProperty XLimP = new DoubleVectorProperty(this, "XLim", new double[] {0.0, 1.0}, 2);
    public DoubleVectorProperty YLimP = new DoubleVectorProperty(this, "YLim", new double[] {0.0, 1.0}, 2);
    public DoubleVectorProperty ZLimP = new DoubleVectorProperty(this, "ZLim", new double[] {-0.5, 0.5}, 2);

    public RadioProperty XLimModeP = new RadioProperty(this, "XLimMode", new String[] {"auto", "manual"}, "auto");
    public RadioProperty YLimModeP = new RadioProperty(this, "YLimMode", new String[] {"auto", "manual"}, "auto");
    public RadioProperty ZLimModeP = new RadioProperty(this, "ZLimMode", new String[] {"auto", "manual"}, "auto");

    public BooleanProperty XMinorGridP = new BooleanProperty(this, "XMinorGrid", false);
    public BooleanProperty YMinorGridP = new BooleanProperty(this, "YMinorGrid", false);
    public BooleanProperty ZMinorGridP = new BooleanProperty(this, "ZMinorGrid", false);

    public BooleanProperty XMinorTickP = new BooleanProperty(this, "XMinorTick", false);
    public BooleanProperty YMinorTickP = new BooleanProperty(this, "YMinorTick", false);
    public BooleanProperty ZMinorTickP = new BooleanProperty(this, "ZMinorTick", false);

    public RadioProperty XScaleP = new RadioProperty(this, "XScale", new String[] {"linear", "log"}, "linear");
    public RadioProperty YScaleP = new RadioProperty(this, "YScale", new String[] {"linear", "log"}, "linear");
    public RadioProperty ZScaleP = new RadioProperty(this, "ZScale", new String[] {"linear", "log"}, "linear");

    public DoubleVectorProperty XTickP = new DoubleVectorProperty(this, "XTick", new double[0], -1);
    public DoubleVectorProperty YTickP = new DoubleVectorProperty(this, "YTick", new double[0], -1);
    public DoubleVectorProperty ZTickP = new DoubleVectorProperty(this, "ZTick", new double[0], -1);

    public StringArrayProperty XTickLabelP = new StringArrayProperty(this, "XTickLabel", new String[0]);
    public StringArrayProperty YTickLabelP = new StringArrayProperty(this, "YTickLabel", new String[0]);
    public StringArrayProperty ZTickLabelP = new StringArrayProperty(this, "ZTickLabel", new String[0]);
    
    public RadioProperty XTickModeP = new RadioProperty(this, "XTickMode", new String[] {"auto", "manual"}, "auto");
    public RadioProperty YTickModeP = new RadioProperty(this, "YTickMode", new String[] {"auto", "manual"}, "auto");
    public RadioProperty ZTickModeP = new RadioProperty(this, "ZTickMode", new String[] {"auto", "manual"}, "auto");

    public RadioProperty XTickLabelModeP = new RadioProperty(this, "XTickLabelMode", new String[] {"auto", "manual"}, "auto");
    public RadioProperty YTickLabelModeP = new RadioProperty(this, "YTickLabelMode", new String[] {"auto", "manual"}, "auto");
    public RadioProperty ZTickLabelModeP = new RadioProperty(this, "ZTickLabelMode", new String[] {"auto", "manual"}, "auto");

    public LineStyleProperty XGridStyleP = new LineStyleProperty(this, "XGridStyle", ":");
    public LineStyleProperty YGridStyleP = new LineStyleProperty(this, "YGridStyle", ":");
    public LineStyleProperty ZGridStyleP = new LineStyleProperty(this, "ZGridStyle", ":");


	public AxesObject()
   	{
	    // set type to "axes"
	    TypeP = new TypeProperty(this, "axes");
	    
		component = new AxesComponent(this);

		autoTick();

		ActivePositionPropertyP.addPropertyListener(this);
		ALimP.addPropertyListener(this);
		ALimModeP.addPropertyListener(this);
		AmbientLightColorP.addPropertyListener(this);
		BoxP.addPropertyListener(this);
		CameraPositionP.addPropertyListener(this);
		CameraPositionModeP.addPropertyListener(this);
		CameraTargetP.addPropertyListener(this);
		CameraTargetModeP.addPropertyListener(this);
		CameraTargetModeP.addPropertyListener(this);
		CameraUpVectorModeP.addPropertyListener(this);
		CameraViewAngleP.addPropertyListener(this);
		CameraViewAngleModeP.addPropertyListener(this);
		ChildrenP.addPropertyListener(this);
		CLimP.addPropertyListener(this);
		CLimModeP.addPropertyListener(this);
		ColorP.addPropertyListener(this);
		ColorOrderP.addPropertyListener(this);
		CurrentPointP.addPropertyListener(this);
		DataAspectRatioP.addPropertyListener(this);
		DataAspectRatioModeP.addPropertyListener(this);
		DrawModeP.addPropertyListener(this);
		FontAngleP.addPropertyListener(this);
		FontNameP.addPropertyListener(this);
		FontUnitsP.addPropertyListener(this);
        FontSizeP.addPropertyListener(this);
		FontWeightP.addPropertyListener(this);
		GridLineStyleP.addPropertyListener(this);
		LayerP.addPropertyListener(this);
		LineWidthP.addPropertyListener(this);
		MinorGridLineStyleP.addPropertyListener(this);
		OuterPositionP.addPropertyListener(this);
		PlotBoxAspectRatioP.addPropertyListener(this);
		PlotBoxAspectRatioModeP.addPropertyListener(this);
		ProjectionP.addPropertyListener(this);
		TickDirP.addPropertyListener(this);
		TickDirModeP.addPropertyListener(this);
		TickLengthP.addPropertyListener(this);
		TightInsetP.addPropertyListener(this);
		TitleP.addPropertyListener(this);
		XAxisLocationP.addPropertyListener(this);
		YAxisLocationP.addPropertyListener(this);
		ZAxisLocationP.addPropertyListener(this);
		XColorP.addPropertyListener(this);
		YColorP.addPropertyListener(this);
		ZColorP.addPropertyListener(this);
		XDirP.addPropertyListener(this);
		YDirP.addPropertyListener(this);
		ZDirP.addPropertyListener(this);
        XGridP.addPropertyListener(this);
        YGridP.addPropertyListener(this);
        ZGridP.addPropertyListener(this);
        //XLabel.addPropertyListener(this);
        //YLabel.addPropertyListener(this);
        //ZLabel.addPropertyListener(this);
		XLimP.addPropertyListener(this);
		YLimP.addPropertyListener(this);
		ZLimP.addPropertyListener(this);
		XLimModeP.addPropertyListener(this);
		YLimModeP.addPropertyListener(this);
		ZLimModeP.addPropertyListener(this);
		XMinorGridP.addPropertyListener(this);
		YMinorGridP.addPropertyListener(this);
		ZMinorGridP.addPropertyListener(this);
		XMinorTickP.addPropertyListener(this);
		YMinorTickP.addPropertyListener(this);
		ZMinorTickP.addPropertyListener(this);
		XScaleP.addPropertyListener(this);
		YScaleP.addPropertyListener(this);
		ZScaleP.addPropertyListener(this);
		XTickP.addPropertyListener(this);
		YTickP.addPropertyListener(this);
		ZTickP.addPropertyListener(this);
        XTickLabelP.addPropertyListener(this);
        YTickLabelP.addPropertyListener(this);
        ZTickLabelP.addPropertyListener(this);
		XTickModeP.addPropertyListener(this);
		YTickModeP.addPropertyListener(this);
		ZTickModeP.addPropertyListener(this);
		XTickLabelModeP.addPropertyListener(this);
		YTickLabelModeP.addPropertyListener(this);
		ZTickLabelModeP.addPropertyListener(this);
        XGridStyleP.addPropertyListener(this);
        YGridStyleP.addPropertyListener(this);
        ZGridStyleP.addPropertyListener(this);
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

			if (XScaleP.is("linear"))
			{
			    // linear axis
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
			}
			else
			{
			    // log axis
                if (xmax/xmin > 10)
                {
                    xmin = Math.floor(xmin);
                    xmax = Math.ceil(xmax);
                }
                else 
                {
                    // ????
                    xmin--;
                    xmax++;
                }
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

		//System.out.println("width = "+this.getSize().width+" height = "+this.getSize().height);
		dyFrame = this.getSize().height;
		dxFrame = this.getSize().width;

		//ErrorLogger.debugLine("AxesObject dxFrame="+dxFrame+" dyFframe="+dyFrame);
		//ErrorLogger.debugLine("AxesObject getX="+this.getX()+" getY="+this.getY());
		//ErrorLogger.debugLine("AxesObject getLocation.x="+this.getLocation().x);

		// size of curves
		int dyCurves = (int)(dyFrame*3/4);
		int dxCurves = (int)(dxFrame*3/4);

		// Origin of curves
		int dyOrig = (dyFrame-dyCurves)/2;
		int dxOrig = (dxFrame-dxCurves)/2;


		// fonts
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

		// add rectangle as background
		g.setColor( ((ColorProperty)getProperty("Color")).getColor() );
		g.fillRect(dxOrig, dyOrig, dxCurves, dyCurves);
		
		Stroke normS = g2d.getStroke();

		int yLabMinX = dxOrig;
		int xLabMinY = dyOrig+dxCurves;

		// X Grid
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
			if (XGridP.isSet() && !XGridStyleP.is("none"))
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
		double[] yticks      = YTickP.getArray();
		String[] yticklabels = YTickLabelP.getArray();
		Stroke   yS          = YGridStyleP.getStroke(1);
		g2d.setColor(YColorP.getColor());
		for (int i=0; i<yticks.length; i++)
		{
			int yt = dyFrame - dyOrig - (int)((yticks[i] - ymin) / (ymax - ymin) * dyCurves);

			if (yt < dyOrig || yt > (dyFrame-dyOrig))
				continue;

			// grid line
			if (YGridP.isSet() && !YGridStyleP.is("none"))
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




