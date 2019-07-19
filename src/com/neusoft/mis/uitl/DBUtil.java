package com.neusoft.mis.uitl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import com.neusoft.mis.constant.Constant;
import com.neusoft.mis.entity.User;

/**
 * DBUtil共工具类 用于数据库的连接和增删改查
 * 				                以及修改密码
 * 
 * @author ASUS
 *
 */
public class DBUtil {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/**
	 * 得到数据库连接
	 * 
	 * @throws SQLException
	 */

	public Connection getConnection() throws SQLException {
		try {
			Class.forName(Constant.DRIVER);
			conn = DriverManager.getConnection(Constant.URL, Constant.USER, Constant.PASSWORD);
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			throw new SQLException("驱动错误或连接失败");
		}
	}

	/**
	 * 释放资源
	 */
	public void closeAll() {
		// 如果rs不空，关闭rs
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		// 如果pstmt不空，关闭pstmt
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		// 如果conn不空，关闭conn
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	/**
	 * 执行SQL语句可以进行查询操作
	 */
	public ResultSet executeQuery(String preparedSql, Object[] param) {

		try {
			// 得到prepareStstement对象
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
	 * 修改密码方法
	 */
	public void reSet(User user) throws SQLException {
		//sql更新语言
		pstmt = conn.prepareStatement("update userdetails set password =?");
		//将password设置为user.getPassword()
		pstmt.setString(1, user.getPassword());
		//调用PrepareStatment对象pstmt的方法excuteUpdate()执行sql语句
		pstmt.executeUpdate();
	}

	/**
	 * 执行SQL语句，可以进行增删改，不能查询
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
			//返回受影响的记录条数
			num = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return num;
	}
 
}
