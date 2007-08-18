package jmathlib.core.graphics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.lang.ref.WeakReference;
import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.graphics.properties.*;
import jmathlib.core.interpreter.*;

/**Base class for handle-based graphics*/
public class HandleObject extends PropertySet
{
	private int            handle;
	private static int     handleSeed = -1;
	private static HashMap handleMap  = new HashMap();

	private static int newHandle()
	{
		return handleSeed--;
	}

	public HandleObject()
	{
		handle = newHandle();
		handleMap.put(new Integer(handle), new WeakReference(this));
	}

	public HandleObject(int handle)
	{
		this.handle = handle;
		handleMap.put(new Integer(handle), new WeakReference(this));
	}

	protected void finalize()
	{
		ErrorLogger.debugLine("finalizing handle: " + handle);
		handleMap.remove(new Integer(handle));
	}

	public int getHandle()
	{
		return handle;
	}

	public static HandleObject getHandleObject(int handle) throws Exception
	{
		WeakReference ref = (WeakReference)handleMap.get(new Integer(handle));
		if (ref != null && ref.get() != null)
		{
			return (HandleObject)ref.get();
		}
		throw new Exception("invalid handle - " + handle);
	}

	public static void removeHandleObject(int handle)
	{
		handleMap.remove(new Integer(handle));
	}

	public static void listObjects()
	{
		Iterator it = handleMap.entrySet().iterator();
        ErrorLogger.debugLine("HandleObject:");
		while (it.hasNext())
		{
			Map.Entry entry = (Map.Entry)it.next();
            ErrorLogger.debugLine(entry.getKey() + " = " + entry.getValue());
		}
	}
    
    public void addProperty(Property p)
    {
        put(p.getName().toLowerCase(), p);
    }

    public Property getProperty(String name)
    {
        return (Property)get((Object)name.toLowerCase());
    }

    public Object get(String name) 
    {
        Property p = getProperty(name);
        if (p != null)
            return p.get();
        Errors.throwMathLibException("property not found - " + name);
        return null;
    }

    public void set(String name, Object value)
    {
        Property p = getProperty(name);
        if (p != null)
        {
            try {
              p.set(value);
             }
            catch (PropertyException e){ Errors.throwMathLibException("property not found - " + name);}
        }
        else
            Errors.throwMathLibException("property not found - " + name);
    }

    public void show()
    {
        Iterator it = values().iterator();
        while (it.hasNext())
        {
            Property p = (Property)it.next();
            System.out.println("show  " + p.getName() + " = " + p);
        }
    }
}
