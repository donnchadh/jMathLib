/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   
 * (c) 2009 stefan@held-mueller.de   
 */
package jmathlib.ui.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import jmathlib.core.interfaces.MathLibOutput;
import jmathlib.core.interpreter.*;

/**
 * 
 * @author stefan
 *
 */
public class JMathLibServlet extends HttpServlet implements MathLibOutput {

    private String dispText ="";

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");

	    String title = "JMathLib as a servlet";

	    out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");

    	// note that all links are created to be relative. this
    	// ensures that we can move the web application that this
    	// servlet belongs to to a different place in the url
    	// tree and not have any harmful side effects.


        //URL url = HelloWorldExample.class.getResourceAsStream("webFunctionsList.dat");
        InputStream in = JMathLibServlet.class.getResourceAsStream("webFunctionsList.dat");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        // read each line of the functions list
        String line = null;
        while ((line = br.readLine()) != null) 
        {
            System.out.println("read =" + line);
        }
        
        
        Interpreter jml = new Interpreter(false);
        jml.setOutputPanel(this);
        
        ErrorLogger.setDebug(true);
        
        jml.executeExpression("a='helloyou'");
        
        jml.executeExpression("aa=class(a)");
        
        jml.executeExpression("dir");

        //String c = 
        
        //ClassLoader.getSystemResourceAsStream(name)
        
        String s = jml.getString("a");
        out.println("<pre>"+s+"</pre>");

        String ss = jml.getString("aa");
        out.println("<pre>"+ss+"</pre>");

        out.println("<pre>"+dispText+"</pre>");
        dispText="";
        
        out.println("<h1>" + title + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }
    
    /**
     * @param text
     */
    public void displayText(String text)
    {
        dispText += text;
    } 
}



