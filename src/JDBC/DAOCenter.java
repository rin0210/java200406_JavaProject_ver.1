package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCenter {
	// �̱���
	private static DAOCenter single = null;

	private DAOInterface DAOIF = null;
	private Connection conn = null;
//	private MDAO mda = null;
//	private RDAO rda = null;

	private ArrayList<Object> obList = null;

	private DAOCenter() {
		admin();
		connect();
	}

	public static DAOCenter getInstance() {
		if (single == null) {
			single = new DAOCenter();
		}
		return single;
	}

	private static void admin() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Ŭ���� �ε� ����~!");

		} catch (ClassNotFoundException e) {
			System.out.println("Ŭ���� �ε� ����..");
		}
	}

	private boolean connect() {
		boolean cFalg = false;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			cFalg = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cFalg;
	}

	// ��ü ��� ��������
	public ArrayList<Object> getList(String s) {
		if (s.equals("member")) { // ȸ�����
			if (connect()) {
				DAOIF = new MDAO(conn);
				obList = DAOIF.getList();
			}
		} else if (s.equals("room")) {
			if (connect()) {
				DAOIF = new RDAO(conn);
				obList = DAOIF.getList();
			}
		}
		return obList;
	}

	// ���
	public void insert(String s, Object ob) {
		if (s.equals("member")) { // ȸ�����
			if (connect()) {
				DAOIF = new MDAO(conn);
				DAOIF.insert(ob);
			} else {
				System.out.println("DB ���� ����..!");
				System.exit(0);
			}
		}
	}

	// ���̵� �ߺ�üũ
	public boolean idCheck(String id) {
		if (connect()) {
			DAOIF = new MDAO(conn);
			if (DAOIF.idCheck(id) == false) { // �ߺ� ���̵� �ִٸ�
				return false;
			}

		} else {
			System.out.println("DB ���� ����..!");
			System.exit(0);
		}
		return true;
	}

}
