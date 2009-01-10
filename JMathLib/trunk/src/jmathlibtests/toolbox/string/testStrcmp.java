package jmathlibtests.toolbox.string;

import jmathlib.tools.junit.framework.*;

public class testStrcmp extends JMathLibTestCase {
	
    public testStrcmp(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}

	public static Test suite() {
		return new TestSuite(testStrcmp.class);
	}

	public void testStrcmp01() {
		assertEvalScalarEquals("a=strcmp('abcd','abcd');", "a", 1);
	}
    public void testStrcmp02() {
        assertEvalScalarEquals("a=strcmp('abcd','ABCD');", "a", 0);
    }
    public void testStrcmp03() {
        assertEvalScalarEquals("a=strcmp('abcd','AbCd');", "a", 0);
    }
    public void testStrcmp04() {
        assertEvalScalarEquals("a=strcmp('abcd','ABCd');", "a", 0);
    }
    public void testStrcmp05() {
        assertEvalScalarEquals("a=strcmp('aBCd','abcd');", "a", 0);
    }
    public void testStrcmp06() {
        assertEvalScalarEquals("a=strcmp('aBCd','axcd');", "a", 0);
    }
    public void testStrcmp07() {
        assertEvalScalarEquals("a=strcmp('abcd','abcde');", "a", 0);
    }



}
