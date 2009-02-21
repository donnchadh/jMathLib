package jmathlib.ui.awt;

import java.awt.*;
import java.awt.event.*;

/**The <code>OptionsGUIDialog</code> class opens a dialog that shows the 
 * current options.<br>
 * You can change the options there, and save them to use on proper 
 * sessions*/
public class OptionsGUIDialog extends GUIDialog implements ActionListener
{
	private static final int LEFT = 80;

	private Button acceptButton;
	private Button defaultButton;
	private Button saveButton;
	private Checkbox my00Checkbox;
	private Checkbox my01Checkbox;
	private Label content0Label;

	/*
	-- Remove the comments if you wish to see a (not finished)
	-- cool version ;-)
	*/
	
	public void paint(Graphics g)
	{
		g.setColor(Color.lightGray);
		g.draw3DRect(5, 40, 200, 80, false);
	}
	

	public OptionsGUIDialog(Frame _owner)
	{
		super(_owner, "Options (disabled by now)");
		setVisible(false);
		setSize(400, 250);
		setResizable(false);

		// Let's initialize the components
		content0Label = new Label("Output options");
		content0Label.setBounds(getInsets().left+10, getInsets().top+30,
								152, 21);
		my00Checkbox = new Checkbox("Output window enabled", true);
		my00Checkbox.setBounds(getInsets().left+30, getInsets().top+55,
							   150, 21);
		my01Checkbox = new Checkbox("Close the stream");
		my01Checkbox.setBounds(getInsets().left+30, getInsets().top+80,
							   125, 21);
		acceptButton = new Button("Accept");
		acceptButton.setBounds(getInsets().left+70, getInsets().top+210,
							   100, 30);
		acceptButton.addActionListener(this);
		defaultButton = new Button("Default");
		defaultButton.setBounds(getInsets().left+180, getInsets().top+210,
								100, 30);
		defaultButton.addActionListener(this);
		saveButton = new Button("Save");
		saveButton.setBounds(getInsets().right+290, getInsets().top+210,
							 100, 30);
		saveButton.addActionListener(this);

		add(content0Label);
		add(my00Checkbox);
		add(my01Checkbox);
		add(acceptButton);
		add(defaultButton);
		add(saveButton);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		
		if (o == acceptButton) {
			// Let's check all the checkboxes
			this.dispose();
		} else if (o == defaultButton) {
		} else if (o == saveButton) {
		}
	}
}
