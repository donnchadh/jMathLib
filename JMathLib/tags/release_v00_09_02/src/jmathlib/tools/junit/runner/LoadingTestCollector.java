package jmathlib.tools.junit.runner;

import java.lang.reflect.*;
import jmathlib.tools.junit.framework.*;

/**
 * An implementation of a TestCollector that loads
 * all classes on the class path and tests whether
 * it is assignable from Test or provides a static suite method.
 * @see TestCollector
 */
public class LoadingTestCollector extends ClassPathTestCollector {
	
	TestCaseClassLoader fLoader;
	
	public LoadingTestCollector() {
		fLoader= new TestCaseClassLoader();
	}
	
	protected boolean isTestClass(String classFileName) {	
		try {
			if (classFileName.endsWith(".class")) {
				Class testClass= classFromFile(classFileName);
				return (testClass != null) && isTestClass(testClass);
			}
		} 
		catch (ClassNotFoundException expected) {
		}
		catch (NoClassDefFoundError notFatal) {
		} 
		return false;
	}
	
	Class classFromFile(String classFileName) throws ClassNotFoundException {
		String className= classNameFromFile(classFileName);
		if (!fLoader.isExcluded(className))
			return fLoader.loadClass(className, false);
		return null;
	}
	
	boolean isTestClass(Class testClass) {
		if (hasSuiteMethod(testClass))
			return true;
		if (Test.class.isAssignableFrom(testClass) &&
			Modifier.isPublic(testClass.getModifiers()) &&
			hasPublicConstructor(testClass)) 
			return true;
		return false;
	}
	
	boolean hasSuiteMethod(Class testClass) {
		try {
			Method suiteMethod= testClass.getMethod(BaseTestRunner.SUITE_METHODNAME, new Class[0]);
	 	} catch(Exception e) {
	 		return false;
		}
		return true;
	}
	
	boolean hasPublicConstructor(Class testClass) {
		Class[] args= { String.class };
		Constructor c= null;
		try {
			c= testClass.getConstructor(args);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

}