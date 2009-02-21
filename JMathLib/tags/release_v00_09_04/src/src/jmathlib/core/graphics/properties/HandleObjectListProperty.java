package jmathlib.core.graphics.properties;

import java.util.*;
import jmathlib.core.graphics.*;

public class HandleObjectListProperty extends Property
{
	Vector objectList;
	int maxCount;

	public HandleObjectListProperty(PropertySet parent, String name, int maxCount)
	{
		super(parent, name);
		this.maxCount = maxCount;
		this.objectList = new Vector();
	}

	public Object get()
	{
		return getHandleArray();
	}

	public void set(Object value) throws PropertyException
	{
		throw new PropertyException("cannot set property - " + getName());
	}

	public double[] getHandleArray()
	{
		double[] hList = new double[objectList.size()];
		Iterator it = objectList.iterator();
		int index = 0;

		while (it.hasNext())
		{
			HandleObject hObj = (HandleObject)it.next();
			hList[index++] = hObj.getHandle();
		}
		return hList;
	}

	public void addElement(HandleObject obj)
	{
		if (maxCount < 0 || objectList.size() < maxCount)
			objectList.add(obj);
	}

	public HandleObject elementAt(int index)
	{
		return (HandleObject)objectList.elementAt(index);
	}

	public int size()
	{
		return objectList.size();
	}

	public Iterator iterator()
	{
		return objectList.iterator();
	}

	public void removeAllElements()
	{
		Iterator it = objectList.iterator();

		while (it.hasNext())
		{
			HandleObject obj = (HandleObject)it.next();
			HandleObject.removeHandleObject(obj.getHandle());
		}
		objectList.removeAllElements();
	}

	public String toString()
	{
		String buf = "[ ";
		Iterator it = objectList.iterator();

		while (it.hasNext())
		{
			HandleObject hObj = (HandleObject)it.next();
			buf += (hObj.getHandle() + " ");
			if (buf.length() > 64)
				return ("[ 1 x " + objectList.size() + " handle array ]");
		}
		buf += "]";

		return buf;
	}
}
