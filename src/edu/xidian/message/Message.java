package edu.xidian.message;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @ClassName: Message
 * @Description: ��Ϣ��
 * @author: wangyannan
 * @date: 2014-11-12 ����10:21:06
 */
public class Message implements Serializable {
	/**
	 * @fieldName: type
	 * @fieldType: MsgType
	 * @Description: ��Ϣ����
	 */
	private MsgType type;
	/**
	 * @fieldName: userID
	 * @fieldType: String
	 * @Description: �û�ID
	 */
	private String userID;
	/**
	 * @fieldName: values
	 * @fieldType: String[]
	 * @Description: �����б�
	 */
	private Object values;	
	
	/** 
	 * @Title:Message
	 * @Description:��Ϣ���췽�� 
	 * @param type
	 * @param userID
	 * @param time
	 * @param values 
	 */
	public Message(MsgType type, String userID,Object values) {
		this.type = type;
		this.userID = userID;
		this.values=values;
	}

	/**
	 * @Title: getType
	 * @Description: ��ȡ��Ϣ����
	 * @return: MsgType
	 */
	public MsgType getType() {
		return type;
	}
	
	/** 
	 * @Title: setType 
	 * @Description: ������Ϣ����
	 * @param t
	 * @return: void
	 */
	public void setType(MsgType t) {
		this.type = t;
	}
	
	/** 
	 * @Title: getUserID 
	 * @Description: ��ȡ�û�ID
	 * @return: String
	 */
	public String getUserID() {
		return userID;
	}
	
	/** 
	 * @Title: getValues 
	 * @Description: ��ȡ�û�����Ĳ����б�
	 * @return: String[]
	 */
	public Object getValues(){
		return values;
	}

}
