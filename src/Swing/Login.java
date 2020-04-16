package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Client.CConnect;

public class Login extends JFrame {
	private CConnect cc = null;
	private HMain hm = null;
	private Choice ch = null;

	private JLabel idChkLabel = new JLabel(); // ���̵� üũ ��
	private JLabel pwdChkLabel = new JLabel(); // ��й�ȣ üũ ��
	private JLabel labelId, labelPwd;
	private JTextField txtId;
	private JButton loginBtn, outBtn;
	private JPasswordField txtPwd;

	public Login(CConnect cc) {
		super("�α���");
		this.cc = cc;
		this.setLayout(null); // ��ġ������ ����
		this.setBounds(0, 0, 250, 280);
		setLocationRelativeTo(null); // ����ȭ�� �Ѱ�� ����

		labelTxtsetting();
		buttonSetting();
		checkLabel();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	// ��, �ؽ�Ʈ�ʵ� ����
	private void labelTxtsetting() {
		labelId = new JLabel("�� �� ��");
		labelId.setFont(new Font("����", Font.PLAIN, 12));
		labelPwd = new JLabel("��й�ȣ");
		labelPwd.setFont(new Font("����", Font.PLAIN, 12));

		labelId.setBounds(30, 60, 100, 20);
		labelPwd.setBounds(30, 100, 100, 20);

		this.add(labelId);
		this.add(labelPwd);

		txtId = new JTextField();
		txtPwd = new JPasswordField();
		txtPwd.setEchoChar('*');

		txtId.setBounds(100, 60, 100, 20);
		txtPwd.setBounds(100, 100, 100, 20);

		this.add(txtId);
		this.add(txtPwd);
	}

	// ��ư ����
	private void buttonSetting() {
		loginBtn = new JButton("�α���");
		loginBtn.setFont(new Font("����", Font.PLAIN, 12));
		loginBtn.setFocusPainted(false);
		loginBtn.setBounds(76, 150, 80, 25);
		this.add(loginBtn);

		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean chk = true;

				String pwd = "";
				char[] secret_pwd = txtPwd.getPassword(); // �н����� �о�ͼ� char[]�迭�� ����
				for (char c : secret_pwd) {
					pwd = pwd + c;
				}
				if (txtId.getText().equals("")) {
					idChkLabel.setText(" *���̵� �Է����ּ���.");
					chk = false;
				} else if (pwd.equals("")) {
					pwdChkLabel.setText(" *��й�ȣ�� �Է����ּ���.");
					chk = false;
				}

				if (chk) {
					idChkLabel.setText("");
					pwdChkLabel.setText("");

					String msg = ">login " + txtId.getText() + " " + pwd;
					cc.send(msg);
					chkMsg();
				}
			}
		});

		outBtn = new JButton("< ����");
		outBtn.setFont(new Font("����", Font.PLAIN, 12));
		outBtn.setBorderPainted(false);
		outBtn.setContentAreaFilled(false);
		outBtn.setFocusPainted(false);
		outBtn.setBounds(5, 200, 70, 30);
		this.add(outBtn);

		outBtn.addMouseListener(new MouseListener() { // ������ ��ư

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				outBtn.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				outBtn.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				hm = HMain.getInstance();
				hm.setVisible(true);
				dispose();
			}
		});

//		outBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new HMain(cc);
//				dispose();
//			}
//		});
	}

	// �Է� üũ �� ����
	private void checkLabel() {
		idChkLabel.setFont(new Font("����", Font.PLAIN, 12)); // ���̵� üũ ��
		idChkLabel.setForeground(Color.RED);
		idChkLabel.setBounds(30, 78, 200, 20);
		this.add(idChkLabel);

		pwdChkLabel.setFont(new Font("����", Font.PLAIN, 12)); // ��й�ȣ üũ ��
		pwdChkLabel.setForeground(Color.RED);
		pwdChkLabel.setBounds(30, 118, 250, 20);
		this.add(pwdChkLabel);
	}

	// ���̵� ��й�ȣ üũ �޼���
	public void chkMsg() {
		String msg = cc.receive();
		if (msg.indexOf("loginYes") > -1) { // �α��� ����
			JOptionPane.showMessageDialog(null, "���������� �α��εǾ����ϴ�.", "Message", JOptionPane.INFORMATION_MESSAGE);
			ch = new Choice(cc);
			dispose();

		} else if (msg.indexOf("loginNo") > -1) { // �α��� ����
			JOptionPane.showMessageDialog(null, "���̵� ��й�ȣ�� �ٽ� Ȯ�����ּ���.", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}

//	public static void main(String[] args) {
//		new Login();
//	}
}
