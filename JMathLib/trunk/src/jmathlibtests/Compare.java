package jmathlibtests;

/** helper class to compare different data objects from mathlib  */
public class Compare
{
	
	/**compare two dimensional numerical arrays*/
	public static boolean ArrayEquals(double[][] a, double[][] b)
	{
    	return 	ArrayEquals(a, b, 0);
    }


	/**compare two dimensional numerical arrays*/
	public static boolean ArrayEquals(double[][] a, double[][] b, double tol)
	{
	    // number of rows must be greater 0 and equal 
	    if (a == null)            return false;
        if (b == null)            return false;
        if (a.length < 1)         return false;
        if (b.length < 1)         return false;
        if (a.length != b.length) return false;
        
    	// check all elements
        for (int y=0; y<a.length; y++)
        {
        	// length of each row must be equal
        	if (a[y].length != b[y].length) return false;
            
            // check individual elements
        	for (int x=0; x<a[y].length; x++)
            {
            	if (Math.abs(a[y][x] - b[y][x]) > tol)	return false;
        	}
        }

		// arrays are equal
        return true;
    }

    /**compare two dimensional boolean arrays*/
    public static boolean ArrayEquals(boolean[][] a, boolean[][] b)
    {
        // number of rows must be greater 0 and equal 
        if (a.length < 1)         return false;
        if (b.length < 1)         return false;
        if (a.length != b.length) return false;
        
        // check all elements
        for (int y=0; y<a.length; y++)
        {
            // length of each row must be equal
            if (a[y].length != b[y].length) return false;
            
            // check individual elements
            for (int x=0; x<a[y].length; x++)
            {
                if (a[y][x] != b[y][x])  return false;
            }
        }

        // arrays are equal
        return true;
    }

}
