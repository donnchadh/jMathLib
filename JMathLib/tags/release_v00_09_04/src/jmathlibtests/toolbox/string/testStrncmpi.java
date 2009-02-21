package jmathlibtests.toolbox.string;

import jmathlib.tools.junit.framework.*;

public class testStrncmpi extends JMathLibTestCase {
	
    public testStrncmpi(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}

	public static Test suite() {
		return new TestSuite(testStrncmpi.class);
	}

	public void testStrcmpi01() {
		assertEvalScalarEquals("a=strncmpi('abcdxxx','abcddd', 3);", "a", 1);
	}
    public void testStrcmpi02() {
        assertEvalScalarEquals("a=strncmpi('abcDDD','ABCD', 3);", "a", 1);
    }
    public void testStrcmpi03() {
        assertEvalScalarEquals("a=strncmpi('abXxxd','AbCd', 3);", "a", 0);
    }
    public void testStrcmpi04() {
        assertEvalScalarEquals("a=strncmpi('abc','ABCDDd', 3);", "a", 1);
    }
    public void testStrcmpi05() {
        assertEvalScalarEquals("a=strncmpi('aB','abcd', 3);", "a", 0);
    }
    public void testStrcmpi06() {
        assertEvalScalarEquals("a=strncmpi('aBCd','ab', 3);", "a", 0);
    }
    public void testStrcmpi07() {
        assertEvalScalarEquals("a=strncmpi('abcd','abcXx', 3);", "a", 1);
    }



}
