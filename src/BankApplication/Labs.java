package BankApplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.ItemSelectable;
import java.lang.Math;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;

import BankApplication.Labs.Login;

import BankApplicationSQLConn.BankAppConnectionLoader;

public class Labs {
	private static Connection conn;
	static String loginID = "";
	static String accID = "";
	static String repaccID = "";
	static String repaccnumID = "";
	static String acctnumID ="";
	static String acctbalID ="";
	static String recipacctbalID ="";
	static String chnpassID ="";
	static String newpassID ="";
//	static String accnameID ="";
	static String securityID1 ="";
	static String securityID2 ="";
	static int accbal;
	

	static class Login extends JFrame {
		DefaultTableModel thisTableModel, model;
		JTable thisTable;
		JFrame frame;
		JDialog mydialog;
		Font font;
		JTextField loginname;
		JPasswordField pinField;
		JLabel username, pin, do_you, sign_up, fogpass, depo, witdraw;
		JButton log_in;
		String Placeholder = "E-mail", Placeholder1 = "Pin";
		Icon icon;
		Statement st, st1, st2;
		String query, held = loginID, useraccnum, useraccbal, chnpass, balance, depname, amountdepo, newpass, ackname, ackacc, acksec1, acksec2;
		
		public Login() {
			st = BankAppConnectionLoader.loadsql();
			st1 = BankAppConnectionLoader.loadsql();
			st2 = BankAppConnectionLoader.loadsql();
			
			frame = new JFrame();
			
			frame.setVisible(true);
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setTitle("Login");
			frame.setSize(500, 600);
			frame.setResizable(false);
			frame.setLayout(null);
			
			font = new Font("Verdana", Font.PLAIN, 11);
			
			icon = new ImageIcon("/U+1F6B9");
			
			Locale [] locales = Calendar.getAvailableLocales();
			
			Locale locale = new Locale("en", "NG");
					
			NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
			
			
			username = new JLabel("Username/Email: ");
			username.setBounds(100, 330, 120, 20);
			username.setFont(font);
			
			pin = new JLabel("Pin: ");
			pin.setBounds(100, 360, 80, 20);
			pin.setFont(font);
			
			do_you = new JLabel("Don't have an account? ");
			do_you.setFont(font);
			do_you.setBounds(140, 420, 150, 15);
			
			sign_up = new JLabel("Sign Up");
			sign_up.setFont(font);
			sign_up.setBounds(285, 420, 50, 15);
			
			fogpass = new JLabel("Forget Password?");
			fogpass.setFont(font);
			fogpass.setBounds(130, 435, 100, 15);
			
			depo = new JLabel("Deposit?");
			depo.setFont(font);
			depo.setBounds(240, 435, 50, 15);
			
			witdraw = new JLabel("Withdraw?");
			witdraw.setFont(font);
			witdraw.setBounds(300, 435, 100, 15);
			
			font = new Font("Verdana", Font.ITALIC, 10);
			
			loginname = new JTextField(30);
			loginname.setText(Placeholder);
			loginname.setBounds(220, 330, 150, 20);
			loginname.getText();
			username.setIcon(null);
			loginname.setFont(font);
//			loginname.setCaretColor(Color.RED);
			pinField = new JPasswordField(30);
			pinField.setBounds(220, 360, 150, 20);
			pinField.setText(Placeholder1);
			pinField.setEchoChar((char) 0);
//			pinField.setCaretColor(Color.RED);
			pinField.setFont(font);
			pinField.getText();
			
			loginname.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if (loginname.getText().length() == 0) {
						loginname.setText(Placeholder);
					}
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					if (loginname.getText().equals(Placeholder)) {
						loginname.setText("");
					}
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
			
			pinField.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if (pinField.getText().length() == 0) {
						pinField.setText(Placeholder1);
						pinField.setEchoChar((char) 0);
					}
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					if (pinField.getText().equals(Placeholder1)) {
						pinField.setText("");
						pinField.setEchoChar((char) '*');
					}
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
			
			fogpass.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					JDialog dialog = createDialog("Change Password");
					
					JPanel mypanel = new JPanel();
					mypanel.setLayout(null);
					JLabel myacc = createLabel("Your account number: ");
					myacc.setBounds(90, 90, 150, 20);
					JLabel getname = createLabel("Username: ");
					getname.setBounds(90, 140, 150, 20);
					JLabel security1 = createLabel("Your favourite color?");
					security1.setBounds(90, 190, 150, 20);
					JLabel security2  = createLabel("Your pet peeve?");
					security2.setBounds(90, 240, 150, 20);
					JLabel newpin = createLabel("Enter New Pin: ");
					newpin.setBounds(90, 290, 150, 20);
					JLabel connewpin = createLabel("Confirm New Pin: ");
					connewpin.setBounds(90, 340, 150, 20);
					JTextField myaccField = createTextField();
					myaccField.setBounds(230, 90, 150, 20);
					JTextField nameField = createTextField();
					nameField.setBounds(230, 140, 150, 20);
					nameField.setEditable(false);
					JTextField answer1 = createTextField();
					answer1 .setBounds(230, 190, 150, 20);
					JTextField answer2 = createTextField();
					answer2.setBounds(230, 240, 150, 20);
					JPasswordField newpinField = createPassField();
					newpinField.setBounds(230, 290, 150, 20);
					newpinField.setEchoChar((char) '*');
					JPasswordField newpinField2 = createPassField();
					newpinField2.setBounds(230, 340, 150, 20);
					newpinField2.setEchoChar((char) '*');
//					
					JButton chng = new JButton("Change");
					chng.setFont(font);
					chng.setFocusable(false);
					chng.setBounds(200, 390, 100, 20);
					
					myaccField.addFocusListener(new FocusListener() {
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							ackname = "";
							ackacc = "";
							acksec1 = "";
							acksec2 = "";
							try {
								String prnt = myaccField.getText();
								
								if (!(prnt.isBlank() && prnt.length() == 11)) {
									
									if (confirmMyaccnum(prnt)) {
										query = "SELECT * FROM Signup where AccountNumber = '" + prnt +"';";
										
										ResultSet rs = st.executeQuery(query);
										
										if (rs.next() && rs.getString(4).equals(prnt)) {
											ackname = rs.getString(1);
											ackacc = rs.getString(4);
											acksec1 = rs.getString(7);
											acksec2 = rs.getString(8);
											System.out.println("I'm getting here");
											accID = ackname;
											acctnumID = ackacc;
											securityID1 = acksec1;
											securityID2 = acksec2;
											nameField.setText(accID);
										}
										else JOptionPane.showMessageDialog(null, "Invalid Account Number");
									}
									else JOptionPane.showMessageDialog(null, "Error, check your account number again!");
								}
						}
							catch (Exception my) {
								System.out.println("Error: " + my.getMessage());
							}
						}
						public void focusGained(FocusEvent e) {
							myaccField.getText();
						}
						
						public boolean confirmMyaccnum(String accnum) {
							String confirm_accnum = "\\d{10}";
							boolean result = Pattern.matches(confirm_accnum, accnum);
							if (!result) {
								System.out.println("Hmmmmm....");
								JOptionPane.showMessageDialog(null, "Invalid Account Number!");
								System.out.println("Okayyyy....");
							}
							return result;
						}
					});
					
					chng.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							held = "";
							useraccnum = "";
							useraccbal = "";
							chnpass = "";
							newpass = "";
							try {
								String thisacc = myaccField.getText();
								String secure = answer1.getText();
								String secure2 = answer2.getText();
								String mynewpin = newpinField.getText();
								String confirmmynewpass = newpinField2.getText();
								if(!(secure.isBlank() || secure2.isBlank() || mynewpin.isBlank() || thisacc.isBlank() || confirmmynewpass.isBlank())) {
//									query = "Create table SecurityQuestions(SecurityQuestions1 varchar(50),SecurityQuestions2 varchar(50)); ";
									
									if (checkmyanswer(secure) || checkmyanswer(secure2) && confirmMypass(thisacc) && confirmMypass(mynewpin) && confirmMypass(confirmmynewpass)) {
										
										if (mynewpin.equals(confirmmynewpass)) {
											query = "Select * from Signup where AccountNumber = '"+ thisacc +"';";
											
											ResultSet rs = st.executeQuery(query);
											
											if (rs.next() && rs.getString(4).equals(thisacc) && rs.getString(7).equals(secure) || rs.getString(8).equals(secure2)) {
												
												query = "Update Signup set Pin = '" + mynewpin +"' where AccountNumber = '"+ thisacc +"'; ";
												st1.executeUpdate(query);
												
												held = rs.getString(1);
												useraccnum = rs.getString(4);
												chnpass = rs.getString(6);
												newpass = mynewpin;
												System.out.println("hello..4");
												loginID = held;
												accID = useraccnum;
												chnpassID = chnpass;
												newpassID = newpass;
												JOptionPane.showMessageDialog(null, "Pin Change Successful");
												dialog.dispose();
												Labs.Login hold = new Login();
												}
												else
													JOptionPane.showMessageDialog(null, "Incorrect Credentials");
											}
											else JOptionPane.showMessageDialog(null, "Pin Mismatch!");
										}
									else JOptionPane.showMessageDialog(null, "Pin Change Failed");
								}
							}catch(Exception eee) {
								System.out.println("Error: " + eee.getMessage());
							}
						}
					});
					
					mypanel.add(myacc);
					mypanel.add(myaccField);
					mypanel.add(getname);
					mypanel.add(nameField);
					mypanel.add(security1);
					mypanel.add(security2);
					mypanel.add(newpin);
					mypanel.add(connewpin);
					mypanel.add(answer1);
					mypanel.add(answer2);
					mypanel.add(newpinField);
					mypanel.add(newpinField2);
					mypanel.add(chng);
					
					
					dialog.add(mypanel);
					
					dialog.setSize(500, 600);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
					
				}
			});
			
			
			depo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
					JDialog dialog = createDialog("Deposit");

					JPanel mypanel = new JPanel();
					mypanel.setLayout(null);

					JLabel accnt = createLabel("Account Number: ");
					accnt.setBounds(90, 190, 120, 20);
					JLabel name = createLabel("Account Name: ");
					name.setBounds(90, 240, 120, 20);
					JLabel rep = createLabel("Depoitor's Name: ");
					rep.setBounds(90, 290, 120, 20);
					JLabel amt = createLabel("Amount: ");
					amt.setBounds(90, 340, 120, 20);
					JTextField entacc = createTextField();
					entacc.setBounds(220, 190, 150, 20);
					JTextField accname = createTextField();
					accname.setBounds(220, 240, 150, 20);
					accname.setEditable(false);
					JTextField deposname = createTextField();
					deposname.setBounds(220, 290, 150, 20);
					JTextField amtfield = createTextField();
					amtfield.setBounds(220, 340, 150, 20);
					
					JButton depos = new JButton("Deposit");
					depos.setFont(font);
					depos.setFocusable(false);
					depos.setBounds(195, 390, 80, 20);

					entacc.addFocusListener(new FocusListener() {
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							ackname = "";
							ackacc = "";
							depname = "";
							amountdepo = "";
							try {
								String prnt = entacc.getText();
								
								if (!(prnt.isBlank() && prnt.length() == 10)) {
									
									if (confirmMyaccnum(prnt)) {
										query = "SELECT * FROM Signup where AccountNumber = '" + prnt +"';";
										
										ResultSet rs = st.executeQuery(query);
										
										if (rs.next() && rs.getString(4).equals(prnt)) {
											ackname = rs.getString(1);
											ackacc = rs.getString(4);
											System.out.println("I'm getting here");
											accID = ackname;
											acctnumID = ackacc;
											accname.setText(accID);
										}
										else
										JOptionPane.showMessageDialog(null, "Invalid Account Number");
									}
									else JOptionPane.showMessageDialog(null, "Error, check your account number again!");
								}
						}
							catch (Exception my) {
								System.out.println("Error: " + my.getMessage());
							}
						}
						public void focusGained(FocusEvent e) {
							entacc.getText();
						}
						
						public boolean confirmMyaccnum(String accnum) {
							String confirm_accnum = "\\d{10}";
							boolean result = Pattern.matches(confirm_accnum, accnum);
							if (!result) {
								System.out.println("Hmmmmm....");
								JOptionPane.showMessageDialog(null, "Invalid Account Number!");
								System.out.println("Okayyyy....");
							}
							return result;
						}
					});
					
					mypanel.add(name);
					mypanel.add(accname);
					mypanel.add(accnt);
					mypanel.add(entacc);
					mypanel.add(rep);
					mypanel.add(deposname);
					mypanel.add(amt);
					mypanel.add(amtfield);
					
					mypanel.add(depos);
					
					dialog.add(mypanel);

					dialog.setSize(500, 600);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);

					depos.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
							String acctname = accname.getText(), acctnum =  entacc.getText(), deponame = deposname.getText(), amount = amtfield.getText();
							
							query = "Deposit";
							
							if(!(acctname.isBlank() || acctnum.isBlank() || deponame.isBlank()|| amount.isBlank())) {
								
								if (checkmyanswer(deponame) && confirmamount(amount)) {
									
//									query = "CREATE TABLE IF NOT EXISTS Deposits(AccountNumber INT primary key, AccountName varchar(20), DepositorssName varchar(30), Amount bigint, AccountBalance bigint)";
									
									query = "SELECT * FROM Signup WHERE Accountnumber = "+ acctnum +";"; 
									
									ResultSet rs = st.executeQuery(query);
									
									if (rs.next() && rs.getString(4).equals(acctnum)) {
										ackname = rs.getString(1);
										ackacc = rs.getString(4);
										balance = rs.getString(5);
										double balanceforint =  Double.parseDouble(balance);
										System.out.println(balanceforint);
										double amountforint = Double.parseDouble(amount);
										System.out.println(balance);
										double finalbalance  = balanceforint + amountforint;
										System.out.println(finalbalance);
										if (amountforint > 0) {
	//									query = "INSERT INTO Deposits(AccountNumber, AccountName, WithdrawersName, Amount, AccountBalance) VALUES (" +"'" + acctnum + "'," + "'" + accname + "'," + "'" +
	//											deponame + "'," + "'" + amount + "'," + "'" + finalbalance + "');";
	//									st1.executeUpdate(query);
	//									
	//									query = "update Signup set openingbalance = " + finalbalance + " where accountnumber = " + acctnum + "; ";
	//									st1.executeUpdate(query);
											JOptionPane.showMessageDialog(null, "Deposit Successful");
										}
										else {
											JOptionPane.showMessageDialog(null, "Cannot deposit this amount");
											}
									}
									else {
										JOptionPane.showMessageDialog(null, "Account name or Account number doesnot exist or match");
									}
							}
							else {
								JOptionPane.showMessageDialog(null, "Incomplete Information!!!");
								}
							}
						}catch (Exception eeee) {
								System.err.println(eeee);
								JOptionPane.showMessageDialog(null, "Error: " + eeee.getMessage());
						}
					}
				});
					 
			}
				public boolean checkmyanswer(String answer) {
					System.out.println("Testing");
					String myanswer = "^[^0-9@._%+-][a-zA-Z .-]*";
					System.out.println("WOw");
					boolean result = Pattern.matches(myanswer, answer);
					if (!result) {
						JOptionPane.showMessageDialog(null, "Depositor's name cannot include numbers...");
					}
					return result;
				}
				
				public boolean confirmamount(String amntdepo) {
					String amount = "\\d{1,}";
					boolean result = Pattern.matches(amount, amntdepo);
					if (!result) {
						System.out.println("Hmmmmm....");
						JOptionPane.showMessageDialog(null, "Invalid Amount Input");
						System.out.println("Okayyyy....");
					}
					return result;
				}
		});
			
		
			
			
		witdraw.addMouseListener(new MouseAdapter(){
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					JDialog dialog = createDialog("Withdrawal");

					JPanel mypanel = new JPanel();
					mypanel.setLayout(null);

					JLabel accnt = createLabel("Account Number: ");
					accnt.setBounds(90, 190, 120, 20);
					JLabel name = createLabel("Account Name: ");
					name.setBounds(90, 240, 120, 20);
					JLabel repnamlabel = createLabel("Withdrawer's Name: ");
					repnamlabel.setBounds(90, 290, 120, 20);
					JLabel amountlabel = createLabel("Amount: ");
					amountlabel.setBounds(90, 340, 120, 20);
					JTextField entacc = createTextField();
					entacc.setBounds(220, 190, 150, 20);
					JTextField accname = createTextField();
					accname.setBounds(220, 240, 150, 20);
					accname.setEditable(false);
					JTextField recipname = createTextField();
					recipname.setBounds(220, 290, 150, 20);
					JTextField amount = createTextField();
					amount.setBounds(220, 340, 150, 20);
					
					JButton withdrawal = new JButton("Withdraw");
					withdrawal.setFont(font);
					withdrawal.setFocusable(false);
					withdrawal.setBounds(210, 390, 90, 20);

					entacc.addFocusListener(new FocusListener() {
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							ackname = "";
							ackacc = "";
							acksec1 = "";
							acksec2 = "";
							try {
								String prnt = entacc.getText();
								
								if (!(prnt.isBlank() && prnt.length() == 11)) {
									
									if (confirmMyaccnum(prnt)) {
										query = "SELECT * FROM Signup where AccountNumber = '" + prnt +"';";
										
										ResultSet rs = st.executeQuery(query);
										
										if (rs.next() && rs.getString(4).equals(prnt)) {
											ackname = rs.getString(1);
											ackacc = rs.getString(4);
											System.out.println("I'm getting here");
											accID = ackname;
											acctnumID = ackacc;
											accname.setText(accID);
										}
										else JOptionPane.showMessageDialog(null, "Invalid Account Number");
									}
									else JOptionPane.showMessageDialog(null, "Error, check your account number again!");
								}
						}
							catch (Exception my) {
								System.out.println("Error: " + my.getMessage());
							}
						}
						public void focusGained(FocusEvent e) {
							entacc.getText();
						}
						
						public boolean confirmMyaccnum(String accnum) {
							String confirm_accnum = "\\d{10}";
							boolean result = Pattern.matches(confirm_accnum, accnum);
							if (!result) {
								System.out.println("Hmmmmm....");
								JOptionPane.showMessageDialog(null, "Invalid Account Number!");
								System.out.println("Okayyyy....");
							}
							return result;
						}
					});
					
					mypanel.add(name);
					mypanel.add(accname);
					mypanel.add(accnt);
					mypanel.add(entacc);
					mypanel.add(repnamlabel);
					mypanel.add(recipname);
					mypanel.add (amountlabel);
					mypanel.add( amount);
					
					mypanel.add(withdrawal);
					
					dialog.add(mypanel);

					dialog.setSize(500, 600);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
					
					withdrawal.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								String acctname = accname.getText(), acctnum =  entacc.getText(), amt = amount.getText(), getname = recipname.getText();
								
								query = "Withdraw";
								
								if(!(acctname.isBlank() || acctnum.isBlank() || amt.isBlank())) {
									
									if (checkmyanswer(getname) && confirmamount(amt)) {
	//									query = "CREATE TABLE IF NOT EXISTS Withdrawal(AccountNumber INT primary key, AccountName varchar(20), WithdrawersName varchar(30), Amount bigint, AccountBalance bigint)";
										
										query = "SELECT * FROM Signup WHERE AccountNumber = "+ acctnum +";"; 
										ResultSet rs = st.executeQuery(query);
										
										if (rs.next() && rs.getString(4).equals(acctnum)) {
											ackname = rs.getString(1);
											ackacc = rs.getString(4);
											balance = rs.getString(5);
											System.out.println(balance);
											double amountforint =  Double.parseDouble(amt);
											double balanceforint =  Double.parseDouble(balance);
											System.out.println(balanceforint);
											double finalbalance  = balanceforint - amountforint;
											System.out.println(finalbalance);
											
											if(balanceforint >= amountforint) {
//												query = "INSERT INTO Deposits(AccountNumber, AccountName, WithdrawersName, Amount, AccountBalance) VALUES (" +"'" + acctnum + "'," + "'" + accname + "'," + "'" +
//														getname + "'," + "'" + amount + "'," + "'" + finalbalance + "');";
//														st1.executeUpdate(query);
												
	//											query = "update Signup set openingbalance = " + finalbalance + " where accountnumber = " + acctnum + "; ";
	//											st.executeUpdate(query);
												JOptionPane.showMessageDialog(null, "Withdraw Successful");
											}
											else {
												JOptionPane.showMessageDialog(null, "Insufficient funds");
											}
										}
										else {
											JOptionPane.showMessageDialog(null, "Account name or Account number does not exist or match");
										}
								}
								else {
									JOptionPane.showMessageDialog(null, "Incomplete Information!!!");
								}
							}
						} catch (Exception eeee) {
							System.err.println(eeee);
							JOptionPane.showMessageDialog(null, "Error: " + eeee.getMessage());
						}
					}
				});
					
			}
				public boolean checkmyanswer(String answer) {
					System.out.println("Testing");
					String myanswer = "^[^0-9@._%+-][a-zA-Z .-]*";
					System.out.println("WOw");
					boolean result = Pattern.matches(myanswer, answer);
					if (!result) {
						JOptionPane.showMessageDialog(null, "Withdrawers's name cannot include numbers...");
					}
					return result;
				}
				
				public boolean confirmamount(String amntdepo) {
					String amount = "\\d{1,}";
					boolean result = Pattern.matches(amount, amntdepo);
					if (!result) {
						System.out.println("Hmmmmm....");
						JOptionPane.showMessageDialog(null, "Invalid Amount Input");
						System.out.println("Okayyyy....");
					}
					return result;
				}
			});
			
			
			font = new Font("Inconsolata", Font.BOLD, 12);
			
			log_in = new JButton("Login");
			log_in.setFont(font);
			log_in.setBounds(210, 395, 70, 20);
			log_in.setFocusable(false);
			
			log_in.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					held = "";
					useraccnum = "";
					useraccbal = "";
					chnpass = "";
					try {
						String uname = loginname.getText(),
								pass = pinField.getText();
						
						query = "Login";
						
						if(!(uname.isBlank() || pass.isBlank() || uname.equals(Placeholder) || pass.equals(Placeholder1))) {
							
							if (confirmMymail(uname) && confirmMypass(pass)) {
								 
								query = "SELECT * FROM Signup WHERE Emailaddress = '" + uname + "' AND Pin = '" + pass + "'";
								
								ResultSet rs = st.executeQuery(query);
								
								if (rs.next() && rs.getString(6).equals(pass)) {
									held = rs.getString(1);
									useraccnum = rs.getString(4);
									useraccbal = rs.getString(5);
									chnpass = rs.getString(6);
									JOptionPane.showMessageDialog(null, "Login successful...");
									System.out.println("Hello...407");
									loginID = held;
									accID = useraccnum;
									acctbalID = useraccbal;
									chnpassID = chnpass;
									frame.dispose();
									Labs.homePage hold = new homePage();
								}
								else
									JOptionPane.showMessageDialog(null, "Incorrect Email or Pin");
							}
							else JOptionPane.showMessageDialog(null, "Login Failed");
						}
					}catch(Exception ae) {
						System.out.println("Error: " + ae.getMessage());
					}
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
			frame.add(depo);
			frame.add(witdraw);
			
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
		
		public JPasswordField createPassField() {
			JPasswordField PassField = new JPasswordField(25);
			PassField.setFont(font);
			return PassField;
		}
		
		public void reset() {
			thisTableModel.setRowCount(0);
		}
		
		public JDialog createDialog(String title) {
			JDialog mydialog = new  JDialog();
			mydialog.setTitle(title);
			mydialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			mydialog.setFont(font);
			return mydialog;
		}
		
		public boolean confirmMymail(String mail) {
			String confirm_email = "[^0-9@._%+-][a-zA-Z0-9]{5,}@[a-z]+(\\.[a-z]+)+$";
			boolean result = Pattern.matches(confirm_email, mail);
			if (!result) {
				System.out.println("Hmmmmm....");
				JOptionPane.showMessageDialog(null, "Email cannot begin with numbers or characters...");
				System.out.println("Okayyyy....");
			}
			return result;
		}
		
		public boolean confirmMypass(String mypass) {
			String confirm_pass = "\\d{4,}";
			boolean result = Pattern.matches(confirm_pass, mypass);
			if (!result) {
				System.out.println("Hmmmmm....");
				JOptionPane.showMessageDialog(null, "Invalid Pin!");
				System.out.println("Okayyyy....");
			}
			return result;
		}
		
		public boolean checkmyanswer(String answer) {
			System.out.println("Testing");
			String myanswer = "^[^0-9@._%+-][a-zA-Z .-]*";
			System.out.println("WOw");
			boolean result = Pattern.matches(myanswer, answer);
			if (!result) {
				JOptionPane.showMessageDialog(null, "Cannot accept numbers...");
			}
			return result;
		}
		
		public JTable createTable(DefaultTableModel ox) {
			JTable table = new JTable(ox);
			table.setPreferredScrollableViewportSize(new Dimension(360, 310));
			table.setFillsViewportHeight(true);
			table.setFont(new Font("Verdana", Font.PLAIN, 10));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.getModel();
			return table;
		}
	}
	
	
	
	
	static class homePage extends JFrame implements ActionListener {
		DefaultTableModel thisTableModel, model;
		JFrame frame, frame1;
		JDialog mydialog;
		Font font;
		Color blue;
		JMenuBar menu;
		JMenu services, recharge, loans, history;
		JMenuItem transfers, bills, witdraw, settings, airtime_hist, data_hist, draw_hist, depo_hist, bills_hist;
		JMenuItem credit, log_out, changepass;
		JMenuItem data, top_up;
		JMenuItem space, space1, space2, space3, space4, space5, space6;
		JLabel accountLabel, accountnumLabel, accountbalLabel, bank;
		JRadioButton thisbnk, otherbnk;
		JTextField ratefield, max_amt, amt, mnth_repay, total_repay;
		JFormattedTextField grant;
		JPasswordField enterpinfield;
		JButton my_trans;
		JPanel transactPanel;
		JTable thisTable;
		String query, max, maxim, rate, ackname, ackacc, mybal, mnth_result, total_result, convaloan;
		String held = loginID, balance, acceptAccnum = "", acceptAccnam = "", acceptAmount = "", useraccbal = "", myaccnum = "", useraccnum = "", repacc = "", billpaid = "";
		Object select, am, chk;
		double maxi_loan, convertbal, amountforint, percent, month_interest, monthlydue, totaldue;
		DecimalFormat df;
		Statement st, st1, st2;
		
		public homePage() {
			st = BankAppConnectionLoader.loadsql();
			st1 = BankAppConnectionLoader.loadsql();
			st2 = BankAppConnectionLoader.loadsql();
			
			frame = new JFrame();
			frame.setLayout(null);
		
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setTitle("HOUK Bank");
			frame.setSize(500, 600);
			frame.setResizable(false);
			frame.getContentPane();
			
			font = new Font("Inconsolata", Font.BOLD, 10);
			frame.setFont(font);
			
			menu = new JMenuBar();
			services = new JMenu("\u2630");
			recharge = new JMenu("Airtime and Data"); recharge.setFont(font);
			loans = new JMenu("Quick Loans"); loans.setFont(font);
			history = new JMenu("Transaction History"); history.setFont(font);
			settings = new JMenu("Settings"); settings.setFont(font);
			transfers = new JMenuItem("Transfers"); transfers.setFont(font);
			bills = new JMenuItem("Bills Payments"); bills.setFont(font);
			log_out = new JMenuItem("Log Out"); log_out.setFont(font);
			top_up = new JMenuItem("Mobile Top-Up"); top_up.setFont(font);
			data = new JMenuItem("Data Bundles"); data.setFont(font);
			credit = new JMenuItem("Quick Credits"); credit.setFont(font);
			changepass = new JMenuItem("Change Password"); changepass.setFont(font);
			draw_hist = new JMenuItem("Withdrawal History"); draw_hist.setFont(font);
			depo_hist = new JMenuItem("Deposit History"); depo_hist.setFont(font);
			airtime_hist = new JMenuItem("Transfer History"); airtime_hist.setFont(font);
			data_hist = new JMenuItem("Transfer History"); data_hist.setFont(font);
			bills_hist = new JMenuItem("Bills Payment History"); bills_hist.setFont(font);
			space = new JMenuItem(""); space1 = new JMenuItem(""); space2 = new JMenuItem(""); space3 = new JMenuItem(""); space4 = new JMenuItem(""); space5 = new JMenuItem("");
			space6 = new JMenuItem("");
			
			
			// LOCALIZATION
			Locale [] locales = Calendar.getAvailableLocales();
			
			Locale locale = new Locale("en", "NG");
					
			NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
			
			df = new DecimalFormat("#.00");
					
			// ADDING ACTIONLISTENERS
			
			bills.addActionListener(this); log_out.addActionListener(this); transfers.addActionListener(this);
			credit.addActionListener(this); settings.addActionListener(this); top_up.addActionListener(this);
			airtime_hist.addActionListener(this); data_hist.addActionListener(this); draw_hist.addActionListener(this);
			depo_hist.addActionListener(this); bills_hist.addActionListener(this); 
			
			recharge.add(top_up);
			recharge.add(data);
			
			loans.add(credit);
			
			history.add(draw_hist);
			history.add(depo_hist);
			history.add(airtime_hist);
			history.add(data_hist);
			history.add(bills_hist);
			
			settings.add(changepass);
			
			services.add(transfers);
			services.add(bills);
			services.add(recharge);
			services.add(loans);
			services.add(settings);
			services.add(history);
			services.add(space);
			services.add(space1);
			services.add(space2);
			services.add(space3);
			services.add(space4);
			services.add(space5);
			services.add(space6);
			services.add(log_out);
			
			menu.add(services);
	
			
			credit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JDialog dialog = createDialog("Loans and Credits");
					
					JPanel mypanel = new JPanel();
					mypanel.setLayout(null);
					JLabel lab = createLabel("Interest Rate: ");
					lab.setBounds(90, 160, 150, 20);
					JLabel lab_1 = createLabel("per Month");
					lab_1.setBounds(329, 160, 90, 20);
					JLabel lab2  = createLabel("Maximum Loan Amount: ");
					lab2.setBounds(90, 200, 150, 20);
					JLabel lab3 = createLabel("Enter Amount: ");
					lab3.setBounds(90, 240, 150, 20);
					JLabel lab4 = createLabel("Select Loan Tenor: ");
					lab4.setBounds(90, 280, 150, 20);
					JLabel lab5 = createLabel("Monthly Repayment: ");
					lab5.setBounds(90, 320, 150, 20);
					JLabel lab6 = createLabel("Total Repayment: ");
					lab6.setBounds(90, 360, 150, 20);
					JLabel enterpin = createLabel("Enter Pin: ");
					enterpin.setBounds(90, 400, 150, 20);
					
					maxi_loan = 500000;
					max = fmt.format(maxi_loan);
					
					ratefield = createTextField();
					ratefield.setBounds(235, 160, 90, 20);
					ratefield.setText("1.33%");
					ratefield.setEditable(false);
					max_amt = createTextField();
					max_amt.setBounds(235, 200, 90, 20);
					max_amt.setText(max);
					max_amt.setEditable(false);
					amt = createTextField();
					amt.setBounds(235, 240, 90, 20);
					mnth_repay = createTextField();
					mnth_repay.setBounds(235, 320, 90, 20);
					mnth_repay.setEditable(false);
					total_repay = createTextField();
					total_repay.setBounds(235, 360, 90, 20);
					total_repay.setEditable(false);
					enterpinfield = createPassField();
					enterpinfield.setBounds(235, 400, 90, 20);
					String [] myloan = {"3 Months", "6 Months", "9 Months", "1 Year", "2 Years"};
					JComboBox<String> payloan = new JComboBox<>(myloan);
					payloan.setBounds(235, 280, 90, 20);
					payloan.setFont(font);
								
					JButton buy = new JButton("Apply");
					buy.setFont(font);
					buy.setFocusable(false);
					buy.setBounds(200, 440, 70, 20);
					
					buy.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								chk = payloan.getSelectedItem().toString();
								String the_amnt = amt.getText();	
								String pin = enterpinfield.getText();
								String maxim = max_amt.getText();
								String rate = ratefield.getText();
								
								query = "Apply";
								
								query = "SELECT * FROM Signup WHERE FirstName = '" + loginID + "'";
								
//								st.execute(query);
//								System.out.println("Hello...4");
								JOptionPane.showMessageDialog(null, loginID);
								
								ResultSet rs = st.executeQuery(query);
								
								while (rs.next()) {
									ackname = rs.getString(1);
									ackacc = rs.getString(4);
									mybal = rs.getString(5);
									System.out.println("I'm getting here");
									convertbal = Double.parseDouble(mybal);
									amountforint = Double.parseDouble(the_amnt);
									JOptionPane.showMessageDialog(null, maxim);
									JOptionPane.showMessageDialog(null, rate);
									if (!(the_amnt.isBlank() || pin.isBlank())) {
										System.out.println("Dami");
										if (confirmMypass(pin) && confirmMyloan(the_amnt)) {
											
//											query = "CREATE TABLE IF NOT EXISTS Loans(LoanAmount bigint, MaxLoanAmount bigint, MonthlyRepayment bigint, TotalRepayment bigint)";
											
												if (rs.getString(6).equals(pin)) {
													JOptionPane.showMessageDialog(null, convaloan);
													JOptionPane.showMessageDialog(null, mnth_result);
													JOptionPane.showMessageDialog(null, total_result);
													JOptionPane.showMessageDialog(null, "Loan Acquisition Successful");
													JOptionPane.showMessageDialog(null, "Your Loan request for " + convaloan + " was successful. \nYou will be paying " + mnth_result + " monthly. \nTotal amount to be repayed, with interests is " + total_result);
													query = "INSERT INTO Loans(LoanAmount, MaxLoanAmount, MonthlyRepayment, TotalRepayment) VALUES (" +"'" + amountforint + "'," + "'" + maxi_loan + "'," + "'" +
															month_interest + "'," + "'" + totaldue + "');";
													st1.executeUpdate(query);
													
//													query = "update Signup set openingbalance = " + finalbalance + " where FirstName = " + loginID + "; ";
//													st.executeUpdate(query);
												}
												else if (!(rs.next() && rs.getString(4).equals(the_amnt))) {
													JOptionPane.showMessageDialog(null, "Invalid Pin");
												}
											}
										}
									}
								}
							catch (Exception crd) {
								JOptionPane.showMessageDialog(null, "Error " + crd.getMessage());
							}
						}
						
						public boolean confirmMypass(String mypass) {
							String confirm_pass = "\\d{4,}";
							boolean result = Pattern.matches(confirm_pass, mypass);
							if (!result) {
								System.out.println("Hmmmmm....");
								JOptionPane.showMessageDialog(null, "Invalid Pin!");
								System.out.println("Okayyyy....");
							}
							return result;
						}
						
						public boolean confirmMyloan(String loan) {
							String confirm_pass = "\\d{4,}";
//							String sks = fmt.format(confirm_pass);
							boolean result = Pattern.matches(confirm_pass, loan);
							if (!result) {
								System.out.println("Hmmmmm....");
								JOptionPane.showMessageDialog(null, "Invalid Money Input!");
								System.out.println("Okayyyy....");
							}
							return result;
						}
					});
					
					
					payloan.addFocusListener(new FocusListener() {
						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
							String month = "";
							String total = "";
							try {
								chk = payloan.getSelectedItem().toString();
								String prnt = amt.getText();
								
								if (!(prnt.isBlank())) {
								
									query = "SELECT * FROM Signup WHERE FirstName = '" + loginID + "'";
									
									ResultSet rs = st.executeQuery(query);
									
									if (rs.next()) {
										ackname = rs.getString(1);
										ackacc = rs.getString(4);
										mybal = rs.getString(5);
										System.out.println("I'm getting here");
										convertbal = Double.parseDouble(mybal);
										amountforint = Double.parseDouble(prnt);
										percent = 0.15 * amountforint;
										if (amountforint <= maxi_loan) {
											if (convertbal >= percent) {
												System.out.println("sksksksksk");
												if (chk.equals("3 Months")) {
													monthlydue = amountforint / 3;
													month_interest = (0.0133 * amountforint) + monthlydue;
													totaldue = month_interest * 3;
												}
												else if (chk.equals("6 Months")) {
													monthlydue = amountforint / 6;
													month_interest = (0.0133 * amountforint) + monthlydue;
													totaldue = month_interest * 6;
												}
												else if (chk.equals("9 Months")) {
													monthlydue = amountforint / 9;
													month_interest = (0.0133 * amountforint) + monthlydue;
													totaldue = month_interest * 9;
												}
												else if (chk.equals("1 Year")) {
													monthlydue = amountforint / 12;
													month_interest = (0.0133 * amountforint) + monthlydue;
													totaldue = month_interest * 12;
												}
												else if (chk.equals("2 Years")) {
													monthlydue = amountforint / 24;
													month_interest = (0.0133 * amountforint) + monthlydue;
													totaldue = month_interest * 24;
												}
												mnth_result = fmt.format(month_interest);
												total_result = fmt.format(totaldue);
												convaloan = fmt.format(amountforint);
												mnth_repay.setText(mnth_result);
												total_repay.setText(total_result);
											}
											else {
												JOptionPane.showMessageDialog(null, "Sorry, You are not eligible for this loan");
											}
										}
										else {
											JOptionPane.showMessageDialog(null, "Sorry, You can not borrow above " + max);
										}
									}
									else {
										JOptionPane.showMessageDialog(null, "Transaction Error");
									}
								}
							}
							catch (Exception ay) {
								System.out.println("Error: " + ay.getMessage());
							}
						}
						public void focusGained(FocusEvent e) {
							chk = payloan.getSelectedItem().toString();
						}
					});
					
					
					mypanel.add(lab);
					mypanel.add(lab_1);
					mypanel.add(lab2);
					mypanel.add(lab3);
					mypanel.add(lab4);
					mypanel.add(lab5);
					mypanel.add(lab6);
					mypanel.add(enterpin);
					mypanel.add(payloan);
					mypanel.add(ratefield);
					mypanel.add(max_amt);
					mypanel.add(amt);
					mypanel.add(mnth_repay);
					mypanel.add(total_repay);
					mypanel.add(enterpinfield);
					mypanel.add(buy);
					
					dialog.add(mypanel);
					
					dialog.setSize(500, 600);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				}
				
			});
			
			
			
			
	top_up.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JDialog dialog = createDialog("Airtime Top-up");
					
					JPanel mypanel = new JPanel();
					mypanel.setLayout(null);
					JLabel lab = createLabel("Enter Number: ");
					lab.setBounds(90, 250, 90, 20);
					JLabel lab2  = createLabel("Enter Amount: ");
					lab2.setBounds(90, 290, 90, 20);
					JLabel lab3 = createLabel("Enter Network:");
					lab3.setBounds(90, 330, 90, 20);
					JTextField number = createTextField();
					number.setBounds(190, 250, 150, 20);
					JTextField amt = createTextField();
					amt.setBounds(190, 290, 150, 20);
					String [] netwrk = {"Glo","Mtn","Airtel","9Mobile"};
					JComboBox<String> jcb = new JComboBox<>(netwrk);
					jcb.setBounds(190, 330, 150, 20);
					jcb.setFont(font);
					
					JButton buy = new JButton("Buy");
					buy.setFont(font);
					buy.setFocusable(false);
					buy.setBounds(200, 370, 70, 20);
					
					buy.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								String num = number.getText();
								String am = amt.getText();
								int len = num.length();
								int len1 = am.length();
								double ma = Double.parseDouble(am);
								
								select = jcb.getSelectedItem().toString();
								System.out.println(select.toString() +" is selected");
								
								query = "Buy";
								
								query = "SELECT * FROM Signup WHERE FirstName = '" + loginID + "'";
								
								ResultSet rs = st.executeQuery(query);
								
								while (rs.next() && rs.getString(1).equals(loginID)) {
									balance = rs.getString(5);
									System.out.println(balance);
									double amountforint =  Double.parseDouble(am);
									double balanceforint =  Double.parseDouble(balance);
									System.out.println(balanceforint);
									double finalbalance  = balanceforint - amountforint;
									System.out.println(finalbalance);
									
									if(balanceforint >= amountforint) {
										
										if (!(num.isBlank() && am.isBlank() && len1 <= 1)) {
											if (select.equals("Mtn")) {
												if (confirmMtn(num)) {
													System.out.println("Adeife");
													JOptionPane.showMessageDialog(null, "Your airtime of \u20A6"+am+" for "+num+" has been successfully recharged.\n"+"\u20A6"
															+ma+" has been deducted from your account.");
												}
											}
											else if (select.equals("Glo")) {
												if (confirmGlo(num)) {
													System.out.println("Adeife Glo");
													JOptionPane.showMessageDialog(null, "Your airtime of \u20A6"+am+" for "+num+" has been successfully recharged.\n"+"\u20A6"
															+ma+" has been deducted from your account.");
												}
											}
											else if (select.equals("Airtel")) {
												if (confirmAirtel(num)) {
													System.out.println("Adeife Airtel");
													JOptionPane.showMessageDialog(null, "Your airtime of \u20A6"+am+" for "+num+" has been successfully recharged.\n"+"\u20A6"
															+ma+" has been deducted from your account.");
												}
											}
											else {
												if(confirm9Mobile(num)) {
													System.out.println("Adeife Etisalat");
													JOptionPane.showMessageDialog(null, "Your airtime of \u20A6"+am+" for "+num+" has been successfully recharged.\n"+"\u20A6"
															+ma+" has been deducted from your account.");
												}
											}
											JOptionPane.showMessageDialog(null, "GET OUT!!");
											query = "INSERT INTO Mobile_TopUp(PhoneNumber, Network, Amount) VALUES (" +"'" + num + "'," + "'" + select + "'," + "'" +
													amountforint + "');";
											st1.executeUpdate(query);
											
//											query = "update Signup set openingbalance = " + finalbalance + " where FirstName = " + loginID + "; ";
//											st.executeUpdate(query);
										}
										else {
											JOptionPane.showMessageDialog(null, "Invalid input! \nPlease input correct details" ,"",JOptionPane.ERROR_MESSAGE);
											number.setText("");
											number.getText();
											amt.setText("");
											amt.getText();
											}
									}
									else {
										JOptionPane.showMessageDialog(null, "Insufficient funds");
									}
									
									
								}
							} catch(Exception ex) {
								System.out.println("Please Input a number!! " + ex.getMessage());
								JOptionPane.showMessageDialog(null, "Please Input a number " + ex.getMessage());
							}
						}
					});
					
					mypanel.add(lab);
					mypanel.add(lab2);
					mypanel.add(lab3);
					mypanel.add(number);
					mypanel.add(amt);
					mypanel.add(jcb);
					mypanel.add(buy);
					
					dialog.add(mypanel);
					
					dialog.setSize(500, 600);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
					

					
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
				}
				public boolean confirmMtn(String mtnNum) {
					String myMTN = "(0|\\+234)[7-9][0-1](0|3|4|6)[- ]?\\d{3}[- ]?\\d{4}";
//					String myMTN ="08096615546";
					boolean result = Pattern.matches(myMTN, mtnNum);
					if (!result) {
						System.out.println("Hmmmmm....");
						JOptionPane.showMessageDialog(null, "This is not a valid MTN number");
						System.out.println("Okayyyy....");
					}
					return result;
				}
					
				
				public boolean confirmGlo(String gloNum) {
					String myGLO = "(0|\\+234)[7-9][0-1](1|5|7)[- ]?\\d{3}[- ]?\\d{4}";
					boolean result = Pattern.matches(myGLO, gloNum);
					if (!result) {
						System.out.println("Hmmmmm....");
						JOptionPane.showMessageDialog(null, "This is not a valid GLO number");
						System.out.println("Okayyyy....");
					}
					return result;
				}
					
				
					
				public boolean confirmAirtel(String airtelNum) {
					String myAIRTEL = "(0|\\+234)[7-9][0-1](1|5|7)[- ]?\\d{3}[- ]?\\d{4}";
					boolean result = Pattern.matches(myAIRTEL, airtelNum);
					if (!result) {
						System.out.println("Hmmmmm....");
						JOptionPane.showMessageDialog(null, "This is not a valid Airtel number");
						System.out.println("Okayyyy....");
					}
					return result;
				}
					
					
					
				public boolean confirm9Mobile(String EtisNum) {
					String my9MOBILE = "(0|\\+234)[7-9][0-1](1|5|7)[- ]?\\d{3}[- ]?\\d{4}";
					boolean result = Pattern.matches(my9MOBILE, EtisNum);
					if (!result) {
						System.out.println("Hmmmmm....");
						JOptionPane.showMessageDialog(null, "This is not a valid 9Mobile number");
						System.out.println("Okayyyy....");
					}
					return result;
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
			lab.setBounds(100, 250, 120, 20);
			JLabel lab2  = createLabel("Enter Network: ");
			lab2.setBounds(100, 290, 120, 20);
			JLabel lab3 = createLabel("Select Data Amount: ");
			lab3.setBounds(100, 330, 120, 20);
			JTextField number = createTextField();
			number.setBounds(220, 250, 150, 20);
			String [] netwrk = {"Glo","Mtn","Airtel","9Mobile"};
			JComboBox<String> netwrk1 = new JComboBox<>(netwrk);
			netwrk1.setBounds(220, 290, 150, 20);
			netwrk1.setFont(font);
			String [] dt = {"1GB","2GB","3GB","4GB","5GB","6GB","7GB","10GB"};
			JComboBox<String> td = new JComboBox<>(dt);
			td.setFont(font);
			td.setBounds(220, 330, 50, 20);
			
			JButton buy = new JButton("Buy");
			buy.setFont(font);
			buy.setFocusable(false);
			buy.setBounds(210, 370, 70, 20);
			
			buy.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("IBADAN");
				try {
					String num = number.getText();
					chk = netwrk1.getSelectedItem().toString();
					am = td.getSelectedItem().toString();
					
						query = "Buy";
						
						query = "SELECT * FROM Signup WHERE FirstName = '" + loginID + "'";
						
						ResultSet rs = st.executeQuery(query);
						System.out.println("OKAYYYYYYYYYYY");
						
						String x = "0";
						if(am.equals(dt[0])) {
							x = "1000";
						}else if(am.equals(dt[1])) {
							x = "2000";
						}else if(am.equals(dt[2])) {
							x = "3000";
						}else if(am.equals(dt[3])) {
							x = "3500";
						}else if(am.equals(dt[4])) {
							x = "5000";
						}else if(am.equals(dt[5])) {
							x = "6000";
						}else if(am.equals(dt[6])) {
							x = "6500";
						}else if(am.equals(dt[7])) {
							x = "9000";
						}
						
						while (rs.next() && rs.getString(1).equals(loginID)) {
							balance = rs.getString(5);
							System.out.println(balance);
							double amountforint = Double.parseDouble(x);
							double balanceforint =  Double.parseDouble(balance);
							System.out.println(balanceforint);
							double finalbalance  = balanceforint - amountforint;
							System.out.println(finalbalance);
							
							
							if (balanceforint >= amountforint) {
								
								if (!(num.isBlank())) {
									if (chk.equals("Mtn")) {
										if (confirmMtn(num)) {
											System.out.println("Adeife");
											JOptionPane.showMessageDialog(null, "Your Data subscription of "+ am + " for " + chk + " number " + "(" + num + ")" + " was successful."
													+"\n"+ "\u20A6" + x + " has been debited from your account.");
										}
									}
									else if (chk.equals("Glo")) {
										if (confirmGlo(num)) {
											System.out.println("Adeife Glo");
											JOptionPane.showMessageDialog(null, "Your Data subscription of "+ am + " for " + chk + " number " + "(" + num + ")" + " was successful."
													+"\n"+ "\u20A6" + x + " has been debited from your account.");
										}
									}
									else if (chk.equals("Airtel")) {
										if (confirmAirtel(num)) {
											System.out.println("Adeife Airtel");
											JOptionPane.showMessageDialog(null, "Your Data subscription of "+ am + " for " + chk + " number " + "(" + num + ")" + " was successful."
													+"\n"+ "\u20A6" + x + " has been debited from your account.");
										}
									}
									else {
										if(confirm9Mobile(num)) {
											System.out.println("Adeife Etisalat");
											JOptionPane.showMessageDialog(null, "Your Data subscription of "+ am + " for " + chk + " number " + "(" + num + ")" + " was successful."
													+"\n"+ "\u20A6" + x + " has been debited from your account.");
										}
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "Invalid input! \nPlease input correct details" ,"",JOptionPane.ERROR_MESSAGE);
									number.setText("");
									number.getText();
									}
								query = "INSERT INTO Data_Subscription(PhoneNumber, Network, DataAmount, Amount) VALUES (" +"'" + num + "'," + "'" + chk + "'," + "'" +
										am + "'," + "'" + x + "');";
								st1.executeUpdate(query);
								
//								query = "UPDATE Signup SET openingbalance = " + finalbalance + " WHERE FirstName = " + loginID + "; ";
//								st.executeUpdate(query);
							}
							else {
								JOptionPane.showMessageDialog(null, "Insufficient funds");
							}
							
							
						}
					}
				
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
				
				public boolean confirmMtn(String mtnNum) {
					String myMTN = "(0|\\+234)[7-9][0-1](0|3|4|6)[- ]?\\d{3}[- ]?\\d{4}";
					boolean result = Pattern.matches(myMTN, mtnNum);
					if (!result) {
						System.out.println("Hmmmmm....");
						JOptionPane.showMessageDialog(null, "This is not a valid MTN number");
						System.out.println("Okayyyy....");
					}
					return result;
				}
					
				
				public boolean confirmGlo(String gloNum) {
					String myGLO = "(0|\\+234)[7-9][0-1](1|5|7)[- ]?\\d{3}[- ]?\\d{4}";
					boolean result = Pattern.matches(myGLO, gloNum);
					if (!result) {
						System.out.println("Hmmmmm....");
						JOptionPane.showMessageDialog(null, "This is not a valid MTN number");
						System.out.println("Okayyyy....");
					}
					return result;
				}
					
				
					
				public boolean confirmAirtel(String airtelNum) {
					String myAIRTEL = "(0|\\+234)[7-9][0-1](1|5|7)[- ]?\\d{3}[- ]?\\d{4}";
					boolean result = Pattern.matches(myAIRTEL, airtelNum);
					if (!result) {
						System.out.println("Hmmmmm....");
						JOptionPane.showMessageDialog(null, "This is not a valid MTN number");
						System.out.println("Okayyyy....");
					}
					return result;
				}
					
					
					
				public boolean confirm9Mobile(String EtisNum) {
					String my9MOBILE = "(0|\\+234)[7-9][0-1](1|5|7)[- ]?\\d{3}[- ]?\\d{4}";
					boolean result = Pattern.matches(my9MOBILE, EtisNum);
					if (!result) {
						System.out.println("Hmmmmm....");
						JOptionPane.showMessageDialog(null, "This is not a valid MTN number");
						System.out.println("Okayyyy....");
					}
					return result;
				}
				
			});
			
			mypanel.add(lab);
			mypanel.add(number);
			mypanel.add(lab3);
			mypanel.add(td);
			mypanel.add(lab2);
			mypanel.add(netwrk1);
			mypanel.add(buy);
			
			dialog.add(mypanel);
			
			dialog.setSize(500, 600);
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
		}
	});
		
	
	transfers.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new Transfer();
			}
	});
	
	
	
	bills.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JDialog dialog = createDialog("Bills Payments");
			
			JPanel mypanel = new JPanel();
			mypanel.setLayout(null);
			
			
			JLabel lab = createLabel("Paying For? ");
			lab.setBounds(90, 230, 120, 20);
			String [] mybills = {"Electric Bill","School Payments","Cable(GOTV/DSTV) Subscription","Airline Ticket Payment", "Utility", "Taxes & Levies"};
			JComboBox<String> paybill = new JComboBox<>(mybills);
			paybill.setBounds(205, 230, 185, 20);
			paybill.setFont(font);
//			paybill.setFocusable(false);
			JLabel lab2  = createLabel("Account Number: ");
			lab2.setBounds(90, 270, 120, 20);
			JTextField number = createTextField();
			number.setBounds(205, 270, 150, 20);
			JLabel lab3 = createLabel("Account Name: ");
			lab3.setBounds(90, 310, 120, 20);
			JTextField name = createTextField();
			name.setBounds(205, 310, 150, 20);
			JLabel lab4 = createLabel("Amount: ");
			lab4.setBounds(90, 350, 120, 20);
			JTextField amnt = createTextField();
			amnt.setBounds(205, 350, 150, 20);
			JLabel enterpin = createLabel("Enter Pin: ");
			enterpin.setBounds(90, 390, 120, 20);
			enterpinfield = createPassField();
			enterpinfield.setBounds(205, 390, 150, 20);
						
			JButton buy = new JButton("Pay");
			buy.setFont(font);
			buy.setFocusable(false);
			buy.setBounds(195, 430, 70, 20);
			
			buy.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						chk = paybill.getSelectedItem().toString();
						String acnum = number.getText();
						String acname = name.getText();
						String the_amnt = amnt.getText();
						String pin = enterpinfield.getText();
						
						query = "Pay";
						
						query = "SELECT * FROM Signup WHERE FirstName = '" + loginID + "'";
						
//						st.execute(query);
//						System.out.println("Hello...4");
						JOptionPane.showMessageDialog(null, loginID);
						
						ResultSet rs = st.executeQuery(query);
						
						while (rs.next()) {
							if (!(acnum.isBlank() || acname.isBlank() || the_amnt.isBlank() || pin.isBlank())) {

								if (checkmyanswer(acname) && confirmMyaccnum(acnum) && confirmMypass(pin) && confirmamount(the_amnt)) {
									
//									query = "CREATE TABLE IF NOT EXISTS Bills_Payments(Service varchar(50), AccountNumber bigint, AccountName varchar(30), Amount decimal(14, 2)";
																
									if (!(rs.getString(4).equals(acnum))) {
									
										if (rs.getString(6).equals(pin)) {
											useraccbal = rs.getString(5);
											myaccnum = rs.getString(4);
											useraccnum = acnum;
											repacc = acname;
											accID = myaccnum;
											acctbalID = useraccbal;
											repaccID = repacc;
											
											double amounttransfered = Double.parseDouble(the_amnt);
											double mycurrentbalance = Double.parseDouble(useraccbal);
			
											double mynewbalance = mycurrentbalance - amounttransfered;
											String mymoney = fmt.format(mynewbalance);
											if (amounttransfered <= mycurrentbalance) {
												query = "INSERT INTO Bills_Payments(Service, AccountNumber, AccountName, Amount, HostAccountNumber) VALUES (" +"'" + chk + "'," + "'" + useraccnum + "'," + "'" +
														repacc + "'," + "'" + amounttransfered + "'," + "'" + accID + "');";
												st2.executeUpdate(query);
			//									
			//									query = "Update Signup set openingBalance = '" + mynewbalance +"' where AccountNumber = '"+ myaccnum +"'; ";
			//									st2.executeUpdate(query);
												
			//									query = "truncate table Bills_Payments;";
												
												JOptionPane.showMessageDialog(null, "Payment Successful");
												int ok = JOptionPane.showConfirmDialog(null, "Do you want to complete another Payment");
												if(ok == 0) {
													System.out.println("yes");
													number.setText("");
													number.getText();
													name.setText("");
													name.getText();
													amnt.setText("");
													amnt.getText();
													enterpinfield.setText("");
													enterpinfield.getText();
												}else if(ok == 1) {
													System.out.println("no");
													dialog.dispose();
												}else if(ok == 2) {
													System.out.println("cancel");
													System.exit(-1);
												}
											}
											else {
												JOptionPane.showMessageDialog(null, "Insufficient Funds");
												amnt.setText("");
											}
										}
									}
									else JOptionPane.showMessageDialog(null, "Failed");
									number.setText("");
									number.setEditable(true);
									number.setEnabled(true);
								}
							}
						}
					}
					catch (Exception exp) {
						JOptionPane.showMessageDialog(null, "Error! " + exp.getMessage());
					}
				}
			});
			
			mypanel.add(lab);
			mypanel.add(paybill);
			mypanel.add(lab2);
			mypanel.add(number);
			mypanel.add(lab3);
			mypanel.add(name);
			mypanel.add(lab4);
			mypanel.add(amnt);
			mypanel.add(buy);
			mypanel.add(enterpin);
			mypanel.add(enterpinfield);
			
			dialog.add(mypanel);
			
			dialog.setSize(500, 600);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		}
		public boolean checkmyanswer(String answer) {
			System.out.println("Testing");
			String myanswer = "^[^0-9@._%+-][a-zA-Z .-]*";
			System.out.println("WOw");
			boolean result = Pattern.matches(myanswer, answer);
			if (!result) {
				JOptionPane.showMessageDialog(null, "Cannot accept numbers...");
			}
			return result;
		}

		public boolean confirmMypass(String mypass) {
			String confirm_pass = "\\d{4,}";
			boolean result = Pattern.matches(confirm_pass, mypass);
			if (!result) {
				System.out.println("Hmmmmm....");
				JOptionPane.showMessageDialog(null, "Invalid Pin!");
				System.out.println("Okayyyy....");
			}
			return result;
		}
		
		public boolean confirmMyaccnum(String accnum) {
			String confirm_accnum = "\\d{10}";
			boolean result = Pattern.matches(confirm_accnum, accnum);
			if (!result) {
				System.out.println("Hmmmmm....");
				JOptionPane.showMessageDialog(null, "Invalid Account Number!");
				System.out.println("Okayyyy....");
			}
			return result;
		}
		
		public boolean confirmamount(String amntdepo) {
			String amount = "\\d{1,}";
			boolean result = Pattern.matches(amount, amntdepo);
			if (!result) {
				System.out.println("Hmmmmm....");
				JOptionPane.showMessageDialog(null, "Invalid Amount Input");
				System.out.println("Okayyyy....");
			}
			return result;
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
			lab.setBounds(90, 250, 120, 20);
			JLabel lab2 = createLabel("Enter New password: ");
			lab2.setBounds(90, 290, 120, 20);
			JLabel lab3 = createLabel("Confirm New password: ");
			lab3.setBounds(90, 330, 160, 20);

			JButton chn = new JButton("Change");
			chn.setFont(font);
			chn.setFocusable(false);
			chn.setBounds(195, 370, 100, 20);

			JTextField oldpass = createTextField();
			oldpass.setText(chnpassID);
			oldpass.setEditable(false);
			oldpass.setBounds(240, 250, 150, 20);
			JTextField newpass = createTextField();
			newpass.setBounds(240, 290, 150, 20);
			JTextField confirmnewpass = createTextField();
			confirmnewpass.setBounds(240, 330, 150, 20);

			mypanel.add(lab);
			mypanel.add(lab2);
			mypanel.add(lab3);
			mypanel.add(oldpass);
			mypanel.add(newpass);
			mypanel.add(confirmnewpass);
			
			mypanel.add(chn);

			dialog.add(mypanel);
			dialog.setSize(500, 600);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

			oldpass.addKeyListener(new KeyAdapter() {
				@SuppressWarnings({ "static-access" })
				public void keyPressed(KeyEvent ke) {
					String num = oldpass.getText();
					char c = ke.getKeyChar();
					if (c >= '0' && c <= '9' || c == ke.VK_BACK_SPACE) {
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Input\nEnter a valid number!", "",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			newpass.addKeyListener(new KeyAdapter() {
				@SuppressWarnings({ "static-access" })
				public void keyPressed(KeyEvent ke) {
					String num = newpass.getText();
					char c = ke.getKeyChar();
					if (c >= '0' && c <= '9' || c == ke.VK_BACK_SPACE) {
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Input\nEnter a valid number!", "",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			});

			chn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						String opass = oldpass.getText(), npass = newpass.getText(), confnpass = confirmnewpass.getText();
						if (!(npass.isBlank())) {
							if (npass.equals(confirmnewpass)) {
								query = "update  signup set Pin = '" + npass + "' where pin = '" + opass + "';";
								st.executeUpdate(query);
								System.out.println("Hello...5");
	
								JOptionPane.showMessageDialog(null, "Pin Changed Successfully");
								oldpass.setText(npass);
								newpass.setText("");
							}
							else {
								JOptionPane.showMessageDialog(null, "Pin Mismatch");
							}

						} else {
							JOptionPane.showMessageDialog(null, "Enter new password");
							oldpass.setText("");
							newpass.setText("");
						}
					} catch (Exception ee) {
						System.err.println("Error: " + ee.getMessage());
					}
				}
			});

		}
	});
	
	
	
	draw_hist.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			thisTableModel = new DefaultTableModel();
			thisTable = createTable(thisTableModel);
			thisTable.setFillsViewportHeight(true);
			JScrollPane scrollpane = new JScrollPane(thisTable);  
		    scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    JTableHeader tableHeader = thisTable.getTableHeader();
		    tableHeader.setFont(font);
		    thisTable.setFont(font);
		    thisTable.setEnabled(false);
			thisTableModel.addColumn("Account No");
			thisTableModel.addColumn("Name");
			thisTableModel.addColumn("Withdrawer's Name");
			thisTableModel.addColumn("Amount");
			thisTableModel.addColumn("Balance");
			scrollpane.setViewportView(thisTable);
			thisTable.getModel();
			
			try {
				System.out.println("GALAXY");
				ResultSet rt = st2.executeQuery("SELECT AccountNumber, AccountName, WithdrawersName, Amount, AccountBalance FROM Withdrawal WHERE HostAccountNumber = '" + accID + "';");
				System.out.println("GEGE");
				System.out.println(rt.next());
				
//				reset();
				thisTableModel.setRowCount(0);
				while (rt.next()) {
					String num = rt.getString(1);
					String name = rt.getString(2);
					String witdrawnam = rt.getString(3);
					double amount = rt.getDouble(4);
					double balance = rt.getDouble(5);
					
					String amont = fmt.format(amount);
					String mybalance = fmt.format(balance);
					
					thisTableModel.addRow(new Object[] {num, name, witdrawnam, amont, mybalance});
				
					System.out.println(num);
					System.out.println(name);
					System.out.println(witdrawnam);
					System.out.println(amount);
					System.out.println(balance);
					System.out.println("");
				}
				
			}
			catch (SQLException exp) {
				JOptionPane.showMessageDialog(null, "Error " + exp.getMessage());
			}
		}
		
	});
	
	
	
	depo_hist.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			thisTableModel = new DefaultTableModel();
			thisTable = createTable(thisTableModel);
			thisTable.setFillsViewportHeight(true);
			JScrollPane scrollpane = new JScrollPane(thisTable);  
		    scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    JTableHeader tableHeader = thisTable.getTableHeader();
		    tableHeader.setFont(font);
		    thisTable.setFont(font);
		    thisTable.setEnabled(false);
			thisTableModel.addColumn("Account No");
			thisTableModel.addColumn("Name");
			thisTableModel.addColumn("Amount");
			thisTableModel.addColumn("Bank Name");
			thisTableModel.addColumn("Balance");
			scrollpane.setViewportView(thisTable);
			thisTable.getModel();
			
			try {
				System.out.println("GALAXY");
				ResultSet rt = st2.executeQuery("SELECT AccountNumber, AccountName, DepositorssName, Amount, AccountBalance FROM Transfer WHERE HostAccountNumber = '" + accID + "';");
				System.out.println("GEGE");
				System.out.println(rt.next());
				
//				reset();
				thisTableModel.setRowCount(0);
				while (rt.next()) {
					String num = rt.getString(1);
					String name = rt.getString(2);
					String depnam = rt.getString(3);
					double amount = rt.getDouble(4);
					double balance = rt.getDouble(5);
					
					String amont = fmt.format(amount);
					String mybalance = fmt.format(balance);
					
					thisTableModel.addRow(new Object[] {num, name, depnam, amont, mybalance});
				
					System.out.println(num);
					System.out.println(name);
					System.out.println(depnam);
					System.out.println(amount);
					System.out.println(balance);
					System.out.println("");
				}
				
			}
			catch (SQLException exp) {
				JOptionPane.showMessageDialog(null, "Error " + exp.getMessage());
			}
		}
		
	});
	
	
	
	airtime_hist.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			thisTableModel = new DefaultTableModel();
			thisTable = createTable(thisTableModel);
			thisTable.setFillsViewportHeight(true);
			JScrollPane scrollpane = new JScrollPane(thisTable);  
		    scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    JTableHeader tableHeader = thisTable.getTableHeader();
		    tableHeader.setFont(font);
		    thisTable.setFont(font);
		    thisTable.setEnabled(false);
			thisTableModel.addColumn("Account No");
			thisTableModel.addColumn("Name");
			thisTableModel.addColumn("Amount");
			thisTableModel.addColumn("Bank Name");
			thisTableModel.addColumn("Balance");
			scrollpane.setViewportView(thisTable);
			thisTable.getModel();
			
			try {
				System.out.println("GALAXY");
				ResultSet rt = st2.executeQuery("SELECT PhoneNumber, Network, Amount FROM Mobile_TopUp WHERE HostAccountNumber = '" + accID + "';");
				System.out.println("GEGE");
				System.out.println(rt.next());
				
//				reset();
				thisTableModel.setRowCount(0);
				while (rt.next()) {
					String num = rt.getString(1);
					String netwrk = rt.getString(2);
					double amount = rt.getDouble(3);
					
					String amont = fmt.format(amount);
					
					thisTableModel.addRow(new Object[] {num, netwrk, amont});
				
					System.out.println(num);
					System.out.println(netwrk);
					System.out.println(amont);
					System.out.println("");
				}
				
			}
			catch (SQLException exp) {
				JOptionPane.showMessageDialog(null, "Error " + exp.getMessage());
			}
		}
		
	});
	
	
	
	data_hist.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			thisTableModel = new DefaultTableModel();
			thisTable = createTable(thisTableModel);
			thisTable.setFillsViewportHeight(true);
			JScrollPane scrollpane = new JScrollPane(thisTable);  
		    scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    JTableHeader tableHeader = thisTable.getTableHeader();
		    tableHeader.setFont(font);
		    thisTable.setFont(font);
		    thisTable.setEnabled(false);
			thisTableModel.addColumn("Account No");
			thisTableModel.addColumn("Name");
			thisTableModel.addColumn("Amount");
			thisTableModel.addColumn("Bank Name");
			thisTableModel.addColumn("Balance");
			scrollpane.setViewportView(thisTable);
			thisTable.getModel();
			
			try {
				System.out.println("GALAXY");
				ResultSet rt = st2.executeQuery("SELECT PhoneNumber, Network, DataAmount, Amount FROM Data_Subscription WHERE HostAccountNumber = '" + accID + "';");
				System.out.println("GEGE");
				System.out.println(rt.next());
				
//				reset();
				thisTableModel.setRowCount(0);
				while (rt.next()) {
					String num = rt.getString(1);
					String netwrk = rt.getString(2);
					String datamont = rt.getString(3);
					double amount = rt.getDouble(4);
					
					String amont = fmt.format(amount);
					
					thisTableModel.addRow(new Object[] {num, netwrk, datamont, amont});
				
					System.out.println(num);
					System.out.println(netwrk);
					System.out.println(datamont);
					System.out.println(amont);
					System.out.println("");
				}
				
			}
			catch (SQLException exp) {
				JOptionPane.showMessageDialog(null, "Error " + exp.getMessage());
			}
		}
		
	});
	
	
	
	bills_hist.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			thisTableModel = new DefaultTableModel();
			thisTable = createTable(thisTableModel);
			thisTable.setFillsViewportHeight(true);
			JScrollPane scrollpane = new JScrollPane(thisTable);  
		    scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    JTableHeader tableHeader = thisTable.getTableHeader();
		    tableHeader.setFont(font);
		    thisTable.setFont(font);
		    thisTable.setEnabled(false);
			thisTableModel.addColumn("Account No");
			thisTableModel.addColumn("Name");
			thisTableModel.addColumn("Amount");
			thisTableModel.addColumn("Bank Name");
			thisTableModel.addColumn("Balance");
			scrollpane.setViewportView(thisTable);
			thisTable.getModel();
			
			try {
				System.out.println("GALAXY.......");
				ResultSet rt = st2.executeQuery("SELECT Service, AccountNumber, AccountName, Amount FROM Bills_Payments WHERE HostAccountNumber = '" + accID + "';");
				System.out.println("GEGE");
//				System.out.println(rt.next());
				
//				reset();
//				thisTableModel.setRowCount(0);
				while (rt.next()) {
					String serve = rt.getString(1);
					String num = rt.getString(2);
					String name = rt.getString(3);
					double amount = rt.getDouble(4);
					
					String amont = fmt.format(amount);
					
					thisTableModel.addRow(new Object[] {serve, num, name, amont});
				
					System.out.println(serve);
					System.out.println(num);
					System.out.println(name);
					System.out.println(amount);
					System.out.println("");
				}
				
			}
			catch (SQLException exp) {
				JOptionPane.showMessageDialog(null, "Error " + exp.getMessage());
			}
		}
		
	});
	
	
	
	log_out.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			frame.dispose();
			new Login();
		}
	});
	
	
	font = new Font("Verdana", Font.PLAIN, 10);
	
	// CREATING THE TRANSACTION TABLE
	thisTableModel = new DefaultTableModel();
	thisTable = createTable(thisTableModel);
	thisTable.setFillsViewportHeight(true);
	JScrollPane scrollpane = new JScrollPane(thisTable);  
    scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JTableHeader tableHeader = thisTable.getTableHeader();
    tableHeader.setFont(font);
    thisTable.setFont(font);
//    thisTable.setEnabled(false);
	thisTableModel.addColumn("Account No");
	thisTableModel.addColumn("Name");
	thisTableModel.addColumn("Amount");
	thisTableModel.addColumn("Bank Name");
	scrollpane.setViewportView(thisTable);
	thisTable.getModel();
	thisTableModel.setRowCount(0);
	
	try {
		System.out.println("GALAXY");
		ResultSet rt = st2.executeQuery("SELECT AccountNumber, AccountName, Amount, BankName FROM Transfer WHERE HostAccountNumber = '" + accID + "';");
		System.out.println("GEGE");
		
//		reset();
		
		while (rt.next()) {
			String num = rt.getString(1);
			String name = rt.getString(2);
			double amount = rt.getDouble(3);
			String accname = rt.getString(4);
			
			String amont = fmt.format(amount);
			
			thisTableModel.addRow(new Object[] {num, name, amont, accname});
		
			System.out.println("");
			System.out.println(num);
			System.out.println(name);
			System.out.println(amount);
			System.out.println(accname);
			System.out.println(balance);
			System.out.println("row complete");
			System.out.println("");
		}
	}
	catch (SQLException exp) {
		JOptionPane.showMessageDialog(null, "Error " + exp.getMessage());
	}
	
			
			
			
			// INITIALIZING PANELS
			transactPanel = new JPanel();
			transactPanel.setBounds(0, 80, 480, 500);
			transactPanel.add(scrollpane);
			
			font = new Font("Lato", Font.PLAIN, 13);
			
			// LABELS
			accountLabel = new JLabel();
			accountLabel.setText(loginID);
			accountLabel.setFont(font);
			accountLabel.setBounds(20, 10, 80, 40);
			
			accountnumLabel = new JLabel();
			accountnumLabel.setText(accID);
			accountnumLabel.setFont(font);
			accountnumLabel.setBounds(20, 30, 80, 40);
			
			font = new Font("Lato", Font.BOLD, 12);
			
			accountbalLabel = new JLabel();
			accountbalLabel.setText(acctbalID);
			accountbalLabel.setFont(font);
			accountbalLabel.setBounds(350, 20, 120, 40);
			
			font = new Font("Verdana", Font.PLAIN, 10);
			
			
			
			//ADDING LABELS TO FRAME
			frame.add(accountLabel);
			frame.add(accountnumLabel);
			frame.add(accountbalLabel);
			frame.add(transactPanel);
			
			frame.add(menu);
			frame.setJMenuBar(menu);	
			
//			frame.pack();
			frame.validate();
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
		
		public JPasswordField createPassField() {
			JPasswordField PassField = new JPasswordField(25);
			PassField.setFont(font);
			return PassField;
		}
		
		public JDialog createDialog(String title) {
			JDialog mydialog = new  JDialog();
			mydialog.setTitle(title);
			mydialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			mydialog.setModalityType(ModalityType.APPLICATION_MODAL);
			mydialog.setFont(font);
			return mydialog;
		}
		
		
		public JTable createTable(DefaultTableModel ox) {
			JTable table = new JTable(ox);
			table.setPreferredScrollableViewportSize(new Dimension(460, 560));
			table.setFillsViewportHeight(true);
			table.setFont(new Font("Verdana", Font.PLAIN, 9));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.getModel();
			return table;
		}
		
		public void reset() {
			thisTableModel.setRowCount(0);
		}
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
//			new homePage();
			Labs.Login hold = new Login();
//			Labs.homePage hold = new homePage();

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	
	
	class Transfer extends JDialog implements ActionListener{
		
		JFrame frame, frame1;
		JDialog mydialog;
		Font font;
		Color blue;
		JMenuBar menu;
		JLabel accountLabel, accountnumLabel, accountbalLabel, bank;
		JRadioButton thisbnk, otherbnk;
		JTextField entacc, repname, amountfield, entbnknam;
		JPasswordField enterpinfield;
		JButton my_trans;
		JPanel transactPanel;
		JTable thisTable;
		String query;
		String held = loginID, repacc = "", myaccnum = "", useraccnum = "", mybal = "", recipaccbal = "";
		String recipmoney, mymoney, acname, acnum, amnt, pin, accpin, banknam;
		Double amounttransfered, recipcurrentbalance, mycurrentbalance, recipnewbalance, mynewbalance;
		Statement st, st1, st2;
		
		
		public Transfer() {
		st = BankAppConnectionLoader.loadsql();
		st1 = BankAppConnectionLoader.loadsql();
		st2 = BankAppConnectionLoader.loadsql();
		
		String username = "root", password = "KiLLingMyDemons#1";
		String url = "jdbc:mysql://localhost:3306/Banking_Application";
		
		frame = new JFrame();
		
		font = new Font("Verdana", Font.PLAIN, 10);
		frame.setFont(font);
		
				
		// LOCALIZATION
		Locale [] locales = Calendar.getAvailableLocales();
		
		Locale locale = new Locale("en", "NG");
				
		NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
			
		JDialog transf = createDialog("Transfers");
		
		
		JPanel mypanel = new JPanel();
		mypanel.setLayout(null);
		
		JLabel accnt  = createLabel("Account Number");
		accnt.setBounds(90, 190, 120, 20);
		JLabel name = createLabel("Account Name: ");
		name.setBounds(90, 230, 120, 20);
		JLabel amount = createLabel("Amount: ");
		amount.setBounds(90, 270, 120, 20);
		JLabel bnknam = createLabel("Bank Name: ");
		bnknam.setBounds(90, 350, 120, 20);
		JLabel enterpin = createLabel("Enter Pin: ");
		enterpin.setBounds(90, 390, 120, 20);
		
		entacc = createTextField();
		entacc.setBounds(220, 190, 150, 20);
		entacc.setEditable(true);
		repname = createTextField();
		repname.setBounds(220, 230, 150, 20);
		repname.setEditable(false);
		amountfield = createTextField();
		amountfield.setBounds(220, 270, 150, 20);
		entbnknam = createTextField();
		entbnknam.setBounds(220, 350, 150, 20);
		entbnknam.setEditable(false);
		enterpinfield = createPassField();
		enterpinfield.setBounds(220, 390, 150, 20);
		
		my_trans = new JButton("Transfer");
		my_trans.setFont(font);
		my_trans.setFocusable(false);
		my_trans.setBounds(190, 430, 90, 20);
		my_trans.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pin = enterpinfield.getText();
				try {
					acname = repname.getText();
					System.out.println(acname);
					acnum = entacc.getText();
					System.out.println(acnum);
					amnt = amountfield.getText();
					System.out.println(amnt);
					pin = enterpinfield.getText();
					System.out.println(pin);
					banknam = "";
					if (otherbnk.isSelected()) {
						banknam = entbnknam.getText();
						System.out.println(banknam);
					}
					else if (thisbnk.isSelected()) {
						banknam = "Houk Bank";
						System.out.println(banknam);
					}
					
					query = "Transfer";
					
					query = "SELECT * FROM Signup WHERE AccountNumber = '" + accID + "' AND Pin = '" + pin + "'";
					
//					st.execute(query);
//					System.out.println("Hello...4");
					JOptionPane.showMessageDialog(null, loginID);
					
					ResultSet rs = st.executeQuery(query);
					
					query = "SELECT * FROM Signup WHERE AccountNumber = '" + accID + "' AND Pin = '" + pin + "'";
					
					ResultSet rat = st2.executeQuery(query);
					
					query = "SELECT * FROM Signup WHERE AccountNumber = '" + acnum + "'";
					
					ResultSet rst = st1.executeQuery(query);
					
						if (!(acname.isBlank() || acnum.isBlank() || amnt.isBlank() || pin.isBlank() || banknam.isBlank())) {
							
							if (checkmyanswer(acname) && checkmyanswer(banknam) && confirmMypass(pin)) {
								
//								query = "CREATE TABLE IF NOT EXISTS Transfer(AccountNumber INT, AccountName varchar(20), Amount bigint, BankName varchar(30), AccountBalance bigint, HostAccountNumber bigint)";
								
								while (rs.next()) {
									accpin = rs.getString(6);
									mybal = rs.getString(5);
									myaccnum = rs.getString(4);
									System.out.println(accpin);
									JOptionPane.showMessageDialog(null, accpin);
									JOptionPane.showMessageDialog(null, mybal);
									JOptionPane.showMessageDialog(null, myaccnum);
									if (rst.next()) {
										acnum = rst.getString(4);
										acname = rst.getString(1);
										recipaccbal = rst.getString(5);
										JOptionPane.showMessageDialog(null, acnum);
										repacc = acname;
										System.out.println(repacc);
										JOptionPane.showMessageDialog(null, repacc);
										acctbalID = mybal;
										System.out.println(acctbalID);
										recipacctbalID = recipaccbal;
										System.out.println(recipacctbalID);
										repaccID = repacc;
										System.out.println(repaccID);
										System.out.println(recipaccbal);
										System.out.println("ADEIFEOLUWA");
											if (rs.getString(6).equals(pin)) {
												System.out.println("ARGENTINA LOST");
												double amounttransfered = Double.parseDouble(amnt);
												double recipcurrentbalance = Double.parseDouble(recipaccbal);
												double mycurrentbalance = Double.parseDouble(mybal);
												double recipnewbalance = amounttransfered + recipcurrentbalance;
												double mynewbalance = mycurrentbalance - amounttransfered;
												String recipmoney = fmt.format(recipnewbalance);
												String mymoney = fmt.format(mynewbalance);
												
												if (amounttransfered <= mycurrentbalance) {
													query = "INSERT INTO Transfer(AccountNumber, AccountName, Amount, BankName, AccountBalance, HostAccountNumber) VALUES (" +"'" + acnum + "'," + "'" + acname + "'," + "'" +
															amounttransfered + "'," + "'" + banknam + "'," + "'" + mynewbalance + "'," + "'" + accID + "');";
													st1.executeUpdate(query);
													
													
													
//													query = "INSERT INTO Transfer(AccountNumber, AccountName, Amount, BankName, AccountBalance, HostAccountNumber) VALUES (" +"'" + "3456789321" + "'," + "'" + "Lois" + "'," + "'" +
//															2000000 + "'," + "'" + "Access" + "'," + "'" + 1200000000 + "'," + "'" + 1234567890 + "');";
//													st1.executeUpdate(query);
													
													
				//									
				//									query = "Update Signup set openingBalance = '" + recipnewbalance +"' where AccountNumber = '"+ acnum +"'; ";
				//									st1.executeUpdate(query);
				//									
				//									query = "Update Signup set openingBalance = '" + mynewbalance +"' where AccountNumber = '"+ myaccnum +"'; ";
				//									st1.executeUpdate(query);
													
													
													
													
													JOptionPane.showMessageDialog(null, "Transfer Successful");
													int ok = JOptionPane.showConfirmDialog(null, "Do you want to perform another transfer");
													if(ok == 0) {
														System.out.println("yes");
														entacc.setEditable(true);
														entacc.setText("");
														entacc.getText();
														repname.setText("");
														repname.getText();
														amountfield.setText("");
														amountfield.getText();
														enterpinfield.setText("");
														enterpinfield.getText();
													}else if(ok == 1) {
														System.out.println("no");
														transf.dispose();
													}else if(ok == 2) {
														System.out.println("cancel");
														System.exit(-1);
													}
												}
												else {
													JOptionPane.showMessageDialog(null, "Insufficient funds");
												}
											}
											else {
												JOptionPane.showMessageDialog(null, "Incorrect Pin");
											}
										}
									
										else {
											if (rat.next() && rat.getString(6).equals(pin)) {
												String cry = rat.getString(6);
												System.out.println(cry);
												JOptionPane.showMessageDialog(null, cry);
												acnum = entacc.getText();
												acname = repname.getText();
												banknam = entbnknam.getText();
												amnt = amountfield.getText();
												pin = enterpinfield.getText();
												recipaccbal = "0";
												double amounttransfered = Double.parseDouble(amnt);
												double recipcurrentbalance = Double.parseDouble(recipaccbal);
												double mycurrentbalance = Double.parseDouble(mybal);
												double recipnewbalance = amounttransfered + recipcurrentbalance;
												double mynewbalance = mycurrentbalance - amounttransfered;
												String recipmoney = fmt.format(recipnewbalance);
												String mymoney = fmt.format(mynewbalance);
												
												if (amounttransfered <= mycurrentbalance) {
													query = "INSERT INTO Transfer(AccountNumber, AccountName, Amount, BankName, AccountBalance, HostAccountNumber) VALUES (" +"'" + acnum + "'," + "'" + acname + "'," + "'" +
															amounttransfered + "'," + "'" + banknam + "'," + "'" + mynewbalance + "'," + "'" + accID + "');";
													st1.executeUpdate(query);
				//									
				//									query = "Update Signup set openingBalance = '" + recipnewbalance +"' where AccountNumber = '"+ acnum +"'; ";
				//									st1.executeUpdate(query);
				//									
				//									query = "Update Signup set openingBalance = '" + mynewbalance +"' where AccountNumber = '"+ myaccnum +"'; ";
				//									st1.executeUpdate(query);
													
													JOptionPane.showMessageDialog(null, "Transfer Successful");
													int ok = JOptionPane.showConfirmDialog(null, "Do you want to perform another transfer");
													if(ok == 0) {
														System.out.println("yes");
														entacc.setText("");
														entacc.getText();
														repname.setText("");
														repname.getText();
														amountfield.setText("");
														amountfield.getText();
														enterpinfield.setText("");
														enterpinfield.getText();
													}else if(ok == 1) {
														System.out.println("no");
														transf.dispose();
													}else if(ok == 2) {
														System.out.println("cancel");
														System.exit(-1);
													}
												}
												else {
													JOptionPane.showMessageDialog(null, "Insufficient funds");
												}
												
											}
											else {
												JOptionPane.showMessageDialog(null, "Incorrect Pin");
											}
												
											}
										}
									}
						}
				}
				catch (Exception ef) {
					JOptionPane.showMessageDialog(transfers, "Error Occured... "  + ef.getMessage());
				}
			}
		});
		
		
		
		bank = new JLabel("Select Bank: ");
		bank.setBounds(90, 310, 90, 20);
		bank.setFont(font);
		thisbnk = new JRadioButton("Houk Bank");
		thisbnk.setBounds(215, 310, 90, 20);
		thisbnk.setFont(font);
//		thisbnk.setSelected(true);
		thisbnk.setFocusable(false);
		otherbnk = new JRadioButton("Other Banks");
		otherbnk.setBounds(310, 310, 95, 20);
		otherbnk.setFont(font);
		otherbnk.setFocusable(false);
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(otherbnk);
		genderGroup.add(thisbnk);
		
		entacc.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				acname = entacc.getText();
				try {
					acname = repname.getText();
					
					acnum = entacc.getText();
					System.out.println(acnum);
					banknam = entbnknam.getText();
					amnt = amountfield.getText();
					pin = enterpinfield.getText();
					
					if (!(acnum.isBlank())) {
						if (acnum.length() == 10) {
					
							query = "SELECT * FROM Signup WHERE AccountNumber = '" + accID + "'";
							
							ResultSet rs = st.executeQuery(query);
							
							query = "SELECT * FROM Signup WHERE AccountNumber = '" + acnum + "'";
							
							ResultSet rst = st1.executeQuery(query);
							
							while (rs.next()) {
								if (rst.next()) {
									myaccnum = rs.getString(4);
									accID = myaccnum;
									JOptionPane.showMessageDialog(null, accID);
									JOptionPane.showMessageDialog(null, myaccnum);
									System.out.println("CHEEEEEEEEE");
									acname = rst.getString(1);
									useraccnum = rst.getString(4);
									repacc = acname;
									repaccID = repacc;
									JOptionPane.showMessageDialog(null, repaccID);
									if (!(acnum.equals(accID))) {
										System.out.println("SKSKSKSKSKKSKSK");
										if (rst.getString(4).equals(acnum)) {
											System.out.println("LMAOOOOOOO");
											thisbnk.setSelected(true);
											entacc.setEditable(false);
											repname.setText(acname);
											enterpinfield.setEditable(true);
										}
									else {
										JOptionPane.showMessageDialog(null, "You cannot transfer to self");
										entacc.setEditable(true);
										thisbnk.setSelected(true);
										enterpinfield.setEditable(false);
									}
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "Invalid Account Number or does not exist. \n\nDo you wish to transfer to another bank?");
									System.out.println("OUT");
									System.out.println("COLLECT");
									repname.setEditable(true);
									otherbnk.setSelected(true);
									entbnknam.setEditable(true);
									acnum = entacc.getText();
									acname = repname.getText();
									banknam = entbnknam.getText();
									amnt = amountfield.getText();
									pin = enterpinfield.getText();
								}
							}
						}
					}
				}
				catch (Exception ay) {
					System.out.println("Error: " + ay.getMessage());
				}
		
			}
			public void focusGained(FocusEvent e) {
				entacc.getText();
			}
		});
		
		mypanel.add(accnt);
		mypanel.add(entacc);
		mypanel.add(name);
		mypanel.add(repname);
		mypanel.add(amount);
		mypanel.add(amountfield);
		mypanel.add(bank);
		mypanel.add(thisbnk);
		mypanel.add(otherbnk);
		mypanel.add(bnknam);
		mypanel.add(entbnknam);
		mypanel.add(enterpin);
		mypanel.add(enterpinfield);
		mypanel.add(my_trans);
		
		transf.add(mypanel);
		
		transf.setSize(500, 600);
		transf.setLocationRelativeTo(null);
		transf.setVisible(true);
		
		
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
		
		
		enterpinfield.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (enterpinfield.getText().length() == 0) {
					enterpinfield.setEchoChar((char) '*');
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (enterpinfield.getText().equals("") ) {
					enterpinfield.setText("");
					enterpinfield.setEchoChar((char) '*');
					}
				}
			});
		}



	public boolean checkmyanswer(String answer) {
		String myanswer = "^[^0-9@._%+-][a-zA-Z .-]*";
		boolean result = Pattern.matches(myanswer, answer);
		if (!result) {
			JOptionPane.showMessageDialog(null, "Invalid Input...");
		}
		return result;
	}

	public boolean confirmMypass(String mypass) {
		String confirm_pass = "\\d{4,}";
		boolean result = Pattern.matches(confirm_pass, mypass);
		if (!result) {
			System.out.println("Hmmmmm....");
			JOptionPane.showMessageDialog(null, "Invalid Pin!");
			System.out.println("Okayyyy....");
		}
		return result;
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
		
		public JPasswordField createPassField() {
			JPasswordField PassField = new JPasswordField(25);
			PassField.setFont(font);
			return PassField;
		}
		
		public JDialog createDialog(String title) {
			JDialog mydialog = new  JDialog();
			mydialog.setTitle(title);
			mydialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			mydialog.setModalityType(ModalityType.APPLICATION_MODAL);
			mydialog.setFont(font);
			return mydialog;
		}

		public void reset() {
			thisTableModel.setRowCount(0);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new Transfer();
			}
		}
	}
}