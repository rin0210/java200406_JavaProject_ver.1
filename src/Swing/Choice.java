package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Client.CConnect;

public class Choice extends JFrame {
	private CConnect cc = null;
	private Reservation rv = null;
	private HMain hm = null;
	private Confirm cf = null;
	private String id = null;

	public Choice(CConnect cc, String id) {
		this.cc = cc;
		this.id = id;
		hm = HMain.getInstance();

		this.setLayout(null); // ��ġ������ ����
		this.setBounds(0, 0, 280, 300);
		setLocationRelativeTo(null); // ����ȭ�� �Ѱ�� ����

		setting();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void setting() {
		JButton labelBooking = new JButton("ȸ�� ����");
		labelBooking.setFont(new Font("����", Font.BOLD, 15));
		JButton labelNon = new JButton("��ȸ�� ����");
		labelNon.setFont(new Font("����", Font.BOLD, 15));
		JButton labelConfirm = new JButton("���� Ȯ��");
		labelConfirm.setFont(new Font("����", Font.BOLD, 15));

		labelBooking.setBorderPainted(false);
		labelBooking.setContentAreaFilled(false);
		labelBooking.setFocusPainted(false);

		labelNon.setBorderPainted(false);
		labelNon.setContentAreaFilled(false);
		labelNon.setFocusPainted(false);

		labelConfirm.setFocusPainted(false);
		labelConfirm.setContentAreaFilled(false);
		labelConfirm.setBorderPainted(false);

		labelBooking.setHorizontalAlignment(JButton.LEFT);
		labelNon.setHorizontalAlignment(JButton.LEFT);
		labelConfirm.setHorizontalAlignment(JButton.LEFT);

		labelBooking.setBounds(93, 40, 120, 40);
		labelNon.setBounds(93, 95, 120, 40);
		labelConfirm.setBounds(93, 150, 120, 40);

		this.add(labelBooking);
		this.add(labelNon);
		this.add(labelConfirm);

		labelBooking.addMouseListener(new MouseListener() { // ȸ�� ���� ��ư

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				labelBooking.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				labelBooking.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (hm.loginChk() == true) {
					rv = new Reservation(cc, id, "mem");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "���� �α��� ���ֽñ� �ٶ��ϴ�.", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		labelNon.addMouseListener(new MouseListener() { // ��ȸ�� ���� ��ư

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				labelNon.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				labelNon.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (hm.loginChk() == false) {
					rv = new Reservation(cc, id, "non");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "�̹� �α��� �ϼ̽��ϴ�.\nȸ�� ���� ��ư�� �����ּ���.", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		labelConfirm.addMouseListener(new MouseListener() { // ���� Ȯ�� ��ư

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				labelConfirm.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				labelConfirm.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				cf = new Confirm(cc, id);
				dispose();
			}
		});

		ImageIcon hc1 = new ImageIcon("Chk1.jpg");
		Image hm1 = hc1.getImage(); // �̹����������� �̹����� ����
		hm1 = hm1.getScaledInstance(33, 33, Image.SCALE_SMOOTH); // �̹��� ������ ����
		hc1 = new ImageIcon(hm1); // �ٽ� �̹������������� ����

		JLabel labelImage1 = new JLabel(hc1);
		labelImage1.setBounds(65, 42, 33, 33);

		ImageIcon hc2 = new ImageIcon("Chk1.jpg");
		Image hm2 = hc2.getImage();
		hm2 = hm2.getScaledInstance(33, 33, Image.SCALE_SMOOTH);
		hc2 = new ImageIcon(hm2);

		JLabel labelImage2 = new JLabel(hc2);
		labelImage2.setBounds(65, 97, 33, 33);

		ImageIcon hc3 = new ImageIcon("Chk1.jpg");
		Image hm3 = hc3.getImage();
		hm3 = hm3.getScaledInstance(33, 33, Image.SCALE_SMOOTH);
		hc3 = new ImageIcon(hm3);

		JLabel labelImage3 = new JLabel(hc3);
		labelImage3.setBounds(65, 152, 33, 33);

		this.add(labelImage1);
		this.add(labelImage2);
		this.add(labelImage3);

		JButton beforeBtn = new JButton("< ����");
		beforeBtn.setFont(new Font("����", Font.PLAIN, 12));
		beforeBtn.setBorderPainted(false);
		beforeBtn.setContentAreaFilled(false);
		beforeBtn.setFocusPainted(false);
		beforeBtn.setBounds(5, 220, 70, 30);
		this.add(beforeBtn);

		beforeBtn.addMouseListener(new MouseListener() { // ������ ��ư

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				beforeBtn.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				beforeBtn.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				hm.visible(1);
				dispose();
			}
		});
	}

//	public static void main(String[] args) {
//		new Choice();
//	}

}
