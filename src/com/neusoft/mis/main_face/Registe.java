package com.neusoft.mis.main_face;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.neusoft.mis.constant.Constant;
import com.neusoft.mis.entity.User;
import com.neusoft.mis.service.UserService;

/**
 * 注册窗口类
 * @author ASUS
 *
 */
public class Registe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7946304615211201131L;
	// 声明主面板和其他面板
	private JPanel p, p1, p2, p3, p4, p5;
	// 声明标签
	private JLabel lName, lPwd1, lPwd2, lPow;
	// 确认和重置按钮
	private JButton bRegister, bRemove;
	// 用户名 文本框
	private JTextField tName;
	// 图标
	private ImageIcon img;
	// 密码框
	private JPasswordField pwd1, pwd2;
	// 登陆权限 单选按钮
	private JRadioButton /* rbtnTeacher, */ rbtnStudent/* ,rbtnVis */;
	// 提示面板
	private Component jPanel;
	// 注册的用户
	private static User user, userfind;
	private UserService userService;

	// 创建构造器
	public Registe() {
		//声明主面板，采用卡片布局
		p = new JPanel(new GridLayout(5, 1));
		//实例化用户业务逻辑对象
		userService = new UserService();
		// 实例化组件
		lName = new JLabel("用  户  名：",new ImageIcon("src\\img\\userName.png"),SwingConstants.CENTER);
		lPwd1 = new JLabel("密        码：",new ImageIcon("src\\img\\password.png"),SwingConstants.CENTER);
		lPwd2 = new JLabel("确认密码：",new ImageIcon("src\\img\\password.png"),SwingConstants.CENTER);
		lPow = new JLabel("注册权限：",new ImageIcon("src\\img\\power.png"),SwingConstants.CENTER);
		//实例化按钮组建
		bRegister = new JButton("注册",new ImageIcon("src\\img\\btnRegiste.png"));
		bRemove = new JButton("清空",new ImageIcon("src\\img\\btnReset.png"));
		bRegister.setSize(60, 25);
		bRemove.setSize(60, 25);
		//实例密码框
		pwd1 = new JPasswordField(20);
		pwd2 = new JPasswordField(20);
		pwd1.setEchoChar('*');
		pwd2.setEchoChar('*');
		//实例文本框
		tName = new JTextField(20);

		/**
		 * 登陆权限单选按钮的实例化和实现单选
		 */
		rbtnStudent = new JRadioButton("学生");
		// rbtnTeacher = new JRadioButton("老师");
		// rbtnVis = new JRadioButton("访客");
		// 单选逻辑
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbtnStudent);
		// bg.add(rbtnTeacher);
		// bg.add(rbtnVis);

		// 将组件添加到面板中
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
		p1.add(lName);
		p1.add(tName);
		p.add(p1);

		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 11, 20));
		p2.add(lPwd1);
		p2.add(pwd1);
		p.add(p2);

		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		p3.add(lPwd2);
		p3.add(pwd2);
		p.add(p3);

		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		p4.add(lPow);
		// p4.add(rbtnTeacher);
		p4.add(rbtnStudent);
		// p4.add(rbtnVis);
		p.add(p4);

		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		p5.add(bRegister);
		p5.add(bRemove);
		p.add(p5);

		// 清空按钮事件处理
		bRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 清空文本框和密码框
				pwd1.setText("");
				pwd2.setText("");
				tName.setText("");
				// 清空单选按钮框
				bg.clearSelection();
			}
		});
		//注册按钮监听器
		bRegister.addActionListener(new BregisterListener());

		// 设置窗体图标
		img = new ImageIcon("src\\img\\btnRegiste.png");
		this.setIconImage(img.getImage());
		//设置标题
		this.setTitle(Constant.REGISTER_TITLE);
		// 将面板添加到容器中
		this.add(p);
		//设置窗体可视化
		this.setVisible(true);
		//设置宽高
		this.setSize(Constant.REGISTER_WIDTH, Constant.REGISTER_HEIGHT);
		//设置位置居中
		this.setLocationRelativeTo(null);
		//窗体大小不可改变
		this.setResizable(false);

	}

	/**
	 * 监听类，处理注册按钮
	 */
	public class BregisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			/**
			 * 获取用户输入的数据
			 */
			String userName = tName.getText().trim();
			String password = new String(pwd1.getPassword());
			String rePassword = new String(pwd2.getPassword());
			// 用户权限，0为老师，1为学生
			//默认为1
			int power = 1;

			// 判断用户名文本框是否已填
			if (userName == null || userName.equals("")) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "用户名不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}

			// 判断密码填否
			if (!(userName == null || userName.equals("")) && (password == null || password.equals(""))) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "密码不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}

			// 判断确认密码填否
			if (!(userName == null || userName.equals("")) && !(password == null || password.equals(""))
					&& (rePassword == null || rePassword.equals(""))) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "确认密码不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}

			// 判断登陆方式是否已选
			boolean powStudent = rbtnStudent.isSelected();
			// boolean powTeacher = rbtnTeacher.isSelected();
			if (!(userName == null || userName.equals("")) && !(password == null || password.equals(""))
					&& !(rePassword == null || rePassword.equals("")) && password.equals(rePassword)
					&& (!powStudent /* && !powTeacher && !powVisite */)) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "请选择登陆方式！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			/*
			 * if(powTeacher) { power = 0; }
			 */
			if (powStudent) {
				power = 1;
			}
			// 判断两次密码是否相同，判断注册内容是否填写齐全
			if (!(userName == null || userName.equals("")) && !(password == null || password.equals(""))
					&& !(rePassword == null || rePassword.equals("")) && password.equals(rePassword)
					&& password.equals(rePassword) && (powStudent /* || powTeacher */)) {
				// 如果相同，将数据封装到user对象中
				user = new User(userName, password, power);

				// 根据注册时所填写的用户名查询是否已经使用该用户名注册
				userfind = userService.findUserByName(userName.trim());
				/**
				 * 保存注册的用户名逻辑
				 */
				if (userService.saveUser(user)) {
					// 提示框
					JOptionPane.showMessageDialog(jPanel, "注册成功！", "温馨提示", JOptionPane.WARNING_MESSAGE);
					Registe.this.setVisible(false);
				}
				// 判断用户名是否重复
				if (userfind != null) {
					if (userfind.getUsername().equals(userName)) {
						// 提示框
						JOptionPane.showMessageDialog(jPanel, "用户名重复！", "温馨提示", JOptionPane.WARNING_MESSAGE);
					}
				}
			} else if (!(userName == null || userName.equals("")) && !(password == null || password.equals(""))
					&& !(rePassword == null || rePassword.equals("")) && !(password.equals(rePassword))) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "俩次密码不一致！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}

		}

	}
}
