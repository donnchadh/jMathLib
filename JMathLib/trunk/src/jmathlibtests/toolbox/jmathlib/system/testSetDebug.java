package jmathlibtests.toolbox.jmathlib.system;

import jmathlib.tools.junit.framework.*;

public class testSetDebug extends JMathLibTestCase {
	
    public testSetDebug(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}

	public static Test suite() {
		return new TestSuite(testSetDebug.class);
	}

	public void testSetDebug01() {
	    assertEvalScalarEquals("setdebug(1); a=getdebug()","a", true);
	}

    public void testSetDebug02() {
        assertEvalScalarEquals("setdebug(0); a=getdebug()","a", false);
    }
   



}
