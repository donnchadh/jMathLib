package jmathlib.core.graphics.properties;

import java.text.DecimalFormat;
import jmathlib.core.graphics.*;

public class IntegerProperty extends Property
{
	private int value;

	public IntegerProperty(PropertySet parent, String name, int value)
	{
		super(parent, name);
		this.value = value;
	}

	public Object get()
	{
		return new Integer(value);
	}

	public void set(Object val) throws PropertyException
	{
		if (val instanceof Number)
			value = ((Number)val).intValue();
		else
			throw new PropertyException("invalid property value - " + val.toString());
	}

    public double intValue()
    {
        return value;
    }

    public double doubleValue()
	{
		return (double)value;
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
