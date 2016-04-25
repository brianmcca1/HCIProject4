package bmccarthy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JFrame;

public class ExitCodesMouseAdapter extends MouseAdapter{
	ManPage page;
	HashMap<String, ManPage> pages;
	JFrame currentFrame;
	
	public ExitCodesMouseAdapter(ManPage page, HashMap<String, ManPage> pages, JFrame currentFrame){
		this.page = page;
		this.pages = pages;
		this.currentFrame = currentFrame;
	}
	
	public void mouseClicked(MouseEvent e){
		ExitCodesView exitCodesView = new ExitCodesView(this.page, this.pages, 1);
		currentFrame.setVisible(false);
		exitCodesView.launch();
	}
}
