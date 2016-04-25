package bmccarthy;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

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

public class FlagsView {

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
	public FlagsView(ManPage page, HashMap<String, ManPage> pages, int pageNum) {
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
				pageShortcuts[i].addMouseListener(new IndexMouseAdapter(entry.getValue(), pages));
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
		
		i = (1 - pageNum) * 7; // 7 per page, so start wherever left off (or at 0 on page 1)
		JLabel flagNames[] = new JLabel[page.getFlags().size()];
		JLabel flagDescriptions[] = new JLabel[page.getFlags().size()];
		for(Map.Entry<String, String> entry: page.getFlags().entrySet()){
			if(i <= pageNum * 7){
				// Can only fit 7 items at once, so don't place any more than that
				flagNames[i] = new JLabel(entry.getKey());
				flagNames[i].setBounds(30, 90 + 60*i, 300, 20);
				flagNames[i].setFont(new Font("Gotham Light", Font.PLAIN, 18));
				
				flagDescriptions[i] = new JLabel("<html>" + entry.getValue() + "</html>");
				flagDescriptions[i].setBounds(50, 110 + 60*i, 450, 45);
				flagNames[i].setFont(new Font("Gotham Light", Font.PLAIN, 15));
				
				mainPanel.add(flagNames[i]);
				mainPanel.add(flagDescriptions[i]);
				i++;
			} 
		}
		
		if(page.getFlags().size() > 6){
			// Requires a second page
		}
		
		
		JLabel backLink = new JLabel("<html><u>BACK</u></html>");
		backLink.setFont(new Font("Gotham Light", Font.BOLD, 20));
		backLink.setForeground(Color.BLUE);
		backLink.setBounds(375, 495, 75, 59);
		mainPanel.add(backLink);
		
		
		
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