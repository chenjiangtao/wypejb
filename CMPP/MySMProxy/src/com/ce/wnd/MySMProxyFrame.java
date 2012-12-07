package com.ce.wnd;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import com.ce.smsr.MyWebSender;

public class MySMProxyFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 562721717412735889L;
	private JTextField textField;
	private JTextArea txtMsg;
	private JTextArea txtMobile;
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MySMProxyFrame frame = new MySMProxyFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public MySMProxyFrame() {
		super();
		getContentPane().setLayout(null);
		setBounds(100, 100, 500, 375);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JButton button = new JButton();
		button.setText("Send");
		button.setBounds(10, 10, 80, 28);
		getContentPane().add(button);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				try {
					String message = txtMsg.getText();
					String smobile = txtMobile.getText();
					String[] mobiles = new String[]{};
					if(smobile != null && smobile.length() > 0 )
						mobiles = txtMobile.getText().split("\n");
					MyWebSender.getInstance().sendMsg(message, mobiles);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		final JButton button1 = new JButton();
		button1.setText("ThSend");
		button1.setBounds(100, 10, 80, 28);
		getContentPane().add(button1);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				
					Thread th = new Thread(new Runnable(){

						public void run() {
							while(true) {
								try {
									String message = txtMsg.getText();
									String smobile = txtMobile.getText();
									String[] mobiles = new String[]{};
									if(smobile != null && smobile.length() > 0 )
										mobiles = txtMobile.getText().split("\n");
									MyWebSender.getInstance().sendMsg(message, mobiles);
									Thread.sleep(3000);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								
								
							}
						}
					});
					th.start();
				
			}
		});
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 300, 80);
		getContentPane().add(scrollPane);


		txtMsg = new JTextArea();
		scrollPane.setViewportView(txtMsg);
		txtMsg.setBorder(new BevelBorder(BevelBorder.LOWERED));
		txtMsg.setText("你好hello!");
		
		final JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(10, 170, 300, 80);
		getContentPane().add(scrollPane1);


		txtMobile = new JTextArea();
		scrollPane1.setViewportView(txtMobile);
		txtMobile.setBorder(new BevelBorder(BevelBorder.LOWERED));
		txtMobile.setText("13800010001\n13800010002");
//
//		textField = new JTextField();
//		textField.setBounds(133, 13, 87, 22);
//		getContentPane().add(textField);
//
//		final JLabel label = new JLabel();
//		label.setText("New JLabel");
//		label.setBounds(226, 15, 66, 18);
//		getContentPane().add(label);
		
		//
	}

}
