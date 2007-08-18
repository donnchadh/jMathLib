package jmathlib.core.graphics;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.graphics.properties.*;;

/** created and holds the axes of a plot*/
public class Axes3DObject extends AxesObject implements MouseListener, MouseMotionListener
{  
	boolean painted = true;
	boolean boxVisible = false;


	double xfac;
	double prevx, prevy;
	double xtheta = 120;
    double ztheta = 60;
	double scalefudge = 1;
	Matrix3D rotMat = new Matrix3D();
	Matrix3D mat  = new Matrix3D();		//?

	String mdname = null;	/**??*/

	public Axes3DObject()
	{  
		super();

		rotMat = makeRotMatrix();

		component.addMouseListener(this);
		component.addMouseMotionListener(this);

		XGridP.set(true);
		YGridP.set(true);
		ZGridP.set(true);
	}

	private Matrix3D makeRotMatrix()
	{
		Matrix3D mat = new Matrix3D();
		mat.unit();
		mat.zrot(ztheta);
		mat.xrot(xtheta);
		return mat;
	}

	/** add a line to the current plot */
	public void add3DLine(double[] _x, double[] _y, double[] _z)
	{
		ErrorLogger.debugLine("Axes3DObject:  add3DLine");

		// if this plot is not on hold. Remove all Elements before plotting
		if (NextPlotP.is("replace"))
            ChildrenP.removeAllElements();

		// add line to plot
        ChildrenP.addElement(new Line3DObject(_x, _y, _z));

		autoScale();
	}

	/** add lines to the current plot */
	public void add3DLines(double[][] _x, double[][] _y, double[][] _z, String color, String lineStyle, String marker)
	{
		ErrorLogger.debugLine("Axes3DObject:  add3DLines");

		// if this plot is not on hold. Remove all Elements before plotting
		if (NextPlotP.is("replace"))
            ChildrenP.removeAllElements();

		// add lines to plot
		for (int n=0; n<_y.length; n++)
		{
            ChildrenP.addElement(new Line3DObject(_x[n], _y[n], _z[n], color, lineStyle, marker));
		}

		autoScale();
	}

	/** add a surface to the current axes */
	public void addSurface(double[][] _x, double[][] _y, double[][] _z)
	{
		//ErrorLogger.debugLine("Axes3DObject:  addSurface");

		// if this plot is not on hold. Remove all Elements before plotting
		if (NextPlotP.is("replace"))
            ChildrenP.removeAllElements();

		// add surface to plot
        ChildrenP.addElement(new SurfaceObject(_x, _y, _z, 'b', ' ', ' '));

		autoScale();
	}

	public void paint(Graphics _g) 
	{
		Graphics g = initBackBuffer(_g);

		//ErrorLogger.debugLine("Axes3DObject: paint");
		//System.out.println("width = "+this.getSize().width+" height = "+this.getSize().height);
		dyFrame = this.getSize().height;
		dxFrame = this.getSize().width;

		// if these axes hold no data -> do nothing
		if (ChildrenP.size() == 0) return;

		// size of curves
		int dyCurves = (int)(dyFrame*3/4);
		int dxCurves = (int)(dxFrame*3/4);

		// Origin of curves
		int dyOrig = (dyFrame-dyCurves)/2;
		int dxOrig = (dxFrame-dxCurves)/2;


		FontMetrics fM  = g.getFontMetrics();
		int    sAscent  = fM.getAscent();
		int    sDescent = fM.getDescent();


		// Find range of x-axis, y-axis and z-axis
		double xmin, xmax, ymin, ymax, zmin, zmax, dx, dy, dz;
		// X axis
		xmin = XLimP.getArray()[0];
		xmax = XLimP.getArray()[1];
		dx = xmax-xmin;
		// Y axis
		ymin = YLimP.getArray()[0];
		ymax = YLimP.getArray()[1];
		dy = ymax-ymin;
		// Z axis
		zmin = ZLimP.getArray()[0];
		zmax = ZLimP.getArray()[1];
		dz = zmax-zmin;

		g.setColor(Color.black);

		mat.unit();
		mat.translate(-0.5, -0.5, -0.5);
		mat.mult(rotMat);
		double f1 = Math.abs(Math.cos(ztheta*Math.PI/180.0f))+Math.abs(Math.sin(ztheta*Math.PI/180.f));
		double f2 = Math.abs(Math.sin(xtheta*Math.PI/180.0f))+Math.abs(Math.cos(xtheta*Math.PI/180.0f))*f1;
		mat.scale(dxCurves/f1, dyCurves/f2, dxCurves/f1);
		mat.translate(dxFrame / 2, dyFrame / 2, dxFrame / 2);


		// curve matrix
		Matrix3D curveMatrix = new Matrix3D();
		curveMatrix.unit();
		curveMatrix.translate(-xmin, -ymin, -zmin);
		curveMatrix.scale(1/dx, 1/dx, 1/dz);
		curveMatrix.mult(mat);

		double xPlane = (ztheta >= 0 && ztheta <= 180 ? 0 : 1);
		double yPlane = (ztheta >= 270 || ztheta <= 90 ? 0 : 1);
		double zPlane = (xtheta < 90 ? 1 : 0);

		g.setColor(Color.white);
		fill3DPlane(g, new double[] {0,1}, yPlane, new double[] {0,1});
		fill3DPlane(g, xPlane, new double[] {0,1}, new double[] {0,1});
		fill3DPlane(g, new double[] {0,1}, new double[] {0,1}, zPlane);

		Graphics2D g2d = (Graphics2D)g;
		Stroke normS = g2d.getStroke();

		int xlabelWidthMax = 0, ylabelWidthMax = 0, zlabelWidthMax = 0;

		// X Grid
		boolean doXGrid = XGridP.isSet() && !XGridStyleP.is("none");
		double[] xticks = XTickP.getArray();
		String[] xticklabels = XTickLabelP.getArray();
		Stroke xS = XGridStyleP.getStroke();
		g2d.setColor(XColorP.getColor());
		for (int i=0; i<xticks.length; i++)
		{
			double xf = (xticks[i] - xmin) / (xmax - xmin);

			// grid line
			if (doXGrid)
			{
				g2d.setStroke(xS);
				draw3DLine(g, xf, 0, zPlane, xf, 1, zPlane);
				draw3DLine(g, xf, yPlane, 0, xf, yPlane, 1);
				g2d.setStroke(normS);
			}

			// tick mark
			draw3DLine(g, xf,1-yPlane,zPlane, xf,1.03-1.06*yPlane,zPlane);

			// tick text
			if (i < xticklabels.length)
			{
				int    sXWidth = fM.stringWidth(xticklabels[i]);
				Point pt = mat.transform(xf, 1.03-1.06*yPlane, zPlane);
				g.drawString(xticklabels[i],
					(xPlane != yPlane ? pt.x+5 : pt.x-sXWidth-5),
					(zPlane > 0 ? dyFrame-pt.y : dyFrame-pt.y+sAscent));
				if (sXWidth > xlabelWidthMax)
					xlabelWidthMax = sXWidth;
			}
		}
		
		// Y Grid
		boolean doYGrid = YGridP.isSet() && !YGridStyleP.is("none");
		double[] yticks = YTickP.getArray();
		String[] yticklabels = YTickLabelP.getArray();
		Stroke yS = YGridStyleP.getStroke();
		g2d.setColor(YColorP.getColor());
		for (int i=0; i<yticks.length; i++)
		{
			double yf = (yticks[i] - ymin) / (ymax - ymin);

			// grid line
			if (doYGrid)
			{
				g2d.setStroke(yS);
				draw3DLine(g, 0, yf, zPlane, 1, yf, zPlane);
				draw3DLine(g, xPlane, yf, 0, xPlane, yf, 1);
				g2d.setStroke(normS);
			}

			// tick mark
			draw3DLine(g, 1-xPlane,yf,zPlane, 1.03-1.06*xPlane,yf,zPlane);

			// tick text
			if (i < yticklabels.length)
			{
				int    sYWidth = fM.stringWidth(yticklabels[i]);
				Point pt = mat.transform(1.03-1.06*xPlane, yf, zPlane);
				g.drawString(yticklabels[i],
					(xPlane == yPlane ? pt.x+5 : pt.x-sYWidth-5),
					(zPlane > 0 ? dyFrame-pt.y : dyFrame-pt.y+sAscent));
				if (sYWidth > ylabelWidthMax)
					ylabelWidthMax = sYWidth;
			}
		}

		// Z Grid
		boolean doZGrid = ZGridP.isSet() && !ZGridStyleP.is("none");
		double[] zticks = ZTickP.getArray();
		String[] zticklabels = ZTickLabelP.getArray();
		Stroke zS = ZGridStyleP.getStroke();
		g2d.setColor(ZColorP.getColor());
		for (int i=0; i<zticks.length; i++)
		{
			double zf = (zticks[i] - zmin) / (zmax - zmin);

			// grid line
			if (doZGrid)
			{
				g2d.setStroke(yS);
				draw3DLine(g, xPlane, 0, zf, xPlane, 1, zf);
				draw3DLine(g, 0, yPlane, zf, 1, yPlane, zf);
				g2d.setStroke(normS);
			}

			// tick mark
			draw3DLine(g, yPlane,1-xPlane,zf, 1.06*yPlane-0.03,1-xPlane,zf);

			// tick text
			if (i < zticklabels.length)
			{
				int    sZWidth = fM.stringWidth(zticklabels[i]);
				Point pt = mat.transform(1.06*yPlane-0.03, 1-xPlane, zf);
				g.drawString(zticklabels[i], pt.x-sZWidth-5,
					(zPlane > 0 ? dyFrame-pt.y+sAscent : dyFrame-pt.y));
				if (sZWidth > zlabelWidthMax)
					zlabelWidthMax = sZWidth;
			}
		}

		// axis
		g.setColor(XColorP.getColor());
		draw3DLine(g, 0,1-yPlane,zPlane, 1,1-yPlane,zPlane);
		g.setColor(YColorP.getColor());
		draw3DLine(g, 1-xPlane,0,zPlane, 1-xPlane,1,zPlane);
		g.setColor(ZColorP.getColor());
		draw3DLine(g, yPlane,1-xPlane,0, yPlane,1-xPlane,1);

		// plot line objects
		for(int n = 0; n < ChildrenP.size(); n++)
		{
			//((GraphicalObject)axesElements.elementAt(n)).setPlotArea(dxOrig,dyFrame-dyOrig,dxCurves,dyCurves);
			((GraphicalObject)ChildrenP.elementAt(n)).setPlotArea(0,dyFrame,dxCurves,dyCurves);
			((GraphicalObject)ChildrenP.elementAt(n)).mat = curveMatrix;
			((GraphicalObject)ChildrenP.elementAt(n)).paint(g);
		}

		g.setColor(Color.black);

		// title
		if (title != null)
		{
			title.setPlotArea(dxOrig+dxCurves/2, dyOrig-5, 0, 0);
			title.paint(g);
		}

		// X label
		g.setColor(XColorP.getColor());
		if (xLabel != null)
		{
			Point pt = mat.transform(0.5, 1.03-1.06*yPlane, zPlane);
			xLabel.setRotation(0);
			xLabel.setAlign(
				(xPlane != yPlane ? TextObject.H_LEFT : TextObject.H_RIGHT),
				(zPlane > 0 ? TextObject.V_BOTTOM : TextObject.V_TOP));
			xLabel.setPlotArea(
				(xPlane != yPlane ? pt.x+xlabelWidthMax+5 : pt.x-xlabelWidthMax-5),
				(zPlane > 0 ? dyFrame-pt.y-sAscent : dyFrame-pt.y+sAscent),
				0, 0);
			xLabel.paint(g);
		}

		// Y label
		g.setColor(YColorP.getColor());
		if (yLabel != null)
		{
			Point pt = mat.transform(1.03-1.06*xPlane, 0.5, zPlane);
			yLabel.setRotation(0);
			yLabel.setAlign(
				(xPlane == yPlane ? TextObject.H_LEFT : TextObject.H_RIGHT),
				(zPlane > 0 ? TextObject.V_BOTTOM : TextObject.V_TOP));
			yLabel.setPlotArea(
				(xPlane == yPlane ? pt.x+ylabelWidthMax+5 : pt.x-ylabelWidthMax-5),
				(zPlane > 0 ? dyFrame-pt.y-sAscent : dyFrame-pt.y+sAscent),
				0, 0);
			yLabel.paint(g);
		}

		// Z label
		g.setColor(ZColorP.getColor());
		if (zLabel != null)
		{
			Point pt = mat.transform(1.06*yPlane-0.03, 1-xPlane, 0.5);
			zLabel.setRotation(-90);
			zLabel.setPlotArea(pt.x-zlabelWidthMax-10, dyFrame-pt.y, 0, 0);
			zLabel.paint(g);
		}

		flushBackBuffer(_g, g);
		setPainted(); //?

	}

	public void draw3DLine(Graphics g, double x0, double y0, double z0,
			double x1, double y1, double z1)
	{
		double[] x= {x0, x1};
		double[] y= {y0, y1};
		double[] z= {z0, z1};

		int[] tx = new int[2];
		int[] ty = new int[2];
		int[] tz = new int[2];

		mat.transform(x, y, z, tx, ty, tz);

		g.drawLine(tx[0], dyFrame - ty[0], tx[1], dyFrame - ty[1]);


	}

	private void fill3DPlane(Graphics g, double[] x, double y, double[] z)
	{
		fill3DPlane(
			g,
			new double[] {x[0], x[1], x[1], x[0]},
			new double[] {y, y, y, y},
			new double[] {z[0], z[0], z[1], z[1]}
		);
	}

	private void fill3DPlane(Graphics g, double x, double[] y, double[] z)
	{
		fill3DPlane(
			g,
			new double[] {x, x, x, x},
			new double[] {y[0], y[1], y[1], y[0]},
			new double[] {z[0], z[0], z[1], z[1]}
		);
	}

	private void fill3DPlane(Graphics g, double x[], double[] y, double z)
	{
		fill3DPlane(
			g,
			new double[] {x[0], x[0], x[1], x[1]},
			new double[] {y[0], y[1], y[1], y[0]},
			new double[] {z, z, z, z}
		);
	}

	private void fill3DPlane(Graphics g, double[] x, double[] y, double[] z)
	{
		int[] ix = new int[x.length];
		int[] iy = new int[y.length];
		int[] iz = new int[z.length];

		mat.transform(x, y, z, ix, iy, iz);
		for (int i=0; i<iy.length; i++)
			iy[i] = dyFrame - iy[i];

		g.fillPolygon(ix, iy, ix.length);
	}

	public void mousePressed(MouseEvent e) 
	{
		prevx = e.getX();
		prevy = e.getY();
		e.consume();
		boxVisible = true;
	}
	public void mouseClicked(MouseEvent e) { }
	public void mouseReleased(MouseEvent e)
	{
		boxVisible = false;
		if (painted) {
			painted = false;
			repaint();
		}
	}
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e)  { }
	public void mouseMoved(MouseEvent e)   { }
	public void mouseDragged(MouseEvent e) 
	{
		int x = e.getX();
		int y = e.getY();

		ztheta -= (x - prevx) * (360.0 / getSize().width);
		xtheta -= (prevy - y) * (360.0 / getSize().height);

		if (xtheta < 0) xtheta = 0;
		else if (xtheta > 180) xtheta = 180;

		if (ztheta > 360) ztheta -= 360;
		else if (ztheta < 0) ztheta += 360;

		// compute roational matrix and change axes matrix
		rotMat = makeRotMatrix();

		if (painted) {
			painted = false;
			repaint();
		}

		prevx = x;
		prevy = y;
		e.consume();
	}

	private synchronized void setPainted() {
		painted = true;
		notifyAll();
	}

	// private synchronized void waitPainted()
	// {
	//    while (!painted)
	//    {
	//       try {
	//          wait();
	//       }
	//       catch (InterruptedException e) {}
	//    }
	//    painted = false;
	// }

	public void rotate(double phiX, double phiY, double phiZ) 
	{
		// compute roational matrix and change axes matrix
		Matrix3D mat = new Matrix3D();
		mat.unit();
		mat.xrot(phiX);
		mat.yrot(phiY);
		mat.zrot(phiZ);
		rotMat.mult(mat);

		if (painted) {
			painted = false;
			repaint();
		}

	}

}




