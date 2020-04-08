package Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Client.CConnect;

public class HMain extends JFrame {
	private CConnect cc = null;
	private Join j;
	private Login l;

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
		loginBtn.setBorderPainted(false); // ��ư �׵θ��� ����
		loginBtn.setContentAreaFilled(false); // ��ư ���� ��� ǥ�� ����
		loginBtn.setFocusPainted(false); // ��Ŀ�� ǥ�� ����
		loginP.add(loginBtn);
		
		loginBtn.addMouseListener(new MouseListener() { // �α��� ��ư

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				loginBtn.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				loginBtn.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				l = new Login(cc);
				dispose();
			}
		});

//		loginBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				l = new Login(cc);
//				dispose();
//
//			}
//		});

		JButton joinBtn = new JButton("ȸ������");
		joinBtn.setFont(new Font("����", Font.PLAIN, 12));
		joinBtn.setBorderPainted(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setFocusPainted(false);
		loginP.add(joinBtn);
		
		joinBtn.addMouseListener(new MouseListener() { // ȸ������ ��ư

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				joinBtn.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				joinBtn.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				j = new Join(cc);
				dispose();
			}
		});

//		joinBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				j = new Join(cc);
//				dispose();
//			}
//		});

		JButton nonMBtn = new JButton("��ȸ������");
		nonMBtn.setFont(new Font("����", Font.PLAIN, 12));
		nonMBtn.setBorderPainted(false);
		nonMBtn.setContentAreaFilled(false);
		nonMBtn.setFocusPainted(false);
		loginP.add(nonMBtn);
		nP.add(loginP, "North");
		
		nonMBtn.addMouseListener(new MouseListener() { // ��ȸ������ ��ư

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				nonMBtn.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				nonMBtn.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
//				new Choice(cc);
				dispose();
			}
		});

//		nonMBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
////				new Choice(cc);
//				dispose();
//			}
//		});

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
//		System.out.println("HMain�Դ�?");
		if (msg.indexOf("/join") == 0) {
			j.chkMsg(msg);
		} else if (msg.indexOf("/login") == 0) {
			l.chkMsg(msg);
		}
	}

	public static void main(String[] args) {
//		new HMain();
	}

}
