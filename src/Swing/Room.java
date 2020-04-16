package Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import Client.CConnect;
import JDBC.RDTO;

public class Room extends JFrame {
	private String header[] = { "��������", "����", "���", "�󼼺���" };

	private DefaultTableModel tableModel = new DefaultTableModel(null, header);
	private JTable table = new JTable(tableModel);
	private JTableHeader th = table.getTableHeader();
	private JScrollPane tableScroll = new JScrollPane(table);

	private JPanel wholeTab = new JPanel();
	private JPanel underTab = new JPanel();

	private JButton outBtn;

	private CConnect cc = null;
	private ArrayList<String[]> roomList = null;
	private String[] in = new String[3];

	public Room(CConnect cc, ArrayList<String[]> roomList) {
		super("���ǿ���");
		this.cc = cc;
		this.roomList = roomList;

		th.setPreferredSize(new Dimension(0, 50)); // ��� ������ ����
		th.setFont(new Font("����", Font.PLAIN, 14));

		this.setBounds(0, 0, 500, 400);
		setLocationRelativeTo(null); // ����ȭ�� �Ѱ�� ����

		init();
		setting();
		createWholeTab();
		createUnderTab();

		this.add(th);
		this.add(tableScroll);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void init() {
		for (int i = 0; i < roomList.size(); i++) {
			tableModel.addRow(roomList.get(i));
		}
	}

	private void createUnderTab() {
		underTab.setLayout(new FlowLayout(FlowLayout.LEFT));
		outBtn = new JButton("< ����");
		outBtn.setFont(new Font("����", Font.PLAIN, 12));
		outBtn.setBorderPainted(false);
		outBtn.setContentAreaFilled(false);
		outBtn.setFocusPainted(false);
		underTab.add(outBtn);
		this.add(underTab, "South");

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
				dispose();
			}
		});
	}

	private void createWholeTab() {
		wholeTab.add(tableScroll);
		this.add(wholeTab, "Center");
	}

	private void setting() {
		table.setRowHeight(50); // �� ���� ����
		table.getTableHeader().setReorderingAllowed(false); // ���̺� �÷��� �̵��� ����, ���콺�巡�׷� �Ժη� �̵����� ���ϰ�
		table.setShowVerticalLines(false); // �� ���̿� ���μ��� �׸��� ����
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // ���̺� ���� �Ѱ��� ������ �� ����

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
