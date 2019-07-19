package com.neusoft.mis.main_face;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
 * 登陆窗口类
 * 
 * @author ASUS
 *
 */
public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7537478672388650187L;
	/**
	 * 声明各种组件
	 */
	// 面板
	private JPanel p, p0, p1, p2, p3, p4;
	// 声明菜单栏组件
	private JMenuBar menuBar;
	// 声明菜单组件
	private JMenu menuHelp;
	// 声明菜单项目
	private JMenuItem aboutItem;
	// 登陆，注册，清空按钮
	private JButton btnOk, btnReg, btnRemove;
	// 登陆方式 单选按钮
	private JRadioButton rbtnTeacher, rbtnStudent;
	// 用户名文本框
	private JTextField txdName;
	// 密码框
	private JPasswordField pwd;
	// 标签
	private JLabel lbMain, lbName, lbPwd, lbPow;
	// 图标
	private ImageIcon img;
	// 提示框面板
	private Component jPanel;
	// 单选按钮组
	private ButtonGroup bg;

	private UserService userService;
	private User user;
	public static String username;
	
	public static int power = 2;

	public Login() {
		// 将用户业务逻辑对象实例化
		userService = new UserService();

		// 实例化菜单栏
		menuBar = new JMenuBar();
		// 实例化菜单
		menuHelp = new JMenu("帮助");
		// 将菜单添加到菜单栏
		menuBar.add(menuHelp);
		// 实例化菜单项
		aboutItem = new JMenuItem("注册?");
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "本系统只有学生可以注册！", "关于", JOptionPane.WARNING_MESSAGE);
			}
		});
		// 将菜单项目添加到菜单
		menuHelp.add(aboutItem);

		/**
		 * 图标初始化
		 */
		img = new ImageIcon("src\\img\\login.png");

		/**
		 * 面板容器初始化
		 * Flowlayout 居中 左右间距10 上下间距30
		 */
		p0 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 11, 0));
		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
		//主面板用GridLayout布局  四行一列
		p = new JPanel(new GridLayout(5, 1));

		/**
		 * 标签初始化
		 */
		Font f = new Font(null, 22, 22);
		lbMain = new JLabel("学生管理系统",new ImageIcon("src\\img\\userName.png"),SwingConstants.CENTER);
		lbName = new JLabel("用  户  名:",new ImageIcon("src\\img\\userName.png"),SwingConstants.CENTER);
		lbPwd = new JLabel("密        码:",new ImageIcon("src\\img\\password.png"),SwingConstants.CENTER);
		lbPow = new JLabel("登陆方式:",new ImageIcon("src\\img\\power.png"),SwingConstants.CENTER);
		lbMain.setFont(f);

		//设置登录图形界面的背景图片
		/**
		 * 按钮初始化
		 */
		btnOk = new JButton("登陆",new ImageIcon("src\\img\\login.png"));
		btnReg = new JButton("注册",new ImageIcon("src\\img\\btnRegiste.png"));
		btnRemove = new JButton("清空",new ImageIcon("src\\img\\btnReset.png"));

		/**
		 * 权限单选按钮
		 */
		rbtnStudent = new JRadioButton("学生");
		rbtnTeacher = new JRadioButton("老师");
		// rbtnVis = new JRadioButton("访客");
		//实现单选逻辑
		bg = new ButtonGroup();
		bg.add(rbtnStudent);
		bg.add(rbtnTeacher);
		// bg.add(rbtnVis);

		/**
		 * 文本框和密码框初始化
		 */
		txdName = new JTextField(15);
		pwd = new JPasswordField(15);
		//让密码框显示为*
		pwd.setEchoChar('*');

		/**
		 * 注册按钮监听器
		 */
		btnReg.addActionListener(new BtnRegListener());
		btnOk.addActionListener(new BtnOkListener());
		btnRemove.addActionListener(new BtnRemoveListener());

		/**
		 * 将小 组件分别加入各个面板 再将各个面板加入总面板
		 */
		p0.add(lbMain);
		p.add(p0);
		
		p1.add(lbName);// 用户名标签
		p1.add(txdName);// 用户名文本框
		p.add(p1);

		p2.add(lbPwd);// 密码标签
		p2.add(pwd);// 密码框
		p.add(p2);

		p3.add(lbPow);// 权限标签
		p3.add(rbtnTeacher);// 权限单选按钮
		p3.add(rbtnStudent);// 权限单选按钮
		// p3.add(rbtnVis);//权限单选按钮
		p.add(p3);

		p4.add(btnOk);// 登录按钮
		p4.add(btnReg);// 注册按钮
		p4.add(btnRemove);// 清空按钮
		p.add(p4);
		
		this.add(p);
		// 将菜单栏设置到窗体中
		this.setJMenuBar(menuBar);
		//设置窗体不可改变大小
		this.setResizable(false);
		//设置图标
		this.setIconImage(img.getImage());
		//设置标题
		this.setTitle(Constant.LOGIN_TITLE);
		//设置宽高
		this.setSize(Constant.LOGIN_WIDTH, Constant.LOGIN_HEIGHT);
		//窗体位置居中
		this.setLocationRelativeTo(null);
		//窗体可视化
		this.setVisible(true);
		//窗体关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 监听类，处理登陆按钮事件
	 */
	public class BtnOkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 输入的用户名
			String strName = txdName.getText();
			if (strName == null || strName.equals("")) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "用户名不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			// 输入的密码
			String strPwd = new String(pwd.getPassword());
			if (!(strName == null || strName.equals("")) && (strPwd == null || strPwd.equals(""))) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "密码不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			/**
			 * 判断单选按钮选择状态，若未选中，则提示
			 */
			boolean powStudent = false;
			boolean powTeacher = false;
			powStudent = rbtnStudent.isSelected();
			powTeacher = rbtnTeacher.isSelected();
			// boolean powVisite = rbtnVis.isSelected();
			if (!(strName == null || strName.equals("")) && !(strPwd == null || strPwd.equals(""))
					&& (!powStudent && !powTeacher /* && !powVisite */)) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "请选择登陆方式！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			
			if (powStudent) {
				power = 1;
			}
			if (powTeacher) {
				power = 0;
			}
			// 根据用户名查询用户
			user = userService.findUserByName(strName.trim());
			
			if ((user != null) && !(strName == null || strName.equals("")) && !(strPwd == null || strPwd.equals("")) && (!powStudent || !powTeacher /* && !powVisite */)) {
				//将输入的用户名传给username 以便知道是哪个用户登录
				username = user.getUsername();
				// 判断密码是否正确
				if (user.getPassword().equals(strPwd)) {
					// 判断登陆权限是否匹配
					if (user.getPower() == power) {
						// 判断是学生登陆还是老师登陆
						if (power == 0) {
							Login.this.setVisible(false);
							//打开老师客户端
							new TeacherFrame();
						}
						if (power == 1) {
							Login.this.setVisible(false);
							//打开学生客户端
							new StudentFrame();
						}
					} else if((powStudent || powTeacher) && !(user.getPower() == power)){
						JOptionPane.showMessageDialog(jPanel, "登陆权限不匹配！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(jPanel, "密码错误！！！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

	/**
	 * 监听类，处理注册按钮
	 */
	public class BtnRegListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//打开注册窗体
			new Registe();
		}
	}

	/**
	 * 监听类 清空按钮
	 */
	public class BtnRemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//清空用户名文本框
			txdName.setText("");
			//清空密码框
			pwd.setText("");
			// 清空单选按钮框
			bg.clearSelection();

		}

	}
}
