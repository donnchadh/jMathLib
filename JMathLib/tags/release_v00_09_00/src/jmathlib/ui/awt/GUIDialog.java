package jmathlib.ui.awt;

import java.awt.*;
import java.awt.event.*;

/**Simple dialog extension that creates a new non-modal and centered dialog.*/
public class GUIDialog extends Dialog implements WindowListener
{
	public GUIDialog(Frame _owner, String _title)
	{
		super(_owner, _title, true);
		setLayout(null);
		setVisible(false);
		this.setBackground(_owner.getBackground());
		this.addWindowListener(this);
	}

	public synchronized void show()
	{
		// Get the size of the screen
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		// position the frame in the centre of the screen
		setLocation((d.width-getSize().width) / 2, (d.height-getSize().height) / 2);
		super.show();
	}

	public void windowActivated(WindowEvent e)
	{
	}

	public void windowClosed(WindowEvent e)
	{
	}

	public void windowClosing(WindowEvent e)
	{
		dispose();
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
