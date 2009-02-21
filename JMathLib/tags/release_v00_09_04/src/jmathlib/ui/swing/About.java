package jmathlib.ui.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * The <i>about</i> dialog.
 */
public class About extends JDialog
{
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    JLabel jLabel8 = new JLabel();
    JTextArea jTextArea1 = new JTextArea();
    JButton jButton1 = new JButton();

    static About activeInstance = null;

    JMenuItem jMenuItemAbout = new JMenuItem("About Dialog");
    JLabel jLabel0 = new JLabel();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel9 = new JLabel();
    JLabel jLabel10 = new JLabel();


    /**
     * 
     */
    public About()
    {
        if  (About.activeInstance != null)
            {
                About.activeInstance.setVisible(true);
                return ;
            }
        if  (About.activeInstance == null)
            {
                jMenuItemAbout.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        jMenuItemAbout_actionPerformed(e);
                    }
                });
                SwingGUI.runningReference.jMenu3.add(this.jMenuItemAbout);
                About.activeInstance = this;
            }
        try
        {
            jbInit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 
     * @throws Exception
     */
    private void jbInit() throws Exception
    {
        jLabel0.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel0.setHorizontalTextPosition(SwingConstants.CENTER);
        jLabel0.setText("JMathLib SwingGUI");
        jLabel0.setBounds(new Rectangle(0, 32, 385, 17));
        this.setTitle("About JMathLib");
        // Added +30 to the height to correct Windows title bar
        this.setSize(new Dimension(360, 336));
        // Get the size of the screen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        // position the frame in the centre of the screen
        this.setLocation((d.width-getSize().width) / 2,
                         (d.height-getSize().height) / 2);
        this.getContentPane().setLayout(null);
        this.addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                this_windowClosing(e);
            }
        });
        jTextArea1.setBackground(this.getContentPane().getBackground());
        jTextArea1.setBorder(null);
        jTextArea1.setEditable(false);
        jTextArea1.setText("SwingGUI is a GUI for the"+
        " JMathLib package using the swing package provided by Sun.");
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBounds(new Rectangle(33, 65, 304, 51));
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                jTextArea1_keyPressed(e);
            }
        });
        jLabel1.setForeground(Color.black);
        jLabel1.setMaximumSize(new Dimension(20000, 17));
        jLabel1.setMinimumSize(new Dimension(95, 17));
        jLabel1.setPreferredSize(new Dimension(95, 17));
        jLabel1.setText("Home page");
        jLabel1.setBounds(new Rectangle(33, 136, 96, 17));
        jLabel2.setForeground(Color.black);
        jLabel2.setMaximumSize(new Dimension(20000, 17));
        jLabel2.setMinimumSize(new Dimension(95, 17));
        jLabel2.setPreferredSize(new Dimension(95, 17));
        jLabel2.setText("Mark Sparshatt");
        jLabel2.setBounds(new Rectangle(33, 162, 111, 17));
        jLabel3.setForeground(Color.black);
        jLabel3.setMaximumSize(new Dimension(20000, 17));
        jLabel3.setMinimumSize(new Dimension(95, 17));
        jLabel3.setPreferredSize(new Dimension(95, 17));
        jLabel3.setText("Stefan Mueller");
        jLabel3.setBounds(new Rectangle(33, 188, 111, 17));
        jLabel4.setForeground(Color.black);
        jLabel3.setMaximumSize(new Dimension(20000, 17));
        jLabel3.setMinimumSize(new Dimension(95, 17));
        jLabel3.setPreferredSize(new Dimension(95, 17));
        jLabel4.setText("Alejandro Torras");
        jLabel4.setBounds(new Rectangle(33, 214, 111, 17));
        jLabel5.setForeground(Color.blue);
        jLabel5.setMaximumSize(new Dimension(20000, 17));
        jLabel5.setMinimumSize(new Dimension(167, 17));
        jLabel5.setText("http://www.jmathlib.de");
        jLabel5.setBounds(new Rectangle(168, 136, 175, 17));
        jLabel6.setForeground(Color.blue);
        jLabel6.setMaximumSize(new Dimension(20000, 17));
        jLabel6.setMinimumSize(new Dimension(167, 17));
        jLabel6.setPreferredSize(new Dimension(167, 17));
        jLabel6.setText("msparshatt@yahoo.co.uk");
        jLabel6.setBounds(new Rectangle(168, 162, 158, 17));
        jLabel7.setForeground(Color.blue);
        jLabel7.setMaximumSize(new Dimension(20000, 17));
        jLabel7.setMinimumSize(new Dimension(167, 17));
        jLabel7.setPreferredSize(new Dimension(167, 17));
        jLabel7.setText("stefan@held-mueller.de");
        jLabel7.setBounds(new Rectangle(168, 188, 158, 17));
        jLabel8.setForeground(Color.blue);
        jLabel8.setMaximumSize(new Dimension(20000, 17));
        jLabel8.setMinimumSize(new Dimension(167, 17));
        jLabel8.setPreferredSize(new Dimension(167, 17));
        jLabel8.setText("atec_post@hotmail.com");
        jLabel8.setBounds(new Rectangle(168, 214, 158, 17));
        jButton1.setBounds(new Rectangle(262, 272, 81, 27));
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                jButton1_actionPerformed(e);
            }
        });
        jLabel9.setBounds(new Rectangle(33, 240, 111, 17));
        jLabel9.setText("Thejesh G. N.");
        jLabel9.setForeground(Color.black);
        jLabel10.setBounds(new Rectangle(168, 240, 158, 17));
        jLabel10.setText("Thejesh_GN@infosys.com");
        jLabel10.setPreferredSize(new Dimension(167, 17));
        jLabel10.setMinimumSize(new Dimension(167, 17));
        jLabel10.setMaximumSize(new Dimension(20000, 17));
        jLabel10.setForeground(Color.blue);
        this.getContentPane().add(jLabel0, null);
        this.getContentPane().add(jTextArea1, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(jLabel6, null);
        this.getContentPane().add(jLabel7, null);
        this.getContentPane().add(jLabel8, null);
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jLabel10, null);
        this.getContentPane().add(jLabel9, null);
        this.setVisible(true);
    }


    /**
     * 
     * @param e
     */
    void this_windowClosing(WindowEvent e)
    {
        SwingGUI.runningReference.jMenu3.remove(this.jMenuItemAbout);
        activeInstance = null;
    }


    /**
     * 
     * @param e
     */
    void jMenuItemAbout_actionPerformed(ActionEvent e)
    {
        About.activeInstance.setVisible(true);
    }


    /**
     * 
     * @param e
     */
    void jButton1_actionPerformed(ActionEvent e)
    {
        this.processWindowEvent(new WindowEvent(this,
                                                WindowEvent.WINDOW_CLOSING));
    }


    /**
     * Handles the key pressing events on the textarea.
     * @param e The key pressed
     */
    void jTextArea1_keyPressed(KeyEvent e)
    {
        int keyValue = e.getKeyCode();
        switch (keyValue)
            {
                case KeyEvent.VK_ENTER:
                case KeyEvent.VK_ESCAPE:
                case KeyEvent.VK_SPACE:
                    // Let's close the window
                    jButton1_actionPerformed(null);
                default:
            }
    }
}