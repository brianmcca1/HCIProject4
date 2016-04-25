package bmccarthy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class PrevMouseAdapter extends MouseAdapter{
	ManPage page;
	HashMap<String, ManPage> pages;
	int pageNum;
	
	public PrevMouseAdapter(ManPage page, HashMap<String, ManPage> pages, int pageNum){
		this.page = page;
		this.pages = pages;
		this.pageNum = pageNum;
	}
	
	public void mouseClicked(MouseEvent e){
		FlagsView flagsView = new FlagsView(this.page, this.pages, pageNum - 1);
		flagsView.launch();
	}
}
