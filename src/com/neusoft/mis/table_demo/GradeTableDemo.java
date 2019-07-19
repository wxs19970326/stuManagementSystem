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
 * ѧ���ɼ���ѯ����
 * 
 * @author ASUS
 *
 */
public class GradeTableDemo extends JFrame {
	/**
	 * 
	 */
	//���岼�����ͱ������������ֹ���
	public static boolean b = true;
	private static final long serialVersionUID = 7618296296867174379L;
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
	//�������ذ�ť
	private JButton btnBack;

	public GradeTableDemo() {
		super("�ɼ���");

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
		// ������Ӱ�ť�����
		pButtons = new JPanel();
		// ����ť��ӵ������
		if(Login.power == 0) {
			pButtons.add(bthDelete);
		}
		pButtons.add(btnFlush);
		pButtons.add(btnBack);
		// ��ʢ�Ű�ť�������ӵ�������ϲ������棩
		this.add(pButtons, BorderLayout.SOUTH);

		// ע��ɾ������
		bthDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����û�Ȩ��Ϊ��ʦ���ſ�ɾ��
				if(Login.power == 0) {
					// ����ɾ�����ݵķ���
					deleteData();
				}
			}
		});
		//ע��ˢ�°�ť�ļ���
		btnFlush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ������ʾ���ݵķ���
				showData();
			}
		});
		//ע�᷵�ذ�ť�ļ���
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ������ʾ���ݵķ���
				if (b) {
					Select s = new Select();
					s.setVisible(true);
					GradeTableDemo.this.setVisible(false);
				} else {
					GradeTableDemo.this.setVisible(false);
				}
			}
		});

		// ��ʼ����ʾ�������
		this.showData();

		// �����С���ɸı�
		this.setResizable(false);
		// �趨���ڴ�С
		this.setSize(600, 400);
		// ����λ�þ���
		this.setLocationRelativeTo(null);
		
		// �趨����Ĭ�Ϲرշ�ʽΪ�˳�Ӧ�ó���
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ���ô��ڿ��ӣ���ʾ��
		this.setVisible(true);
	}
	/**
	 * ��ʾ���ݵķ���
	 */
	private void showData() {
		// ��ѯgrades��
		//����sql���
		String sql = "select sno as ѧ��, sname as ����,gjava as JAVA,gshuju as ���ݿ�,gcaozuo as ����ϵͳ,gzucheng as ���ԭ��,gsuanfa as �㷨,gEnglish as English,gOrical as Orical,ggailv as ������  from grades";
		// ���ݿ����
		//�������߶���
		DBUtil db = new DBUtil();
		try {
			//���ݿ�����
			db.getConnection();
			//���ò�ѯ��䣬����ѯ�������rs��
			ResultSet rs = db.executeQuery(sql, null);
			//�õ�rs���������ݵ����ݶ���
			ResultSetMetaData rsmd = rs.getMetaData();
			// ��ȡ����
			int colCount = rsmd.getColumnCount();
			// �������
			Vector<String> title = new Vector<String>();
			// ����
			for (int i = 1; i <= colCount; i++) {
				//��ȡsql��������ƶ���ǩ
				title.add(rsmd.getColumnLabel(i));
			}
			// �������
			Vector<Vector<String>> data = new Vector<Vector<String>>();
			int rowCount = 0;
			//������ѯ���
			while (rs.next()) {
				rowCount++;
				// ������
				Vector<String> rowdata = new Vector<String>();
				for (int i = 1; i <= colCount; i++) {
					//��ȡ����
					rowdata.add(rs.getString(i));
				}
				//��ӵ���������
				data.add(rowdata);
			}
			if (rowCount == 0) {
				//��ʾ���������
				model.setDataVector(null, title);
			} else {
				//��ʾ�������
				model.setDataVector(data, title);
			}
		} catch (Exception ee) {
			System.out.println(ee.toString());
			JOptionPane.showMessageDialog(this, "ϵͳ�����쳣�����������ݿ⡣ϵͳ�����˳�������", "����", 0);
		} finally {
			db.closeAll();
		}
	}

	/**
	 * ɾ�����ݵķ���
	 */
	public void deleteData() {
		//��ȡ����б�
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
						//��ȡѡ����е�ѧ��
						String sno = table.getValueAt(index[0], 0).toString();
						//����ɾ��sql���
						String sql = "delete from grades where sno=?";
						//ִ��ɾ��
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
