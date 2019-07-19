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

import com.neusoft.mis.constant.Constant;
import com.neusoft.mis.main_face.Login;
import com.neusoft.mis.main_face.Select;
import com.neusoft.mis.uitl.DBUtil;

/**
 * 学生信息查询表类
 * 
 * @author ASUS
 *
 */
public class CourseTableDemo extends JFrame {
	/**
	 * 
	 */
	public static boolean courb = true;
	private static final long serialVersionUID = -3811253375703436185L;
	// 声明滚动面板
	private JScrollPane spTable;
	// 声明一个盛放按钮的面板
	private JPanel pButtons;
	private JButton bthDelete, btnFlush;
	// 声明默认表格模式
	private DefaultTableModel model;
	// 声明表格
	private JTable table;
	private JButton btnBack;

	public CourseTableDemo() {

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
		// 创建面板
		pButtons = new JPanel();
		// 将按钮添加到面板中
		if(Login.power == 0) {
			pButtons.add(bthDelete);
		}
		pButtons.add(btnFlush);
		pButtons.add(btnBack);
		// 将盛放按钮的面板添加到窗体的南部（下面）
		this.add(pButtons, BorderLayout.SOUTH);

		// 注册监听
		bthDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Login.power == 0) {
					// 调用删除数据的方法
					deleteData();
				}
			}
		});
		btnFlush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 调用显示数据的方法
				showData();
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 调用显示数据的方法
				if (courb) {
					Select s = new Select();
					s.setVisible(true);
					CourseTableDemo.this.setVisible(false);
				} else {
					CourseTableDemo.this.setVisible(false);
				}
			}
		});

		// 初始化显示表格数据
		this.showData();

		//设置标题
		this.setTitle(Constant.COURSETABLE_TITLE);
		// 窗体大小不可改变
		this.setResizable(false);
		// 设定窗口大小
		this.setSize(Constant.COURSETABLE_WIDTH, Constant.COURSETABLE_HEIGHT);
		// 窗体位置居中
		this.setLocationRelativeTo(null);
		// 设定窗口默认关闭方式为退出应用程序
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口可视（显示）
		this.setVisible(true);
	}

	private void showData() {
		// 查询course表
		String sql = "select cno as 课程号, cname as 课程名,ccredit as 学分,cteacher as 教师  from courses";
		// 数据库访问
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			ResultSet rs = db.executeQuery(sql, null);
			ResultSetMetaData rsmd = rs.getMetaData();
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

	// 删除数据
	public void deleteData() {
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
						db.getConnection();
						String sno = table.getValueAt(index[0], 0).toString();
						String sql = "delete from students where sno=?";
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
