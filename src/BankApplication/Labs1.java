package BankApplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import BankApplication.Labs.Login;

import BankApplicationSQLConn.ConnectionLoader;

public class Labs1 {
	private static Connection conn;
	static String LoginID ="";
	static String acctnumID ="";
	static String acctbalID ="";
	static String chnpassID ="";
	static int accbal;
	static int accbal2;
	
	
static class Loginn extends JFrame {
	JFrame frame;
	JDialog mydialog;
	Font font;
	JTextField loginname;
	JPasswordField pinField;
	JLabel username, pin, do_you, sign_up,fogpass;
	JButton log_in;
	String Placeholder = "E-mail", Placeholder1 = "Pin";
	Icon icon;
	Statement st;
	String query;
	
	public Loginn() {
		
		st = ConnectionLoader.loadsql();
		frame = new JFrame();
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setTitle("Login");
		frame.setSize(400, 500);
		frame.setResizable(false);
		frame.setLayout(null);
		
		font = new Font("Inconsolata", Font.PLAIN, 11);
		
		icon = new ImageIcon("/U+1F6B9");
		
		username = new JLabel("Username/Email: ");
		username.setBounds(60, 270, 90, 20);
		username.setFont(font);
		
		pin = new JLabel("Pin: ");
		pin.setBounds(60, 300, 80, 20);
		pin.setFont(font);
		
		do_you = new JLabel("Don't have an account? ");
		do_you.setFont(font);
		do_you.setBounds(120, 350, 120, 15);
		
		sign_up = new JLabel("Sign Up");
		sign_up.setFont(font);
		sign_up.setBounds(245, 350, 50, 15);
		
		fogpass = new JLabel("Forget Password?");
		fogpass.setFont(font);
		fogpass.setBounds(140, 370, 100, 15);
		
		font = new Font("Inconsolata", Font.ITALIC, 11);
		
		loginname = new JTextField(30);
		loginname.setText(Placeholder);
		loginname.setBounds(150, 270, 150, 20);
		loginname.getText();
		username.setIcon(null);
		loginname.setFont(font);
//		loginname.setCaretColor(Color.RED);
		pinField = new JPasswordField(30);
		pinField.setBounds(150, 300, 150, 20);
		pinField.setText(Placeholder1);
		pinField.setEchoChar((char) 0);
//		pinField.setCaretColor(Color.RED);
		pinField.setFont(font);
		pinField.getText();
		
		loginname.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String uname = loginname.getText();
				loginname.setText("");
			}
		});
		
		pinField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				pinField.setText("");
				pinField.setEchoChar((char) '*');
			}
			
		});
		
		sign_up.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				new SignUp();
			}
		});
		
		font = new Font("Inconsolata", Font.BOLD, 11);
		
		log_in = new JButton("Login");
		log_in.setFont(font);
		log_in.setBounds(160, 330, 70, 20);
		log_in.setFocusable(false);
		
		log_in.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String held = "";
				String acctnum = "";
				String acctball = "";
				String chnpass = "";
				try {
					String uname = loginname.getText(),
							pass = pinField.getText();
					
					query = "Login";
					
					if(!(uname.isBlank() || pass.isBlank() || uname.equals(Placeholder) || pass.equals(Placeholder1))) {
						query = "Select * from Signup where email = '"+ uname+"' and pin = '"+ pass + "'";
						 
						query = "SELECT * FROM Signup WHERE Emailaddress = '" + uname + "' AND Pin = '" + pass + "'";
						
						ResultSet rs = st.executeQuery(query);
					
						
						if (rs.next() && rs.getString(9).equals(pass)) {
							 held = rs.getString(1);
							 acctnum = rs.getString(4);
							 acctball = rs.getString(5);
							 chnpass = rs.getString(6);
//							 System.out.println(held);
							JOptionPane.showMessageDialog(null, "Login successful...");
							LoginID = held;
							acctnumID = acctnum;
							acctbalID = acctball;
							accbal = Integer.parseInt(acctball);
							chnpassID = chnpass;
							frame.dispose();
//							new homePage();
							Labs1.homePage hold = new homePage();
							System.out.println(hold);
						}
						else
							JOptionPane.showMessageDialog(null, "Invalid Credentials");
							
					}else JOptionPane.showMessageDialog(null, "Login Failed");

				}catch(Exception ae) {
					System.out.println("Error: " + ae.getMessage());
				}
			
//			System.out.println(LoginID);
			}
		});
		fogpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JDialog dialog = createDialog("Change password");
				
				JPanel mypanel = new JPanel();
				mypanel.setLayout(null);
				JLabel security1 = createLabel("Security Question1: ");
				security1.setBounds(40, 150, 150, 20);
				JLabel security2  = createLabel("Security Question2:  ");
				security2.setBounds(40, 210, 150, 20);
				JLabel newpassord = createLabel("Enter New passord:");
				newpassord.setBounds(40, 270, 150, 20);
				JLabel connewpassord = createLabel("Confirm New password:");
				connewpassord.setBounds(40, 330, 150, 20);
				JTextField answer1 = createTextField();
				answer1 .setBounds(130, 180, 150, 20);
				JTextField answer2 = createTextField();
				answer2.setBounds(130, 240, 150, 20);
				JTextField newpassField = createTextField();
				newpassField.setBounds(130, 300, 150, 20);
				JTextField newpassField2 = createTextField();
				newpassField2.setBounds(130,360, 150, 20);
//				
				JButton chng = new JButton("Change");
				chng.setFont(font);
				chng.setFocusable(false);
				chng.setBounds(120, 400, 100, 20);
				
				
				chng.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							String secure = security1.getText();
							String secure2 = security2.getText();
							String mynewpass = newpassField.getText();
							String confirmmynewpass = newpassField2.getText();
							if(!(secure.isBlank() || secure2.isBlank())) {
//							query = "Create table SecurityQuestions(SecurityQuestions1 varchar(50),SecurityQuestions2 varchar(50)); ";
							query = "Update Signup set Pin = '" + mynewpass +" where pin = '"+ confirmmynewpass + "; ";
							st.executeUpdate(query);
							System.out.println("hello..4");
							}
						}catch(Exception eee) {
							System.out.println("Error: " +eee.getMessage());
						}
					}
				});
				
				mypanel.add(security1);
				mypanel.add(security2);
				mypanel.add( newpassord);
				mypanel.add(connewpassord);
				mypanel.add(answer1);
				mypanel.add(answer2);
				mypanel.add(newpassField);
				mypanel.add(newpassField2);
				mypanel.add(chng);
				
				
				dialog.add(mypanel);
				
				dialog.setSize(400, 500);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				
			}
		});
		frame.add(username);
		frame.add(loginname);
		frame.add(pin);
		frame.add(pinField);
		frame.add(log_in);
		frame.add(do_you);
		frame.add(sign_up);
		frame.add(fogpass);
		
//		frame.pack();
		frame.setLocationRelativeTo(null);
	}
		public JLabel createLabel(String text) {
			JLabel label = new JLabel();
			label.setText(text);
			label.setFont(font);
			return label;
			
		}
		
		public JTextField createTextField() {
			JTextField textField = new JTextField(25);
			textField.setFont(font);
			return textField;
		}
		
		public JDialog createDialog(String title) {
			JDialog mydialog = new  JDialog();
			mydialog.setTitle(title);
			mydialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			mydialog.setFont(font);
			return mydialog;
		}
	
	
	}

 
 
static class homePage extends JFrame implements ActionListener{
		JFrame frame, frame1;
		JDialog mydialog;
		Font font;
		Color blue;
		JMenuBar menu;
		JMenu services, recharge, loans;
		JMenuItem transfers, bills, depo, witdraw, Settings;
		JMenuItem credit, log_out, changepass;
		JMenuItem data, top_up;
		JMenuItem  space1, space2, space3, space4, space5, space6;
		JLabel accountLabel, accountnumLabel, accountbalLabel, bank;
		JRadioButton thisbnk, otherbnk;
		JPanel transactPanel;
		JTable thisTable;
		String query;
		Statement st;
		
		public homePage() {
			loadsql();
			frame = new JFrame();
			frame.setLayout(null);
		
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setTitle("HOUK Bank");
			frame.setSize(400, 500);
			frame.setResizable(false);
			
			font = new Font("Inconsolata", Font.BOLD, 10);
			frame.setFont(font);
			
			menu = new JMenuBar();
			services = new JMenu("\u2630"); services.setFont(font);
			recharge = new JMenu("Airtime and Data"); recharge.setFont(font);
			loans = new JMenu("Quick Loans"); loans.setFont(font);
			transfers = new JMenuItem("Transfers"); transfers.setFont(font);
			bills = new JMenuItem("Bills and Payments"); bills.setFont(font);
			depo = new JMenuItem("Deposit"); depo.setFont(font);
			witdraw = new JMenuItem("Withdraw"); witdraw.setFont(font);
			log_out = new JMenuItem("Log Out"); log_out.setFont(font);
			top_up = new JMenuItem("Mobile Top-Up"); top_up.setFont(font);
			data = new JMenuItem("Data Bundles"); data.setFont(font);
			credit = new JMenuItem("Quick Credits"); credit.setFont(font);
			Settings = new JMenu("Settings"); Settings.setFont(font);
			changepass = new JMenuItem("Change Password"); changepass.setFont(font);
			space1 = new JMenuItem(""); space2 = new JMenuItem(""); space3 = new JMenuItem(""); space4 = new JMenuItem(""); space5 = new JMenuItem("");
			space6 = new JMenuItem("");
			
			// ADDING ACTIONLISTENERS
			
			bills.addActionListener(this); log_out.addActionListener(this); transfers.addActionListener(this);
			credit.addActionListener(this); depo.addActionListener(this); witdraw.addActionListener(this); top_up.addActionListener(this);
			
			recharge.add(top_up);
			recharge.add(data);
			
			loans.add(credit);
			Settings.add(changepass);
			services.add(transfers);
			services.add(depo);
			services.add(witdraw);
			services.add(bills);
			services.add(recharge);
			services.add(loans);
			services.add(Settings);
			services.add(space1);
			services.add(space2);
			services.add(space3);
			services.add(space4);
			services.add(space5);
			services.add(space6);
			services.add(log_out);
			
			menu.add(services);
			
	top_up.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JDialog dialog = createDialog("Airtime Top-up");
					
					JPanel mypanel = new JPanel();
					mypanel.setLayout(null);
					JLabel lab = createLabel("Enter Number: ");
					lab.setBounds(40, 250, 90, 20);
					JLabel lab2  = createLabel("Enter Amount: ");
					lab2.setBounds(40, 280, 90, 20);
					JLabel lab3 = createLabel("Enter Network:");
					lab3.setBounds(40, 310, 90, 20);
					JTextField number = createTextField();
					number.setBounds(150, 250, 150, 20);
					JTextField amt = createTextField();
					amt.setBounds(150, 280, 150, 20);
					String [] netwrk = {"Glo","Mtn","Airtel","9Mobile","Smile","Starcomms"};
					JComboBox<String> netwrk1 = new JComboBox<>(netwrk);
					netwrk1.setBounds(150, 310, 150, 20);
					netwrk1.setFont(font);
					JButton buy = new JButton("Buy");
					buy.setFont(font);
					buy.setFocusable(false);
					buy.setBounds(160, 350, 70, 20);
					
					mypanel.add(lab);
					mypanel.add(lab2);
					mypanel.add(lab3);
					mypanel.add(number);
					mypanel.add(amt);
					mypanel.add(netwrk1);
					mypanel.add(buy);
					
					dialog.add(mypanel);
					
					dialog.setSize(400, 500);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
					
					number.addKeyListener(new KeyAdapter() {
						@SuppressWarnings({  "static-access" })
						public void keyPressed(KeyEvent ke) {
							String num = number.getText();
							char c = ke.getKeyChar();
							if(c>= '0'&& c<= '9' || c == ke.VK_BACK_SPACE ) {
							}else {
								JOptionPane.showMessageDialog(null, "Invalid Input\nEnter a valid number!","",JOptionPane.ERROR_MESSAGE);
							}
						}
					});
					
					amt.addKeyListener(new KeyAdapter() {
						@SuppressWarnings("static-access")
						public void keyPressed(KeyEvent ke) {
							String num = amt.getText();
							char c = ke.getKeyChar();
							if(c>= '0'&& c<= '9' || c == ke.VK_BACK_SPACE ) {
							}else {
								JOptionPane.showMessageDialog(null, "Invalid Input\nEnter a valid number!","",JOptionPane.ERROR_MESSAGE);;
							}
						}
					});
					
					buy.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String num = number.getText();
							String am = amt.getText();
							try {
							int len = num.length();
							int len1 = am.length();

							if(len != 11 || num.isBlank() || am.isBlank() || len1 == 1) {
								JOptionPane.showMessageDialog(null, "Invalid Input! \nPlease Input correct details" ,"",JOptionPane.ERROR_MESSAGE);
								number.setText("");
								amt.setText("");
							}
							else {
							int ma = Integer.parseInt(am);
						    accbal = accbal - ma;
							JOptionPane.showMessageDialog(null, "Your airtime of \u20A6"+am+" for "+num+" has been successfully recharged.\n"+"\u20A6"
									+ma+" has been deducted from your account.");
							String ac = String.valueOf(accbal);
							acctbalID = ac.toString();
							accountbalLabel.setText(acctbalID);
							try {
								query = "update  signup set openingBalance = '"+ acctbalID+"' where Firstname = '" + LoginID+ "';";
								   st.executeUpdate(query);
								}catch (Exception eee) {
									System.out.println(eee);
								}
							}
							}catch(NumberFormatException ex) {
								System.out.println("Please Input a number!! " + ex.getMessage());
//								JOptionPane.showMessageDialog(null, "Please Input a number!!");
								}
							}
					});
				}
			});

	data.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JDialog dialog = createDialog("Data");
			
			JPanel mypanel = new JPanel();
			mypanel.setLayout(null);
			JLabel lab = createLabel("Enter Number: ");
			lab.setBounds(40, 250, 120, 20);
			JLabel lab2  = createLabel("Enter Network: ");
			lab2.setBounds(40, 280, 120, 20);
			JLabel lab3 = createLabel("Select Data Amount: ");
			lab3.setBounds(40, 310, 120, 20);
			JTextField number = createTextField();
			number.setBounds(170, 250, 150, 20);
			String [] netwrk = {"Glo","Mtn","Airtel","9Mobile","Smile","Starcomms"};
			JComboBox<String> netwrk1 = new JComboBox<>(netwrk);
			netwrk1.setBounds(170, 280, 150, 20);
			netwrk1.setFont(font);
			String [] dt = {"1GB","2GB","3GB","4GB","5GB","6GB","7GB","10GB"};
			JComboBox<String> td = new JComboBox<>(dt);
			td.setFont(font);
			td.setFocusable(false);
			td.setBounds(170, 310, 50, 20);
			JButton buy = new JButton("Buy");
			buy.setFont(font);
			buy.setFocusable(false);
			buy.setBounds(160, 350, 70, 20);
			
			mypanel.add(lab);
			mypanel.add(number);
			mypanel.add(lab3);
			mypanel.add(td);
			mypanel.add(lab2);
			mypanel.add(netwrk1);
			mypanel.add(buy);
			
			dialog.add(mypanel);
			
			dialog.setSize(400, 500);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
			
			number.addKeyListener(new KeyAdapter() {
				@SuppressWarnings("static-access")
				public void keyPressed(KeyEvent ke) {
					String num = number.getText();
					char c = ke.getKeyChar();
					if(c>= '0'&& c<= '9' || c == ke.VK_BACK_SPACE ) {
					}else {
						JOptionPane.showMessageDialog(null, "Invalid Input\nEnter a valid number!","",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			buy.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String num = number.getText();
					String am = td.getSelectedItem().toString();
					String chk = netwrk1.getSelectedItem().toString();
					try {
					int x = 0;
					if(am.equals(dt[0])) {
						x = 1000;
					}else if(am.equals(dt[1])) {
						x = 2000;
					}else if(am.equals(dt[2])) {
						x = 3000;
					}else if(am.equals(dt[3])) {
						x = 3500;
					}else if(am.equals(dt[4])) {
						x = 5000;
					}else if(am.equals(dt[5])) {
						x = 6000;
					}else if(am.equals(dt[6])) {
						x = 6500;
					}else if(am.equals(dt[7])) {
						x = 9000;
					}
					int len = num.length();
					int y = x;
					accbal = accbal - y;
					String ac = String.valueOf(accbal);
					acctbalID = ac.toString();
					accountbalLabel.setText(acctbalID);
					try {
					query = "update  signup set openingBalance = '"+ acctbalID+"' where Firstname = '" + LoginID+ "';";
					   st.executeUpdate(query);
					}catch (Exception eee) {
						System.out.println(eee);
					}
					if(len != 11) {
						JOptionPane.showMessageDialog(null, "Invalid Input","",JOptionPane.ERROR_MESSAGE);
						number.setText("");
					}else {
					JOptionPane.showMessageDialog(null, "Your Data subscription of "+ am + " for " + chk + " number " + "(" + num + ")" + " was successful."
							+"\n"+ "\u20A6" + x + " has been debited from your account.");
					number.setText("");
					}
					}catch(NumberFormatException ex) {
						JOptionPane.showConfirmDialog(null,"Fields cannot be empty!!");
					}
				}
			});
		}
	});
			
	transfers.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JDialog dialog = createDialog("Transfers");
			
			JPanel mypanel = new JPanel();
			mypanel.setLayout(null);
			
			JLabel name = createLabel("Account Name: ");
			name.setBounds(40, 160, 120, 20);
			JLabel accnt  = createLabel("Account Number");
			accnt.setBounds(40, 190, 120, 20);
			JLabel amount = createLabel("Amount: ");
			amount.setBounds(40, 220, 120, 20);
			JTextField repname = createTextField();
			repname.setBounds(170, 160, 150, 20);
			JTextField entacc = createTextField();
			entacc.setBounds(170, 190, 150, 20);
			JTextField amountfield = createTextField();
			amountfield.setBounds(170, 220, 150, 20);
			JLabel bnknam = createLabel("Bank Name: ");
			bnknam.setBounds(40, 280, 120, 20);
			JTextField entbnknam = createTextField();
			entbnknam.setBounds(170, 280, 150, 20);
			JLabel enterpin = createLabel("Enter Pin: ");
			enterpin.setBounds(40, 310, 120, 20);
			JTextField enterpinfield = createTextField();
			enterpinfield.setBounds(170, 310, 150, 20);
			
			JButton trans = new JButton("Transfer");
			trans.setFont(font);
			trans.setFocusable(false);
			trans.setBounds(140, 340, 90, 20);
			
			bank = new JLabel("Select Bank: ");
			bank.setBounds(40, 250, 90, 20);
			bank.setFont(font);
			thisbnk = new JRadioButton("Houk Bank");
			thisbnk.setBounds(165, 250, 90, 20);
			thisbnk.setFont(font);
//			thisbnk.setSelected(true);
			thisbnk.setFocusable(false);
			otherbnk = new JRadioButton("Other Banks");
			otherbnk.setBounds(260, 250, 95, 20);
			otherbnk.setFont(font);
			otherbnk.setFocusable(false);
			ButtonGroup genderGroup = new ButtonGroup();
			genderGroup.add(otherbnk);
			genderGroup.add(thisbnk);
			
			
			mypanel.add(name);
			mypanel.add(repname);
			mypanel.add(accnt);
			mypanel.add(entacc);
			mypanel.add(amount);
			mypanel.add(amountfield);
			mypanel.add(bank);
			mypanel.add(thisbnk);
			mypanel.add(otherbnk);
			mypanel.add(bnknam);
			mypanel.add(entbnknam);
			mypanel.add(enterpin);
			mypanel.add(enterpinfield);
			mypanel.add(trans);
			
			dialog.add(mypanel);
			
			dialog.setSize(400, 500);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
			
			
			thisbnk.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					entbnknam.setEditable(false);
					entbnknam.setEnabled(false);
				}
				
			});
			
			
			otherbnk.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					entbnknam.setEditable(true);
					entbnknam.setEnabled(true);
				}
				
			});
			
			entacc.addKeyListener(new KeyAdapter() {
				@SuppressWarnings("static-access")
				public void keyPressed(KeyEvent ke) {
					String num = entacc.getText();
					char c = ke.getKeyChar();
					if(c>= '0'&& c<= '9' || c == ke.VK_BACK_SPACE ) {
					}else {
						JOptionPane.showMessageDialog(null, "Invalid Input\nEnter a valid number!","",JOptionPane.ERROR_MESSAGE);
						entacc.setText("");
					}
				}
			});
			
			amountfield.addKeyListener(new KeyAdapter() {
				@SuppressWarnings("static-access")
				public void keyPressed(KeyEvent ke) {
					String num = amountfield.getText();
					char c = ke.getKeyChar();
					if(c>= '0'&& c<= '9' || c == ke.VK_BACK_SPACE ) {
					}else {
						JOptionPane.showMessageDialog(null, "Invalid Input\nEnter a valid number!","",JOptionPane.ERROR_MESSAGE);
						amountfield.setText("");
					}
				}
			});
			
	trans.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
				String acname = repname.getText();
				String acnum = entacc.getText();
				String amnt = amountfield.getText();
				String pin = enterpinfield.getText();
				String banknam = "";
				if (otherbnk.isSelected()) {
					banknam = entbnknam.getText();;
				System.out.println(banknam);	
				}
				else if (thisbnk.isSelected()) {
					banknam = "Houk Bank";
					System.out.println(banknam);
				}
				int amt = Integer.parseInt(amnt);	
				accbal = accbal - amt;
				accbal2 = accbal2 + amt;
				String ac = String.valueOf(accbal);
				acctbalID = ac.toString();
				accountbalLabel.setText(acctbalID);
				int aacnum = Integer.parseInt(acnum);
				int aacnumm = Integer.parseInt(acctnumID);
//				updateRecordBalance(aacnumm,accbal);
				try {
				query = "select openingbalance,firstname,lastname from signup; ";
				st.execute(query);
				}catch(Exception eee) {
					System.err.println(eee);
				}
				try {
			query = "update signup set openingbalance = "+ accbal +" where  Firstname  = '" + LoginID + "';";
			st.execute(query);
				}catch(Exception eee) {
					System.err.println(eee);
				}
				try {
					query = "update signup set openingbalance = "+ accbal2 +" where  AccountNumber  = '" +acnum + "';";
					st.execute(query);
				}catch(Exception eee) {
					System.err.println(eee);
				}
//					query = "update Signup set Pin = '" + these +" where pin = '"+those + "; ";

//				try {
//				
//				query = "Register";
//			
//				if(!(acname.isBlank() ||acnum.isBlank() ||amnt.isBlank() 
//						|| pin.isBlank() ||banknam.isBlank())) {
//						
//						query = "Insert into Transfer(Recipient_AccountName, Recipient_AccountNumber, Amount, Bank Name, gender, pin) "
//								+ "values(" + "'" + acname + "'," + "'" + acnum + "'," + "'" + amnt
//								+ "'," + "'" + banknam
//								+ "');";
//						
//						System.out.println("Hello...4");
//						
//						query = "SELECT * FROM Transfer WHERE Recipient_AccountName = '" 
//						+ acname + "', Recipient_AccountNumber = '" + acnum + "', AND Pin = '" + pin + "'";
//						
//						ResultSet rs = st.executeQuery(query);
//						
//						if (rs.next() && rs.getString(6).equals(pin)) {
//							JOptionPane.showMessageDialog(null, "Login successful...");
//							usernameField.setText("");
//							passWord.setText("");
//						}
//						else
//							JOptionPane.showMessageDialog(null, "Invalid Credentials");
//					}
//					else 
//						JOptionPane.showMessageDialog(null, "Login Failed!!...");
//				}
//				catch (Exception ex) {
//					System.out.println("Error Occured...: " + ex.getMessage());
//				}
		}
		
	});
		}
	});
			log_out.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frame.dispose();
					new Loginn();
				}
			});
			changepass.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JDialog dialog = createDialog("Change Password");
					JPanel mypanel = new JPanel();
					mypanel.setLayout(null);
					
					JLabel lab = createLabel("Enter Old Password: ");
					lab.setBounds(40, 250, 120, 20);
					JLabel lab2  = createLabel("Enter New password: ");
					lab2.setBounds(40, 280, 120, 20);
					
					JButton chn = new JButton("Change");
					chn.setFont(font);
					chn.setFocusable(false);
					chn.setBounds(160, 350, 100, 20);
					
					JTextField oldpass = createTextField();
					oldpass.setText(chnpassID);
					oldpass.setEditable(false);
					oldpass.setBounds(170, 250, 150, 20);
					
					JTextField newpass = createTextField();
					newpass.setBounds(170, 280, 150, 20);
					
					mypanel.add(lab);
					mypanel.add(lab2);
					mypanel.add(oldpass);
					mypanel.add(newpass);
					mypanel.add(chn);
					
					dialog.add(mypanel);
					dialog.setSize(400, 500);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
					
					oldpass.addKeyListener(new KeyAdapter() {
						@SuppressWarnings({  "static-access" })
						public void keyPressed(KeyEvent ke) {
							String num =oldpass.getText();
							char c = ke.getKeyChar();
							if(c>= '0'&& c<= '9' || c == ke.VK_BACK_SPACE ) {
							}else {
								JOptionPane.showMessageDialog(null, "Invalid Input\nEnter a valid number!","",JOptionPane.ERROR_MESSAGE);
							}
						}
					});
					newpass.addKeyListener(new KeyAdapter() {
						@SuppressWarnings({  "static-access" })
						public void keyPressed(KeyEvent ke) {
							String num = newpass.getText();
							char c = ke.getKeyChar();
							if(c>= '0'&& c<= '9' || c == ke.VK_BACK_SPACE ) {
							}else {
								JOptionPane.showMessageDialog(null, "Invalid Input\nEnter a valid number!","",JOptionPane.ERROR_MESSAGE);
							}
					
						}});
					
					chn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								String opass = oldpass.getText(),
										npass = newpass.getText();
								if(!(npass.isBlank())) {
								 query = "update  signup set Pin = '"+ npass+"' where pin = '" + opass+ "';";
								   st.executeUpdate(query);
								 System.out.println("Hello...5");
								 
								 JOptionPane.showMessageDialog(null, "Password Changed Successfully");
								 oldpass.setText(npass);
								 newpass.setText("");
								 
								
								}else {
									JOptionPane.showMessageDialog(null, "Enter new password");
									oldpass.setText("");
									newpass.setText("");
								}
							}catch (Exception ee) {
								System.err.println("Error: " + ee.getMessage());
							}
						}
					});
					
				}
			});
			// INITIALIZING PANELS
			transactPanel = new JPanel();
			transactPanel.setBounds(0, 100, 420, 300);
			
			// LABELS
			accountLabel = new JLabel();
			accountLabel.setText(LoginID);
//			accountLabel.setText("qwertyuioy");
//			System.out.println(LoginID);
			accountLabel.setFont(font);
			accountLabel.setBounds(20, 10, 80, 40);
			
			accountnumLabel = new JLabel();
			accountnumLabel.setText(acctnumID);
			accountnumLabel.setFont(font);
			accountnumLabel.setBounds(20, 25, 80, 40);
			
			accountbalLabel = new JLabel("\u20A6");
			accountbalLabel.setText(acctbalID);
			accountbalLabel.setFont(font);
			accountbalLabel.setBounds(270, 20, 100, 40);
			
			font = new Font("Verdana", Font.PLAIN, 10);
			
			//ADDING LABELS TO FRAME
			frame.add(accountLabel);
			frame.add(accountnumLabel);
			frame.add(accountbalLabel);
			frame.add(transactPanel);
			
			frame.add(menu);
			frame.setJMenuBar(menu);	
			
//			frame.pack();
			frame.setLocationRelativeTo(null);
		}
		
		public JLabel createLabel(String text) {
			JLabel label = new JLabel();
			label.setText(text);
			label.setFont(font);
			return label;
			
		}
		
		public JTextField createTextField() {
			JTextField textField = new JTextField(25);
			textField.setFont(font);
			return textField;
		}
		
		public JDialog createDialog(String title) {
			JDialog mydialog = new  JDialog();
			mydialog.setTitle(title);
			mydialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			mydialog.setFont(font);
			return mydialog;
		}
		public void loadsql() {
			try {

//				String query = "insert into master_data values(4,'haleemah',30);";
//				st = ConnectionLoader.loadsql();
//				 st.execute(query);
				 System.out.println("Hello...4");
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}

//		public static void main(String[] args) {
//			// TODO Auto-generated method stub
////			new homePage();
//			new Loginn();
//
//		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Loginn hold = new Loginn();
//Labs.homePage hold = new homePage();
//		new homePage();
//		new Login();
	}

//	public static void updateRecordBalance(int Acctnumm, int AccBalance) {
//		try {
//			String sqlQuery = "UPDATE Signup SET OpeningBalance=? WHERE AccountNumber=?";
//			PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
//			preparedStatement.setInt(1, AccBalance);
//			preparedStatement.setInt(2, Acctnumm);
//			preparedStatement.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//	}

	
	
}
