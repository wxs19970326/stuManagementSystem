package com.neusoft.mis.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.neusoft.mis.entity.Course;
import com.neusoft.mis.uitl.DBUtil;
/**
 * �γ���Ϣҵ���߼���
 * @author ASUS
 *
 */
public class CourseService {

	/**
	 * �γ���Ϣ����
	 */
	public boolean saveCourse(Course course) {
		boolean r = false;
		DBUtil db = new DBUtil();
		try {
			//�������ݿ�
			db.getConnection();
			//��������
			String sql = "INSERT INTO courses (cno,cname,ccredit,cteacher) VALUES (?,?,?,?)";
			//����ѯ�����ӵ�����
			Object[] param = new Object[] { course.getCno(), course.getCname(), course.getCcredit(),
					course.getCteacher() };
			//�����ѯ�������0��ô������
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
	 * ȫ���γ̲�ѯ����
	 */
	public ResultSet readCourse() {
		DBUtil db = new DBUtil();
		ResultSet rs=null;
		try {
			// ��ȡ���ݿ�����
			Connection conn=db.getConnection();
			// ��ѯƥ�����־������ResultSet����ʹ�ó���next()֮��ķ������������
			Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//ִ��sql����
			String sql = "SELECT * from courses";
			rs = st.executeQuery(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

}
