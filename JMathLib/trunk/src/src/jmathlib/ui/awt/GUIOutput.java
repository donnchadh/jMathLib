package jmathlib.ui.awt;

import java.io.OutputStream;
import java.io.PrintStream;
import java.awt.TextArea;

/**Simple OutputStream extension for redirecting the System.out to a specified
 * TextArea.*/
public class GUIOutput extends OutputStream
{
	private static PrintStream redirectedOut;
	private static TextArea outputTextArea;
	private static PrintStream oldPrintStream;

	/**<ol><li>Creates a new <code>PrintStream</code> object to redirect the standard <code>System.out</code> object</li><li>Does the redirection</li></ol>*/
	public GUIOutput(TextArea _outputTextArea)
	{
		super();
		outputTextArea = _outputTextArea;
		redirectedOut = new PrintStream(this);
		oldPrintStream = System.out;
		System.setOut(redirectedOut);
	}

	/**<ol><li>Covert the specified <code>int</code> to an array of bytes</li><li>Convert the array of bytes to a String</li><li>Calls to <code>write(String s)</code> method</li></ol>*/
	public void write(int b)
	{
		byte b8[] = new byte[1];
		b8[0] = (new Integer(b)).byteValue();
		write(new String(b8));
	}

	/**<ol><li>Covert the specified array of ints to an array of bytes</li><li>Convert the array of bytes to a String</li><li>Calls to <code>write(String s)</code> method</li></ol>*/
	public void write(int[] b)
	{
		byte b8[] = new byte[b.length];
		for (int i=0; i<b.length; i++) {
			b8[i] = (new Integer(b[i])).byteValue();
		}
		write(new String(b8));
	}

	/**Appends the specified <code>String</code> to the <code>java.awt.TextArea</code> specified at constructor's time*/
	public void write(String s)
	{
		outputTextArea.append(s);
	}

	/**Restores the original <code>System.out</code> PrintStream.*/
	public void dispose()
	{
		System.setOut(oldPrintStream);
	}
}
