package jmathlib.core.graphics.axes.coreObjects;

import java.awt.*;

import jmathlib.core.graphics.axes.*;
import jmathlib.core.graphics.properties.ColorProperty;
import jmathlib.core.graphics.properties.DoubleProperty;
import jmathlib.core.graphics.properties.LineStyleProperty;
import jmathlib.core.graphics.properties.Property;
import jmathlib.core.interpreter.ErrorLogger;

/** implementation of a surface object*/
public class SurfaceObject extends LineObject 
{  
  
	/** x values */
    double[][] x;
    
	/** y values */
    double[][] y;
    
	/** surface values */
    double[][] z;

    protected ColorProperty     LineColorP = new ColorProperty(this, "Color", Color.red);
    protected LineStyleProperty LineStyleP = new LineStyleProperty(this, "Style", "-");
    protected DoubleProperty    LineWidthP = new DoubleProperty(this, "Width", 0.0);

   
	public SurfaceObject(double[][] _x, double[][] _y, double[][] _z)
	{
		this(_x, _y, _z, ' ' , ' ' , ' ' );
	}

	/** Constructor for a line */
	public SurfaceObject(double[][] _x, double[][] _y, double[][] _z, char _colorC, char _lineStyleC, char _markerC)
   	{  
    	super();
		x = _x;
		y = _y;
        z = _z;

		// Find the range of x-axis and y-axis and z surface values
		xmin = x[0][0]; 	xmax = x[0][0];
		ymin = y[0][0];		ymax = y[0][0];
		zmin = z[0][0];		zmax = z[0][0];
		for (int i=0; i<x.length; i++)
		{
        	for (int j=0; j<x[0].length; j++)
            {
				if (x[i][j] < xmin) 	xmin = x[i][j];
				if (x[i][j] > xmax) 	xmax = x[i][j];
				if (y[i][j] < ymin) 	ymin = y[i][j];
				if (y[i][j] > ymax) 	ymax = y[i][j];
				if (z[i][j] < zmin) 	zmin = z[i][j];
				if (z[i][j] > zmax) 	zmax = z[i][j];
            }
		}		
		
        ErrorLogger.debugLine("SurfaceObject: xmin=" + xmin + " xmax="+xmax);
        ErrorLogger.debugLine("SurfaceObject: ymin=" + ymin + " ymax="+ymax);
        ErrorLogger.debugLine("SurfaceObject: zmin=" + zmin + " zmax="+zmax);
       
	    ColorP.setColor(_colorC);
   	}


	public void paint(Graphics g) 
	{
		ErrorLogger.debugLine("SurfaceObject: paint");

		if (x == null) return;
		if (x.length == 0) return;

		//Graphics2D g2 = (Graphics2D) g;

		double dx = xmax-xmin;
		double dy = ymax-ymin;

		ErrorLogger.debugLine("SurfaceObject: paint: transforming");

		// transformed data
		int[][] tx = new int[x.length][x[0].length];
		int[][] ty = new int[x.length][x[0].length];
		int[][] tz = new int[x.length][x[0].length];


		mat.transform(x, y, z, tx, ty, tz);
		//transformed = true;

		ErrorLogger.debugLine("SurfaceObject: paint: transformed");

		g.setColor(ColorP.getColor());

		// draw surface segments
		for (int yi=0; yi<tx.length-1; yi++)
		{
			for (int xi=0; xi<tx[0].length-1; xi++)
			{
				int[] xPoints = {tx[yi][xi], 	         tx[yi][xi+1], 
				                 tx[yi+1][xi+1],         tx[yi+1][xi]};
				int[] yPoints = {yOrig - ty[yi][xi],     yOrig - ty[yi][xi+1],
				                 yOrig - ty[yi+1][xi+1], yOrig - ty[yi+1][xi]   };

				
				g.drawPolygon(xPoints, yPoints, 4);

			}
		}

		ErrorLogger.debugLine("SurfaceObject: paint: done");

	} // end paint()

    public void propertyChanged(Property p)
    {
        ErrorLogger.debugLine("SurfObject property changed: "+ p.getName());

        //parent.repaint();
    }

}









