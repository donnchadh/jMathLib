package jmathlib.ui.awt;

import jmathlib.core.interfaces.RemoteAccesible;
import jmathlib.core.interpreter.Interpreter;
//import jmathlib.ui.common.console;

import java.awt.*;
import java.awt.event.*;

/**
 * Simple GUI for the MathLib package.

 * Some options may be given in the command line, by example:
 * <kbd>localhost# java MathLib.GUI.GUI -width=320 -height=200</kbd>.
 * <p>
 * <b>Command line options</b>
 * <ul>
 * <li><kbd>-width</kbd> - The width of the main window</li>
 * <li><kbd>-height</kbd> - The height of the main window</li>
 * </ul>
 * </p>
 */
public class GUI extends Frame implements WindowListener, ActionListener, RemoteAccesible
{
        /*The menubar container.*/
        private MenuBar  mainMenuBar;
        private Menu     fileMenu;
        private Menu     editMenu;
        private Menu     windowMenu;
        private Menu     helpMenu;
        private MenuItem separator1;
        private MenuItem separator2;
        private MenuItem newFileMenuItem;
        private MenuItem openFileMenuItem;
        private MenuItem saveFileMenuItem;
        private MenuItem saveAsFileMenuItem;
        private MenuItem checkForUpdatesMenuItem;
        private MenuItem exitFileMenuItem;
        private MenuItem cutEditMenuItem;
        private MenuItem copyEditMenuItem;
        private MenuItem pasteEditMenuItem;
        private MenuItem consoleWindowMenuItem;
        private MenuItem plotWindowMenuItem;
        private MenuItem aboutHelpMenuItem;

        /**Constant with the application title.*/
        private final String TITLE="JMathLib GUI";

        /**Flag storing whether the program is running as an application or an applet*/
        private boolean runningStandalone;

        /**The area used for user input and where the answers are displayed*/
        private Console answer;

        /**The interpreter*/
        private Interpreter interpreter;

    /**Reacts to the user menu and update (if necessary) the interface.*/
    public void actionPerformed(ActionEvent e)
    {
        Object o = e.getSource();

        if (o == newFileMenuItem) 
        {
        } 
        else if (o == openFileMenuItem) 
        {
            FileDialog theFileDialog = new FileDialog(this, "Open the file...", FileDialog.LOAD);
            theFileDialog.setVisible(true);
        } 
        else if (o == saveFileMenuItem) 
        {
            FileDialog theFileDialog = new FileDialog(this, "Save the file", FileDialog.SAVE);
            theFileDialog.setVisible(true);
        } 
        else if (o == saveAsFileMenuItem) 
        {
            FileDialog theFileDialog = new FileDialog(this, "Save the file", FileDialog.SAVE);
            theFileDialog.setVisible(true);
        } 
        else if (o == checkForUpdatesMenuItem) 
        {
            String answerString = interpreter.executeExpression("checkforupdates()");
            answer.displayText(answerString);
            answer.displayPrompt();
        } 
        else if (o == exitFileMenuItem) 
        {
                close();
        } 
        else if (o == cutEditMenuItem) 
        {
        } 
        else if (o == copyEditMenuItem) 
        {
        } 
        else if (o == pasteEditMenuItem) 
        {
        } 
        else if (o == aboutHelpMenuItem) 
        {
            AboutGUIDialog aboutDialog = new AboutGUIDialog(this);
        } 
        else if (o == consoleWindowMenuItem) 
        {
            this.setTitle(TITLE + " - Console Window");
            plotWindowMenuItem.setEnabled(true);
            consoleWindowMenuItem.setEnabled(false);
        } 
        else if (o == plotWindowMenuItem) 
        {
            this.setTitle(TITLE + " - Plot Window");
            plotWindowMenuItem.setEnabled(false);
            consoleWindowMenuItem.setEnabled(true);
        }
    }



    /**
     * Command-line parameter handler
     * Takes control of the size of the main window
     */
    private void argumentHandler(String[] args)
    {
        int width  = -1;
        int height = -1;

        // Parse all the arguments (or parameters)
        for (int i=0; i<args.length; i++)
        {
            String s;
            if  (args[i].startsWith("-width="))
            {
                s = args[i].substring(7);
                try
                {
                    width = Integer.valueOf(s).intValue();
                    if  (width < 0)
                        throw new NumberFormatException();
                }
                catch (NumberFormatException nfe)
                {
                    System.out.println(s + ": Invalid number.");
                    width = -1;
                }
            }
            else if  (args[i].startsWith("-height="))
            {
                s = args[i].substring(8);
                try
                {
                    height = Integer.valueOf(s).intValue();
                    if  (width < 0)
                    {
                        throw new NumberFormatException();
                    }
                }
                catch (NumberFormatException nfe)
                {
                    System.out.println(s + ": Invalid number.");
                    height = -1;
                }
            }
            else
                System.out.println(args[i] + ": Invalid option.");
        }
        // Let's resize the window...
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        if  (width == -1)
            width = (d.width*70)/100;
        
        if  (height == -1)
            height = (d.height*50)/100;

        this.setSize(width, height);
    }


    /**Function called when the gui is being close*/
    public void close()
    {
        interpreter.save();
        this.dispose();
        System.exit(0);
    }

    
    /**Create the main graphical interface (menu, buttons, delays...).*/
    public GUI(String[] args)
    {
            //this is an application, so set to true
            runningStandalone = true;
            this.argumentHandler(args);

            this.setVisible(false);
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(214,211,206));
            //Get the size of the screen
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            //position the frame in the centre of the screen
            this.setLocation((d.width-getSize().width) / 2,
                             (d.height-getSize().height) / 2);
            this.addWindowListener(this);
            this.setResizable(true);
            this.setVisible(true);
            
            // add image to window
            Toolkit tk = Toolkit.getDefaultToolkit();
            Image icon = tk.getImage(GUI.class.getResource("smalllogo.gif"));
            MediaTracker mt = new MediaTracker(this);
            mt.addImage(icon,0);
            try {
                mt.waitForAll();
            }
            catch (InterruptedException e){ ;}
            this.setIconImage(icon);
            
            this.setTitle(TITLE + " [1/4] Initializing menus");
            InitMenuBar(this);

            this.setTitle(TITLE + " [2/4] Initializing console window");
            InitConsole();

            this.setTitle(TITLE + " [3/4] Initializing interpreter");
            interpreter = new Interpreter(runningStandalone);
            interpreter.setOutputPanel(answer);
            
           
            this.setTitle(TITLE + " - [4/4] running startup script");
            interpreter.executeExpression("startup;");
            //interpreter.executeExpression("messageoftheday");
            answer.displayPrompt();

            // silent check for updates
            interpreter.executeExpression("checkforupdates('-silent')");

            this.setTitle(TITLE + " - Console Window");

            // in case an update is available inform the user
            String u = interpreter.globals.getProperty("update.newversionavailable");
            if ((u!=null) && u.equals("yes"))
            {
                this.setTitle(TITLE + " - (NEW version available: type update at prompt)");
                String s = interpreter.globals.getProperty("update.newversionavailable.message01");
                if (s==null)
                    answer.displayText("A NEW version of JMathLib is available\n     type update   or  visit www.jmathlib.de");
                else
                    answer.displayText(s);
                
                answer.displayPrompt();
            }
            

    }

    /**The main console initializer.*/
    private void InitConsole()
    {
        answer = new Console(this);
        this.add(answer);
        this.validate();
        answer.displayPrompt();
        // Sometimes I get an unfocused console, so I request it manually.
        answer.requestFocus();
    }

    /**The menu initializer.*/
    private void InitMenuBar(ActionListener listener)
    {
        mainMenuBar = new MenuBar();
        fileMenu = new Menu("File", true);
        mainMenuBar.add(fileMenu);
        editMenu = new Menu("Edit", true);
        mainMenuBar.add(editMenu);
        windowMenu = new Menu("Window", true);
        mainMenuBar.add(windowMenu);
        helpMenu = new Menu("Help", true);
        mainMenuBar.add(helpMenu);

        separator1 = new MenuItem("-");
        separator2 = new MenuItem("-");
        newFileMenuItem = new MenuItem("New");
        newFileMenuItem.setShortcut(new MenuShortcut(KeyEvent.VK_N));
        newFileMenuItem.addActionListener(listener);
        openFileMenuItem = new MenuItem("Open");
        openFileMenuItem.setShortcut(new MenuShortcut(KeyEvent.VK_O));
        openFileMenuItem.addActionListener(listener);
        saveFileMenuItem = new MenuItem("Save");
        saveFileMenuItem.setShortcut(new MenuShortcut(KeyEvent.VK_S));
        saveFileMenuItem.addActionListener(listener);
        saveAsFileMenuItem = new MenuItem("Save as...");
        saveAsFileMenuItem.addActionListener(listener);
        checkForUpdatesMenuItem = new MenuItem("Check for updates");
        checkForUpdatesMenuItem.addActionListener(listener);
        exitFileMenuItem = new MenuItem("Exit");
        exitFileMenuItem.setShortcut(new MenuShortcut(KeyEvent.VK_E));
        exitFileMenuItem.addActionListener(listener);
        cutEditMenuItem = new MenuItem("Cut");
        cutEditMenuItem.addActionListener(listener);
        copyEditMenuItem = new MenuItem("Copy");
        copyEditMenuItem.addActionListener(listener);
        pasteEditMenuItem = new MenuItem("Paste");
        pasteEditMenuItem.addActionListener(listener);
        consoleWindowMenuItem = new MenuItem("Console Window");
        consoleWindowMenuItem.setEnabled(false);
        consoleWindowMenuItem.addActionListener(listener);
        plotWindowMenuItem = new MenuItem("Plot Window");
        plotWindowMenuItem.addActionListener(listener);
        aboutHelpMenuItem = new MenuItem("About...");
        aboutHelpMenuItem.addActionListener(listener);
        fileMenu.add(newFileMenuItem);
        fileMenu.add(openFileMenuItem);
        fileMenu.add(separator1);
        fileMenu.add(saveFileMenuItem);
        fileMenu.add(saveAsFileMenuItem);
        fileMenu.add(separator1);
        fileMenu.add(checkForUpdatesMenuItem);
        fileMenu.add(separator2);
        fileMenu.add(exitFileMenuItem);
        editMenu.add(cutEditMenuItem);
        editMenu.add(copyEditMenuItem);
        editMenu.add(pasteEditMenuItem);
        windowMenu.add(consoleWindowMenuItem);
        windowMenu.add(plotWindowMenuItem);
        helpMenu.add(aboutHelpMenuItem);

        this.setMenuBar(mainMenuBar);
    }

    /**Interpret the last command line entered*/
    public void interpretLine(String line)
    {
        String answerString = interpreter.executeExpression(line);
        answer.displayText(answerString);
        answer.displayPrompt();
    }

    public static void main (String[] args)
    {
        GUI myGui = new GUI(args);
    }

    public void windowActivated(WindowEvent e)
    {
    }

    public void windowClosed(WindowEvent e)
    {
    }

    public void windowClosing(WindowEvent e)
    {
        close();
    }

    public void windowDeactivated(WindowEvent e)
    {
    }

    public void windowDeiconified(WindowEvent e)
    {
    }

    public void windowIconified(WindowEvent e)
    {
    }

    public void windowOpened(WindowEvent e)
    {
    }
}
