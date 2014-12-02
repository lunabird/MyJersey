package sample.DBOP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnManager {
	
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/cloudhost";
	private static String user = "root";
	private static String password = "123456";
	
	//���ݿ�������
	private static DBConnManager dbcMananger = null; 
	//���ݿ����Ӷ���
	private static Connection conn=null;
	//���ݿ�Ԥ�������
    private PreparedStatement ps=null;
	//�����
	private ResultSet rs=null;
	
	
	public DBConnManager() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("���ݿ�driver����");
		}
		try {
			conn =  DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			System.out.println("���ݿ�connection����");
		}
	}
	
	/**@author ZXQ
	 * ��ȡ���ݿ�������ʵ��
	 * @return
	 */
	 public static DBConnManager getInstance() {
		if(dbcMananger == null) {
			dbcMananger = new DBConnManager();
		}
		return dbcMananger;
	}
	
	/**@author ZXQ
	 * ��ȡ���ݿ����Ӷ���
	 * @return
	 */
	public static Connection getConnection() {
		return conn;
	}
	
	/**@author ZXQ
	 * @param sql ���ݿ��ѯ���
	 * @param paras �����б����Դ�����,Ҳ���Բ�������,������ΪNULL,���ַ���������ʽ���ݣ�
	 * @return ���ز�ѯ�Ľ����
	 */
	public ResultSet query(String sql,String... paras) {
		
		try {
			ps = conn.prepareStatement(sql);
			if(paras != null) {
				for(int i=0;i<paras.length;i++) {
					ps.setString(i+1, paras[i]);
				}
			}
			rs = ps.executeQuery();//ִ��query
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;	
	}
	
//	/**@author ZXQ
//	 * @param sql ���ݿ�����ɾ�������
//	 * @param paras �����б����Դ�����,Ҳ���Բ�������,������ΪNULL,���ַ���������ʽ���ݣ�
//	 * @return ������Ӱ�������
//	 **/
//	public int update(String sql,String...paras) {
//		int result = 0;
//		try {
//			ps = conn.prepareStatement(sql);
//			
//			if(paras != null) {
//				for(int i=0;i<paras.length;i++) {
//					ps.setString(i+1, paras[i]);
//				}
//			}
//			
//			result = ps.executeUpdate();//ִ��update
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		if(ps != null) {
//			try {
//				ps.close(); //�ͷ���Դ
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}			
//		}	
//		return result;
//	}
	
	
	/**@author ZXQ
	 * �ͷ���Դ���ͷ�ResultSet��PreparedStatement��
	 */ 
	public void close() {
		if(rs!=null)
		{
			try{
				rs.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(ps!=null)
		{
			try{
				ps.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**@author ZXQ
	 * �ͷ���Դ���ͷ�ResultSet,PreparedStatement��Connection��
	 */ 
	public void closeAll() {
		if(rs!=null)
		{
			try{
				rs.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(ps!=null)
		{
			try{
				ps.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(conn!=null)
		{
			try{
				conn.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
