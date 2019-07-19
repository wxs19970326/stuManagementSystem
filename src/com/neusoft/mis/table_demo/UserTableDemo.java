package com.neusoft.mis.table_demo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
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
import com.neusoft.mis.uitl.DBUtil;
/**
 * 用户名表格类
 * @author ASUS
 *
 */
public class UserTableDemo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8368422869550481459L;
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

	public UserTableDemo() {

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
		// 创建面板
		pButtons = new JPanel();
		// 将按钮添加到面板中
		pButtons.add(bthDelete);
		pButtons.add(btnFlush);
		// 将盛放按钮的面板添加到窗体的南部（下面）
		this.add(pButtons, BorderLayout.SOUTH);

		// 注册监听
		bthDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 调用删除数据的方法
				deleteData();
			}
		});
		btnFlush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 调用显示数据的方法
				showData();
			}
		});

		// 初始化显示表格数据
		this.showData();

		//设置标题
		this.setTitle(Constant.USER_TITLE);
		// 窗体大小不可改变
		this.setResizable(false);
		// 设定窗口大小
		this.setSize(Constant.USER_WIDTH, Constant.USER_HEIGHT);
		// 窗体位置居中
		this.setLocationRelativeTo(null);
		// 设定窗口默认关闭方式为退出应用程序
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口可视（显示）
		this.setVisible(true);
	}

	// 查看userdetails表，并显示到表格中
	private void showData() {
		// 查询userdetails表并指定标题
		String sql = "select username as 用户名,case when power='0' then '老师' when power='1' then '学生' end  as 权限  from userdetails";
		// 数据库访问
		DBUtil db = new DBUtil();
		try {
			//链接数据库
			db.getConnection();
			//将查询到的结果存到rs中
			ResultSet rs = db.executeQuery(sql, null);
			//利用ResultSet的getMetaData的方法可以获得ResultSetMeta对象，而ResultSetMetaData存储了 ResultSet的MetaData
			//ResultSet是以表格的形式存在，所以getMetaData就包括了数据的字段名称、类型以及数目等表格所必须具备的信息。
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取列数
			int colCount = rsmd.getColumnCount();
			// 存放列名
			Vector<String> title = new Vector<String>();
			// 列名
			for (int i = 1; i <= colCount; i++) {
				//得到指定列明
				title.add(rsmd.getColumnLabel(i));
			}
			// 表格数据
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			int rowCount = 0;
			//遍历查询结果
			while (rs.next()) {
				rowCount++;
				// 行数据容器
				Vector<String> rowdata = new Vector<String>();
				for (int i = 1; i <= colCount; i++) {
					//得到每行的每列的数据
					rowdata.add(rs.getString(i));
				}
				data.add(rowdata);
			}
			if (rowCount == 0) {
				//将数据添加到表格
				model.setDataVector(null, title);
			} else {
				//将数据添加到表格
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
		//得到所选的行
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
						//得到所选的数据的主键
						String username = table.getValueAt(index[0], 0).toString();
						String sql = "delete from userdetails where username=?";
						//DBUtil类中的更新方法返回值为整型
						int count = db.executeUpdate(sql, new String[] { username });
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

/*	public static void main(String[] args) {
		new UserTableDemo();
	}*/
}
