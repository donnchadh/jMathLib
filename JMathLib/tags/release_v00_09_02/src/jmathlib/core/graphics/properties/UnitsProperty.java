package jmathlib.core.graphics.properties;

import jmathlib.core.graphics.*;

public class UnitsProperty extends RadioProperty
{
	public UnitsProperty(PropertySet parent, String name, String style)
	{
		super(parent, name, new String[] {"inches","centimeters","normalized","points","pixels","characters"}, style);
	}
}
