package jmathlib.ui.swing;

//import javax.swing.JDialog;
//import java.awt.HeadlessException;
import java.util.*;
import java.io.*;
//import java.util.*;
import jmathlib.core.functions.FileFunctionLoader;



public class AutoCompletion
{
    static AutoCompletion runningReference = null;

    /**
     * A sorted array with function names.
     */
    private String[] list;



    public AutoCompletion()
    {
        if (runningReference == null)
        {
            runningReference = this;
        }
    }


    public void load()
    {
        String s = System.getProperty("AUTOCOMP_FILE_EXTS");
        StringTokenizer st = new StringTokenizer(s," ");
        String[] fileExtensions = new String[st.countTokens()];
        int i=0;
        while (st.hasMoreTokens())
        {
            fileExtensions[i] = st.nextToken();
            i++;
        }

        KeyHandler.runningReference.interpreter.getFunctionManager().clearCustomFunctionLoaders();
        s = System.getProperty("AUTOCOMP_SEARCH_DIRS");
        st = new StringTokenizer(s," ");
        String[] fileDirectories = new String[st.countTokens()];
        i=0;
        while (st.hasMoreTokens())
        {
            fileDirectories[i] = st.nextToken();
            i++;
        }
        load(fileDirectories, fileExtensions);
    }



    public void load(String[] fileDirectories, String[] fileExtensions)
    {
        Vector v = new Vector(200);
        for (int i = 0; i < fileDirectories.length; i++)
        {
            //System.out.println("load "+fileDirectories[i]);
            v.addAll(recursiveLoad(new File(fileDirectories[i]), fileExtensions));
        }
        Collections.sort(v, new Comparator() {
            public int compare(Object o1, Object o2)
            {
                return ((String)o1).compareToIgnoreCase((String)o2);
            }
        });

        Object[] oa = v.toArray();
        list = new String[oa.length];
        for (int i = 0; i < oa.length; i++)
        {
            list[i] = (String) oa[i];
        }
    }



    private Vector recursiveLoad(File directory, String[] fileExtensions)
    {
        File[] fa = directory.listFiles();
        Vector list = new Vector();

        int i = 0;
        while (i < fa.length)
        {
            if (fa[i].isDirectory())
            {
                Vector v = recursiveLoad(fa[i], fileExtensions);
                if (v.size() > 0)
                {
                    try
                    {
                        KeyHandler.runningReference.interpreter.getFunctionManager().addFunctionLoader(new FileFunctionLoader(fa[i].getCanonicalFile(), false));
                    }
                    catch (IOException ioe) { ; }
                }
                list.addAll(v);
            }
            else if (fa[i].isFile())
            {
                for (int j = 0; j < fileExtensions.length; j++)
                {
                    if (fa[i].getName().endsWith(fileExtensions[j]))
                    {
                        list.add(fa[i].getName());
                        break;
                    }
                }
            }
            i++;
        }
        return list;
    }



    /**
     * Return those functions starting with the prefix.

     * @param prefix Prefix of the function name.
     * @return An array of function (full) names. If nothing can be matched, it
     * returns null
     */
    public String[] getMatched(String prefix)
    {
        if (prefix.equals(""))
        {
            return list;
        }
        int i = firstIndexOfMatchedString(prefix);
        int j = lastIndexOfMatchedString(prefix, i);
        String[] sa = new String[j-i];

        for (int k = 0; k < sa.length; k++)
        {
            sa[k] = list[i+k];
        }

        return sa;
    }



    private int firstIndexOfMatchedString(String prefix)
    {
        int i = 1;
        int j = 0;
        do
        {
            j += i/2;
            i = 1;
            while (j+i-1 < list.length)
            {
                int k = (list[j+i-1].length () < prefix.length ()) ?
                    list[j+i-1].length () : prefix.length ();
                int m = list[j+i-
                    1].substring (0, k).compareToIgnoreCase (prefix);
                if (list[j+i-1].length () >= prefix.length () && m >= 0)
                {
                    j += i/2;
                    if (i == 1) { break; }
                    i = 1;
                }
                else
                {
                    i *= 2;
                }
            }
        }
        while (i != 1);

        return j;
    }



    private int lastIndexOfMatchedString(String prefix, int startingPoint)
    {
        int i = 1;
        int j = startingPoint;
        do
        {
            j += i/2;
            i = 1;
            while (j+i-1 < list.length)
            {
                int k = (list[j+i-1].length () < prefix.length ()) ?
                    list[j+i-1].length () : prefix.length ();
                int m = list[j+i-
                    1].substring (0, k).compareToIgnoreCase (prefix);
                if (list[j+i-1].length () >= prefix.length () && m > 0)
                {
                    j += i/2;
                    if (i == 1) { break; }
                    i = 1;
                }
                else
                {
                    i *= 2;
                }
            }
        }
        while (i != 1);

        return j;
    }
}