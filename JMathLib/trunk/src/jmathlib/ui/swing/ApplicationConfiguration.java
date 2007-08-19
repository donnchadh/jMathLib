package jmathlib.ui.swing;

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class ApplicationConfiguration extends Properties {
    private static final String SERIALIZED_FILENAME = "config.dat";


    public static final String SESSION_HISTORY_SESSIONCOUNT_PROPERTY = "Session.HistorySessionCount";
    public static final String SESSION_HISTORY_SESSIONCMDCOUNT_PROPERTY = "Session.HistorySessionCmdCount";
    public static final String SEARCH_PATH_PROPERTY = "Path.SearchPath";
    public static final String CURRENT_WORKING_DIRECTORY_PROPERTY = "Path.CurrentWorkingDirectory";

    private static File cachedConfigDir = null;
    private static ApplicationConfiguration config = null;

    private static Properties createDefaultProperties() {
        Properties props = new Properties();
        props.put(SESSION_HISTORY_SESSIONCOUNT_PROPERTY, "5");
        props.put(SESSION_HISTORY_SESSIONCMDCOUNT_PROPERTY, "99");
        props.put(SEARCH_PATH_PROPERTY, "");
        props.put(CURRENT_WORKING_DIRECTORY_PROPERTY, ".");
        return props;
    }

    private ApplicationConfiguration() {
        super(createDefaultProperties());

        File configPath = new File(getConfigurationDirectory(), SERIALIZED_FILENAME);
        try {
            if (configPath.exists() && configPath.canRead()) {
                this.load(new FileInputStream(configPath));
            }
        } catch (IOException ex) {
            //What to do here. Show message to user?
            //Main GUI class needs a logging window.
        }
    }      
    
    public void writeConfiguration() {
        File dir = ApplicationConfiguration.getConfigurationDirectory();
        if (dir != null) {
            File f = new File(dir, SERIALIZED_FILENAME);
            if (!f.exists() || f.canWrite()) {
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(f);
                    store(out, "CONFIG");
                } catch (Exception ex) {
                  //TODO: Need to see if there is some sort of error reporting mechanism                    
                } finally {                    
                  try {
                    out.close();
                  } catch (Exception ex) {}
                }
            }
        }
    }
    
    public static ApplicationConfiguration getInstance() {
        if (config == null) {
            config = new ApplicationConfiguration();
        }
        return config;
    }

    /**
     * Returns a file object to the directory containing all of the Mathlib configuration information.
     * Other classes may use this directory to store additional information
     * If the directory does not exist, it is attempted to be created.
     * If the directory cannot be created, this method will return null.
     */
    public static File getConfigurationDirectory() {
        if (cachedConfigDir == null) {
            String home = System.getProperty("user.home");
            if (home != null) {
                File f = new File(home, ".mathlib/");
                if (!f.exists()) {
                    if (f.mkdirs()) {
                        cachedConfigDir = f;
                    }
                } else cachedConfigDir = f;
            }
        }
        return cachedConfigDir;
    }
    
    public int getIntProperty(String key) {
        String val = this.getProperty(key);
        return Integer.parseInt(val);
    }
    
    public void setIntProperty(String key, int val) {
        this.setProperty(key, Integer.toString(val));
    }    
    
    public void showConfigurationDialog(Component container) {
        ConfigurationPanel p = new ConfigurationPanel();
        p.setSessionHistoryCount(getIntProperty(SESSION_HISTORY_SESSIONCOUNT_PROPERTY));
        p.setSessionCommandCount(getIntProperty(SESSION_HISTORY_SESSIONCMDCOUNT_PROPERTY));
        p.setCurrentWorkingDirectory(getProperty(CURRENT_WORKING_DIRECTORY_PROPERTY));
      
    
        if (JOptionPane.showConfirmDialog(container, p, "Modifiy Configuration Options", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
          setIntProperty(SESSION_HISTORY_SESSIONCOUNT_PROPERTY, p.getSessionHistoryCount());
          setIntProperty(SESSION_HISTORY_SESSIONCMDCOUNT_PROPERTY, p.getSessionCommandCount());
          setProperty(CURRENT_WORKING_DIRECTORY_PROPERTY, p.getCurrentWorkingDirectory());            
        }        
    }
}
