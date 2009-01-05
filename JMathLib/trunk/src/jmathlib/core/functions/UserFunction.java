package jmathlib.core.functions;

import jmathlib.core.interpreter.*;
import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;

import java.util.ArrayList;

/**Class for storing user defines*/
public class UserFunction extends Function
{
    
    /** The code of the current m-function */
    private OperandToken code;
    
    /**The names of the parameter values*/
    private ArrayList parameterVariables;

    /**The names of the return values*/
    private ArrayList returnVariables;

    /**true if this is a M-Script (not a M-function) */
    private boolean mScriptB = false;

    /**Creates a user function */	
    public UserFunction() 
    {
        parameterVariables = new ArrayList();
        returnVariables    = new ArrayList();
    }
    
    /**Executes a user function
       @param operands - the array of parameters
       @return the result of the function as an OperandToken*/	
    public OperandToken evaluate(Token[] operands)
    {
        Context      functionContext;     // The context of the function
        OperandToken result     = null;
        boolean      vararginB  = false;
        boolean      varargoutB = false;
        
        if (mScriptB)
        {
            // evaluate m-script
            if (code!=null)
                result = code.evaluate(null);
        }
        else
        {
            // evaluate m-function

            // m-functions have a local context (local variables)
            VariableList localVariables = new VariableList();
            functionContext = getContextList().createContext(localVariables);

            //if nescessary create a context and store on list
	        //if(activeContext == null)
	        //    activeContext = ((Context)functionContext.clone());
	
	        //getContextList().pushContext(activeContext);
	        
	        //set the variable NARGIN to the number of arguments
	        int opLength = 0;
	        if (operands!=null)
	            opLength = operands.length;
	        
            // check for "varargin" as last input parameter
            // e.g. function x=barfoo(a,b,c,varargin)
            if (parameterVariables.size()>0)
            {
                if ( ((String)parameterVariables.get(parameterVariables.size()-1)).equals("varargin") )
                {
                    vararginB = true;
                    ErrorLogger.debugLine("UserF: varargin found");   
                }
            }                
            
            // check for "varargout" as last input parameter
            // e.g. function [x,y,varargout]=barfoo(a,b,c)
            if (returnVariables.size()>0)
            {
                if ( ((String)returnVariables.get(returnVariables.size()-1)).equals("varargout") )
                {
                    varargoutB = true;
                    ErrorLogger.debugLine("UserF: varargout found");   
                }
            }
            
	        // check if number of parameters inside the function is equal to the number
	        //    of calling expression  
	        // e.g. plot(x,y,z)  
	        //      function [...]=plot(x,y,z)
	        if ( (parameterVariables.size() < opLength) && !vararginB )
	            Errors.throwMathLibException("UserFunction: "+name+" number of parameters to large"); 
	        
	        // set the variable NARGIN to the number of parameters of the calling function
	        createVariable("nargin").assign(new DoubleNumberToken(opLength));

	        // set the variable NARGOUT to the number of return values
	        createVariable("nargout").assign(new DoubleNumberToken(returnVariables.size()));
	
	
            //set the input parameters for the function
	        // e.g. function x=barfoo(x,y,z)
            // e.g. function x=barfoo(x,y,z,varargin)
            if (!vararginB)
            {
                // e.g. function =bar(a,b,c,d,e)
    	        for(int paramNo = 0; paramNo < opLength; paramNo++)
    	        {
    	            String parameterName = (String)parameterVariables.get(paramNo);
    		        //System.out.println("UserFunction: "+parameterName);
    	            createVariable(parameterName).assign((OperandToken)operands[paramNo]);
    	        }
            }
            else
            {
                //e.g. function =bar(a,b,c,varargin)
                int parN         = parameterVariables.size();
                int remainingOps = opLength - (parN - 1);

                // copy parameters, but not "varargin"  (copy parameters 0 ... n-1)
                for(int paramNo = 0; paramNo < Math.min((parN - 1), opLength); paramNo++)
                {
                    String parameterName = (String)parameterVariables.get(paramNo);
                    ErrorLogger.debugLine("UserF: params: "+parameterName);
                    createVariable(parameterName).assign((OperandToken)operands[paramNo]);
                }
                
                ErrorLogger.debugLine("UserF: remainingOps: "+ remainingOps);
                

                // copy remaining operands into cell array, but only if operands are left
                // e.g. function x=barfoo(a,b,c,d,varargin)   
                //    with barfoo(1,2,3) will have varargin==null;
                if (remainingOps >0)
                {
                    OperandToken[][] values       = new OperandToken[remainingOps][1];
                    for (int i=0; i<remainingOps; i++)
                    {
                        values[i][0] = (OperandToken)operands[parameterVariables.size()-1+i];
                    }
                    CellArrayToken cell = new CellArrayToken(values);
                    createVariable("varargin").assign(cell);
                }
                else
                {
                    // varargin is empty
                    CellArrayToken cell = new CellArrayToken();
                    createVariable("varargin").assign(cell);
                }
            }
            
	        
            // execute m-function
	        try
	        {
	            // must clone function code, so that the original code remains untouched
	            OperandToken codeLocal = (OperandToken)code.clone();
	            result = codeLocal.evaluate(null);
	            
	            
	            // result should be DoubleNumberToken e.g. 1+2             ->  >3<  
	            // or a MatrixToken             e.g. [x,y]=foo(2,4)  ->  [x,y]
	            if(returnVariables.size() == 1)
	            {
	                String name  = (String)returnVariables.get(0);
	                //System.out.println("UserFunction: returnVariable "+name);
	                //Variable var = (Variable)getVariables().getVariable(name);
                    Variable var = getVariable(name);
	                result       = var.getData();
	            }
	            else if (returnVariables.size() > 1)
	            {
	                
	                // for more than one return argument, return a matrix of operands
	                // e.g.  function [t,y]=foo(...)
	                OperandToken[][] values = new OperandToken[1][returnVariables.size()];           

	                for(int i = 0; i < returnVariables.size(); i++)
	                {
	                      String name  = (String)returnVariables.get(i);
	                      //Variable retVar = ((Variable)getVariables().getVariable(name));
                          Variable retVar = getVariable(name);
	                      // check if return variable has been used before
	                      if (retVar != null)
	                      	  values[0][i] = retVar.getData();
	                      else
	                      	  values[0][i] = null;
	                }
	                    
	                result = new MatrixToken(values);
	            }        
	                
            
	            //reset to the previous variable frame
	            getContextList().popContext();
	        }
	        catch(ControlException e)
	        {
                    // assign return values
	                getContextList().popContext();
	                //activeContext = null;
	        }
            catch (Exception e)
            {
                getContextList().popContext();
                throwMathLibException(e.getMessage());
            }

        }
        
        
         
        return result;
    }
    
    /**Tests if an object is equal to this function
       if obj is an instance of Function or Function token then it
       compares the name of obj to the functions name otherwise it
       calls the superclasses version
       @param obj = object to test
       @return true if the objects are equal*/
    public boolean equals(Object obj)
    {
        boolean equal = false;

        if(obj instanceof Function)
        {
            equal = ((Function)obj).getName().toUpperCase().equals(name.toUpperCase());
        }
        else if(obj instanceof FunctionToken)
        {
            equal = ((FunctionToken)obj).getName().toUpperCase().equals(name.toUpperCase());
        }
        else
            equal = super.equals(obj);
        return equal;
    }
	
	/**Returns the number of return variables of this function
		@return returnCount = number of return variables */
    //public int getReturnCount()
    //{
    //    return returnVariables.size();
    //}
	
    /**Returns the number of parameters of this function
       @return parameterCount = number of parameters */
    //public int getParameterCount()
    //{
    //    return paramaterNames.size();
    //}

    public void setParameterVariables(ArrayList _parameterVariables)
    {
        //getLocalVariables().createVariable(name);
        parameterVariables = _parameterVariables;
    }

    public void setReturnVariables(ArrayList _returnVariables)
    {
        //getLocalVariables().createVariable(name);
        returnVariables = _returnVariables;
    }

    /**Sets the number of parameters of this function
       @param parameterCount = number of parameters */
    //public void setParameterCount(int _parameterCount)
    //{
    //    //parameterCount = _parameterCount;
    //}
	
    /**Returns the name of this function
       @return name = name of this function */
    //public String getName()
    //{
    //    return name;
    //}
	
    /**Sets the name of this function
       @param name = name of this function */
    //public void setName(String _name)
    //{
    //    name = _name;
    //}	
	
    /**Sets the parsed code as a token tree
       @param code = parsed code of this function */
    public void setCode(OperandToken _code)
    {
        code = _code;
    }

    /**Returns the local variables
       @return localVariables = local variables of this function  */
    //public VariableList getLocalVariables()
    //{
    //    //return functionContext.getVariables();
    //    return getVariables();
    //}	        
    
/*    public void setFunctionParameters(Token[] operands)
    {
        //set the parameters for the function
        int noParameters =0;
        if (operands!=null) noParameters = operands.length;

        int parameterCount = getParameterCount();

        if(noParameters < parameterCount)
        {
            Errors.throwMathLibException(ERR_INSUFFICIENT_PARAMETERS, new Object[] {new DoubleNumberToken(parameterCount)});
        }
        else if(noParameters > parameterCount)
        {
            //set last param to be a list of parameters
            OperandToken[][] values = new OperandToken[1][noParameters - parameterCount + 1];           

            for(int index = parameterCount - 1; index < noParameters; index++)
            {
                values[0][index - parameterCount + 1] = (OperandToken)operands[index];
            }
            
            operands[parameterCount - 1] = new MatrixToken(values);
        }
        
        for(int paramNo = 0; paramNo < parameterCount; paramNo++)
        {
            getVariables().setVariable(((String)parameterVariables.get(paramNo)), (OperandToken)operands[paramNo]);
        }
    }
*/
    
    //public OperandToken getFunctionResult()
    //{
    //    OperandToken result = null;
    //    int returnCount = getReturnCount();

    //    //acess the return values for the function
    //    if(returnCount == 1)
    //    {
    //        String name = ((String)returnNames.get(0));
    //        result = ((Variable)getVariables().getVariable(name)).getData();
    //    }
    //    else
    //    {
    //        OperandToken[][] values = new OperandToken[1][returnCount];           

    //        for(int index = 0; index < returnCount; index++)
    //        {
    //            String name = ((String)returnNames.get(index));
    //            values[0][index] = ((Variable)getVariables().getVariable(name)).getData();
    //        }
            
    //        result = new MatrixToken(values);
    //    }        
        
    //    return result;
    //}
    
    /** set true if this is a m-script file and not a function */
    public void setScript(boolean _mScriptB)
    {
        mScriptB = _mScriptB;
    }
    
    /**  returns true if this is a m-script file */
    public boolean isScript()
    {
        return mScriptB;
    }
      
}
