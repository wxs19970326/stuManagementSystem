package com.neusoft.mis.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.neusoft.mis.entity.User;
import com.neusoft.mis.uitl.DBUtil;

/**
 * 用户业务逻辑类
 * 
 * @author ASUS
 *
 */
public class UserService {
	/**
	 * 根据用户名查询用户
	 */
	public User findUserByName(String userName) {
		DBUtil db = new DBUtil();
		User user = null;

		try {
			// 获取数据库连接
			db.getConnection();
			// 用PreparedStatement 发送SQL语句
			String sql = "SELECT * FROM userdetails WHERE username = ?";
			// 设置参数
			Object[] param = new Object[] { userName };
			// 执行查询
			ResultSet rs = db.executeQuery(sql, param);
			if (rs.next()) {
				// 将查询结果封装到对象中
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
	 * 根据用户名以及密码查询数据
	 */
	public User findUserByPwd(String userName,String pwd) {
		DBUtil db = new DBUtil();
		User user = null;

		try {
			// 获取数据库连接
			db.getConnection();
			// 用PreparedStatement 发送SQL语句
			String sql = "SELECT * FROM userdetails WHERE username = ? and password=?";
			// 设置参数
			Object[] param = new Object[] { userName,pwd };
			// 执行查询
			ResultSet rs = db.executeQuery(sql, param);
			if (rs.next()) {
				// 将查询结果封装到对象中
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
	 * 注册并保存用户信息的方法
	 */
	public boolean saveUser(User user) {
		boolean r = false;
		DBUtil db = new DBUtil();
		try {
			//连接数据库
			db.getConnection();
			//定义插入sql语言
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
