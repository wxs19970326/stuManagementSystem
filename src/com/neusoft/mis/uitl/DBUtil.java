package com.neusoft.mis.uitl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import com.neusoft.mis.constant.Constant;
import com.neusoft.mis.entity.User;

/**
 * DBUtil�������� �������ݿ�����Ӻ���ɾ�Ĳ�
 * 				                �Լ��޸�����
 * 
 * @author ASUS
 *
 */
public class DBUtil {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * �õ����ݿ�����
	 * 
	 * @throws SQLException
	 */

	public Connection getConnection() throws SQLException {
		try {
			Class.forName(Constant.DRIVER);
			conn = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			throw new SQLException("�������������ʧ��");
		}
	}

	/**
	 * �ͷ���Դ
	 */
	public void closeAll() {
		// ���rs���գ��ر�rs
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}

		// ���pstmt���գ��ر�pstmt
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}

		// ���conn���գ��ر�conn
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}

	/**
	 * ִ��SQL�����Խ��в�ѯ����
	 */
	public ResultSet executeQuery(String preparedSql, Object[] param) {

		try {
			// �õ�prepareStstement����
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return rs;
	}
	
	/**
	 * �޸����뷽��
	 */
	public void reSet(User user) throws SQLException {
		//sql��������
		pstmt = conn.prepareStatement("update userdetails set password =?");
		//��password����Ϊuser.getPassword()
		pstmt.setString(1, user.getPassword());
		//����PrepareStatment����pstmt�ķ���excuteUpdate()ִ��sql���
		pstmt.executeUpdate();
	}

	/**
	 * ִ��SQL��䣬���Խ�����ɾ�ģ����ܲ�ѯ
	 */
	public int executeUpdate(String preparedSql, Object[] param) {
		int num = 0;
		try {
			
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			//������Ӱ��ļ�¼����
			num = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return num;
	}
 
}
