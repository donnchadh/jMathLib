package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testFft extends TestCase {
	protected Interpreter ml;
	
    public testFft(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testFft.class);
	}

	public void testFft01() {
        ml.executeExpression("x=linspace(0,20,256)");
        ml.executeExpression("y=sin(x).*sin(3*x);");
        ml.executeExpression("z=fft(y)");
        ml.executeExpression("zz=find(z>0.3)");
        ml.executeExpression("a1=zz(1);");
        ml.executeExpression("a2=zz(2);");
        ml.executeExpression("a3=z(100);");
        assertTrue(ml.getScalarValueRe("a1") == 7);
        assertTrue(ml.getScalarValueRe("a2") == 14);
        assertTrue(ml.getScalarValueRe("a3") <= 0.2);
	}


}
