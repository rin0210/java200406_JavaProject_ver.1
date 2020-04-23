package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Client.CConnect;
import JDBC.BDTO;

public class Confirm extends JFrame {
	private CConnect cc = null;
	private HMain hm = null;

	private JList<String> list;
	private JLabel label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8;
	private JLabel txt_1, txt_2, txt_3, txt_4, txt_5, txt_6;
	private JLabel nonLabel, noNumLabel;
	private JTextField nonTxt;
	private JButton outBtn, confirmBtn, nonNumBtn;
	private JScrollPane scrollPane;

	private DefaultListModel<String> listModel = new DefaultListModel<>();

	private Object o = null;
	private ArrayList<Object> getObjList = null;
	private BDTO bt = null;
	private ArrayList<BDTO> getBookingList = new ArrayList<>();

	private String chk = null; // ȸ������ ��ȸ������
	private String nonReNum = null; // ��ȸ���� �����ȣ
	private String id = null;

	public Confirm(CConnect cc, String id) {
//	public Confirm() {
		super("����Ȯ��");
		this.cc = cc;
		this.id = id;
		System.out.println("�� ���̵��? " + id);
		hm = HMain.getInstance();

		txtSetting();
		buttonSetting();
		getList();
		loginChk();

		this.setLayout(null); // ��ġ������ ����
		this.setBounds(0, 0, 335, 525);
		setLocationRelativeTo(null); // ����ȭ�� �Ѱ�� ����

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	// ���� ��� �ҷ���
	private void getList() {
		cc.send("/myBooking");
		o = cc.receiveObject();
		getObjList = (ArrayList<Object>) o;
		System.out.println(o);

		for (Object o : getObjList) {
			bt = (BDTO) o;
			getBookingList.add(bt);
		}
		System.out.println("������ �����Դ�? " + getBookingList);
	}

	// ȸ�� ���� Ȯ��
	private void loginChk() {
		if (hm.loginChk() == true) {
			chk = "mem"; // ȸ��
			mem();
			labelSetting();
			confirmBtn.setVisible(true);

		} else {
			chk = "non"; // ��ȸ��
			non();
		}

	}

	private void setInfo() {
		if (chk.equals("mem")) { // ȸ��
			for (BDTO b : getBookingList) {
				if (list.getSelectedValue().equals(b.getReNum())) {
					txt_1.setText(b.getReNum());
					txt_2.setText(b.getName());
					txt_3.setText(b.getChkIn());
					txt_4.setText(b.getChkOut());
					txt_5.setText(String.valueOf(b.getNight()) + "��");
					txt_6.setText(String.valueOf(b.getPrice()) + "��");
				}
			}
		} else { // ��ȸ��
			for (BDTO b : getBookingList) {
				if (nonReNum.equals(b.getReNum())) {
					txt_1.setText(b.getReNum());
					txt_2.setText(b.getName());
					txt_3.setText(b.getChkIn());
					txt_4.setText(b.getChkOut());
					txt_5.setText(String.valueOf(b.getNight()) + "��");
					txt_6.setText(String.valueOf(b.getPrice()) + "��");
				}
			}
		}
	}

	// ȸ��
	private void mem() {
		list = new JList<String>(listModel);
		list.setFont(new Font("Serif", Font.PLAIN, 14));
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // ����Ʈ ��輱 ����
//		list.setBounds(0, 0, 350, 70);
		list.setCellRenderer(new DefaultListCellRenderer() {
			public int getHorizontalAlignment() {
				return CENTER; // ����Ʈ �� ��� ����
			}
		});

		scrollPane = new JScrollPane(list); // ��ũ�� �߰�
		scrollPane.setBounds(0, 30, 320, 70);
		this.add(scrollPane);

		System.out.println("������ �����°� �´�? " + getBookingList.size());

		for (int i = 0; i < getBookingList.size(); i++) {
			if (id.equals(getBookingList.get(i).getId())) {
				listModel.addElement(getBookingList.get(i).getReNum());
				System.out.println("�� �����ȣ :" + getBookingList.get(i).getReNum());
			} else {
				
			}
		}
		list.addListSelectionListener(new ListSelectionListener() { // ����Ʈ �׸� ���� ��

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedIndex() != -1) { // �����ϸ�
					System.out.println(list.getSelectedIndex());
					setInfo();
				}
			}
		});
	}

	// ��ȸ��
	private void non() {
		nonLabel = new JLabel("�����ȣ 10�ڸ� ���ڸ� �Է��ϼ���.");
		nonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nonLabel.setFont(new Font("����", Font.PLAIN, 14));
		nonLabel.setBounds(40, 150, 240, 25);
		this.add(nonLabel);

		nonTxt = new JTextField();
		nonTxt.setHorizontalAlignment(SwingConstants.CENTER);
		nonTxt.setFont(new Font("Serif", Font.BOLD, 14));
		nonTxt.setBounds(70, 190, 180, 37);
		this.add(nonTxt);

		nonTxt.addKeyListener(new KeyAdapter() { // �����ȣ ���ڼ� ����
			public void keyTyped(KeyEvent k) {
				JTextField src = (JTextField) k.getSource();
				if (src.getText().length() >= 10) {
					k.consume();
				}
			}
		});

		noNumLabel = new JLabel();
		noNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		noNumLabel.setFont(new Font("����", Font.PLAIN, 12));
		noNumLabel.setForeground(Color.RED);
		noNumLabel.setBounds(35, 240, 250, 25);
		this.add(noNumLabel);

		nonNumBtn = new JButton("Ȯ��");
		nonNumBtn.setFont(new Font("����", Font.PLAIN, 14));
		nonNumBtn.setFocusPainted(false);
		nonNumBtn.setBounds(125, 415, 70, 30);
		this.add(nonNumBtn);
//		nonNumBtn.setVisible(true);

		nonNumBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (BDTO b : getBookingList) {
					if (nonTxt.getText().equals(b.getReNum())) { // �Է��� ��ȣ�� ������ ����߿� ���������
						nonReNum = nonTxt.getText();
						nonLabel.setVisible(false);
						nonTxt.setVisible(false);
						noNumLabel.setVisible(false);
						nonNumBtn.setVisible(false);
						labelSetting();
						label_7.setVisible(false);
						label_8.setVisible(true);
						confirmBtn.setVisible(true);
						setInfo();

					} else if (!nonTxt.getText().equals(b.getReNum())) {
						noNumLabel.setText("*�Է��Ͻ� �����ȣ�� �������� �ʽ��ϴ�.");
					}
				}
			}
		});
	}

	private void labelSetting() {
		label_1 = new JLabel("�����ȣ :");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("����", Font.PLAIN, 14));
		label_1.setBounds(60, 130, 85, 25);
		this.add(label_1);

		label_2 = new JLabel("��      �� :");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("����", Font.PLAIN, 14));
		label_2.setBounds(60, 175, 85, 25);
		this.add(label_2);

		label_3 = new JLabel("ü ũ �� :");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("����", Font.PLAIN, 14));
		label_3.setBounds(60, 220, 85, 25);
		this.add(label_3);

		label_4 = new JLabel("üũ�ƿ� :");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("����", Font.PLAIN, 14));
		label_4.setBounds(60, 265, 85, 25);
		this.add(label_4);

		label_5 = new JLabel("���ڱⰣ :");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("����", Font.PLAIN, 14));
		label_5.setBounds(60, 310, 85, 25);
		this.add(label_5);

		label_6 = new JLabel("��      �� :");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("����", Font.PLAIN, 14));
		label_6.setBounds(60, 355, 85, 25);
		this.add(label_6);

		label_7 = new JLabel("*�����ȣ�� �����Ͻð� Ȯ���ϼ���.");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("����", Font.PLAIN, 12));
		label_7.setForeground(Color.BLUE);
		label_7.setBounds(5, 5, 200, 25);
		this.add(label_7);
		label_7.setVisible(true);

		label_8 = new JLabel("����Ȯ��");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("����", Font.BOLD, 18));
		label_8.setBounds(58, 65, 200, 25);
		this.add(label_8);
		label_8.setVisible(false);
	}

	private void txtSetting() {
		txt_1 = new JLabel("");
		txt_1.setHorizontalAlignment(SwingConstants.CENTER);
		txt_1.setFont(new Font("����", Font.BOLD, 14));
		txt_1.setHorizontalAlignment(JTextField.LEFT);
		txt_1.setBounds(150, 130, 120, 25);
		this.add(txt_1);

		txt_2 = new JLabel("");
		txt_2.setHorizontalAlignment(SwingConstants.CENTER);
		txt_2.setFont(new Font("����", Font.BOLD, 14));
		txt_2.setHorizontalAlignment(JTextField.LEFT);
		txt_2.setBounds(150, 175, 120, 25);
		this.add(txt_2);

		txt_3 = new JLabel("");
		txt_3.setHorizontalAlignment(SwingConstants.CENTER);
		txt_3.setFont(new Font("����", Font.BOLD, 14));
		txt_3.setHorizontalAlignment(JTextField.LEFT);
		txt_3.setBounds(150, 220, 120, 25);
		this.add(txt_3);

		txt_4 = new JLabel("");
		txt_4.setHorizontalAlignment(SwingConstants.CENTER);
		txt_4.setFont(new Font("����", Font.BOLD, 14));
		txt_4.setHorizontalAlignment(JTextField.LEFT);
		txt_4.setBounds(150, 265, 120, 25);
		this.add(txt_4);

		txt_5 = new JLabel("");
		txt_5.setHorizontalAlignment(SwingConstants.CENTER);
		txt_5.setFont(new Font("����", Font.BOLD, 14));
		txt_5.setHorizontalAlignment(JTextField.LEFT);
		txt_5.setBounds(150, 310, 120, 25);
		this.add(txt_5);

		txt_6 = new JLabel("");
		txt_6.setHorizontalAlignment(SwingConstants.CENTER);
		txt_6.setFont(new Font("����", Font.BOLD, 14));
		txt_6.setHorizontalAlignment(JTextField.LEFT);
		txt_6.setBounds(150, 355, 120, 25);
		this.add(txt_6);

	}

	private void buttonSetting() {
		outBtn = new JButton("< ����");
		outBtn.setFont(new Font("����", Font.PLAIN, 12));
		outBtn.setBorderPainted(false);
		outBtn.setContentAreaFilled(false);
		outBtn.setFocusPainted(false);
		outBtn.setBounds(5, 445, 70, 30);
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
				hm.visible(1);
				dispose();
			}
		});

		confirmBtn = new JButton("Ȯ��");
		confirmBtn.setFont(new Font("����", Font.PLAIN, 14));
		confirmBtn.setFocusPainted(false);
		confirmBtn.setBounds(125, 415, 70, 30);
		this.add(confirmBtn);
		confirmBtn.setVisible(false);
		
		confirmBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hm.visible(1);
				dispose();
			}
		});

	}

//	public static void main(String[] args) {
//		new Confirm();
//	}
}
