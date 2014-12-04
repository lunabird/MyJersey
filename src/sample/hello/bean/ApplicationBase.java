package sample.hello.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.ws.rs.QueryParam;

import edu.xidian.enc.MD5Util;
import edu.xidian.enc.SerializeUtil;
import edu.xidian.message.Message;
import edu.xidian.message.MsgType;

public class ApplicationBase {
	/**
	 * ��װtomcat
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupTomcatMsg(String uid,String ip,String[] scIPAddr,String installPath,String jdkPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[4];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			values[3] = jdkPath;
			Message msg = new Message(MsgType.setupTomcat, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupTomcat)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װtomcat on Linux
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupTomcatOnLinuxMsg(String uid,String ip,String[] scIPAddr,String installPath,String jdkName,String jdkPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[5];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			values[3] = jdkName;
			values[4] = jdkPath;
			Message msg = new Message(MsgType.setupTomcat, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupTomcat)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װMysql
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupMySqlMsg(String uid,String ip,String[] scIPAddr,String installPath,String pswd){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[4];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			values[3] = pswd;
			Message msg = new Message(MsgType.setupMySql, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupMySql)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װ Mysql on Linux
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupMySqlOnLinuxMsg(String uid,String ip,String[] scIPAddr,String pswd){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[4];
			values[0] = scIPAddr[0];
			String temps = scIPAddr[1];
			values[1] = temps.split(",")[0];
			values[2] = temps.split(",")[1];
			values[3] = pswd;
			Message msg = new Message(MsgType.setupMySql, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupMySql)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װJdk
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupJdkMsg(String uid,String ip,String[] scIPAddr,String installPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[3];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			Message msg = new Message(MsgType.setupJdk, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupJdk)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װApache
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupApacheMsg(String uid,String ip,String[] scIPAddr,String installPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[3];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			Message msg = new Message(MsgType.setupApache, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupApache)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װNginx
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupNginxMsg(String uid,String ip,String[] scIPAddr,String installPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[3];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			Message msg = new Message(MsgType.setupNginx, uid,values);
			System.out.println("values:"+values[0]+","+values[1]+","+values[2]);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			/*ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(msg);
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			msg = (Message) ois.readObject();*/

			if (msg.getType().equals(MsgType.setupNginx)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װZendGuardLoader
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupZendGuardLoaderMsg(String uid,String ip,String[] scIPAddr,String installPath,String phpPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[4];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[3] = phpPath;
			values[2] = installPath;
			Message msg = new Message(MsgType.setupZendGuardLoader, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupZendGuardLoader)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װPython
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupPythonMsg(String uid,String ip,String[] scIPAddr){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[2];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			Message msg = new Message(MsgType.setupPython, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupPython)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װMemcached
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupMemcachedMsg(String uid,String ip,String[] scIPAddr){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[3];
			values[0] = scIPAddr[0];
			String temps = scIPAddr[1];
			if(temps.contains(",")){
				values[1] = temps.split(",")[0];
				values[2] = temps.split(",")[1];
			}else{
				values[1] = scIPAddr[1];
			}
			Message msg = new Message(MsgType.setupMemcached, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupMemcached)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װIISRewrite
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupIISRewriteMsg(String uid,String ip,String[] scIPAddr,String installPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[3];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			Message msg = new Message(MsgType.setupIISRewrite, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupIISRewrite)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װASP
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupASPMsg(String uid,String ip,String[] scIPAddr,String installPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[3];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			Message msg = new Message(MsgType.setupASP, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupASP)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װFTP
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupFTPMsg(String uid,String ip,String[] scIPAddr,String installPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[3];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			Message msg = new Message(MsgType.setupFTP, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupFTP)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װASPNET
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupASPNETMsg(String uid,String ip,String[] scIPAddr,String installPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[3];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			Message msg = new Message(MsgType.setupASPNET, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupASPNET)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װSQLServer2008R2
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @param rootPswd
	 * @param hostName
	 * @param userName
	 * @return
	 */
	public boolean sendSetupSQLServer2008R2Msg(String uid,String ip,String[] scIPAddr,String installPath,String rootPswd,String hostName,String userName){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[6];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			values[3] = rootPswd;
			values[4] = hostName;
			values[5] = userName;
			Message msg = new Message(MsgType.setupSQLServer2008R2, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupSQLServer2008R2)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װSQLServer2000
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupSQLServer2000Msg(String uid,String ip,String[] scIPAddr,String installPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[3];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			Message msg = new Message(MsgType.setupSQLServer2000, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupSQLServer2000)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װOracle10g
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetupOracle10gMsg(String uid,String ip,String[] scIPAddr,String installPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[3];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			Message msg = new Message(MsgType.setupOracle10g, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupOracle10g)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װOracle11g
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param hostname
	 * @param inventorypath
	 * @param installPath
	 * @param oraclehome
	 * @param rootPswd
	 * @return
	 */
	public boolean sendSetupOracle11gMsg(String uid,String ip,String[] scIPAddr,String hostname,String inventorypath,
			String installPath,String oraclehome, String rootPswd){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[7];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = hostname;
			values[3] = inventorypath;
			values[4] = installPath;
			values[5] = oraclehome;
			values[6] = rootPswd;
			Message msg = new Message(MsgType.setupOracle11g, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setupOracle11g)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	/**
	 * ��װ360
	 * @param uid
	 * @param ip
	 * @param scIPAddr
	 * @param installPath
	 * @return
	 */
	public boolean sendSetup360Msg(String uid,String ip,String[] scIPAddr,String installPath){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[3];
			values[0] = scIPAddr[0];
			values[1] = scIPAddr[1];
			values[2] = installPath;
			Message msg = new Message(MsgType.setup360, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setup360)) {
				String ret = (String)msg.getValues();
				if(ret.equals("success")||ret.equals("executing")){
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
	
	public String sendGetStatusMsg(String uid,String ip,String softwareName){
		//����Socket��Ϣ��Agent
		try {
			Socket socket = new Socket(ip, 9100);
			String[] values = new String[1];
			values[0] = softwareName;
			Message msg = new Message(MsgType.setup360, uid,values);
			//����
			String datatemp = SerializeUtil.serialize(msg);  
            String str = MD5Util.convertMD5(datatemp);
            //����
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			oos.writeObject(str);
			//��÷�����Ϣ
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			str = (String)ois.readObject();
			//����
			String str2 = MD5Util.convertMD5(str);
			msg = (Message)SerializeUtil.deserialize(str2); 
			if (msg.getType().equals(MsgType.setup360)) {
				String ret = (String)msg.getValues();
//				if(ret.equals("success")||ret.equals("executing")){
//					return true;
//				}
				System.out.println(ret);
				return ret;
			}
			socket.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "cannot get status of "+softwareName;
	}
}
