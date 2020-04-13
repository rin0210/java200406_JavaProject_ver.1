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

import Client.CConnect;

public class Choice extends JFrame {
	private CConnect cc = null;
	private Reservation rv = null;

	public Choice(CConnect cc) {
		this.cc = cc;
		this.setLayout(null); // ��ġ������ ����
		this.setBounds(0, 0, 250, 280);
		setLocationRelativeTo(null); // ����ȭ�� �Ѱ�� ����

		setting();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void setting() {
		JButton labelBooking = new JButton("���� ����");
		labelBooking.setFont(new Font("����", Font.BOLD, 15));
		JButton labelConfirm = new JButton("���� Ȯ��");
		labelConfirm.setFont(new Font("����", Font.BOLD, 15));

		labelBooking.setBorderPainted(false);
		labelBooking.setContentAreaFilled(false);
		labelBooking.setFocusPainted(false);

		labelConfirm.setBorderPainted(false);
		labelConfirm.setContentAreaFilled(false);
		labelConfirm.setFocusPainted(false);

		labelBooking.setBounds(72, 60, 120, 40);
		labelConfirm.setBounds(72, 120, 120, 40);

		this.add(labelBooking);
		this.add(labelConfirm);

		labelBooking.addMouseListener(new MouseListener() { // ���� ���� ��ư

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
				rv = new Reservation(cc);
				dispose();
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

			}
		});

		ImageIcon hc1 = new ImageIcon("Chk1.jpg");
		Image hm1 = hc1.getImage(); // �̹����������� �̹����� ����
		hm1 = hm1.getScaledInstance(35, 35, Image.SCALE_SMOOTH); // �̹��� ������ ����
		hc1 = new ImageIcon(hm1); // �ٽ� �̹������������� ����

		JLabel labelImage1 = new JLabel(hc1);
		labelImage1.setBounds(50, 60, 35, 35);

		ImageIcon hc2 = new ImageIcon("Chk1.jpg");
		Image hm2 = hc2.getImage();
		hm2 = hm2.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		hc2 = new ImageIcon(hm2);

		JLabel labelImage2 = new JLabel(hc2);
		labelImage2.setBounds(50, 120, 35, 35);

		this.add(labelImage1);
		this.add(labelImage2);
	}

//	public static void main(String[] args) {
//		new Choice();
//	}

}
