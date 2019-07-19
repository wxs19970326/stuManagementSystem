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
 * ѧ����Ϣ��ѯ����
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
	// �����������
	private JScrollPane spTable;
	// ����һ��ʢ�Ű�ť�����
	private JPanel pButtons;
	private JButton bthDelete, btnFlush;
	// ����Ĭ�ϱ��ģʽ
	private DefaultTableModel model;
	// �������
	private JTable table;
	private JButton btnBack;

	public CourseTableDemo() {

		// ����Ĭ�ϱ��ģʽ
		model = new DefaultTableModel();
		// �������
		table = new JTable(model);
		// ���ñ��ѡ��ģʽΪ��һѡ��
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// ����һ��������壬�������
		spTable = new JScrollPane(table);
		// �����������ӵ���������
		this.add(spTable, BorderLayout.CENTER);

		// ������ť
		bthDelete = new JButton("ɾ��");
		btnFlush = new JButton("ˢ��");
		btnBack = new JButton("����");
		// �������
		pButtons = new JPanel();
		// ����ť��ӵ������
		if(Login.power == 0) {
			pButtons.add(bthDelete);
		}
		pButtons.add(btnFlush);
		pButtons.add(btnBack);
		// ��ʢ�Ű�ť�������ӵ�������ϲ������棩
		this.add(pButtons, BorderLayout.SOUTH);

		// ע�����
		bthDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Login.power == 0) {
					// ����ɾ�����ݵķ���
					deleteData();
				}
			}
		});
		btnFlush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ������ʾ���ݵķ���
				showData();
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ������ʾ���ݵķ���
				if (courb) {
					Select s = new Select();
					s.setVisible(true);
					CourseTableDemo.this.setVisible(false);
				} else {
					CourseTableDemo.this.setVisible(false);
				}
			}
		});

		// ��ʼ����ʾ�������
		this.showData();

		//���ñ���
		this.setTitle(Constant.COURSETABLE_TITLE);
		// �����С���ɸı�
		this.setResizable(false);
		// �趨���ڴ�С
		this.setSize(Constant.COURSETABLE_WIDTH, Constant.COURSETABLE_HEIGHT);
		// ����λ�þ���
		this.setLocationRelativeTo(null);
		// �趨����Ĭ�Ϲرշ�ʽΪ�˳�Ӧ�ó���
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ô��ڿ��ӣ���ʾ��
		this.setVisible(true);
	}

	private void showData() {
		// ��ѯcourse��
		String sql = "select cno as �γ̺�, cname as �γ���,ccredit as ѧ��,cteacher as ��ʦ  from courses";
		// ���ݿ����
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			ResultSet rs = db.executeQuery(sql, null);
			ResultSetMetaData rsmd = rs.getMetaData();
			// ��ȡ����
			int colCount = rsmd.getColumnCount();
			// �������
			Vector<String> title = new Vector<String>();
			// ����
			for (int i = 1; i <= colCount; i++) {
				title.add(rsmd.getColumnLabel(i));
			}
			// �������
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			int rowCount = 0;
			while (rs.next()) {
				rowCount++;
				// ������
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
			JOptionPane.showMessageDialog(this, "ϵͳ�����쳣�����������ݿ⡣ϵͳ�����˳�������", "����", 0);
		} finally {
			db.closeAll();
		}
	}

	// ɾ������
	public void deleteData() {
		int index[] = table.getSelectedRows();
		if (index.length == 0) {
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ���ļ�¼", "��ʾ", JOptionPane.PLAIN_MESSAGE);
		} else {
			try {
				int k = JOptionPane.showConfirmDialog(this, "��ȷ��Ҫ�����ݿ���ɾ����ѡ�������� ��", "ɾ��", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (k == JOptionPane.YES_OPTION) {
					DBUtil db = new DBUtil();
					try {
						db.getConnection();
						String sno = table.getValueAt(index[0], 0).toString();
						String sql = "delete from students where sno=?";
						int count = db.executeUpdate(sql, new String[] { sno });
						if (count == 1) {
							JOptionPane.showMessageDialog(this, "ɾ�������ɹ����!", "�ɹ�", JOptionPane.PLAIN_MESSAGE);
							showData();
						} else {
							JOptionPane.showMessageDialog(this, "��Ǹ�� ɾ������ʧ��!", "ʧ��:", 0);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						db.closeAll();
					}
				}
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(this, "��Ǹ!ɾ������ʧ��!��ϵͳ�쳣����", "ʧ��:", 0);
			}
		}
	}

	/*
	 * public static void main(String[] args) { new UserTableDemo(); }
	 */
}
