
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
public class MainScreen extends JPanel implements ActionListener{
	private String recipeName, savedIngName, savedRecipeName;
	private ArrayList<JLabel> hyperLink = new ArrayList<JLabel>();
	private boolean start, HypePageChange;
	private JFrame frame;
	private Container can;
	private JTextField password, recipeTitle, remove;
	private JTextArea ingredients, homeList;
	private JButton loginEnterButton, back2Login, addRecipe, fav, enterRecipe, back2Home, remove_nter;
	private JLabel currentRecipe;
	private Timer timer;
	private FontMetrics fm;
	private int page, fmWidth;
	private BufferedWriter writer = null;
	private String triedpass = ""; 
	private ArrayList<String> recs = new ArrayList<String>();
	public static final int HOME = 1, RECIPE = 3, MATCHES = 2, LOGIN = 0, FAV = 4, NEW=5, SUCCESS=6;
	private GridLayout thelayout;
   	private GridBagConstraints gbc;



	
	public Main() {
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		can = frame.getContentPane();

		this.setLayout(null);
		
		setPreferredSize(new Dimension(1500,800));
		//this.setVisible(true);
		can.add(this);
		
		
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
	
		timer = new Timer(1000/60, this);
		timer.addActionListener(this);
		timer.start();
		
		HypePageChange = false;
        
		
		
        
		
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
			g.setColor(Color.BLACK);
			g.setFont(title);
			g.drawString("Your Recipes", 500, 100);
			g.setColor(Color.WHITE);
			g.fillRect(350,150,750,550);
			
		}
		else if(page == RECIPE) {
			g.setFont(title);
			g.drawString(currentRecipe.getName(), 350, 150);
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
			
					this.add(back2Login);
					this.add(addRecipe);
					this.add(fav);
	
					for(JLabel j: hyperLink) {
						this.add(j);
					}
					this.remove(loginEnterButton);
					this.remove(password);
					
				}
			}
			if(source.equals(addRecipe)) {
				page = NEW;
				this.setVisible(true);
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
			
			if(HypePageChange == true) {
				page = RECIPE;
				this.add(back2Home);
				this.remove(back2Login);
				this.remove(addRecipe);
				this.remove(fav);
				for(JLabel j: hyperLink) {
					this.remove(j);
				}
				HypePageChange = false;
			}
				
			}			
			
			if(source.equals(remove_nter)) {
				recRead();
				recRemove(remove.getText());
				while(recs.size() != 0) {
					recs.remove(0);
				}
			
			}
			if(source.equals(enterRecipe)) {
				recipeName = recipeTitle.getText();
				hyperAdd(recipeName);
				page = SUCCESS;
				
				this.remove(recipeTitle);
				this.remove(ingredients);
				this.remove(enterRecipe);
				
				recRead();
				addIngredients(ingredients.getText());
				recWrite();
				while(recs.size() != 0) {
					recs.remove(0);
				}
			}
		repaint();

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
		
		
	}
	
	public void addIngredients(String s) { // grabs the new ingredients and recipe name when you add a recipe to then add to the database
		recs.add(":" + recipeTitle.getText() + ";");
		String[] parse = s.split("\n");
		for(String s1: parse) {
			
			recs.add(s1);
			
		}
		recs.add("n-der");
	}
	
	public void recRead(){ // reads all the data in the recipe txt file and adds them to an array list with every recipe so we can then access any recipe 
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
	public void recWrite()  { // adds any new recipes to the data base
		FileWriter fw;
		try {
			fw = new FileWriter("Recipie.txt");
			PrintWriter out = new PrintWriter(fw);
			for(String s : recs) {
				out.print(s);
				
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
	public void recRemove(String remove)  { // removes a specific recipe from the recipe database
	 int counter = 0;
	 int original = 0;
	 int original2 = 0;
	 for(int i = 0; i < recs.size(); i++) {
		 if(recs.get(i).indexOf(":;") !=0) {
			 if(recs.get(i).contains(remove)) {
				 counter = i;
				 System.out.println(recs.get(i));
				for(int a = i; a < recs.size(); a++) {
					 if(recs.get(a).contains("n-der")) {
						 counter++;
						break;
					 }
					 else {
						 counter++;
					 }
				}
				original = i;
				break;
			 }
		 }
		 
	 }
	 original2 = original;
	 System.out.println(original);
	 System.out.println(counter);
	 while (original2 < counter) {
		 recs.remove(original);
		 original2++;
	 }
	recWrite();	 
	
	}
	public void hyperRead() {
		 hyperLink.clear();
		 recRead();
		 FileReader fr;
			try {
				fr = new FileReader("Recipie.txt");
				Scanner in = new Scanner(fr);
				ArrayList<String> split = new ArrayList<String>();
				while(in.hasNext()){
					split.add(in.next());
					
					System.out.println(recs);
				}
				for(String s: split) {
					if(s.indexOf(":;") != 0) {
						savedRecipeName = s.substring(s.indexOf(":") +1, s.indexOf(";"));
						hyperAdd(savedRecipeName);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		recs = new ArrayList<String>();
	}
	public void hyperAdd(String s) {
		hyperLink.add(new JLabel(s));
		int temp = 300;
		for(JLabel j: hyperLink) {
			j.setBounds(600,temp,100,20);
			temp += 20;
			j.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
		    	HypePageChange = true;
		    }
		 
		    public void mouseEntered(MouseEvent e) {
		    }
		 
		    public void mouseExited(MouseEvent e) {
	
		    }
			});
		}
		temp = 300;
		ingRead();
	}
	
	public void ingRead() {
		recRead();
		 FileReader fr;
			try {
				fr = new FileReader("Recipie.txt");
				Scanner in = new Scanner(fr);
				ArrayList<String> split = new ArrayList<String>();
				while(in.hasNext()){
					split.add(in.next());
					System.out.println(recs);
				}
				for(String s: split) {
					if(s.indexOf(",") != 0) {
						int x = s.indexOf(",");
						savedIngName = s.substring(x + 1,x + s.indexOf(","));
						System.out.println(savedIngName);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		recs = new ArrayList<String>();
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
	
	
