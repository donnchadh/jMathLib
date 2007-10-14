package jmathlib.core.graphics.axes.coreObjects;

import jmathlib.core.graphics.axes.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class TextObject extends AxesObject 
{
	public static final int H_LEFT   = 0;
	public static final int H_CENTER = 1;
	public static final int H_RIGHT  = 2;
	public static final int V_TOP    = 0;
	public static final int V_MIDDLE = 1;
	public static final int V_BOTTOM = 3;

	private int hAlign = H_LEFT;
	private int vAlign = V_TOP;
	private Font font = null;
	private Content content;
	private int rotation = 0;

	class SimpleFactory
	{
		private String buffer;
		private LinkedList list;
		private int anchor = 0, current = 0;

		SimpleFactory(String txt, LinkedList lst)
		{
			buffer = txt;
			list = lst;
		}

		int matchBrace(int start)
		{
			int depth = 0;
			while (start < buffer.length())
			{
				switch (buffer.charAt(start))
				{
				case '{': depth++; break;
				case '}': depth--; if (depth == 0) return start;
				default: break;
				}
				start++;
			}
			return -1;
		}

		String getArgument(int start)
		{
			if (start >= buffer.length())
				return null;
			if (buffer.charAt(start) == '{')
			{
				int pos = matchBrace(start);
				if (pos < 0)
					return null;
				else
				{
					anchor = pos+1;
					return buffer.substring(start+1, pos);
				}
			}
			else
			{
				anchor = start+1;
				return buffer.substring(start, start+1);
			}
		}

		void flush()
		{
			if (current > anchor)
			{
				list.add(new Element(buffer.substring(anchor, current)));
				anchor = current;
			}
		}

		void parse()
		{
			current = anchor;
			while (current < buffer.length())
			{
				switch (buffer.charAt(current))
				{
				case '^':
				case '_':
					flush();
					String arg = getArgument(current+1);
					if (arg != null)
					{
						if (buffer.charAt(current) == '_')
							list.add(new SubscriptElement(arg));
						else if (buffer.charAt(current) == '^')
							list.add(new SuperscriptElement(arg));
						current = anchor;
					}
					else
						current++;
					break;
				default:
					current++;
					break;
				}
			}

			flush();
		}

	}

	class Element
	{
		String text;
		Rectangle rect;
		
		Element(String txt)
		{
			text = txt;
		}

		void render(Graphics2D g)
		{
			g.drawString(text, 0, 0);
		}

		Rectangle layout(Graphics2D g)
		{
			FontMetrics fm = g.getFontMetrics();
			rect = new Rectangle(0, -fm.getMaxDescent(), fm.stringWidth(text), fm.getMaxDescent()+fm.getMaxAscent());
			return rect;
		}
	}

	class LineElement extends Element
	{
		private LinkedList elements = new LinkedList();

		LineElement(String txt)
		{
			super(txt);

			SimpleFactory f = new SimpleFactory(txt, elements);
			f.parse();
		}

		Rectangle layout(Graphics2D g)
		{
			Iterator it = elements.iterator();
			FontMetrics fm = g.getFontMetrics();

			rect = new Rectangle(0, -fm.getMaxDescent(), 0, fm.getMaxAscent()+fm.getMaxDescent());
			while (it.hasNext())
			{
				Element e = (Element)it.next();
				Rectangle eRect = e.layout(g);
				eRect.x = rect.width;
				rect = rect.union(eRect);
			}
			return rect;
		}

		void render(Graphics2D g)
		{
			Iterator it = elements.iterator();
			int xoffset = 0, yoffset = (rect.height + rect.y);

			g.translate(0, yoffset);
			while (it.hasNext())
			{
				Element e = (Element)it.next();
				e.render(g);
				g.translate(e.rect.width, 0);
				xoffset += e.rect.width;
			}
			g.translate(-xoffset, -yoffset);
		}

		void add(Element e)
		{
			elements.add(e);
		}
	}

	class SubscriptElement extends LineElement
	{
		SubscriptElement(String txt)
		{
			super(txt);
		}

		Font getSubscriptFont(Font f)
		{
			return new Font(f.getFamily(), f.getStyle(), f.getSize()-2);
		}

		Rectangle layout(Graphics2D g)
		{
			Font currentFont = g.getFont();
			Font subscriptFont = getSubscriptFont(currentFont);

			g.setFont(subscriptFont);
			super.layout(g);
			g.setFont(currentFont);
			rect.y -= (rect.height+rect.y)/2;

			return rect;
		}

		void render(Graphics2D g)
		{
			Font currentFont = g.getFont();
			Font subscriptFont = getSubscriptFont(currentFont);

			g.setFont(subscriptFont);
			super.render(g);
			g.setFont(currentFont);
		}
	}

	class SuperscriptElement extends LineElement
	{
		SuperscriptElement(String txt)
		{
			super(txt);
		}

		Font getSuperscriptFont(Font f)
		{
			return new Font(f.getFamily(), f.getStyle(), f.getSize()-2);
		}

		Rectangle layout(Graphics2D g)
		{
			Font currentFont = g.getFont();
			Font subscriptFont = getSuperscriptFont(currentFont);

			g.setFont(subscriptFont);
			super.layout(g);
			g.setFont(currentFont);

			FontMetrics fm = g.getFontMetrics();
			rect.y += fm.getMaxAscent()/2;

			return rect;
		}

		void render(Graphics2D g)
		{
			Font currentFont = g.getFont();
			FontMetrics fm = g.getFontMetrics();
			int ascent = fm.getMaxAscent();
			Font subscriptFont = getSuperscriptFont(currentFont);

			g.setFont(subscriptFont);
			g.translate(0, -(rect.height+rect.y)-ascent/2);
			super.render(g);
			g.translate(0, (rect.height+rect.y)+ascent/2);
			g.setFont(currentFont);
		}
	}

	class Content extends Element
	{
		private LineElement[] lines;

		Content(String txt)
		{
			super(txt);
			String[] txtLines = txt.split("\n", -1);
			lines = new LineElement[txtLines.length];
			for (int i=0; i<txtLines.length; i++)
			{
				lines[i] = new LineElement(txtLines[i]);
			}
		}

		Rectangle layout(Graphics2D g)
		{
			return lines[0].layout(g);
		}

		void render(Graphics2D g)
		{
			lines[0].render(g);
		}
	}

	public TextObject(String txt)
	{
		this(txt, H_LEFT, V_TOP);
	}

	public TextObject(String txt, int hAlign, int vAlign)
	{
		super();
		this.content = new Content(txt);
		this.hAlign = hAlign;
		this.vAlign = vAlign;
	}

	public void setAlign(int hAlign, int vAlign)
	{
		if (hAlign >= 0)
			this.hAlign = hAlign;
		if (vAlign >= 0)
			this.vAlign = vAlign;
	}

	public void setRotation(int angle)
	{
		this.rotation = angle;
	}

	public void paint(Graphics g)
	{
		Font oldFont = (font != null ? g.getFont() : null);

		if (font != null)
			g.setFont(font);

		Graphics2D g2d = (Graphics2D)g;
		Rectangle r = content.layout(g2d);
		int xoff = 0, yoff = 0;

		switch (hAlign)
		{
		case H_LEFT:
			break;
		case H_RIGHT:
			xoff -= r.width;
			break;
		case H_CENTER:
			xoff -= r.width/2;
			break;
		}

		switch (vAlign)
		{
		case V_TOP:
			break;
		case V_BOTTOM:
			yoff -= r.height;
			break;
		case V_MIDDLE:
			yoff -= r.height/2;
			break;
		}

		java.awt.geom.AffineTransform m = g2d.getTransform();
		//Object oldHint = g2d.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
		//g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.translate(xOrig, yOrig);
		g2d.rotate(rotation*Math.PI/180.0);
		g2d.translate(xoff, yoff);
		content.render(g2d);
		g2d.setTransform(m);
		//g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, oldHint);
		//g2d.drawRect(xOrig-2, yOrig-2, 5, 5);

		if (font != null)
			g.setFont(oldFont);
	}
}
