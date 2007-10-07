package jmathlib.core.graphics;

import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.graphics.properties.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;

/** created and holds the axes of a plot, there can be multiple axes in one plot*/
public class FigureObject extends GraphicalObject implements WindowListener, Printable, ActionListener
{  
	private class FigurePanel extends Panel
	{
		FigurePanel()
		{
			super();
		}

		public void update(Graphics g)
		{
            ErrorLogger.debugLine("FigureOb: update");
			paint(g);
		}

		public void add(AxesObject ax)
		{
			super.add(ax.getComponent());
		}

		public void add(AxesObject ax, int index)
		{
			super.add(ax.getComponent(), index);
		}
	}

	/**The number of this figure*/
	private int figureNo = 0;

	/** number of tiles in horizontal direction*/
	private int gridX = 1;

	/** number of tiles in vertical direction*/
	private int gridY = 1;

	/**An array of the axes*/	
	private AxesObject[][] figureElements = new AxesObject[gridY][gridX];

	/**The current axes where all plot commands will be directed to */
	private int currentAxes = 1;

	/**A pointer to the graphics manager*/
	private GraphicsManager gM;

	// size of frame;
	private int dyFrame = 500;
	private int dxFrame = 500;

	/*Holds all elements of a figure. The panel is organized as a grid*/
	private Frame       fig = new Frame();
	private FigurePanel p   = new FigurePanel();

    // properties
    private BooleanProperty BackingStoreP = new BooleanProperty(this, "BackingStore", true);
    // private CloseRequestFcnP
    private ColorProperty colorP = new ColorProperty(this, "Color", Color.lightGray);

    private ColormapProperty ColormapP = new ColormapProperty(this, "Colormap");
    // private CurrentCharacterP
    // private CurrentPointP
    private BooleanProperty DockControlsP = new BooleanProperty(this, "DockControls", true);
    private BooleanProperty DoubleBufferP = new BooleanProperty(this, "DoubleBuffer", true);
    // private FileNameP
    // private FixedColorsP
    private BooleanProperty IntegerHandleP = new BooleanProperty(this, "IntegerHandle", true);
    private BooleanProperty InvertHardcopyP = new BooleanProperty(this, "InvertHardcopy", true);
    // private KeyPressFcnP
    // private MenuBarP
    // private NameP
    // private NextPlotP
    private BooleanProperty NumberTitleP = new BooleanProperty(this, "NumberTitle", true);
    // private PaperOrientationP
    // private PaperPositionP
    // private PaperSizeP
    // private PaperTypeP
    // private PaterUnitsP
    // private PointerP
    // private PointerShapeCDataP
    // private PositionP
    // private RendererP
    // private RendererModeP
    private BooleanProperty ResizeP = new BooleanProperty(this, "Resize", true);
    // private ResizeFcnP
    // private SelectionTypeP
    private BooleanProperty ShareColorsP = new BooleanProperty(this, "ShareColors", true);
    // private ToolBarP
    // private UIContextMenuP
    private UnitsProperty UnitsP = new UnitsProperty(this, "Units", "pixels");
    // private WVisualP
    // private WVisualModeP
    // private WindowButtonDownFcnP
    // private WindowButtonMotionFcnP
    // private WindowButtonUpFcnP
    // private WindowStyleP
    
	public FigureObject(int _figureNo)
	{  

        figureNo = _figureNo;

		// setup window frame
		fig.setSize(dxFrame, dyFrame);
		fig.setTitle("Figure No."+figureNo);
		fig.setBackground( colorP.getColor() );
		fig.addWindowListener(this);

		// create initial axes object
		figureElements[0][0] = new AxesObject();

		// setup internal layout
		p.setLayout(new GridLayout(gridY, gridX));
		p.add(figureElements[0][0]);
		fig.add(p);
        fig.setResizable(ResizeP.isSet());

		// other initialization
		initMenuBar();
		fig.setVisible(true);
	}

    /**
     * set up menue for figure window
     *
     */
	private void initMenuBar()
	{
		MenuBar mbar = new MenuBar();

		Menu fileMenu = new Menu("File");
		fileMenu.add(new MenuItem("New Figure", new MenuShortcut(KeyEvent.VK_N, false)));
		fileMenu.add(new MenuItem("Close", new MenuShortcut(KeyEvent.VK_W, false)));
		fileMenu.add("Close All");
		fileMenu.addSeparator();
		fileMenu.add(new MenuItem("Print...", new MenuShortcut(KeyEvent.VK_P, false)));
		fileMenu.addActionListener(this);
		mbar.add(fileMenu);
		
		Menu editMenu = new Menu("Edit");
		mbar.add(editMenu);

		Menu viewMenu = new Menu("View");
		mbar.add(viewMenu);

		Menu insertMenu = new Menu("Insert");
		mbar.add(insertMenu);

		Menu toolsMenu = new Menu("Tools");
		mbar.add(toolsMenu);

		Menu helpMenu = new Menu("Help");
		helpMenu.add("Check for Updates");
		helpMenu.add("About JMathLib");
		mbar.add(helpMenu);

		fig.setMenuBar(mbar);
	}

	//public void addAxes(AxesObject _axes) 
	//{
		//figureElements.addElement(_axes);
		//currentAxes = figureElements.indexOf(_axes);
		//ErrorLogger.debugLine("FigureObject: addElement: "+currentAxes);
	//}

	public AxesObject getCurrentAxes() 
	{
		// numbering starts with 1 (top left) increases to the right
		// 1 | 2 | 3 | 4
		// 5 | 6 | 7 | 8
		int y = (int)(currentAxes / gridX);
		if (y*gridX == currentAxes) y--;

		int x = (int)(currentAxes - y*gridX) - 1;
		if (figureElements[y][x] == null)
			figureElements[y][x] = new AxesObject();

		return figureElements[y][x];
	}

    public PolarAxesObject getCurrentPolarAxes() 
    {
        ErrorLogger.debugLine("FigureObject: getcurrentpolaraxes");
        // numbering starts with 1 (top left) increases to the right
        // 1 | 2 | 3 | 4
        // 5 | 6 | 7 | 8
        int y = (int)(currentAxes / gridX);
        if (y*gridX == currentAxes) y--;

        int x = (int)(currentAxes - y*gridX) - 1;

        if (figureElements[y][x] instanceof PolarAxesObject)
            return (PolarAxesObject)figureElements[y][x];
        else if (figureElements[y][x] == null)
            figureElements[y][x] = new PolarAxesObject();
        else
            figureElements[y][x] = new PolarAxesObject();
        
        p.remove(currentAxes-1);
        p.add(figureElements[y][x], currentAxes-1);
        p.validate();

        return (PolarAxesObject)figureElements[y][x];
    }

	public void convertCurrentAxesTo3DAxes()
	{
		//ErrorLogger.debugLine("FigureObject: convertCurrentAxesTo3DAxes");

		if (getCurrentAxes() instanceof Axes3DObject) return;

		// somehow remove following 4 lines      
		int y = (int)(currentAxes / gridX);
		if (y*gridX == currentAxes) y--;

		int x = (int)(currentAxes - y*gridX) - 1;

		figureElements[y][x]= new Axes3DObject();

		p.remove(currentAxes-1);
		p.add(figureElements[y][x], currentAxes-1);
		p.validate();
	}

	public int getFigureNumber()
	{
		return figureNo;
	}

	/**devide the current figure into n*m tiles*/
	public void setSubPlot(int _gridY, int _gridX, int _currentAxes)
	{
		// numberring starts with 1 (top left) increases to the right
		// 1 | 2 | 3 | 4
		// 5 | 6 | 7 | 8   (example of setSubPlot(2,4,x))
		//ErrorLogger.debugLine("Figure:setSubPlot: ");

		if (_currentAxes<= _gridY*_gridX)	currentAxes = _currentAxes;
		else  							    currentAxes = 1;

		// check if only currentAxes is changed
		if ((gridY==_gridY) && (gridX==_gridX)) return;

		// gridY and/or gridX is changed => make new layout
		gridY       = _gridY;
		gridX       = _gridX;

		p.removeAll();
		p.setLayout(new GridLayout(gridY,gridX));
		figureElements = new AxesObject[gridY][gridX];

		for (int y=0; y<figureElements.length; y++)
		{
			for (int x=0; x<figureElements[y].length; x++)
			{
				//ErrorLogger.debugLine("FigureObject: subplot : "+y+" "+x);
				//if (figureElements[y][x]!=null)
				figureElements[y][x] = new AxesObject();
				p.add(figureElements[y][x]);
			}	
		}

		p.validate();
	}

    /**
     *
     */
    public void clearFigure()
    {
        p.removeAll();
        p.setLayout(new GridLayout(1,1));

        for (int y=0; y<figureElements.length; y++)
        {
            for (int x=0; x<figureElements[y].length; x++)
            {
                //ErrorLogger.debugLine("FigureObject: subplot : "+y+" "+x);
                //if (figureElements[y][x]!=null)
                figureElements[y][x] = null;
            }   
        }
        p.validate();
        p.repaint();
        p.show();
    }
    
	/**
	 * method for printing the graphics window to an external printer
	 */
	public int print(Graphics g, PageFormat pf, int page)  throws PrinterException
	{
		//ErrorLogger.debugLine("FigureObject: print : page="+page );

		// get printable area
		int yPos = (int)pf.getImageableY();
		int xPos = (int)pf.getImageableX();
		int yMax = (int)pf.getImageableHeight();
		int xMax = (int)pf.getImageableWidth();
		//ErrorLogger.debugLine("FigureObject: xPos="+xPos+" yPos="+yPos);
		//ErrorLogger.debugLine("FigureObject: xMax="+xMax+" yMax="+yMax);

		double rPage = (double)yMax/(double)xMax;
		double rFig = (double)fig.getSize().height/(double)fig.getSize().width;

		if (rPage > rFig)
		{
			yMax = (int)(rFig*xMax);
			yPos += ((int)pf.getImageableHeight()-yMax)/2;
		}
		else
		{
			xMax = (int)(yMax/rFig);
			xPos += ((int)pf.getImageableWidth()-xMax)/2;
		}

		// draw black rectangle around printing area
		g.setColor(Color.black);
		g.drawRect(xPos, yPos, xMax, yMax);

		//this.setBackground(Color.lightGray);    

		// create a new panel and group all elements which need to be printed
		//  -> is is done in order to get the correct size of the elements
		FigurePanel pP = new FigurePanel();
		pP.setLayout(new GridLayout(gridY, gridX));
		for (int y=0; y<figureElements.length; y++)
		{
			for (int x=0; x<figureElements[y].length; x++)
			{
				//ErrorLogger.debugLine("FigureObject: print : "+y+" "+x);
				pP.add(figureElements[y][x]);
			}    
		}

		// needed?
		pP.setSize(xMax,yMax);
		//pP.setBounds(xPos,yPos,xMax,yMax);

		// move origin of graphical context to left top corner
		//  of the visible page layout
		g.translate(xPos, yPos);

		int xTransSum=0;
		int yTransSum=0;
		int xTrans=0;
		int yTrans=0;

		// print axes on the printer-page
		for (int y=0; y<figureElements.length; y++)
		{
			yTrans=0;
			for (int x=0; x<figureElements[y].length; x++)
			{
				//ErrorLogger.debugLine("FigureObject: print-method : "+y+" "+x);
				//ErrorLogger.debugLine("figure:size x="+figureElements[y][x].getSize().width+" y="+figureElements[y][x].getSize().height);

				figureElements[y][x].getComponent().setSize(xMax, yMax);
				xTrans = figureElements[y][x].getSize().width;
				yTrans = Math.max(yTrans, figureElements[y][x].getSize().height);

				figureElements[y][x].setBackBuffer(false);
				figureElements[y][x].paint(g);
				figureElements[y][x].setBackBuffer(true);

				// move origin to next figure element
				g.translate(xTrans, 0);
				xTransSum += xTrans;
			}    

			// move origin back to left side and 
			// move origin down one row
			g.translate(-xTransSum, 0);
			g.translate(0, yTrans);

			// remember moving down
			yTransSum += yTrans;

		}

		// go back to origin "top left"
		g.translate(0, -yTransSum);


		// copy figure elements back to original panel on the screen
		for (int y=0; y<figureElements.length; y++)
		{
			for (int x=0; x<figureElements[y].length; x++)
			{
				//ErrorLogger.debugLine("FigureObject: copy back : "+y+" "+x);
				//if (figureElements[y][x]!=null)
				p.add(figureElements[y][x]);
			}    
		}

		// return one valid page (page-no. 0)
		if (page==0) 
			return PAGE_EXISTS;
		else
			return NO_SUCH_PAGE;
	}

	public void close()
	{
		close(true);
	}

	public void close(boolean notify)
	{
		if (notify)
			gM.removeFigure(figureNo);
		fig.dispose();
	}

	public void windowClosing(WindowEvent e)
	{
		//ErrorLogger.debugLine("FigureObject: windowClosing: "+figureNo);
		this.close();
	}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowOpened(WindowEvent e){}

	public void setGraphicsManager(GraphicsManager _gM)
	{
		gM = _gM;
	}

	public void actionPerformed(ActionEvent ev)
	{
		String cmd = ev.getActionCommand();
		if (cmd.equals("Close"))
			gM.closeFigure(figureNo);
		else if (cmd.equals("New Figure"))
			gM.createNewFigure();
		else if (cmd.equals("Close All"))
			gM.closeAll();
		else if (cmd.equals("Print..."))
			doPrint();
	}

	private void doPrint()
	{
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		if (job.printDialog())
		{
			try
			{
				job.print();
			}
			catch (PrinterException e)
			{
			}
		}
	}

    public void repaint()
    {
        //super.repaint();
    }

}
