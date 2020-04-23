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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Client.CConnect;
import JDBC.DAOCenter;

public class HMain extends JFrame {
	private CConnect cc = null;
	private Choice c = null;
	private Join j = null;
	private Login l = null;
	private JButton loginBtn, joinBtn, nonMBtn;
	private String id = null;

	// �̱���
	private static HMain single = null;

	public static HMain getInstance() {
		if (single == null) {
			single = new HMain();
		}
		return single;
	}

	public void admin(CConnect cc) {
		this.cc = cc;
	}

	private HMain() {
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 700, 600);
		setLocationRelativeTo(null); // ������â�� ����ȭ�� �Ѱ�� �����

		createCP();
		createNP();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

		loginBtn = new JButton("�α���");
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
				if (loginBtn.getText().equals("�α���")) {
					l = new Login(cc);
					visible(2);
				} else if (loginBtn.getText().equals("�α׾ƿ�")) {
					cc.send("/logout");
					chkMsg();
				}
			}
		});

//		loginBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				l = new Login(cc);
//				dispose();
//			}
//		});

		joinBtn = new JButton("ȸ������");
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
				if (loginChk()) {
					JOptionPane.showMessageDialog(null, "�̹� �α��� �ϼ̽��ϴ�.\n�α׾ƿ� �Ͻ� �� �̿����ּ���.", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					j = new Join(cc);
					visible(2);
				}
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

		nonMBtn = new JButton("����");
		nonMBtn.setFont(new Font("����", Font.PLAIN, 12));
		nonMBtn.setBorderPainted(false);
		nonMBtn.setContentAreaFilled(false);
		nonMBtn.setFocusPainted(false);
		loginP.add(nonMBtn);
		nP.add(loginP, "North");

		nonMBtn.addMouseListener(new MouseListener() { // ���� ��ư

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
				c = new Choice(cc, id);
				visible(2);
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
		ImageIcon hc = new ImageIcon("pic_1.png");
		Image hm = hc.getImage(); // �̹����������� �̹����� ����
		hm = hm.getScaledInstance(700, 460, Image.SCALE_SMOOTH); // �̹��� ������ ����
		hc = new ImageIcon(hm); // �ٽ� �̹������������� ����

		JLabel labelImage = new JLabel(hc);
		cP.add(labelImage);
		this.add(cP, "Center");
	}

	public void setLogout() {
		loginBtn.setText("�α׾ƿ�");
	}

	public void chkMsg() {
		String msg = cc.receive();
		if (msg.indexOf("/logout") > -1) {
			JOptionPane.showMessageDialog(null, "���������� �α׾ƿ� �Ǿ����ϴ�.", "Message", JOptionPane.INFORMATION_MESSAGE);
			loginBtn.setText("�α���");
		}
	}

	// �α��� ���� üũ
	public boolean loginChk() {
		if (loginBtn.getText().equals("�α׾ƿ�")) {
			return true; // ȸ��
		}
		return false; // ��ȸ��
	}

	public void setId(String id) {
		this.id = id;
	}

	public void visible(int a) {
		if (a == 1) {
			this.setVisible(true);
		} else if (a == 2) {
			this.setVisible(false);
		}
	}

}
