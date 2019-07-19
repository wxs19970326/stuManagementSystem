package com.neusoft.mis.main_face;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.neusoft.mis.constant.Constant;
import com.neusoft.mis.entity.Grade;
import com.neusoft.mis.service.CourseService;
import com.neusoft.mis.table_demo.CourseTableDemo;
import com.neusoft.mis.table_demo.GradeTableDemo;
import com.neusoft.mis.table_demo.StudentTableDemo;
import com.neusoft.mis.uitl.DBUtil;

public class Select extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3798206084974411203L;
	private CardLayout card;
	// 声明主面板
	private JPanel p, pStu, pCour, pGra, p1, p2, p12, p3, p4, p5, p6, pk2, pk3;
	// 声明table面板
	private JPanel pStuTab, pGraTab, pCouTab, pStuButtons, pGraButtons, pCouButtons;
	// 查询单个按钮
	private JButton btnOneGraClose;
	// 滚动面板
	private JScrollPane graScrollPane, stuScrollPane, couScrollPane;
	// 声明菜单栏组件
	private JMenuBar menuBar;
	// 声明菜单组件
	private JMenu menuCaoz, menuHelp;
	// 声明菜单项目
	private JMenuItem saveItem, exitItem, aboutItem;
	// 声明工具栏
	private JToolBar toolBar;
	// 声明工具栏按钮
	private JButton btnStuIn, btnCouIn, btnStuGra;
	// 声明查询学生信息按钮
	private JButton btnSele, btnStuRem, btnAllSele;
	// 声明查询课程信息按钮
	private JButton btnCouSe, btnCouAll, btnCouRem;
	// 声明查询成绩按钮
	private JButton btnGraSe, btnGraAll, btnGraRem;
	// 声明table面板按钮
	private JButton btnStuClose, btnGraClose, btnCouClose;
	// 声明学号、课程代码标签
	private JLabel lbno;
	private JLabel lbcno;
	private JLabel lbGraNo;
	private JLabel lbname;
	// 声明文本框
	private JTextField txtno, txtcno, txtGraNo, txtname;

	private ImageIcon img;

	private JLabel lbname1, lbname2;
	private DefaultTableModel model, modelStu, modelCou;
	private JTable table, tableStu, tableCou;

	public Select() {

		// 声明并初始化标签字体
		Font f = new Font("", 15, 20);

		// 初始化图标
		img = new ImageIcon("src\\img\\select.png");
		// 初始化卡片布局
		card = new CardLayout();
		// 将出面板初始化为卡片布局
		p = new JPanel(card);

		// 实例化菜单栏
		menuBar = new JMenuBar();
		// 实例化菜单
		menuCaoz = new JMenu("操作");
		menuHelp = new JMenu("帮助");
		// 将菜单添加到菜单栏
		menuBar.add(menuCaoz);
		menuBar.add(menuHelp);
		// 实例化菜单项目并注册监听
		saveItem = new JMenuItem("保存");
		// 添加菜单项目监听器
		exitItem = new JMenuItem("退出");
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
				JOptionPane.showMessageDialog(null, "版本1.0\n作者:王晓森\n版权:Java期末项目", "关于", JOptionPane.WARNING_MESSAGE);
			}
		});

		// 将菜单项目添加到菜单
		menuCaoz.add(saveItem);
		menuCaoz.add(exitItem);
		menuHelp.add(aboutItem);

		// 声明工具栏
		toolBar = new JToolBar();
		// 声明按钮
		btnStuGra = new JButton("查询成绩", new ImageIcon("src\\img\\selectGra.png"));
		btnStuIn = new JButton("查询学生信息", new ImageIcon("src\\img\\selectStu.png"));
		btnCouIn = new JButton("查询课程信息", new ImageIcon("src\\img\\select.png"));
		// 添加工具栏按钮监听类
		btnStuIn.addActionListener(new BtnListener());
		btnCouIn.addActionListener(new BtnListener());
		btnStuGra.addActionListener(new BtnListener());

		// 将按钮添加到工具栏
		toolBar.add(btnStuGra);
		toolBar.add(btnStuIn);
		toolBar.add(btnCouIn);

		// 初始化各个标签并设置字体
		lbno = new JLabel("学号：");
		lbcno = new JLabel("课程代码：");
		lbGraNo = new JLabel("学号：");
		lbname = new JLabel("学生成绩查询！",new ImageIcon("src\\img\\select.png"),SwingConstants.CENTER);
		lbname1 = new JLabel("学生信息查询！",new ImageIcon("src\\img\\select.png"),SwingConstants.CENTER);
		lbname2 = new JLabel("课程信息查询！",new ImageIcon("src\\img\\select.png"),SwingConstants.CENTER);
		lbname.setFont(f);
		lbname1.setFont(f);
		lbname2.setFont(f);

		// 文本框初始化
		txtno = new JTextField(15);
		txtcno = new JTextField(15);
		txtGraNo = new JTextField(15);
		txtname = new JTextField(15);

		// 查询窗口各个面板初始化
		pStu = new JPanel(new GridLayout(4, 1));
		pCour = new JPanel(new GridLayout(4, 1));
		pGra = new JPanel(new GridLayout(4, 1));
		// 学生信息按钮初始化按钮
		btnSele = new JButton("查询");
		btnAllSele = new JButton("查询全部");
		btnStuRem = new JButton("清空");
		// 课程信息按钮初始化
		btnCouSe = new JButton("查询");
		btnCouAll = new JButton("查询全部");
		btnCouRem = new JButton("清空");
		// 成绩信息按钮初始化
		btnGraSe = new JButton("查询");
		btnGraAll = new JButton("查询全部");
		btnGraRem = new JButton("清空");
		// 信息表按钮
		btnStuClose = new JButton("关闭");
		btnStuClose.addActionListener(new BtnListener());
		// 成绩表按钮
		btnGraClose = new JButton("关闭");
		btnOneGraClose = new JButton("返回");
		btnGraClose.addActionListener(new BtnListener());
		btnOneGraClose.addActionListener(new BtnListener());
		// 课程表按钮
		btnCouClose = new JButton("关闭");
		btnCouClose.addActionListener(new BtnListener());

		// 添加学生信息按钮监听
		btnSele.addActionListener(new BtnOkListener());
		btnAllSele.addActionListener(new BtnOkListener());
		btnStuRem.addActionListener(new BtnRemoveListener());

		// 添加课程信息按钮监听
		btnCouSe.addActionListener(new BtnOkListener());
		btnCouAll.addActionListener(new BtnOkListener());
		btnCouRem.addActionListener(new BtnRemoveListener());

		// 添加成绩按钮监听
		btnGraSe.addActionListener(new BtnOkListener());
		btnGraAll.addActionListener(new BtnOkListener());
		btnGraRem.addActionListener(new BtnRemoveListener());

		// 将组件添加到各个卡片面板上
		pk3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 30));
		pk3.add(lbname1);
		pStu.add(pk3);

		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
		p1.add(lbno);
		p1.add(txtno);
		pStu.add(p1);

		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		p2.add(btnSele);
		p2.add(btnAllSele);
		p2.add(btnStuRem);
		pStu.add(p2);

		pk2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 30));
		pk2.add(lbname2);
		pCour.add(pk2);

		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
		p3.add(lbcno);
		p3.add(txtcno);
		pCour.add(p3);

		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
		p4.add(btnCouSe);
		p4.add(btnCouAll);
		p4.add(btnCouRem);
		pCour.add(p4);

		p12 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 30));
		p12.add(lbname);
		pGra.add(p12);

		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
		p5.add(lbGraNo);
		p5.add(txtGraNo);
		pGra.add(p5);

		p6 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		p6.add(btnGraSe);
		p6.add(btnGraAll);
		p6.add(btnGraRem);
		pGra.add(p6);

		/*
		 * 成绩单个查询表格面板
		 */
		// 创建默认表格模式
		model = new DefaultTableModel();
		// 创建表格
		table = new JTable(model);
		// 设置表格选择模式为单一选择
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pGraTab = new JPanel(new BorderLayout());
		// 创建一个滚动面板，包含表格
		graScrollPane = new JScrollPane(table);
		// 将滚动面板添加到窗体中央
		pGraTab.add(graScrollPane, BorderLayout.CENTER);
		// 创建面板
		pGraButtons = new JPanel();
		// 将按钮添加到面板中
		pGraButtons.add(btnGraClose);
		// 将盛放按钮的面板添加到窗体的南部（下面）
		pGraTab.add(pGraButtons, BorderLayout.SOUTH);

		/*
		 * 学生信息单个查询表格面板
		 */
		// 创建默认表格模式
		modelStu = new DefaultTableModel();
		// 创建表格
		tableStu = new JTable(modelStu);
		// 设置表格选择模式为单一选择
		tableStu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pStuTab = new JPanel(new BorderLayout());
		// 创建一个滚动面板，包含表格
		stuScrollPane = new JScrollPane(tableStu);
		// 将滚动面板添加到窗体中央
		pStuTab.add(stuScrollPane, BorderLayout.CENTER);
		// 创建面板
		pStuButtons = new JPanel();
		// 将按钮添加到面板中
		pStuButtons.add(btnStuClose);
		// 将盛放按钮的面板添加到窗体的南部（下面）
		pStuTab.add(pStuButtons, BorderLayout.SOUTH);

		/*
		 * 课程信息单个查询表格面板
		 */
		// 创建默认表格模式
		modelCou = new DefaultTableModel();
		// 创建表格
		tableCou = new JTable(modelCou);
		// 设置表格选择模式为单一选择
		tableCou.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pCouTab = new JPanel(new BorderLayout());
		// 创建一个滚动面板，包含表格
		couScrollPane = new JScrollPane(tableCou);
		// 将滚动面板添加到窗体中央
		pCouTab.add(couScrollPane, BorderLayout.CENTER);
		// 创建面板
		pCouButtons = new JPanel();
		// 将按钮添加到面板中
		pCouButtons.add(btnCouClose);
		// 将盛放按钮的面板添加到窗体的南部（下面）
		pCouTab.add(pCouButtons, BorderLayout.SOUTH);

		// 将各种面板添加到卡片布局，并命名
		p.add("查询成绩", pGra);
		p.add("查询学生信息", pStu);
		p.add("查询课程信息", pCour);
		p.add("showCou", pCouTab);
		p.add("showGra", pGraTab);
		p.add("showStu", pStuTab);

		// 将主面板添加到容器
		this.add(p);
		// 设置客户端图标
		this.setIconImage(img.getImage());
		// 将菜单栏设置到窗体中
		this.setJMenuBar(menuBar);
		// 将工具栏添加到窗体中
		this.add(toolBar, BorderLayout.NORTH);
		this.setTitle(Constant.TEACHER_SELECT_TITLE);
		this.setSize(Constant.TEACHER_SELECT_WIDTH, Constant.TEACHER_SELECT_HEIGHT);
		// 设置窗体居中
		this.setLocationRelativeTo(null);
		// 窗体可视化
		this.setVisible(true);
		// 窗体大小不可改变
		this.setResizable(false);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 工具栏按钮监听器类
	 */
	public class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == btnStuIn || source == btnStuClose) {
				card.show(p, "查询学生信息");
			}
			if (source == btnCouIn || source == btnCouClose) {
				card.show(p, "查询课程信息");
			}
			if (source == btnStuGra || source == btnGraClose || source == btnOneGraClose) {
				card.show(p, "查询成绩");
			}
		}

	}

	/*
	 * 查询按钮监听类
	 */
	public class BtnOkListener implements ActionListener {
		// 提示框面板声明
		private Component jPanel;

		@Override
		public void actionPerformed(ActionEvent e) {

			// 信息查询，查询单个学生信息事件
			if (e.getSource() == btnSele) {
				String strsno = txtno.getText();
				if (strsno == null || strsno.equals("")) {
					// 提示框
					JOptionPane.showMessageDialog(jPanel, "    学号不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
				if (!(strsno == null || strsno.equals(""))) {
					stuOneData(strsno);
					card.show(p, "showStu");
				}
			}

			// 信息学生查询，查询全部按钮事件
			if (e.getSource() == btnAllSele) {
				new StudentTableDemo();
				Select.this.setVisible(false);
			}

			// 单个课程信息查询
			if (e.getSource() == btnCouSe) {
				// 输入课程代码
				String strcno = txtcno.getText();
				if (strcno == null || strcno.equals("")) {
					// 提示框
					JOptionPane.showMessageDialog(jPanel, "    课程代码不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
				if (!(strcno == null || strcno.equals(""))) {
					// 调用查询单个课程信息的方法
					couOneData(strcno);
					card.show(p, "showCou");
				}
			}
			// 全部课程信息查询
			if (e.getSource() == btnCouAll) {
				new CourseTableDemo();
				Select.this.setVisible(false);

			}

			// 单个成绩查询
			if (e.getSource() == btnGraSe) {

				// 输入的学号
				String strGraNo = txtGraNo.getText();
				if (strGraNo == null || strGraNo.equals("")) {
					// 提示框
					JOptionPane.showMessageDialog(jPanel, "          学号不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
				// 根据学号查询学生
				if (!(strGraNo == null || strGraNo.equals("")) /* && !(strname == null || strname.equals("")) */) {
					// 调用单个成绩查询方法
					graOneData(strGraNo);
					card.show(p, "showGra");
				}
			}
			// 全部成绩查询
			if (e.getSource() == btnGraAll) {
				new GradeTableDemo();
				Select.this.setVisible(false);
			}

		}

	}

	// 清空按钮监听器
	public class BtnRemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnStuRem) {
				txtno.setText("");
			}
			if (e.getSource() == btnCouRem) {
				txtcno.setText("");
			}
			if (e.getSource() == btnGraRem) {
				txtGraNo.setText("");
				txtname.setText("");
			}
		}
	}

	/*
	 * 单个成绩查询方法
	 */
	private void graOneData(String sno) {
		System.out.println("aaa");
		// 查询grades表
		String sql = "select sno as 学号, sname as 姓名,gjava as JAVA,gshuju as 数据库,gcaozuo as 操作系统,gzucheng as 组成原理,gsuanfa as 算法,gEnglish as English,gOrical as Orical,ggailv as 概率论   from grades where sno=? ";

		// 数据库访问
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			ResultSet rs = db.executeQuery(sql, new String[] { sno });
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			// 获取列数
			int colCount = rsmd.getColumnCount();
			// 存放列名
			Vector<String> title = new Vector<String>();
			// 列名
			for (int i = 1; i <= colCount; i++) {
				title.add(rsmd.getColumnLabel(i));
			}
			// 表格数据
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			int rowCount = 0;
			while (rs.next()) {
				rowCount++;
				// 行数据
				Vector<String> rowdata = new Vector<String>();
				for (int i = 1; i <= colCount; i++) {
					rowdata.add(rs.getString(i));
				}
				data.add(rowdata);
			}
			if (rowCount == 0) {
				model.setDataVector(null, title);
			} else {
				model.setDataVector(data, title);
			}
		} catch (Exception ee) {
			System.out.println(ee.toString());
			JOptionPane.showMessageDialog(this, "系统出现异常错误。请检查数据库。系统即将退出！！！", "错误", 0);
		} finally {
			db.closeAll();
		}
	}

	/*
	 * 单个学生信息查询方法
	 */
	private void stuOneData(String sno) {
		System.out.println("aaa");
		// 查询students表
		String sql = "select sno as 学号, sname as 姓名,szy as 专业,sclass as 班级,scall as 电话  from students where sno=? ";

		// 数据库访问
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			ResultSet rs = db.executeQuery(sql, new String[] { sno });
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			// 获取列数
			int colCount = rsmd.getColumnCount();
			// 存放列名
			Vector<String> title = new Vector<String>();
			// 列名
			for (int i = 1; i <= colCount; i++) {
				title.add(rsmd.getColumnLabel(i));
			}
			// 表格数据
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			int rowCount = 0;
			while (rs.next()) {
				rowCount++;
				// 行数据
				Vector<String> rowdata = new Vector<String>();
				for (int i = 1; i <= colCount; i++) {
					rowdata.add(rs.getString(i));
				}
				data.add(rowdata);
			}
			if (rowCount == 0) {
				modelStu.setDataVector(null, title);
			} else {
				modelStu.setDataVector(data, title);
			}
		} catch (Exception ee) {
			System.out.println(ee.toString());
			JOptionPane.showMessageDialog(this, "系统出现异常错误。请检查数据库。系统即将退出！！！", "错误", 0);
		} finally {
			db.closeAll();
		}
	}

	/*
	 * 单个课程信息查询方法
	 */
	private void couOneData(String cno) {
		System.out.println("aaa");
		// 查询courses表
		String sql = "select cno as 课程号, cname as 课程名,ccredit as 学分,cteacher as 教师  from courses where cno=? ";

		// 数据库访问
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			ResultSet rs = db.executeQuery(sql, new String[] { cno });
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			// 获取列数
			int colCount = rsmd.getColumnCount();
			// 存放列名
			Vector<String> title = new Vector<String>();
			// 列名
			for (int i = 1; i <= colCount; i++) {
				title.add(rsmd.getColumnLabel(i));
			}
			// 表格数据
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			int rowCount = 0;
			while (rs.next()) {
				rowCount++;
				// 行数据
				Vector<String> rowdata = new Vector<String>();
				for (int i = 1; i <= colCount; i++) {
					rowdata.add(rs.getString(i));
				}
				data.add(rowdata);
			}
			if (rowCount == 0) {
				modelCou.setDataVector(null, title);
			} else {
				modelCou.setDataVector(data, title);
			}
		} catch (Exception ee) {
			System.out.println(ee.toString());
			JOptionPane.showMessageDialog(this, "系统出现异常错误。请检查数据库。系统即将退出！！！", "错误", 0);
		} finally {
			db.closeAll();
		}
	}

	/*
	 * public static void main(String[] args) { new Select(); }
	 */
}