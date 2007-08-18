package jmathlib.core.interpreter;

//import MathLib.Tokens.*;

/**A context object contains the variables and code for the executing function*/
public class Context implements java.io.Serializable 
{
     /**Reference to the contexts variables*/
     private VariableList variables;
        
     /**Reference to the contexts calling context*/
     private Context parent;
    
     /**The name of the function that defined this context, used for diagnostics*/
     //private String functionName;

     /**Create a Context with an empty variable list, used to construct the global context*/        
     public Context()
     {
         variables    = new VariableList();
         parent       = null;
     }

     /**Create a Context with the supplied values
        @param _variables = the variable list of the new context
        @param _parent    = the calling context*/
     public Context(VariableList _variables, Context _parent)
     {
         variables    = _variables;
         parent       = _parent;
     }
        
     /**
      * 
      * @return
      */
     public Context getParent()
     {
         return parent;
     }

     /**
      * 
      * @param _parent
      */
     public void setParent(Context _parent)
     {
         parent = _parent;
     }

     /**
      * 
      * @return
      */
     public VariableList getVariables()
     {
         return variables;
     }

     /**
      * 
      */
     public Object clone()
     {
         VariableList _variables = null;
         if(variables != null)
             _variables = ((VariableList)variables.clone());

         //Context context = new Context(_variables, _code, null);
         Context context = new Context(_variables, null);
         //context.setFunctionName(functionName);
         return context;
     }

}
