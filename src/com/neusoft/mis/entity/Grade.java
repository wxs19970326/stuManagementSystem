package com.neusoft.mis.entity;

/**
 * 学生成绩类
 * 包括所需要的成绩的所有属性
 * @author ASUS
 *
 */
public class Grade {
	private String sno, sname;
	private int gjava, gshuju, gcaozuo, gzucheng, gsuanfa, gEglish, gOrical, ggailv;

	public Grade() {

	}

	public Grade(String sno, String sname, int gjava, int gshuju, int gcaozuo, int gzucheng, int gsuanfa, int gEglish,
			int gOrical, int ggailv) {
		this.sno = sno;
		this.sname = sname;
		this.gjava = gjava;
		this.gshuju = gshuju;
		this.gcaozuo = gcaozuo;
		this.gzucheng = gzucheng;
		this.gsuanfa = gsuanfa;
		this.gEglish = gEglish;
		this.gOrical = gOrical;
		this.ggailv = ggailv;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getGjava() {
		return gjava;
	}

	public void setGjava(int gjava) {
		this.gjava = gjava;
	}

	public int getGshuju() {
		return gshuju;
	}

	public void setGshuju(int gshuju) {
		this.gshuju = gshuju;
	}

	public int getGcaozuo() {
		return gcaozuo;
	}

	public void setGcaozuo(int gcaozuo) {
		this.gcaozuo = gcaozuo;
	}

	public int getGzucheng() {
		return gzucheng;
	}

	public void setGzucheng(int gzucheng) {
		this.gzucheng = gzucheng;
	}

	public int getGsuanfa() {
		return gsuanfa;
	}

	public void setGsuanfa(int gsuanfa) {
		this.gsuanfa = gsuanfa;
	}

	public int getgEglish() {
		return gEglish;
	}

	public void setgEglish(int gEglish) {
		this.gEglish = gEglish;
	}

	public int getgOrical() {
		return gOrical;
	}

	public void setgOrical(int gOrical) {
		this.gOrical = gOrical;
	}

	public int getGgailv() {
		return ggailv;
	}

	public void setGgailv(int ggailv) {
		this.ggailv = ggailv;
	}

}
