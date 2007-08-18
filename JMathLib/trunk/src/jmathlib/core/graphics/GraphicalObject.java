package jmathlib.core.graphics;

import java.awt.*;
import jmathlib.core.graphics.properties.*;

/** implementation of a line object*/
public abstract class GraphicalObject extends HandleObject
{  

	/** common properties to all objects*/
    // private BeingDeletedP
    // private BusyActionP
    // private ButtonDownFcnP
    // private ChildrenP
    protected BooleanProperty ClippingP = new BooleanProperty(this, "Clipping", true);
    // private CreateFcnP
    // private DeleteFcnP
    protected BooleanProperty HandleVisibilityP = new BooleanProperty(this, "HandleVisibility", true);
    protected BooleanProperty HitTestP          = new BooleanProperty(this, "HitTest", true);
    protected BooleanProperty InterruptibleP    = new BooleanProperty(this, "Interruptible", true);
    // private ParentP
    // private SelectedP
    protected BooleanProperty SelectionHighlightP = new BooleanProperty(this, "SelectionHighlight", true);
    // private TagP
    //protected TypeP
    // private UserDataP
    protected BooleanProperty VisibileP = new BooleanProperty(this, "Visible", true);
	

    /** parent axes */
	//HandleObjectListProperty Parent = new HandleObjectListProperty(this, "Parent",     1);
    GraphicalObject parent = null;
    
	/** Origin of curves */
	int xOrig;
	int yOrig;
	int zOrig;

	/* Size of area to plot */
	int width;
	int height;

	/* boundary values */
	double xmin;
	double xmax;
	double ymin;
	double ymax;
	double zmin;
	double zmax;

	/* axes boundaries */
	double ax_xmin;
	double ax_xmax;
	double ax_ymin;
	double ax_ymax;
	double ax_zmin;
	double ax_zmax;

	public Matrix3D mat  = new Matrix3D();		//?

	public GraphicalObject()
	{

	}

	public double getXMin()
	{
		return xmin;
	}

	public double getXMax()
	{
		return xmax;
	}

	public double getYMin()
	{
		return ymin;
	}

	public double getYMax()
	{
		return ymax;
	}

	public double getZMin()
	{
		return zmin;
	}

	public double getZMax()
	{
		return zmax;
	}

	/** Sets the area on the screen in which the line must be plotted */
	public void setPlotArea(int _xOrig, int _yOrig, int _width, int _height)
	{
		xOrig  = _xOrig;
		yOrig  = _yOrig;
		width  = _width;
		height = _height;
	}

	/** Sets the boundaries for the physical values of the line, this is used
	  for graphs with multiple line */ 
	public void setAxesBoundaries(double _xmin, double _xmax, double _ymin, double _ymax, double _zmin, double _zmax)
	{
		ax_xmin = _xmin;
		ax_xmax = _xmax;
		ax_ymin = _ymin;
		ax_ymax = _ymax;
		ax_zmin = _zmin;
		ax_zmax = _zmax;
	}
	
	public void setAxesBoundaries(double _xmin, double _xmax, double _ymin, double _ymax)
	{
		setAxesBoundaries(_xmin, _xmax, _ymin, _ymax, -0.5, 0.5);
	}

	public void paint(Graphics g)
	{
	    jmathlib.core.interpreter.Errors.throwMathLibException("GraphicalObject: paint");
	}

    public void repaint()
    {
        jmathlib.core.interpreter.Errors.throwMathLibException("GraphicalObject: repaint");
    }

    // register a parent, so that the children can call methods of their parent
	public void setParent(GraphicalObject parent)
	{
		//Parent.addElement(parent);
	    this.parent = parent;
    }
}

