package Swing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import Client.CConnect;
import JDBC.BDTO;
import JDBC.RDTO;

public class CompareDate {
	private CConnect cc = null;
	private Reservation rv = null;
	private Room r = null;
	
	private String info = null;
	private Object o = null;
	private ArrayList<Object> getObjList = null;
	private ArrayList<RDTO> getRoomList = new ArrayList<>();
	private ArrayList<BDTO> getBookingList = new ArrayList<>();
	private RDTO rt = null;
	private BDTO bt = null;
	
	private String chkIn = null;
	private String chkOut = null;
	private String adult = null;
	private String kid = null;
	private String night = null;
	private String chk = null;
	
	private ArrayList<String> myDaysList = new ArrayList<>();
	private ArrayList<String[]> myRoomList = new ArrayList<>();
	private ArrayList<String> overlapList = new ArrayList<>(); // �ߺ��� �� ���
	private String[] mine = null; /////

	public CompareDate(CConnect cc, Reservation rv, String chk, String info, String night, String[] mine) { ///////
		this.cc = cc;
		this.rv = rv;
		this.chk = chk;
		this.info = info;
		this.night = night;
		this.mine = mine; /////
//		this.getObjList = cc.receiveObject();

		getList();
		myReserve();
	}

	// DB���� ��,���� ��� ��������
	private void getList() {
		String msg = "/callRoom";
		cc.send(msg);
		o = cc.receiveObject();
		getObjList = (ArrayList<Object>) o;
//		getObjList = cc.receiveObject();

		for (Object o : getObjList) {
			rt = (RDTO) o;
			getRoomList.add(rt);
		}
		System.out.println("���� �����Դ�? " + getRoomList.size());

		msg = "/callBooking";
		cc.send(msg);
		o = cc.receiveObject();
		getObjList = (ArrayList<Object>) o;

		for (Object o : getObjList) {
			bt = (BDTO) o;
			getBookingList.add(bt);
		}
		System.out.println("������ �����Դ�? " + getBookingList.size());
	}

	// msg�м��ϴ� �޼���
	private void myReserve() {
		StringTokenizer st = new StringTokenizer(info);

		while (st.hasMoreTokens()) {
			for (int i = 0; i < st.countTokens(); i++) {
				chkIn = st.nextToken();
				chkOut = st.nextToken();
				adult = st.nextToken();
				kid = st.nextToken();
			}
		}
		chkDays("mine", chkIn, chkOut, null);
		System.out.println("üũ�� : " + chkIn);
		System.out.println("üũ�ƿ� : " + chkOut);
	}

	// �� ��¥ ������ ��¥ ��� ���ϱ�
	private void chkDays(String a, String in, String out, RDTO r) {
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
			reserveChk(); // �� ��¥��� ���ϰ� �Ѿ

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
			compareDate(alreadyList, r);
		}
	}

	// ��¥ ��
	private void compareDate(ArrayList<String> alreadyList, RDTO r) {
		System.out.println("alreadyList ������? "+alreadyList.size());
		System.out.println("myDaysList ������? "+myDaysList.size());
		if (alreadyList.size() >= myDaysList.size()) {
			for (int i = 0; i < myDaysList.size(); i++) {
				if (alreadyList.contains(myDaysList.get(i))) { // ��¥�� �ߺ��Ǹ�
					overlapList.add(r.getRoom());
					break;
				} else {
				}
			}
		} else {
			for (int i = 0; i < alreadyList.size(); i++) {
				if (myDaysList.contains(alreadyList.get(i))) { // ��¥�� �ߺ��Ǹ�
					overlapList.add(r.getRoom());
					break;
				} else {
				}
			}
		}
	}

	// ������ �ִ��� üũ�ϱ�
	private void reserveChk() {

		if (getBookingList.size() >= 1) { // ȣ�� �� �߿� ������ ���� �ϳ��� ������
			
			for (RDTO r : getRoomList) {
				for (BDTO b : getBookingList) {
					if (r.getRoom().equals(b.getRoom())) {
						chkDays("already", b.getChkIn(), b.getChkOut(), r); // ��¥üũ�Ҳ���
						break;
					} else {

					}
				}
			}
		}
		reserveGo();
	}

	private void reserveGo() {
		System.out.println("�ߺ���¥ ���� :" + overlapList);
		
		for (int i = 0; i < getRoomList.size(); i++) {
			for (int j = 0; j < overlapList.size(); j++) {
				if (getRoomList.get(i).getRoom().equals(overlapList.get(j))) {
					getRoomList.remove(i);
				}
			}
		}
		for (RDTO r : getRoomList) {
			myRoomList.add(r.getArray());
		}

		System.out.println("���� ��� ���� �? " + myRoomList.size());
		r = new Room(cc, rv, chk, myRoomList, night, mine); 
	}

}
