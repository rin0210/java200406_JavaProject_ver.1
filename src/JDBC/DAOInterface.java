package JDBC;

import java.util.ArrayList;

public interface DAOInterface {
	
	
	// ���̵� �ߺ�üũ, ����Ȯ��
	boolean idCheck(String id);

	boolean insert(Object o);
	
	// ��ü ��� ��������
	public ArrayList<Object> getList();

}
