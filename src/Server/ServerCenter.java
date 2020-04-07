package Server;

import java.net.Socket;
import java.util.ArrayList;

import JDBC.DAOCenter;
import JDBC.MDTO;

public class ServerCenter {
	private ArrayList<SConnect> sList = new ArrayList<>();
	private DAOCenter dc = null;
	private MDTO mt = null;
	private boolean check = false; // ���̵� �ߺ�üũ �ߴ���

	public ServerCenter() {
		dc = DAOCenter.getInstance();
	}

	public void addList(SConnect s) {
		this.sList.add(s);
	}

	public void receive(Socket withClient, String msg) {
		if ('*' == (msg.charAt(0))) { // �ߺ�üũ��
			check = true;
			idCheck(withClient, msg);
		} else if ('/' == (msg.charAt(0))) { // ȸ����������
			joinGo(msg);
		} else if ('>' == (msg.charAt(0))) { // �α�������
			loginGo(msg);
		}
	}

	// �α���
	private void loginGo(String msg) {

	}

	// ȸ������
	private void joinGo(String msg) {
		int ed = msg.indexOf(" ", 0);
		String a = msg.substring(0, ed); // ���� �κ�
		String b = msg.substring(ed + 1); // ȸ������ �κ�
		mt = new MDTO();

		if (a.indexOf("id") == 0) {
			mt.setId(b);
		} else if (a.indexOf("pwd") == 0) {
		} else if (a.indexOf("name") == 0) {
		} else if (a.indexOf("tel") == 0) {
		} else if (a.indexOf("addr") == 0) {
		}

//		dc.insert("join", );
	}

	// ���̵� �ߺ�üũ
	private void idCheck(Socket withClient, String msg) {
		String m;
		if (dc.idCheck(msg)) { // �ߺ� ���̵� �ִٸ�
			m = "no";
		} else {
			m = "yes";
		}

		for (SConnect s : sList) {
			s.send(withClient,m);
		}
		
		check = false;

	}
}
