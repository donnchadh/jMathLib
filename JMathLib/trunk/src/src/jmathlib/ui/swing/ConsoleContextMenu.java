package jmathlib.ui.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * This class creates the console context menu at mouse's right click on the
 * console.
 */
public class ConsoleContextMenu extends MouseAdapter
{

    JPopupMenu sjpm = null;


    public ConsoleContextMenu()
    {
    }


    @Override
    public void mousePressed(MouseEvent mev)
    {
        int b = mev.getButton();
        if  ((b & MouseEvent.BUTTON2) == MouseEvent.BUTTON2)
        {
            showPopupMenu(mev, true);
        }
        else if ((b & MouseEvent.BUTTON1) == MouseEvent.BUTTON1)
        {
            showPopupMenu(mev, false);
        }
    }


    private void showPopupMenu(MouseEvent mev, boolean show)
    {
        SwingGUI ref = SwingGUI.runningReference;
        JPopupMenu jpm = sjpm;
        if  (show)
            {
                if (jpm == null)
                    {
                        jpm = new JPopupMenu();
                        sjpm = jpm;
                        int j = ref.jMenu2.getItemCount();
                        JMenuItem[] jmi = new JMenuItem[j];
                        // See DN0009
                        for (int i = 0; i < j; i++)
                            {
                                JMenuItem jmit;
                                jmit= ref.jMenu2.getItem(i);
                                if (jmit == null)
                                    {
                                        jpm.addSeparator();
                                        continue;
                                    }
                                jmi[i] = new JMenuItem();
                                jmi[i].setText(jmit.getText());
                                jmi[i].setMnemonic(jmit.getMnemonic());
                                ActionListener[] al = jmit.getActionListeners();
                                for (int ii = al.length-1; ii >= 0; ii--)
                                    {
                                        jmi[i].addActionListener(al[ii]);
                                    }
                                FocusListener[] fl = jmit.getFocusListeners();
                                for (int ii = fl.length-1; ii >= 0; ii--)
                                    {
                                        jmi[i].addFocusListener(fl[ii]);
                                    }
                                jpm.add(jmi[i]);
                            }
                    }
                Console c = (Console) mev.getSource();
                // Now let's place the context menu taking care about not
                // drawing it outside the screen
                Point p = c.getLocationOnScreen();
                Point q = mev.getPoint();
                p.x += q.x;
                p.y += q.y;
                Dimension jpmd = jpm.getSize();
                q.x = p.x + jpmd.width;
                q.y = p.y + jpmd.height;
                Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
                if (q.x > sd.width)
                    {
                        p.x -= jpmd.width;
                    }
                if (q.y > sd.height)
                    {
                        p.y -= jpmd.height;
                    }
                jpm.setLocation(p);
                jpm.setVisible(true);
            }
        else if (!show && jpm != null)
            {
                jpm.setVisible(false);
            }
    }
}
