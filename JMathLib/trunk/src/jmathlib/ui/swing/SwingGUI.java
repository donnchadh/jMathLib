package jmathlib.ui.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;



/**
 * Stand-alone GUI for the JMathLib package.
 *
 * Some options may be given in the command line, by example:
 * <kbd>localhost# java MathLib.UI.Swing.SwingGUI -width 320 -height 200</kbd>.
 * <p>
 * <b>Command line options</b>
 * <table cellpadding="1" cellspacing="0" border="1">
 * <tr><td><kbd>-height</kbd></td><td>The height of the main window</td>
 * <tr><td><kbd>-width</kbd></td><td>The width of the main window</td>
 * </table>
 * @version 2.0
 */
public class SwingGUI extends JFrame {

    final String appTitle = "JMathLib SwingGUI";

    static final String DEAFULT_PROPERTIES = "jmathLib/ui/swing/SwingGUI.properties";

    public static SwingGUI runningReference;

    Console   answer      = new Console();

    JLabel    statusBar   = new JLabel();
    
    JMenuBar  jMenuBar1   = new JMenuBar();

    JMenu     jMenu1      = new JMenu("File");
    JMenu     jMenu2      = new JMenu("Edit");
    JMenu     jMenu3      = new JMenu("Window");
    JMenu     jMenu4      = new JMenu("Help");
    JMenu     jMenu5      = new JMenu("Tools");

    JMenuItem jMenuItem2  = new JMenuItem("New");
    JMenuItem jMenuItem3  = new JMenuItem("Open");
    JMenuItem jMenuItem4  = new JMenuItem("Save");
    JMenuItem jMenuItem5  = new JMenuItem("Save as...");
    JMenuItem jMenuItem14 = new JMenuItem("Update Manager");
    JMenuItem jMenuItem1  = new JMenuItem("Exit");
    
    JMenuItem jMenuItem10 = new JMenuItem("Cut");
    JMenuItem jMenuItem11 = new JMenuItem("Copy");
    JMenuItem jMenuItem12 = new JMenuItem("Paste");
    JMenuItem jMenuItem13 = new JMenuItem("Select All");

    JMenuItem jMenuItem15 = new JMenuItem("Search Path");
    JMenuItem jMenuItem16 = new JMenuItem("Configuration");

    JMenuItem jMenuItem9  = new JMenuItem("JMathLib Documentation");
    JMenuItem jMenuItem8  = new JMenuItem("Functions Reference");
    JMenuItem jMenuItem7  = new JMenuItem("Release Notes");
    JMenuItem jMenuItem6  = new JMenuItem("About SwingGUI...");

    JScrollPane jScrollPane1 = new JScrollPane();

    JSplitPane splitter2;

    JTabbedPane editors     = new JTabbedPane(JTabbedPane.NORTH);
    ArrayList   editorFiles = new ArrayList();

    JFileChooser jFileChooser1;

    /**
     * 
     * @param args
     */
    public SwingGUI(String[] args) {
        runningReference = this;
        loadProperties();
        argumentHandler(args);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Command-line parameter handler
     * Takes control of the size of the main window (by now)
     */
    private void argumentHandler(String[] args) 
    {
        int width = -1;
        int height = -1;

        // Parse all the arguments (or parameters)
        for (int i = 0; i < args.length; i++) 
        {
            String s;
            if (args[i].equals("-width")) 
            {
                try {
                    s = args[i + 1];
                    width = Integer.valueOf(s).intValue();
                    if (width < 0) {
                        throw new NumberFormatException();
                    }
                    i++;
                } 
                catch (NumberFormatException nfe) 
                {
                    System.out.println(args[i + 1] + ": " + System.getProperty("ERRMSG001"));
                    width = -1;
                } 
                catch (ArrayIndexOutOfBoundsException aiobe) 
                {
                    System.out.println(args[i] + ": " + System.getProperty("ERRMSG003"));
                    width = -1;
                }
            } 
            else if (args[i].equals("-height")) 
            {
                try 
                {
                    s = args[i + 1];
                    height = Integer.valueOf(s).intValue();
                    if (width < 0) {
                        throw new NumberFormatException();
                    }
                    i++;
                } 
                catch (NumberFormatException nfe) 
                {
                    System.out.println(args[i + 1] + ": " + System.getProperty("ERRMSG001"));
                    height = -1;
                } 
                catch (ArrayIndexOutOfBoundsException aiobe) 
                {
                    System.out.println(args[i] + ": " + System.getProperty("ERRMSG002"));
                    width = -1;
                }
            } 
            else 
            {
                System.out.println(args[i] + ": " + System.getProperty("ERRMSG002"));
            }
        }
        
        // Let's resize the window...
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        if (width == -1) {
            width = (d.width * 85) / 100;
        }
        if (height == -1) {
            height = (d.height * 85) / 100;
        }
        this.setSize(width, height);
    }


    /**
     * 
     */
    private void close() 
    {
        CommandHistoryManager.getDefaultInstance().writeSessionHistory();
        ApplicationConfiguration.getInstance().writeConfiguration();
        this.dispose();
        System.exit(0);
    }


    /**
     * 
     * @return
     */
    private static String getJavaRuntimeVersion() 
    {
        String jrv = System.getProperty("java.runtime.version");
        return jrv.substring(0, 3);
    }


    /**
     * 
     * @throws Exception
     */
    private void jbInit() throws Exception 
    {
        // JBUILDER DESIGN NOTE: It should be changed the initialization of
        // ANSWER to the "new JTextArea" defintion
        this.setJMenuBar(jMenuBar1);
        // Get the size of the screen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        // position the frame in the centre of the screen
        this.setLocationRelativeTo(null);
        /*
        this.setLocation((d.width-getSize().width) / 2,
        (d.height-getSize().height) / 2);
         */
        this.setIconImage(new ImageIcon(System.getProperty("ICON_FILE")).getImage());
        this.setTitle(appTitle);
        this.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                this_windowClosing(e);
            }
        });

        jMenu1.setText(System.getProperty("MENU1_TXT"));
        jMenu1.setMnemonic(System.getProperty("MENU1_MNE").charAt(0));

        jMenuItem1.setText(System.getProperty("MENUITEM1_TXT"));
        jMenuItem1.setMnemonic(System.getProperty("MENUITEM1_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM1_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM1_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem1.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem1_actionPerformed(e);
            }
        });

        jMenu4.setText(System.getProperty("MENU4_TXT"));
        jMenu4.setMnemonic(System.getProperty("MENU4_MNE").charAt(0));

        jMenuItem2.setText(System.getProperty("MENUITEM2_TXT"));
        jMenuItem2.setMnemonic(System.getProperty("MENUITEM2_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM2_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM2_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem2.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem2_actionPerformed(e);
            }
        });

        jMenuItem3.setText(System.getProperty("MENUITEM3_TXT"));
        jMenuItem3.setMnemonic(System.getProperty("MENUITEM3_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM3_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM3_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem3.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem3_actionPerformed(e);
            }
        });

        jMenuItem4.setText(System.getProperty("MENUITEM4_TXT"));
        jMenuItem4.setMnemonic(System.getProperty("MENUITEM4_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM4_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM4_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem4.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem4_actionPerformed(e);
            }
        });

        jMenuItem5.setText(System.getProperty("MENUITEM5_TXT"));
        jMenuItem5.setMnemonic(System.getProperty("MENUITEM5_MNE").charAt(0));
        try 
        {
            String s = System.getProperty("MENUITEM5_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM5_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem5.setAccelerator(ks);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem5_actionPerformed(e);
            }
        });

        jMenuItem6.setText(System.getProperty("MENUITEM6_TXT"));
        jMenuItem6.setMnemonic(System.getProperty("MENUITEM6_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM6_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM6_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem6.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem6_actionPerformed(e);
            }
        });

        jMenuItem7.setText(System.getProperty("MENUITEM7_TXT"));
        jMenuItem7.setMnemonic(System.getProperty("MENUITEM7_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM7_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM7_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem7.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem7_actionPerformed(e);
            }
        });

        jMenuItem8.setText(System.getProperty("MENUITEM8_TXT"));
        jMenuItem8.setMnemonic(System.getProperty("MENUITEM8_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM8_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM8_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem8.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem8_actionPerformed(e);
            }
        });

        jMenuItem9.setText(System.getProperty("MENUITEM9_TXT"));
        jMenuItem9.setMnemonic(System.getProperty("MENUITEM9_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM9_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM9_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem9.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem9_actionPerformed(e);
            }
        });

        jMenu3.setText(System.getProperty("MENU3_TXT"));
        jMenu3.setMnemonic(System.getProperty("MENU3_MNE").charAt(0));

        jMenu2.setText(System.getProperty("MENU2_TXT"));
        jMenu2.setMnemonic(System.getProperty("MENU2_MNE").charAt(0));

        jMenuItem10.setText(System.getProperty("MENUITEM10_TXT"));
        jMenuItem10.setMnemonic(System.getProperty("MENUITEM10_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM10_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM10_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem10.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem10_actionPerformed(e);
            }
        });

        jMenuItem11.setText(System.getProperty("MENUITEM11_TXT"));
        jMenuItem11.setMnemonic(System.getProperty("MENUITEM11_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM11_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM11_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem11.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem11_actionPerformed(e);
            }
        });

        jMenuItem12.setText(System.getProperty("MENUITEM12_TXT"));
        jMenuItem12.setMnemonic(System.getProperty("MENUITEM12_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM12_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM12_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem12.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem12_actionPerformed(e);
            }
        });

        jMenuItem13.setText(System.getProperty("MENUITEM13_TXT"));
        jMenuItem13.setMnemonic(System.getProperty("MENUITEM13_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM13_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM13_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem13.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem13_actionPerformed(e);
            }
        });

        jMenuItem13.setText(System.getProperty("MENUITEM13_TXT"));
        jMenuItem13.setMnemonic(System.getProperty("MENUITEM13_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM13_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM13_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem13.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem13_actionPerformed(e);
            }
        });

        jMenuItem14.setText(System.getProperty("MENUITEM14_TXT"));
        jMenuItem14.setMnemonic(System.getProperty("MENUITEM14_MNE").charAt(0));
        try {
            String s = System.getProperty("MENUITEM14_KS");
            if (!s.equals("")) {
                int ksp = new Integer(s).intValue();
                s = System.getProperty("MENUITEM14_KSM");
                int ksmp;
                if (!s.equals("")) {
                    ksmp = new Integer(s).intValue();
                } else {
                    ksmp = 0;
                }
                KeyStroke ks = KeyStroke.getKeyStroke(ksp, ksmp);
                jMenuItem14.setAccelerator(ks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem14_actionPerformed(e);
            }
        });

        jFileChooser1 = new JFileChooser(System.getProperty("FILE_DIALOG_PATH"));
        jFileChooser1.setFileFilter(new javax.swing.filechooser.FileFilter() {

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                // msw - MathLib SwingGUI Workspace
                if (f.getName().endsWith(System.getProperty("MSW_EXT"))) {
                    return true;
                }
                return false;
            }

            public String getDescription() {
                return System.getProperty("MSW_DESC");
            }
        });

        jMenuItem15.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem15_actionPerformed(e);
            }
        });
        
        jMenuItem16.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jMenuItem16_actionPerformed(e);
            }
        });            

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu5);
        jMenuBar1.add(jMenu3);
        jMenuBar1.add(jMenu4);
        
        jMenu1.add(jMenuItem2);
        jMenu1.add(jMenuItem3);
        jMenu1.add(jMenuItem4);
        jMenu1.add(jMenuItem5);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem14);
        jMenu1.add(jMenuItem1);

        jMenu4.add(jMenuItem9);
        jMenu4.add(jMenuItem8);
        jMenu4.addSeparator();
        jMenu4.add(jMenuItem7);
        jMenu4.add(jMenuItem6);
        
        jMenu5.add(jMenuItem15);
        jMenu5.addSeparator();
        jMenu5.add(jMenuItem16);

        if (System.getProperty("LINE_WRAP").equalsIgnoreCase("true")) {
            answer.setLineWrap(true);
            answer.setWrapStyleWord(true);
        }

        JTree history = new JTree(new HistoryModel(CommandHistoryManager.getDefaultInstance()));
        //Bit of a hack, but the first row is the current session.
        history.expandRow(1);

        JPanel commandHistoryPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Command History:");
        label.setHorizontalAlignment(JLabel.CENTER);
        commandHistoryPanel.add(label, BorderLayout.NORTH);
        commandHistoryPanel.add(new JScrollPane(history), BorderLayout.CENTER);

        this.editors.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        WorkingDirectoryPanel workingDirectoryPanel = new WorkingDirectoryPanel();
        JSplitPane leftSplitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, workingDirectoryPanel, commandHistoryPanel);
        leftSplitter.setOneTouchExpandable(true);

        //splitters
        splitter2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jScrollPane1, null);
        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplitter, splitter2);
        splitter.setOneTouchExpandable(true);
        splitter2.setOneTouchExpandable(true);
        this.add(splitter, BorderLayout.CENTER);
        this.add(statusBar, BorderLayout.SOUTH);

        leftSplitter.setDividerLocation(300);

        jMenu2.add(jMenuItem10);
        jMenu2.add(jMenuItem11);
        jMenu2.add(jMenuItem12);
        jMenu2.addSeparator();
        jMenu2.add(jMenuItem13);
        jScrollPane1.getViewport().add(answer, null);
        
        // AutoCompletion load
        new AutoCompletion().load();
        statusBar.setText(System.getProperty("STATUSBAR"));
        this.setVisible(true);
        
        // call the "startup.m" script to set up JMathLib
        this.answer.keyHandler.interpreter.executeExpression("startup;");
        this.answer.append('\n' + this.answer.prompt);
        this.answer.setCaretPosition(this.answer.getText().length());
        this.answer.requestFocus();

    }

    
    /**
     * 
     * @param file
     * @throws Exception
     */
    public void editFile(File file) throws Exception {
        int alreadyOpen = this.editorFiles.indexOf(file);
        if (alreadyOpen != -1) {
            this.editors.setSelectedIndex(alreadyOpen);
            return;
        }
        ScriptEditor editor = new ScriptEditor(file);
        if (this.editorFiles.size() == 1) {
            this.splitter2.setBottomComponent(this.editors);
        }
        this.editors.addTab(file.getName(), null, new JScrollPane(editor), file.getPath());
        this.editorFiles.add(file);
    }

    
    /**
     * 
     * @param file
     */
    public void closeEditor(File file) {
        int index = this.editorFiles.indexOf(file);
        if (this.editorFiles.size() == 1) {
            this.splitter2.setBottomComponent(null);
        }
        this.editors.removeTabAt(index);
        this.editorFiles.remove(index);
    }

    
    /**
     * 
     * @param file
     */
    public void openFileInEditor(File file) {
        try {
            this.editFile(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
    /**
     * 
     * @param e
     */
    void jMenuItem1_actionPerformed(ActionEvent e) 
    {
        this.close();
    }

    
    /**
     * Called on FILE/NEW
     * @param e The action event
     */
    void jMenuItem2_actionPerformed(ActionEvent e) 
    {
        // Check if the main window is empty
        int x = this.answer.getText().length();
        if (x > 2) {
            // No
            // Do you want save it?
            jMenuItem5_actionPerformed(e);
        }
        this.setTitle(this.appTitle);
        KeyHandler kh = (KeyHandler) (this.answer.getKeyListeners()[0]);
        CommandHistoryManager.getDefaultInstance().clearCurrentSession();
        this.answer.setText("> ");
    }


    /**
     * Called on FILE/OPEN
     * @param e The action event
     */
    void jMenuItem3_actionPerformed(ActionEvent e) 
    {
        int val = jFileChooser1.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) 
        {
            loadWorkspace(jFileChooser1.getSelectedFile());
        }
    }


    /**
     * Called on FILE/SAVE
     * @param e The action event
     */
    void jMenuItem4_actionPerformed(ActionEvent e) 
    {
        // Check if we have already opened a file
        if (this.getTitle().length() < this.appTitle.length()) 
        {
            // Yes
            String fileName = this.getTitle().substring(this.appTitle.length());
            saveWorkspace(new File(fileName));
        } 
        else 
        {
            jMenuItem5_actionPerformed(e);
        }
    }



    /**
     * Called on FILE/SAVE AS..
     * @param e The action event
     */
    void jMenuItem5_actionPerformed(ActionEvent e) 
    {
        int val = jFileChooser1.showSaveDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) 
        {
            File f = jFileChooser1.getSelectedFile();
            if (!f.getName().endsWith(".msw")) 
            {
                f = new File(f.getAbsolutePath() + ".msw");
            }
            saveWorkspace(f);
        }
    }


    /**
     * 
     * @param e
     */
    void jMenuItem6_actionPerformed(ActionEvent e) {
        new About();
    }


    /**
     * 
     * @param e
     */
    void jMenuItem7_actionPerformed(ActionEvent e) {
        new ReleaseNotes();
    }


    /**
     * 
     * @param e
     */
    void jMenuItem8_actionPerformed(ActionEvent e) {
        String s = answer.getcurrentWord();
        HTMLRenderDialog hrd = new HTMLRenderDialog(SwingGUI.runningReference, "Function reference: " + s, false);
        try {
            hrd.setPage("file:///" + new File("..").getCanonicalPath() + "\\Documentation\\functions\\" + s + ".html");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * 
     * @param e
     */
    void jMenuItem9_actionPerformed(ActionEvent e) {
        HTMLRenderDialog hrd = new HTMLRenderDialog(SwingGUI.runningReference, "JMathLib Documentation", false);
        try {
            hrd.setPage("file:///" + new File("..").getCanonicalPath() + "\\mathlib\\Documentation\\help\\index.html");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * 
     * @param e
     */
    void jMenuItem10_actionPerformed(ActionEvent e) {
        // Edit - Cut
        // Let's "cut" the text to the System Clipboard
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException awtex) {
            awtex.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_X);
        r.keyRelease(KeyEvent.VK_X);
        r.keyRelease(KeyEvent.VK_CONTROL);
    }


    /**
     * 
     * @param e
     */
    void jMenuItem11_actionPerformed(ActionEvent e) {
        // Edit - Copy
        // Let's "copy" the text to the System Clipboard
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException awtex) {
            awtex.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_C);
        r.keyRelease(KeyEvent.VK_C);
        r.keyRelease(KeyEvent.VK_CONTROL);
    }


    /**
     * 
     * @param e
     */
    void jMenuItem12_actionPerformed(ActionEvent e) {
        // Edit - Paste
        // Let's paste the content System Clipboard (if possible)
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException awtex) {
            awtex.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_CONTROL);
    }


    /**
     * 
     * @param e
     */
    void jMenuItem13_actionPerformed(ActionEvent e) {
        // Edit - Select All
        // Let's select all the text
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException awtex) {
            awtex.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_A);
        r.keyRelease(KeyEvent.VK_A);
        r.keyRelease(KeyEvent.VK_CONTROL);
    }


    /**
     * 
     * @param e
     */
    void jMenuItem14_actionPerformed(ActionEvent e) {
        // File - Update Manager
        // First of all, let's save the workspace
        jMenuItem5_actionPerformed(e);
        new UpdateManager();
    }

    
    /**
     * 
     * @param e
     */
    void jMenuItem15_actionPerformed(ActionEvent e) {
        SearchPathSelection.showPathSelectionDialog(this);
    }
    
    
    /**
     * 
     * @param e
     */
    void jMenuItem16_actionPerformed(ActionEvent e) {
        ApplicationConfiguration.getInstance().showConfigurationDialog(this);
    }

    
    /**
     * 
     */
    static void loadProperties() {
        try {
            InputStream s = SwingGUI.class.getClassLoader().getResourceAsStream(DEAFULT_PROPERTIES);
            System.getProperties().load(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Load the specified workspace file.
     *
     * All the main window text and the command history is overwritten.
     * See release notes - General notes - GN0009
     * @param f File to load
     */
    void loadWorkspace(File f) {
        try {
            FileReader fr = new FileReader(f);
            this.setTitle(appTitle + " - " + f.getAbsolutePath());
            String s = this.statusBar.getText();
            this.statusBar.setText("Reading file...");
            this.answer.setText("");

            // Command History Vector
            char c = (char) fr.read();
            while (fr.ready()) {
                String line = "";
                // Read a line
                while (c != '\r' && c != '\n' && fr.ready()) {
                    line += c;
                    c = (char) fr.read();
                }
                // Skip new-lines (MAC, DOS or UNIX styles supported)
                char c2 = 0;
                while (c != c2 && fr.ready() && (c == '\n' || c == '\r')) {
                    c2 = c;
                    c = (char) fr.read();
                }
                // Test if it's a command
                if (line.startsWith("> ") && line.length() > 2) {
                    // Yes, it's a command
                    CommandHistoryManager.getDefaultInstance().addCommand(line.substring(2));
                }
                // Append the line to the main window
                if (fr.ready()) {
                    line += '\n';
                } else {
                    line += c;
                }
                this.answer.append(line);
            }

            // It's safer to uncomment the next line only when saving files
            // with unfinished operations (no prompt is shown)
            //// answer.append('\n' + "> ");
            answer.setCaretPosition(answer.getText().length());
            this.statusBar.setText(s);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        if (getJavaRuntimeVersion().compareTo("1.4") < 0) {
            System.err.println("WARNING: Required java runtime " + "version 1.4.0-b92 or later. " + "System reported: " + System.getProperty("java.runtime.version"));
        }
        // See GN0013.
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        new SwingGUI(args);
    }


    /**
     * 
     * @param f
     */
    void saveWorkspace(File f) {
        try {
            FileWriter fw = new FileWriter(f);
            this.setTitle(appTitle + " - " + f.getAbsolutePath());
            String s = this.statusBar.getText();
            this.statusBar.setText("Writing file...");
            this.answer.write(fw);
            this.statusBar.setText(s);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /**
     * 
     * @param e
     */
    void this_windowClosing(WindowEvent e) {
        this.close();
    }
}