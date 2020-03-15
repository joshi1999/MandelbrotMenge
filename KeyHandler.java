import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
	Frame f = main.frame;
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_X)
		{
			main.frame.amaxX = main.frame.amaxY = 2;
			main.frame.aminX = main.frame.aminY = -2;
			main.frame.apoint = new Point(0, 0);
			main.frame.bpoint = new Point(main.frame.getWidth(), main.frame.getHeight());
			main.frame.repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
			f.verlaufPos--;
			f.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_X)
		{
			main.frame.amaxX = main.frame.amaxY = 2;
			main.frame.aminX = main.frame.aminY = -2;
			main.frame.apoint = new Point(0, 0);
			main.frame.bpoint = new Point(main.frame.getWidth(), main.frame.getHeight());
			main.frame.repaint();
		}
	}

}
