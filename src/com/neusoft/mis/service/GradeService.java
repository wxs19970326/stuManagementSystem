package com.neusoft.mis.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.neusoft.mis.entity.Grade;
import com.neusoft.mis.uitl.DBUtil;

/**
 * �ɼ�ҵ���߼���
 */
public class GradeService {

	/**
	 * �ɼ����뷽��
	 */
	public boolean saveGrade(Grade grade) {
		boolean r = false;
		//�������߶���
		DBUtil db = new DBUtil();
		try {
			//�������ݿ�
			db.getConnection();
			//����sql���ԣ�����ɼ�
			String sql = "INSERT INTO grades (sno,sname,gjava,gshuju,gcaozuo,gzucheng,gsuanfa,gEnglish,gOrical,ggailv) VALUES (?,?,?,?,?,?,?,?,?,?)";
			//��������grade���������һһ����ӵ�����
			Object[] param = new Object[] { grade.getSno(), grade.getSname(), grade.getGjava(), grade.getGshuju(),
					grade.getGcaozuo(), grade.getGzucheng(), grade.getGsuanfa(), grade.getgEglish(), grade.getgOrical(),
					grade.getGgailv() };
			//���Ӱ���¼�������0��ô������
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
	 * ȫ���ɼ���ѯ����
	 */
/*	public ResultSet readGrade() {
		DBUtil db = new DBUtil();
		ResultSet rs=null;
		try {
			// ��ȡ���ݿ�����
			Connection conn=db.getConnection();
			// ��ѯƥ�����־������ResultSet����ʹ�ó���next()֮��ķ������������
			Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//��ѯȫ�������
			String sql = "SELECT * from grades";
			//ִ��sql����
			rs = st.executeQuery(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}*/
		
}
