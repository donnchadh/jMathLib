package jmathlib.core.graphics.properties;

//import java.awt.Color;
import java.util.LinkedList;
import java.util.Iterator;
import jmathlib.core.graphics.*;
import jmathlib.core.interpreter.*;

/**Abstract root class for any kind of property*/
public abstract class Property
{
	private String     name         = "";
	private LinkedList listenerList = new LinkedList();
	private boolean    lockNotify   = false;

	public Property(PropertySet parent, String name)
	{
		this.name = name;
		parent.addProperty(this);
	}

	public String getName()
	{
		return name;
	}

	public void addPropertyListener(PropertyListener listener)
	{
		listenerList.add(listener);
	}

	public abstract Object get();

	public abstract void set(Object value) throws PropertyException;

	public void update(Object value) //throws PropertyException
	{
		lockNotify = true;
		//set(value);
        try { set(value); }
		catch (PropertyException e) {  e.printStackTrace(); Errors.throwMathLibException("Property update:"+name+" EXCEPTION ");}
		lockNotify = false;
	}

	/*
	public boolean equals(Object obj)
	{
		if (obj instanceof Property)
			return key.equals(((Property)obj).key);
		else if (obj instanceof String)
			return key.equals(obj);
		else
			return super.equals(obj);
	}
	*/

	protected void valueChanged()
	{
		if (!lockNotify)
		{
			Iterator it = listenerList.iterator();
			while (it.hasNext())
				((PropertyListener)it.next()).propertyChanged(this);
		}
	}
}
