package bmccarthy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class FlagsMouseAdapter extends MouseAdapter{
	ManPage page;
	HashMap<String, ManPage> pages;
	
	public FlagsMouseAdapter(ManPage page, HashMap<String, ManPage> pages){
		this.page = page;
		this.pages = pages;
	}
	
	public void mouseClicked(MouseEvent e){
		FlagsView flagsView = new FlagsView(this.page, this.pages, 1);
		flagsView.launch();
	}
}
