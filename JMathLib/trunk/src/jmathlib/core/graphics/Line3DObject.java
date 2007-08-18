package jmathlib.core.graphics;

import java.awt.*;
//import MathLib.Interpreter.ErrorLogger;
import jmathlib.core.graphics.properties.*;

/** implementation of a line object*/
public class Line3DObject extends LineObject
{  


 	public Line3DObject()
	{
		super();
	}
 
	public Line3DObject(double[] _x, double[] _y, double[] _z)
	{
		this(_x, _y, _z, "b" , "-" , "none" );
	}

	/** Constructor for a line */
	public Line3DObject(double[] _x, double[] _y, double[] _z, String color, String lineStyle, String marker)
   	{  
		this();

		XDataP.setArray(_x);
		YDataP.setArray(_y);
		ZDataP.setArray(_z);
		
		double[] x = _x;
		double[] y = _y;
		double[] z = _z;

		mat.xrot(20); //?
		mat.yrot(30); //?

		// Find range of x-axis and y-axis
		xmin = x[0]; 	xmax = x[0];
		ymin = y[0];	ymax = y[0];
		zmin = z[0];	zmax = z[0];
		for (int i=0; i<x.length; i++)
		{
			if (x[i] < xmin) 	xmin = x[i];
			if (x[i] > xmax) 	xmax = x[i];
			if (y[i] < ymin) 	ymin = y[i];
			if (y[i] > ymax) 	ymax = y[i];
			if (z[i] < zmin) 	zmin = z[i];
			if (z[i] > zmax) 	zmax = z[i];
		}		
		
        //ErrorLogger.debugLine("Line3DObject: xmin=" + xmin + " xmax="+xmax);
        //ErrorLogger.debugLine("Line3DObject: ymin=" + ymin + " ymax="+ymax);
        //ErrorLogger.debugLine("Line3DObject: zmin=" + zmin + " zmax="+zmax);
       
	    ColorP.setColor(color);
		LineStyleP.setStyle(lineStyle);
		MarkerP.setMarker(marker);
   	}

  
	public void paint(Graphics g) 
	{
		//ErrorLogger.debugLine("Line3DObject: paint");

		double[] x = XDataP.getArray();
		double[] y = YDataP.getArray();
		double[] z = ZDataP.getArray();
        
		if (x == null) return;
		if (x.length == 0) return;

		//Graphics2D g2 = (Graphics2D) g;

		double dx = xmax-xmin;
		double dy = ymax-ymin;

		//ErrorLogger.debugLine("Line3DObject: paint: transforming");

		// transformed data
		int[] tx = new int[x.length];
		int[] ty = new int[x.length];
		int[] tz = new int[x.length];
       

		mat.transform(x, y, z, tx, ty, tz);
		//transformed = true;

		//ErrorLogger.debugLine("Line3DObject: paint: transformed");

		// begin of a new line segment is end of the last line segment
		int xLast = tx[0];
		int yLast = ty[0];	

		g.setColor(ColorP.getColor());

		// draw line segments
		int x0,x1,y0,y1;
		for (int i=0; i<tx.length; i++)
		{
			//ErrorLogger.debugLine("Line3DObject: paint: "+i);
			g.drawLine(xLast, yOrig - yLast, tx[i], yOrig - ty[i]);
			xLast = tx[i];
			yLast = ty[i];
		}

		//ErrorLogger.debugLine("Line3DObject: paint: done");

	} // end paint()

}

