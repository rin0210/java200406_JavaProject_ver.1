package Swing;

import javax.swing.JFrame;

public class Room extends JFrame {

	public Room() {

		this.setLayout(null); // ��ġ������ ����
		this.setBounds(0, 0, 250, 280);
		setLocationRelativeTo(null); // ����ȭ�� �Ѱ�� ����

//		setting();

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

}
