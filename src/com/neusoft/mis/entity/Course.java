package com.neusoft.mis.entity;

/**
 * 学生课程信息类
 * 
 * @author ASUS
 *
 */
public class Course {
	private String cno, cname, ccredit, cteacher;

	public Course() {

	}

	public Course(String cno, String cname, String ccredit, String cteacher) {
		this.cno = cno;
		this.cname = cname;
		this.ccredit = ccredit;
		this.cteacher = cteacher;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCcredit() {
		return ccredit;
	}

	public void setCcredit(String ccredit) {
		this.ccredit = ccredit;
	}

	public String getCteacher() {
		return cteacher;
	}

	public void setCteacher(String cteacher) {
		this.cteacher = cteacher;
	}

}
