package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SMain {

	public static void main(String[] args) throws Exception {
		ServerSocket serverS = null;
		Socket withClient = null;

		serverS = new ServerSocket();
		serverS.bind(new InetSocketAddress("10.0.0.77", 9999));
		
		ServerCenter sc = new ServerCenter();

		while (true) {
			System.out.println("���� �����");
			withClient = serverS.accept();

			System.out.println("Ŭ���̾�Ʈ ���� ��");
			SConnect s = new SConnect(withClient);
			s.admin(sc);
			sc.addList(s);
			s.start();

		}
	}

}
