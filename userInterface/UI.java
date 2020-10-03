package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CPU.CPUbench;
import HDD.HDDbench;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.Icon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JCheckBox;

public class UI extends JFrame {

	
	private JPanel contentPane;
	private JTextField textFieldForButton1 = new JTextField();
	private JTextField textField_1 = new JTextField();
	private JTextField textFieldThanksForSubmission = new JTextField();
	private JButton buttonRun = new JButton("Run");
	private JCheckBox checkBoxHDD = new JCheckBox("HDD bench");
	private JCheckBox checkBoxCPU = new JCheckBox("CPU bench");
	private JLabel bottomLabel;
	private String CPUScore;
	private String HDDScore;
	private String displayScore="<html>";
	private JLabel labelBackground;
	private JButton buttonSubmit = new JButton("Submit");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public UI() throws IOException {
		System.setProperty("java.library.path","./lib/sigar-amd64-winnt.dll");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 659);
		contentPane = new JPanel();	
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		labelBackground = new JLabel(new ImageIcon(UI.class.getResource("/image/background.png")));
		labelBackground.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		labelBackground.setBounds(0, 0, 837, 620);
		contentPane.add(labelBackground);
		
		
		
		JButton button2 = new JButton("");
		labelBackground.add(button2);
		button2.setForeground(new Color(0, 0, 102));
		button2.setBorder(BorderFactory.createEtchedBorder(1));
		button2.setBackground(Color.BLACK);
		button2.setIcon(new ImageIcon(UI.class.getResource("/image/menu_filled_20px.png")));
		button2.setBounds(2, 89, 39, 36);
		button2.setVisible(true);
		
		
		
		JButton button3 = new JButton("");
		button3.setForeground(new Color(0, 0, 102));
		button3.setBorder(BorderFactory.createEtchedBorder(1));
		button3.setBackground(Color.BLACK);
		button3.setBounds(0, 150, 45, 42);
		button3.setIcon(new ImageIcon(UI.class.getResource("/image/submit_document_20px.png")));
		labelBackground.add(button3);
		button3.setVisible(true);
		
		
		
		JButton button1 = new JButton("");
		labelBackground.setLabelFor(button1);
		button1.setForeground(new Color(0, 0, 102));
		button1.setBackground(new Color(0, 0, 0));
		button1.setBorder(BorderFactory.createEtchedBorder(1));
		button1.setIcon(new ImageIcon(UI.class.getResource("/image/icons8_play_20px.png")));
		button1.setBounds(0, 28, 39, 36);
		labelBackground.add(button1);
		
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(button2.isVisible() == true && bottomLabel != null) {bottomLabel.setVisible(false);}
				if(textField_1 != null) {textField_1.setVisible(false); buttonSubmit.setVisible(false); textFieldThanksForSubmission.setVisible(false);}
				
				textFieldForButton1.setBounds(103, 28, 390, 36);
				labelBackground.add(textFieldForButton1);
				textFieldForButton1.setVisible(true);
				textFieldForButton1.setText("Check what benchmark(s) you would like to run:");
				textFieldForButton1.setBorder(BorderFactory.createEtchedBorder(1));
				textFieldForButton1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
				
				
				
				checkBoxCPU.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
				labelBackground.add(checkBoxCPU);
				checkBoxCPU.setForeground(new Color(0, 0, 0));
				checkBoxCPU.setBounds(85, 80, 103, 23);
				checkBoxCPU.setBackground(Color.WHITE);
				checkBoxCPU.setVisible(true);
				
				
			
				checkBoxHDD.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
				labelBackground.add(checkBoxHDD);
				checkBoxHDD.setForeground(new Color(0, 0, 0));
				checkBoxHDD.setBounds(85, 115, 110, 23);
				checkBoxHDD.setBackground(Color.WHITE);
				checkBoxHDD.setVisible(true);
				
				
				
				
				buttonRun.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
				buttonRun.setBounds(103, 150, 72, 36);
				labelBackground.add(buttonRun);
				buttonRun.setVisible(true);
				buttonRun.setBackground(Color.WHITE);
				buttonRun.setBorder(BorderFactory.createEtchedBorder(1));
				buttonRun.setBorderPainted(false);
				
				buttonRun.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(bottomLabel != null) {bottomLabel.setIcon(null); bottomLabel.setVisible(false);}
						if(textField_1 != null) {textField_1.setVisible(false); buttonSubmit.setVisible(false); textFieldThanksForSubmission.setVisible(false);}
						displayScore="<html>";
						
						if(checkBoxCPU.isSelected() == false && checkBoxHDD.isSelected() == false) {
							bottomLabel = new JLabel();
							bottomLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
							bottomLabel.setBounds(130, 300, 450, 280);
							labelBackground.add(bottomLabel);
							bottomLabel.setBackground(Color.WHITE);
							bottomLabel.setForeground(Color.WHITE);
							bottomLabel.setVisible(true);
							bottomLabel.setText("Please add AT LEAST one benchmark!");
						}
						else
						{
							ExecutorService executor = Executors.newFixedThreadPool(3); 
							ComputeScore bench = new ComputeScore();
							executor.execute(bench);
						
							URL url = this.getClass().getResource("/image/g.gif");
							Icon gif = new ImageIcon(url);	   
							bottomLabel = new JLabel(gif);
							bottomLabel.setBounds(267, 337, 285, 183);
							labelBackground.add(bottomLabel);
							bottomLabel.setBackground(Color.WHITE);
							bottomLabel.setVisible(true);

							executor.shutdown();
						}
							
					}   });
			
				
			}    });
		
		
		
		
		
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					
			if(button1.isVisible() == true) {textFieldForButton1.setVisible(false); checkBoxCPU.setVisible(false); checkBoxHDD.setVisible(false); buttonRun.setVisible(false);}
			if(bottomLabel != null) {bottomLabel.setIcon(null); bottomLabel.setVisible(false); }
			textField_1.setVisible(false); buttonSubmit.setVisible(false); textFieldThanksForSubmission.setVisible(false);
			
			
			bottomLabel = new JLabel();	
			bottomLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
			bottomLabel.setBounds(100, 300, 550, 280);
			labelBackground.add(bottomLabel);
			bottomLabel.setBackground(Color.WHITE);
			bottomLabel.setForeground(Color.WHITE);
			bottomLabel.setVisible(true);
			//bottomLabel.setText(ComputerSpecifications.getComputerInfo().getSpecifications());
			DataSystem d = new DataSystem();
			d.run();
			bottomLabel.setText(d.getResult());
			}   });
		
		
		
		
		
		
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(bottomLabel != null) {bottomLabel.setIcon(null); bottomLabel.setVisible(false);}
				if(button1.isVisible() == true) {textFieldForButton1.setVisible(false); checkBoxCPU.setVisible(false); checkBoxHDD.setVisible(false); buttonRun.setVisible(false);}
				
				textField_1.setBounds(103, 28, 150, 36);
				labelBackground.add(textField_1);
				textField_1.setText("Submit your score:");
				textField_1.setBorder(BorderFactory.createEtchedBorder(1));
				textField_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
				textField_1.setVisible(true);
			
				
				labelBackground.add(buttonSubmit);
				buttonSubmit.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
				buttonSubmit.setBounds(135, 100, 72, 36);
				buttonSubmit.setBackground(Color.WHITE);
				buttonSubmit.setBorder(BorderFactory.createEtchedBorder(1));
				buttonSubmit.setBorderPainted(false);
				buttonSubmit.setVisible(true);
				
				buttonSubmit.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						String messageDisplay=null;
						if(CPUScore == null && HDDScore == null) {
							messageDisplay = "Please run first at least one benchmark!";
						}
						else {
							SendEmail s = new SendEmail();
							s.sendMail();
							messageDisplay="Thanks for your submission!";
						}
						
						textFieldThanksForSubmission.setBounds(230, 380, 450, 70);
						labelBackground.add(textFieldThanksForSubmission);
						textFieldThanksForSubmission.setText(messageDisplay);
                        textFieldThanksForSubmission.setBackground(new Color(42,40,38));
                        textFieldThanksForSubmission.setForeground(Color.WHITE);
						textFieldThanksForSubmission.setBorder(BorderFactory.createEtchedBorder(1));
						textFieldThanksForSubmission.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
						textFieldThanksForSubmission.setVisible(true);
						
					}   });
			}   });
		
	}
	
	
	class ComputeScore implements Runnable {
		@Override
		public void run() {
			if(UI.this.checkBoxCPU.isSelected() == true && UI.this.checkBoxHDD.isSelected() == true) {
				CPUbench bench1 = CPUbench.getInstance();
				HDDbench bench2 = HDDbench.getInstance();;
				bench1.run();
				bench2.run();
				
				UI.this.CPUScore = bench1.getScore();
				UI.this.HDDScore = bench2.getScore();

				UI.this.displayScore += UI.this.CPUScore+"<br>"+UI.this.HDDScore+"<br>";
				UI.this.displayScore += "</html>";
			}
			else if(UI.this.checkBoxCPU.isSelected() == true)
				{
					CPUbench bench1 = CPUbench.getInstance();
					bench1.run();
					UI.this.CPUScore = bench1.getScore();
					UI.this.displayScore += UI.this.CPUScore;
					UI.this.displayScore += "</html>";
				}
				else if(UI.this.checkBoxHDD.isSelected() == true)
					{
						HDDbench bench2 = HDDbench.getInstance();
						bench2.run();
						UI.this.HDDScore = bench2.getScore();
						UI.this.displayScore += UI.this.HDDScore+"<br>";
						UI.this.displayScore += "</html>";
					}
				
			
			UI.this.bottomLabel.setIcon(null); UI.this.bottomLabel.setVisible(false);
			UI.this.bottomLabel = new JLabel();	
			UI.this.bottomLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
			UI.this.bottomLabel.setBounds(130, 300, 500, 280);
			UI.this.labelBackground.add(UI.this.bottomLabel);
			UI.this.bottomLabel.setBackground(Color.WHITE);
			UI.this.bottomLabel.setForeground(Color.WHITE);
			UI.this.bottomLabel.setVisible(true);
			UI.this.bottomLabel.setText(UI.this.displayScore);
		}
	}
	
	
	public class SendEmail {

		public void sendMail() {
			try {
			      String host = "smtp.gmail.com";
			      String user = "proiectco.bitsplease@gmail.com";
			      String pass = "1234567890!Qwe";
			      String to = "anita.patrut@gmail.com";
			      String from = "proiectco.bitsplease@gmail.com";
			      String subject = "Sample message";
			      DataSystem d= new DataSystem();
			      //String messageText = ComputerSpecifications.getComputerInfo().getSpecifications()+"\n CPU score: "+UI.this.CPUScore+"\n RAM score: "+UI.this.RAMScore;
			      String messageText = d.getSpecifications()+"\n CPU score: "+UI.this.CPUScore+"\n HDD score: "+UI.this.HDDScore;
			      boolean sessionDebug = false;
			      
			      Properties props = System.getProperties();
			      
			      props.put("mail.smtp.starttis.enable","true");
			      props.put("mail.smtp.host",host);
			      props.put("mail.smtp.port", "587");
			      props.put("mail.smtp.auth", "true");
			      props.put("mail.smtp.starttls.required", "true");
			      
			      
			      java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			      Session mailSession = Session.getDefaultInstance(props,null);
			      mailSession.setDebug(sessionDebug);
			      Message msg = new MimeMessage(mailSession);
			      msg.setFrom(new InternetAddress(from));
			      InternetAddress[] address = {new InternetAddress(to)};
			      msg.setRecipients(Message.RecipientType.TO, address);
			      msg.setSubject(subject); msg.setSentDate(new Date());
			      msg.setText(messageText);
			      
			      Transport transport = mailSession.getTransport("smtp");
			      transport.connect(host,user,pass);
			      transport.sendMessage(msg, msg.getAllRecipients());
			      transport.close();
			         
			      } catch (Exception mex) {
			         mex.printStackTrace();
			      }
		}
	}
	
	
	
}
