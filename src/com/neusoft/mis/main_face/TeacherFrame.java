package com.neusoft.mis.main_face;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.neusoft.mis.constant.Constant;
import com.neusoft.mis.entity.Course;
import com.neusoft.mis.entity.Student;
import com.neusoft.mis.entity.User;
import com.neusoft.mis.main_face.StudentFrame.BtnRemoveListener;
import com.neusoft.mis.main_face.StudentFrame.BtnUpdatePwdListener;
//import com.neusoft.mis.main_face.CourseInfor.BtnCouOkListener;
import com.neusoft.mis.service.CourseService;
import com.neusoft.mis.service.StuService;
import com.neusoft.mis.service.UserService;
import com.neusoft.mis.table_demo.UserTableDemo;
import com.neusoft.mis.uitl.DBUtil;

/**
 * 老师登陆主界面
 * 
 * @author ASUS
 *
 */
public class TeacherFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3725713683054780797L;
	// 声明主面板
	private CardLayout card;
	private JPanel pmain, p, p1, p2, p3, p4, p5, p6;
	// 声明主面板
	private JPanel cp, cp1, cp2, cp3, cp4, cp5;
	// 声明菜单栏组件
	private JMenuBar menuBar;
	// 声明菜单组件
	private JMenu menuCaoz, menuHelp;
	// 声明菜单项目
	private JMenuItem loginItem, loginOutItem, exitItem, aboutItem;
	// 声明工具栏
	private JToolBar toolBar;
	// 声明按钮
	private JButton btnAdd1, btnAdd2, btnSelect, btnSave, btnAddGra, btnreSet, btnOk, btnRemove;
	// 声明标签
	private JLabel lbCname, lbCno, lbCcredit, lbCteacher;
	// 声明按钮
	private JButton btnCouOk;
	// 声明组合框
	private JComboBox cmbCno, cmbCname, cmbCcredit, cmbCteacher;
	// 声明课程信息对象
	private Course course;
	private CourseService courseService;

	/**
	 * 修改密码组件
	 */
	// 声明主面板
	private JPanel rp, rp1, rp2, rp3, rp4, rp5;
	// 声明标签
	private JLabel lbUser, lbPwd, lbNewPwd, lbRePwd;
	// 声明文本框
	private JTextField txtUser;
	// 声明密码框
	private JPasswordField pfPwd, pfNewPwd, pfRePwd;
	// 声明按钮
	private JButton rbtnIsUpdate, rbtnRemove;
	/**
	 * 姓名、学号、专业、班级、电话标签声明
	 */
	private JLabel lbname;
	private JLabel lbno;
	private JLabel lbzy;
	private JLabel lbclass;
	private JLabel lbcall;
	// 声明文本框
	private JTextField txtno, txtname, txtzy, txtclass, txtcall;
	// 图标
	private ImageIcon img;
	// 声明学生对象
	private Student student;
	private StuService stuService;

	// 声明用户对象
	private User user;
	private UserService userService;

	public TeacherFrame() {
		card = new CardLayout();
		pmain = new JPanel(card);
		// 实例化学生业务逻辑对象
		stuService = new StuService();
		// 初始化CourseService对象
		courseService = new CourseService();
		/**
		 * 图标初始化
		 */
		img = new ImageIcon("src\\img\\client.png");

		// 实例化菜单栏
		menuBar = new JMenuBar();
		// 实例化菜单
		menuCaoz = new JMenu("操作");
		menuHelp = new JMenu("帮助");
		// 将菜单添加到菜单栏
		menuBar.add(menuCaoz);
		menuBar.add(menuHelp);
		// 实例化菜单项目并注册监听
		loginItem = new JMenuItem("当前登录",new ImageIcon("src\\img\\login.png"));
		loginItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "欢迎“" + Login.username + "”用户！！", "当前登录",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		loginOutItem = new JMenuItem("切换用户",new ImageIcon("src\\img\\btnReset.png"));
		loginOutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TeacherFrame.this.setVisible(false);
				new Login();
			}
		});

		exitItem = new JMenuItem("退出",new ImageIcon("src\\img\\exit.png"));
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "您确定要退出系统吗？", "退出系统",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		aboutItem = new JMenuItem("关于系统");
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "版本1.0\n作者:王晓森\n版权:Java期末项目", "关于",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// 将菜单项目添加到菜单
		menuCaoz.add(loginItem);
		menuCaoz.add(loginOutItem);
		menuCaoz.add(exitItem);
		menuHelp.add(aboutItem);

		// 声明工具栏
		toolBar = new JToolBar();
		// 声明按钮
		btnAdd1 = new JButton("添加学生信息", new ImageIcon("src\\img\\add1.png"));
		btnAdd2 = new JButton("添加课程信息", new ImageIcon("src\\img\\add2.png"));
		btnAddGra = new JButton("添加成绩", new ImageIcon("src\\img\\addGra.png"));
		btnSelect = new JButton("查询信息", new ImageIcon("src\\img\\select.png"));
		btnSave = new JButton("用户管理", new ImageIcon("src\\img\\user.png"));
		btnreSet = new JButton("修改密码", new ImageIcon("src\\img\\reset.png"));
		// 添加工具栏按钮监听类
		btnAdd1.addActionListener(new BtnListener());
		btnAdd2.addActionListener(new BtnListener());
		btnSelect.addActionListener(new BtnListener());
		btnSave.addActionListener(new BtnListener());
		btnAddGra.addActionListener(new BtnListener());
		btnreSet.addActionListener(new BtnListener());

		// 将按钮添加到工具栏
		toolBar.add(btnAdd1);
		toolBar.add(btnAdd2);
		toolBar.add(btnAddGra);
		toolBar.add(btnSelect);
		toolBar.add(btnreSet);
		toolBar.add(btnSave);

		/**
		 * 学生信息
		 */
		// 标签初始化
		lbname = new JLabel("姓名：");
		lbno = new JLabel("学号：");
		lbzy = new JLabel("专业：");
		lbclass = new JLabel("班级：");
		lbcall = new JLabel("电话：");

		// 文本框初始化
		txtname = new JTextField(20);
		txtno = new JTextField(20);
		txtzy = new JTextField(20);
		txtclass = new JTextField(20);
		txtcall = new JTextField(20);

		// 初始化6行1列的主面板
		p = new JPanel(new GridLayout(6, 1));

		// 初始化按钮
		btnOk = new JButton("插入",new ImageIcon("src\\img\\ok.png"));
		btnRemove = new JButton("清空",new ImageIcon("src\\img\\remove.png"));
		// 添加按钮监听
		btnOk.addActionListener(new BtnOkListener());
		btnRemove.addActionListener(new BtnRemoveListener());

		// 面板初始化添加
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 25));
		p1.add(lbno);
		p1.add(txtno);
		p.add(p1);

		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
		p2.add(lbname);
		p2.add(txtname);
		p.add(p2);

		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		p3.add(lbzy);
		p3.add(txtzy);
		p.add(p3);

		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		p4.add(lbclass);
		p4.add(txtclass);
		p.add(p4);

		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		p5.add(lbcall);
		p5.add(txtcall);
		p.add(p5);

		p6 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		p6.add(btnOk);
		p6.add(btnRemove);
		p.add(p6);

		/**
		 * 课程
		 */
		// 初始化按钮
		// 添加监听器
		btnCouOk = new JButton("插入",new ImageIcon("src\\img\\ok.png"));
		// btnRemove = new JButton("清空");
		btnCouOk.addActionListener(new BtnCouOkListener());

		// 初始化标签
		lbCno = new JLabel("课程代码 ：");
		lbCname = new JLabel("课  程  名 ：");
		lbCcredit = new JLabel("学        分 ：");
		lbCteacher = new JLabel("代课教师 ：");

		// 将面板实例化
		cp = new JPanel(new GridLayout(5, 1));

		// 组合框初始化
		cmbCno = new JComboBox(new String[] { "01                                               ", "02", "03", "11",
				"12", "13", "21", "22" });
		cmbCno.setSize(8, 20);
		cmbCname = new JComboBox(new String[] { "JAVA", "数据库原理 ", "操作系统基本原理                 ", "计算机组成原理  ", "算法",
				"大学英语", "Orical", "概率论" });
		cmbCcredit = new JComboBox(new String[] { "2                                                  ", "3", "4" });
		cmbCteacher = new JComboBox(new String[] { "白老师", "乔老师", "高老师", "曹老师", "忽老师", "刘老师", "赵老师",
				"胡老师                                        " });
		cmbCno.setEditable(true);
		cmbCname.setEditable(true);
		cmbCcredit.setEditable(true);
		cmbCteacher.setEditable(true);

		cp1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		cp1.add(lbCno);
		cp1.add(cmbCno);
		cp.add(cp1);

		cp2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		cp2.add(lbCname);
		cp2.add(cmbCname);
		cp.add(cp2);

		cp3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		cp3.add(lbCcredit);
		cp3.add(cmbCcredit);
		cp.add(cp3);

		cp4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		cp4.add(lbCteacher);
		cp4.add(cmbCteacher);
		cp.add(cp4);

		cp5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		cp5.add(btnCouOk);
		cp.add(cp5);

		/**
		 * 修改密码
		 */

		rp = new JPanel(new GridLayout(5, 1));

		// 初始化标签
		lbUser = new JLabel("用  户  名：");
		lbPwd = new JLabel("旧  密  码：");
		lbNewPwd = new JLabel("新  密  码：");
		lbRePwd = new JLabel("确认密码：");

		// 初始化文本框
		txtUser = new JTextField(20);
		txtUser.setText(Login.username);
		// 初始化密码框
		pfPwd = new JPasswordField(20);
		pfNewPwd = new JPasswordField(20);
		pfRePwd = new JPasswordField(20);
		pfPwd.setEchoChar('*');
		pfNewPwd.setEchoChar('*');
		pfRePwd.setEchoChar('*');
		// 初始化按钮
		rbtnIsUpdate = new JButton("修改",new ImageIcon("src\\img\\reset.png"));
		rbtnRemove = new JButton("清空",new ImageIcon("src\\img\\remove.png"));
		// 添加按钮监听器
		rbtnRemove.addActionListener(new BtnRemoveListener());
		rbtnIsUpdate.addActionListener(new BtnUpdatePwdListener());

		rp1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		rp1.add(lbUser);
		rp1.add(txtUser);
		rp.add(rp1);

		rp2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		rp2.add(lbPwd);
		rp2.add(pfPwd);
		rp.add(rp2);

		rp3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		rp3.add(lbNewPwd);
		rp3.add(pfNewPwd);
		rp.add(rp3);

		rp4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		rp4.add(lbRePwd);
		rp4.add(pfRePwd);
		rp.add(rp4);

		rp5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
		rp5.add(rbtnIsUpdate);
		rp5.add(rbtnRemove);
		rp.add(rp5);

		pmain.add(p, "addStu");
		pmain.add(cp, "addCou");
		pmain.add(rp, "reset");
		// 将主面板添加到主容器
		this.add(pmain);
		// 设置客户端图标
		this.setIconImage(img.getImage());
		// 将菜单栏设置到窗体中
		this.setJMenuBar(menuBar);
		// 将工具栏添加到窗体中
		this.add(toolBar, BorderLayout.NORTH);
		// 设置标题
		this.setTitle(Constant.TEACHER_TITLE);
		// 设置宽高
		this.setSize(Constant.TEACHER_WIDTH, Constant.TEACHER_HEIGHT);
		// 窗体位置居中
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		// 窗体大小不可改变
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 工具栏按钮监听器类
	 */
	public class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == btnAdd1) {
				//添加学生信息窗体
				card.show(pmain, "addStu");
			}
			if (source == btnAdd2) {
				// 添加课程信息窗体
				card.show(pmain, "addCou");
			}
			if (source == btnSelect) {
				//查询窗体
				new Select();
			}
			if (source == btnSave) {
				//用户窗体
				new UserTableDemo();
			}
			if (source == btnAddGra) {
				//添加成绩信息窗体
				new GradeFrame();
			}
			if (source == btnreSet) {
				// 修改密码窗体
				card.show(pmain, "reset");
			}
		}

	}

	/**
	 * 插入按钮监听类
	 */
	public class BtnOkListener implements ActionListener {
		private Component jPanel;

		@Override
		public void actionPerformed(ActionEvent e) {
			// 输入的学号
			String strno = txtno.getText();
			if (strno == null || strno.equals("")) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "学号不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			// 输入的姓名
			String strname = txtname.getText();
			if (!(strno == null || strno.equals("")) && (strname == null || strname.equals(""))) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "姓名不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			// 输入专业
			String strzy = txtzy.getText();
			if (!(strno == null || strno.equals("")) && !(strname == null || strname.equals(""))
					&& (strzy == null || strzy.equals(""))) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "专业不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			// 输入班级
			String strclass = txtclass.getText();
			if (!(strno == null || strno.equals("")) && !(strname == null || strname.equals(""))
					&& !(strzy == null || strzy.equals("")) && (strclass == null || strclass.equals(""))) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "班级不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			// 输入电话
			String strcall = txtcall.getText();
			if (!(strno == null || strno.equals("")) && !(strname == null || strname.equals(""))
					&& !(strzy == null || strzy.equals("")) && !(strclass == null || strclass.equals(""))
					&& (strcall == null || strcall.equals(""))) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "电话不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}

			/**
			 * 保存信息
			 */
			if (!(strno == null || strno.equals("")) && !(strname == null || strname.equals(""))
					&& !(strzy == null || strzy.equals("")) && !(strclass == null || strclass.equals(""))
					&& !(strcall == null || strcall.equals(""))) {
				// 将输入的信息封装到学生对象
				student = new Student(strno, strname, strzy, strclass, strcall);
				if (stuService.saveInformation(student)) {
					// 提示框
					JOptionPane.showMessageDialog(jPanel, "保存成功！", "温馨提示", JOptionPane.WARNING_MESSAGE);
					// 保存成功以后调用清除文本框的方法清空文本框
					removeTxt();
				} else {
					JOptionPane.showMessageDialog(jPanel, "学号重复，保存失败！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
				/*
				 * userStu = new Student(strno); if (stuService.saveStuUser(userStu)) { // 提示框
				 * JOptionPane.showMessageDialog(jPanel, "学生用户已注册，初始密码为123456！", "温馨提示",
				 * JOptionPane.WARNING_MESSAGE); removeTxt(); } else {
				 * JOptionPane.showMessageDialog(jPanel, "保存失败！", "温馨提示",
				 * JOptionPane.WARNING_MESSAGE); }
				 */
			}

		}

	}

	/**
	 * 清空按钮监听类
	 */
	public class BtnRemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == rbtnRemove) {
				pfPwd.setText("");
				pfNewPwd.setText("");
				pfRePwd.setText("");
			}
			if (e.getSource() == btnRemove) {
				removeTxt();
			}
		}
	}

	/**
	 * 修改密码按钮监听类
	 */
	public class BtnUpdatePwdListener implements ActionListener {

		private Component jPanel;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			userService = new UserService();
			String strPwd = String.valueOf(pfPwd.getPassword());

			if (strPwd == null || strPwd.equals("")) {
				JOptionPane.showMessageDialog(jPanel, "密码不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			String strNewPwd = String.valueOf(pfNewPwd.getPassword());
			if (!(strPwd == null || strPwd.equals("")) && (strNewPwd == null || strNewPwd.equals(""))) {
				JOptionPane.showMessageDialog(jPanel, "新密码不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			String strRePwd = String.valueOf(pfRePwd.getPassword());
			if (!(strPwd == null || strPwd.equals("")) && !(strNewPwd == null || strNewPwd.equals(""))
					&& (strRePwd == null || strRePwd.equals(""))) {
				JOptionPane.showMessageDialog(jPanel, "确认密码不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			if (!(strPwd == null || strPwd.equals("")) && !(strNewPwd == null || strNewPwd.equals(""))
					&& !(strRePwd == null || strRePwd.equals("")) && !(strNewPwd.equals(strRePwd))) {
				JOptionPane.showMessageDialog(jPanel, "两次密码输入不一致！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			if (!(strPwd == null || strPwd.equals("")) && !(strNewPwd == null || strNewPwd.equals(""))
					&& !(strRePwd == null || strRePwd.equals("")) && (strNewPwd.equals(strRePwd))) {
				user = userService.findUserByPwd(Login.username, strPwd);
				if (user != null) {
					if ((user.getUsername().equals(Login.username)) && !(user.getPassword().equals(strPwd))) {
						JOptionPane.showMessageDialog(jPanel, "原密码输入错误！", "温馨提示", JOptionPane.WARNING_MESSAGE);
					}
					if ((user.getUsername().equals(Login.username)) && (user.getPassword().equals(strPwd))) {
						System.out.println(user.getUsername());
						System.out.println(user.getPassword());
							user.setPassword(strRePwd);
							System.out.println(user.getPassword());
							DBUtil db = new DBUtil();
							try {
								db.getConnection();
								db.reSet(user);
							} catch (SQLException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							JOptionPane.showMessageDialog(jPanel, "修改成功！", "温馨提示", JOptionPane.WARNING_MESSAGE);
							pfPwd.setText("");
							pfNewPwd.setText("");
							pfRePwd.setText("");
						}
					}
				}
			}

		}

	

	/**
	 * 插入课程信息按钮监听类
	 */
	public class BtnCouOkListener implements ActionListener {

		private Component jPanel;

		@Override
		public void actionPerformed(ActionEvent e) {
			String strCno = cmbCno.getSelectedItem().toString();
			String strCname = cmbCname.getSelectedItem().toString();
			String strCcredit = cmbCcredit.getSelectedItem().toString();
			String strCteacher = cmbCteacher.getSelectedItem().toString();
			// 将选中的课程信息封装到Course对象
			course = new Course(strCno, strCname, strCcredit, strCteacher);
			// 保存数据
			if (courseService.saveCourse(course)) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "保存成功！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(jPanel, "保存失败！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

	/**
	 * 清除文本框的方法
	 */
	public void removeTxt() {
		txtcall.setText("");
		txtclass.setText("");
		txtname.setText("");
		txtno.setText("");
		txtzy.setText("");
	}

	/*
	 * public static void main(String[] args) { new TeacherFrame(); }
	 */

}

