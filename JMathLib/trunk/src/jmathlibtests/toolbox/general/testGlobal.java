/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:  Stefan Mueller (stefan@held-mueller.de) 
 * (c) 2008, 2009   
 */

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
                doit.evaluate(null, ml.globals);
            } 
            catch (MathLibException ex) {
                exCaught = true;
            }
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
            ml.globals.getFunctionManager().addFunctionLoader(new TestFunctionLoader(doit));
            
            b = new StringBuffer();
            b.append("a = 1;\n");
            b.append("doit;\n");
            b.append("doit;\n");
            b.append("doit;\n");
            ml.executeExpression(b.toString());                        
            double val = ml.getScalarValueRe("a");
            assertTrue(4 == val);
        }

	   public void testGlobal10() {
	        ml.executeExpression("clear('all');");
            ml.executeExpression("global asdf");
            ml.executeExpression("asdf = 5");
	        assertTrue(5.0 == ml.getScalarValueRe("asdf"));
	    }

       public void testGlobal11() {
           ml.executeExpression("clear('all');");
           ml.executeExpression("asdf = 55");
           ml.executeExpression("global asdf");
           assertTrue(55.0 == ml.getScalarValueRe("asdf"));
       }

       public void testGlobal100() {
           ml.executeExpression("clear('all');");
           ml.executeExpression("helloGlobal = 11.0");
           ml.executeExpression("testGlobal001(8)");
           assertTrue(11.0 == ml.getScalarValueRe("helloGlobal"));
           ml.executeExpression("testGlobal001(77)");
           assertTrue(11.0 == ml.getScalarValueRe("helloGlobal"));
       }

       public void testGlobal101() {
           ml.executeExpression("clear('all');");
           ml.executeExpression("global helloGlobal;");
           ml.executeExpression("helloGlobal = 11.0");
           assertTrue(11.0 == ml.getScalarValueRe("helloGlobal"));
           ml.executeExpression("testGlobal001(88)");
           assertTrue(88.0 == ml.getScalarValueRe("helloGlobal"));
           ml.executeExpression("helloGlobal = 99.0");
           assertTrue(99.0 == ml.getScalarValueRe("helloGlobal"));
           ml.executeExpression("testGlobal001(88)");
           assertTrue(88.0 == ml.getScalarValueRe("helloGlobal"));
       }

}