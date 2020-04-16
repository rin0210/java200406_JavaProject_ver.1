package Client;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import JDBC.RDTO;
import Swing.HMain;
import oracle.jdbc.driver.T4CXAConnection;

public class CConnect {
	private Socket withServer = null;
	private Socket withServer_1 = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private InputStream reMsg_1 = null;
	private OutputStream sendMsg_1 = null;
	private HMain hm = null;

	public CConnect(Socket s, Socket s_1) {
		this.withServer = s;
		this.withServer_1 = s_1;
		hm = HMain.getInstance();
		hm.admin(this);
	}

	public String receive() {
		System.out.println("receiveMsg");
		try {

			reMsg = withServer.getInputStream();
			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			String msg = new String(reBuffer);
			msg = msg.trim();
			System.out.println(msg);

			return msg;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Object> receiveObject() {
		System.out.println("CConnect receiveObject");
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		
		try {
			reMsg_1 = withServer_1.getInputStream();
			byte[] reBuffer = new byte[1024];
			reMsg_1.read(reBuffer);

			ByteArrayInputStream bis = new ByteArrayInputStream(reBuffer);
			ObjectInputStream ois = new ObjectInputStream(bis);

			Object o = ois.readObject();
			ArrayList<Object> oList = (ArrayList<Object>) o;

			return oList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void send(String m) {
		try {
			sendMsg = withServer.getOutputStream();
			sendMsg.write(m.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
