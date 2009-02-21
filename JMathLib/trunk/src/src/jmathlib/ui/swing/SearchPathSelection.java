package jmathlib.ui.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.metal.MetalIconFactory;
import jmathlib.core.functions.*;


/** Panel that allows the user to change the application properties
 *  TODO: Make this look nicer. Internationalise the strings.
 */


public class SearchPathSelection extends JPanel {

    public static final int OK_BUTTON = JOptionPane.OK_OPTION;
    public static final int CANCEL_BUTTON = JOptionPane.CANCEL_OPTION;

    private DefaultListModel model = new DefaultListModel();
    private JList list = new JList(model);

    private JButton addButton = new JButton("Add");
    private JButton removeButton = new JButton("Remove");
    //TODO: Some nice graphics here.
    private JButton upButton = new JButton("^");
    private JButton downButton = new JButton("v");


    public SearchPathSelection() {
        init();
    }

    //adds function loaders that are not already in the model
    public void addFunctionLoader(FileFunctionLoader fl) {
        if (!model.contains(fl)) {
            this.model.addElement(fl);
        }
    }

    public int getFunctionLoaderCount() {
        return model.getSize();
    }

    public FileFunctionLoader getFunctionLoader(int index) {
        return (FileFunctionLoader) model.elementAt(index);
    }

    /*
     * blocks till dialog closed
     */
    public static void showPathSelectionDialog(Component container) {
        SearchPathSelection p = new SearchPathSelection();
        FunctionManager fm = KeyHandler.runningReference.interpreter.globals.getFunctionManager();
        for (int i = 0; i < fm.getFunctionLoaderCount(); i++) {
            FunctionLoader fl = fm.getFunctionLoader(i);
            if (fl instanceof FileFunctionLoader) {
                p.addFunctionLoader((FileFunctionLoader) fl);
            }
        }

        if (JOptionPane.showConfirmDialog(container, p, "Select Search Paths", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
            //Pass 1: Remove functionloaders that are not in the new set
            for (int i = 0; i < fm.getFunctionLoaderCount(); i++) {
                FunctionLoader fl = fm.getFunctionLoader(i);
                if (fl instanceof FileFunctionLoader) {
                    boolean found = false;
                    for (int j = 0; j < p.getFunctionLoaderCount(); j++) {
                        if (p.getFunctionLoader(j).equals(fl)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        fm.removeFunctionLoader(fl);
                        //Adjust loop (this is bad i know!) Better to use an Iterator.
                        i--;
                    }
                }
            }
            //Pass 2: Add new functionloaders in the new set
            for (int j = 0; j < p.getFunctionLoaderCount(); j++) {
                FileFunctionLoader fl = p.getFunctionLoader(j);
                if (j >= fm.getFunctionLoaderCount()) {
                    fm.addFunctionLoader(fl);
                } else if (!fl.equals(fm.getFunctionLoader(j))) {
                    fm.addFunctionLoaderAt(j, fl);
                }
            }
        }
    }


    private void init() {
        setPreferredSize(new Dimension(500, 250));
        this.setLayout(new BorderLayout());
        DefaultListCellRenderer render = new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel c = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
                FileFunctionLoader f = (FileFunctionLoader) value;
                File basePath = f.getBaseDirectory();
                if (FileSystemView.getFileSystemView().isFileSystem(basePath)) {
                    c.setText(basePath.toString());
                } else {
                    c.setText(FileSystemView.getFileSystemView().getSystemDisplayName(basePath));
                }
                c.setToolTipText(basePath.getPath());
                if (f.isSystemLoader()) {
                    c.setEnabled(false);
                } else {
                    c.setEnabled(true);
                }
                return c;
            }
        };
        this.list.setCellRenderer(render);
        this.add(new JScrollPane(list), BorderLayout.CENTER);
        JPanel buttonContainer = new JPanel();
        this.add(buttonContainer, BorderLayout.EAST);
        GridBagLayout glayout = new GridBagLayout();
        buttonContainer.setLayout(glayout);
        addButton.setIcon(new MetalIconFactory.FolderIcon16());
        removeButton.setIcon(new MetalIconFactory.PaletteCloseIcon());

        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(5, 5, 5, 5);
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.anchor = GridBagConstraints.CENTER;


        buttonContainer.add(upButton, cons);
        cons.gridy = 1;
        buttonContainer.add(downButton, cons);

        cons.gridx = 1;
        cons.gridy = 0;
        buttonContainer.add(addButton, cons);
        cons.gridy = 1;
        buttonContainer.add(removeButton, cons);

        final Component container = this;
        MouseAdapter mouseListener = new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent event) {
                super.mouseReleased(event);
                if (event.getSource() == addButton) {
                    JFileChooser jfc = new JFileChooser();
                    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    jfc.setMultiSelectionEnabled(true);
                    int result = jfc.showOpenDialog(SwingGUI.runningReference);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File[] dirs = jfc.getSelectedFiles();
                        if (dirs != null) {
                            for (int i = 0; i < dirs.length; i++) {
                                addFunctionLoader(new FileFunctionLoader(dirs[i], false));
                            }
                        }
                    }
                } else if (event.getSource() == removeButton) {
                    if (list.getSelectedIndex() != -1) {
                        FileFunctionLoader fl = (FileFunctionLoader) model.elementAt(list.getSelectedIndex());
                        if (fl.isSystemLoader()) {
                            JOptionPane.showMessageDialog(container, "Cannot remove a System Function Loader");
                        } else {
                            model.remove(list.getSelectedIndex());
                        }
                    }
                } else if (event.getSource() == upButton) {
                    int selIndex = list.getSelectedIndex();
                    if (selIndex != -1 && selIndex > 0) {
                        int newIndex = selIndex - 1;

                        Object src = model.elementAt(selIndex);
                        Object dst = model.elementAt(newIndex);

                        model.setElementAt(dst, selIndex);
                        model.setElementAt(src, newIndex);
                        list.setSelectedIndex(newIndex);
                    }
                } else if (event.getSource() == downButton) {
                    int selIndex = list.getSelectedIndex();
                    if ((selIndex != -1) && selIndex < (model.getSize() - 1)) {
                        int newIndex = selIndex + 1;

                        Object src = model.elementAt(selIndex);
                        Object dst = model.elementAt(newIndex);

                        model.setElementAt(dst, selIndex);
                        model.setElementAt(src, newIndex);
                        list.setSelectedIndex(newIndex);
                    }
                }
            }
        };
        addButton.addMouseListener(mouseListener);
        removeButton.addMouseListener(mouseListener);
        upButton.addMouseListener(mouseListener);
        downButton.addMouseListener(mouseListener);
    }
}
