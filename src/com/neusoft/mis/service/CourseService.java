package com.neusoft.mis.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.neusoft.mis.entity.Course;
import com.neusoft.mis.uitl.DBUtil;
/**
 * 课程信息业务逻辑类
 * @author ASUS
 *
 */
public class CourseService {

	/**
	 * 课程信息插入
	 */
	public boolean saveCourse(Course course) {
		boolean r = false;
		DBUtil db = new DBUtil();
		try {
			//连接数据库
			db.getConnection();
			//插入语言
			String sql = "INSERT INTO courses (cno,cname,ccredit,cteacher) VALUES (?,?,?,?)";
			//将查询结果添加到数组
			Object[] param = new Object[] { course.getCno(), course.getCname(), course.getCcredit(),
					course.getCteacher() };
			//如果查询结果大于0那么返回真
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
	 * 全部课程查询方法
	 */
	public ResultSet readCourse() {
		DBUtil db = new DBUtil();
		ResultSet rs=null;
		try {
			// 获取数据库链接
			Connection conn=db.getConnection();
			// 查询匹配的日志，设置ResultSet可以使用除了next()之外的方法操作结果集
			Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//执行sql语言
			String sql = "SELECT * from courses";
			rs = st.executeQuery(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

}
