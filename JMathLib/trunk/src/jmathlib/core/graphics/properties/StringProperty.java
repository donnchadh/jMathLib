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
		return getArray();
	}

	public void set(Object value) throws PropertyException
	{
		try
		{
			String v = (value == null ? new String()  : (String)value);
			setArrayInternal(v);
		}
		catch (ClassCastException e)
		{
			throw new PropertyException("invalid character array value - " + value.toString());
		}
	}

	private void setArrayInternal(String a)
	{
		s = a;
		valueChanged();
	}

	public String getArray()
	{
		return s;
	}

	public void setArray(String v)
	{
		try { set(v); }
		catch (PropertyException e) { }
	}

	public String toString()
	{
		return "[ "+ s +" ]";
	}
}
