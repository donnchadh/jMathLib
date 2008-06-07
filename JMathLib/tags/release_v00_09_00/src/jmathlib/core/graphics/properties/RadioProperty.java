package jmathlib.core.graphics.properties;

import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import jmathlib.core.graphics.*;

public class RadioProperty extends Property
{
	protected Set    valueSet;
	protected String value;

	public RadioProperty(PropertySet parent, String name, String[] values, String defaultValue)
	{
		super(parent, name);
		valueSet = Collections.synchronizedSet(new HashSet());
        
		for (int i=0; i<values.length; i++)
			valueSet.add(values[i]);
		
        if (valueSet.contains(defaultValue))
			value = defaultValue;
		else
			value = values[0];
	}

	public Object get()
	{
		return getValue();
	}

	public void set(Object newValue) throws PropertyException
	{
		if (newValue instanceof String)
			setValueInternal((String)newValue);
		else
			throw new PropertyException("invalid property value - " + newValue.toString());
	}

	public String getValue()
	{
		return value;
	}

	public boolean is(String val)
	{
		return value.equals(val);
	}

	private void setValueInternal(String newValue) throws PropertyException
	{
		if (valueSet.contains(newValue))
		{
			value = newValue;
			valueChanged();
		}
		else
			throw new PropertyException("invalid property value - " + newValue);
	}

	public String toString()
	{
		return value;
	}
}
