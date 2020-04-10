package Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarData extends JFrame implements ActionListener {

	private JPanel NPanel, CPanel, SPanel, Panel_1, Panel_2, Panel_3;
	private JLabel yearLabel, monthLabel;
	private JLabel[] weeksLabel = new JLabel[7]; // ���� ��
	private String[] weeksName = { "��", "��", "ȭ", "��", "��", "��", "��" };
	private JButton[] calBtn = new JButton[42]; // �޷� ���� ��ư
	private JButton beforeBtn, nextBtn, okBtn;
	private int year, month, day, thisYear, thisMonth = 0;
	private int beforeDay_1;

	private Reservation r = null;
	private String s;
	private String a, b;

	Calendar today, calendar;

	public CalendarData(Reservation r, String s) {
		super("��¥����");
		this.r = r;
		this.s = s;
		this.setBounds(0, 0, 360, 360);
		setLocationRelativeTo(null); // ������â�� ����ȭ�� �Ѱ�� �����

		CPanel = new JPanel(); // �޷� ��ü ��
		CPanel.setLayout(new GridLayout(7, 7));
		this.add(CPanel, BorderLayout.CENTER);

		addGrid();
		topSetting();
		calendarSetting();
		laoutSetting();

		beforeBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		okBtn.addActionListener(this);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	// �׸��巹�̾ƿ��� �� CPanel�� ���϶󺧰� ��¥��ư�� �־���
	private void addGrid() {
		for (int i = 0; i < weeksName.length; i++) { // 7��
			CPanel.add(weeksLabel[i] = new JLabel(weeksName[i])); // ���϶��� �־��ش�.
			weeksLabel[i].setHorizontalAlignment(JLabel.CENTER);
		}

		for (int i = 0; i < calBtn.length; i++) { // 42��
			CPanel.add(calBtn[i] = new JButton("")); // ��¥��ư�� �־��ش�.
			calBtn[i].setFont(new Font("����", Font.PLAIN, 13));
			calBtn[i].setBorderPainted(false);
			calBtn[i].setContentAreaFilled(false);
		}
	}

	private void calendarSetting() {
		// Ķ���� ��¥ ��������
		// calendar: �׷����� Ķ����
		calendar.set(Calendar.YEAR, year); // ����
		calendar.set(Calendar.MONTH, month - 1); // �̹���(���� -1�� ����� �ش���� �ν��Ѵ�.)
		// todSetting ������ month = calendar.get(Calendar.MONTH) + 1;�� ����µ� �̴� ����Ҷ� �����������
		// �����ϱ� ������
		calendar.set(Calendar.DATE, 1); // 1��
		System.out.println("Calendar.DATE: " + calendar.get(Calendar.DATE));

		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // ������ ���° ��������(�Ͽ��Ϻ��� 1�� ����)
		System.out.println("dayOfWeek: " + dayOfWeek);
		System.out.println("calendar.getFirstDayOfWeek(): " + calendar.getFirstDayOfWeek());

		int beforeDay = 0;
		// 1���� �ִ� ù���� ���Ϻ��� 1���� ����������, �� �Ͽ��Ϻ��� �ش� ���� ù���� ���������� ��ĭ���� �����ϱ� ���ؼ� ���
		for (int i = calendar.getFirstDayOfWeek(); i < dayOfWeek; i++) {
			System.out.println("i: " + i);
			beforeDay++;
		}
		beforeDay_1 = beforeDay;

		System.out.println("beforeDay: " + beforeDay);
		for (int i = 0; i < beforeDay; i++) {
			calBtn[i].setText("");
		}

		// 1�Ϻ��� ������� ������ ��¥���� ������ for��
		for (int i = calendar.getMinimum(Calendar.DAY_OF_MONTH); i <= calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
//			calendar.set(Calendar.DATE, i);
//			if (calendar.get(Calendar.MONTH) != month - 1) {
//				break;
//			}
			calBtn[i + beforeDay - 1].setText((i) + "");
			calBtn[i + beforeDay - 1].addActionListener(this); // ��ư Ŭ���� �׼� ����
		}

		System.out.println("1 :" + calendar.get(Calendar.MONTH));
		System.out.println("2 :" + (month - 1));
		System.out.println(month);

		// ���� ���� ��¥ ��ư ��Ȱ��ȭ
		if (month == thisMonth) {
			for (int i = calendar.getMinimum(Calendar.DAY_OF_MONTH); i < day; i++) {
				calBtn[i + beforeDay - 1].setForeground(Color.GRAY);
				calBtn[i + beforeDay - 1].setEnabled(false);
				if (year > thisYear) {
					calBtn[i + beforeDay - 1].setForeground(Color.BLACK);
					calBtn[i + beforeDay - 1].setEnabled(true);
				}
			}
		}
		for (int i = calendar.getMinimum(Calendar.DAY_OF_MONTH); i <= calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			if ((month < thisMonth && year == thisYear)) {
				calBtn[i + beforeDay - 1].setForeground(Color.GRAY);
				calBtn[i + beforeDay - 1].setEnabled(false);
			} else if (year < thisYear) {
				calBtn[i + beforeDay - 1].setForeground(Color.GRAY);
				calBtn[i + beforeDay - 1].setEnabled(false);
			}
		} // �������

		// ���ڰ� ������ ���� ��ư ��Ȱ��ȭ
		for (int i = 0; i < calBtn.length; i++) {
			if (calBtn[i].getText().equals("")) {
				calBtn[i].setEnabled(false);
			}
		}
	}

	// 0000�� 0�� ����
	private void topSetting() {
		today = Calendar.getInstance();
		calendar = new GregorianCalendar();

		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1; // 1���� 0���� ����

		thisYear = today.get(Calendar.YEAR);
		thisMonth = today.get(Calendar.MONTH) + 1;
		day = today.get(Calendar.DATE); // day�� ���� ��¥ ����
	}

	private void laoutSetting() {
		NPanel = new JPanel();
		this.add(NPanel, BorderLayout.NORTH);
		NPanel.setLayout(new BorderLayout());

		Panel_1 = new JPanel();
		NPanel.add(Panel_1, BorderLayout.CENTER);

		yearLabel = new JLabel(year + "��");
		yearLabel.setFont(new Font("����", Font.BOLD, 20));
		Panel_1.add(yearLabel);

		monthLabel = new JLabel(month + "��");
		monthLabel.setFont(new Font("����", Font.BOLD, 20));
		Panel_1.add(monthLabel);

		Panel_2 = new JPanel();
		NPanel.add(Panel_2, BorderLayout.WEST);

		beforeBtn = new JButton("<"); // ������ �޷� ���� ��ư
		beforeBtn.setFont(new Font("����", Font.BOLD, 12));
		beforeBtn.setBorderPainted(false);
		beforeBtn.setContentAreaFilled(false);
		beforeBtn.setFocusPainted(false);
		Panel_2.add(beforeBtn);
		beforeBtn.addMouseListener(new MouseListener() {

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
			}
		});

		Panel_3 = new JPanel();
		NPanel.add(Panel_3, BorderLayout.EAST);

		nextBtn = new JButton(">"); // ������ �޷� ���� ��ư
		nextBtn.setFont(new Font("����", Font.BOLD, 12));
		nextBtn.setBorderPainted(false);
		nextBtn.setContentAreaFilled(false);
		nextBtn.setFocusPainted(false);
		Panel_3.add(nextBtn);
		nextBtn.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				nextBtn.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				nextBtn.setForeground(Color.GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		SPanel = new JPanel();
		getContentPane().add(SPanel, BorderLayout.SOUTH);

		okBtn = new JButton("����"); // ���� ��ư
		okBtn.setFont(new Font("����", Font.PLAIN, 12));
		okBtn.setFocusPainted(false);
		SPanel.add(okBtn);
	}

	// �� ��ư�� �ൿ ����
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == beforeBtn) { // < ���� ��ư ������
			CPanel.removeAll();
			calendarInput(-1);
			addGrid();
			calendarSetting();
			yearLabel.setText(year + "��");
			monthLabel.setText(month + "��");
		} else if (e.getSource() == nextBtn) { // > ���� ��ư ������
			CPanel.removeAll();
			calendarInput(1);
			addGrid();
			calendarSetting();
			yearLabel.setText(year + "��");
			monthLabel.setText(month + "��");
		}

		for (int i = calendar.getMinimum(Calendar.DAY_OF_MONTH); i <= calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
			if (e.getSource().equals(calBtn[i + beforeDay_1 - 1])) {
				a = calBtn[i + beforeDay_1 - 1].getText();
				chkDay();
				break;
			}
		}

		if (e.getSource().equals(okBtn)) {
			dispose();
		}

	}

	private void chkDay() {
		b = year + "-" + month + "-" + a;
			r.chkInDay(b,s,a); // b:��ü��¥, s:in/out����, a:day 
	}

	// ������ �������� �Ѿ�� month�� year����
	private void calendarInput(int i) {
		if (i == -1 || i == 1) {
			month = month + i;
			if (month <= 0) {
				month = 12;
				year = year - 1;
			} else if (month >= 13) {
				month = 1;
				year = year + 1;
			}
		}
	}

//	public static void main(String[] args) {
//		new CalendarData();
//	}

}
