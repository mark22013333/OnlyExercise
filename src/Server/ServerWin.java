package Server;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ServerWin extends JFrame {
	private JLabel statusLal;
	private JTextArea textArea;
	private JTextArea infoTextArea;
	private JTextField textField;
	private JButton sendBtn;
	private JScrollPane scrollPane;

	public ServerWin() {
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		this.setSize(650, 540);
		this.setTitle("Server");

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(null);
		Container contentPane = this.getContentPane();
		this.setBackground(Color.white);

		// 狀態提示
		statusLal = new JLabel("等待客戶端連接");
		statusLal.setBounds(0, 0, 650, 50);
		statusLal.setFont(new Font("Andale Mono", 12, 18));
		statusLal.setHorizontalAlignment(JLabel.CENTER);
		statusLal.setVerticalAlignment(JLabel.CENTER);
		contentPane.add(statusLal);

		// 450 * 340 聊天記錄框
		textArea = new JTextArea();
		textArea.setBounds(0, 50, 450, 340);
		scrollPane = new JScrollPane(textArea);
		textArea.setBackground(Color.lightGray);
		textArea.setFont(new Font("Andale Mono", 12, 16));
		contentPane.add(textArea);

		// 450 * 70 訊息輸入框
		textField = new JTextField();
		textField.setBounds(0, 390, 450, 50);
		textField.setFont(new Font("Andale Mono", 12, 18));
		scrollPane.setBackground(Color.blue);
		textArea.setText("HELLO");
		contentPane.add(textArea);

		// 訊息發送按鈕
		sendBtn = new JButton("送出");
		sendBtn.setBounds(370, 450, 70, 25);
//		sendBtn.addActionListener();
		contentPane.add(sendBtn);

		// info資料框
		infoTextArea = new JTextArea();
		infoTextArea.setBounds(450, 50, 200, 490);
		infoTextArea.setText("好友資料訊息");
		infoTextArea.setFont(new Font("Andale Mono", 12, 18));
		contentPane.add(infoTextArea);
	}

	public static void main(String[] args) {

		ServerWin sw = new ServerWin();
		sw.setVisible(true);

	}

}
