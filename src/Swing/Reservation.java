package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Client.CConnect;

public class Reservation extends JFrame {
	private JLabel mainLabel, chkInLable, chkOutLabel, dayLabel, adultLabel, kidLabel, numLabel, numLabel_1,
			peopleChkLabel;
	private JTextField textField, textField_1, textField_2;
	private JButton button, button_1, button_2, button_3;
	private JComboBox<String> comboBox, comboBox_1;

	private Calendar calIn = Calendar.getInstance();
	private Calendar calOut = Calendar.getInstance();

	private CConnect cc = null;
	private CalendarData cd = null;
	private CompareDate cpd = null;
	private Reservation rv = null;

	private String chkInDay = null;
	private String chkOutDay = null;
	private String adultCombo = null;
	private String kidCombo = null;
	private String night = null;
	private String chk = null; // ȸ������ ��ȸ������ üũ
	private String id = null;

	public Reservation(CConnect cc, String id, String chk) {
		super("���ǿ���");
		this.cc = cc;
		this.rv = this;
		this.id = id;
		this.chk = chk;

		getContentPane().setLayout(null); // ��ġ������ ����
		this.setBounds(0, 0, 635, 370);
		setLocationRelativeTo(null); // ����ȭ�� �Ѱ�� ����

		labelSetting();
		txtSetting();
		buttonSetting();
		comboSetting();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	// �� ����
	private void labelSetting() {
		mainLabel = new JLabel("��¥ �� �ο� ����");
		mainLabel.setFont(new Font("����", Font.BOLD, 24));
		mainLabel.setHorizontalAlignment(JLabel.CENTER);
		mainLabel.setBounds(133, 36, 344, 37);
		this.add(mainLabel);

		chkInLable = new JLabel("üũ�� ��¥");
		chkInLable.setFont(new Font("����", Font.PLAIN, 13));
		chkInLable.setHorizontalAlignment(JLabel.CENTER);
		chkInLable.setBounds(44, 109, 90, 27);
		this.add(chkInLable);

		chkOutLabel = new JLabel("üũ�ƿ� ��¥");
		chkOutLabel.setFont(new Font("����", Font.PLAIN, 13));
		chkOutLabel.setHorizontalAlignment(JLabel.CENTER);
		chkOutLabel.setBounds(199, 109, 90, 27);
		this.add(chkOutLabel);

		adultLabel = new JLabel("����");
		adultLabel.setFont(new Font("����", Font.PLAIN, 13));
		adultLabel.setHorizontalAlignment(JLabel.CENTER);
		adultLabel.setBounds(423, 109, 55, 27);
		this.add(adultLabel);

		kidLabel = new JLabel("�Ƶ�");
		kidLabel.setFont(new Font("����", Font.PLAIN, 13));
		kidLabel.setHorizontalAlignment(JLabel.CENTER);
		kidLabel.setBounds(516, 109, 55, 27);
		this.add(kidLabel);

		dayLabel = new JLabel("��");
		dayLabel.setFont(new Font("����", Font.PLAIN, 13));
		dayLabel.setBounds(390, 141, 37, 37);
		this.add(dayLabel);

		numLabel = new JLabel("��");
		numLabel.setFont(new Font("����", Font.PLAIN, 13));
		numLabel.setBounds(483, 141, 37, 37);
		this.add(numLabel);

		numLabel_1 = new JLabel("��");
		numLabel_1.setFont(new Font("����", Font.PLAIN, 13));
		numLabel_1.setBounds(576, 141, 37, 37);
		this.add(numLabel_1);

		peopleChkLabel = new JLabel(" *���� �ο��� ���ΰ� �Ƶ��� ���� ��� ���Ͽ� �ִ� 5����� ���డ���մϴ�.");
		peopleChkLabel.setFont(new Font("����", Font.PLAIN, 12));
		peopleChkLabel.setHorizontalAlignment(JTextField.RIGHT);
		peopleChkLabel.setForeground(Color.BLUE);
		peopleChkLabel.setBounds(156, 290, 438, 30);
		getContentPane().add(peopleChkLabel);

	}

	// �ؽ�Ʈ�ʵ� ����
	private void txtSetting() {
		textField = new JTextField(); // üũ��
		textField.setBounds(32, 141, 146, 37);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setEnabled(false); // ����ڰ� ���Ƿ� ���� �Ұ��ϰ� ����
		this.add(textField);

		textField_1 = new JTextField(); // üũ�ƿ�
		textField_1.setBounds(190, 141, 146, 37);
		textField_1.setHorizontalAlignment(JTextField.CENTER);
		textField_1.setEnabled(false); // ����ڰ� ���Ƿ� ���� �Ұ��ϰ� ����
		this.add(textField_1);

		textField_2 = new JTextField(); // ��
		textField_2.setBounds(348, 141, 37, 37);
		textField_2.setHorizontalAlignment(JTextField.CENTER);
		textField_2.setEnabled(false); // ����ڰ� ���Ƿ� ���� �Ұ��ϰ� ����
		this.add(textField_2);

	}

	// ��ư ����
	private void buttonSetting() {
		ImageIcon icon_1 = new ImageIcon("calendar1.jpg"); // ����Ʈ �̹��� ������
		Image img_1 = icon_1.getImage(); // �̹����������� �̹����� ����
		img_1 = img_1.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // �̹��� ������ ����
		icon_1 = new ImageIcon(img_1); // �ٽ� �̹������������� ����

		ImageIcon icon_2 = new ImageIcon("calendar2.jpg"); // ��ư�� ���콺 �÷����� ��µǴ� �̹���������
		Image img_2 = icon_2.getImage();
		img_2 = img_2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		icon_2 = new ImageIcon(img_2);

		button = new JButton(icon_1);
		button.setRolloverIcon(icon_2); // ��ư�� ���콺 �÷����� ��µǴ� �̹���������
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBounds(125, 101, 37, 37);
		this.add(button);

		button.addActionListener(new ActionListener() { // üũ�� ��ư �޷� Ŭ����

			@Override
			public void actionPerformed(ActionEvent arg0) {
				lookCalendar("In");
			}
		});

		button_1 = new JButton(icon_1);
		button_1.setRolloverIcon(icon_2); // ��ư�� ���콺 �÷����� ��µǴ� �̹���������
		button_1.setBorderPainted(false);
		button_1.setContentAreaFilled(false);
		button_1.setFocusPainted(false);
		button_1.setBounds(287, 101, 37, 37);
		this.add(button_1);

		button_1.addActionListener(new ActionListener() { // üũ�ƿ� ��ư �޷� Ŭ����

			@Override
			public void actionPerformed(ActionEvent arg0) {
				lookCalendar("Out");

			}
		});

		button_2 = new JButton("�˻�");
		button_2.setFont(new Font("����", Font.PLAIN, 14));
		button_2.setHorizontalAlignment(JButton.CENTER);
		button_2.setFocusPainted(false);
		button_2.setBounds(232, 218, 146, 37);
		this.add(button_2);

		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || textField_1.getText().equals("")
						|| textField_2.getText().equals("") || comboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "�Է»����� ��� �������ּ���.", "Message", JOptionPane.INFORMATION_MESSAGE);
				} else {
					adultCombo = comboBox.getSelectedItem().toString(); // �޺��ڽ����� �����Ͱ� ��������
					kidCombo = comboBox_1.getSelectedItem().toString();

					int people = Integer.valueOf(adultCombo) + Integer.valueOf(kidCombo); // ��ü�ο�:����+�Ƶ�
					if (people > 5) {
						peopleChkLabel.setForeground(Color.RED);

					} else {
						String info = chkInDay + " " + chkOutDay + " " + adultCombo + " " + kidCombo;
						System.out.println(info);

						String[] mine = new String[7];

						mine[2] = String.valueOf(people) + " ";
						mine[3] = chkInDay + " ";
						mine[4] = chkOutDay + " ";
						mine[5] = night + " ";

						cpd = new CompareDate(cc, rv, chk, info, night, mine);
						dispose();
					}

				}
			}
		});

		button_3 = new JButton("< ����");
		button_3.setFont(new Font("����", Font.PLAIN, 12));
		button_3.setBorderPainted(false);
		button_3.setContentAreaFilled(false);
		button_3.setFocusPainted(false);
		button_3.setBounds(5, 290, 70, 30);
		this.add(button_3);

		button_3.addMouseListener(new MouseListener() { // ������ ��ư

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button_3.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				button_3.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new Choice(cc, id);
				dispose();
			}
		});
	}

	// �޺��ڽ� ����
	private void comboSetting() {
		String[] combo = new String[6];
		for (int i = 0; i <= 5; i++) {
			combo[i] = String.valueOf(i);
		}

		comboBox = new JComboBox<>(combo);
		comboBox.setBounds(423, 141, 55, 35);
		this.add(comboBox);

		comboBox_1 = new JComboBox<>(combo);
		comboBox_1.setBounds(516, 141, 55, 35);
		this.add(comboBox_1);

	}

	// �޷� ����
	private void lookCalendar(String s) {
		cd = new CalendarData(this, s);
	}

	// ������ ��¥ ����
	public void chkInOutDay(String b, String s, int y, int m, int d) { // b:��ü��¥, s:in/out����
		System.out.println(y + "/" + m + "/" + d);
		
		if (s.equals("In")) {
			textField.setText(b);
			calIn.set(y, m, d);

			String strFormat = "yyyy-MM-dd"; // cConnect�� �Ѱ��ֱ����ؼ� String���� ����ȯ��Ŵ
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			chkInDay = sdf.format(calIn.getTime());
			System.out.println("chkInDay: " + chkInDay);
			
		} else if (s.equals("Out")) {
			textField_1.setText(b);
			calOut.set(y, m, d);

			String strFormat = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			chkOutDay = sdf.format(calOut.getTime());
			System.out.println("chkOutDay: " + chkOutDay);
			
		}

		if (!textField.getText().equals("") && !textField_1.getText().equals("")) { // üũ�ξƿ� ��¥�� ��� �����ϸ�
			int compare = chkInDay.compareTo(chkOutDay); // üũ�ξƿ� ��¥ ���ϴ� ����
			System.out.println(compare);
			if (compare < 0) {
				long diffSec = (calOut.getTimeInMillis() - calIn.getTimeInMillis()) / 1000; // �� ��¥���� ���̸� 1000���� ������
																							// �ʴ����� ��ȯ
				long diffDay = diffSec / (24 * 60 * 60); // 1���� 24(�ð�)*60(��)*60(��)�̹Ƿ� �̸� ������ �Ϸ� ��ȯ��
				night = String.valueOf(diffDay);
				textField_2.setText(night);
			} else {
				JOptionPane.showMessageDialog(null, "��¥�� �ٽ� Ȯ�����ּ���.", "Message", JOptionPane.INFORMATION_MESSAGE);
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		}
	}

//	public static void main(String[] args) {
//		new Reservation(cc);
//	}

}
