
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Main extends JPanel implements ActionListener{
	private int linkY = 400;
	private String recipeName;
	private GridLayout thelayout;
    private GridBagConstraints gbc;
	private ArrayList<JLabel> hyperLink = new ArrayList<JLabel>();
	private boolean start, hypePageChange;
	private JFrame frame;
	private JPanel homePanel, panel2;
	private Container can;
	private JTextField password, recipeTitle;
	private JTextArea ingredients, homeList;
	private JScrollPane homeScroll;
	private JButton loginEnterButton, back2Login, addRecipe, fav, enterRecipe, back2Home;
	private Timer timer;
	private FontMetrics  fm;
	private int page, fmWidth;
	private BufferedWriter writer = null;
	private String triedpass = "";
	private ArrayList<String> recs = new ArrayList<String>();
	public static final int HOME = 1, RECIPE = 3, MATCHES = 2, LOGIN = 0, FAV = 4, NEW=5, SUCCESS=6;
	



	
	public Main() {
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		can = frame.getContentPane();

		thelayout = new GridLayout(3,2);
	    this.setLayout(thelayout);
		
		setPreferredSize(new Dimension(1500,800));
		this.setVisible(true);
		can.add(this);
		
		panel2 = new Panel2(can);
		//panel2.setVisible(false);
		
		frame.pack();
		
		recipeName = new String(" ");
		
		password = new JTextField("123456");
		password.setPreferredSize(new Dimension(100,200));
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
		
		homePanel = new JPanel();
		homePanel.setLayout(new BorderLayout());
		
		homeList = new JTextArea();

		homeList.setLayout(new BorderLayout());
		homeList.setEditable(true);
		
		homeScroll = new JScrollPane(homeList);
		homeScroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		homePanel.setPreferredSize(new Dimension(800,500));
		
		homePanel.add(homeScroll, BorderLayout.CENTER);
	
		timer = new Timer(1000/60, this);
		timer.addActionListener(this);
		timer.start();
		
		hypePageChange = false;
        
		
		
        
		
		init();
		
		frame.setVisible(true);

	}
	private void init() {
		page = LOGIN;
		start = true;
		
												
	        
	    }
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(167,214,235));
		g.fillRect(0,0,getWidth(), getHeight());
		
		String fName = "JosefinSans-VariableFont_wght.ttf"; //Input file of font from Google
	    InputStream is = Main.class.getResourceAsStream(fName);
	
	    Font font = null; //Tug 'O War font
	   
	    // copies of font using different sizes
	    Font title = null;
	    Font small = null;
	    Font middle = null;
	  
	    try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			title = font.deriveFont(Font.BOLD, 75);
			small = font.deriveFont(Font.BOLD, 30);
			middle = font.deriveFont(Font.BOLD, 50);

		} catch (Exception e) {}
	    
	    
		if(page == LOGIN) {
			g.setColor(Color.BLACK);
		    g.setFont(title);
		    g.drawString("SmartChef",550,200);
		    g.setFont(small);
		    g.drawString("Login",450,440);
		    
		}
		
		else if(page == HOME) {
			
		}
		else if(page == RECIPE) {
			
		}
		else if(page == MATCHES) {
			
		}
		else if(page == FAV) {
			
		}
		else if(page == NEW) {
			g.setColor(Color.BLACK);
			g.setFont(title);
			g.drawString("New Recipe", 550, 100);
			g.setFont(small);
			g.drawString("Title", 475, 240);
			g.drawString("Enter Ingredients here: Please seperate each ingredient by dropping to a new line", 150, 325);
			
		}
		else if(page == SUCCESS) {
			g.setColor(Color.BLACK);
			g.setFont(middle);
			g.drawString("Your Recipe has been successfully addded!", 250, 400);
		}
		
		
	}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(timer.isRunning()) {
			setAllBounds(); //updates according to JFrame size
			if(start) {
				this.add(password);
				this.add(loginEnterButton);
				start = false;
				repaint();
			}
			if(source.equals(back2Login)) {
				init();
				this.remove(back2Login);
				this.remove(addRecipe);
				this.remove(fav);
				for(JLabel j: hyperLink) {
					this.remove(j);
				}
			}
			if(source.equals(loginEnterButton) || source.equals(password)) {
				if(readPass("passwords.txt")) {
					page = HOME;
				
					this.setVisible(false);
					can.add(panel2);
					panel2.repaint();
					
					
					/*this.add(back2Login);
					this.add(addRecipe);
					this.add(homePanel);
					this.add(fav);
					*/
					
					
					/*for(JLabel j: hyperLink) {
						this.add(j);
					}
					
					this.remove(loginEnterButton);
					this.remove(password);*/
				}
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
				for(JLabel j: hyperLink) {
					this.remove(j);
				}
			}
			if(source.equals(fav)) {
				page = FAV;
				this.add(back2Home);
				
				
				this.remove(back2Login);
				this.remove(addRecipe);
				this.remove(fav);
				for(JLabel j: hyperLink) {
					this.remove(j);
				}
				
			}
			if(source.equals(back2Home)) {
				page = HOME;
				this.add(back2Login);
				this.add(addRecipe);
				this.add(fav);
				for(JLabel j: hyperLink) {
					this.add(j);
				}
				
				
				this.remove(recipeTitle);
				this.remove(ingredients);
				this.remove(enterRecipe);
				this.remove(back2Home);
				
			}
			
			if(hypePageChange == true) {
				this.add(back2Home);
				this.remove(back2Login);
				this.remove(addRecipe);
				this.remove(fav);
				for(JLabel j: hyperLink) {
					this.remove(j);
				}
				hypePageChange = false;
			}
			
			if(source.equals(enterRecipe) && recipeTitle.getText()!=null && ingredients.getText()!=null) {
				recipeName = recipeTitle.getText();
				hyperLink.add(new JLabel(recipeName));
				for(JLabel j: hyperLink) {
					j.setBounds(600,linkY,100,20);
					linkY += 20;
					j.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
				    	hypePageChange = true;
				    }
				    public void mouseEntered(MouseEvent e) {}
				    public void mouseExited(MouseEvent e) {}
					}
					);
				}
				
					 
				    
				page = SUCCESS;
				this.remove(recipeTitle);
				this.remove(ingredients);
				this.remove(enterRecipe);
				
			}			
			
			if(source.equals(enterRecipe)) {
				RecRead();
				addIngredients(ingredients.getText());
				RecWrite();
				while(recs.size() != 0) {
					recs.remove(0);
				}
			}
		//repaint();
		}
	}
	private void setAllBounds() {
		password.setBounds(550, 400, 400,50);
		loginEnterButton.setBounds(1200,700,100,50);
		back2Login.setBounds(10, 10, 100,50);
		addRecipe.setBounds(1200,10,100,50);
		fav.setBounds(1200,700,100,50);
		recipeTitle.setBounds(550,200,400,50);
		ingredients.setBounds(350,350,750,350);
		enterRecipe.setBounds(1140,700,100,50);
		back2Home.setBounds(10, 10, 100,50);
		homePanel.setBounds(300,200,800,500);
		
	}
	
	public void addIngredients(String s) {
		String[] parse = s.split("\n");
		recs.add(recipeTitle.getText());
		for(String s1: parse) {
			
			recs.add(s1);
			
		}
		recs.add("n-der");

	}

	
	public void RecRead(){

		FileReader fr;
		try {
			fr = new FileReader("Recipie.txt");
			Scanner in = new Scanner(fr); 
			while(in.hasNext()){

				String[] split = in.next().split(",");

				for(String s: split) {

					recs.add(s);

				}

				System.out.println(recs);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void RecWrite()  {
		FileWriter fw;
		try {
			fw = new FileWriter("Recipie.txt");
			PrintWriter out = new PrintWriter(fw);

			for(String s : recs) {
				out.print(s);
				//System.out.println(s);
				if (s.equals("n-der")) {
					out.println();
				}
				else {
					out.print(",");
				}
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean readPass(String path){
		boolean r = false;
		try {
			Scanner in = new Scanner(new FileReader(path));
			while(in.hasNext()){
				if(password.getText().equals(in.nextLine())) {
					r = true;
				}
				
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}


		
	public static void main(String[] args) {
		new Main();
	}

}


