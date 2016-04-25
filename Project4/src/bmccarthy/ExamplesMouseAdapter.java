package bmccarthy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class ExamplesMouseAdapter extends MouseAdapter{
	ManPage page;
	HashMap<String, ManPage> pages;
	
	public ExamplesMouseAdapter(ManPage page, HashMap<String, ManPage> pages){
		this.page = page;
		this.pages = pages;
	}
	
	public void mouseClicked(MouseEvent e){
		ExamplesView examplesView = new ExamplesView(this.page, this.pages, 1);
		examplesView.launch();
	}
}
