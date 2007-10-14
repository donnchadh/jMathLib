package jmathlib.core.graphics.properties;

import jmathlib.core.graphics.*;

public class FunctionalHandleProperty extends Property
{
	private String value;

	public FunctionalHandleProperty(PropertySet parent, String name, String value)
	{
		super(parent, name);
		this.value = value;
	}

	public Object get()
	{
		return new String(value);
	}

	public void set(Object val) throws PropertyException
	{
		if (val instanceof String)
			value = ((String)val);
		else
			throw new PropertyException("invalid property value - " + val.toString());
	}


	public String toString()
	{
		return value;
	}
}
