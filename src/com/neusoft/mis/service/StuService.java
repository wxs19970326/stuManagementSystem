package com.neusoft.mis.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.neusoft.mis.entity.Student;
import com.neusoft.mis.uitl.DBUtil;

/**
 * 学生信息业务逻辑类
 * 
 * @author ASUS
 *
 */
public class StuService {

	/**
	 * 课程信息插入
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			db.closeAll();
		}
		return r;

	}
	
	/*
	 * 全部学生信息查询方法
	 */
	public ResultSet readStudent() {
		DBUtil db = new DBUtil();
		ResultSet rs=null;
		try {
			// 获取数据库链接
			Connection conn=db.getConnection();
			// 查询匹配的日志，设置ResultSet可以使用除了next()之外的方法操作结果集
			Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			String sql = "SELECT * from students";
			rs = st.executeQuery(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 学生登陆用户名密码插入
	 */
	/*
	 * public boolean saveStuUser(Student student) { boolean r = false; DBUtil db =
	 * new DBUtil(); try { db.getConnection(); String sql =
	 * "INSERT INTO userdetails (username,password,power) VALUES (?,?,?)"; Object[]
	 * param = new Object[] { student.getSno(), student.getSpwd(),
	 * student.getSpower() }; if (db.executeUpdate(sql, param) > 0) { r = true; } }
	 * catch (SQLException e) { // TODO 自动生成的 catch 块 e.printStackTrace(); } finally
	 * { db.closeAll(); } return r;
	 * 
	 * }
	 */

}
