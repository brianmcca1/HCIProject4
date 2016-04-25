package bmccarthy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class BackMouseAdapter extends MouseAdapter{
	ManPage page;
	HashMap<String, ManPage> pages;
	
	public BackMouseAdapter(ManPage page, HashMap<String, ManPage> pages){
		this.page = page;
		this.pages = pages;
	}
	
	public void mouseClicked(MouseEvent e){
		PageView pageView = new PageView(this.page, this.pages);
		pageView.launch();
	}
}
