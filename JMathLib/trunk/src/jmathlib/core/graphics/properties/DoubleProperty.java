package jmathlib.core.graphics.properties;

import java.text.DecimalFormat;
import jmathlib.core.graphics.*;

public class DoubleProperty extends Property
{
	private double value;

	public DoubleProperty(PropertySet parent, String name, double value)
	{
		super(parent, name);
		this.value = value;
	}

	public Object get()
	{
		return new Double(value);
	}

	public void set(Object val) throws PropertyException
	{
		if (val instanceof Number)
			value = ((Number)val).doubleValue();
		else
			throw new PropertyException("invalid property value - " + val.toString());
	}

	public double doubleValue()
	{
		return value;
	}

	public float floatValue()
	{
		return (float)value;
	}

	public String toString()
	{
		DecimalFormat fmt = new DecimalFormat("0.0000");
		return fmt.format(value);
	}
}
