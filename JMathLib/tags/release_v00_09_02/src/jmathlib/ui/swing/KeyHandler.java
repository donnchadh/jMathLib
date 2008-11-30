package jmathlib.ui.swing;

import java.awt.event.*;
import javax.swing.text.*;
import java.util.*;
import jmathlib.core.interpreter.Interpreter;



/**
 * Console-keys class handler.

 * <p>Provides <i>command history</i> handling via the <i>up</i> and
 * <i>down</i> keys.</p>
 * <p>See <kbd>MathLib/Source/UI/Swing/releasenotes.txt</kbd> for more information.</p>
 * <p><table style="background-color='#FEE'"><td>This class is not guaranteed to work on older releases than <i>jdk1.4.0-b92</i>.<br>
 * See GN0004 for more information.</td></table></p>
 * @version 3.1.1
 */
public class KeyHandler implements KeyListener
{
    public static KeyHandler runningReference = null;

    final CommandHistoryManager commandHistory = CommandHistoryManager.getDefaultInstance();

    // The interpreter
    Interpreter interpreter = new Interpreter(true);



    /**
     * Main constructor.

     * Initializes the interpreter.
     * @param con Where the interpreter will place its output.
     */
    public KeyHandler(Console con)
    {
        if (runningReference == null)
        {
            runningReference = this;
        }
        interpreter.setOutputPanel(con);
    }



    /**
     * Handles key-typing not handled by keyPressed.

     * <p>This method only takes care of the backspace key</p>
     * @param e The event
     */
    public void keyTyped(KeyEvent e)
    {
        if  (this.isOutsideTyping(e))
            {
                return ;
            }
        int x = e.getKeyChar();
        switch (e.getKeyChar())
            {
                case '\b':
                    // We must take care not deleting the prompt ;-)
                    Console source = (Console) e.getSource();
                    int lc = source.getLineCount() - 1;
                    try
                        {
                            int i = source.getLineStartOffset(lc);
                            i = source.getCaretPosition() - i;
                            if  (i <= 2)
                                {
                                    e.consume();
                                }
                        }
                    catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    break;
            }
    }



    /**
     * Handles key-typing.
     * @param e The event
     */
    public void keyPressed(KeyEvent e)
    {
        Console source = (Console) e.getSource();
        int keyValue = e.getKeyCode();
        int i, j, k, lc;

        if  (this.isOutsideTyping(e))
            {
                return ;
            }

        switch (keyValue)
            {
                // When the ENTER key is pressed...
                case KeyEvent.VK_ENTER:
                    e.consume();
                    lc = source.getLineCount() - 1;
                    try
                        {
                            i = source.getLineStartOffset(lc);
                            j = source.getLineEndOffset(lc);
                            String s = source.getText().substring(i+2, j);
                            if (s.equals(""))
                                {
                                    break;
                                }
                            this.commandHistory.addCommand(s);
                            interpreter.executeExpression(s);
                        }
                    catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    source.append('\n' + source.prompt);
                    source.setCaretPosition(source.getText().length());
                    break;
                case KeyEvent.VK_UP:
                    // Scroll back the command input history.
                    e.consume();
                    lc = source.getLineCount() - 1;
                    try
                        {
                            i = source.getLineStartOffset(lc);
                            j = source.getLineEndOffset(lc);
                            String s = commandHistory.prevCommand();
                            if (s != null)
                              source.replaceRange(s, i+2, j);
                        }
                    catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    break;
                // When the DOWN key is pressed
                case KeyEvent.VK_DOWN:
                    // Scroll forward the command input history.
                    e.consume();
                    lc = source.getLineCount() - 1;
                    try
                        {
                            i = source.getLineStartOffset(lc);
                            j = source.getLineEndOffset(lc);
                            String s = commandHistory.nextCommand();
                            if (s != null)
                            // Skip the prompt (i+2) and the end (j)
                            source.replaceRange(s, i+2, j);
                        }
                    catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    break;
                // When the ESCAPE key is pressed...
                case KeyEvent.VK_ESCAPE:
                    // Erase the current editing line
                    e.consume();
                    lc = source.getLineCount() - 1;
                    try
                        {
                            i = source.getLineStartOffset(lc);
                            j = source.getLineEndOffset(lc);
                            // Skip the prompt (i+2) and the end (j-1)
                            source.replaceRange("", i+2, j);
                        }
                    catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    commandHistory.resetToLastCommand();
                    break;
                case KeyEvent.VK_LEFT:
                    // We must take care not entering through the prompt ;-)
                    lc = source.getLineCount() - 1;
                    try
                        {
                            i = source.getLineStartOffset(lc);
                            j = source.getCaretPosition();
                            if  (j-i <= 2)
                                {
                                    e.consume();
                                }
                        }
                    catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    break;
                case KeyEvent.VK_HOME:
                    // We go to the first position after the prompt
                    e.consume();
                    lc = source.getLineCount() - 1;
                    try
                        {
                            i = source.getLineStartOffset(lc);
                            source.setCaretPosition(i+2);
                        }
                    catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    break;
                case KeyEvent.VK_PAGE_UP:
                    e.consume();
                    break;
                case KeyEvent.VK_TAB:
                    e.consume();
                    String s = source.getCurrentCommand();
                    String[] sa = AutoCompletion.runningReference.getMatched(s);
                    if (sa.length == 1)
                    {
                        // Let's trim the extension
                        String s2 = sa[0].substring(0, sa[0].indexOf("."));
                        source.setSelectionStart(source.getCaretPosition() - s.length());
                        source.setSelectionEnd(source.getCaretPosition());
                        source.replaceSelection(s2 + "()");
                        source.setCaretPosition(source.getCaretPosition() - 1);
                    }
                    else
                    {
                        source.append("" + '\n');
                        for(i = 0; i < sa.length; i++) {
                            source.append(sa[i] + '\t');
                        }
                        source.append("" + '\n' + source.prompt + s);
                    }
                    break;
                default:
                    break;
            }
    }



    public void keyReleased(KeyEvent e)
    {
    }



    /**
     * Checks if we type a key out the last line.

     * It never <i>consume's</i> the key.
     * @param e The event
     * @return <kbd>True</kbd> - The key was typed out the last line or
     * inside the prompt.
     */
    private boolean isOutsideTyping(KeyEvent e)
    {
        Console source = (Console) e.getSource();
        int i, j, k, lc;

        // Ensure we are not editing outside the last line
        lc = source.getLineCount();
        i = source.getCaretPosition();
        k = 0;
        try
            {
                k = source.getLineOfOffset(i) - lc;
                i = source.getLineStartOffset(lc - 1);
                i = source.getCaretPosition() - i;
            }
        catch (BadLocationException ble)
            {
                ble.printStackTrace();
            }
        if  (k != -1 || i < 2)
            {
                return true;
            }
        return false;
   }
}