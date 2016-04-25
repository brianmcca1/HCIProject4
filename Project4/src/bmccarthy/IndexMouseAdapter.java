package bmccarthy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class IndexMouseAdapter extends MouseAdapter{
	ManPage page;
	HashMap<String, ManPage> pages;
	
	public IndexMouseAdapter(ManPage page, HashMap<String, ManPage> pages){
		this.page = page;
		this.pages = pages;
	}
	public void mouseClicked(MouseEvent e){
		System.out.println("Clicked on!");
		PageView pageView = new PageView(this.page, this.pages);
		pageView.launch();
	}
}
