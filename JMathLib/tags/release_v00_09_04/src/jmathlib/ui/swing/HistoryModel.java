package jmathlib.ui.swing;

import java.util.ArrayList;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import jmathlib.ui.swing.CommandHistoryManager.SessionCommandListener;
import jmathlib.ui.swing.CommandHistoryManager.Command;
import jmathlib.ui.swing.CommandHistoryManager.Session;

public class HistoryModel implements TreeModel{
	
	private RootNode root = new RootNode();
	private CommandHistoryManager commandHistory;
	private ArrayList<TreeModelListener> listeners;
	
	private SessionCommandListener listener;
	
	public HistoryModel(final CommandHistoryManager commandHistory){
		this.commandHistory = commandHistory;
		this.listeners = new ArrayList<TreeModelListener>();
		listener = new SessionCommandListener(){

			public void commandAdded(Command command) {
				for(int i = 0; i < listeners.size(); i++){
					TreeModelEvent event = new TreeModelEvent(commandHistory, new Object[]{root, command.getSession()});
					listeners.get(i).treeNodesInserted(event);
					listeners.get(i).treeStructureChanged(event);
				}
				
			}
			
		};
		this.commandHistory.addChangeListener(listener);
	}

	public void addTreeModelListener(TreeModelListener l) {
		this.listeners.add(l);
	}

	public Object getChild(Object parent, int index) {
		if(parent instanceof RootNode){
			return this.commandHistory.getSession(this.commandHistory.getSessionCount() - 1 - index);
		}else if(parent instanceof Session){
			Session session = (Session)parent;
			return session.getCommand(session.commandCount() - 1 - index);
		}
		return null;
	}
	
	public int indexReverser(int size, int currentIndex){
		return size - 1 - currentIndex;
	}

	public int getChildCount(Object parent) {
		if(parent instanceof RootNode){
			return this.commandHistory.getSessionCount();
		}else if(parent instanceof Session){
			Session session = (Session)parent;
			return session.commandCount();
		}
		return -1;
	}

	public int getIndexOfChild(Object parent, Object child) {
		if(parent instanceof RootNode){
			return indexReverser(this.commandHistory.getSessionCount(), this.commandHistory.indexOfSession((Session)child));
		}else if(parent instanceof Session){
			Session session = (Session)parent;
			return indexReverser(session.commandCount(), session.indexOfCommand((String)child));
		}
		return -1;
	}

	public Object getRoot() {
		return root;
	}

	public boolean isLeaf(Object node) {
		if(node instanceof RootNode){
			return false;
		}else if(node instanceof Session){
			return false;
		}else if(node instanceof Command){
			return true;
		}
		return false;
	}

	public void removeTreeModelListener(TreeModelListener l) {
		this.listeners.remove(l);
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
	}
	
	public static class RootNode {
		private static final String ROOT_NAME = "History";
		
		public String toString(){
			return ROOT_NAME;
		}
		
	}
	
	

}
