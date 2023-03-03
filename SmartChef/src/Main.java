import java.awt.*; 
import java.awt.event.*;
import java.io.InputStream;
import javax.swing.*;

public class MainScreen extends JPanel implements ActionListener{
	/* Initializes all necessary variables
	 */
	private JFrame frame;
	private Container can;
	private JTextField password, recipeTitle;
	private JTextArea ingredients;
	private JButton loginEnterButton, back2Login, addRecipe, fav, enterRecipe, back2Home;
	private Timer timer;
	private boolean start;
	private FontMetrics  fm;
	private int page, fmWidth;
	public static final int HOME = 1, RECIPE = 3, MATCHES = 2, LOGIN = 0, FAV = 4, NEW=5, SUCCESS=6;
	
	public Main() {
		/* Creates JFrame and JPanel to be used for program
		 * Declares and connects ActionListener to all JButtons, JTextFields, Hyperlinks, and TextAreas
		 * 
		 */
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		can = frame.getContentPane();
		
		setPreferredSize(new Dimension(1500,800));
		can.add(this);
		
		frame.pack();
	
		this.setLayout(null);
		
		password = new JTextField();
		password.addActionListener(this);
		
		loginEnterButton = new JButton("Enter");
		loginEnterButton.addActionListener(this);
		
		back2Login = new JButton("Back");
		back2Login.addActionListener(this);
		
		addRecipe = new JButton("+ Add");
		addRecipe.addActionListener(this);
		
		fav = new JButton("Favorites");
		fav.addActionListener(this);
		
		recipeTitle = new JTextField(); 
		
		ingredients = new JTextArea();
		
		enterRecipe = new JButton("Enter");
		enterRecipe.addActionListener(this);
		
		back2Home = new JButton("Back");
		back2Home.addActionListener(this);
		
		init();
		
		
		frame.setVisible(true);
		
	}
	private void init() {
		page = LOGIN; //Declares int page which is used to record the page the user is on
		start = true; //Boolean that allows the program to start at the login screen
		
	}
	public void paintComponent(Graphics g) {
		/* Adds all necessary drawing elements to the program's interface
		 * Includes: Text, Colors, Font
		 * 
		 * 
		 */
		super.paintComponent(g);
		g.setColor(new Color(167,214,235));
		g.fillRect(0,0,getWidth(), getHeight());
		/* Downloads and implements Font JosefinSans to be used throughout the program
		 */
		String fName = "JosefinSans-VariableFont_wght.ttf"; //Input file of font
	    InputStream is = MainScreen.class.getResourceAsStream(fName);
	    Font font = null; //Initial font
	   
	    // copies of font that can be manipulated to have different sizing
	    Font title = null;
	    Font small = null;
	    Font middle = null;
	  
	    try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			//Setting the copies to different sizes
			title = font.deriveFont(Font.BOLD, 75);
			small = font.deriveFont(Font.BOLD, 30);
			middle = font.deriveFont(Font.BOLD, 50);

		} catch (Exception e) {}
	    
	    
		if(page == LOGIN) { //Draws all necessary components for login screen
			g.setColor(Color.BLACK);
		    g.setFont(title);
		    g.drawString("SmartChef",550,200);
		    g.setFont(small);
		    g.drawString("Login",450,440);
		    
		}
		
		else if(page == HOME) {//Draws all necessary components for Home screen
			
		}
		else if(page == RECIPE) {//Draws all necessary components for Recipe screen
			
		}
		else if(page == MATCHES) {//Draws all necessary components for the matches screen
			
		}
		else if(page == FAV) {//Draws all necessary components for favorites screen
			
		}
		else if(page == NEW) {//Draws all necessary components for adding a new Recipe screen
			g.setColor(Color.BLACK);
			g.setFont(title);
			g.drawString("New Recipe", 550, 100);
			g.setFont(small);
			g.drawString("Title", 475, 240);
			g.drawString("Enter Ingredients here: Please seperate each ingredient by dropping to a new line", 150, 325);
			
		}
		else if(page == SUCCESS) {//Draws all necessary components for login screen
			g.setColor(Color.BLACK);
			g.setFont(middle);
			g.drawString("Your Recipe has been successfully addded!", 250, 400);
		}
		
		
	}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
			setAllBounds(); //Declares sizes of JComponents
			if(start) {
				this.add(password);
				this.add(loginEnterButton);
				start = false;
			}
			if(source.equals(back2Login)) {
				init();
				this.remove(back2Login);
				this.remove(addRecipe);
				this.remove(fav);
			}
			if(source.equals(loginEnterButton) || source.equals(password)) {
				page = HOME;
				this.add(back2Login);
				this.add(addRecipe);
				this.add(fav);
				
				this.remove(loginEnterButton);
				this.remove(password);
			}
			if(source.equals(addRecipe)) {
				page = NEW;
				this.add(recipeTitle);
				this.add(ingredients);
				this.add(enterRecipe);
				this.add(back2Home);
				
				this.remove(back2Login);
				this.remove(addRecipe);
				this.remove(fav);
			}
			if(source.equals(fav)) {
				page = FAV;
				this.add(back2Home);
				
				
				this.remove(back2Login);
				this.remove(addRecipe);
				this.remove(fav);
				
			}
			if(source.equals(back2Home)) {
				page = HOME;
				this.add(back2Login);
				this.add(addRecipe);
				this.add(fav);
				
				
				this.remove(recipeTitle);
				this.remove(ingredients);
				this.remove(enterRecipe);
				this.remove(back2Home);
				
			}
			if(source.equals(enterRecipe)) {
				page = SUCCESS;
				
				this.remove(recipeTitle);
				this.remove(ingredients);
				this.remove(enterRecipe);
				
			}			
			repaint();
		}
	private void setAllBounds() {
		/*
		 * sets all JButtons, JTextFields, JTextAreas etc to specific size and coordinates
		 */
		password.setBounds(550, 400, 400,50);
		loginEnterButton.setBounds(1200,700,100,50);
		back2Login.setBounds(0, 0, 100,50);
		addRecipe.setBounds(1140,0,100,50);
		fav.setBounds(1140,700,100,50);
		recipeTitle.setBounds(550,200,400,50);
		ingredients.setBounds(350,350,750,350);
		enterRecipe.setBounds(1140,700,100,50);
		back2Home.setBounds(10, 10, 100,50);
	}


		
	public static void main(String[] args) {
		new Main();
	}

}
