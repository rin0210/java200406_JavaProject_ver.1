package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SMain {

	public static void main(String[] args) throws Exception {
		ServerSocket serverS = null;
		Socket withClient = null;

		serverS = new ServerSocket();
		serverS.bind(new InetSocketAddress("10.0.0.77", 9988));

		
		ServerSocket serverS_1 = null;
		Socket withClient_1 = null;

		serverS_1 = new ServerSocket();
		serverS_1.bind(new InetSocketAddress("10.0.0.77", 9977));

		ServerCenter sc = new ServerCenter();

		while (true) {

			System.out.println("���� �����");
			withClient = serverS.accept();
			withClient_1 = serverS_1.accept();

			System.out.println("Ŭ���̾�Ʈ ���� ��");
			SConnect s = new SConnect(withClient, withClient_1);
			s.admin(sc);
			sc.addList(s);
			s.start();

		}
	}

}
