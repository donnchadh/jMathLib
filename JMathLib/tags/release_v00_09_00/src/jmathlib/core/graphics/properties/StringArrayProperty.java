package jmathlib.core.graphics.properties;

import jmathlib.core.graphics.*;

public class StringArrayProperty extends Property
{
	private String[] array;

	public StringArrayProperty(PropertySet parent, String name, String[] value)
	{
		super(parent, name);
		array = value;
	}

	public Object get()
	{
		return getArray();
	}

	public void set(Object value) throws PropertyException
	{
		try
		{
			String[] v = (value == null ? new String[0] : (String[])value);
			setArrayInternal(v);
		}
		catch (ClassCastException e)
		{
			throw new PropertyException("invalid character array value - " + value.toString());
		}
	}

	private void setArrayInternal(String[] a)
	{
		array = a;
		valueChanged();
	}

	public String[] getArray()
	{
		return array;
	}

	public void setArray(String[] v)
	{
		try { set(v); }
		catch (PropertyException e) { }
	}

	public String toString()
	{
		String buf = "[ ";
		for (int i=0; i<array.length; i++)
		{
			buf += (array[i] + " ");
			if (buf.length() > 64)
				return ("[ 1 x " + array.length + " character array ]");
		}
		buf += "]";
		return buf;
	}
}
