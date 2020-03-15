import java.awt.MouseInfo;
import java.awt.PointerInfo;

public class main 
{
	static Frame frame;
	static boolean firstRound = false;
	public static void main(String[] args)
	{
		int durchl�ufe = 100;
		if(args.length > 0) {
			durchl�ufe = Integer.parseInt(args[0]);
		}
		frame = new Frame(durchl�ufe);
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
				frame.vorl�ufigesBPoint = a.getLocation();
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
