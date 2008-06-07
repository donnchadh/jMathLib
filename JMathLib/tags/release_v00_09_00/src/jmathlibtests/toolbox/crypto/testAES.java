package jmathlibtests.toolbox.crypto;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testAES extends TestCase {
	protected Interpreter ml;
	
    public testAES(String name) {
		super(name);
	}
	public static void main (String[] args) {
	    jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testAES.class);
	}

    /****** AES() ***********************************************************/
	public void testAES01() {
        ml.executeExpression(" k=double(\"0987098709870987\") ");  // key
        ml.executeExpression(" a=double(\"asdfasdfasdfasdf\") ");  // plain text
        ml.executeExpression(" b=aes(\"e\",a,k) ");  // encrypt
        ml.executeExpression(" c=aes(\"d\",b,k) ");  // decrypt
        ml.executeExpression(" cc = char(c)  ");  // convert back to string
        ml.executeExpression("");
        ml.executeExpression("");
		assertTrue(ml.getString("cc").equals("asdfasdfasdfasdf"));
	}

    public void testAES02() {
        ml.executeExpression(" k=double(\"0987098709870987\"); ");  // key
        ml.executeExpression(" a=double(\"bbbbbbdfasdfasdf\"); ");  // plain text
        ml.executeExpression(" b=aes(\"encrypt\",a,k) ");  // encrypt
        ml.executeExpression(" c=aes(\"d\",b,k) ");  // decrypt
        ml.executeExpression(" cc = char(c)  ");  // convert back to string
        ml.executeExpression("");
        ml.executeExpression("");
        assertTrue(ml.getString("cc").equals("bbbbbbdfasdfasdf"));
    }

    public void testAES03() {
        ml.executeExpression(" k=double(\"0987098709870987\") ");  // key
        ml.executeExpression(" a=double(\"XXXbbbdfasdfasdf\") ");  // plain text
        ml.executeExpression(" b=aes(\"encrypt\",a,k) ");  // encrypt
        ml.executeExpression(" c=aes(\"decrypt\",b,k) ");  // decrypt
        ml.executeExpression(" cc = char(c)  ");  // convert back to string
        ml.executeExpression("");
        ml.executeExpression("");
        assertTrue(ml.getString("cc").equals("XXXbbbdfasdfasdf"));
    }

    public void testAES04() {
        
        double[][] input2  = {{0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77,
                               0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff}};
        double[][] key2    = {{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                               0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f}};
        double[][] output2 = {{0x69, 0xc4, 0xe0, 0xd8, 0x6a, 0x7b, 0x04, 0x30,
                               0xd8, 0xcd, 0xb7, 0x80, 0x70, 0xb4, 0xc5, 0x5a}};
        
        ml.setArray("in2", input2,null);
        ml.setArray("key2",key2  ,null);
        ml.executeExpression(" out2=aes(\"encrypt\",in2,key2) ");  // encrypt
        assertTrue(Compare.ArrayEquals(output2, ml.getArrayValueRe("out2")));
    }

    public void testAES05() {
        // this is the test for 128bit AES from the FIPS197 specification
        double[][] input  = {{0x32, 0x43, 0xf6, 0xa8, 0x88, 0x5a, 0x30, 0x8d,
                              0x31, 0x31, 0x98, 0xa2, 0xe0, 0x37, 0x07, 0x34}};
        double[][] key    = {{0x2b, 0x7e, 0x15, 0x16, 0x28, 0xae, 0xd2, 0xa6,
                              0xab, 0xf7, 0x15, 0x88, 0x09, 0xcf, 0x4f, 0x3c}};
        double[][] output = {{0x39, 0x25, 0x84, 0x1d, 0x02, 0xdc, 0x09, 0xfb,
                              0xdc, 0x11, 0x85, 0x97, 0x19, 0x6a, 0x0b, 0x32}};
        
        ml.setArray("in", input,null);
        ml.setArray("key",key  ,null);
        ml.executeExpression("in");
        ml.executeExpression(" out=aes(\"encrypt\",in,key) ");  // encrypt
        assertTrue(Compare.ArrayEquals(output, ml.getArrayValueRe("out")));
    }

    public void testAES06() {
        //    PT=93385C1F2AEC8BED192F5A8E161DD508
        //    KEY=78797A7B7D7E7F80828384858788898A
        //    CT=F29E986C6A1C27D7B29FFD7EE92B75F1
        double[][] input  = {{0x93, 0x38, 0x5C, 0x1F, 0x2A, 0xEC, 0x8B, 0xED,
                              0x19, 0x2F, 0x5A, 0x8E, 0x16, 0x1D, 0xD5, 0x08}};
        double[][] key    = {{0x78, 0x79, 0x7A, 0x7B, 0x7D, 0x7E, 0x7F, 0x80, 
                              0x82, 0x83, 0x84, 0x85, 0x87, 0x88, 0x89, 0x8A}};
        double[][] output = {{0xF2, 0x9E, 0x98, 0x6C, 0x6A, 0x1C, 0x27, 0xD7, 
                              0xB2, 0x9F, 0xFD, 0x7E, 0xE9, 0x2B, 0x75, 0xF1}};
        
        ml.setArray("in3", input,null);
        ml.setArray("key3",key  ,null);
        ml.executeExpression(" out3=aes(\"encrypt\",in3,key3) ");  // encrypt
        assertTrue(Compare.ArrayEquals(output, ml.getArrayValueRe("out3")));
    }
 
    
    // different keys
    public void testAES100() {
        ml.executeExpression(" k=double(\"0987098709870987\") ");  // key
        ml.executeExpression(" a=double(\"XXXbbbdfasdfasdf\") ");  // plain text
        ml.executeExpression(" b=aes(\"encrypt\",a,k) ");  // encrypt

        ml.executeExpression(" k=double(\"1111111111111111\") ");  // different key
        ml.executeExpression(" c=aes(\"decrypt\",b,k) ");  // decrypt
        ml.executeExpression(" cc = char(c)  ");  // convert back to string
        ml.executeExpression("");
        ml.executeExpression("");
        assertTrue( !ml.getString("cc").equals("XXXbbbdfasdfasdf"));
    }

}