package bmccarthy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JFrame;

public class BackMouseAdapter extends MouseAdapter{
	ManPage page;
	HashMap<String, ManPage> pages;
	JFrame currentFrame;
	
	public BackMouseAdapter(ManPage page, HashMap<String, ManPage> pages, JFrame currentFrame){
		this.page = page;
		this.pages = pages;
		this.currentFrame = currentFrame;
		
	}
	
	public void mouseClicked(MouseEvent e){
		PageView pageView = new PageView(this.page, this.pages);
		currentFrame.setVisible(false);
		pageView.launch();
	}
}
