package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testSubAssign extends TestCase {
	protected Interpreter ml;

    double[][] aRe = {{1.0, 2.0, 3.0, 4.0},{1.0, 2.0, 3.0, 4.0}};
    double[][] aIm = {{0.0, 0.0, 0.0, 0.0},{0.0, 0.0, 0.0, 0.0}};
	
    public testSubAssign(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	public static Test suite() {
		return new TestSuite(testSubAssign.class);
	}

	protected void setUp() {
		ml = new Interpreter(true);
        ml.setArray("a", aRe, aIm);
	}


    /****** subassign() ******************************************************/
	public void testSubAssign01() {
        ml.executeExpression("b=subassign([2],[3],1,1)");
		assertTrue(3 == ml.getScalarValueRe("b"));
	}
	public void testSubAssign02() {
        ml.executeExpression("b=subassign([2.0, 3.0, 4.0], 6.0, 1 ,2)");
   	    double[][] bRe = {{2.0, 6.0, 4.0}};
		assertTrue(Compare.ArrayEquals(bRe,  ml.getArrayValueRe("b")));
	}
   	public void testSubAssign04() {
        ml.executeExpression("b=subassign(a,[5,5,5,5],2,1:4)");
   	    double[][] bRe = {{1.0, 2.0, 3.0, 4.0},{5.0, 5.0, 5.0, 5.0}};
		assertTrue(Compare.ArrayEquals(bRe,  ml.getArrayValueRe("b")));
	}
	public void testSubAssign05() {
        ml.executeExpression("b=subassign([2, 3, 4; 5, 6, 7],[8,8]', 1:2, 3)");
	    double[][] bRe = {{2.0, 3.0, 8.0},
                          {5.0, 6.0, 8.0}};
		assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("b")));
    }
    public void testSubAssign06() {
        ml.executeExpression("a=subassign(a,[8,8;9,9],1:2, 1:2)");
        double[][] bRe = {{8.0, 8.0, 3.0, 4.0},
                          {9.0, 9.0, 3.0, 4.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign06a() {
        ml.executeExpression("a=subassign(a,[8,8;9,9], :, 3:4)");
   	    double[][] bRe = {{1.0, 2.0, 8.0, 8.0},
                          {1.0, 2.0, 9.0, 9.0}};
		assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign06b() {
        ml.executeExpression("a=subassign(a,[8,8;9,9], :, 2:3)");
        double[][] bRe = {{1.0, 8.0, 8.0, 4.0},
                          {1.0, 9.0, 9.0, 4.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign06c() {
        ml.executeExpression("a=subassign(a,[8,8;9,9], :, 1:2)");
        double[][] bRe = {{8.0, 8.0, 3.0, 4.0},
                          {9.0, 9.0, 3.0, 4.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign06d() {
        ml.executeExpression("a=[1,2,3,4; 5,6,7,8; 1,2,3,4]");
        ml.executeExpression("a=subassign(a,[8,8;9,9], 2:3, 2:3)");
        double[][] bRe = {{1.0, 2.0, 3.0, 4.0},
                          {5.0, 8.0, 8.0, 8.0},
                          {1.0, 9.0, 9.0, 4.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign06e() {
        ml.executeExpression("a=[1,2,3,4; 5,6,7,8; 1,2,3,4]");
        ml.executeExpression("a=subassign(a,[8,8;9,9], 1:2, 2:3)");
        double[][] bRe = {{1.0, 8.0, 8.0, 4.0},
                          {5.0, 9.0, 9.0, 8.0},
                          {1.0, 2.0, 3.0, 4.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign06f() {
        ml.executeExpression("a=[1,2,3,4; 5,6,7,8; 1,2,3,4]");
        ml.executeExpression("a=subassign(a,[8,8;9,9], 1:2, 3:4)");
        double[][] bRe = {{1.0, 2.0, 8.0, 8.0},
                          {5.0, 6.0, 9.0, 9.0},
                          {1.0, 2.0, 3.0, 4.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign06g() {
        ml.executeExpression("a=[1,2,3,4; 5,6,7,8; 1,2,3,4]");
        ml.executeExpression("a=subassign(a,[8,8;9,9], 2:3, 1:2)");
        double[][] bRe = {{1.0, 2.0, 3.0, 4.0},
                          {8.0, 8.0, 7.0, 8.0},
                          {9.0, 9.0, 3.0, 4.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    
    // some bug in subassign or AssignOperatorToken
    //   due to the bug, the example below returned b(1,2)=5 instead of 999
    public void testSubAssign07() {
        ml.executeExpression("b=[2,3,4;5,6,7]");
        ml.executeExpression("b(1,2)=999");
        ml.executeExpression("c=b(1,2)");
        assertTrue( ml.getScalarValueRe("c") == 999);
    }
    public void testSubAssign08() {
        ml.executeExpression("b=[2,3,4;5,6,7]");
        ml.executeExpression("d=subassign(b,999,1,2)");
        ml.executeExpression("c=d(1,2)");
        assertTrue( ml.getScalarValueRe("c") == 999);
    }
 
    public void testSubAssign09() {
        ml.executeExpression("b=[2,3,4;5,6,7]");
        ml.executeExpression("b(3)=55");
        ml.executeExpression("c=b(1,2)");
        assertTrue( ml.getScalarValueRe("c") == 55);
    }
    
    public void testSubAssign10() {
        ml.executeExpression("b=[2,3,4;5,6,7]");
        ml.executeExpression("b(2:4)=[55,66,77]");
        ml.executeExpression("c=b(2,1)");
        assertTrue( ml.getScalarValueRe("c") == 55);
        ml.executeExpression("c=b(1,2)");
        assertTrue( ml.getScalarValueRe("c") == 66);
        ml.executeExpression("c=b(2,2)");
        assertTrue( ml.getScalarValueRe("c") == 77);
    }

    public void testSubAssign11() {
        ml.executeExpression("b=[2,3,4;5,6,7]");
        ml.executeExpression("b(2:4)=[22,33,44]'");
        ml.executeExpression("c=b(2,1)");
        assertTrue( ml.getScalarValueRe("c") == 22);
        ml.executeExpression("c=b(1,2)");
        assertTrue( ml.getScalarValueRe("c") == 33);
        ml.executeExpression("c=b(2,2)");
        assertTrue( ml.getScalarValueRe("c") == 44);
    }

    public void testSubAssign12() {
        ml.executeExpression("b=[2,3,4;5,6,7]");
        ml.executeExpression("b(2:5)=[222,333,444,555]'");
        ml.executeExpression("c=b(2,1)");
        assertTrue( ml.getScalarValueRe("c") == 222);
        ml.executeExpression("c=b(1,2)");
        assertTrue( ml.getScalarValueRe("c") == 333);
        ml.executeExpression("c=b(2,2)");
        assertTrue( ml.getScalarValueRe("c") == 444);
        ml.executeExpression("c=b(1,3)");
        assertTrue( ml.getScalarValueRe("c") == 555);
    }

    public void testSubAssign13() {
        ml.executeExpression("b=[2,3,4;5,6,7]");
        ml.executeExpression("b(2:5)=[222,333;444,555]");
        ml.executeExpression("c=b(2,1)");
        assertTrue( ml.getScalarValueRe("c") == 222);
        ml.executeExpression("c=b(1,2)");
        assertTrue( ml.getScalarValueRe("c") == 444);
        ml.executeExpression("c=b(2,2)");
        assertTrue( ml.getScalarValueRe("c") == 333);
        ml.executeExpression("c=b(1,3)");
        assertTrue( ml.getScalarValueRe("c") == 555);
    }

    public void testSubAssign14() {
        ml.executeExpression("a=[1,2,3; 7,8,9]");
        ml.executeExpression("a=subassign(a,[8;9], :, 1)");
        double[][] bRe = {{8.0, 2.0, 3.0},
                          {9.0, 8.0, 9.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign15() {
        ml.executeExpression("a=[1,2,3; 7,8,9]");
        ml.executeExpression("a=subassign(a,[8;9], :, 2)");
        double[][] bRe = {{1.0, 8.0, 3.0},
                          {7.0, 9.0, 9.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign16() {
        ml.executeExpression("a=[1,2,3; 7,8,9]");
        ml.executeExpression("a=subassign(a,[33,44,55;66,77,88], :, :)");
        double[][] bRe = {{33.0, 44.0, 55.0},
                          {66.0, 77.0, 88.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }


    public void testSubAssign100() {
        ml.executeExpression("a=[1,2,3,7,8,9]");
        ml.executeExpression("a(8)=88");
        double[][] bRe = {{1.0, 2.0, 3.0, 7.0, 8.0, 9.0, 0.0, 88.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign101() {
        ml.executeExpression("a=[1,2,3,7,8,9]");
        ml.executeExpression("a(3)=88");
        double[][] bRe = {{1.0, 2.0, 88.0, 7.0, 8.0, 9.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign102() {
        ml.executeExpression("a=[1,2,3,7,8,9]");
        ml.executeExpression("a(8:10)=[77, 88, 99]");
        double[][] bRe = {{1.0, 2.0, 3.0, 7.0, 8.0, 9.0, 0.0, 77.0, 88.0, 99.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }

    public void testSubAssign110() {
        ml.executeExpression("a=[1;2;3;7;8;9]");
        ml.executeExpression("a(8)=99");
        double[][] bRe = {{1.0},{2.0},{3.0},{7.0},{8.0},{9.0},{0.0},{99.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign111() {
        ml.executeExpression("a=[1;2;3;7;8;9]");
        ml.executeExpression("a(3)=88");
        double[][] bRe = {{1.0},{2.0},{88.0},{7.0},{8.0},{9.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign112() {
        ml.executeExpression("a=[1,2,3,7,8,9]'");
        ml.executeExpression("a(8:10)=[66, 88, 99]'");
        double[][] bRe = {{1.0},{2.0},{3.0},{7.0},{8.0},{9.0},{0.0},{66.0},{88.0},{99.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }

    public void testSubAssign120() {
        ml.executeExpression("a=[1,2;3,4]");
        ml.executeExpression("a(1,3)=99");
        double[][] bRe = {{1.0, 2.0, 99.0},{3.0, 4.0, 0.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }
    public void testSubAssign121() {
        ml.executeExpression("a=[1,2;3,4]");
        ml.executeExpression("a(3:4,3:4)=[5.0, 6.0; 7.0, 8.0]");
        double[][] bRe = {{1.0, 2.0, 0.0, 0.0},
                          {3.0, 4.0, 0.0, 0.0},
                          {0.0, 0.0, 5.0, 6.0},
                          {0.0, 0.0, 7.0, 8.0} };
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }

    public void testSubAssign130() {
        ml.executeExpression("clear(aaaa)");
        ml.executeExpression("aaaa(1,3)=99");
        double[][] bRe = {{0.0, 0.0, 99.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("aaaa")));
    }
    public void testSubAssign131() {
        ml.executeExpression("clear(aaaa)");
        ml.executeExpression("aaaa(3)=999");
        double[][] bRe = {{0.0, 0.0, 999.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("aaaa")));
    }
    public void testSubAssign133() {
        ml.executeExpression("bbb=[]");
        ml.executeExpression("bbb(4)=789");
        double[][] bRe = {{0.0, 0.0, 0.0, 789.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("bbb")));
    }
    public void testSubAssign134() {
        ml.executeExpression("bbb=subassign([],[2,3,4,5],1:4)");
        double[][] bRe = {{2.0, 3.0, 4.0, 5.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("bbb")));
    }
    public void testSubAssign135() {
        ml.executeExpression("bbb=subassign([],[2,3,4,5],3:6)");
        double[][] bRe = {{0.0, 0.0, 2.0, 3.0, 4.0, 5.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("bbb")));
    }
    public void testSubAssign136() {
        ml.executeExpression("bbb=subassign([],2,5)");
        double[][] bRe = {{0.0, 0.0, 0.0, 0.0, 2.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("bbb")));
    }
    public void testSubAssign137() {
        ml.executeExpression("bbb=subassign([],4,2,5)");
        double[][] bRe = {{0.0, 0.0, 0.0, 0.0, 0.0},
                          {0.0, 0.0, 0.0, 0.0, 4.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("bbb")));
    }

    public void testSubAssign150() {
        ml.executeExpression("b=[0;0]");
        ml.executeExpression("b(1,1+4)=555");
        double[][] bRe = {{0.0, 0.0, 0.0, 0.0, 555.0},
                          {0.0, 0.0, 0.0, 0.0,   0.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("b")));
    }
    public void testSubAssign151() {
        ml.executeExpression("b=[0;0];n=2");
        ml.executeExpression("b(1,n)=444");
        double[][] bRe = {{0.0, 444.0},
                          {0.0,   0.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("b")));
        ml.executeExpression("n=n+1");
        ml.executeExpression("b(1,n)=333");
        double[][] cRe = {{0.0, 444.0, 333.0},
                          {0.0,   0.0,   0.0}};
        assertTrue(Compare.ArrayEquals(cRe, ml.getArrayValueRe("b")));
    }

    public void testSubAssign160() {
        ml.executeExpression("b=[22,33,44,55;66,77,88,99]");
        ml.executeExpression("b([2,6,3])=11");
        double[][] bRe = {{22.0, 11.0, 44.0, 55.0},
                          {11.0, 77.0, 11.0, 99.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("b")));
    }
    public void testSubAssign161() {
        ml.executeExpression("b=[22,33,44,55;66,77,88,99]");
        ml.executeExpression("b([2,3,8])=23");
        double[][] bRe = {{22.0, 23.0, 44.0, 55.0},
                          {23.0, 77.0, 88.0, 23.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("b")));
    }
    public void testSubAssign162() {
        ml.executeExpression("b=[22,33,44,55;66,77,88,99]");
        ml.executeExpression("b([1,2],[1,2,4])=55");
        double[][] bRe = {{55.0, 55.0, 44.0, 55.0},
                          {55.0, 55.0, 88.0, 55.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("b")));
    }
    public void testSubAssign163() {
        ml.executeExpression("b=[22,33,44,55;66,77,88,99]");
        ml.executeExpression("b([1,1],[3])=79.0");
        double[][] bRe = {{22.0, 33.0, 79.0, 55.0},
                          {66.0, 77.0, 88.0, 99.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("b")));
    }
    public void testSubAssign164() {
        ml.executeExpression("b=[22,33,44,55;66,77,88,99]");
        ml.executeExpression("b([1,1],[3,6])=79.0");
        double[][] bRe = {{22.0, 33.0, 79.0, 55.0, 00.0, 79.0},
                          {66.0, 77.0, 88.0, 99.0, 00.0, 00.0}};
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("b")));
    }
    
}