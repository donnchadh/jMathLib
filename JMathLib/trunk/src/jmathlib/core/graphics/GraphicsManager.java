/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   stefan@held-mueller.de and others
 * (c) 2008-2009   
 */
package jmathlib.core.graphics;

import java.util.Vector;
import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.graphics.properties.*;

public class GraphicsManager extends HandleObject
{

	/**A list of all figures*/	
	Vector figures = new Vector();

	/** current figure that is used for plot()... commands*/
	private int currentFigure = -1;

    /** properties */
    //CallbackObjectP
    //DoubleStructProperty CommandWindowSizeP;
    //CurrentFigureP
    //BooleanProperty DiaryP = new BooleanProperty(this, "Diary", true);
    //DiaryFileP
    //BooleanProperty EchoP = new BooleanProperty(this, "Echo", true);
    //FixedWithFontNameP
    //FormatP
    //FormatSpacingP
    //LanguageP
    //DoubleStructProperty MonitorPositionsP
    //BooleanProperty MoreP
    //PointerLocationP
    //PointerWindowP
    //RecursionLimitP
    //ScreenDepthP
    //ScreenPicxelsPerInchP
    //ScreenSizeP
    //BooleanProperty ShowHiddenHandlesP
    UnitsProperty UnitsP = new UnitsProperty(this, "Units", "pixels");
    //BooleanProperty BeeingDeletedP
    //ButtonDownFcnP
    //ChildrenP
    //BooleanProperty ClippingP
    //CreateFcnP
    //DeleteFcnP
    //BusyActionP
    //BooleanProperty HandleVisibilityP
    //BooleanProperty HitTestP
    //BooleanProperty InterruptibleP = new BooleanProperty(this, "Interruptible", true);
    //ParentP
    BooleanProperty SelectedP = new BooleanProperty(this, "Selected", false);
    BooleanProperty SelectionHighlightP = new BooleanProperty(this, "SelectionHighlight", true);
    //TagP
    //TypeP
    //UIContextMenuP
    //UserDataP
    BooleanProperty VisibileP = new BooleanProperty(this, "Visible", true);
    
	public GraphicsManager()
	{

	}

	public FigureObject getCurrentFigure() 
	{
		// If no figure was created before, create one.
		//if (currentFigure == -1) createNewFigure();

		// find current figure
	    for(int n = 0; n < figures.size(); n++)
		{
			if ( ((FigureObject)figures.elementAt(n)).getFigureNumber() == currentFigure )
				return (FigureObject)figures.elementAt(n);
		}

		// If this point is reached the figure number does not exist any more
		// If no figure was created before, create one.
		createNewFigure();				
		return getCurrentFigure();
	}


	public void createNewFigure() 
	{
		createNewFigure( getUnusedFigureNumber() );

		ErrorLogger.debugLine("GraphicsManager: createNewFigure: "+currentFigure);
	}

	public void createNewFigure(int _figureNo) 
	{
		if (_figureNo < 1 ) return;

		// The current figure is switch to this number anyway, so that all
        //  subsequent graphics-commands go to this figure
		currentFigure    = _figureNo;

		// check if the figure with number _figureNo is already created
		if (findFigureNumber(_figureNo)) return;

		FigureObject fig = new FigureObject(currentFigure);
		fig.setGraphicsManager(this);
		figures.addElement( fig );

		ErrorLogger.debugLine("GraphicsManager: addFigure: "+currentFigure);
	}


	/**check the figures vector if a figure with the number _figureNo exists*/
	private boolean findFigureNumber(int _figureNo)
	{
		FigureObject fig = findFigure(_figureNo);
		return (fig != null);
	}

	private FigureObject findFigure(int _figureNo)
	{
	        for(int n = 0; n < figures.size(); n++)
		{
			if ( ((FigureObject)figures.elementAt(n)).getFigureNumber() == _figureNo )
				return (FigureObject)figures.elementAt(n);
		}
		return null;
	}

	/**return a figure number that is not already used*/
	private int getUnusedFigureNumber()
	{
		int number = 1;

		while (true)
		{
			if (findFigureNumber( number ) == false) return number;
			number++;
		}
	}


	public int getCurrentFigureNumber() 
	{
		return currentFigure;
	}

	public void setCurrentFigure(int _currentFigure)
	{
		if (!findFigureNumber(_currentFigure)) return;

		currentFigure = _currentFigure;
	}

    // public FigureObject getFigure(int index)
	//{
	//	if(index > -1)
	//		return (FigureObject)figures.elementAt(index);
	//	else
	//		return null;
	//}


	public void removeFigure(int _figureNo)
	{

		// check if this figure is the  "current figure"
		if (_figureNo == currentFigure) currentFigure = -1;

		// find figure
	    for(int n = 0; n < figures.size(); n++)
		{
			if ( ((FigureObject)figures.elementAt(n)).getFigureNumber() == _figureNo )
			{
				figures.removeElementAt(n);
			}
		}

		if (currentFigure < 1 && figures.size() > 0)
			currentFigure = ((FigureObject)figures.elementAt(0)).getFigureNumber();
	}

	public void findProperty(String property)
	{

	}

	public void setProperty(String property, double value)
	{

	}

	public void setProperty(String property, String value)
	{

	}

	public void closeAll()
	{
		for(int n = 0; n < figures.size(); n++)
			((FigureObject)figures.elementAt(n)).close(false);
		figures.clear();
		currentFigure = -1;
	}

	public void closeFigure(int figureNo)
	{
		if (figureNo < 1)
		{
			if (currentFigure >= 1)
				closeFigure (currentFigure);
		}
		else
		{
			FigureObject fig = findFigure(figureNo);
			if (fig != null)
				fig.close();
		}
	}

	/*public HandleObject getHandleObject(int handle)
	{
		try { return HandleObject.getHandleObject(handle); }
		catch (Exception e) { 
		    ErrorLogger.debugLine("GraphicalManager: handle not found");      
        }
		return null;
	}*/
}
