package jmathlib.core.graphics.properties;


import java.text.DecimalFormat;
import jmathlib.core.graphics.*;

public class DoubleArrayProperty extends Property
{
	private int maxCount;
	private double[] array;

	public DoubleArrayProperty(PropertySet parent, String name, double[] value, int maxCount)
	{
		super(parent, name);
		this.array = value;
		this.maxCount = maxCount;
	}

	public Object get()
	{
		return getArray();
	}

	public void set(Object value) throws PropertyException
	{
		try
		{
			double[] v = (value == null ? new double[0] : (double[])value);
			if (maxCount != -1 && v.length != maxCount)
				throw new PropertyException("incorrect array length - " + value.toString());
			setArrayInternal(v);
		}
		catch (ClassCastException e)
		{
			throw new PropertyException("invalid array value - " + value.toString());
		}
	}

	public double[] getArray()
	{
		return array;
	}

	public void setArray(double[] a)
	{
		try { set(a); }
		catch (PropertyException e) { }
	}

	private void setArrayInternal(double[] v)
	{
		array = v;
		valueChanged();
	}

	public String toString()
	{
		if (array.length > 4)
			return ("[ 1 x " + array.length + " array ]");
		else
		{
			String s = "[ ";
			DecimalFormat fmt = new DecimalFormat("0.0000 ");
			for (int i=0; i<array.length; i++)
				s += fmt.format(array[i]);
			s += "]";
			return s;
		}
	}
}
