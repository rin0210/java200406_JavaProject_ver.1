package Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import Client.CConnect;
import JDBC.BDTO;

public class Room extends JFrame {
	private String header[] = { "��������", "����", "���" };
	private String[][] imsi = { { "��_1", "1", "1" }, { "��_2", "2", "2" }, { "��_3", "3", "3" }, { "��_4", "4", "4" },
			{ "��_5", "5", "5" }, { "��_6", "6", "6" }, { "��_7", "7", "7" }, { "��_8", "8", "8" }, { "��_9", "9", "9" },
			{ "��_10", "10", "10" } };

	private DefaultTableModel tableModel = new DefaultTableModel(null, header) {
		public boolean isCellEditable(int r, int c) { // ����Ŭ�� ������ ����
			return false;
		}
	};
	private JTable table = new JTable(tableModel);
	private JTableHeader th = table.getTableHeader();
	private JScrollPane tableScroll = new JScrollPane(table);

	private JButton outBtn, reserveBtn;
	private JLabel lookLabel, pic_1, pic_2, pic_3, pic_4, pic_5, viewLabel;
	private JLabel info_1, info_2, info_3, info_4, info_5, info_6, info_7, info_8;

	private ArrayList<String[]> roomList = null;
	private String[] in = new String[3];

	private int modIntRow = 0;
	private String room = null; // ���̸�
	private String price = null; // ����
	private int night = 0; // ������
	private String[] mine = null; /////

	private CConnect cc = null;
	private MBooking mb = null;
	private BBooking bb = null;
	private Reservation rv = null;
	private Room rm = null;
	private String chk = null; // ȸ������ ��ȸ������ üũ

	public Room(CConnect cc, Reservation rv, String chk, ArrayList<String[]> roomList, String night, String[] mine) {

//	public Room() {
		super("���ǿ���");
		this.cc = cc;
		this.rv = rv;
		this.chk = chk;
		this.roomList = roomList;
		this.night = Integer.valueOf(night);
		this.mine = mine; 
		this.rm = this;

		this.setLayout(null);
		th.setPreferredSize(new Dimension(0, 50)); // ��� ������ ����
		th.setFont(new Font("����", Font.PLAIN, 14));

		this.setBounds(0, 0, 800, 600);
		setLocationRelativeTo(null); // ����ȭ�� �Ѱ�� ����

		init();
		setting();
		createWholeTab();
		createUnderTab();
		createRigthTab();
		room_1();
		room_2();
		room_3();
		room_4();
		room_5();
		visible();

		this.add(th);
		this.add(tableScroll);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void visible() {
		pic_5.setVisible(false);
		pic_4.setVisible(false);
		pic_3.setVisible(false);
		pic_2.setVisible(false);
		pic_1.setVisible(false);
	}

	private void room_5() {
		ImageIcon hc = new ImageIcon("room_5.png");
		Image hm = hc.getImage(); // �̹����������� �̹����� ����
		hm = hm.getScaledInstance(302, 200, Image.SCALE_SMOOTH); // �̹��� ������ ����
		hc = new ImageIcon(hm); // �ٽ� �̹������������� ����

		pic_5 = new JLabel(hc);
		pic_5.setBounds(468, 50, 314, 200);
		this.add(pic_5);
	}

	private void room_4() {
		ImageIcon hc = new ImageIcon("room_4.png");
		Image hm = hc.getImage(); // �̹����������� �̹����� ����
		hm = hm.getScaledInstance(305, 235, Image.SCALE_SMOOTH); // �̹��� ������ ����
		hc = new ImageIcon(hm); // �ٽ� �̹������������� ����

		pic_4 = new JLabel(hc);
		pic_4.setBounds(470, 35, 308, 235);
		this.add(pic_4);
	}

	private void room_3() {
		ImageIcon hc = new ImageIcon("room_3.png");
		Image hm = hc.getImage(); // �̹����������� �̹����� ����
		hm = hm.getScaledInstance(430, 285, Image.SCALE_SMOOTH); // �̹��� ������ ����
		hc = new ImageIcon(hm); // �ٽ� �̹������������� ����

		pic_3 = new JLabel(hc);
		pic_3.setBounds(465, 50, 320, 200);
		this.add(pic_3);
	}

	private void room_2() {
		ImageIcon hc = new ImageIcon("room_2.png");
		Image hm = hc.getImage(); // �̹����������� �̹����� ����
		hm = hm.getScaledInstance(355, 270, Image.SCALE_SMOOTH); // �̹��� ������ ����
		hc = new ImageIcon(hm); // �ٽ� �̹������������� ����

		pic_2 = new JLabel(hc);
		pic_2.setBounds(473, 50, 304, 200);
		this.add(pic_2);
	}

	private void room_1() {
		ImageIcon hc = new ImageIcon("room_1.png");
		Image hm = hc.getImage(); // �̹����������� �̹����� ����
		hm = hm.getScaledInstance(304, 260, Image.SCALE_SMOOTH); // �̹��� ������ ����
		hc = new ImageIcon(hm); // �ٽ� �̹������������� ����

		pic_1 = new JLabel(hc);
		pic_1.setBounds(473, 50, 304, 200);
		this.add(pic_1);

	}

	private void init() {
		if (roomList != null) {
			for (int i = 0; i < roomList.size(); i++) {
				tableModel.addRow(roomList.get(i));
			}
		}
	}

	private void createRigthTab() {
		lookLabel = new JLabel("�� ����");
		lookLabel.setFont(new Font("����", Font.PLAIN, 14));
		lookLabel.setBounds(586, 0, 70, 50);
		this.add(lookLabel);
		lookLabel.setVisible(false);

		reserveBtn = new JButton("�����ϱ�");
		reserveBtn.setFont(new Font("����", Font.PLAIN, 14));
		reserveBtn.setFocusPainted(false);
		reserveBtn.setBounds(570, 504, 100, 37);
		this.add(reserveBtn);
		reserveBtn.setVisible(false);

		reserveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				room = ((String) table.getValueAt(modIntRow, 0));
				price = ((String) table.getValueAt(modIntRow, 2));
				int totalPrice = Integer.valueOf(price) * night;

				mine[1] = room + " ";
				mine[6] = totalPrice + " ";

				cc.send("/getMyInfo");

				if (chk.equals("mem")) {
					mb = new MBooking(cc, rm, room, totalPrice, mine);
					dispose();
				} else if (chk.equals("non")) {
					bb = new BBooking(cc, rm, room, totalPrice, mine);
					dispose();
				}
			}
		});

		ImageIcon hc = new ImageIcon("pic_3.png");
		Image hm = hc.getImage(); // �̹����������� �̹����� ����
		hm = hm.getScaledInstance(304, 215, Image.SCALE_SMOOTH); // �̹��� ������ ����
		hc = new ImageIcon(hm); // �ٽ� �̹������������� ����

		viewLabel = new JLabel(hc);
		viewLabel.setBounds(473, 52, 304, 200);
		this.add(viewLabel);
		viewLabel.setVisible(true);

		info_1 = new JLabel("Hotel Info.");
		info_1.setFont(new Font("����", Font.BOLD, 14));
		info_1.setBounds(585, 288, 150, 50);
		this.add(info_1);

		info_2 = new JLabel("�� üũ��/üũ�ƿ� �ð�");
		info_2.setFont(new Font("����", Font.BOLD, 12));
		info_2.setBounds(555, 328, 150, 50);
		this.add(info_2);

		info_3 = new JLabel("- üũ�� : ���� 2�� ����");
		info_3.setFont(new Font("����", Font.PLAIN, 12));
		info_3.setBounds(555, 348, 150, 50);
		this.add(info_3);

		info_4 = new JLabel("- üũ�ƿ� : ���� 11��");
		info_4.setFont(new Font("����", Font.PLAIN, 12));
		info_4.setBounds(555, 368, 150, 50);
		this.add(info_4);

		info_5 = new JLabel("�� ���� ������� �ȳ�");
		info_5.setFont(new Font("����", Font.BOLD, 12));
		info_5.setBounds(555, 398, 150, 50);
		this.add(info_5);

		info_6 = new JLabel("- ���� 6�� ~ ���� 11��");
		info_6.setFont(new Font("����", Font.PLAIN, 12));
		info_6.setBounds(555, 418, 150, 50);
		this.add(info_6);

		info_7 = new JLabel("(���߹���)");
		info_7.setFont(new Font("����", Font.PLAIN, 12));
		info_7.setBounds(590, 438, 150, 50);
		this.add(info_7);

		info_8 = new JLabel(""); // �뱸��
		info_8.setFont(new Font("����", Font.PLAIN, 12));
		info_8.setBounds(475, 240, 300, 50);
		this.add(info_8);
	}

	private void createUnderTab() {
		outBtn = new JButton("< ����");
		outBtn.setFont(new Font("����", Font.PLAIN, 12));
		outBtn.setBorderPainted(false);
		outBtn.setContentAreaFilled(false);
		outBtn.setFocusPainted(false);
		outBtn.setBounds(5, 522, 70, 30);
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
				rv.setVisible(true);
				dispose();
			}
		});
	}

	private void createWholeTab() {
//		wholeTab.add(tableScroll);
//		this.add(tableScroll, "West");
		tableScroll.setBounds(0, 0, 467, 502);
		this.add(tableScroll);
	}

	private void setting() {
		table.setRowHeight(50); // �� ���� ����
		table.getTableHeader().setReorderingAllowed(false); // ���̺� �÷��� �̵��� ����, ���콺�巡�׷� �Ժη� �̵����� ���ϰ�
		table.setShowVerticalLines(false); // �� ���̿� ���μ��� �׸��� ����
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // ���̺� ���� �Ѱ��� ������ �� ����

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) { // ���콺 Ŭ����
					viewLabel.setVisible(false);
					lookLabel.setVisible(true);
					reserveBtn.setVisible(true);
					visible();

					modIntRow = table.getSelectedRow();
					if (((String) table.getValueAt(modIntRow, 0)).equals("��_1")
							|| ((String) table.getValueAt(modIntRow, 0)).equals("��_2")) {
						pic_1.setVisible(true);
						info_8.setText("[�뱸��] ħ��1(�̱�), ���1, ȭ���1");
					} else if (((String) table.getValueAt(modIntRow, 0)).equals("��_3")
							|| ((String) table.getValueAt(modIntRow, 0)).equals("��_4")) {
						pic_2.setVisible(true);
						info_8.setText("[�뱸��] ħ��1(Ʈ��), ���1, ȭ���1");
					} else if (((String) table.getValueAt(modIntRow, 0)).equals("��_5")
							|| ((String) table.getValueAt(modIntRow, 0)).equals("��_6")) {
						pic_3.setVisible(true);
						info_8.setText("[�뱸��] ħ��1(����/�̱�), ���1, ȭ���1");
					} else if (((String) table.getValueAt(modIntRow, 0)).equals("��_7")
							|| ((String) table.getValueAt(modIntRow, 0)).equals("��_8")) {
						pic_4.setVisible(true);
						info_8.setText("[�뱸��] ħ��2(����/Ʈ��), ���2, ȭ���2");
					} else if (((String) table.getValueAt(modIntRow, 0)).equals("��_9")
							|| ((String) table.getValueAt(modIntRow, 0)).equals("��_10")) {
						pic_5.setVisible(true);
						info_8.setText("[�뱸��] ħ��2(����/Ʈ��/�̱�), ���2, ȭ���2");
					}
				}
			}
		});

		DefaultTableCellRenderer ts = new DefaultTableCellRenderer(); // �� �ȿ� ���� �������� ������ ����
		ts.setHorizontalAlignment(SwingConstants.CENTER); // ���� ��µ� ������ ���� ����
		TableColumnModel tc = table.getColumnModel();
		for (int i = 0; i < tc.getColumnCount(); i++) {
			tc.getColumn(i).setCellRenderer(ts);
		}

	}

//	public static void main(String[] args) {
//		new Room();
//	}

}
