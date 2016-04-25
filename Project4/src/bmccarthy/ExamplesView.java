package bmccarthy;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ExamplesView {

	private JFrame frame;
	
	ManPage page;
	HashMap<String, ManPage> pages;
	int pageNum;

	/**
	 * Launch the application.
	 */
	public void launch() {
		initialize(page, pages, pageNum);
		
	}

	/**
	 * Create the application.
	 */
	public ExamplesView(ManPage page, HashMap<String, ManPage> pages, int pageNum) {
		this.page = page;
		this.pages = pages;
		this.pageNum = pageNum;
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize(ManPage page, HashMap<String, ManPage> pages, int pageNum) {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Color c = frame.getBackground();
		
		JPanel index = new JPanel();
		index.setBounds(0, 0, 175, 561);
		frame.getContentPane().add(index); 
		index.setLayout(null);
		index.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.darkGray));
		
		JLabel indexTitle = new JLabel("<HTML><U>INDEX</U></HTML>");
		indexTitle.setHorizontalAlignment(SwingConstants.CENTER);
		indexTitle.setFont(new Font("Gotham Light", Font.BOLD, 24));
		indexTitle.setBounds(10, 21, 155, 56);
		index.add(indexTitle);
		
		// Create the index with all names in the man page
		JLabel pageShortcuts[] = new JLabel[pages.size()];
		int i = 0;
		for(Map.Entry<String, ManPage> entry: pages.entrySet()){
			pageShortcuts[i] = new JLabel(entry.getKey());
			
			pageShortcuts[i].setFont(new Font("Gotham Light", Font.PLAIN, 18));
			pageShortcuts[i].setBounds(45, 100 + 50 * i, 80, 30);
			if(page.getName() == entry.getKey()){
				// This is the page we're currently on, so indicate that
				pageShortcuts[i].setForeground(Color.BLACK);
			} else {
				// These are links, so indicate that
				pageShortcuts[i].setForeground(Color.BLUE);
				// Underlines the text
				Font font = pageShortcuts[i].getFont();
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				pageShortcuts[i].setFont(font.deriveFont(attributes));
				pageShortcuts[i].addMouseListener(new IndexMouseAdapter(entry.getValue(), pages, frame));
				pageShortcuts[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
				// TODO: links to other pages, based on the name
			}
			index.add(pageShortcuts[i]);
			i++;
		}
//		JLabel lblNewLabel = new JLabel("New label");
//		lblNewLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));
//		lblNewLabel.setBounds(10, 11, 79, 32);
//		index.add(lblNewLabel);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(null);
		mainPanel.setBounds(185, 0, 499, 561);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblName = new JLabel(page.getName() + ": Examples");
		lblName.setBounds(30, 20, 369, 83);
		mainPanel.add(lblName);
		lblName.setFont(new Font("Gotham Light", Font.PLAIN, 32));
		
		i = (1 - pageNum) * 7; // 7 per page, so start wherever left off (or at 0 on page 1)
		JLabel exampleCommands[] = new JLabel[page.getExamples().size()];
		JLabel exampleMeanings[] = new JLabel[page.getExamples().size()];
		
		//TODO: REFACTOR THIS!!
		// Only works on 1 page, because the iteration doesn't know it's moving pages
		// Convert to an array and then iterate in a standard for loop?
		// Maybe even sort it and pass a starting point? Might be too much though
		// OR: Just abandon this entirely. Not worth the time? Maybe put a pin in it for now?
		
		for(Map.Entry<String, String> entry: page.getExamples().entrySet()){
			if(i <= (pageNum * 7) - 1){
				// Can only fit 7 items at once, so don't place any more than that
				exampleCommands[i] = new JLabel(entry.getKey());
				exampleCommands[i].setBounds(30, 90 + 90*i, 300, 20);
				exampleCommands[i].setFont(new Font("Gotham Light", Font.PLAIN, 18));
				
				exampleMeanings[i] = new JLabel("<html>" + entry.getValue() + "</html>");
				exampleMeanings[i].setBounds(50, 100 + 90*i, 450, 70);
				exampleMeanings[i].setFont(new Font("Helvetica", Font.PLAIN, 15));
				exampleMeanings[i].setAlignmentX(JLabel.TOP);
				
				mainPanel.add(exampleCommands[i]);
				mainPanel.add(exampleMeanings[i]);
				i++;
			} 
		}
		
		if(page.getExitCodes().size() > 6){
			// Requires a second page, so put in a "Next" and/or "Prev" button and page tracker
			JLabel pageNumLabel = new JLabel("Page " + pageNum);
			pageNumLabel.setFont(new Font("Gotham Light", Font.PLAIN, 12));
			pageNumLabel.setBounds(200, 495, 75, 60);
			mainPanel.add(pageNumLabel);
			
			if(pageNum < (page.getExamples().size() / 6)){
				// We need a "Next" button unless this is the last page
				JLabel nextPageLabel = new JLabel("<html><u>NEXT</html></u>");
				nextPageLabel.setFont(new Font("Gotham Light", Font.PLAIN, 12));
				nextPageLabel.setForeground(Color.BLUE);
				nextPageLabel.setBounds(275, 495, 75, 60);
				//nextPageLabel.addMouseListener(new NextMouseAdapter(page, pages, pageNum));
				mainPanel.add(nextPageLabel);
			}
			
			if(pageNum > 1){
				// Similarly, we need a "Prev" button unless this is the first page
				JLabel prevPageLabel = new JLabel("<html><u>PREV</html></u>");
				prevPageLabel.setFont(new Font("Gotham Light", Font.PLAIN, 12));
				prevPageLabel.setForeground(Color.BLUE);
				prevPageLabel.setBounds(125, 495, 75, 60);
				prevPageLabel.addMouseListener(new PrevMouseAdapter(page, pages, pageNum));
				mainPanel.add(prevPageLabel);
			}
			
		}
		
		
		JLabel backLink = new JLabel("<html><u>BACK</u></html>");
		backLink.setFont(new Font("Gotham Light", Font.BOLD, 20));
		backLink.setForeground(Color.BLUE);
		backLink.setBounds(375, 495, 75, 59);
		backLink.addMouseListener(new BackMouseAdapter(page, pages, frame));
		backLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainPanel.add(backLink);
		
		
		this.frame.setVisible(true);
	}
}
