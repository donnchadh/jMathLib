package jmathlib.plugins.dynjava;

import jmathlib.plugins.Plugin;
import jmathlib.core.interpreter.*;
import java.io.*;

//classes for the java interpreter
import koala.dynamicjava.interpreter.*;
import koala.dynamicjava.parser.wrapper.*;


/*

java('console.displayText("hello world");')
hello world
ans = null

To access a JMathLib variable enter
x=5
JAVA('global.getVariable("x");')
ans = 5

*/

/**Class containing the extensions of jmathlib*/
public class JavaPlugin extends Plugin 
{
    
    private koala.dynamicjava.interpreter.TreeInterpreter djInterpreter;

    //private String name;
    
    public JavaPlugin()
    {

        name = "JavaPlugin";
        
        djInterpreter = new TreeInterpreter(new JavaCCParserFactory());

	    
	
    }

    public void init()
    {
	    djInterpreter.defineVariable("interpreter", getPluginsManager().getInterpreter());
        djInterpreter.defineVariable("global", getPluginsManager().getGlobalVariables());
	    djInterpreter.defineVariable("console", getPluginsManager().getInterpreter().getOutputPanel());
    
    
    }

    
    public koala.dynamicjava.interpreter.Interpreter getJavaInterpreter()
    {
        System.out.println("*******+ getJavaInter....");
	    return djInterpreter;
    }

    public String executeJavaExpression(String exp)
    {
	    StringReader reader = new StringReader(exp);
	    Object result = null;
        //System.out.println("*******+ executeJavaExp....");

	    try
	    {
	         result = djInterpreter.interpret(reader, "buffer");
	    }
	    catch(InterpreterException e)
	    {
	      ErrorLogger.debugLine("interpreter error " + e);
	    }

	    ErrorLogger.debugLine("java result = " + result);
	    return "" + result;
    }

      
}
