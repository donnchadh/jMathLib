/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   
 * (c) 2005-2009   
 */
package jmathlib.core.functions;

import java.util.ArrayList;
import java.io.File;

/**
 * Using a base directory, uses a consistent search to find a function by its name
 */
public class FunctionPathBroker 
{
    private File      baseDir = null;
    private ArrayList paths   = new ArrayList();
    private boolean traverseChildren;
    
    public FunctionPathBroker(File _baseDir, boolean _traverseChildren) 
    {
        baseDir = _baseDir;
        traverseChildren = _traverseChildren;
        
        populateSearchPaths();      
    }
    
    private void populateSearchPaths() {
        paths.clear();
        if (traverseChildren) 
        {            
            if (baseDir.exists() && baseDir.isDirectory()) 
            {
              addSearchPath(baseDir);
            }
        }      
    }
    
    public File getBaseDirectory() 
    {
        return baseDir;
    }
    
    public void setBaseDirectory(File dir) {
       this.baseDir = dir;
       
       populateSearchPaths();
    }
    
    public File findFunction(String functionName) {
        File result = findClassOrMFile(baseDir, functionName);
        if(result == null)
        {            
            int size = paths.size();
            for(int index = 0; index < size && (result == null); index++)
            {
                result = findClassOrMFile((File)paths.get(index), functionName);               
            }
        }      
        return result;
    }
    
    /**Searchs a directory for the specified class
        @param path         - the directory to search
        @param functionName - the function to search for
        
        @return a File object representing the full path to the file that matches the fileName
      */
    private File findClassOrMFile(File path, String functionName)
    {
        //System.out.println("file search: "+fileName);
        File[] files = path.listFiles();
        
        File result = null;
        
        // only check non-empty directories
        if (files != null)
        {
            for(int fileNo = 0; fileNo < files.length; fileNo++)
            {
                String fileName = files[fileNo].getName();
               
                int index = fileName.lastIndexOf(".");
                //System.out.println("file: "+temp);
                if(index > -1)
                {                    
                    String tempFunction = fileName.substring(0, index);
                    if(tempFunction.equals(functionName) &&
                       (fileName.equals(functionName+".m")   ||
                        fileName.equals(functionName+".p")   ||
                        fileName.equals(functionName+".class") ) )
                    {
                        result = files[fileNo];
                        break;
                    }
                }
            }
        }
        return result;
    }    
    
    /**
     * build up the list of directories to search for functions
     */
    private void addSearchPath(File path)
    {
        String[] files = path.list();

        if(files != null)
        {
            for(int fileNo = 0; fileNo < files.length; fileNo++)
            {               
                String newPath = path + File.separator + files[fileNo];
                File temp = new File(newPath);
                if(temp.isDirectory() && newPath.indexOf("_private") == -1)
                {                    
                    paths.add(temp);
                    addSearchPath(temp);
                }
            }
        }
    }   
    
    /**
     * 
     * @return
     */
    public int getPathCount() 
    {
        return paths.size();
    }
    
    /**
     * 
     * @param index
     * @return
     */
    public File getPath(int index) 
    {
        return (File)paths.get(index);
    }
}
