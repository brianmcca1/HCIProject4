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

public class HomeView {

	private JFrame frame;
	
	ManPage page;
	HashMap<String, ManPage> pages;

	/**
	 * Launch the application.
	 */
	public void launch() {
		initialize(pages);
		
	}

	/**
	 * Create the application.
	 */
	public HomeView(HashMap<String, ManPage> pages) {
	
		this.pages = pages;
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize(HashMap<String, ManPage> pages) {
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
		JLabel homeLink = new JLabel("Home");
		homeLink.setFont(new Font("Gotham Light", Font.PLAIN, 18));
		homeLink.setForeground(Color.BLACK);
		homeLink.setBounds(45, 100, 80, 30);
		index.add(homeLink);
		
		JLabel pageShortcuts[] = new JLabel[pages.size()];
		int i = 0;
		for(Map.Entry<String, ManPage> entry: pages.entrySet()){
			pageShortcuts[i] = new JLabel(entry.getKey());
			
			pageShortcuts[i].setFont(new Font("Gotham Light", Font.PLAIN, 18));
			pageShortcuts[i].setBounds(45, 150 + 50 * i, 80, 30);
			
			// These are links, so indicate that
			pageShortcuts[i].setForeground(Color.BLUE);
			// Underlines the text
			Font font = pageShortcuts[i].getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			pageShortcuts[i].setFont(font.deriveFont(attributes));
			pageShortcuts[i].addMouseListener(new IndexMouseAdapter(entry.getValue(), pages, frame));
			pageShortcuts[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		
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
		
		JLabel lblName = new JLabel("Home");
		lblName.setBounds(30, 20, 182, 83);
		mainPanel.add(lblName);
		lblName.setFont(new Font("Gotham Light", Font.PLAIN, 32));
		
		i = 0;
		JLabel pageLinks[] = new JLabel[pages.size()];
		JLabel pageDescriptions[] = new JLabel[pages.size()];
		
		for(Map.Entry<String, ManPage> entry: pages.entrySet()){
			pageLinks[i] = new JLabel(entry.getKey());
			
			pageLinks[i].setFont(new Font("Gotham Light", Font.PLAIN, 18));
			pageLinks[i].setBounds(30, 100 + 80 * i, 80, 30);
			
			// These are links, so indicate that
			pageLinks[i].setForeground(Color.BLUE);
			// Underlines the text
			Font font = pageLinks[i].getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			pageLinks[i].setFont(font.deriveFont(attributes));
			pageLinks[i].addMouseListener(new IndexMouseAdapter(entry.getValue(), pages, frame));
			pageLinks[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			mainPanel.add(pageLinks[i]);
			
			pageDescriptions[i] = new JLabel("<html>" + entry.getValue().getDescription() + "</html>");
			pageDescriptions[i].setFont(new Font("Helvetica", Font.PLAIN, 15));
			pageDescriptions[i].setForeground(Color.BLACK);
			pageDescriptions[i].setBounds(50, 130 + 80 * i, 400, 50);
			pageDescriptions[i].setAlignmentX(JLabel.TOP);
			mainPanel.add(pageDescriptions[i]);
			
			i++;
		}
		
		
		
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
