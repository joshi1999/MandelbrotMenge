import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Frame extends GameFrame
{
	public boolean debug = false;
	public Point vorläufigesAPoint = new Point(0, 0);
	public Point vorläufigesBPoint = new Point(getWidth(), getHeight());
	public boolean isClicked = false;
	public Point apoint = new Point(0, 0);
	public Point bpoint = new Point(getWidth(), getHeight());
	ArrayList<double[]> verlauf = new ArrayList<>(); //aminx, aminy, amaxx, amaxy
	int verlaufPos = 0; //Position in der ArrayList
	double aminX = -2;
	double amaxX = 2;
	double aminY = -2;
	double amaxY = 2;
	int durchläufe;
	int pixelFertig = 0;
	int pixelInsgesamt;
	BufferedImage savedOffImage; //Lässt das ständige Neuberechnen des Männchens beim Ziehen der Zoomecken wegfallen.
	public Frame(int durchläufe) 
	{
		super("Mandelbrot-Menge");
		this.durchläufe = durchläufe;
		setSize(1000, 1000);
		pixelInsgesamt = 1000 * 1000;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(false);
		setVisible(true);
		addMouseListener(new MouseHandler());
		addKeyListener(new KeyHandler());
		bpoint = new Point(getWidth(), getHeight());
		double[] a = {-2, -2, 2, 2};
		verlauf.add(a);
	}
	
	public void activateClicked()
	{
		isClicked = true;
	}

	public void bigPaint(Graphics g)
	{
		if(!isClicked)
		{
			calculate();
			drawAppleMan(g);
		}
		else
		{
			g.drawImage(savedOffImage, 0, 0, this);
		}
		drawSizes(g);
		drawRect(g);
	}
	
	public void drawSizes(Graphics g)
	{
		g.setColor(Color.GREEN);
		Font f = new Font("Arial", 10, 10);
		g.setFont(f);
		g.drawString("Max X:" + amaxX + "; Min X:" + aminX + "; Max Y:" + amaxY + "; Min Y:" + aminY, 20, getHeight() - 20);
	}
	
	public void drawRect(Graphics g)
	{
		if(isClicked)
		{
			g.setColor(Color.RED);
			g.drawRect((int)vorläufigesAPoint.getX() + getX(), (int)vorläufigesAPoint.getY() + getY(), (int)(vorläufigesBPoint.getX() - vorläufigesAPoint.getX()) + getX(), (int)(vorläufigesBPoint.getY() - vorläufigesAPoint.getY() + getY()));
		}
	}
	
	public void calculate()
	{
		double akx1 = aminX + ((amaxX-aminX)/(double)getWidth()*apoint.getX());
			double aky1 = aminY + ((amaxY-aminY)/(double)getHeight()*apoint.getY());
			double akx2 = aminX + ((amaxX-aminX)/(double)getWidth()*bpoint.getX());
			double aky2 = aminY + ((amaxY-aminY)/(double)getHeight()*bpoint.getY());
			
			if(debug)
				System.out.println(bpoint.getX() + " / " + bpoint.getY());
			aminX = akx1;
			amaxX = akx2;
			aminY = aky1;
			amaxY = aky2;
			double[] a = {aminX, aminY, amaxX, amaxY};
			verlauf.add(a);
			verlaufPos++;
			if(debug)
				System.out.println("minx:" + verlauf.get(verlaufPos)[0] + " maxX:" + verlauf.get(verlaufPos)[2] + " aminY:" + verlauf.get(verlaufPos)[1] + " amaxY:" + verlauf.get(verlaufPos)[3]);
	}
	
	public void drawAppleMan(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for(int i = 0; i < getWidth(); i++)
	    {
	        for(int j = 0; j < getHeight(); j++)
	        {
	            double akx = verlauf.get(verlaufPos)[0] + ((verlauf.get(verlaufPos)[2]-verlauf.get(verlaufPos)[0])/(double)getWidth())*i;
	            double aky = verlauf.get(verlaufPos)[1] + ((verlauf.get(verlaufPos)[3]-verlauf.get(verlaufPos)[1])/(double)getHeight())*j;
	            double a = akx;
	            double b = aky;
	            double x = akx;
	            double y = aky;
	            double newX = akx;
	            double newY = aky;
	            
	            for(int k = 0; k <  durchläufe; k++)
	            {
	                if(getDistance(x, y))
	                {
	                    newX = getNewX(x, y, a);
	                    newY = getNewY(x, y, b);
	                    x = newX;
	                    y = newY;
	                }
	                else
	                {
	                	float percents = k / (float) durchläufe;
	                	
	                	/*if(percents <= 0.25)
	                		g.setColor(Color.BLUE);
	                	else if(percents <= 0.5)
	                		g.setColor(Color.GREEN);
	                	else if(percents <= 0.75)
	                		g.setColor(Color.ORANGE);
	                	else if(percents <= 1)
	                		g.setColor(Color.RED);
	                	else*/
	                		g.setColor(Color.WHITE);
	                	
	                	
	                    break;
	                }
	            }
	            if(getDistance(x, y))
	            {
	            	g.setColor(Color.BLACK);
	                g.drawLine(i, j, i, j);
	            }
	            else
	            {
	            	g.drawLine(i, j, i, j);
	            }
	            pixelFertig++;
	        }
	    }
		pixelFertig = 0;
	}
	public boolean getDistance(double x1, double y1)
	{
		int x2 = 0;
	    int y2 = 0;
	      
	    double dist = Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
	    if(dist > 2) //Radius des Kreises (Durchmesser = 4) -> -2 -> 2
	    {
	        //tools.println("false" + dist);
	        return false;
	        
	    }
	    else
	    {
	        //tools.println("true" + dist);
	        return true;
	          
	    }
	}
	  
	public double getNewX(double x, double y, double a)
	{
	    double newX = (x*x) - (y*y) + a;
	    return newX;
	}
	  
	public double getNewY(double x, double y, double b)
	{
	    double newY = 2*x*y + b;
	    return newY;
	}
}
