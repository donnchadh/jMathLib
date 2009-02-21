package jmathlib.ui.swing;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;



/**
 * <p>The <i>Release notes Frame</i> dialog.</p>
 * <p>This dialog shows the file <kbd>releasenotes.txt</kbd></p>
 * @version 1.9
 */
public class ReleaseNotes extends JDialog
{
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextArea jTextArea1 = new JTextArea();

    static ReleaseNotes activeInstance = null;

    JMenuItem jMenuItemReleaseNotes = new JMenuItem("Release Notes Dialog");



    public ReleaseNotes()
    {
        if  (ReleaseNotes.activeInstance != null)
            {
                ReleaseNotes.activeInstance.setVisible(true);
                return ;
            }
        if  (ReleaseNotes.activeInstance == null)
            {
                jMenuItemReleaseNotes.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        jMenuItemReleaseNotes_actionPerformed(e);
                    }
                });
                SwingGUI.runningReference.jMenu3.add(this.jMenuItemReleaseNotes);
                ReleaseNotes.activeInstance = this;
            }
        try
        {
            jbInit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        String fileName = "MathLib/UI/Swing/releasenotes.txt";
        try
        {
            FileReader fr = new FileReader(fileName);
            jTextArea1.read(fr, null);
        }
        catch (IOException ioex)
        {
            ioex.printStackTrace();
            // See DN0008
            String errmsg = "There was an error opening " + fileName;
            errmsg += "" + '\n' + "" + '\n';
            errmsg += "Current directory: " + new File(".").getAbsolutePath();
            jTextArea1.setText(errmsg);
        }
    }



    private void jbInit() throws Exception
    {
        this.setTitle("Release Notes");
        // Added +30 to the height to correct Windows title bar
        this.setSize(new Dimension(360, 282+30));
        // Get the size of the screen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        // position the frame in the centre of the screen
        this.setLocation((d.width-getSize().width) / 2,
                         (d.height-getSize().height) / 2);
        this.addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                this_windowClosing(e);
            }
        });
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12));
        jTextArea1.setBorder(null);
        jTextArea1.setEditable(false);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                jTextArea1_keyPressed(e);
            }
        });
        this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jTextArea1, null);
        this.setVisible(true);
    }



    void this_windowClosing(WindowEvent e)
    {
        SwingGUI.runningReference.jMenu3.remove(this.jMenuItemReleaseNotes);
        activeInstance = null;
    }



    void jMenuItemReleaseNotes_actionPerformed(ActionEvent e)
    {
        ReleaseNotes.activeInstance.setVisible(true);
    }



    /**
     * Handles the key pressing events on the textarea.

     * <p>See releasenotes.txt - DN0013 section</p>
     * @param e The key pressed
     */
    void jTextArea1_keyPressed(KeyEvent e)
    {
        int keyValue = e.getKeyCode();
        switch (keyValue)
            {
                case KeyEvent.VK_ESCAPE:
                    // Let's close the window
                    this.processWindowEvent(new WindowEvent(this,
                                                           WindowEvent.WINDOW_CLOSING));
               default:
            }
    }
}