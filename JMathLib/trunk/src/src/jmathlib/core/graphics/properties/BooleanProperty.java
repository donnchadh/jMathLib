package jmathlib.core.graphics.properties;

import jmathlib.core.graphics.*;


public class BooleanProperty extends RadioProperty
{
	public BooleanProperty(PropertySet parent, String name, boolean value)
	{
		super(parent, name, new String[] {"on", "off"}, (value ? "on" : "off"));
	}

	public boolean getBoolean()
	{
		return getValue().equals("on");
	}

	public boolean isSet()
	{
		return getBoolean();
	}


    public void set(boolean value) 
    {
        try
        {
            set(new Boolean(value));
        }
        catch (Exception e)
        { 
            jmathlib.core.interpreter.Errors.throwMathLibException("BooleanProperty exception");
        }
        
    }
    
    public void set(Object value) throws PropertyException
	{
		if (value instanceof Boolean)
			super.set(((Boolean)value).booleanValue() ? "on" : "off");
		else if (value instanceof Number)
			super.set(((Number)value).intValue() != 0 ? "on" : "off");
		else
			super.set(value);
	}
}
