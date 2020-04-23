package Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import JDBC.BDTO;
import JDBC.DAOCenter;
import JDBC.MDTO;
import JDBC.RDTO;

public class ServerCenter {
	private ArrayList<SConnect> sList = new ArrayList<>();
	private DAOCenter dc = null;
	private MDTO mt = null;
	private RDTO rt = null;
	private BDTO bt = null;
	private ArrayList<Object> obList = null;
	private String reNum = null;

	ServerCenter() {
		dc = DAOCenter.getInstance();
	}

	public void addList(SConnect s) {
		this.sList.add(s);
	}

	public void receive(Socket withClient, String msg, String id) {
		if (msg.indexOf("/idChk") > -1) { // �ߺ�üũ��
			idCheck(withClient, msg);

		} else if (msg.indexOf("/join") > -1) { // ȸ����������
			joinGo(withClient, msg);

		} else if (msg.indexOf("/login") > -1) { // �α�������
			loginGo(withClient, msg);

		} else if (msg.indexOf("/call") > -1) { // ���� ��������
			getDBList(withClient, msg);

		} else if (msg.indexOf("/getMyInfo") > -1) { // ������ ��������
			getMyInfo(withClient, msg, id);

		} else if (msg.indexOf("/reservation") > -1) { // ������������
			setReserve(withClient, msg, id);

		} else if (msg.indexOf("/logout") > -1) { // �α׾ƿ�����
			logoutGo(withClient, msg);

		} else if (msg.indexOf("/myBooking") > -1) { // ����Ȯ������
			getMyBooking(withClient, msg, id);
		}
	}

	// ����Ȯ���ϱ� ����
	private void getMyBooking(Socket withClient, String msg, String id) {
		ArrayList<BDTO> getBookingList = new ArrayList<>();
		ArrayList<BDTO> myBookingList = new ArrayList<>();
		this.obList = dc.getList("booking"); // ������ ��� �ҷ�����

		System.out.println("������ ��� �����Ծ�? " + obList);

		for (SConnect s : sList) {
			s.sendObject(withClient, obList);
		}
	}

	// �α׾ƿ�
	private void logoutGo(Socket withClient, String msg) {
		for (SConnect s : sList) {
			s.logout(withClient, msg);
		}
	}

	// ������ �����ȣ �����
	private String setRandomNum() {
		Random r = new Random();
		reNum = "";

		for (int i = 0; i < 10; i++) {
			reNum = reNum + String.valueOf(r.nextInt(8) + 1);
		}

		return reNum;
	}

	// ���� ����
	private void setReserve(Socket withClient, String msg, String id) {
		int ed = msg.indexOf(" ", 0);
//		String a = msg.substring(0, ed); // ���� �κ�
		String info = msg.substring(ed + 1); // ȸ������ �κ�

		bt = new BDTO();
		bt.setReNum(setRandomNum());

		if (id != null) {
			bt.setId(id); // ȸ���� ���
		}

		StringTokenizer st = new StringTokenizer(info); // �⺻�� ����
		while (st.hasMoreTokens()) {
			for (int i = 0; i < st.countTokens(); i++) {
				bt.setName(st.nextToken());
				bt.setRoom(st.nextToken());
				bt.setPeople(Integer.valueOf(st.nextToken()));
				bt.setChkIn(st.nextToken());
				bt.setChkOut(st.nextToken());
				bt.setNight(Integer.valueOf(st.nextToken()));
				bt.setPrice(Integer.valueOf(st.nextToken()));
			}
		}
		dc.insert("booking", (Object) bt);

		String m = "reservationOk " + reNum; // �����ȣ ������

		for (SConnect s : sList) {
			s.send(withClient, m);
		}
		System.out.println("m: " + m);
	}

	// ������ ��������
	private void getMyInfo(Socket withClient, String msg, String id) {
		ArrayList<MDTO> memberList = new ArrayList<>();
		String[] myInfo = null;
		this.obList = dc.getList("member"); // ȸ����� ��������
		for (Object o : obList) {
			mt = (MDTO) o;
			memberList.add(mt);
		}
		for (MDTO m : memberList) {
			if (m.getId().equals(id)) { // ���̵� üũ ��
				myInfo = m.getArray(); // ���� ����
				break;
			}
		}
//		String m = myInfo+"";
		for (SConnect s : sList) {
			s.sendObject(withClient, myInfo);
		}
		System.out.println("myInfo : " + myInfo);

	}

	// DB�κ��� ���� ������
	private void getDBList(Socket withClient, String msg) {

		if (msg.indexOf("callRoom") > -1) { // ���� �ҷ���
			this.obList = dc.getList("room");

			for (SConnect s : sList) {
				s.sendObject(withClient, obList);
			}
			System.out.println(obList);

		} else if (msg.indexOf("callBooking") > -1) { // ������ �ҷ���
			this.obList = dc.getList("booking");

			for (SConnect s : sList) {
				s.sendObject(withClient, obList);
			}
			System.out.println(obList);
		}
	}

	// �α���
	private void loginGo(Socket withClient, String msg) {
		int fs = msg.indexOf(" ", 0);
		int ed = msg.lastIndexOf(" ");
//		String a = msg.substring(0, fs); // ���� �κ�
		String id = msg.substring(fs + 1, ed); // ���̵� �κ�
		String pwd = msg.substring(ed + 1); // ��й�ȣ �κ�

		if (loginChk(id, pwd)) {
			String m = "loginYes"; // �α��� ����
			for (SConnect s : sList) {
				s.send(withClient, m);
				s.streamSet(withClient, id);
			}
		} else {
			String m = "loginNo"; // �α��� ����
			for (SConnect s : sList) {
				s.send(withClient, m);
			}
		}
	}

	// �α��� üũ
	private boolean loginChk(String id, String pwd) {
		ArrayList<MDTO> memberList = new ArrayList<>();

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

		String m = "memberOk";

		for (SConnect s : sList) {
			s.send(withClient, m);
		}
	}

	// ���̵� �ߺ�üũ
	private void idCheck(Socket withClient, String msg) {
		int ed = msg.indexOf(" ", 0);
		String id = msg.substring(ed + 1); // ���̵� �κ�

		String m = null;
		if (dc.idCheck(id) == false) { // �ߺ� ���̵� �ִٸ�
			m = "idNo";
		} else {
			m = "idYes";
		}

		for (SConnect s : sList) {
			s.send(withClient, m);
		}
	}
}
