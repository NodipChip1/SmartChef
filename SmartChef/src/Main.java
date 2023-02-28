import java.awt.*;
import javax.swing.*;

public class Main extends JPanel{
	private JFrame frame;
	private Container can;
	
	public Main() {
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		can = frame.getContentPane();
		
		setPreferredSize(new Dimension(1500,800));
		can.add(this);
		
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Main();

	}

}
