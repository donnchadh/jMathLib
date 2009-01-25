package jmathlib.core.interpreter;

import jmathlib.core.tokens.*;

/**class for binding a variable name to some data*/
public class Variable extends RootObject
{
    /**variable name*/
    private String name;

    /**variable data*/
    private OperandToken data;

    /** local or global context */
    private boolean globalB = false;
    
    /**default constructor*/
    public Variable()
    {
        super();
        name = "";
        data = null;
    }

    /**constructor containing a variable name
     * @param _name*/
    public Variable(String _name)
    {
        super();
        name = _name;
        data = null;
    }

    /**constructor containing the variables name and data
     * @param _name
     * @param _data*/
    public Variable(String _name, OperandToken  _data)
    {
        super();
        name = _name;
		data = _data;

        //if(_data != null)
        //{
        //    OperandToken subTree = null;
        //    //if the item is an Expression node then just set the data value
        //    //without creating a new node
        //    if(_data instanceof Expression)
        //        subTree = ((Expression)_data);
        //    else if(_data instanceof OperandToken)
        //    	subTree = ((OperandToken)_data);
        //
        //    data = subTree;
        //}
    }

    /**Assign a new value to the variable
     * @param _data
     * @return
     */
    public OperandToken assign(OperandToken _data)
    {
        if (_data!=null)
            ErrorLogger.debugLine("Variable assign "+_data.toString());
        else
            ErrorLogger.debugLine("Variable assign NULL");
	   
        data = _data;
        
        return data;
    }

    /**implement the equals operator to find a variable with the correct name
    if _data is an instance of Variable then it compares the name of _data to the variable otherwise it calls the superclasses version*/
    public boolean equals(Object _data)
    {
		//ErrorLogger.debugLine("Variable equals "+_data.toString());
        boolean equal;
        //if(_data instanceof VariableToken)
        //    equal = (this.name.toUpperCase().equals(((VariableToken)_data).getName().toUpperCase()));
        //if(_data instanceof Variable)
        //    equal = (this.name.toUpperCase().equals(((Variable)_data).getName().toUpperCase()));
        //else
        //    equal = super.equals(_data);

        if(_data instanceof Variable)
            equal = this.name.equalsIgnoreCase(((Variable)_data).getName());
        else
            equal = super.equals(_data);

        return equal;
    }

    /**Converts the variable to a string.
     * @return
     */
    public String toString()
    {
        //Errors.throwMathLibException("Variable");
        if(data == null)
            return null;
        else
	       return data.toString();
    }
    
    /**Converts the variable to a string.*/
    public String toString(String[] operands)
    {
    	return toString();
    }

    /**Returns the variables name*/
    public String getName()
    {
    	return name;
    }
    
    /**Returns the variables data
     * @return
     */
    public OperandToken getData()
    {
    	return data;
    }
    
    /**
     * 
     * @return
     */
    public boolean isGlobal()
    {
        return globalB;
    }
    
    /**
     * 
     * @param globalB
     */
    public void setGlobal(boolean globalB)
    {
        this.globalB = globalB;
    }
}
