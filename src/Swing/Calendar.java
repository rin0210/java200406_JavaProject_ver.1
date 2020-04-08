package Swing;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Calendar extends JFrame {
	private JLabel mainLabel, chkInLable, chkOutLabel, dayLabel, adultLabel, kidLabel;
	private JTextField textField, textField_1, textField_2;
	private JButton button, button_1, button_2, button_3;
	private JComboBox comboBox, comboBox_1;

	public Calendar() {
		getContentPane().setLayout(null); // ��ġ������ ����
		this.setBounds(0, 0, 620, 370);
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
		mainLabel.setBounds(124, 36, 344, 37);
		this.add(mainLabel);

		chkInLable = new JLabel("üũ�� ��¥");
		chkInLable.setFont(new Font("����", Font.PLAIN, 13));
		chkInLable.setHorizontalAlignment(JLabel.CENTER); 
		chkInLable.setBounds(32, 109, 92, 27);
		this.add(chkInLable);

		chkOutLabel = new JLabel("üũ�ƿ� ��¥");
		chkOutLabel.setFont(new Font("����", Font.PLAIN, 13));
		chkOutLabel.setHorizontalAlignment(JLabel.CENTER); 
		chkOutLabel.setBounds(190, 109, 92, 27);
		this.add(chkOutLabel);

		dayLabel = new JLabel("��");
		dayLabel.setFont(new Font("����", Font.PLAIN, 13));
		dayLabel.setBounds(390, 141, 37, 37);
		this.add(dayLabel);

		adultLabel = new JLabel("��");
		adultLabel.setFont(new Font("����", Font.PLAIN, 13));
		adultLabel.setBounds(465, 141, 37, 37);
		this.add(adultLabel);

		kidLabel = new JLabel("��");
		kidLabel.setFont(new Font("����", Font.PLAIN, 13));
		kidLabel.setBounds(540, 141, 37, 37);
		this.add(kidLabel);
	}

	// �ؽ�Ʈ�ʵ� ����
	private void txtSetting() {
		textField = new JTextField();
		textField.setBounds(32, 141, 146, 37);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(190, 141, 146, 37);
		getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setBounds(348, 141, 37, 37);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

	}

	// ��ư ����
	private void buttonSetting() {
		button = new JButton();
		button.setBounds(119, 104, 37, 37);
		this.add(button);

		button_1 = new JButton();
		button_1.setBounds(279, 104, 37, 37);
		this.add(button_1);

		button_2 = new JButton("�˻�");
		button_2.setFont(new Font("����", Font.PLAIN, 14));
		button_2.setHorizontalAlignment(JButton.CENTER);
		button_2.setBounds(218, 218, 146, 37);
		this.add(button_2);

		button_3 = new JButton("New button");
		button_3.setBounds(12, 299, 97, 23);
		this.add(button_3);

	}

	// �޺��ڽ� ����
	private void comboSetting() {
		comboBox = new JComboBox();
		comboBox.setBounds(423, 141, 37, 37);
		getContentPane().add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(498, 141, 37, 37);
		getContentPane().add(comboBox_1);

	}

	public static void main(String[] args) {
		new Calendar();
	}

}
