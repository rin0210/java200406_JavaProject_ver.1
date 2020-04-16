package Swing;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import JDBC.RDTO;
import Server.SConnect;

public class Test {

	private ArrayList<String> myDaysList = new ArrayList<>();
	private ArrayList<String[]> roomList = new ArrayList<>();

	private String chkIn = null;
	private String chkOut = null;
	private String adult = null;
	private String kid = null;

	// msg�м��ϴ� �޼���
	private void myReserve(Socket withClient, String msg) {
		int ed = msg.indexOf(" ", 0);
//		String a = msg.substring(0, ed); // ���� �κ�
		String info = msg.substring(ed + 1); // �������� �κ�
		StringTokenizer st = new StringTokenizer(info);
		while (st.hasMoreTokens()) {
			for (int i = 0; i < st.countTokens(); i++) {
				chkIn = st.nextToken();
				chkOut = st.nextToken();
				adult = st.nextToken();
				kid = st.nextToken();
			}
		}
		chkDays(withClient, "mine", chkIn, chkOut, null);
	}

	// �� ��¥ ������ ��¥ ��� ���ϱ�
	private void chkDays(Socket withClient, String a, String in, String out, RDTO r) {
		int year = 0, month = 0, date = 0;
		int year_1 = 0, month_1 = 0, date_1 = 0;

		StringTokenizer st = new StringTokenizer(in, "-"); // üũ�� ��¥ ����
		while (st.hasMoreTokens()) {
			year = Integer.valueOf(st.nextToken());
			month = Integer.valueOf(st.nextToken());
			date = Integer.valueOf(st.nextToken());
		}
		st = new StringTokenizer(out, "-"); // üũ�ƿ� ��¥ ����
		while (st.hasMoreTokens()) {
			year_1 = Integer.valueOf(st.nextToken());
			month_1 = Integer.valueOf(st.nextToken());
			date_1 = Integer.valueOf(st.nextToken());
		}

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		cal.set(year_1, month_1 - 1, date_1); // üũ�ƿ� ��¥ ����
		String end = sdf.format(cal.getTime());
		cal.set(year, month - 1, date); // üũ�� ��¥ ����
		String start = sdf.format(cal.getTime());

		if (a.equals("mine")) { // ���� ������ ��¥
			while (true) {
				myDaysList.add(start);
				cal.add(Calendar.DATE, 1); // 1�Ͼ� ������
				start = sdf.format(cal.getTime()); // �񱳸� ���ؼ� �� ����

				if (start.equals(end)) {
					break;
				}
			}
			reserveChk(withClient); // �� ��¥��� ���ϰ� �Ѿ

		} else if (a.equals("already")) { // �ٸ� ����� ������ ��¥
			ArrayList<String> alreadyList = new ArrayList<>(); // ��� ���� ����������
			while (true) {
				alreadyList.add(start);
				cal.add(Calendar.DATE, 1);
				start = sdf.format(cal.getTime());

				if (start.equals(end)) {
					break;
				}
			}
			compare(alreadyList, r);
		}
	}

	// ��¥ ��
	private void compare(ArrayList<String> alreadyList, RDTO r) {
		if (alreadyList.size() >= myDaysList.size()) {
			for (int i = 0; i < alreadyList.size(); i++) {
				if (alreadyList.contains(myDaysList.get(i))) { // ��¥�� �ߺ��Ǹ�
					break;
				} else {
					addList(r);
				}
			}
		} else {
			for (int i = 0; i < myDaysList.size(); i++) {
				if (myDaysList.contains(alreadyList.get(i))) { // ��¥�� �ߺ��Ǹ�
					break;
				} else {
					addList(r);
				}
			}
		}
	}

	// DB�κ��� ��ü��� �����ͼ� �� üũ�ϱ�
	private void reserveChk(Socket withClient) {
		ArrayList<RDTO> getRoomList = new ArrayList<>();

//		obList = dc.getList("room");
//		for (Object o : obList) {
//			rt = (RDTO) o;
//			getRoomList.add(rt);
//		}
		System.out.println("getRoomList : " + getRoomList.size());

//		for (SConnect s : sList) {
//			s.sendObject(withClient, getRoomList);
//		}

		int people = Integer.valueOf(adult) + Integer.valueOf(kid); // ��ü�ο�:����+�Ƶ�

//		for (RDTO r : getRoomList) {
//			if (people <= r.getPeople()) { // �ο� �� üũ
//				if (r.getId() == null) { // �����ڰ� ������
//					addList(r);
//				} else { // �����ڰ� ������
//					chkDays(withClient, "already", r.getChkIn(), r.getChkOut(), r); // ��¥üũ�Ҳ���
//				}
//			}

//		}
		reserveGo(withClient);
	}

	// ����ȭ������ �Ѿ��
	private void reserveGo(Socket withClient) {
//		Object o = (Object) roomList;
//		System.out.println("Object: " + o);

//		for (SConnect s : sList) {
//			s.sendObject(withClient, roomList);
//		}
	}

	// �� ��Ͽ� ���
	private void addList(RDTO r) {
		roomList.add(r.getArray());
		System.out.println("roomList: " + roomList);
	}

}
