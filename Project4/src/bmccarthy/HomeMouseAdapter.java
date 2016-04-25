package bmccarthy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JFrame;

public class HomeMouseAdapter extends MouseAdapter{
	
	HashMap<String, ManPage> pages;
	JFrame currentFrame;
	
	public HomeMouseAdapter( HashMap<String, ManPage> pages, JFrame currentFrame){
		
		this.pages = pages;
		this.currentFrame = currentFrame;
	}
	public void mouseClicked(MouseEvent e){
		HomeView homeView = new HomeView(this.pages);
		currentFrame.setVisible(false);
		homeView.launch();
	}
}
