package com.neusoft.mis.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.neusoft.mis.entity.Grade;
import com.neusoft.mis.uitl.DBUtil;

/**
 * 成绩业务逻辑类
 */
public class GradeService {

	/**
	 * 成绩插入方法
	 */
	public boolean saveGrade(Grade grade) {
		boolean r = false;
		//创建工具对象
		DBUtil db = new DBUtil();
		try {
			//连接数据库
			db.getConnection();
			//定义sql语言，插入成绩
			String sql = "INSERT INTO grades (sno,sname,gjava,gshuju,gcaozuo,gzucheng,gsuanfa,gEnglish,gOrical,ggailv) VALUES (?,?,?,?,?,?,?,?,?,?)";
			//将传来的grade对象的属性一一的添加到数组
			Object[] param = new Object[] { grade.getSno(), grade.getSname(), grade.getGjava(), grade.getGshuju(),
					grade.getGcaozuo(), grade.getGzucheng(), grade.getGsuanfa(), grade.getgEglish(), grade.getgOrical(),
					grade.getGgailv() };
			//如果影响记录结果大于0那么返回真
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
	 * 全部成绩查询方法
	 */
/*	public ResultSet readGrade() {
		DBUtil db = new DBUtil();
		ResultSet rs=null;
		try {
			// 获取数据库链接
			Connection conn=db.getConnection();
			// 查询匹配的日志，设置ResultSet可以使用除了next()之外的方法操作结果集
			Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//查询全部的语句
			String sql = "SELECT * from grades";
			//执行sql语言
			rs = st.executeQuery(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}*/
		
}
