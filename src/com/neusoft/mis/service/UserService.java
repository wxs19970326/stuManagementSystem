package com.neusoft.mis.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.neusoft.mis.entity.User;
import com.neusoft.mis.uitl.DBUtil;

/**
 * �û�ҵ���߼���
 * 
 * @author ASUS
 *
 */
public class UserService {
	/**
	 * �����û�����ѯ�û�
	 */
	public User findUserByName(String userName) {
		DBUtil db = new DBUtil();
		User user = null;

		try {
			// ��ȡ���ݿ�����
			db.getConnection();
			// ��PreparedStatement ����SQL���
			String sql = "SELECT * FROM userdetails WHERE username = ?";
			// ���ò���
			Object[] param = new Object[] { userName };
			// ִ�в�ѯ
			ResultSet rs = db.executeQuery(sql, param);
			if (rs.next()) {
				// ����ѯ�����װ��������
				user = new User(rs.getString(1), rs.getString(2), rs.getInt(3));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeAll();
		}
		return user;
	}
	
	/**
	 * �����û����Լ������ѯ����
	 */
	public User findUserByPwd(String userName,String pwd) {
		DBUtil db = new DBUtil();
		User user = null;

		try {
			// ��ȡ���ݿ�����
			db.getConnection();
			// ��PreparedStatement ����SQL���
			String sql = "SELECT * FROM userdetails WHERE username = ? and password=?";
			// ���ò���
			Object[] param = new Object[] { userName,pwd };
			// ִ�в�ѯ
			ResultSet rs = db.executeQuery(sql, param);
			if (rs.next()) {
				// ����ѯ�����װ��������
				user = new User(rs.getString(1), rs.getString(2), rs.getInt(3));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeAll();
		}
		return user;
	}
	
	/**
	 * ע�Ტ�����û���Ϣ�ķ���
	 */
	public boolean saveUser(User user) {
		boolean r = false;
		DBUtil db = new DBUtil();
		try {
			//�������ݿ�
			db.getConnection();
			//�������sql����
			String sql = "INSERT INTO userdetails (username,password,power) VALUES (?,?,?)";
			Object[] param = new Object[] { user.getUsername(), user.getPassword(), user.getPower() };
			if (db.executeUpdate(sql, param) > 0) {
				r = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeAll();
		}
		return r;

	}

}
