package com.neusoft.mis.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.neusoft.mis.entity.Student;
import com.neusoft.mis.uitl.DBUtil;

/**
 * ѧ����Ϣҵ���߼���
 * 
 * @author ASUS
 *
 */
public class StuService {

	/**
	 * �γ���Ϣ����
	 */
	public boolean saveInformation(Student student) {
		boolean r = false;
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			String sql = "INSERT INTO students (sno,sname,szy,sclass,scall) VALUES (?,?,?,?,?)";
			Object[] param = new Object[] { student.getSno(), student.getSname(), student.getSzy(), student.getSclass(),
					student.getScall() };
			if (db.executeUpdate(sql, param) > 0) {
				r = true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			db.closeAll();
		}
		return r;

	}
	
	/*
	 * ȫ��ѧ����Ϣ��ѯ����
	 */
	public ResultSet readStudent() {
		DBUtil db = new DBUtil();
		ResultSet rs=null;
		try {
			// ��ȡ���ݿ�����
			Connection conn=db.getConnection();
			// ��ѯƥ�����־������ResultSet����ʹ�ó���next()֮��ķ������������
			Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			String sql = "SELECT * from students";
			rs = st.executeQuery(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * ѧ����½�û����������
	 */
	/*
	 * public boolean saveStuUser(Student student) { boolean r = false; DBUtil db =
	 * new DBUtil(); try { db.getConnection(); String sql =
	 * "INSERT INTO userdetails (username,password,power) VALUES (?,?,?)"; Object[]
	 * param = new Object[] { student.getSno(), student.getSpwd(),
	 * student.getSpower() }; if (db.executeUpdate(sql, param) > 0) { r = true; } }
	 * catch (SQLException e) { // TODO �Զ����ɵ� catch �� e.printStackTrace(); } finally
	 * { db.closeAll(); } return r;
	 * 
	 * }
	 */

}
