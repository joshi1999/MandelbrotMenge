import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class GameFrame extends JFrame
{
	public BufferedImage savedAppleMan;
	public GameFrame(String name)
	{
		super(name);
	}
	
	private final GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	
	public void bigPaint(Graphics g)
	{
		
	}
	
	private BufferedImage offImg;
	public void paint(Graphics g)
	{
		if(offImg == null || offImg.getWidth() != getWidth() || offImg.getHeight() != getHeight())
		{
			offImg = gfxConf.createCompatibleImage(getWidth(), getHeight());
			bigPaint(offImg.createGraphics());
		}
		
		g.drawImage(offImg,  0,  0, this);
		savedAppleMan = null;
		savedAppleMan = offImg;
		offImg = null;
	}
}