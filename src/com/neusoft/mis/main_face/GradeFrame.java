package com.neusoft.mis.main_face;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.neusoft.mis.constant.Constant;
import com.neusoft.mis.entity.Grade;
import com.neusoft.mis.service.GradeService;
/**
 * 成绩录入窗体类
 * @author 王晓森
 *
 */
public class GradeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1844401318765078346L;
	/**
	 * 声明各种组件
	 */
	// 声明主面板
	private JPanel p, p1, p2, p3, p4, p5, p6;
	// 声明菜单栏组件
	private JMenuBar menuBar;
	// 声明菜单组件
	private JMenu menuCaoz, menuHelp;
	// 声明菜单项目
	private JMenuItem saveItem, exitItem, aboutItem;
	// 声明标签
	private JLabel lbsname, lbsno, lbjava, lbshuju, lbcaozuo, lbzucheng, lbsuanfa, lbEglish, lbOrical, lbgailv;
	// 声明文本框
	private JTextField txtsname, txtsno, txtjava, txtshuju, txtcaozuo, txtzucheng, txtsuanfa, txtEglish, txtOrical,
			txtgailv;
	// 声明按钮
	private JButton btnOk;
	private JButton btnRemove;
	//声明图标
	private ImageIcon img;

	// 声明Grade对象
	private Grade grade;
	// 声明GradeService对象
	private GradeService gradeService;
	//创建构造器
	public GradeFrame() {
		// 初始化
		gradeService = new GradeService();
		//初始化图标
		img = new ImageIcon("src\\img\\add2.png");

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
		saveItem.addActionListener(new BtnOkListener());

		exitItem = new JMenuItem("退出");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "您确定要退出吗？", "退出",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					//如果选择退出，那么窗体隐藏
					GradeFrame.this.setVisible(false);
				}
			}
		});

		aboutItem = new JMenuItem("关于系统");
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "版本1.0\n作者:王晓森组\n版权:Java期末项目", "关于", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// 将菜单项目添加到菜单
		menuCaoz.add(saveItem);
		menuCaoz.add(exitItem);
		menuHelp.add(aboutItem);

		// 初始化按钮
		btnOk = new JButton("插入",new ImageIcon("src\\img\\ok.png"));
		btnRemove = new JButton("清空",new ImageIcon("src\\img\\remove.png"));
		// 添加监听器
		btnOk.addActionListener(new BtnOkListener());
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//清空文本框
				removeTxt();
			}
		});

		// 初始化标签
		lbsno = new JLabel("学        号:");
		lbsname = new JLabel("姓        名:");
		lbjava = new JLabel("j  a  v  a  :");
		lbshuju = new JLabel("数  据  库:");
		lbcaozuo = new JLabel("操作系统:");
		lbzucheng = new JLabel("组成原理:");
		lbsuanfa = new JLabel("算        法:");
		lbEglish = new JLabel("英        语:");
		lbOrical = new JLabel("O r i c a l:");
		lbgailv = new JLabel("概  率  论:");

		// 文本框初始化
		txtsname = new JTextField(12);
		txtsno = new JTextField(12);
		txtjava = new JTextField(12);
		txtshuju = new JTextField(12);
		txtcaozuo = new JTextField(12);
		txtzucheng = new JTextField(12);
		txtsuanfa = new JTextField(12);
		txtEglish = new JTextField(12);
		txtOrical = new JTextField(12);
		txtgailv = new JTextField(12);

		// 将面板实例化，采用6行1列布局
		p = new JPanel(new GridLayout(6, 1));

		// 组合面板始化并将各种组件添加到各种面板中，并添加到主面板
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		p1.add(lbsno);
		p1.add(txtsno);
		p1.add(lbsname);
		p1.add(txtsname);
		p.add(p1);

		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		p2.add(lbjava);
		p2.add(txtjava);
		p2.add(lbshuju);
		p2.add(txtshuju);
		p.add(p2);

		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		p3.add(lbcaozuo);
		p3.add(txtcaozuo);
		p3.add(lbzucheng);
		p3.add(txtzucheng);
		p.add(p3);

		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		p4.add(lbsuanfa);
		p4.add(txtsuanfa);
		p4.add(lbEglish);
		p4.add(txtEglish);
		p.add(p4);

		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		p5.add(lbOrical);
		p5.add(txtOrical);
		p5.add(lbgailv);
		p5.add(txtgailv);
		p.add(p5);

		p6 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		p6.add(btnOk);
		p6.add(btnRemove);
		p.add(p6);

		//将主面板添加到容器
		this.add(p);
		// 设置窗体图标
		this.setIconImage(img.getImage());
		// 将菜单栏设置到窗体中
		this.setJMenuBar(menuBar);
		//设置标题
		this.setTitle(Constant.TEACHER_GRADE_TITLE);
		//设置大小
		this.setSize(Constant.TEACHER_GRADE_WIDTH, Constant.TEACHER_GRADE_HEIGHT);
		//初始化位置居中
		this.setLocationRelativeTo(null);
		//可视化
		this.setVisible(true);
		//大小不可改变
		this.setResizable(false);
	}

	/**
	 * 插入成绩按钮监听类
	 */
	public class BtnOkListener implements ActionListener {

		private Component jPanel;

		@Override
		public void actionPerformed(ActionEvent e) {
			String strsno = txtsno.getText();
			String strsname = txtsname.getText();

			// 将选中的课程信息封装到Course对象
			if (strsno == null || strsno.equals("")) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "学号不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			if (!(strsno == null || strsno.equals("")) && (strsname == null || strsname.equals(""))) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "姓名不能为空！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			if (!(strsno == null || strsno.equals("")) && !(strsname == null || strsname.equals(""))
					&& ((txtjava.getText() == null || txtjava.getText().equals(""))
							|| (txtshuju.getText() == null || txtshuju.getText().equals(""))
							|| (txtcaozuo.getText() == null || txtcaozuo.getText().equals(""))
							|| (txtzucheng.getText() == null || txtzucheng.getText().equals(""))
							|| (txtsuanfa.getText() == null || txtsuanfa.getText().equals(""))
							|| (txtEglish.getText() == null || txtEglish.getText().equals(""))
							|| (txtOrical.getText() == null || txtOrical.getText().equals(""))
							|| (txtgailv.getText() == null || txtgailv.getText().equals("")))) {
				// 提示框
				JOptionPane.showMessageDialog(jPanel, "请填完成绩！", "温馨提示", JOptionPane.WARNING_MESSAGE);
			}
			//如果输入完全
			if (!(strsno == null || strsno.equals("")) && !(strsname == null || strsname.equals(""))
					&& !((txtjava.getText() == null || txtjava.getText().equals(""))
							|| (txtshuju.getText() == null || txtshuju.getText().equals(""))
							|| (txtcaozuo.getText() == null || txtcaozuo.getText().equals(""))
							|| (txtzucheng.getText() == null || txtzucheng.getText().equals(""))
							|| (txtsuanfa.getText() == null || txtsuanfa.getText().equals(""))
							|| (txtEglish.getText() == null || txtEglish.getText().equals(""))
							|| (txtOrical.getText() == null || txtOrical.getText().equals(""))
							|| (txtgailv.getText() == null || txtgailv.getText().equals("")))) {
				//将文本框输入的字符串转化成整型
				int intjava = Integer.parseInt(txtjava.getText());
				int intshuju = Integer.parseInt(txtshuju.getText());
				int intcaozuo = Integer.parseInt(txtcaozuo.getText());
				int intzucheng = Integer.parseInt(txtzucheng.getText());
				int intsuanfa = Integer.parseInt(txtsuanfa.getText());
				int intEnglish = Integer.parseInt(txtEglish.getText());
				int intOrical = Integer.parseInt(txtOrical.getText());
				int intgailv = Integer.parseInt(txtgailv.getText());
				//将各门成绩封装到成绩实体类
				grade = new Grade(strsno, strsname, intjava, intshuju, intcaozuo, intzucheng, intsuanfa, intEnglish,
						intOrical, intgailv);
				// 保存数据
				if (gradeService.saveGrade(grade)) {
					// 提示框
					JOptionPane.showMessageDialog(jPanel, "保存成功！", "温馨提示", JOptionPane.WARNING_MESSAGE);
					//保存成功后清空文本框
					removeTxt();
				} else {
					JOptionPane.showMessageDialog(jPanel, "学号不得重复！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}

			}

		}

	}

	/**
	 * 清除文本框的方法
	 */
	public void removeTxt() {
		txtcaozuo.setText("");
		txtEglish.setText("");
		txtgailv.setText("");
		txtjava.setText("");
		txtOrical.setText("");
		txtshuju.setText("");
		txtsname.setText("");
		txtsno.setText("");
		txtsuanfa.setText("");
		txtzucheng.setText("");
	}

	/*
	 * public static void main(String[] args) { new GradeFrame(); }
	 */
}
