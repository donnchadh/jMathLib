package jmathlib.core.graphics.axes.coreObjects;

import jmathlib.core.graphics.*;
import jmathlib.core.graphics.properties.*;
import jmathlib.core.interpreter.ErrorLogger;

import java.awt.*;

/** implementation of a line object*/
public class LineObject extends GraphicalObject implements PropertyListener
{
	public static final int LINE_MODE  = 0;
	public static final int STEM_MODE  = 1;
	public static final int STAIR_MODE = 2;
    public static final int AREA_MODE  = 3;
    
	// properties 
    public ColorProperty ColorP = new ColorProperty(this, "Color", Color.red);
    // protected EraseModeP
    // protected LineStyleP
    // protected LineWidthP
    public MarkerProperty MarkerP = new MarkerProperty(this, "Marker", "none");
    // protected MarkerEdgeColorP
    // protected MarkerFaceColorP
    public DoubleProperty MarkerSizeP = new DoubleProperty(this, "MarkerSize", 9.0);
    // protected UIContextMenuP
    public DoubleArrayProperty XDataP = new DoubleArrayProperty(this, "XData", new double[0], -1);
    public DoubleArrayProperty YDataP = new DoubleArrayProperty(this, "YData", new double[0], -1);
    public DoubleArrayProperty ZDataP = new DoubleArrayProperty(this, "ZData", new double[0], -1);;

    // protected XDataModeP
    // protected XDataSourceP
	// protected YDataSourceP
    // protected ZDataSourceP

    public LineStyleProperty LineStyleP = new LineStyleProperty(this, "Style", "-");
    public DoubleProperty    LineWidthP = new DoubleProperty(this, "Width", 0.0);

    public RadioProperty TypeP = new RadioProperty(this, "Type", new String[] {"Line", "Stem","Stair","Area"},"Line");
   
	/* line mode */
	protected int mode = AREA_MODE; //LINE_MODE;
    
	public LineObject()
	{
	    ColorP.addPropertyListener(this);
		XDataP.addPropertyListener(this);
		YDataP.addPropertyListener(this);
        ZDataP.addPropertyListener(this);
        LineStyleP.addPropertyListener(this);
        LineWidthP.addPropertyListener(this);
        MarkerP.addPropertyListener(this);
        MarkerSizeP.addPropertyListener(this);
        TypeP.addPropertyListener(this);
	}

	public LineObject(double[] _x, double[] _y)
	{
		this(_x, _y, "r" , "-" , "none" );
	}

	/** Constructor for a line */
	public LineObject(double[] _x, double[] _y, String color, String lineStyle, String marker)
   	{
		this();

		XDataP.update(_x);
		YDataP.update(_y);
		double[] x = _x;
		double[] y = _y;

		// Find range of x-axis and y-axis
		findMinMax();

		ColorP.update(color);
		LineStyleP.update(lineStyle);
		MarkerP.update(marker);
   	}

	private void findMinMax()
	{
		double[] x = XDataP.getArray();
		double[] y = YDataP.getArray();

		// Find range of x-axis and y-axis
		xmin = x[0];
		xmax = x[0];
		ymin = y[0];
		ymax = y[0];
		zmin = -0.5;
		zmax = 0.5;
		for (int i=1; i<x.length; i++)
		{
			if (x[i] < xmin) 	xmin = x[i];
			if (x[i] > xmax) 	xmax = x[i];
			if (y[i] < ymin) 	ymin = y[i];
			if (y[i] > ymax) 	ymax = y[i];
		}		
	}

	public void paint(Graphics g) 
	{
		double[] x = XDataP.getArray();
		double[] y = YDataP.getArray();
	
		if (x == null) return;
		if (x.length == 0) return;

		double dx = ax_xmax-ax_xmin;
		double dy = ax_ymax-ax_ymin;

		int[] ix = new int[x.length];
		int[] iy = new int[x.length];
		for (int i=0; i<x.length; i++)
		{
            ErrorLogger.debugLine("x="+x[i]+" y="+y[i]);
			ix[i] = xOrig + (int)((x[i]  - ax_xmin) / dx * width);
			iy[i] = yOrig - (int)((y[i]  - ax_ymin) / dy * height);
        }

		Graphics2D g2d = (Graphics2D)g;
		Stroke normS = g2d.getStroke();
		Stroke lineS = LineStyleP.getStroke(LineWidthP.floatValue());
		Stroke markerS = new BasicStroke(LineWidthP.floatValue());

        // get mode
        String m = TypeP.getValue();
        if (m.equals("Line"))
            mode = LINE_MODE;
        else if (m.equals("Stem"))
            mode = STEM_MODE;
        else if (m.equals("Stair"))
            mode = STAIR_MODE;
        else 
            mode = AREA_MODE;
        
		// draw line
		g.setColor(ColorP.getColor());
        
		switch (mode)
		{
		case LINE_MODE:
			if (lineS != null)
			{
				g2d.setStroke(lineS);
				g.drawPolyline(ix, iy, x.length);
			}
			break;
            
		case STAIR_MODE:
			if (lineS != null)
			{
				g2d.setStroke(lineS);
				for (int i=1; i<x.length; i++)
				{
					g.drawLine(ix[i-1], iy[i-1], ix[i], iy[i-1]);
					g.drawLine(ix[i], iy[i-1], ix[i], iy[i]);
				}
			}
			break;
            
		case STEM_MODE:
			int y0 = yOrig - (int)((0.0 - ax_ymin) / dy * height);
			if (lineS != null)
			{
				g2d.setStroke(lineS);
				for (int i=0; i<x.length; i++)
					g.drawLine(ix[i], y0, ix[i], iy[i]);
			}
			if (ax_ymax >= 0 && ax_ymin <= 0)
			{
				g2d.setStroke(normS);
				g2d.setColor(Color.black);
				g.drawLine(xOrig, y0, xOrig+width, y0);
				g2d.setColor(ColorP.getColor());
			}
			break;
             
        case AREA_MODE:
            int[] iix = new int[1+ix.length+2];
            int[] iiy = new int[1+ix.length+2];

            iix[0] = ix[0];
            iiy[0] = 0;
            
            iix[iix.length-2] = ix[ix.length-1];
            iiy[iix.length-2] = 0;

            iix[iix.length-2] = ix[0];
            iiy[iix.length-2] = 0;

            for (int i=0; i<ix.length; i++)
            {
                iix[i+1]= ix[i];
                iiy[i+1]= iy[i];
            }

            g2d.setStroke(lineS);
            g.fillPolygon(iix, iiy, iix.length);

            
            break;
		}
        
		// draw markers
		if (MarkerP.isSet())
		{
			g2d.setStroke(markerS);
			for (int i=0; i<x.length; i++)
				MarkerP.drawMarker(g, ix[i], iy[i], MarkerSizeP.doubleValue());
		}

		g2d.setStroke(normS);
	} // end paint()

	public void propertyChanged(Property p)
	{
        ErrorLogger.debugLine("LineObject property changed: "+ p.getName());

        if (p == XDataP || p == YDataP)
			findMinMax();
        
        parent.repaint();
	}
    
}

