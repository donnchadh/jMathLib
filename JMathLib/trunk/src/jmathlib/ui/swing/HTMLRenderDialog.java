package jmathlib.ui.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.text.Document;

public class HTMLRenderDialog extends JDialog
{
    JPanel panel1 = new JPanel();

    BorderLayout borderLayout1 = new BorderLayout();
    JScrollPane jScrollPane1 = new JScrollPane();
    JToolBar jToolBar1 = new JToolBar();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JEditorPane jEditorPane1 = new JEditorPane();
    JTextField jTextField1 = new JTextField();

    Stack backHistoryVector = new Stack();
    Stack forwardHistoryVector = new Stack();
    String currentURL = "";



    public HTMLRenderDialog()
    {
        this(null, "", false);
    }



    public HTMLRenderDialog(Frame frame, String title, boolean modal)
    {
        super(frame, title, modal);
        try
        {
            jbInit();
            pack();

            // Calculation of default bounds
            int h, w;
            if (frame != null)
            {
                h = (int) (frame.getSize().height * 0.85);
                w = (int) (frame.getSize().width * 0.85);
            }
            else
            {
                h = (int) (SwingGUI.runningReference.getSize().height * 0.85);
                w = (int) (SwingGUI.runningReference.getSize().width * 0.85);
            }
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            this.setBounds((d.width-w) / 2, (d.height-h) / 2, w, h);
            // End of calculation

            setVisible(true);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }



    private void jbInit() throws Exception
    {
        panel1.setLayout(borderLayout1);
        jButton1.setText("Back");
        jButton1.addActionListener(new HTMLRenderDialog_jButton1_actionAdapter(this));
        jButton2.setText("Forward");
        jButton2.addActionListener(new HTMLRenderDialog_jButton2_actionAdapter(this));
        jTextField1.setText("");
        jTextField1.addActionListener(new HTMLRenderDialog_jTextField1_actionAdapter(this));
        jEditorPane1.setEditable(false);
        jEditorPane1.addHyperlinkListener(new HTMLRenderDialog_jEditorPane1_hyperlinkAdapter(this));
        getContentPane().add(panel1);
        panel1.add(jScrollPane1, BorderLayout.CENTER);
        panel1.add(jTextField1,  BorderLayout.SOUTH);
        jScrollPane1.getViewport().add(jEditorPane1, null);
        this.getContentPane().add(jToolBar1, BorderLayout.NORTH);
        jToolBar1.add(jButton1, null);
        jToolBar1.add(jButton2, null);
    }



    void jTextField1_actionPerformed(ActionEvent e)
    {
        backHistoryVector.push(currentURL);
        forwardHistoryVector.clear();
        this.setPage(jTextField1.getText());
    }



    void jButton1_actionPerformed(ActionEvent e)
    {
        if (!backHistoryVector.empty())
        {
            this.setPage( -1);
        }
    }



    void jButton2_actionPerformed(ActionEvent e)
    {
        if (!forwardHistoryVector.empty())
        {
            this.setPage(1);
        }
    }



    void jEditorPane1_hyperlinkUpdate(HyperlinkEvent e)
    {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
        {
            backHistoryVector.push(currentURL);
            this.setPage(e.getURL().toString());
        }
    }



    /**
     * Loads the relative page from (back/forward) history.
     *
     * A postive value of historyPos will locate the page in the "forward" history.
     * A negative value will search in the "back" history.
     * A zero will reload the current page.
     *
     * An unbounded historyPos is checked making to go to the first/last page in
     * history.
     * @param historyPos History relative index (positive or negative).
     */
    void setPage(int historyPos)
    {
        if (historyPos < 0)
        {
            // Back history
            int hvs = backHistoryVector.size();
            int j = (hvs < Math.abs(historyPos)) ? hvs : Math.abs(historyPos);
            for (int i = 0; i < j; i++)
            {
                forwardHistoryVector.push(currentURL);
                currentURL = (String) backHistoryVector.pop();
            }
        }
        else
        {
            // Forward history.
            int hvs = forwardHistoryVector.size();
            int j = (hvs < historyPos) ? hvs : historyPos;
            for (int i = 0; i < j; i++)
            {
                backHistoryVector.push(currentURL);
                currentURL = (String) forwardHistoryVector.pop();
            }
        }
        this.setPage(currentURL);
    }



    void setPage(String url)
    {
        Document d = jEditorPane1.getDocument();
        URL oldURL = jEditorPane1.getPage();
        try
        {
            jEditorPane1.setPage(url);
            jTextField1.setText(url);
            currentURL = url;
        }
        catch (IOException ioe)
        {
            JOptionPane.showMessageDialog(this, "An error was found while opening " + url,
                                          "Error: Invalid URL",
                                          JOptionPane.ERROR_MESSAGE);
            if (oldURL != null)
            {
                jEditorPane1.setDocument(d);
            }
        }
    }



    void setPage(URL url)
    {
        this.setPage(url.toString());
    }
}



class HTMLRenderDialog_jTextField1_actionAdapter implements java.awt.event.ActionListener
{
    HTMLRenderDialog adaptee;

    HTMLRenderDialog_jTextField1_actionAdapter(HTMLRenderDialog adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.jTextField1_actionPerformed(e);
    }
}

class HTMLRenderDialog_jButton2_actionAdapter implements java.awt.event.ActionListener
{
    HTMLRenderDialog adaptee;

    HTMLRenderDialog_jButton2_actionAdapter(HTMLRenderDialog adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.jButton2_actionPerformed(e);
    }
}

class HTMLRenderDialog_jButton1_actionAdapter implements java.awt.event.ActionListener
{
    HTMLRenderDialog adaptee;

    HTMLRenderDialog_jButton1_actionAdapter(HTMLRenderDialog adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.jButton1_actionPerformed(e);
    }
}

class HTMLRenderDialog_jEditorPane1_hyperlinkAdapter implements javax.swing.event.HyperlinkListener
{
    HTMLRenderDialog adaptee;

    HTMLRenderDialog_jEditorPane1_hyperlinkAdapter(HTMLRenderDialog adaptee)
    {
        this.adaptee = adaptee;
    }
    public void hyperlinkUpdate(HyperlinkEvent e)
    {
        adaptee.jEditorPane1_hyperlinkUpdate(e);
    }
}