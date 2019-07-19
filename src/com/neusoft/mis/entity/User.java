package com.neusoft.mis.entity;

/**
 * 用户实体类
 * 
 * @author ASUS
 *
 */
public class User {

	// 用户名
	private String username;
	// 密码
	private String password;
	// 登陆方式
	private int power;

	public User() {

	}

	public User(String username, String password, int power) {
		this.username = username;
		this.password = password;
		this.power = power;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

}
