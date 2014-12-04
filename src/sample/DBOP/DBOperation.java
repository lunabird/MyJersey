package sample.DBOP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBOperation {

	private DBConnManager dbcManager = null;
	private Connection con = null;
	
	public DBOperation() {
		dbcManager = DBConnManager.getInstance();
		con = dbcManager.getConnection();
		if (con == null) {
            System.out.println("Can't get connection");
            return;
        }
	}
	/**
	 *  �޸�IP��������ݿ�
	 * @param oldIP
	 * @param changeToIP
	 * @throws SQLException 
	 */
	public boolean updateHostIP(String oldIP,String changeToIP) throws SQLException{
		Statement stmt = null;
		stmt = con.createStatement();
		//��ø��û��ɲ�������������
		String sql = "UPDATE  hostinfo  SET hostIP ='"+changeToIP+"' WHERE hostIP='"+oldIP+"'";
		boolean flag = stmt.execute(sql);
		//�ͷ���Դ
		dbcManager.close();
		if(stmt!=null)
		{
			try{
				stmt.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return flag;
	}
	/**@author XQ
	 * �����û�ID��ѯ���ݿ��ø��û����Բ�����������������Ӧ��������ĵ�ַ�б�
	 * @param userID �û�ID
	 * @return
	 * @throws SQLException
	 */
	public List<String[]> getRCAddr(String userID) throws SQLException {
		List<String[]> resultList = null;
		Statement stmt = null;
		ResultSet rs = null;
		stmt = con.createStatement();
		//��ø��û��ɲ�������������
		String sql = "SELECT hostID FROM userhost where userID=?";
		rs = dbcManager.query(sql, new String[]{userID});
		ArrayList<String> hostList = new ArrayList<String>();
		System.out.println("user contains hostID:::");
		while(rs.next()) {
			System.out.println(rs.getString(1));
			hostList.add(rs.getString(1));
		}
		if(hostList.size() > 0) {
			//���û��ɲ�����������������0
			resultList = new ArrayList<String[]>();
			for(String hostID: hostList) {
				String[] result = new String[2];
				System.out.println("hostID:" + hostID);
				//���ݸ�����ID���������IP
				sql = "SELECT hostIP FROM hostinfo where hostID=? LIMIT 1";
				rs = dbcManager.query(sql, new String[]{hostID});
				if(rs.next()) {
					System.out.println("hostIP:" + rs.getString(1));
					result[0] = rs.getString(1);
				}
				//��ø��������ڵ�������ĵ�ַ
				sql = "SELECT RCAddress FROM hostrc where hostID=?";
				rs = dbcManager.query(sql, new String[]{hostID});
				if(rs.next()) {
					System.out.println("RCAddress:" + rs.getString(1));
					result[1] = rs.getString(1);
				}
				resultList.add(result);
			}
		}
		//�ͷ���Դ
		dbcManager.close();
		if(rs!=null)
		{
			try{
				rs.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(stmt!=null)
		{
			try{
				stmt.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return resultList;
	}
	
	
	
	/**@author ZXQ
	 * �����û���������ID��ѯ���ݿ��ø���������Ӧ��������ĵ�ַ
	 * @param userID �û�ID
	 * @param hostID  ����ID
	 * @return
	 */
	public  String[] getRCAddrByID(String userID,String hostID,String softName) throws SQLException {
		String[] result = null;
		Statement stmt = null;
		ResultSet rs = null;
		String RCAddress = null;
		String OS = null;
		stmt = con.createStatement();
		//�����û�ID��ø��û��ɲ�������������
		String sql = "SELECT hostID FROM userhost where userID=?";
		rs = dbcManager.query(sql, new String[]{userID});
		ArrayList<String> hostList = new ArrayList<String>();
		System.out.println("user contains hostID:::");
		while(rs.next()) {
			System.out.println(rs.getString(1));
			hostList.add(rs.getString(1));
		}
		if(hostList.contains(hostID))  {
			//��Ҫ�������������û��ɲ���������������
			result = new String[3];
			//���Ҫ����������IP
			sql = "SELECT hostIP,OS FROM hostinfo where hostID=? LIMIT 1";
			rs = dbcManager.query(sql, hostID);
			if(rs.next()) {
				System.out.println("hostIP:" + rs.getString(1));
				result[0] = rs.getString(1);
				OS = rs.getString(2);
				System.out.println("OS:" + OS);
			}
			//���Ҫ�����������ڵ�������ĵĵ�ַ 
			sql = "SELECT RCAddress FROM hostrc where hostID=?";
			rs = dbcManager.query(sql, hostID);
			if(rs.next()) {
				System.out.println("RCAddress:" + rs.getString(1));
				RCAddress = rs.getString(1);
				result[1] = RCAddress;
			}
			//���Ҫ��װ�������λ��
			sql = "SELECT softPath FROM rcinfo where RCAddress= ? and softName=? and OS=?";
			rs = dbcManager.query(sql, RCAddress,softName.toLowerCase(),OS);
			if(rs.next()) {
				System.out.println("softPath:" + rs.getString(1));
				result[2] = rs.getString(1);
			}
		}
		//�ͷ���Դ
		dbcManager.close();
		if(rs!=null)
		{
			try{
				rs.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(stmt!=null)
		{
			try{
				stmt.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**@author XQ
	 * �����û���������IP��ѯ���ݿ��ø���������Ӧ��������ĵ�ַ
	 * @param userID
	 * @param hostIP
	 * @return
	 * @throws SQLException
	 */
	public String[] getRCAddrByIP(String userID,String hostIP,String softName) throws SQLException {
		String[] result = null;
		String hostID = null;
		String RCAddress = null;
		String OS = null;
		ArrayList<String>  hostIDList = new ArrayList<String>();
		//��������ip��ø�������hostID
		Statement stmt = null;
		ResultSet rs = null;
		stmt = con.createStatement();
		String sql = "SELECT hostID,OS FROM hostinfo where hostIP=?";
		rs = dbcManager.query(sql, hostIP);
		if(rs.next()) {
			System.out.println("hostID:" + rs.getString(1));
			hostID = rs.getString(1);
			OS = rs.getString(2);
			System.out.println("OS:" + OS);
		}
		//����userID��ø��û��ɲ�������������
		sql = "SELECT hostID FROM userhost where userID=?";
		rs = dbcManager.query(sql, userID);
		System.out.println("user can operator hostList:");
		while(rs.next()) {
			System.out.println(rs.getString(1));
			hostIDList.add(rs.getString(1));
			
		}
		if(hostIDList.contains(hostID)) {
			//�����������û��ɲ���������������
			result = new String[2];
			//��ø��������ڵ�������ĵĵ�ַ
			sql = "SELECT RCAddress FROM hostrc where hostID=?";
			rs = dbcManager.query(sql, hostID);
			if(rs.next()) {
				System.out.println("RCAddress:" + rs.getString(1));
				RCAddress = rs.getString(1);
				result[0] = RCAddress;
				
			}
			//���Ҫ��װ�����·��
			sql = "SELECT softPath FROM rcinfo where RCAddress=? and softName=? and OS=?";
			rs = dbcManager.query(sql, RCAddress,softName.toLowerCase(),OS);
			if(rs.next()) {
				System.out.println("softPath:" + rs.getString(1));
				result[1] = rs.getString(1);
			}
			
		}
		//�ͷ���Դ
		dbcManager.close();
		if(rs!=null)
		{
			try{
				rs.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(stmt!=null)
		{
			try{
				stmt.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void test() throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		stmt = con.createStatement();
		String sql = "SELECT * FROM userinfo";
		rs = dbcManager.query(sql, null);
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
		
		sql = "SELECT * FROM hostinfo";
		rs = dbcManager.query(sql, null);
		while(rs.next()) {
			System.out.println(rs.getString(1)+":"+rs.getString(2));
		}
	
		sql = "SELECT * FROM hostrc";
		rs = dbcManager.query(sql, null);
		while(rs.next()) {
			System.out.println(rs.getString(1)+":"+rs.getString(2));
		}
		
		sql = "SELECT * FROM rcinfo";
		rs = dbcManager.query(sql, null);
		while(rs.next()) {
			System.out.println(rs.getString(1)+":"+rs.getString(2)+":" + rs.getString(3) + ":" + rs.getString(4));
		}
	
		sql = "SELECT * FROM userhost";
		rs = dbcManager.query(sql, null);
		while(rs.next()) {
			System.out.println(rs.getString(1)+":"+rs.getString(2));
		}
	}
	/**@author WZY
	 * �����û�����ID�õ� ���ڵ�����
	 * @param userID �û�ID
	 * @param hostID  ����ID
	 * @return
	 */
	public  String judge(String userID) throws SQLException {
		String result = null;
		ResultSet rs = null;
		Statement stmt = null;
		stmt = con.createStatement();
		//�����û�ID��ø��û�������
		System.out.println(userID+"---------");
		String sql = "SELECT * FROM userinfo where userID = '"+userID+"'";
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getString(1));
			result = rs.getString("pwd");
		}
		
		dbcManager.close();
		if(rs!=null)
		{
			try{
				rs.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(stmt!=null)
		{
			try{
				stmt.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) {
		
		DBOperation dbop = new DBOperation();
		
//		try {
//			dbop.getRCAddr("2");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			dbop.getRCAddrByID("2","3");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//		dbop.getRCAddrByID("hp","wyqAgent","mysql");
//		
//		} catch (SQLException e) {
//		e.printStackTrace();
//		}	
		
//		try {
//		dbop.getRCAddrByIP("hp","192.168.0.215","mysql");
//		
//		} catch (SQLException e) {
//		e.printStackTrace();
//		}	
//		
		try {
			dbop.updateHostIP("192.168.0.555", "192.168.0.122");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
