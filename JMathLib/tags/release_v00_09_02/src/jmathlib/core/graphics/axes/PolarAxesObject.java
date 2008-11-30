package jmathlib.core.graphics.axes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.util.Vector;

import jmathlib.core.graphics.*;
import jmathlib.core.graphics.GraphicalObject;
import jmathlib.core.graphics.properties.ColorProperty;
import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.graphics.axes.coreObjects.*;

/** created and holds the axes of a plot*/
public class PolarAxesObject extends AxesObject //implements WindowListener
{  

	/**A list of all object which belong to this axis*/	
	Vector axesElements = new Vector();

	public PolarAxesObject()
   	{   
        super();
        ErrorLogger.debugLine("PolarAxesObject: constructor");
        XGridP.set(true);
        YGridP.set(true);
   	}

    public void paint(Graphics _g) 
    {
        ErrorLogger.debugLine("PolarAxesObject: paint");
        
        Graphics g = initBackBuffer(_g);

        Graphics2D g2d = (Graphics2D)g;

        //System.out.println("width = "+this.getSize().width+" height = "+this.getSize().height);
        dyFrame = this.getSize().height;
        dxFrame = this.getSize().width;

        //ErrorLogger.debugLine("AxesObject dxFrame="+dxFrame+" dyFframe="+dyFrame);
        //ErrorLogger.debugLine("AxesObject getX="+this.getX()+" getY="+this.getY());
        //ErrorLogger.debugLine("AxesObject getLocation.x="+this.getLocation().x);

        // if these axes hold no data -> do nothing
        //if (axesElements.size() == 0) return;

        // size of curves
        int dyCurves = (int)(dyFrame*3/4)/2;
        int dxCurves = (int)(dxFrame*3/4)/2;

        // Origin of curves
        int dyOrig = dyFrame / 2;
        int dxOrig = dxFrame / 2;

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
        //ErrorLogger.debugLine("xmin="+xmin+" xmax="+xmax+" ymin="+ymin+" ymax="+ymax);

        // add white circle (oval) as background
        g.setColor( ((ColorProperty)getProperty("Color")).getColor() );
        g.fillOval(dxOrig-dxCurves, dyOrig-dyCurves, dxCurves*2, dyCurves*2);
        
        Stroke normS = g2d.getStroke();

        int yLabMinX = dxOrig;
        int xLabMinY = dyOrig+dxCurves;

        // X Grid (angle theta in radians)
        boolean  doXGrid     = XGridP.isSet() && !XGridStyleP.is("none");
        double[] xticks      = XTickP.getArray();
        String[] xticklabels = XTickLabelP.getArray();
        Stroke   xS          = XGridStyleP.getStroke(1);
        g2d.setColor(XColorP.getColor());

        for (int i=0; i<xticks.length-1; i++)
        {
            //ErrorLogger.debugLine("doXGrid xtick="+xticks[i]);
            int xt = (int)( Math.cos(xticks[i]) * dxCurves);
            int yt = (int)( Math.sin(xticks[i]) * dyCurves);

            // grid line
            if (doXGrid)
            {
                g2d.setStroke(xS);
                g.drawLine(dxOrig, dyOrig, dxOrig+xt, dyOrig-yt);
                g2d.setStroke(normS);
            }

            // tick text
            if (i < xticklabels.length)
            {
                int    sXWidth = fM.stringWidth(xticklabels[i]);
                g.drawString( xticklabels[i], 
                              dxOrig+ (int)(xt*1.1) -sXWidth/2, 
                              dyOrig- (int)(yt*1.1) -7 +sAscent);
            }

        }
        
        // Y Grid (radius rho) 
        boolean doYGrid = YGridP.isSet() && !YGridStyleP.is("none");
        double[] yticks = YTickP.getArray();
        String[] yticklabels = YTickLabelP.getArray();
        Stroke yS = YGridStyleP.getStroke(1);
        g2d.setColor(YColorP.getColor());
        for (int i=0; i<yticks.length; i++)
        {
            //ErrorLogger.debugLine("doYGrid ytick="+yticks[i]);
            int xt = (int)((yticks[i] - ymin) / (ymax - ymin) * dxCurves);
            int yt = (int)((yticks[i] - ymin) / (ymax - ymin) * dyCurves);

            // grid line
            if (doYGrid)
            {
                g2d.setStroke(yS);
                //g.drawLine(dxOrig, yt, dxOrig+dxCurves, yt);
                g.drawOval(dxOrig-xt, dyOrig-yt, xt*2, yt*2);
                g2d.setStroke(normS);
            }

            // tick text
            if (i < yticklabels.length-1)
            {
                int    sYWidth = fM.stringWidth(yticklabels[i]);
                g.drawString( yticklabels[i],
                              dxOrig + 5, // - sYWidth, 
                              (int)(dyOrig-yt-(sAscent/2)) );
            }
        }

        // draw borders
        if (BoxP.isSet())
        {
            g.setColor(XColorP.getColor());
            g.drawOval(dxOrig-dxCurves, dyOrig-dyCurves, dxCurves*2, dyCurves*2);
            g.setColor(YColorP.getColor());
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
        g.clipRect(dxOrig-dxCurves, dyOrig-dyCurves, dxCurves*2, dyCurves*2);
        for(int n = 0; n < ChildrenP.size(); n++)
        {
            ((LineObject)ChildrenP.elementAt(n)).setAxesBoundaries(xmin, xmax, ymin, ymax);
            ((LineObject)ChildrenP.elementAt(n)).setPlotArea(dxOrig,dyFrame-dyOrig,dxCurves,dyCurves);
            //((LineObject)ChildrenP.elementAt(n)).setPlotArea(dxOrig-dxCurves, dyOrig-dyCurves, dxCurves*2, dyCurves*2);
            //((LineObject)ChildrenP.elementAt(n)).paint(g);

            LineObject line = ((LineObject)ChildrenP.elementAt(n));
        
            paintLine(g, line);
        }
        g.setClip(clip);

        flushBackBuffer(_g, g);

    }
    
    public void paintLine(Graphics g, LineObject line) 
    {
        ErrorLogger.debugLine("PolarAxesObject: paintLine");
        
        double[] x = line.XDataP.getArray();
        double[] y = line.YDataP.getArray();
    
        if (x == null) return;
        if (x.length == 0) return;

        double dx = line.ax_xmax-line.ax_xmin;
        double dy = line.ax_ymax-line.ax_ymin;

        int[] ix = new int[x.length];
        int[] iy = new int[x.length];
        for (int i=0; i<x.length; i++)
        {
            ErrorLogger.debugLine("x="+x[i]+" y="+y[i]);
            // convert x = (angle theta) into cartesian ix,iy coordinates
            double xp = Math.cos(x[i]);
            double yp = Math.sin(x[i]);
            ix[i] = line.xOrig + (int)((y[i] * xp ) / dy * line.width);
            iy[i] = line.yOrig - (int)((y[i] * yp ) / dy * line.height);
            
        }

        Graphics2D g2d = (Graphics2D)g;
        Stroke normS = g2d.getStroke();
        Stroke lineS = line.LineStyleP.getStroke(line.LineWidthP.floatValue());
        Stroke markerS = new BasicStroke(line.LineWidthP.floatValue());

        // draw line
        g.setColor(line.ColorP.getColor());
        
        if (lineS != null)
        {
            g2d.setStroke(lineS);
            for (int i=1; i<x.length; i++)
            {
                g.drawLine(ix[i], iy[i], ix[i-1], iy[i-1]);
            }

        }

        // draw markers
        if (line.MarkerP.isSet())
        {
            g2d.setStroke(markerS);
            for (int i=0; i<x.length; i++)
                line.MarkerP.drawMarker(g, ix[i], iy[i], line.MarkerSizeP.doubleValue());
        }

        g2d.setStroke(normS);

    } // end paintLine()


    protected void autoScaleX()
    {
        double xmin, xmax;
        if (XLimModeP.is("auto") && ChildrenP.size() > 0)
        {
            xmin = 0;          // in degrees   0[deg]
            xmax = 2*Math.PI;  // in degrees 360[deg]
        
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
                ymin = Math.floor(ymin);
                ymax = Math.ceil(ymax);
            }
            else if (dy == 0)
            {
                ymin--;
                ymax++;
            }

            // for polar plot axis are [0...max]
            ymax = Math.max( Math.abs(ymin), Math.abs(ymax));
            ymin = 0;
            
            YLimP.update(new double[] {ymin, ymax});
            autoTickY();
        }
    }

    protected void autoTickX()
    {
        if (XTickModeP.is("auto"))
        {
            double xmin = XLimP.getArray()[0];
            double xmax = XLimP.getArray()[1];
            double[] ticks = new double[13];
            for (int i=0; i<ticks.length; i++)
                ticks[i] = xmin + i * (xmax - xmin) / (ticks.length - 1);
            XTickP.update(ticks);
        }
        autoTickLabelX();
    }
    
    protected void autoTickLabelX()
    {
        // angle from radians to degrees
        double[] ticks = XTickP.getArray();
        String[] labels = new String[ticks.length];
        for (int i=0; i<ticks.length; i++)
        {
            double val = ((double)Math.round(ticks[i]/Math.PI/2*360));
            labels[i] = new Double(val).toString();
        }
        XTickLabelP.update(labels);
    }
}




