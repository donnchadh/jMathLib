package jmathlib.core.graphics.properties;

import jmathlib.core.graphics.*;

public class StringProperty extends Property
{
	private String s;

	public StringProperty(PropertySet parent, String name, String s)
	{
		super(parent, name);
		this.s = s;
	}

	public Object get()
	{
		return getString();
	}

	public void set(Object value) throws PropertyException
	{
		try
		{
			String v = (value == null ? new String()  : (String)value);
			s = v;
	        valueChanged();
		}
		catch (ClassCastException e)
		{
			throw new PropertyException("invalid character array value - " + value.toString());
		}
	}


	public String getString()
	{
		return s;
	}

	public void setString(String v)
	{
		try { set(v); }
		catch (PropertyException e) { }
	}

	public String toString()
	{
		return "[ "+ s +" ]";
	}
}
