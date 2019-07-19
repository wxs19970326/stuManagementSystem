package com.neusoft.mis.table_demo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.neusoft.mis.main_face.Login;
import com.neusoft.mis.main_face.Select;
import com.neusoft.mis.uitl.DBUtil;

/**
 * 学生成绩查询表类
 * 
 * @author ASUS
 *
 */
public class GradeTableDemo extends JFrame {
	/**
	 * 
	 */
	//定义布尔类型变量，用来区分功能
	public static boolean b = true;
	private static final long serialVersionUID = 7618296296867174379L;
	// 声明滚动面板
	private JScrollPane spTable;
	// 声明一个盛放按钮的面板
	private JPanel pButtons;
	//声明删除刷新按钮
	private JButton bthDelete, btnFlush;
	// 声明默认表格模式
	private DefaultTableModel model;
	// 声明表格
	private JTable table;
	//声明返回按钮
	private JButton btnBack;

	public GradeTableDemo() {
		super("成绩表");

		// 创建默认表格模式
		model = new DefaultTableModel();
		// 创建表格
		table = new JTable(model);
		// 设置表格选择模式为单一选择
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// 创建一个滚动面板，包含表格
		spTable = new JScrollPane(table);
		// 将滚动面板添加到窗体中央
		this.add(spTable, BorderLayout.CENTER);

		// 创建按钮
		bthDelete = new JButton("删除");
		btnFlush = new JButton("刷新");
		btnBack = new JButton("返回");
		// 创建添加按钮的面板
		pButtons = new JPanel();
		// 将按钮添加到面板中
		if(Login.power == 0) {
			pButtons.add(bthDelete);
		}
		pButtons.add(btnFlush);
		pButtons.add(btnBack);
		// 将盛放按钮的面板添加到窗体的南部（下面）
		this.add(pButtons, BorderLayout.SOUTH);

		// 注册删除监听
		bthDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//如果用户权限为老师，才可删除
				if(Login.power == 0) {
					// 调用删除数据的方法
					deleteData();
				}
			}
		});
		//注册刷新按钮的监听
		btnFlush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 调用显示数据的方法
				showData();
			}
		});
		//注册返回按钮的监听
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 调用显示数据的方法
				if (b) {
					Select s = new Select();
					s.setVisible(true);
					GradeTableDemo.this.setVisible(false);
				} else {
					GradeTableDemo.this.setVisible(false);
				}
			}
		});

		// 初始化显示表格数据
		this.showData();

		// 窗体大小不可改变
		this.setResizable(false);
		// 设定窗口大小
		this.setSize(600, 400);
		// 窗体位置居中
		this.setLocationRelativeTo(null);
		
		// 设定窗口默认关闭方式为退出应用程序
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 设置窗口可视（显示）
		this.setVisible(true);
	}
	/**
	 * 显示数据的方法
	 */
	private void showData() {
		// 查询grades表
		//定义sql语句
		String sql = "select sno as 学号, sname as 姓名,gjava as JAVA,gshuju as 数据库,gcaozuo as 操作系统,gzucheng as 组成原理,gsuanfa as 算法,gEnglish as English,gOrical as Orical,ggailv as 概率论  from grades";
		// 数据库访问
		//声明工具对象
		DBUtil db = new DBUtil();
		try {
			//数据库链接
			db.getConnection();
			//调用查询语句，将查询结果放入rs中
			ResultSet rs = db.executeQuery(sql, null);
			//得到rs的描述数据的数据对象
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取列数
			int colCount = rsmd.getColumnCount();
			// 存放列名
			Vector<String> title = new Vector<String>();
			// 列名
			for (int i = 1; i <= colCount; i++) {
				//获取sql语言里的制定标签
				title.add(rsmd.getColumnLabel(i));
			}
			// 表格数据
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			int rowCount = 0;
			//遍历查询结果
			while (rs.next()) {
				rowCount++;
				// 行数据
				Vector<String> rowdata = new Vector<String>();
				for (int i = 1; i <= colCount; i++) {
					//获取数据
					rowdata.add(rs.getString(i));
				}
				//添加到行数据中
				data.add(rowdata);
			}
			if (rowCount == 0) {
				//显示安东表格里
				model.setDataVector(null, title);
			} else {
				//显示到表格里
				model.setDataVector(data, title);
			}
		} catch (Exception ee) {
			System.out.println(ee.toString());
			JOptionPane.showMessageDialog(this, "系统出现异常错误。请检查数据库。系统即将退出！！！", "错误", 0);
		} finally {
			db.closeAll();
		}
	}

	/**
	 * 删除数据的方法
	 */
	public void deleteData() {
		//获取表格行标
		int index[] = table.getSelectedRows();
		if (index.length == 0) {
			JOptionPane.showMessageDialog(this, "请选择要删除的记录", "提示", JOptionPane.PLAIN_MESSAGE);
		} else {
			try {
				int k = JOptionPane.showConfirmDialog(this, "您确定要从数据库中删除所选的数据吗 ？", "删除", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (k == JOptionPane.YES_OPTION) {
					
					DBUtil db = new DBUtil();
					try {
						//连接数据库
						db.getConnection();
						//获取选择的行的学号
						String sno = table.getValueAt(index[0], 0).toString();
						//定义删除sql语句
						String sql = "delete from grades where sno=?";
						//执行删除
						int count = db.executeUpdate(sql, new String[] { sno });
						if (count == 1) {
							JOptionPane.showMessageDialog(this, "删除操作成功完成!", "成功", JOptionPane.PLAIN_MESSAGE);
							showData();
						} else {
							JOptionPane.showMessageDialog(this, "抱歉！ 删除数据失败!", "失败:", 0);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						db.closeAll();
					}
				}
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(this, "抱歉!删除数据失败!【系统异常！】", "失败:", 0);
			}
		}
	}

	/*
	 * public static void main(String[] args) { new UserTableDemo(); }
	 */
}
