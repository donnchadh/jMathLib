package jmathlib.ui.swing;

import javax.swing.*;
import java.awt.*;

/** Panel that allows the user to change the application properties
 *  TODO: Make this look nicer. Internationalise the strings.
 */
public class ConfigurationPanel extends JPanel {

    /**
     *
     */
    JLabel jLabel1 = new JLabel("Session History Count");
    JLabel jLabel2 = new JLabel("Session History Item Count");
    JLabel jLabel4 = new JLabel("Current Working Directory");
    JTextField jTextField1 = new JTextField("Session History Count");
    JTextField jTextField2 = new JTextField("Session History Item Count");
    JTextField jTextField4 = new JTextField("Current Working Directory");

    public ConfigurationPanel() {
        initialiseGUI();
    }

    private void initialiseGUI() {
        setLayout(new GridBagLayout());

        JLabel label1 = new JLabel("Session History Count");
        GridBagConstraints cons = new GridBagConstraints();
        cons.anchor = GridBagConstraints.WEST;
        cons.gridx = 0;
        cons.gridy = 0;
        //cons.gridwidth = 2;
        cons.weighty = 0.1;
        add(label1, cons);
        
        ApplicationConfiguration config = ApplicationConfiguration.getInstance();
        
        String count = config.getProperty(ApplicationConfiguration.SESSION_HISTORY_SESSIONCOUNT_PROPERTY);
        JTextField textfield1 = new JTextField(count);
        textfield1.setColumns(3);
        cons.gridx = 1;
        cons.gridy = 0;
        //cons.gridwidth = 2;
        cons.weighty = 0.1;
        add(textfield1, cons);        

        JLabel label2 = new JLabel("Session History Item Count");
        cons.gridx = 0;
        cons.gridy = 1;
        //cons.gridwidth = 2;
        cons.weighty = 0.1;
        add(label2, cons);
        
        String item = config.getProperty(ApplicationConfiguration.SESSION_HISTORY_SESSIONCMDCOUNT_PROPERTY);
        JTextField textfield2 = new JTextField(item);
        textfield2.setColumns(3);        
        cons.gridx = 1;
        cons.gridy = 1;
        //cons.gridwidth = 2;
        cons.weighty = 0.1;
        add(textfield2, cons);        

        JLabel label3 = new JLabel("Search Path");
        cons.gridx = 0;
        cons.gridy = 2;
        //cons.gridwidth = 2;
        cons.weighty = 0.1;
        add(label3, cons);
        
        String search = config.getProperty(ApplicationConfiguration.SEARCH_PATH_PROPERTY);
        JTextField textfield3 = new JTextField(search);
        textfield3.setColumns(20);        
        cons.gridx = 1;
        cons.gridy = 2;
        //cons.gridwidth = 2;
        cons.weighty = 0.1;
        add(textfield3, cons);        

        JLabel label4 = new JLabel("Current Working Directory");       
        cons.gridx = 0;
        cons.gridy = 3;
        //cons.gridwidth = 2;
        cons.weighty = 0.1;
        add(label4, cons);

        String directory = config.getProperty(ApplicationConfiguration.CURRENT_WORKING_DIRECTORY_PROPERTY);
        JTextField textfield4 = new JTextField(directory);
        textfield4.setColumns(20);         
        cons.gridx = 1;
        cons.gridy = 3;
        //cons.gridwidth = 2;
        cons.weighty = 0.1;
        add(textfield4, cons);
    }
    
    public int getSessionHistoryCount() {
	return Integer.parseInt(jTextField1.getText());
    }
    
    public void setSessionHistoryCount(int count) {
	jTextField1.setText(Integer.toString(count));
    }    
    
    public int getSessionCommandCount() {
        return Integer.parseInt(jTextField2.getText());
    }
    public void setSessionCommandCount(int count) {
	jTextField2.setText(Integer.toString(count));
    }    
    
    public String getCurrentWorkingDirectory() {    
	return jTextField4.getText();
    }
    public void setCurrentWorkingDirectory(String cwd) {    
	jTextField4.setText(cwd);
    }    
}
