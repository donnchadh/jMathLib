package jmathlib.tools.treeanalyser;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.*;
import java.util.*;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.Function;
import jmathlib.core.interpreter.RootObject;

public class TreeAnalyser extends RootObject
{
    /** Window for showing Tree. */
    private JFrame            frame;
    /** Tree used for the example. */
    private JTree             tree;
    /** Tree model. */
    private DefaultTreeModel        treeModel;

    /** Constructs a new instance of the TreeAnalyser. */
    public TreeAnalyser(OperandToken opTok) 
	{

		try 
		{
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception exc) 
		{
		    System.err.println("Error loading L&F: " + exc);
		}

		JMenuBar   menuBar = constructMenuBar();
		JPanel     panel   = new JPanel(true);

		frame = new JFrame("MathLib - TreeAnalyser");
		frame.getContentPane().add("Center", panel);
		frame.setJMenuBar(menuBar);
		frame.setBackground(Color.lightGray);

		/* Create the JTreeModel. */
		DefaultMutableTreeNode root = new DefaultMutableTreeNode( new TreeData(null, Color.black, "Root") );	

		DefaultMutableTreeNode eNode = createTree( opTok );	

		root.add( eNode );

		treeModel = new SampleTreeModel(root);

		/* Create the tree. */
		tree = new JTree(treeModel);

		/* Make tree ask for the height of each row. */
		tree.setRowHeight(-1);

		/* Put the Tree in a scroller. */
		JScrollPane        sp = new JScrollPane();
		sp.setPreferredSize(new Dimension(300, 300));
		sp.getViewport().add(tree);

		/* And show it. */
		panel.setLayout(new BorderLayout());
		panel.add("Center", sp);
		panel.add("South", constructOptionsPanel());
	
		//frame.addWindowListener( new WindowAdapter() {
		//    public void windowClosing(WindowEvent e) {System.exit(0);}});

		frame.pack();
		frame.show();
    }

	/** recursively parse the tree of MathLib-expressions */
	private DefaultMutableTreeNode createTree( OperandToken token )
	{
		DefaultMutableTreeNode node       = new DefaultMutableTreeNode( "---" );
		String                 dataString = "";

		if (token instanceof Expression)
		{
			Expression expr = (Expression)token;

			/* create the tree node depending on the expressions visibility */
			node = new DefaultMutableTreeNode( "Expr" + isDisplayResult(expr));

			// process data field
			if (expr.getData() != null)
			{
			    dataString  = expr.getData().toString();
			    dataString += isDisplayResult(expr.getData());
			}
			DefaultMutableTreeNode dataNode = new DefaultMutableTreeNode( "Data: "+dataString );
			node.add( dataNode );

			// process children
			for (int i=0; i<expr.getNumberOfChildren(); i++)
			{
				DefaultMutableTreeNode subNode = createTree( expr.getChild(i) );
				node.add(subNode);
			}
		}
		else if (token instanceof ForOperatorToken)
		{
			ForOperatorToken forT = (ForOperatorToken)token;

			node = new DefaultMutableTreeNode( "for" );

			// process initialisation part
			DefaultMutableTreeNode initNode = new DefaultMutableTreeNode( "init" );
			DefaultMutableTreeNode subNode  = createTree( forT.getForInitialisation() );
			initNode.add(subNode);
			node.add(initNode);

			// process relation part
			DefaultMutableTreeNode relNode = new DefaultMutableTreeNode( "relation" );
			                       subNode = createTree( forT.getForRelation() );
			relNode.add(subNode);
			node.add(relNode);

			// process increment part
			DefaultMutableTreeNode incNode = new DefaultMutableTreeNode( "increment" );
			                       subNode = createTree( forT.getForIncrement() );
			incNode.add(subNode);
			node.add(incNode);

			// process code part
			DefaultMutableTreeNode codeNode = new DefaultMutableTreeNode( "code" );
			                       subNode  = createTree( forT.getForCode() );
			codeNode.add(subNode);
			node.add(codeNode);

		}
  		else if (token instanceof IfThenOperatorToken)
		{
			node = new DefaultMutableTreeNode( "if - then" );
			IfThenOperatorToken ifThenT = (IfThenOperatorToken)token;

			Iterator iter = ifThenT.iterator();
			String str = "if ";
			while(iter.hasNext())
			{
			    ConditionToken condition = ((ConditionToken)iter.next()); 
			    OperandToken relation = condition.getCondition();
			    if(relation != null)
			    {	
				DefaultMutableTreeNode ifNode = new DefaultMutableTreeNode( str + relation.toString());
				node.add(ifNode);
				ifNode.add(new DefaultMutableTreeNode( "then " + condition.getExpression().toString()));
			    }
       			    else
			    {	    
				node.add(new DefaultMutableTreeNode( "else " + condition.getExpression().toString()));
			    }
			    str = "elseif ";
			} 
		}
   		else if (token instanceof SwitchToken)
		{
			SwitchToken switchT = (SwitchToken)token;

			// switch(<data>)...
			if (switchT.getData() != null)
				dataString = switchT.getData().toString();

			node = new DefaultMutableTreeNode( "switch( "+dataString+" )" );

			// process cases
			Vector cases = switchT.getCases();

			for (int i=0; i<cases.size(); i++)
   		 	{
			    CaseToken caseToken = ((CaseToken)cases.elementAt(i));
    		
			    DefaultMutableTreeNode caseNode = new DefaultMutableTreeNode( caseToken.toString() );
			    DefaultMutableTreeNode subNode = createTree( caseToken.getExpression() );
			    caseNode.add(subNode);
			    node.add(caseNode);
			}

		}
		else if (token instanceof VariableToken)
		{
			node = new DefaultMutableTreeNode( ((VariableToken)token).getName() 
                                              + isDisplayResult( token ) );
		}
		else if (token instanceof FunctionToken)
		{
		    FunctionToken func = (FunctionToken)token;
		    node = new DefaultMutableTreeNode( "function " + func.getName() );

		    Function function = null;
		    try
		    {
			function = getFunctionManager().findFunction(func);
		    }
		    catch(Exception exception)
		    {
		    }

		    node.add(new DefaultMutableTreeNode( function.toString()));

		}
		else
		{
			if (token != null)
				node = new DefaultMutableTreeNode( token.toString() + isDisplayResult(token) );
			else
				node = new DefaultMutableTreeNode( "null" );
		}

		return node;
	}


    /** Constructs a JPanel containing check boxes for the different
      * options that tree supports. */
    private JPanel constructOptionsPanel() 
	{
	JCheckBox        aCheckbox;
	JPanel           retPanel   = new JPanel(false);
	JPanel           borderPane = new JPanel(false);

	borderPane.setLayout(new BorderLayout());
	retPanel.setLayout(new FlowLayout());

	aCheckbox = new JCheckBox("show handles");
	aCheckbox.setSelected(tree.getShowsRootHandles());
	aCheckbox.addChangeListener(new ShowHandlesChangeListener());
	retPanel.add(aCheckbox);

	aCheckbox = new JCheckBox("show root");
	aCheckbox.setSelected(tree.isRootVisible());
	aCheckbox.addChangeListener(new ShowRootChangeListener());
	retPanel.add(aCheckbox);

	aCheckbox = new JCheckBox("editable");
	aCheckbox.setSelected(tree.isEditable());
	aCheckbox.addChangeListener(new TreeEditableChangeListener());
	aCheckbox.setToolTipText("Triple click to edit");
	retPanel.add(aCheckbox);

	borderPane.add(retPanel, BorderLayout.CENTER);

	return borderPane;
    }

    /** Construct a menu. */
    private JMenuBar constructMenuBar() 
	{
		JMenu            menu;
		JMenuBar         menuBar = new JMenuBar();
		JMenuItem        menuItem;

		menu = new JMenu("File");
		menuBar.add(menu);

		menuItem = menu.add(new JMenuItem("Exit"));
		menuItem.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			System.exit(0);
		    }});

		/* Tree related stuff. */
		menu = new JMenu("Tree");
		menuBar.add(menu);		

		menuItem = menu.add(new JMenuItem("Add"));
		menuItem.addActionListener(new AddAction());
		menuItem = menu.add(new JMenuItem("Insert"));
		menuItem.addActionListener(new InsertAction());
		menuItem = menu.add(new JMenuItem("Reload"));
		menuItem.addActionListener(new ReloadAction());
		menuItem = menu.add(new JMenuItem("Remove"));
		menuItem.addActionListener(new RemoveAction());

		return menuBar;
    }

    /** Returns the TreeNode instance that is selected in the tree.
      * If nothing is selected, null is returned.
      */
    protected DefaultMutableTreeNode getSelectedNode() 
	{
		TreePath   selPath = tree.getSelectionPath();

		if(selPath != null)
		    return (DefaultMutableTreeNode)selPath.getLastPathComponent();
		return null;
    }

    //protected DefaultMutableTreeNode createNewNode(String name) 
	//{
	//	return new DynamicTreeNode(new TreeData(null, Color.black, name));
    //}

	/******************************************************************/
    /** AddAction is used to add a new item after the selected item.
      */
    class AddAction extends Object implements ActionListener
    {
	/** Number of nodes that have been added. */
	public int               addCount;

	/**
	  * Messaged when the user clicks on the Add menu item.
	  * Determines the selection from the Tree and adds an item
	  * after that.  If nothing is selected, an item is added to
	  * the root.
	  */
	public void actionPerformed(ActionEvent e) {
	    int               newIndex;
	    DefaultMutableTreeNode          lastItem = getSelectedNode();
	    DefaultMutableTreeNode          parent;

		System.out.println("action Perfomed");

	    /* Determine where to create the new node. */
	    if(lastItem != null) {
		parent = (DefaultMutableTreeNode)lastItem.getParent();
		if(parent == null) {
		    parent = (DefaultMutableTreeNode)treeModel.getRoot();
		    lastItem = null;
		}
	    }
	    else
		parent = (DefaultMutableTreeNode)treeModel.getRoot();
	    if(lastItem == null)
		newIndex = treeModel.getChildCount(parent);
	    else
		newIndex = parent.getIndex(lastItem) + 1;

	    /* Let the treemodel know. */
	    //treeModel.insertNodeInto(createNewNode("Added " +
		//			Integer.toString(addCount++)),
		//		     parent, newIndex);
	}
    } // End of SampleTree.AddAction


    /**
      * InsertAction is used to insert a new item before the selected item.
      */
    class InsertAction extends Object implements ActionListener
    {
	/** Number of nodes that have been added. */
	public int               insertCount;

	/**
	  * Messaged when the user clicks on the Insert menu item.
	  * Determines the selection from the Tree and inserts an item
	  * after that.  If nothing is selected, an item is added to
	  * the root.
	  */
	public void actionPerformed(ActionEvent e) {
	    int               newIndex;
	    DefaultMutableTreeNode          lastItem = getSelectedNode();
	    DefaultMutableTreeNode          parent;

	    /* Determine where to create the new node. */
	    if(lastItem != null) {
		parent = (DefaultMutableTreeNode)lastItem.getParent();
		if(parent == null) {
		    parent = (DefaultMutableTreeNode)treeModel.getRoot();
		    lastItem = null;
		}
	    }
	    else
		parent = (DefaultMutableTreeNode)treeModel.getRoot();
	    if(lastItem == null)
		newIndex = treeModel.getChildCount(parent);
	    else
		newIndex = parent.getIndex(lastItem);

	    /* Let the treemodel know. */
	    //treeModel.insertNodeInto(createNewNode("Inserted " +
		//			Integer.toString(insertCount++)),
		//		     parent, newIndex);
	}
    } // End of SampleTree.InsertAction


    /**
      * ReloadAction is used to reload from the selected node.  If nothing
      * is selected, reload is not issued.
      */
    class ReloadAction extends Object implements ActionListener
    {
	/**
	  * Messaged when the user clicks on the Reload menu item.
	  * Determines the selection from the Tree and asks the treemodel
	  * to reload from that node.
	  */
	public void actionPerformed(ActionEvent e) {
	    DefaultMutableTreeNode          lastItem = getSelectedNode();

	    if(lastItem != null)
		treeModel.reload(lastItem);
	}
    } // End of SampleTree.ReloadAction

    /**
      * RemoveAction removes the selected node from the tree.  If
      * The root or nothing is selected nothing is removed.
      */
    class RemoveAction extends Object implements ActionListener
    {
	/**
	  * Removes the selected item as long as it isn't root.
	  */
	public void actionPerformed(ActionEvent e) {
	    DefaultMutableTreeNode          lastItem = getSelectedNode();

	    if(lastItem != null && lastItem != (DefaultMutableTreeNode)treeModel.getRoot()) {
		treeModel.removeNodeFromParent(lastItem);
	    }
	}
    } // End of SampleTree.RemoveAction


    /**
      * ShowHandlesChangeListener implements the ChangeListener interface
      * to toggle the state of showing the handles in the tree.
      */
    class ShowHandlesChangeListener extends Object implements ChangeListener
    {
	public void stateChanged(ChangeEvent e) {
	    tree.setShowsRootHandles(((JCheckBox)e.getSource()).isSelected());
	}

    } // End of class SampleTree.ShowHandlesChangeListener


    /**
      * ShowRootChangeListener implements the ChangeListener interface
      * to toggle the state of showing the root node in the tree.
      */
    class ShowRootChangeListener extends Object implements ChangeListener
    {
	public void stateChanged(ChangeEvent e) {
	    tree.setRootVisible(((JCheckBox)e.getSource()).isSelected());
	}

    } // End of class SampleTree.ShowRootChangeListener


    /**
      * TreeEditableChangeListener implements the ChangeListener interface
      * to toggle between allowing editing and now allowing editing in
      * the tree.
      */
    class TreeEditableChangeListener extends Object implements ChangeListener
    {
	public void stateChanged(ChangeEvent e) {
	    tree.setEditable(((JCheckBox)e.getSource()).isSelected());
	}

    } // End of class SampleTree.TreeEditableChangeListener


	public String isDisplayResult(Token token)
    {
    	if (token.isDisplayResult())
        	return " (display)";
        else
            return "";
    }


	/***********    MAIN    ****************/
    static public void main(String args[]) {
		new TreeAnalyser(new Expression(null));
    }

}
