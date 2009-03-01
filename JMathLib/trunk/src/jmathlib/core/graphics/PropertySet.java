/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   stefan@held-mueller.de and others
 * (c) 2008-2009   
 */
package jmathlib.core.graphics;

import jmathlib.core.graphics.properties.*;
import jmathlib.core.interpreter.Errors;

import java.util.*;


public class PropertySet extends TreeMap
{
	public PropertySet()
	{
		super();
	}

	public void addProperty(Property p)
	{
		put(p.getName().toLowerCase(), p);
	}

	public Property getProperty(String name)
	{
		return (Property)get((Object)name.toLowerCase());
	}

	public Object get(String name) //throws PropertyException
	{
		Property p = getProperty(name);
		//if (p != null)
			return p.get();
		//throw new PropertyException("property not found - " + name);
    }

	public void set(String name, Object value)  throws PropertyException
	{
		Property p = getProperty(name);

        try {
        if (p != null)
			p.set(value);
        }
        catch (PropertyException e)
        {
        //throw new PropertyException("property not found - " + name);
            Errors.throwMathLibException("PropertySet: set "+name+"EXCEPTION");
        }
        
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
