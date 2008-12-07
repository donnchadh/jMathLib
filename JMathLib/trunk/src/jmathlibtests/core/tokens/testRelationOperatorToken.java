package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.*;

public class testRelationOperatorToken extends TestCase {
	protected Interpreter ml;
	
    public testRelationOperatorToken(String name) {
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
		return new TestSuite(testRelationOperatorToken.class);
	}

	/************* < ***************/
    public void test001a() {
        ml.executeExpression("a=(2<3)");
		assertTrue(true == ml.getScalarValueBoolean("a"));
	}
    public void test001b() {
        ml.executeExpression("a=(3<2)");
        assertTrue(false == ml.getScalarValueBoolean("a"));
    }
    public void test001c() {
        ml.executeExpression("a=([1,2,3,4]<3)");
        boolean[][] r =          {{true,true,false,false}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test001d() {
        ml.executeExpression("a=(3<[1,2,3,4;  2,4,3,4])");
        boolean[][] r = {{false,false,false,true},{false,true,false,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test001e() {
        ml.executeExpression("a=([3,1;  1,4]<[1,2;  3,4])");
        boolean[][] r ={{false,true},{true,false}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
        
    /************* > ***************/
    public void test002a() {
        ml.executeExpression("a=(5>4)");
        assertTrue(true == ml.getScalarValueBoolean("a"));
    }
    public void test002b() {
        ml.executeExpression("a=(1>2)");
        assertTrue(false == ml.getScalarValueBoolean("a"));
    }
    public void test002c() {
        ml.executeExpression("a=([4,2,3,4]>3)");
        boolean[][] r = {{true,false,false,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test002d() {
        ml.executeExpression("a=(3>[1,2,3,4;  2,4,3,4])");
        boolean[][] r = {{true,true,false,false},{true,false,false,false}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test002e() {
        ml.executeExpression("a=([3,1;  1,4]>[1,2;  3,4])");
        boolean[][] r =  {{true,false},{false,false}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }

    /************* <= ***************/
    public void test003a() {
        ml.executeExpression("a=(2<=3)");
        assertTrue(true == ml.getScalarValueBoolean("a"));
    }
    public void test003b() {
        ml.executeExpression("a=(3<=2)");
        assertTrue(false == ml.getScalarValueBoolean("a"));
    }
    public void test003bb() {
        ml.executeExpression("a=(3<=3)");
        assertTrue(true == ml.getScalarValueBoolean("a"));
    }
    public void test003c() {
        ml.executeExpression("a=([1,2,3,4]<=3)");
        boolean[][] r = {{true,true,true,false}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test003d() {
        ml.executeExpression("a=(3<=[1,2,3,4;  2,4,3,4])");
        boolean[][] r = {{false,false,true,true},{false,true,true,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test003e() {
        ml.executeExpression("a=([3,1;  1,4]<=[1,2;  3,4])");
        boolean[][] r =  {{false,true},{true,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }

    /************* >= ***************/
    public void test004a() {
        ml.executeExpression("a=(4>=3)");
        assertTrue(true == ml.getScalarValueBoolean("a"));
    }
    public void test004b() {
        ml.executeExpression("a=(1>=2)");
        assertTrue(false == ml.getScalarValueBoolean("a"));
    }
    public void test004bb() {
        ml.executeExpression("a=(2>=2)");
        assertTrue(true == ml.getScalarValueBoolean("a"));
    }
    public void test004c() {
        ml.executeExpression("a=([1,2,3,4]>=3)");
        boolean[][] r = {{false,false,true,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test004d() {
        ml.executeExpression("a=(3>=[1,2,3,4;  2,4,3,4])");
        boolean[][] r = {{true,true,true,false},{true,false,true,false}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test004e() {
        ml.executeExpression("a=([3,1;  1,4]>=[1,2;  3,4])");
        boolean[][] r =  {{true,false},{false,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }

    /************* == ***************/
    public void test005a() {
        ml.executeExpression("a=(3==3)");
        assertTrue(true == ml.getScalarValueBoolean("a"));
    }
    public void test005b() {
        ml.executeExpression("a=(3==2)");
        assertTrue(false == ml.getScalarValueBoolean("a"));
    }
    public void test005c() {
        ml.executeExpression("a=([1,2,3,4]==3)");
        boolean[][] r = {{false,false,true,false}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test005d() {
        ml.executeExpression("a=(3==[1,2,3,4;  2,4,3,4])");
        boolean[][] r =  {{false,false,true,false},{false,false,true,false}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test005e() {
        ml.executeExpression("a=([3,1;  3,4]==[1,2;  3,4])");
        boolean[][] r = {{false,false},{true,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }

    /************* != and ~= ***************/
    public void test006a() {
        ml.executeExpression("a=(2!=3)");
        assertTrue(true == ml.getScalarValueBoolean("a"));
    }
    public void test006b() {
        ml.executeExpression("a=(3!=3)");
        assertTrue(false == ml.getScalarValueBoolean("a"));
    }
    public void test006c() {
        ml.executeExpression("a=([1,2,3,4]!=3)");
        boolean[][] r = {{true,true,false,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test006d() {
        ml.executeExpression("a=(3!=[1,2,3,4;  2,4,3,4])");
        boolean[][] r = {{true,true,false,true},{true,true,false,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test006e() {
        ml.executeExpression("a=([3,1;  1,4]!=[1,2;  3,4])");
        boolean[][] r = {{true,true},{true,false}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }

    public void test006f() {
        ml.executeExpression("a=(2!=3)");
        assertTrue(true == ml.getScalarValueBoolean("a"));
    }
    public void test006g() {
        ml.executeExpression("a=(3!=3)");
        assertTrue(false == ml.getScalarValueBoolean("a"));
    }
    public void test006h() {
        ml.executeExpression("a=([1,2,3,4]!=3)");
        boolean[][] r = {{true,true,false,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test006i() {
        ml.executeExpression("a=(3!=[1,2,3,4;  2,4,3,4])");
        boolean[][] r = {{true,true,false,true},{true,true,false,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test006j() {
        ml.executeExpression("a=([3,1;  1,4]!=[1,2;  3,4])");
        boolean[][] r = {{true,true},{true,false}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }

    /************* | ***************/
    public void test007a() {
        ml.executeExpression("a=(0|3)");
        assertTrue(true == ml.getScalarValueBoolean("a"));
    }
    public void test007aa() {
        ml.executeExpression("a=(3|0)");
        assertTrue(true == ml.getScalarValueBoolean("a"));
    }
    public void test007b() {
        ml.executeExpression("a=(0|0)");
        assertTrue(false == ml.getScalarValueBoolean("a"));
    }
    public void test007c() {
        ml.executeExpression("a=([1,2,3,4]|0)");
        boolean[][] r =  {{true,true,true,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test007cc() {
        ml.executeExpression("a=([0,0,1,0]|0)");
        boolean[][] r = {{false,false,true,false}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test007e() {
        ml.executeExpression("a=([0,0;  1,4]|[1,0;  0,1])");
        boolean[][] r =  {{true,false},{true,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }

    /************* & ***************/
    public void test008a() {
        ml.executeExpression("a=(2&3)");
        assertTrue(true == ml.getScalarValueBoolean("a"));
    }
    public void test008b() {
        ml.executeExpression("a=(3&0)");
        assertTrue(false == ml.getScalarValueBoolean("a"));
    }
    public void test008c() {
        ml.executeExpression("a=([1,2,0,4]&3)");
        boolean[][] r = {{true,true,false,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test008d() {
        ml.executeExpression("a=(-4&[1,0,3,4;  0,0,3,4])");
        boolean[][] r =  {{true,false,true,true},{false,false,true,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test008dd() {
        ml.executeExpression("a=(0&[1,0,3,4;  0,0,3,4])");
        boolean[][] r =  {{false,false,false,false},{false,false,false,false}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
    public void test008e() {
        ml.executeExpression("a=([3,1;  0,5]&[0,0;  3,4])");
        boolean[][] r =  {{false,false},{false,true}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueBoolean("a")));
    }
					
    // *********** exceptions ************+
    public void test100() {
        // test if exception if thrown
        try 
        {
            ml.throwErrorsB = true;
            ml.executeExpression("a=([1,2]<[1,2,3,4])");
            ml.throwErrorsB = false;
        }
        catch (Exception e) 
        { 
            // test ok, no exception thrown
            return;
        }
        fail("failed");
    }
    
    public void test101() {
        // test if exception if thrown
        try 
        {
            ml.throwErrorsB = true;
            ml.executeExpression("a=(2<'hallo')");
            ml.throwErrorsB = false;
        }
        catch (Exception e) 
        { 
            // test ok, no exception thrown
            return;
        }
        fail("failed");
    }

}