package JDBC;

public class DAOCenter {
	// �̱���
	private static DAOCenter single = null;
	private DAOInterface DAOIF = null;

	private DAOCenter() {

	}

	public static DAOCenter getInstance() {
		if (single == null) {
			single = new DAOCenter();
		}
		return single;
	}

	public void insert(String s, String[] list) {
		if (s.equals("join")) {
			DAOIF = new MDAO();
			DAOIF.insert(list);
		}
	}

	// ���̵� �ߺ�üũ
	public boolean idCheck(String id) {
		DAOIF = new MDAO();
		if (DAOIF.idCheck(id) == true) { // �ߺ� ���̵� �ִٸ�
			return true;
		}
		return false;
	}

}
