import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.InputStream;

public class Main extends JPanel implements ActionListener{
	private JFrame frame;
	private Container can;
	private JTextField password;
	private JButton loginEnterButton, back2Login, addRecipe, fav;
	private Timer timer;
	private boolean start;
	private FontMetrics  fm;
	private int page, fmWidth;
	public static final int HOME = 1, RECIPE = 3, MATCHES = 2, LOGIN = 0;


	
	public Main() {
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
		
		timer = new Timer(1000/60, this);
		timer.addActionListener(this);
		timer.start();
		
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
	    System.out.println(is);
	    Font font = null; //Tug 'O War font
	   
	    // copies of font using different sizes
	    Font title = null;
	    Font small = null;
	  
	    try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
			title = font.deriveFont(Font.BOLD, 75);
			small = font.deriveFont(Font.BOLD, 30);

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
	}
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if(timer.isRunning()) {
			setAllBounds(); //updates according to JFrame size
			if(start) {
				this.add(password);
				this.add(loginEnterButton);
				start = false;
			}
			if(source.equals(back2Login)) {
				init();
				this.remove(back2Login);
			}
			if(source.equals(loginEnterButton) || source.equals(password)) {
				page = HOME;
				this.add(back2Login);
				this.add(addRecipe);
				this.add(fav);
				
				this.remove(loginEnterButton);
				this.remove(password);
			}
			
			repaint();
		}
	}
	private void setAllBounds() {
		password.setBounds(550, 400, 400,50);
		loginEnterButton.setBounds(1200,700,100,50);
		back2Login.setBounds(0, 0, 100,50);
		addRecipe.setBounds(1140,0,100,50);
		fav.setBounds(1140,700,100,50);
	}


		
	public static void main(String[] args) {
		new Main();
// So I'm not really sure if this works but can someone let me know if it does?
	}

}
