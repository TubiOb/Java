package BankApplication;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Register1 implements ActionListener {
	JButton register, back;
	JFrame frame1, frame2, frame3, frame4;
	JPanel panel1, panel2, panel5;
	JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7;
	JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7;
	JRadioButton male, female;
	Statement st;
	String query;

	public Register1() {
		loadsql();
		frame2 = new JFrame("Sign Up Page");
		frame2.setVisible(true);
		frame2.setTitle("Sign-up");
		frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame2.setSize(500, 500);
		frame2.setLocationRelativeTo(null);
		GridBagConstraints gridbag = new GridBagConstraints();
		panel5 = new JPanel(new GridBagLayout());
		frame2.add(panel5);

		lb1 = new JLabel("FirstName: ");
		gridbag.gridx = 0;
		gridbag.gridy = 0;
		panel5.add(lb1, gridbag);
		txt1 = new JTextField(10);
		gridbag.gridx = 1;
		gridbag.gridy = 0;
		panel5.add(txt1, gridbag);

		lb2 = new JLabel("LastName: ");
		gridbag.gridx = 0;
		gridbag.gridy = 1;
		panel5.add(lb2, gridbag);
		txt2 = new JTextField(10);

		gridbag.gridx = 1;
		gridbag.gridy = 1;
		panel5.add(txt2, gridbag);

		lb3 = new JLabel("Email Address: ");
		gridbag.gridx = 0;
		gridbag.gridy = 2;
		panel5.add(lb3, gridbag);
		txt3 = new JTextField(10);
		gridbag.gridx = 1;
		gridbag.gridy = 2;
		panel5.add(txt3, gridbag);

		lb5 = new JLabel("Opening Balance: ");
		gridbag.gridx = 0;
		gridbag.gridy = 3;
		panel5.add(lb5, gridbag);
		txt5 = new JTextField(10);
		txt5.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent ke) {
				String num = txt5.getText();
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == ke.VK_BACK_SPACE) {
				} else {
					JOptionPane.showMessageDialog(null, "only number", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		gridbag.gridx = 1;
		gridbag.gridy = 3;
		panel5.add(txt5, gridbag);

		lb6 = new JLabel("Create Pin: ");
		gridbag.gridx = 0;
		gridbag.gridy = 4;
		panel5.add(lb6, gridbag);
		txt6 = new JPasswordField(10);
		txt6.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent ke) {
				String num = txt6.getText();
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == ke.VK_BACK_SPACE) {
				} else {
					JOptionPane.showMessageDialog(null, "only number", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		gridbag.gridx = 1;
		gridbag.gridy = 4;
		panel5.add(txt6, gridbag);

		lb6 = new JLabel("Confirm Pin: ");
		gridbag.gridx = 0;
		gridbag.gridy = 5;
		panel5.add(lb6, gridbag);
		txt7 = new JPasswordField(10);
		txt7.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent ke) {
				String num = txt7.getText();
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == ke.VK_BACK_SPACE) {
				} else {
					JOptionPane.showMessageDialog(null, "only number", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		gridbag.gridx = 1;
		gridbag.gridy = 5;
		panel5.add(txt7, gridbag);

		lb7 = new JLabel("Select Gender: ");
		gridbag.gridx = 0;
		gridbag.gridy = 6;
		panel5.add(lb7, gridbag);
		male = new JRadioButton();
		male.setText("Male");
		gridbag.gridx = 2;
		gridbag.gridy = 6;
		panel5.add(male, gridbag);

		female = new JRadioButton("female");
		gridbag.gridx = 1;
		gridbag.gridy = 6;
		panel5.add(female, gridbag);
		female.setSelected(true);
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(male);
		genderGroup.add(female);

		register = new JButton("Register");
		gridbag.gridx = 0;
		gridbag.gridy = 7;
		panel5.add(register, gridbag);
		register.addActionListener(this);
		
		JButton back = new JButton("«");
		gridbag.gridx = 1;
		gridbag.gridy = 7;
		panel5.add(back,gridbag);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Register1();
	}
	
	public void loadsql() {
		String uname = "root", password = "KiLLingMyDemons#1";
		String url = "jdbc:mysql://localhost:3306/Signup_info";
		try {
			System.out.println("Hello...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Hello...2");

			Connection conn = DriverManager.getConnection(url, uname, password);
			System.out.println("Hello...3");

			String query = "insert into master_data values(4,'haleemah',30);";
			st = conn.createStatement();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent ee) {
		// TODO Auto-generated method stub
		try {
			String num1 = txt6.getText();
			String num2 = txt7.getText();
			int nom1 = Integer.parseInt(num1);
			int nom2 = Integer.parseInt(num2);
		String fname = txt1.getText();
		String lname = txt2.getText();
		String email = txt3.getText();
		String openbal = txt5.getText();
		String pin = txt6.getText();
		String gender = "female";
		if (male.isSelected())
			gender = "male";
//		query = "Create table Signup(Firstname varchar(20),Lastname varchar(20),Emailaddress varchar(30),"	
//     +"AccountNumber INT Auto_Increment primary key, openingBalance int,Pin int,Gender Varchar(10));";
//		query = "Alter table Signup Auto_Increment=1234567890";
		query = "Insert into Signup(firstname, lastname, emailaddress, openingbalance, pin, gender) values(" + "'" + fname + "'," + "'" + lname + "'," + "'" + email
				+ "'," + "'" + openbal + "'," + "'" + pin + "'," + " ' " + gender
				+ "');";
		
//		st.execute(query);
//		System.out.println("Hello...4");
			if(!(fname.isEmpty() ||lname.isEmpty() ||email.isEmpty() 
					||openbal.isEmpty() ||pin.isEmpty() ||gender.isEmpty()) || nom1 != nom2 ) {
				
				st.execute(query);
				JOptionPane.showMessageDialog(null, "Registration Successfull");
				System.out.println("Hello...4");
				frame2.dispose();
				new home_page();
			}else {
				JOptionPane.showMessageDialog(null, "Registration failed!!");
			}
		} catch (Exception ex) {
			System.out.println("Error occured: " + ex.getMessage());
			JOptionPane.showMessageDialog(null, "Registration failed!!");
		}
		//			frame2.dispose();
		//			new home_page();

	}

}
