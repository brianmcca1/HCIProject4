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

public class PageView {

	private JFrame frame;
	
	ManPage page;
	HashMap<String, ManPage> pages;

	/**
	 * Launch the application.
	 */
	public void launch() {
		initialize(page, pages);
		
	}

	/**
	 * Create the application.
	 */
	public PageView(ManPage page, HashMap<String, ManPage> pages) {
		this.page = page;
		this.pages = pages;
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize(ManPage page, HashMap<String, ManPage> pages) {
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
		JLabel homeLink = new JLabel("<html><u>Home</u></html>");
		homeLink.setFont(new Font("Gotham Light", Font.PLAIN, 18));
		homeLink.setForeground(Color.BLUE);
		homeLink.setBounds(45, 100, 80, 30);
		homeLink.addMouseListener(new HomeMouseAdapter(pages, frame));
		homeLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		index.add(homeLink);
		
		JLabel pageShortcuts[] = new JLabel[pages.size()];
		int i = 0;
		for(Map.Entry<String, ManPage> entry: pages.entrySet()){
			pageShortcuts[i] = new JLabel(entry.getKey());
			
			pageShortcuts[i].setFont(new Font("Gotham Light", Font.PLAIN, 18));
			pageShortcuts[i].setBounds(45, 150 + 50 * i, 80, 30);
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
		
		JLabel lblName = new JLabel(page.getName());
		lblName.setBounds(30, 20, 182, 83);
		mainPanel.add(lblName);
		lblName.setFont(new Font("Gotham Light", Font.PLAIN, 32));
		
		JLabel synopsis = new JLabel(page.getSynopsis());
		synopsis.setFont(new Font("Gotham Light", Font.PLAIN, 20));
		synopsis.setBounds(30, 84, 429, 67);
		mainPanel.add(synopsis);
		
		JLabel description = new JLabel("<html>" + page.getDescription() + "</html>");
		description.setVerticalAlignment(SwingConstants.TOP);
		description.setFont(new Font("Helvetica", Font.PLAIN, 18));
		description.setBounds(30, 142, 429, 109);
		mainPanel.add(description);
		
		JLabel flagsLink = new JLabel("<html><u>FLAGS</u></html>");
		flagsLink.setFont(new Font("Gotham Light", Font.BOLD, 20));
		flagsLink.setForeground(Color.BLUE);
		flagsLink.setBounds(30, 240, 83, 49);
		flagsLink.addMouseListener(new FlagsMouseAdapter(page, pages, frame));
		flagsLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainPanel.add(flagsLink);
		
		JLabel author = new JLabel("<html>" + page.getAuthor() + "</html>");
		author.setFont(new Font("Helvetica", Font.PLAIN, 15));
		author.setBounds(30, 507, 459, 43);
		mainPanel.add(author);
		
		JLabel exitCodesLink = new JLabel("<html><u>EXIT CODES</u></html>");
		exitCodesLink.setForeground(Color.BLUE);
		exitCodesLink.setFont(new Font("Gotham Light", Font.BOLD, 20));
		exitCodesLink.setBounds(30, 307, 135, 49);
		exitCodesLink.addMouseListener(new ExitCodesMouseAdapter(page, pages, frame));
		exitCodesLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainPanel.add(exitCodesLink);
		
		JLabel examplesLink = new JLabel("<html><u>EXAMPLES</u></html>");
		examplesLink.setForeground(Color.BLUE);
		examplesLink.setFont(new Font("Gotham Light", Font.BOLD, 20));
		examplesLink.setBounds(30, 384, 135, 49);
		examplesLink.addMouseListener(new ExamplesMouseAdapter(page, pages, frame));
		examplesLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainPanel.add(examplesLink);
		
		
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					
//					this.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
		this.frame.setVisible(true);
	}
}
