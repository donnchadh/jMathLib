package jmathlib.ui.awt;

import java.awt.*;

/**Simple dialog extension that creates a new non-modal and centered dialog.*/
public class AboutGUIDialog extends GUIDialog
{
	private static final int LEFT = 80;

	private Label content0Label;
	private Label content1Label;
	private Label content2Label;
	private Label content3Label;

	public AboutGUIDialog(Frame _owner)
	{
		super(_owner, "About JMathLib");
		setVisible(false);
		setSize(250, 135);
		setResizable(false);
		// Let's initialize the labels
		content0Label = new Label("The JMathLib team (by now)");
		content0Label.setLocation(getInsets().left+40, getInsets().top+30);
		content0Label.setSize(200, 21);
		content0Label.setFont(new Font("Dialog", Font.BOLD, 12));
		content0Label.setForeground(new Color(8421504));

        content1Label = new Label("Stefan Mueller");
        content1Label.setLocation(getInsets().left+LEFT, getInsets().top+60);
        content1Label.setSize(100, 21);

        content2Label = new Label("Mark Sparshatt");
		content2Label.setLocation(getInsets().left+LEFT, getInsets().top+80);
		content2Label.setSize(100, 21);

		content3Label = new Label("Alejandro Torras");
		content3Label.setLocation(getInsets().left+LEFT, getInsets().top+100);
		content3Label.setSize(100, 21);

		add(content0Label);
		add(content1Label);
		add(content2Label);
		add(content3Label);
		setVisible(true);
	}
}
