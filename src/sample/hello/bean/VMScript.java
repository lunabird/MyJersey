package sample.hello.bean;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import edu.xidian.enc.MD5Util;
import edu.xidian.enc.SerializeUtil;
import edu.xidian.message.Message;
import edu.xidian.message.MsgType;

public class VMScript {

	public boolean sendExeVmScriptMsg(String uid, String ip, File file) {
		try {
			Socket socket = new Socket(ip, 9400);

			Message msg = new Message(MsgType.executeVMScript, uid,
					file.getName());
			// ����
			String datatemp = SerializeUtil.serialize(msg);
			String str = MD5Util.convertMD5(datatemp);
			// ����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			oos.flush();
			// �����ļ�
			System.out.println("�ļ�����Ϊ��" + file.length());
			DataOutputStream dos = new DataOutputStream(
					socket.getOutputStream());
			FileInputStream fis = new FileInputStream(file.getPath());
			// �����ļ�����
			dos.writeLong((long) file.length());
			dos.flush();
			System.out.println(file.length());
			System.out.println("��ʼ�����ļ�" + file.getName() + "...");

			// ��������С
			int bufferSize = 8192;
			// ������
			byte[] buf = new byte[bufferSize];
			// �����ļ�
			while (true) {
				int read = 0;
				if (fis != null) {
					read = fis.read(buf);

				}
				if (read == -1) {
					break;
				}
				dos.write(buf, 0, read);

			}
			dos.flush();
			System.out.println("�������");

			// ��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String) ois.readObject();
			// ����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message) SerializeUtil.deserialize(str2);
			if (msg.getType().equals(MsgType.executeVMScript)) {
				String ret = (String) msg.getValues();
				if (ret.equals("success") || ret.equals("executing")) {
					return true;
				}
				System.out.println(ret);
			}
			socket.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}
