package jmathlib.ui.swing;

import javax.swing.*;
import javax.swing.text.*;


/**
 * Main input text area class.
 */
public class Console extends JTextArea 
{
    public static Console runningReference = null;

    public String prompt = "> ";

    public KeyHandler keyHandler = new KeyHandler(this);


    /**
     * 
     */
    public Console()
    {
        if (runningReference == null)
        {
            runningReference = this;
        }
        this.addKeyListener(keyHandler);
        this.setText(prompt);
        //this.setCaretPosition(2);

        this.addMouseListener(new ConsoleContextMenu());
    }


    /**
     * Display some text on a new line
     * @param text The response string.
     */
    public void displayText(String text)
    {
        this.append('\n' + text);
    }


    /**
     * Returns the current command line.
     *
     * The prompt is also included.
     * @return The last line.
     */
    public String getCurrentCommand()
    {
        String s = getText();
        int i = s.lastIndexOf(""+'\n') + prompt.length()+1;
        int j = s.length();
        s = s.substring(i, j);
        //this.setAutoscrolls(true);
        return s;
    }


    /**
     * Returns the word located down the cursor.
     * @return The string without delimiters.
     */
    public String getcurrentWord()
    {
        int i = this.getCaretPosition();
        String s = this.getText();
        int j = i;
        // Workaround. See DN0016.
        while (j < s.length() &&
               s.charAt(j) != ' ' && s.charAt(j) != '.' &&
               s.charAt(j) != '(' && s.charAt(j) != ')' &&
               s.charAt(j) != '[' && s.charAt(j) != ']' &&
               s.charAt(j) != '{' && s.charAt(j) != '}' &&
               s.charAt(j) != '+' && s.charAt(j) != '-' &&
               s.charAt(j) != '*' && s.charAt(j) != '/' &&
               s.charAt(j) != '\\' && s.charAt(j) != '"' &&
               s.charAt(j) != '=' && s.charAt(j) != ',' &&
               s.charAt(j) != '$' && s.charAt(j) != '%' &&
               s.charAt(j) != '\n' && s.charAt(j) != '\t')
        {
            j++;
        }
        do
        {
            i--;
        }
        while (i > 0 &&
               s.charAt(i) != ' ' && s.charAt(i) != '.' &&
               s.charAt(i) != '(' && s.charAt(i) != ')' &&
               s.charAt(i) != '[' && s.charAt(i) != ']' &&
               s.charAt(i) != '{' && s.charAt(i) != '}' &&
               s.charAt(i) != '+' && s.charAt(i) != '-' &&
               s.charAt(i) != '*' && s.charAt(i) != '/' &&
               s.charAt(i) != '\\' && s.charAt(i) != '"' &&
               s.charAt(i) != '=' && s.charAt(i) != ',' &&
               s.charAt(i) != '$' && s.charAt(i) != '%' &&
               s.charAt(i) != '\n' && s.charAt(i) != '\t');
        try
        {
            s = this.getText(i+1, j-i-1);
        }
        catch (BadLocationException ble)
        {
            s = "";
        }
        return s;
    }
}