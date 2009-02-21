package jmathlibtests.toolbox.string;

import jmathlib.tools.junit.framework.*;

public class testStrncmp extends JMathLibTestCase {
	
    public testStrncmp(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}

	public static Test suite() {
		return new TestSuite(testStrncmp.class);
	}

	public void testStrcmpi01() {
		assertEvalScalarEquals("a=strncmp('abcdxxx','abcddd', 3);", "a", 1);
	}
    public void testStrcmpi02() {
        assertEvalScalarEquals("a=strncmp('abcDDD','abcDD', 3);", "a", 1);
    }
    public void testStrcmpi03() {
        assertEvalScalarEquals("a=strncmp('abxxxd','abcd', 3);", "a", 0);
    }
    public void testStrcmpi04() {
        assertEvalScalarEquals("a=strncmp('abc','abcdd', 3);", "a", 1);
    }
    public void testStrcmpi05() {
        assertEvalScalarEquals("a=strncmp('ab','abcd', 3);", "a", 0);
    }
    public void testStrcmpi06() {
        assertEvalScalarEquals("a=strncmpi('abcd','ab', 3);", "a", 0);
    }
    public void testStrcmpi07() {
        assertEvalScalarEquals("a=strncmp('abcd','abcXx', 3);", "a", 1);
    }



}
