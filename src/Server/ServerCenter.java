package Server;

import java.net.Socket;
import java.util.ArrayList;
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
//	private ArrayList<String> myDaysList = new ArrayList<>();
//	private ArrayList<String[]> roomList = new ArrayList<>();

//	private String chkIn = null;
//	private String chkOut = null;
//	private String adult = null;
//	private String kid = null;

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

		} else if ('^' == (msg.charAt(0))) { // ���� �����ü� �ֵ���
			getDBList(withClient, msg);
		}
	}

	// DB�κ��� ���� ������
	private void getDBList(Socket withClient, String msg) {
//		ArrayList<RDTO> getRoomList = new ArrayList<>();
//		ArrayList<BDTO> getBookingList = new ArrayList<>();

		if (msg.indexOf("callRoom") > -1) { // ���� �ҷ���
			obList = dc.getList("room");
//			for (Object o : obList) {
//				rt = (RDTO) o;
//				getRoomList.add(rt);
//			}

			for (SConnect s : sList) {
				s.sendObject(withClient, obList);
			}
			System.out.println(obList);

		} else if (msg.indexOf("callBooking") > -1) { // ������ �ҷ���
			obList = dc.getList("booking");
//			for (Object o : obList) {
//				bt = (BDTO) o;
//				getBookingList.add(bt);
//			}
//
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

		String m;
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
