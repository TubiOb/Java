package BankApplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.lang.Math;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

import BankApplication.Labs.Login;
import BankApplicationSQLConn.BankAppConnectionLoader;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.MatchResult;

public class SignUp extends JFrame implements ActionListener{
	JFrame frame;
	Font font;
	JTextField firstnameField, lastnameField, emailField, Opening, securityans1, securityans2;
	JPasswordField createpinField, confirmpinField;
	JLabel firstname, lastname, email, openbal, createpin, confirmpin, sex, securityquestion1, securityquestion2;
	JButton sign_in;
	String Placeholder = "Firstname", Placeholder1 = "Lastname", Placeholder2 = "E-mail", Placeholder3 = "Your Opening Balance",
			Placeholder4 = "Create Pin", Placeholder5 = "Confirm Pin", Placeholder6 = "your answer";
	JRadioButton male, female;
	Icon icon;
	Statement st;
	String query, user1;
	int count = 0;
	private String username;
	private String pin;
	
	public SignUp() {
		st = BankAppConnectionLoader.loadsql();
		
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setTitle("Sign Up");
		frame.setSize(450, 550);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.getContentPane();
		frame.setVisible(true);
		
		font = new Font("Inconsolata", Font.PLAIN, 13);
		
		firstname = new JLabel("First Name: ");
		firstname.setBounds(40, 60, 90, 20);
		firstname.setFont(font);
		
		lastname = new JLabel("Last Name: ");
		lastname.setBounds(40, 100, 90, 20);
		lastname.setFont(font);
		
		email = new JLabel("Email: ");
		email.setBounds(40, 140, 90, 20);
		email.setFont(font);
		
		openbal = new JLabel("Opening Balance: ");
		openbal.setBounds(40, 180, 150, 20);
		openbal.setFont(font);
		
		createpin = new JLabel("Create Pin: ");
		createpin.setBounds(40, 220, 150, 20);
		createpin.setFont(font);
		
		confirmpin = new JLabel("Confirm Pin: ");
		confirmpin.setBounds(40, 260, 150, 20);
		confirmpin.setFont(font);
		
		securityquestion1 = new JLabel("Your favourite color?");
		securityquestion1.setBounds(40, 300, 210, 20);
		securityquestion1.setFont(font);
		
		securityquestion2 = new JLabel("Your pet peeve?");
		securityquestion2.setBounds(40, 340, 210, 20);
		securityquestion2.setFont(font);
		
		sex = new JLabel("Gender: ");
		sex.setBounds(40, 380, 90, 20);
		sex.setFont(font);
		
		female = new JRadioButton("Female");
		female.setSelected(true);
		female.setBounds(200, 380, 70, 20);
		female.setFocusable(false);
		
		male = new JRadioButton("Male");
		male.setBounds(290, 380, 70, 20);
		male.setFocusable(false);
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(male);
		genderGroup.add(female);
		
		font = new Font("Inconsolata", Font.PLAIN, 12);
		
		sign_in = new JButton("Register");
		sign_in.setFont(font);
		sign_in.setBounds(160, 420, 85, 20);
		sign_in.setFocusable(false);
		sign_in.addActionListener(this);
		
//		font = new Font("Inconsolata", Font.ITALIC, 11);
		
		firstnameField = new JTextField(30);
		firstnameField.setText(Placeholder);
		firstnameField.setBounds(210, 60, 150, 20);
		firstnameField.getText();
		firstnameField.setFont(font);
		firstnameField.setCaretColor(Color.RED);

		lastnameField = new JTextField(30);
		lastnameField.setText(Placeholder1);
		lastnameField.setBounds(210, 100, 150, 20);
		lastnameField.getText();
		lastnameField.setFont(font);
		lastnameField.setCaretColor(Color.RED);

		emailField = new JTextField(30);
		emailField.getText();
		emailField.setBounds(210, 140, 150, 20);
		emailField.setText(Placeholder2);
		emailField.setFont(font);
		emailField.setCaretColor(Color.RED);
		
		Opening = new JTextField(30);
		Opening.getText();
		Opening.setBounds(210, 180, 150, 20);
		Opening.setText(Placeholder3);
		Opening.setFont(font);
		Opening.setCaretColor(Color.RED);

		createpinField = new JPasswordField(30);
		createpinField.setBounds(210, 220, 150, 20);
		createpinField.setText(Placeholder4);
		createpinField.setEchoChar((char) 0);
		createpinField.setCaretColor(Color.RED);
		createpinField.setFont(font);
		createpinField.getText();

		confirmpinField = new JPasswordField(30);
		confirmpinField.setBounds(210, 260, 150, 20);
		confirmpinField.setText(Placeholder5);
		confirmpinField.setEchoChar((char) 0);
		confirmpinField.setCaretColor(Color.RED);
		confirmpinField.setFont(font);
		confirmpinField.getText();
		
		securityans1 = new JTextField(30);
		securityans1.getText();
		securityans1.setBounds(210, 300, 150, 20);
		securityans1.setText(Placeholder6);
		securityans1.setFont(font);
		securityans1.setCaretColor(Color.RED);
		
		securityans2 = new JTextField(30);
		securityans2.getText();
		securityans2.setBounds(210, 340, 150, 20);
		securityans2.setText(Placeholder6);
		securityans2.setFont(font);
		securityans2.setCaretColor(Color.RED);
		
		// ADDING THE LISTENERS
		firstnameField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (firstnameField.getText().length() == 0) {
					firstnameField.setText(Placeholder);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (firstnameField.getText().equals(Placeholder)) {
					firstnameField.setText("");
				}
			}
		});
		
		lastnameField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (lastnameField.getText().length() == 0) {
					lastnameField.setText(Placeholder1);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (lastnameField.getText().equals(Placeholder1)) {
					lastnameField.setText("");
				}
			}
		});
		
		emailField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (emailField.getText().length() == 0) {
					emailField.setText(Placeholder2);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (emailField.getText().equals(Placeholder2)) {
					emailField.setText("");
				}
			}
		});
		
		Opening.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (Opening.getText().length() == 0) {
					Opening.setText(Placeholder3);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (Opening.getText().equals(Placeholder3)) {
					Opening.setText("");
				}
			}
		});
		
		Opening.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent ke) {
//				String num = Opening.getText();
				Opening.getText();
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == ke.VK_BACK_SPACE) {
				} else {
					JOptionPane.showMessageDialog(null, "Inputs can only be numbers", "", JOptionPane.ERROR_MESSAGE);
					Opening.setText("");
				}
			}
		});
		
		createpinField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (createpinField.getText().length() == 0) {
					createpinField.setText(Placeholder4);
					createpinField.setEchoChar((char) 0);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (createpinField.getText().equals(Placeholder4)) {
					createpinField.setText("");
					createpinField.setEchoChar((char) '*');
				}
			}
		});
		
		createpinField.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent ke) {
//				String num = createpinField.getText();
				createpinField.getText();
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == ke.VK_BACK_SPACE) {
				} else {
					JOptionPane.showMessageDialog(null, "Input Numbers only", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		confirmpinField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (confirmpinField.getText().length() == 0) {
					confirmpinField.setText(Placeholder5);
					confirmpinField.setEchoChar((char) 0);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (confirmpinField.getText().equals(Placeholder5)) {
					confirmpinField.setText("");
					confirmpinField.setEchoChar((char) '*');
				}
			}
		});
		
		confirmpinField.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			public void keyPressed(KeyEvent ke) {
//				String num = confirmpinField.getText();
				confirmpinField.getText();
				char c = ke.getKeyChar();
				if (c >= '0' && c <= '9' || c == ke.VK_BACK_SPACE) {
				} else {
					JOptionPane.showMessageDialog(null, "Input Numbers only", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		securityans1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (securityans1.getText().length() == 0) {
					securityans1.setText(Placeholder6);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (securityans1.getText().equals(Placeholder6)) {
					securityans1.setText("");
				}
			}
		});

		securityans2.addFocusListener(new FocusListener() {
	
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (securityans2.getText().length() == 0) {
					securityans2.setText(Placeholder6);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (securityans2.getText().equals(Placeholder6)) {
					securityans2.setText("");
				}
			}
		});
		
		frame.add(firstname);
		frame.add(firstnameField);
		frame.add(lastname);
		frame.add(lastnameField);
		frame.add(email);
		frame.add(emailField);
		frame.add(openbal);
		frame.add(Opening);
		frame.add(createpin);
		frame.add(createpinField);
		frame.add(confirmpin);
		frame.add(confirmpinField);
		frame.add(securityquestion1);
		frame.add(securityquestion2);
		frame.add(securityans1);
		frame.add(securityans2);
		frame.add(sex);
		frame.add(female);
		frame.add(male);
		frame.add(sign_in);
		
//		frame.pack();
		frame.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SignUp();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			String fname = firstnameField.getText();
			String lname = lastnameField.getText();
			String email = emailField.getText();
			String openbal = Opening.getText();
			String pin = createpinField.getText();
			String pin2 = confirmpinField.getText();
			String ans = securityans1.getText();
			String ans2 = securityans2.getText();
			String gender = "female";
			if (male.isSelected())
				gender = "male";
//		query = "Create table if not exists Signup(Firstname varchar(20),Lastname varchar(20), Emailaddress varchar(30),"	
//     +"AccountNumber INT Auto_Increment primary key, openingBalance bigint, Pin int, SecurityQuestion1 varchar(30), SecurityQuestion2 varchar(30), Gender Varchar(10));";
		
//		query = "Alter table Signup Auto_Increment=1234567890";
		
//		query = "DROP TABLE Signup;";
			
//		query = "ALTER TABLE Signup MODIFY openingBalance float";
			
		query = "Insert into Signup(firstname, lastname, emailaddress, openingbalance, pin, securityquestion1, securityquestion2, gender) values (" + "'" + fname + "'," + "'" + lname + "'," + "'" + email
				+ "'," + "'" + openbal + "'," + "'" + pin + "'," + "'" + ans + "'," + "'" + ans2 + "'," + " ' " + gender
				+ "');";
		
//		query = "truncate table Signup;";
	
//		st.execute(query);
//		System.out.println("Hello...4");
		
			if(!(fname.isBlank() || lname.isBlank() || email.isBlank() 
					|| openbal.isBlank() || pin.isBlank() || pin2.isBlank() || ans.isBlank() || ans2.isBlank()
					|| fname.equals(Placeholder) || lname.equals(Placeholder1) || email.equals(Placeholder2)
					|| openbal.equals(Placeholder3) || pin.equals(Placeholder4) || pin2.equals(Placeholder5)
					|| ans.equals(Placeholder6) || ans2.equals(Placeholder6))) {
				
				if (confirmMydetails(fname) && confirmMydetails(lname) && confirmMymail(email) && checkmyanswer(ans) && checkmyanswer(ans2)) {
				
				if (pin.equals(pin2)) {
					st.execute(query);
					
					firstnameField.setText(Placeholder);
					lastnameField.setText(Placeholder1);
					emailField.setText(Placeholder2);
					Opening.setText(Placeholder3);
					createpinField.setText(Placeholder4);
					createpinField.setEchoChar((char) 0);
					confirmpinField.setText(Placeholder5);
					confirmpinField.setEchoChar((char) 0);
					securityans1.setText(Placeholder6);
					securityans2.setText(Placeholder6);
					
					JOptionPane.showMessageDialog(null, "Registration Successful");
					System.out.println("Hello...4");
					frame.dispose();
					Labs.Login hold = new Login();
				}
				else {
					JOptionPane.showMessageDialog(null, "Password Mismatch");
				}
			}
		} else {
				JOptionPane.showMessageDialog(null, "Registration failed!.. \n" + "Fields cannot be Empty");
			}
		} catch (Exception ex) {
			System.out.println("Error occured: " + ex.getMessage());
			JOptionPane.showMessageDialog(null, "Registration failed!!");
		}
	}
	
	
	
	public boolean confirmMydetails(String name) {
		String confirmmy_name = "^[^0-9@._%+-][a-zA-Z0-9-.]*";
		boolean result = Pattern.matches(confirmmy_name, name);
		if (!result) {
			JOptionPane.showMessageDialog(Opening, "Name cannot begin with numbers or characters...");
		}
		return result;
	}
	
	public boolean confirmMymail(String mail) {
		String confirm_email = "[^0-9@._%+-][a-zA-Z0-9\\.]{5,}@[a-z]+(\\.[a-z]+)+$";
		boolean result = Pattern.matches(confirm_email, mail);
		if (!result) {
			System.out.println("Hmmmmm....");
			JOptionPane.showMessageDialog(Opening, "Email cannot begin with numbers or characters...");
			System.out.println("Okayyyy....");
		}
		return result;
	}
	
	public boolean checkmyanswer(String answer) {
		System.out.println("Testing");
		String myanswer = "^[^0-9@._%+][a-zA-Z .-]*";
		System.out.println("WOw");
		boolean result = Pattern.matches(myanswer, answer);
		if (!result) {
			JOptionPane.showMessageDialog(Opening, "Cannot accept numbers...");
		}
		return result;
	}
}
