package Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import JDBC.DAOCenter;
import JDBC.MDTO;

public class ServerCenter {
	private ArrayList<SConnect> sList = new ArrayList<>();
	private DAOCenter dc = null;
	private MDTO mt = null;
	private ArrayList<MDTO> memberList = new ArrayList<>();
	private ArrayList<Object> obList = null;

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
		StringTokenizer st = new StringTokenizer(info, " ");
//		System.out.println(st.countTokens());
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
