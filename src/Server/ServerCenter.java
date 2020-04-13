package Server;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import JDBC.DAOCenter;
import JDBC.MDTO;
import JDBC.RDTO;

public class ServerCenter {
	private ArrayList<SConnect> sList = new ArrayList<>();
	private DAOCenter dc = null;
	private MDTO mt = null;
	private RDTO rt = null;
	private ArrayList<MDTO> memberList = new ArrayList<>();
	private ArrayList<RDTO> getRoomList = new ArrayList<>();
	private ArrayList<Object> obList = null;
	private ArrayList<RDTO> myRoomList = new ArrayList<>();
	private ArrayList<String> myDaysList = new ArrayList<>();

	private String chkIn = null;
	private String chkOut = null;
	private String adult = null;
	private String kid = null;

	ServerCenter() {
		dc = DAOCenter.getInstance();
	}

	public void addList(SConnect s) {
		this.sList.add(s);
	}

	public void receive(Socket withClient, String msg) {
		if ('*' == (msg.charAt(0))) { // �ߺ�üũ��
			idCheck(withClient, msg);

		} else if ('/' == (msg.charAt(0))) { // ȸ����������
			joinGo(withClient, msg);

		} else if ('>' == (msg.charAt(0))) { // �α�������
			loginGo(withClient, msg);

		} else if ('^' == (msg.charAt(0))) { // ����ȭ������
			myReserve(withClient, msg);
		}
	}

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
		chkDays("mine", chkIn, chkOut, null);
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
			compare(alreadyList, r);
		}

		System.out.println("days :" + myDaysList);
	}

	// ��¥ ��
	private void compare(ArrayList<String> alreadyList, RDTO r) {
		System.out.println("�������� compare �Դ�?");
		if (alreadyList.size() >= myDaysList.size()) {
			for (int i = 0; i < alreadyList.size(); i++) {
				if (alreadyList.contains(myDaysList.get(i))) { // ��¥�� �ߺ��Ǹ�
					break;
				} else {
					reserveGo(r);
				}
			}
		} else {
			for (int i = 0; i < myDaysList.size(); i++) {
				if (myDaysList.contains(alreadyList.get(i))) { // ��¥�� �ߺ��Ǹ�
					break;
				} else {
					reserveGo(r);
				}
			}
		}
	}

	// DB�κ��� ��ü��� �����ͼ� �� üũ�ϱ�
	private void reserveChk() {
		obList = dc.getList("room");
		for (Object o : obList) {
			rt = (RDTO) o;
			getRoomList.add(rt);
		}
		System.out.println("getRoomList : " + getRoomList.size());

		int people = Integer.valueOf(adult) + Integer.valueOf(kid); // ��ü�ο�:����+�Ƶ�

		for (RDTO r : getRoomList) {
			if (people <= r.getPeople()) { // �ο� �� üũ
				if (r.getId() == null) { // �����ڰ� ������
					reserveGo(r);
				} else { // �����ڰ� ������
					chkDays("already", r.getChkIn(), r.getChkOut(), r); // ��¥üũ�Ҳ���
				}
			}
		}
	}

	// �������� �Ѿ��
	private void reserveGo(RDTO r) {
		myRoomList.add(r);
		
	}

	// �α���
	private void loginGo(Socket withClient, String msg) {
		int fs = msg.indexOf(" ", 0);
		int ed = msg.lastIndexOf(" ");
//		String a = msg.substring(0, fs); // ���� �κ�
		String id = msg.substring(fs + 1, ed); // ���̵� �κ�
		String pwd = msg.substring(ed + 1); // ��й�ȣ �κ�

		if (loginChk(id, pwd)) {
			String m = "/login yes"; // �α��� ����
			for (SConnect s : sList) {
				s.send(withClient, m);
				s.streamSet(withClient, id);
			}
		} else {
			String m = "/login no"; // �α��� ����
			for (SConnect s : sList) {
				s.send(withClient, m);
			}
		}
	}

	// �α��� üũ
	private boolean loginChk(String id, String pwd) {
		this.obList = dc.getList("member"); // ȸ����� ��������
		for (Object o : obList) {
			mt = (MDTO) o;
			memberList.add(mt);
		}
		for (MDTO m : memberList) {
			if (m.getId().equals(id) && m.getPwd().equals(pwd)) {
				return true;
			}
		}
		return false;
	}

	// ȸ������
	private void joinGo(Socket withClient, String msg) {
		System.out.println(msg);

		int ed = msg.indexOf(" ", 0);
//		String a = msg.substring(0, ed); // ���� �κ�
		String info = msg.substring(ed + 1); // ȸ������ �κ�

		mt = new MDTO();
		StringTokenizer st = new StringTokenizer(info); // �⺻�� ����
		while (st.hasMoreTokens()) {
			for (int i = 0; i < st.countTokens(); i++) {
				mt.setId(st.nextToken());
				mt.setPwd(st.nextToken());
				mt.setName(st.nextToken());
				mt.setTel(st.nextToken());
				mt.setAddr(st.nextToken());
			}
		}
		dc.insert("member", (Object) mt);

		String m = "/join memberOk";

		for (SConnect s : sList) {
			s.send(withClient, m);
		}
	}

	// ���̵� �ߺ�üũ
	private void idCheck(Socket withClient, String msg) {
		int ed = msg.indexOf(" ", 0);
		String id = msg.substring(ed + 1); // ���̵� �κ�

		String m;
		if (dc.idCheck(id) == false) { // �ߺ� ���̵� �ִٸ�
			m = "/join no";
		} else {
			m = "/join yes";
		}

		for (SConnect s : sList) {
			s.send(withClient, m);
		}
	}
}
