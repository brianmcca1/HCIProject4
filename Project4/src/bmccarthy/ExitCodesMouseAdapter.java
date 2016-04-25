package bmccarthy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class ExitCodesMouseAdapter extends MouseAdapter{
	ManPage page;
	HashMap<String, ManPage> pages;
	
	public ExitCodesMouseAdapter(ManPage page, HashMap<String, ManPage> pages){
		this.page = page;
		this.pages = pages;
	}
	
	public void mouseClicked(MouseEvent e){
		ExitCodesView exitCodesView = new ExitCodesView(this.page, this.pages, 1);
		exitCodesView.launch();
	}
}
