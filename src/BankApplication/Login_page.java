package BankApplication;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class Login_page extends JFrame {
	JLabel label1, label2, label3,label4,lb1,lb2,lb3,lb4,lb5,lb6;
	JTextField text1,text2,txt1,txt2,txt3,txt4,txt5,txt6;
	JPanel panel1, panel2,panel5;
	JButton login,register;
	Color blue_magenta,blue;
	ImageIcon one, two, three;
	JFrame frame1 , frame2,frame3, frame4;
	Statement st;
	String query;
	public  Login_page() {
		setVisible(true);
		setTitle("bank");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(700, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		//		String life1 = "C:\\Users\\HP\\Documents\\java/username_icon2.jpg";
		//		three = new ImageIcon(life1);
		//		Image img1 = one.getImage().getScaledInstance(90,90, Image.SCALE_DEFAULT);
		//		ImageIcon new_img1 = new ImageIcon(img1);

		blue_magenta = new Color(35,31,178);
		blue = new Color(61,52,240);
		GridBagConstraints gridBag = new GridBagConstraints();
		panel1 = new JPanel(new GridBagLayout());
		panel1.setBackground(blue_magenta);
		add(panel1);
		String life = "C:\\Users\\HP\\Documents\\java/username_icon2.jpg";
		one = new ImageIcon(life);
		Image img = one.getImage().getScaledInstance(90,90, Image.SCALE_DEFAULT);
		ImageIcon new_img = new ImageIcon(img);

		String life2 = "C:\\Users\\HP\\Documents\\java/password_icon.jpg";
		two = new ImageIcon(life2);
		Image img2 = two.getImage().getScaledInstance(90,90, Image.SCALE_DEFAULT);
		ImageIcon new_img2 = new ImageIcon(img2);

		label1 = new JLabel(new_img);
		gridBag.gridx = 0;
		gridBag.gridy = 0;
		panel1.add(label1,gridBag);
		JLabel space = new JLabel("  ");
		gridBag.gridx = 2;
		gridBag.gridy = 0;
		panel1.add(space,gridBag);
		text1 = new JTextField(30);
		text1.setBackground(blue);
		text1.setForeground(Color.BLACK);
		//		text1.setBorder((Border) Color.BLACK);
		text1.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();
				if((Character.isAlphabetic(c)) || c == ke.VK_BACK_SPACE || c== ke.VK_AT|| c== ke.VK_CAPS_LOCK|| c== ke.VK_DECIMAL) {
					//					System.out.println("fxcgb");
				}else {
					//					number.setEditable(false);
					JOptionPane.showMessageDialog(null, "only Letters","",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		gridBag.gridx = 3;
		gridBag.gridy = 0;
		panel1.add(text1,gridBag);
		label2 = new JLabel(new_img2);
		gridBag.gridx = 0;
		gridBag.gridy = 2;
		panel1.add(label2,gridBag);
		JLabel space1 = new JLabel("  ");
		gridBag.gridx = 2;
		gridBag.gridy = 2;
		panel1.add(space1,gridBag);
		text2 = new JPasswordField(30);
		text2.setBackground(blue);
		text2.setForeground(Color.BLACK);
		//	    text2.setBorder((Border) Color.BLACK);
		text2.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent ke) {
				String num = text2.getText();
				char c = ke.getKeyChar();
				if(c>= '0'&& c<= '9' || c == ke.VK_BACK_SPACE ) {
					//					number.setEditable(true);
				}else {
					//					number.setEditable(false);
					JOptionPane.showMessageDialog(null, "only number","",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		gridBag.gridx = 3;
		gridBag.gridy = 2;
		panel1.add(text2,gridBag);
		JLabel space2 = new JLabel("  ");
		gridBag.gridx = 1;
		gridBag.gridy = 1;
		panel1.add(space,gridBag);
		login = new JButton("Log in");
		login.setBackground(blue);
		login.setForeground(Color.BLACK);
		gridBag.gridx= 3;
		gridBag.gridy = 3;
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String uname = text1.getText(),
							pass = text2.getText();
					query = "Log in";
					if(!(uname.isEmpty() || pass.isEmpty())) {
						query = "Select * from Signup where email = '"+ uname+"' and pin = '"+ pass + "'";
						
							JOptionPane.showMessageDialog(null, "Login Successfull");
							dispose();
							new home_page();
					}else JOptionPane.showMessageDialog(null, "Login Failed");

				}catch(Exception ae) {
					System.out.println(ae);
				}

			}
		});
		panel1.add(login,gridBag);
		label3 = new JLabel("Don't have an Account: Sign up");
		gridBag.gridx= 3;
		gridBag.gridy = 4;
		label3.setForeground(Color.BLACK);
		label3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Register1();
			}
		});
		panel1.add(label3,gridBag);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				new Register1();
			}
		});

		getContentPane().setBackground(blue_magenta);
		pack();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new  Login_page();
	}

	public void loadsql() {
		String username = "root", password = "KiLLingMyDemons#1";
		String url = "jdbc:mysql://localhost:3306/master";
		
		try {
			System.out.println("Hello...1");
			// register driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Hello...2");
		
		// load driver
		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println("Hello...3");
		
		String query = "insert into master_data values (4, 'Eniola', 30);";
		st = conn.createStatement();
//		st.execute(query);
		System.out.println("Hello...4");
		
		}
		catch (Exception e) {
			System.out.println("There was an error..." + e.getMessage());
		}
	}

}

