package JDBC;

import java.util.ArrayList;

public interface DAOInterface {
	
	// ���̵� �ߺ�üũ
	boolean idCheck(String id);

	// ȸ�����, ������
	boolean insert(Object o);
	
	// ��ü ��� ��������
	public ArrayList<Object> getList();

}
