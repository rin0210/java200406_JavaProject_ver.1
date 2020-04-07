package Swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Client.CConnect;

public class HMain extends JFrame {
	Join j;

	CConnect cc = null;

	public HMain(CConnect cc) {
		this.cc = cc;
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 700, 600);
		setLocationRelativeTo(null); // ������â�� ����ȭ�� �Ѱ�� �����

		createCP();
		createNP();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void createNP() {
		JPanel nP = new JPanel();
//		nP.setLayout(new FlowLayout(FlowLayout.RIGHT)); // ������ ����
		nP.setLayout(new BorderLayout());
		JPanel hotelP = new JPanel();
		JLabel hotelLable = new JLabel("HOTEL");
		hotelLable.setFont(new Font("Serif", Font.BOLD, 40)); // �۾�ü, ����, ũ��
		hotelP.add(hotelLable);
		nP.add(hotelP, "Center");

		JPanel loginP = new JPanel();
		loginP.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton loginBtn = new JButton("�α���");
		loginBtn.setFont(new Font("����", Font.PLAIN, 12));
		loginBtn.setBorderPainted(false); // �׵θ���
		loginBtn.setContentAreaFilled(false);
		loginBtn.setFocusPainted(false);
		loginP.add(loginBtn);

		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				dispose();

			}
		});

		JButton joinBtn = new JButton("ȸ������");
		joinBtn.setFont(new Font("����", Font.PLAIN, 12));
		joinBtn.setBorderPainted(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setFocusPainted(false);
		loginP.add(joinBtn);

		joinBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				j = new Join(cc);
				dispose();
			}
		});

		JButton nonMBtn = new JButton("��ȸ������");
		nonMBtn.setFont(new Font("����", Font.PLAIN, 12));
		nonMBtn.setBorderPainted(false);
		nonMBtn.setContentAreaFilled(false);
		nonMBtn.setFocusPainted(false);
		loginP.add(nonMBtn);
		nP.add(loginP, "North");

		nonMBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Choice();
				dispose();
			}
		});

		this.add(nP, "North");
	}

	private void createCP() {
		JPanel cP = new JPanel();
		ImageIcon hc = new ImageIcon("HMain.jpg");
		Image hm = hc.getImage(); // �̹����������� �̹����� ����
		hm = hm.getScaledInstance(700, 460, Image.SCALE_SMOOTH); // �̹��� ������ ����
		hc = new ImageIcon(hm); // �ٽ� �̹������������� ����

		JLabel labelImage = new JLabel(hc);
		cP.add(labelImage);
		this.add(cP, "Center");
	}

	public void setMsg(String msg) {
		System.out.println("HMain�Դ�?");
		j.idChk(msg);
	}
	
	public static void main(String[] args) {
//		new HMain();
	}


}
