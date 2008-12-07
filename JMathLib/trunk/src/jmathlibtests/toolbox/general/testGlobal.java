package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlib.core.functions.*;
import jmathlib.core.interpreter.*;

public class testGlobal extends TestCase {
	protected Interpreter ml;
        
        public class TestFunctionLoader extends FunctionLoader {
            Function _f;
            public TestFunctionLoader(Function f) {
                _f = f;
            }

            public Function findFunction(String functionName) {
              if (_f.getName().equals(functionName))
                return _f;
              return null;
            }

            public void setPFileCaching(boolean caching) {
            }

            public boolean getPFileCaching() {
              return false;
            }

            public void checkAndRehashTimeStamps() {
            }            
        }
	
    public testGlobal(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}
    protected void tearDown() {
        ml = null;
    }

	public static Test suite() {
		return new TestSuite(testGlobal.class);
	}

	public void testGlobal01() {
            //Test parse error for function with missing global
            StringBuffer b = new StringBuffer();
            b.append("function doit()\n");
            //Missing "global a;"
            b.append("a = a+1;\n");
            b.append("end\n");            
            FunctionParser funcParser = new FunctionParser();
            boolean exCaught = false;
            Function doit = null;
            //Expected an exception here
            try {
            doit = funcParser.parseFunction(b.toString());
            } catch (MathLibException ex) {
                exCaught = true;
            }
            assertNull(doit);
            assertTrue(exCaught);
        }        

	public void testGlobal02() {
            //Test sucessful parse and update of global variable
            ml.executeExpression("global a;");                        
            
            StringBuffer b = new StringBuffer();
            b.append("function doit()\n");
            b.append("global a;\n");
            b.append("a = a+1;\n");
            b.append("end\n");            
            FunctionParser funcParser = new FunctionParser();
            Function doit = funcParser.parseFunction(b.toString());
            assertNotNull(doit);
            ml.getFunctionManager().addFunctionLoader(new TestFunctionLoader(doit));
            
            b = new StringBuffer();
            b.append("a = 1;\n");
            b.append("doit;\n");
            b.append("doit;\n");
            b.append("doit;\n");
            ml.executeExpression(b.toString());                        
            double val = ml.getScalarValueRe("a");
		assertTrue(4 == val);
        }

}