package jmathlib.ui.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.metal.MetalIconFactory;
import java.io.*;
import java.util.*;

public class WorkingDirectoryPanel extends JPanel {

    private JComboBox workingDirectory = new JComboBox();
    private DefaultComboBoxModel workingDirectoryModel = new DefaultComboBoxModel();
    private JButton setWorkingDirectory = new JButton();
    private DefaultListModel listModel = new DefaultListModel();
    private JList fileView = new JList(listModel);
    private ListSelectionListener fileViewSelectionListener;

    File filesDirectory;

    public WorkingDirectoryPanel() {
        initialiseGUI();
        registerListeners();        
        File cwd = KeyHandler.runningReference.interpreter.getFunctionManager().getWorkingDirectory();
        if (cwd != null) {
            setWorkingDirectory(cwd, false);
        }
    }

    private void initialiseGUI() {
        setLayout(new GridBagLayout());
                
        JLabel label = new JLabel("Working Directory:");
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 2;
        cons.weighty = 0.1;
        add(label, cons);

        workingDirectory.setModel(workingDirectoryModel);
        workingDirectory.setEditable(true);
        workingDirectory.setRenderer(createRender2());
        workingDirectory.getMinimumSize().width = 2;
        workingDirectory.getMaximumSize().width = 40;

        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridwidth = 1;
        cons.weightx = 1;
        cons.weighty = 0.1;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(5, 5, 0, 0);
        add(workingDirectory, cons);

        setWorkingDirectory.setIcon(new MetalIconFactory.FolderIcon16());
        cons.gridx = 1;
        cons.gridy = 1;
        cons.gridwidth = 2;
        cons.weightx = 0.1;
        cons.weighty = 0.1;
        cons.insets = new Insets(5, 5, 0, 0);
        add(setWorkingDirectory, cons);

        cons.gridx = 0;
        cons.gridy = 2;
        cons.gridwidth = 2;
        cons.weighty = 0.1;
        add(new JLabel("Files:"), cons);

        cons.gridx = 0;
        cons.gridy = 3;
        cons.gridwidth = 2;
        cons.insets = new Insets(5, 5, 0, 0);
        cons.fill = GridBagConstraints.BOTH;
        cons.weighty = 1;
        cons.gridheight = GridBagConstraints.REMAINDER;
        cons.gridwidth = GridBagConstraints.REMAINDER;
        add(new JScrollPane(this.fileView), cons);
        fileView.setCellRenderer(createRender());
    }

    private void registerListeners() {
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getSource() == fileView) {
                    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (fileView.getSelectedIndex() != -1) {
                            Object sel = fileView.getSelectedValue();
                            if (sel instanceof UpDirectory) {
                                UpDirectory up = (UpDirectory) sel;
                                setWorkingDirectory(up.getDirectory(), false);
                            } else if (sel instanceof File) {
                                File file = (File) sel;
                                if (file.isDirectory()) {
                                    setWorkingDirectory(file, false);
                                    return;
                                }
                                SwingGUI.runningReference.openFileInEditor(file);
                            }
                        }
                    }
                } else if (event.getSource() == workingDirectory.getEditor().getEditorComponent()) {
                    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                        JTextField field = (JTextField) workingDirectory.getEditor().getEditorComponent();
                        String text = field.getText();
                        setWorkingDirectory(new File(text), true);
                    }
                }
            }
        };
        this.fileView.addKeyListener(keyListener);
        this.workingDirectory.getEditor().getEditorComponent().addKeyListener(keyListener);
        
        MouseAdapter selectionListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                super.mouseClicked(event);
                if (event.getSource() == fileView) {
                    if (event.getClickCount() >= 2) {
                        Object c = fileView.getSelectedValue();
                        if (c instanceof UpDirectory) {
                            UpDirectory up = (UpDirectory) c;
                            setWorkingDirectory(up.getDirectory(), false);
                        } else if (c instanceof File) {
                            File file = (File) fileView.getSelectedValue();
                            if (file.isDirectory()) {
                                setWorkingDirectory(file, false);
                            } else SwingGUI.runningReference.openFileInEditor(file);
                        }
                    }
                } else if (event.getSource() == setWorkingDirectory) {
                    JFileChooser jfc = new JFileChooser();
                    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int result = jfc.showOpenDialog(SwingGUI.runningReference);
                    if (result == JFileChooser.APPROVE_OPTION && jfc.getSelectedFile() != null) {
                        setWorkingDirectory(jfc.getSelectedFile(), true);
                    }
                }
            }
        };

        this.fileView.addMouseListener(selectionListener);
        this.setWorkingDirectory.addMouseListener(selectionListener);
    }

    private DefaultListCellRenderer createRender() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel c = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
					cellHasFocus);
                if (value instanceof File) {
                    c.setText(FileSystemView.getFileSystemView().getSystemDisplayName((File) value));
                    c.setToolTipText(((File) value).getPath());
                    c.setIcon(FileSystemView.getFileSystemView().getSystemIcon((File) value));
                } else if (value instanceof UpDirectory) {
                    UpDirectory ud = (UpDirectory) value;
                    c.setText("../ (" + FileSystemView.getFileSystemView().getSystemDisplayName(ud.getDirectory()) + ")");
                    c.setToolTipText(ud.getDirectory().getAbsolutePath());
                    c.setIcon(FileSystemView.getFileSystemView().getSystemIcon(ud.getDirectory()));
                }
                return c;
            }
        };
    }

    private DefaultListCellRenderer createRender2() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel c = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
					cellHasFocus);
                if (value instanceof File) {
                    c.setText(((File) value).getAbsolutePath());
                    c.setToolTipText(((File) value).getPath());
                    c.setIcon(FileSystemView.getFileSystemView().getSystemIcon((File) value));
                }
                return c;
            }
        };
    }

    public void setWorkingDirectory(File dir, boolean remember) {
        if (!dir.exists()) {
            JOptionPane.showMessageDialog(this, "Folder Does not Exist", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!dir.isDirectory()) {
            JOptionPane.showMessageDialog(this, "Is not a Directory", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //Set the working directory in Mathlib (to find function files)
        KeyHandler.runningReference.interpreter.getFunctionManager().setWorkingDirectory(dir);
        
        //Update the list view (ie files in the current working directory)
        updateFileList(dir);
        
        //updating JComboBox working directory stuff
        if (remember) {   
            File currentValue = (File)workingDirectory.getSelectedItem();
            if (currentValue != null)
              updateDirectoryHistory(currentValue);
        }
        this.workingDirectory.setSelectedItem(dir.getAbsoluteFile());        
    }
    
    private void updateFileList(File currentWorkingDir) {
        listModel.removeAllElements();
        if (!FileSystemView.getFileSystemView().isRoot(currentWorkingDir) && 
            !FileSystemView.getFileSystemView().isFileSystemRoot(currentWorkingDir)) {
          UpDirectory up = new UpDirectory(FileSystemView.getFileSystemView().getParentDirectory(currentWorkingDir));
          listModel.addElement(up);
        }

        File[] children = order(currentWorkingDir.listFiles(new EasyFileFilter(new String[]{".m"}, "MATHLAB Scripts (.m)", true)));
        for (int i = 0; i < children.length; i++) {
            listModel.addElement(children[i]);
        }    
    }
    
    private void updateDirectoryHistory(File dir) {
        int index = workingDirectoryModel.getIndexOf(dir);
        if (index != -1) {
          //Bring the previous entry to the head of the history
          Object obj = workingDirectoryModel.getElementAt(index);
          workingDirectoryModel.removeElementAt(index);
          workingDirectory.insertItemAt(obj, 0);
        } else {
          if (workingDirectoryModel.getSize() > 10) {
            workingDirectoryModel.removeElementAt(workingDirectoryModel.getSize() - 1);
          }
          this.workingDirectory.insertItemAt(dir, 0);
        }
    }

    private File[] getFiles(File[] files) {
        int counter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                counter++;
            }
        }
        File[] only = new File[counter];
        counter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                only[counter] = files[i];
                counter++;
            }
        }
        return only;
    }

    private File[] getDirectorys(File[] files) {
        int counter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                counter++;
            }
        }
        File[] only = new File[counter];
        counter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                only[counter] = files[i];
                counter++;
            }
        }
        return only;
    }

    public File[] getOrderedCombined(File[] directorys, File[] files) {
        File[] ordered = new File[directorys.length + files.length];
        
         //Arrays.copyOf(directorys, directorys.length + files.length);

        for (int i = 0; i < directorys.length; i++) {
            ordered[i] = directorys[i];
        }

        int insertPoint = directorys.length;
        for (int i = 0; i < files.length; i++) {
            ordered[insertPoint] = files[i];
        }
        return ordered;
    }

    public File[] order(File[] unordered) {
        return getOrderedCombined(getDirectorys(unordered), getFiles(unordered));
    }

    public static class UpDirectory {

        private File directoryAbove;

        public UpDirectory(File directoryAbove) {
            if (directoryAbove == null)
                throw new IllegalArgumentException("Unexpected non-null directory");
            this.directoryAbove = directoryAbove;
        }

        public File getDirectory() {
            return this.directoryAbove;
        }
    }
}