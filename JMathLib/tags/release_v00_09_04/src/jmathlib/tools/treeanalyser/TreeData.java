package jmathlib.tools.treeanalyser;

import java.awt.Color;
import java.awt.Font;


public class TreeData extends Object
{
    /** Font used for drawing. */
    protected Font          font;

    /** Color used for text. */
    protected Color         color;

    /** Value to display. */
    protected String        string;


    /**
      * Constructs a new instance of SampleData with the passed in
      * arguments.
      */
    public TreeData(Font newFont, Color newColor, String newString) {
	font   = newFont;
	color  = newColor;
	string = newString;
    }

    public TreeData(String newString) {
	string = newString;
    }


    public void setFont(Font newFont) 
	{
		font = newFont;
    }

    public Font getFont() 
	{
		return font;
    }

    public void setColor(Color newColor) 
	{
		color = newColor;
    }

    public Color getColor() 
	{
		return color;
    }

    public void setString(String newString) 
	{
		string = newString;
    }

    public String string() 
	{
		return string;
    }

    public String toString() 
	{
		return string;
    }
}
