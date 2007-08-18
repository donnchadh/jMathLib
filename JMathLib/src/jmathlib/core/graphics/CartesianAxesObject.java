package jmathlib.core.graphics;

import java.awt.*;
import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.graphics.properties.*;

/** created and holds the axes of a plot*/
public class CartesianAxesObject extends AxesObject
{

	public CartesianAxesObject()
   	{
	    super();
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

}




