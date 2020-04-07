package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Client.CConnect;

public class Join extends JFrame {
	private CConnect cc = null;
	
//	JLabel checkLabel; // ���̵� �ߺ�üũ
//	String checkMsg;
//	JTextField checkTxt; 

	public Join(CConnect cc) {
		super("ȸ������");
		this.cc = cc;
		this.setLayout(null); // ��ġ������ ����
		this.setBounds(0, 0, 350, 440);
		setLocationRelativeTo(null); // ������â�� ����ȭ�� �Ѱ�� �����
		

		setting();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void setting() {
		JLabel label = new JLabel("ȸ������ �Է�");
		label.setFont(new Font("����", Font.BOLD, 17));
		JLabel labelLineS = new JLabel("��������������������������������������������");
		JLabel labelId = new JLabel("�� �� ��");
		labelId.setFont(new Font("����", Font.PLAIN, 15));

//		checkLabel = new JLabel(checkMsg); // ���̵� �ߺ�üũ
////		checkTxt = new JTextField();
//		checkLabel.setFont(new Font("����", Font.PLAIN, 12));
//		checkLabel.setForeground(Color.RED);

		JLabel labelPwd = new JLabel("��й�ȣ");
		labelPwd.setFont(new Font("����", Font.PLAIN, 15));
		JLabel labelName = new JLabel("��     ��");
		labelName.setFont(new Font("����", Font.PLAIN, 15));
		JLabel labelTel = new JLabel("��ȭ��ȣ");
		labelTel.setFont(new Font("����", Font.PLAIN, 15));
		JLabel labelAddr = new JLabel("��     ��");
		labelAddr.setFont(new Font("����", Font.PLAIN, 15));
		JLabel labelLineE = new JLabel("��������������������������������������������");

		label.setBounds(30, 25, 120, 20);
		labelLineS.setBounds(25, 60, 320, 20);
		labelId.setBounds(30, 100, 100, 20);
//		checkLabel.setBounds(30, 118, 200, 20);
		labelPwd.setBounds(30, 140, 100, 20);
		labelName.setBounds(30, 180, 100, 20);
		labelTel.setBounds(30, 220, 100, 20);
		labelAddr.setBounds(30, 260, 100, 20);
		labelLineE.setBounds(25, 300, 320, 20);

		this.add(label);
		this.add(labelLineS);
		this.add(labelId);
//		this.add(checkLabel);
		this.add(labelPwd);
		this.add(labelName);
		this.add(labelTel);
		this.add(labelAddr);
		this.add(labelLineE);

		JTextField txtId = new JTextField();
		JTextField txtPwd = new JTextField();
		JTextField txtName = new JTextField();
		JTextField txtTel1 = new JTextField();
		JTextField txtTel2 = new JTextField();
		JTextField txtTel3 = new JTextField();
		JLabel hipen1 = new JLabel("-");
		JLabel hipen2 = new JLabel("-");
		JTextField txtAddr = new JTextField();

		txtId.setBounds(100, 100, 120, 20);
		txtPwd.setBounds(100, 140, 120, 20);
		txtName.setBounds(100, 180, 120, 20);
		txtTel1.setBounds(100, 220, 45, 20);
		txtTel2.setBounds(160, 220, 45, 20);
		txtTel3.setBounds(220, 220, 45, 20);
		hipen1.setBounds(150, 220, 20, 20);
		hipen2.setBounds(210, 220, 20, 20);
		txtAddr.setBounds(100, 260, 210, 20);

		this.add(txtId);
		this.add(txtPwd);
		this.add(txtName);
		this.add(txtTel1);
		this.add(txtTel2);
		this.add(txtTel3);
		this.add(hipen1);
		this.add(hipen2);
		this.add(txtAddr);

		JButton checkBtn = new JButton("�ߺ�Ȯ��");
		checkBtn.setFont(new Font("����", Font.PLAIN, 12));
		checkBtn.setBounds(225, 100, 85, 20);
		this.add(checkBtn);

		checkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cc.send("*id " + txtId.getText());
			}
		});

		JButton applyBtn = new JButton("���� ��û");
		applyBtn.setFont(new Font("����", Font.PLAIN, 12));
		applyBtn.setBounds(120, 340, 90, 25);
		this.add(applyBtn);

		applyBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField txtTel = new JTextField();
				txtTel.setText(txtTel1.getText() + txtTel2.getText() + txtTel3.getText()); // ��ȭ��ȣ ��ħ

				cc.send("/id " + txtId.getText());
				cc.send("/pwd " + txtPwd.getText());
				cc.send("/name " + txtName.getText());
				cc.send("/tel " + txtTel.getText());
				cc.send("/addr " + txtAddr.getText());

				JOptionPane.showMessageDialog(null, "ȸ������� �Ϸ�Ǿ����ϴ�.", "Message", JOptionPane.INFORMATION_MESSAGE);
				new HMain(cc);
				dispose();
			}
		});
	}

	public void idChk(String msg) {
		if(msg.equals("yes")) {
			JOptionPane.showMessageDialog(null, "��밡���� ���̵��Դϴ�.", "Message", JOptionPane.INFORMATION_MESSAGE);
		} else if(msg.equals("no")){
			JOptionPane.showMessageDialog(null, "�ߺ��� ���̵� �ֽ��ϴ�.", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
//		new Join();
	}


}
