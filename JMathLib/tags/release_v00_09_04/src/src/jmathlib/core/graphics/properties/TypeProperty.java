package jmathlib.core.graphics.properties;

import jmathlib.core.graphics.*;

public class TypeProperty extends Property
{
	private String s;

	public TypeProperty(PropertySet parent, String s)
	{
		super(parent, "Type");
		this.s = s;
	}

	public Object get()
	{
		return getArray();
	}

	public void set(Object value) throws PropertyException
	{
		
			throw new PropertyException("type property is read only");
	}


	public String getArray()
	{
		return s;
	}


	public String toString()
	{
		return "[ "+ s +" ]";
	}
}
