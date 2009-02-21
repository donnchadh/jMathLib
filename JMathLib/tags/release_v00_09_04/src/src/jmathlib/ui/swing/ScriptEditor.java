package jmathlib.ui.swing;

import java.io.File;
import java.io.FileInputStream;

import javax.swing.JEditorPane;
import javax.swing.text.DefaultEditorKit;

public class ScriptEditor extends JEditorPane{
	
	public ScriptEditor(File file){
		this.setEditorKit(new DefaultEditorKit());
		try{
			this.setPage(file.toURI().toURL());
			//this.read(new FileInputStream(file), null);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
