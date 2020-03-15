import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener
{
	@Override
	public void mouseClicked(MouseEvent arg0) {

		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		//sending Frame first Position
		PointerInfo a = MouseInfo.getPointerInfo();
		main.frame.vorläufigesAPoint = a.getLocation();
		main.firstRound = true;
		main.frame.isClicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		//sending Frame second Position
		
		main.frame.bpoint = new Point(e.getX(), e.getY());
		main.frame.apoint = main.frame.vorläufigesAPoint;
		main.frame.isClicked = false;
		main.frame.repaint();
	}

}
