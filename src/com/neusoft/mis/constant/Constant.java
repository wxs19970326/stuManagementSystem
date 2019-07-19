package com.neusoft.mis.constant;

public class Constant {
	/**
	 * 登陆界面的宽、高、标题
	 */
	public static final int LOGIN_WIDTH = 400;
	public static final int LOGIN_HEIGHT = 320;
	public static final String LOGIN_TITLE = "登陆";

	/**
	 * 注册界面的宽、高、标题
	 */
	public static final int REGISTER_WIDTH = 400;
	public static final int REGISTER_HEIGHT = 350;
	public static final String REGISTER_TITLE = "注册";

	/**
	 * 登陆方式为老师的用户界面宽、高、标题
	 */
	public static final int TEACHER_WIDTH = 700;
	public static final int TEACHER_HEIGHT = 500;
	public static final String TEACHER_TITLE = "学生管理系统客户端";
	// 课程信息插入的宽高标题
	public static final int TEACHER_COURSEINFOR_WIDTH = 500;
	public static final int TEACHER_COURSEINFOR_HEIGHT = 350;
	public static final String TEACHER_COURSEINFOR_TITLE = "课程信息插入";
	// 成绩插入
	public static final int TEACHER_GRADE_WIDTH = 550;
	public static final int TEACHER_GRADE_HEIGHT = 400;
	public static final String TEACHER_GRADE_TITLE = "成绩录入";
	// 修改密码窗体宽高标题
	public static final int TEACHER_RESETPWD_WIDTH = 400;
	public static final int TEACHER_RESETPWD_HEIGHT = 300;
	public static final String TEACHER_RESETPWD_TITLE = "修改密码";
	// 信息查询
	public static final int TEACHER_SELECT_WIDTH = 600;
	public static final int TEACHER_SELECT_HEIGHT = 400;
	public static final String TEACHER_SELECT_TITLE = "信息查询";
	
	/**
	 * 用户管理界面常量
	 */
	public static final int USER_WIDTH = 450;
	public static final int USER_HEIGHT = 350;
	public static final String USER_TITLE = "用户管理表";
	
	/**
	 * 课程信息查询表
	 */
	public static final int COURSETABLE_WIDTH = 600;
	public static final int COURSETABLE_HEIGHT = 400;
	public static final String COURSETABLE_TITLE = "课程信息表";
	
	/**
	 * 登陆方式为学生的用户界面宽、高、标题
	 */
	public static final int STUDENT_WIDTH = 600;
	public static final int STUDENT_HEIGHT = 400;
	public static final String STUDENT_TITLE = "教务在线";

	/**
	 * 数据库常量
	 */
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8";
	public static final String USER = "root";
	public static final String PASSWORD = "123456";
}
