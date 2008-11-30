package jmathlib.core.tokens;

import jmathlib.core.interpreter.*;
import jmathlib.core.tokens.numbertokens.*;
import java.util.*;

//import java.util.ArrayList;

/** */
public class MathLibObject extends DataToken
{
    /**An array of all the data stored in the structure*/
    private HashMap fields;
    
    /**Create a new structure*/
    public MathLibObject()
    {
        super(10, "struct"); 
        fields = new HashMap();
    }
    
    /**Create a structure and copy it's data from another structure
    @param oldVal = the structure to copy the values from*/
    public MathLibObject(MathLibObject oldVal)
    {
        super(10, "struct"); 
    	fields = ((HashMap)oldVal.getFieldsHash().clone());
    }

    /**Set the value of one of the structures fields
    @param fieldName = the name of the field to set
    @param value = the value to set the field to*/
    public void setField(String fieldName, OperandToken value)
    {
		 Variable var = ((Variable)fields.get(fieldName));
	
		 if(var == null)
		 {
		      var = new Variable(fieldName, value);
		      fields.put(fieldName, var);
		 }
		 var.assign(value);
    }

    /**Get the value of a particular field
    @param fieldName = the name of the field
    @return the fields data*/    
    public OperandToken getFieldData(String fieldName)
    {
		ErrorLogger.debugLine("getfield");
		Variable var = ((Variable)fields.get(fieldName));
		if(var != null)
		    return var.getData();   
	
	        return null;
    } 
    
    /**Get the value of a particular field
    @param fieldName = the name of the field
    @return a variable pointing to the fields data*/    
    public Variable getFieldVariable(String fieldName)
    {
    	Variable var = ((Variable)fields.get(fieldName));
        if(var != null)
        {
            ErrorLogger.debugLine("getting field data");
            return var;   
        }
        return null;
    }

    /**@return a list of all fields*/
    public Iterator getFields()
    {
        return fields.entrySet().iterator();
    }
    
    /**@return a list of all fields*/
    public HashMap getFieldsHash()
    {
        return fields;
    }

    /**Turns the structure into a string*/
    public String toString()
    {
        String result = "[";
		Set entries = fields.entrySet();
		Iterator iter = entries.iterator();
		while(iter.hasNext())
		{
		    Map.Entry entry = (Map.Entry)iter.next();
		    result += entry.getKey() + " = " + entry.getValue(); 
	
		    if(iter.hasNext())
			 result += ", ";
		}    
        
		result += "]";
        return result;
    }
    
    /**Evaluates the structure
    @return the structure itself*/
    public OperandToken evaluate(Token[] operands)
    {
        return this;
    }

    public OperandToken add(OperandToken arg)
    {
		 MathLibObject result = new MathLibObject(this);
	        
		 if(arg instanceof MathLibObject)
		 {
		      Iterator argFields = ((MathLibObject)arg).getFields();
            
            while(argFields.hasNext())
            {
                Variable var = ((Variable)((Map.Entry)argFields.next()).getValue());
                String fieldName = var.getName();
                
                OperandToken data = getFieldData(fieldName);
                
                if(data != null)
                {
                    result.setField(fieldName, data.add(var.getData()));
                }
                else
                {
                    result.setField(fieldName, var.getData());
                }
            }                
        }
        return result;
    }
    
    public OperandToken subtract(OperandToken arg)
    {
    	MathLibObject result = new MathLibObject(this);
        
    	if(arg instanceof MathLibObject)
    	{
    		Iterator argFields = ((MathLibObject)arg).getFields();
            
		      while(argFields.hasNext())
		      {
			   Variable var = ((Variable)((Map.Entry)argFields.next()).getValue());
			   String fieldName = var.getName();
			   
			   OperandToken data = getFieldData(fieldName);
	                
			   if(data != null)
			   {
				result.setField(fieldName, data.subtract(var.getData()));
			   }
			   else
			   {
				NumberToken temp = DoubleNumberToken.zero;
				result.setField(fieldName, temp.subtract(var.getData()));
			   }
		      }                
    	}
    	return result;
    }
    
    public OperandToken multiply(OperandToken arg)
    {
    	MathLibObject result = new MathLibObject(this);
        
    	 if(arg instanceof MathLibObject)
    	 {
    	      Iterator argFields = ((MathLibObject)arg).getFields();
                
    	      while(argFields.hasNext())
    	      {
    		   Variable var = ((Variable)((Map.Entry)argFields.next()).getValue());
    		   String fieldName = var.getName();
    		   
    		   OperandToken data = getFieldData(fieldName);
                    
    		   if(data != null)
    		   {
    			result.setField(fieldName, data.multiply(var.getData()));
    		   }
    		   else
    		   {
    			result.setField(fieldName, DoubleNumberToken.zero);
    		   }
    	      }                
    	 }
    	 return result;
    }

    public OperandToken divide(OperandToken arg)
    {
    	 MathLibObject result = new MathLibObject(this);
            
    	 if(arg instanceof MathLibObject)
    	 {
    	      Iterator argFields = ((MathLibObject)arg).getFields();
                
    	      while(argFields.hasNext())
    	      {
    		   Variable var = ((Variable)((Map.Entry)argFields.next()).getValue());
    		   String fieldName = var.getName();
    		   
    		   OperandToken data = getFieldData(fieldName);
                    
    		   if(data != null)
    		   {
    			result.setField(fieldName, data.divide(var.getData()));
    		   }
    		   else
    		   {
    			result.setField(fieldName, DoubleNumberToken.zero);
    		   }
    	      }                
    	 }
    	 return result;
    }

    public OperandToken power(OperandToken arg)
    {
    	 MathLibObject result = new MathLibObject(this);
            
    	 if(arg instanceof MathLibObject)
    	 {
    	      Iterator argFields = ((MathLibObject)arg).getFields();
                
    	      while(argFields.hasNext())
    	      {
    		   Variable var = ((Variable)((Map.Entry)argFields.next()).getValue());
    		   String fieldName = var.getName();
    		   
    		   OperandToken data = getFieldData(fieldName);
                    
    		   if(data != null)
    		   {
    			result.setField(fieldName, data.power(var.getData()));
    		   }
    		   else
    		   {
    			result.setField(fieldName, DoubleNumberToken.zero);
    		   }
    	      }                
    	 }
    	 return result;
    }
    
    //unary operations
    /**calculate the factorial
    @return the result as an OperandToken*/
    public OperandToken factorial()
    {
    	 MathLibObject result = new MathLibObject();
    	 Iterator fields = getFields();
                
    	 while(fields.hasNext())
    	 {
    	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
    	      String fieldName = var.getName();
    		   
    	      OperandToken data = var.getData();
                
    	      result.setField(fieldName, data.factorial());
    	 }                
         return result;
    }

    /**trigonometric functions - calculate the sine of this token
    @return the result as an OperandToken*/
 /*  public OperandToken sin()
    {
    	 MathLibObject result = new MathLibObject();
    	 Iterator fields = getFields();
                
    	 while(fields.hasNext())
    	 {
    	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
    	      String fieldName = var.getName();
    		   
    	      OperandToken data = var.getData();
                
    	      result.setField(fieldName, data.sin());
    	 }                
         return result;
    }*/

    /**trigonometric functions - calculate the cosine of this token
    @return the result as an OperandToken*/
/*    public OperandToken cos()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
	      result.setField(fieldName, data.cos());
	 }                
        return result;
    }*/

    /**trigonometric functions - calculate the tangent of this token
    @return the result as an OperandToken*/
    public OperandToken tan()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
//	      result.setField(fieldName, data.tan());
	 }                
        return result;
    }

    /**trigonometric functions - calculate the arc sine of this token
    @return the result as an OperandToken*/
    public OperandToken asin()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
//	      result.setField(fieldName, data.asin());
	 }                
        return result;
    }

    /**trigonometric functions - calculate the arc cosine of this token
    @return the result as an OperandToken*/
    public OperandToken acos()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
//	      result.setField(fieldName, data.acos());
	 }                
        return result;
    }

    /**trigonometric functions - calculate the arc tangent of this token
    @return the result as an OperandToken*/
    public OperandToken atan()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
//	      result.setField(fieldName, data.atan());
	 }                
        return result;
    }

	
	/**Trigonometric function - calculates the hyperbolic sine
    @return the result as an OperandToken*/   
    public OperandToken sinh()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
//	      result.setField(fieldName, data.sinh());
	 }                
        return result;
    }
    
	/**Trigonometric function - calculates the hyperbolic cosine
    @return the result as an OperandToken*/   
    public OperandToken cosh()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
//	      result.setField(fieldName, data.cosh());
	 }                
        return result;
	}

	/**Trigonometric function - calculates the hyperbolic tan
    @return the result as an OperandToken*/   
    public OperandToken tanh()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
//	      result.setField(fieldName, data.tanh());
	 }                
        return result;
	}
		
	/**Trigonometric function - calculates the inverse hyperbolic sine
    @return the result as an OperandToken*/   
  /*  public OperandToken asinh()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
	      result.setField(fieldName, data.asinh());
	 }                
        return result;
    }*/
	
    /**Trigonometric function - calculates the inverse hyperbolic cosine
       @return the result as an OperandToken*/   
    public OperandToken acosh()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
//	      result.setField(fieldName, data.acosh());
	 }                
        return result;
    }

    /**Trigonometric function - calculates the inverse hyperbolic tangent
       @return the result as an OperandToken*/   
    public OperandToken atanh()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
//	      result.setField(fieldName, data.atanh());
	 }                
        return result;
    }
    
	/**Standard functions - calculates the absolute value
    @return the result as an OperandToken*/
    public OperandToken abs()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
//	      result.setField(fieldName, data.abs());
	 }                
        return result;
    }

	/**Standard functions - calculates the exponent
    @return the result as an OperandToken*/
    public OperandToken exp()
    {
	 MathLibObject result = new MathLibObject();
	 Iterator fields = getFields();
            
	 while(fields.hasNext())
	 {
	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
	      String fieldName = var.getName();
		   
	      OperandToken data = var.getData();
            
//	      result.setField(fieldName, data.exp());
	 }                
        return result;
    }

    public OperandToken degreesToRadians()
    {
		 MathLibObject result = new MathLibObject();
		 Iterator fields = getFields();
	            
		 while(fields.hasNext())
		 {
		      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
		      String fieldName = var.getName();
			   
		      OperandToken data = var.getData();
	            
//		      result.setField(fieldName, data.degreesToRadians());
		 }                
	     return result;
	
    }

    public OperandToken radiansToDegrees()
    {
    	 MathLibObject result = new MathLibObject();
    	 Iterator fields = getFields();
                
    	 while(fields.hasNext())
    	 {
    	      Variable var = ((Variable)((Map.Entry)fields.next()).getValue());
    	      String fieldName = var.getName();
    		   
    	      OperandToken data = var.getData();
                
//    	      result.setField(fieldName, data.radiansToDegrees());
    	 }                
         return result;
    }
    
    
}
