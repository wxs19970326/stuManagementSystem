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
 * �û��������
 * @author ASUS
 *
 */
public class UserTableDemo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8368422869550481459L;
	// �����������
	private JScrollPane spTable;
	// ����һ��ʢ�Ű�ť�����
	private JPanel pButtons;
	//����ɾ��ˢ�°�ť
	private JButton bthDelete, btnFlush;
	// ����Ĭ�ϱ��ģʽ
	private DefaultTableModel model;
	// �������
	private JTable table;

	public UserTableDemo() {

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
		// �������
		pButtons = new JPanel();
		// ����ť��ӵ������
		pButtons.add(bthDelete);
		pButtons.add(btnFlush);
		// ��ʢ�Ű�ť�������ӵ�������ϲ������棩
		this.add(pButtons, BorderLayout.SOUTH);

		// ע�����
		bthDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����ɾ�����ݵķ���
				deleteData();
			}
		});
		btnFlush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ������ʾ���ݵķ���
				showData();
			}
		});

		// ��ʼ����ʾ�������
		this.showData();

		//���ñ���
		this.setTitle(Constant.USER_TITLE);
		// �����С���ɸı�
		this.setResizable(false);
		// �趨���ڴ�С
		this.setSize(Constant.USER_WIDTH, Constant.USER_HEIGHT);
		// ����λ�þ���
		this.setLocationRelativeTo(null);
		// �趨����Ĭ�Ϲرշ�ʽΪ�˳�Ӧ�ó���
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ô��ڿ��ӣ���ʾ��
		this.setVisible(true);
	}

	// �鿴userdetails������ʾ�������
	private void showData() {
		// ��ѯuserdetails��ָ������
		String sql = "select username as �û���,case when power='0' then '��ʦ' when power='1' then 'ѧ��' end  as Ȩ��  from userdetails";
		// ���ݿ����
		DBUtil db = new DBUtil();
		try {
			//�������ݿ�
			db.getConnection();
			//����ѯ���Ľ���浽rs��
			ResultSet rs = db.executeQuery(sql, null);
			//����ResultSet��getMetaData�ķ������Ի��ResultSetMeta���󣬶�ResultSetMetaData�洢�� ResultSet��MetaData
			//ResultSet���Ա�����ʽ���ڣ�����getMetaData�Ͱ��������ݵ��ֶ����ơ������Լ���Ŀ�ȱ��������߱�����Ϣ��
			ResultSetMetaData rsmd = rs.getMetaData();
			// ��ȡ����
			int colCount = rsmd.getColumnCount();
			// �������
			Vector<String> title = new Vector<String>();
			// ����
			for (int i = 1; i <= colCount; i++) {
				//�õ�ָ������
				title.add(rsmd.getColumnLabel(i));
			}
			// �������
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			int rowCount = 0;
			//������ѯ���
			while (rs.next()) {
				rowCount++;
				// ����������
				Vector<String> rowdata = new Vector<String>();
				for (int i = 1; i <= colCount; i++) {
					//�õ�ÿ�е�ÿ�е�����
					rowdata.add(rs.getString(i));
				}
				data.add(rowdata);
			}
			if (rowCount == 0) {
				//��������ӵ����
				model.setDataVector(null, title);
			} else {
				//��������ӵ����
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
		//�õ���ѡ����
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
						//�������ݿ�
						db.getConnection();
						//�õ���ѡ�����ݵ�����
						String username = table.getValueAt(index[0], 0).toString();
						String sql = "delete from userdetails where username=?";
						//DBUtil���еĸ��·�������ֵΪ����
						int count = db.executeUpdate(sql, new String[] { username });
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

/*	public static void main(String[] args) {
		new UserTableDemo();
	}*/
}
