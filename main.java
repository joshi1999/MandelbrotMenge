import java.awt.MouseInfo;
import java.awt.PointerInfo;

public class main 
{
	static Frame frame;
	static boolean firstRound = false;
	public static void main(String[] args)
	{
		int durchläufe = 100;
		if(args.length > 0) {
			durchläufe = Integer.parseInt(args[0]);
		}
		frame = new Frame(durchläufe);
		while(true)
		{
			if(frame.isClicked)
			{
				if(firstRound)
				{
					frame.savedOffImage = frame.savedAppleMan;
					firstRound = false;
				}
				PointerInfo a = MouseInfo.getPointerInfo();
				frame.vorläufigesBPoint = a.getLocation();
				frame.repaint();
			}
			else
			{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
