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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class home_page extends JFrame{
	JLabel label1, label2, label3,label4,label5,label6, label7, label8, lb1,lb2,lb3,lb4,lb5,lb6;
	JTextField text1,text2, text3, text4,text5, text6,txt1,txt2,txt3,txt4,txt5,txt6;
	Color blue_magenta,blue;
	JButton btn1, btn2,btn3,btn4,btn5,btn6,btn7,btn8, btn9,login, register;
	JPanel panel1, panel2,panel5, panel17;
	JFrame frame1 , frame2,frame3, frame4,frame5,frame6;
	JMenuBar mb;
	String[] banks = {"Gtb","Zenith","AccessDiamond","Kuda","Firstbank","UBA"};
	JMenu services,loan, th,Buy_airtime,back;
	JMenuItem transfers,bill_payment,help,logout, airtime,acctbal,space1,space2,space3,
	data,bill,cable,checkl,borrowl;
	JLabel accountLabel, accountnumLabel, accountbalLabel;
	JPanel transactPanel;
	public home_page() {
		setVisible(true);
		setTitle("Home Page");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(500, 500);
		setLocationRelativeTo(null);


		mb = new JMenuBar();
//		back = new JMenu("«");
	
		services= new JMenu("\u2630");
        space1 = new JMenuItem("");
        space2  = new JMenuItem("");
        space3  = new JMenuItem("");
		transfers = new  JMenuItem("\uD83D\uDCB0 Transfers");
		acctbal = new JMenuItem("Account Balance");
		help = new JMenuItem("Help");
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame2 = new JFrame();
				frame2.setVisible(true);
				frame2.setTitle("Help Page");
				frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame2.setSize(500, 500);
				frame2.setLocationRelativeTo(null);
				blue = new Color(61,52,240);
				JTextArea hlp = new JTextArea("sdg  gsujbtgufw fw7");
				hlp.setEditable(false);
				hlp.setBackground(blue);
				hlp.setForeground(Color.BLACK);
				frame2.add(hlp);

			}
		});
		logout = new JMenuItem("Log Out");
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new Register1();
			}
		});

		Buy_airtime = new JMenu("Buy Airtime & Data");
		airtime = new JMenuItem("Airtime");
		airtime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame1 = new  JFrame();
				frame1.setVisible(true);
				frame1.setTitle("Airtime");
				frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame1.setSize(500, 500);
				frame1.setLocationRelativeTo(null);
				blue = new Color(61,52,240);
				GridBagConstraints gridBag = new GridBagConstraints();
				JPanel pan1 = new JPanel(new GridBagLayout());
				JLabel lab = new JLabel("Enter PhoneNumber: ");
				gridBag.gridx = 0;
				gridBag.gridy = 0;
				pan1.add(lab,gridBag);
				JTextField number = new JTextField(10);
				gridBag.gridx = 3;
				gridBag.gridy = 0;
				number.addKeyListener(new KeyAdapter() {
					@SuppressWarnings({  "static-access" })
					public void keyPressed(KeyEvent ke) {
						String num = number.getText();
						char c = ke.getKeyChar();
						if(c>= '0'&& c<= '9' || c == ke.VK_BACK_SPACE ) {
							//							number.setEditable(true);
						}else {
							//							number.setEditable(false);
							JOptionPane.showMessageDialog(null, "only number","",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				pan1.add(number,gridBag);
				JLabel lab2  = new JLabel("Enter Amount: ");
				gridBag.gridx = 0;
				gridBag.gridy = 2;
				pan1.add(lab2,gridBag);
				JTextField amt = new JTextField(10);
				gridBag.gridx = 3;
				gridBag.gridy = 2;
				amt.addKeyListener(new KeyAdapter() {
					@SuppressWarnings("static-access")
					public void keyPressed(KeyEvent ke) {
						String num = amt.getText();
						char c = ke.getKeyChar();
						if(c>= '0'&& c<= '9' || c == ke.VK_BACK_SPACE ) {
							//							number.setEditable(true);
						}else {
							//							number.setEditable(false);
							JOptionPane.showMessageDialog(null, "only number","",JOptionPane.ERROR_MESSAGE);;
						}
					}
				});
				pan1.add(amt,gridBag);
				JLabel space1 = new JLabel("  ");
				gridBag.gridx = 2;
				gridBag.gridy = 3;
				pan1.add(space1,gridBag);
				JButton buy = new JButton("Buy");
				gridBag.gridx= 2;
				gridBag.gridy = 4;
				buy.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String num = number.getText();
						String am = amt.getText();
						try {
						int len = num.length();
						//						System.out.println(len);
						if(len != 11) {
							JOptionPane.showMessageDialog(null, "incomplete number","",JOptionPane.ERROR_MESSAGE);
							number.setText(null);
						}
						int len1 = am.length();
						//						System.out.println(len);
						if(len1 == 0 && len1 == 1 ) {
							JOptionPane.showMessageDialog(null, "enter amount","",JOptionPane.ERROR_MESSAGE);
							amt.setText(null);	
						}
						int ma = Integer.parseInt(am);
						int at = ma + 50;
						JOptionPane.showMessageDialog(null, "Your airtime of #"+am+" for "+num+" has been successfully recharged.\n"+"#"
								+at+" has been deducted from your account.");

						//						setEnabled(false);
						}catch(NumberFormatException ex) {
							int hi = JOptionPane.showConfirmDialog(null,"only number","",JOptionPane.YES_NO_OPTION);
							if(hi == 0) {
							number.setText(null);
							amt.setText(null);	
//								setVisible(false);
//								new home_page();
							}else {
								frame1.dispose();
								new home_page();
							}
							
						}
					}
				});
				pan1.add(buy,gridBag);
				JButton back = new JButton("«");
				gridBag.gridx= 3;
				gridBag.gridy = 4;
				back.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frame1.dispose();
						new home_page();
					}
				});
				pan1.add(back,gridBag);
				frame1.add(pan1);
			}
		});
		data = new JMenuItem("Data");
		data.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame4 = new  JFrame();
				frame4.setVisible(true);
				frame4.setTitle("Airtime");
				frame4.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame4.setSize(500, 500);
				frame4.setLocationRelativeTo(null);
				blue = new Color(61,52,240);
				GridBagConstraints gridBag = new GridBagConstraints();
				JPanel pan1 = new JPanel(new GridBagLayout());
				JLabel lab = new JLabel("Enter PhoneNumber: ");
				gridBag.gridx = 0;
				gridBag.gridy = 0;
				pan1.add(lab,gridBag);
				JTextField number1 = new JTextField(10);
				gridBag.gridx = 3;
				gridBag.gridy = 0;
				number1.addKeyListener(new KeyAdapter() {
					@SuppressWarnings("static-access")
					public void keyPressed(KeyEvent ke) {
						String num = number1.getText();
						char c = ke.getKeyChar();
						if(c>= '0'&& c<= '9' || c == ke.VK_BACK_SPACE ) {
							//								number.setEditable(true);
						}else {
							//								number.setEditable(false);
							JOptionPane.showMessageDialog(null, "only number","",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				pan1.add(number1,gridBag);
				JLabel lab2  = new JLabel("Select DataAmount: ");
				gridBag.gridx = 0;
				gridBag.gridy = 2;
				pan1.add(lab2,gridBag);
				String [] dt ={"1GB","2GB","3GB","4GB","5GB","6GB","7GB","10GB"};
				JComboBox<String> td =new JComboBox<>(dt);
				gridBag.gridx = 3;
				gridBag.gridy = 2;
				pan1.add(td,gridBag);
				JLabel space1 = new JLabel("  ");
				gridBag.gridx = 2;
				gridBag.gridy = 3;
				pan1.add(space1,gridBag);
				JButton buy = new JButton("Buy");
				gridBag.gridx= 2;
				gridBag.gridy = 4;
				buy.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String num = number1.getText();
						//							int len = num.length();
						//							System.out.println(len);
						String am = td.getSelectedItem().toString();
						try {
						int x =0;
						if(am.equals(dt[0])) {
							x = 500;
						}else if(am.equals(dt[1])) {
							x = 1000;
						}else if(am.equals(dt[2])) {
							x = 1500;
						}else if(am.equals(dt[3])) {
							x = 2000;
						}else if(am.equals(dt[4])) {
							x = 3000;
						}else if(am.equals(dt[5])) {
							x = 3500;
						}else if(am.equals(dt[6])) {
							x = 4500;
						}else if(am.equals(dt[7])) {
							x = 1000;
						}
						//							int ma = Integer.parseInt(am);
						int len = num.length();
						if(len != 11) {
							JOptionPane.showMessageDialog(null, "incomplete number","",JOptionPane.ERROR_MESSAGE);
							number1.setText(null);
						}else {
						//							int ma = Integer.parseInt(am);
						int at = x + 50;
						JOptionPane.showMessageDialog(null, "Your Data of "+ am  +" for "+ num+ " has been recharged."
								+"\n"+ at + " has been debited from your account.");
						number1.setText(null);
						}
						//							setEnabled(false);
						}catch(NumberFormatException ex) {
							int hi = JOptionPane.showConfirmDialog(null,"only number","",JOptionPane.YES_NO_OPTION);
							if(hi == 0) {
							number1.setText(null);
								
//								setVisible(false);
//								new home_page();
							}else {
								frame4.dispose();
								new home_page();
							}
							
						}
					}
				});
				pan1.add(buy,gridBag);
				JButton back = new JButton("«");
				gridBag.gridx= 3;
				gridBag.gridy = 4;
				back.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frame4.dispose();
						new home_page();
					}
				});
				pan1.add(back,gridBag);
				frame4.add(pan1);

				// TODO Auto-generated method stub
				//					 double prc1 = Double.parseDouble(prc);
				//					double a = x*0.10;
				//					double b = a+x;
				//				    JOptionPane.showMessageDialog(null, "Your Airtime of "+ prc  +" for "+ num+ "has been recharged."
				//					+"\n"+ b + " has been debited from your account.");
			}

		});
		Buy_airtime.add(airtime);
		Buy_airtime.add(data);

		bill_payment = new JMenu("Bill Payment");
		bill = new JMenuItem("Pay Electricity Bill");
		cable = new JMenuItem("Pay For Cable");
		cable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				frame5 = new  JFrame();
				frame5.setVisible(true);
				frame5.setTitle("Airtime");
				frame5.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame5.setSize(500, 500);
				frame5.setLocationRelativeTo(null);
				GridBagConstraints gridBag = new GridBagConstraints();
				JPanel pan1 = new JPanel(new GridBagLayout());
				JLabel lab = new JLabel("Enter Cable Network: ");
				gridBag.gridx = 0;
				gridBag.gridy = 0;
				pan1.add(lab,gridBag);
				String [] cable = {"DSTV", "GOTV"};
				JComboBox<String> combobox1= new JComboBox<>(cable);
				gridBag.gridx = 3;
				gridBag.gridy = 0;
				pan1.add(combobox1,gridBag);
				JLabel lab2  = new JLabel("Enter Decoder pin:  ");
				gridBag.gridx = 0;
				gridBag.gridy = 2;
				pan1.add(lab2,gridBag);
				JTextField pin = new JTextField(10);
				gridBag.gridx = 3;
				gridBag.gridy = 2;
				pin.addKeyListener(new KeyAdapter() {
					@SuppressWarnings("static-access")
					public void keyPressed(KeyEvent ke) {
						String num = pin.getText();
						char c = ke.getKeyChar();
						if(c>= '0'&& c<= '9' || c == ke.VK_BACK_SPACE ) {
							//								number.setEditable(true);
						}else {
							//								number.setEditable(false);
							JOptionPane.showMessageDialog(null, "only number","",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				pan1.add(pin,gridBag);
				JLabel space1 = new JLabel("  ");
				gridBag.gridx = 2;
				gridBag.gridy = 3;
				pan1.add(space1,gridBag);
				JButton sub= new JButton("Buy");
				gridBag.gridx= 2;
				gridBag.gridy = 4;
				sub.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String nip = pin.getText();
						int len = nip.length();
						if(len != 10) {
							JOptionPane.showMessageDialog(null, "incomplete decoder pin");
						}
						String come = combobox1.getSelectedItem().toString();
						if(come.equals(cable[0])) {
							String []dstv = {"Padi","Yanga","Confam","Compact","Compact plus","Premium"};
							JComboBox<String> combobox2= new JComboBox<>(dstv);
							int amt= 0;
							JOptionPane.showMessageDialog(null, combobox2);
							String come2  = combobox2.getSelectedItem().toString();
							if(come2.equals(dstv[0])) {
								amt = 2100;
							}else if(come2.equals(dstv[1])) {
								amt = 2950;
							}else if(come2.equals(dstv[2])) {
								amt = 5300;
							}else if(come2.equals(dstv[3])) {
								amt = 9000;
							}else if(come2.equals(dstv[4])) {
								amt = 14250;
							}else if(come2.equals(dstv[5])) {
								amt = 21000;
							}int at = amt + 150;
							//								 String op =JOptionPane.showInputDialog(null, "input DSTV pin:");

							JOptionPane.showMessageDialog(null, "You have suscribed for : "+nip+" for package "+come2); 
							JOptionPane.showMessageDialog(null, "Subscription succesfull.\n" + " #"+at+
									" has been deducted from your account.");
						}else if(come.equals(cable[1])) {
							String []gotv = {"GOtv supa","GOtv max","GOtv jolli","GOtv jinja","GOtv lite"};
							JComboBox<String> combobox2= new JComboBox<>(gotv);
							int amt1= 0;
							JOptionPane.showMessageDialog(null, combobox2);
							String come3  = combobox2.getSelectedItem().toString();
							if(come3.equals(gotv[0])) {
								amt1 = 5500;
							}else if(come3.equals(gotv[1])) {
								amt1 = 4150;
							}else if(come3.equals(gotv[2])) {
								amt1 = 2800;
							}else if(come3.equals(gotv[3])) {
								amt1 = 1900;
							}else if(come3.equals(gotv[4])) {
								amt1 = 900;
							}int at = amt1 + 150;							
							JOptionPane.showMessageDialog(null, "You have suscribed for : "+nip+" for package " +come3); 
							JOptionPane.showMessageDialog(null, "Subscription succesfull.\n"+ " #"+at+
									" has been deducted from your account.");					
						} 


					}
				});
				pan1.add(sub,gridBag);
				JButton back = new JButton("«");
				gridBag.gridx= 3;
				gridBag.gridy = 4;
				back.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frame5.dispose();
						new home_page();
					}
				});
				pan1.add(back,gridBag);
				frame5.add(pan1);

			}
		});

		bill_payment.add(bill);
		bill_payment.add(cable);


		th= new JMenu("Transactions History");
		loan= new JMenu ("Request Loan");
		checkl = new JMenuItem("Check Loan Eligibility");
		borrowl = new JMenuItem("Collect Loan");
		loan.add(checkl);
		loan.add(borrowl);
		services.add(transfers);
		services.add(acctbal);
		services.add(loan);
		services.add(Buy_airtime);
		services.add(bill_payment);
		services.add(help);
		services.add(space1);
		services.add(space2);
		services.add(space3);
		services.add(logout);
//		mb.add(back);
		mb.add(services);
		mb.add(th);
		add(mb);
		setJMenuBar(mb);
		//		  String life = "C:\\Users\\HP\\Documents\\java/bank_background.jpg";
		//			ImageIcon one = new ImageIcon(life);
		//			 Image img = one.getImage().getScaledInstance(1600,1000, Image.SCALE_DEFAULT);
		//			 ImageIcon new_img = new ImageIcon(img);
		//			 
		//		  JLabel thumb = new JLabel(new_img);
		//		  add(thumb);
		transactPanel = new JPanel();
		transactPanel.setBounds(0, 100, 520, 600);
		
		accountLabel = new JLabel("Obaloluwa");
		accountLabel.setBounds(20, 10, 80, 40);
		
		accountnumLabel = new JLabel("0173797746");
		accountnumLabel.setBounds(20, 35, 80, 40);
		
		accountbalLabel = new JLabel("\u20A6100,000,000.00");
		accountbalLabel.setBounds(370, 25, 100, 40);
		
		//ADDING LABELS TO FRAME
		add(accountLabel);
		add(accountnumLabel);
		add(accountbalLabel);
		add(transactPanel);
		
//		pack();

//		addWindowListener(new WindowAdapter() {
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//				// TODO Auto-generated method stub
//				new Login_page();
//			}
//		});

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new home_page();

	}



}
