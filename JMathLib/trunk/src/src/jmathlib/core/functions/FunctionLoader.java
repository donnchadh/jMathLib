/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   
 * (c) 2005-2009   
 */
package jmathlib.core.functions;

import java.util.*;


/** Base class used to find and load a function.
 */
public abstract class FunctionLoader
{    
    private boolean isSystemLoader;
    private HashMap functionCache = new HashMap();
    
    protected FunctionLoader(boolean _isSystemLoader) {
        isSystemLoader = _isSystemLoader;
    }
    
    public FunctionLoader() {this(false);}
    
    
    protected void cacheFunction(Function f) {
      functionCache.put(f.name, f);
    }
    
    protected Function getCachedFunction(String name) {
      return (Function)functionCache.get(name);
    }
    
    protected Iterator getCachedFunctionIterator() {
        return functionCache.values().iterator();
    }
    
    protected void clearCachedFunction(String name) {
        functionCache.remove(name);
    }
    
    public void clearCache() {
      functionCache.clear();
    }
    
    public boolean isSystemLoader() {
      return isSystemLoader;
    }
    
    /**find unknown class/m-file in directory structure
       @param fileName = the file to look for*/
    public abstract Function findFunction(String functionName);
    
    public abstract void setPFileCaching(boolean caching);
    
    public abstract boolean getPFileCaching();
    
    public abstract void checkAndRehashTimeStamps();
    

}
