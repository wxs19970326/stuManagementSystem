package com.neusoft.mis.entity;

/**
 * 学生信息类
 * 
 * @author ASUS
 *
 */
public class Student {
	private String sno, sname, szy, sclass, scall, spwd, spower;

	public Student() {

	}

	public Student(String sno, String sname, String szy, String sclass, String scall) {
		this.sno = sno;
		this.sname = sname;
		this.szy = szy;
		this.sclass = sclass;
		this.scall = scall;
	}

	public Student(String sno) {
		this.sno = sno;
		this.spwd = "123456";
		this.spower = "1";

	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String getSpower() {
		return spower;
	}

	public void setSpower(String spower) {
		this.spower = spower;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSzy() {
		return szy;
	}

	public void setSzy(String szy) {
		this.szy = szy;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}

	public String getScall() {
		return scall;
	}

	public void setScall(String scall) {
		this.scall = scall;
	}

}
