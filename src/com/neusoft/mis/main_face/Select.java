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
	// ���������
	private JPanel p, pStu, pCour, pGra, p1, p2, p12, p3, p4, p5, p6, pk2, pk3;
	// ����table���
	private JPanel pStuTab, pGraTab, pCouTab, pStuButtons, pGraButtons, pCouButtons;
	// ��ѯ������ť
	private JButton btnOneGraClose;
	// �������
	private JScrollPane graScrollPane, stuScrollPane, couScrollPane;
	// �����˵������
	private JMenuBar menuBar;
	// �����˵����
	private JMenu menuCaoz, menuHelp;
	// �����˵���Ŀ
	private JMenuItem saveItem, exitItem, aboutItem;
	// ����������
	private JToolBar toolBar;
	// ������������ť
	private JButton btnStuIn, btnCouIn, btnStuGra;
	// ������ѯѧ����Ϣ��ť
	private JButton btnSele, btnStuRem, btnAllSele;
	// ������ѯ�γ���Ϣ��ť
	private JButton btnCouSe, btnCouAll, btnCouRem;
	// ������ѯ�ɼ���ť
	private JButton btnGraSe, btnGraAll, btnGraRem;
	// ����table��尴ť
	private JButton btnStuClose, btnGraClose, btnCouClose;
	// ����ѧ�š��γ̴����ǩ
	private JLabel lbno;
	private JLabel lbcno;
	private JLabel lbGraNo;
	private JLabel lbname;
	// �����ı���
	private JTextField txtno, txtcno, txtGraNo, txtname;

	private ImageIcon img;

	private JLabel lbname1, lbname2;
	private DefaultTableModel model, modelStu, modelCou;
	private JTable table, tableStu, tableCou;

	public Select() {

		// ��������ʼ����ǩ����
		Font f = new Font("", 15, 20);

		// ��ʼ��ͼ��
		img = new ImageIcon("src\\img\\select.png");
		// ��ʼ����Ƭ����
		card = new CardLayout();
		// ��������ʼ��Ϊ��Ƭ����
		p = new JPanel(card);

		// ʵ�����˵���
		menuBar = new JMenuBar();
		// ʵ�����˵�
		menuCaoz = new JMenu("����");
		menuHelp = new JMenu("����");
		// ���˵���ӵ��˵���
		menuBar.add(menuCaoz);
		menuBar.add(menuHelp);
		// ʵ�����˵���Ŀ��ע�����
		saveItem = new JMenuItem("����");
		// ��Ӳ˵���Ŀ������
		exitItem = new JMenuItem("�˳�");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�˳�ϵͳ��", "�˳�ϵͳ",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		aboutItem = new JMenuItem("����ϵͳ");
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "�汾1.0\n����:����ɭ\n��Ȩ:Java��ĩ��Ŀ", "����", JOptionPane.WARNING_MESSAGE);
			}
		});

		// ���˵���Ŀ��ӵ��˵�
		menuCaoz.add(saveItem);
		menuCaoz.add(exitItem);
		menuHelp.add(aboutItem);

		// ����������
		toolBar = new JToolBar();
		// ������ť
		btnStuGra = new JButton("��ѯ�ɼ�", new ImageIcon("src\\img\\selectGra.png"));
		btnStuIn = new JButton("��ѯѧ����Ϣ", new ImageIcon("src\\img\\selectStu.png"));
		btnCouIn = new JButton("��ѯ�γ���Ϣ", new ImageIcon("src\\img\\select.png"));
		// ��ӹ�������ť������
		btnStuIn.addActionListener(new BtnListener());
		btnCouIn.addActionListener(new BtnListener());
		btnStuGra.addActionListener(new BtnListener());

		// ����ť��ӵ�������
		toolBar.add(btnStuGra);
		toolBar.add(btnStuIn);
		toolBar.add(btnCouIn);

		// ��ʼ��������ǩ����������
		lbno = new JLabel("ѧ�ţ�");
		lbcno = new JLabel("�γ̴��룺");
		lbGraNo = new JLabel("ѧ�ţ�");
		lbname = new JLabel("ѧ���ɼ���ѯ��",new ImageIcon("src\\img\\select.png"),SwingConstants.CENTER);
		lbname1 = new JLabel("ѧ����Ϣ��ѯ��",new ImageIcon("src\\img\\select.png"),SwingConstants.CENTER);
		lbname2 = new JLabel("�γ���Ϣ��ѯ��",new ImageIcon("src\\img\\select.png"),SwingConstants.CENTER);
		lbname.setFont(f);
		lbname1.setFont(f);
		lbname2.setFont(f);

		// �ı����ʼ��
		txtno = new JTextField(15);
		txtcno = new JTextField(15);
		txtGraNo = new JTextField(15);
		txtname = new JTextField(15);

		// ��ѯ���ڸ�������ʼ��
		pStu = new JPanel(new GridLayout(4, 1));
		pCour = new JPanel(new GridLayout(4, 1));
		pGra = new JPanel(new GridLayout(4, 1));
		// ѧ����Ϣ��ť��ʼ����ť
		btnSele = new JButton("��ѯ");
		btnAllSele = new JButton("��ѯȫ��");
		btnStuRem = new JButton("���");
		// �γ���Ϣ��ť��ʼ��
		btnCouSe = new JButton("��ѯ");
		btnCouAll = new JButton("��ѯȫ��");
		btnCouRem = new JButton("���");
		// �ɼ���Ϣ��ť��ʼ��
		btnGraSe = new JButton("��ѯ");
		btnGraAll = new JButton("��ѯȫ��");
		btnGraRem = new JButton("���");
		// ��Ϣ��ť
		btnStuClose = new JButton("�ر�");
		btnStuClose.addActionListener(new BtnListener());
		// �ɼ���ť
		btnGraClose = new JButton("�ر�");
		btnOneGraClose = new JButton("����");
		btnGraClose.addActionListener(new BtnListener());
		btnOneGraClose.addActionListener(new BtnListener());
		// �γ̱�ť
		btnCouClose = new JButton("�ر�");
		btnCouClose.addActionListener(new BtnListener());

		// ���ѧ����Ϣ��ť����
		btnSele.addActionListener(new BtnOkListener());
		btnAllSele.addActionListener(new BtnOkListener());
		btnStuRem.addActionListener(new BtnRemoveListener());

		// ��ӿγ���Ϣ��ť����
		btnCouSe.addActionListener(new BtnOkListener());
		btnCouAll.addActionListener(new BtnOkListener());
		btnCouRem.addActionListener(new BtnRemoveListener());

		// ��ӳɼ���ť����
		btnGraSe.addActionListener(new BtnOkListener());
		btnGraAll.addActionListener(new BtnOkListener());
		btnGraRem.addActionListener(new BtnRemoveListener());

		// �������ӵ�������Ƭ�����
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
		 * �ɼ�������ѯ������
		 */
		// ����Ĭ�ϱ��ģʽ
		model = new DefaultTableModel();
		// �������
		table = new JTable(model);
		// ���ñ��ѡ��ģʽΪ��һѡ��
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pGraTab = new JPanel(new BorderLayout());
		// ����һ��������壬�������
		graScrollPane = new JScrollPane(table);
		// �����������ӵ���������
		pGraTab.add(graScrollPane, BorderLayout.CENTER);
		// �������
		pGraButtons = new JPanel();
		// ����ť��ӵ������
		pGraButtons.add(btnGraClose);
		// ��ʢ�Ű�ť�������ӵ�������ϲ������棩
		pGraTab.add(pGraButtons, BorderLayout.SOUTH);

		/*
		 * ѧ����Ϣ������ѯ������
		 */
		// ����Ĭ�ϱ��ģʽ
		modelStu = new DefaultTableModel();
		// �������
		tableStu = new JTable(modelStu);
		// ���ñ��ѡ��ģʽΪ��һѡ��
		tableStu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pStuTab = new JPanel(new BorderLayout());
		// ����һ��������壬�������
		stuScrollPane = new JScrollPane(tableStu);
		// �����������ӵ���������
		pStuTab.add(stuScrollPane, BorderLayout.CENTER);
		// �������
		pStuButtons = new JPanel();
		// ����ť��ӵ������
		pStuButtons.add(btnStuClose);
		// ��ʢ�Ű�ť�������ӵ�������ϲ������棩
		pStuTab.add(pStuButtons, BorderLayout.SOUTH);

		/*
		 * �γ���Ϣ������ѯ������
		 */
		// ����Ĭ�ϱ��ģʽ
		modelCou = new DefaultTableModel();
		// �������
		tableCou = new JTable(modelCou);
		// ���ñ��ѡ��ģʽΪ��һѡ��
		tableCou.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pCouTab = new JPanel(new BorderLayout());
		// ����һ��������壬�������
		couScrollPane = new JScrollPane(tableCou);
		// �����������ӵ���������
		pCouTab.add(couScrollPane, BorderLayout.CENTER);
		// �������
		pCouButtons = new JPanel();
		// ����ť��ӵ������
		pCouButtons.add(btnCouClose);
		// ��ʢ�Ű�ť�������ӵ�������ϲ������棩
		pCouTab.add(pCouButtons, BorderLayout.SOUTH);

		// �����������ӵ���Ƭ���֣�������
		p.add("��ѯ�ɼ�", pGra);
		p.add("��ѯѧ����Ϣ", pStu);
		p.add("��ѯ�γ���Ϣ", pCour);
		p.add("showCou", pCouTab);
		p.add("showGra", pGraTab);
		p.add("showStu", pStuTab);

		// ���������ӵ�����
		this.add(p);
		// ���ÿͻ���ͼ��
		this.setIconImage(img.getImage());
		// ���˵������õ�������
		this.setJMenuBar(menuBar);
		// ����������ӵ�������
		this.add(toolBar, BorderLayout.NORTH);
		this.setTitle(Constant.TEACHER_SELECT_TITLE);
		this.setSize(Constant.TEACHER_SELECT_WIDTH, Constant.TEACHER_SELECT_HEIGHT);
		// ���ô������
		this.setLocationRelativeTo(null);
		// ������ӻ�
		this.setVisible(true);
		// �����С���ɸı�
		this.setResizable(false);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * ��������ť��������
	 */
	public class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == btnStuIn || source == btnStuClose) {
				card.show(p, "��ѯѧ����Ϣ");
			}
			if (source == btnCouIn || source == btnCouClose) {
				card.show(p, "��ѯ�γ���Ϣ");
			}
			if (source == btnStuGra || source == btnGraClose || source == btnOneGraClose) {
				card.show(p, "��ѯ�ɼ�");
			}
		}

	}

	/*
	 * ��ѯ��ť������
	 */
	public class BtnOkListener implements ActionListener {
		// ��ʾ���������
		private Component jPanel;

		@Override
		public void actionPerformed(ActionEvent e) {

			// ��Ϣ��ѯ����ѯ����ѧ����Ϣ�¼�
			if (e.getSource() == btnSele) {
				String strsno = txtno.getText();
				if (strsno == null || strsno.equals("")) {
					// ��ʾ��
					JOptionPane.showMessageDialog(jPanel, "    ѧ�Ų���Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				if (!(strsno == null || strsno.equals(""))) {
					stuOneData(strsno);
					card.show(p, "showStu");
				}
			}

			// ��Ϣѧ����ѯ����ѯȫ����ť�¼�
			if (e.getSource() == btnAllSele) {
				new StudentTableDemo();
				Select.this.setVisible(false);
			}

			// �����γ���Ϣ��ѯ
			if (e.getSource() == btnCouSe) {
				// ����γ̴���
				String strcno = txtcno.getText();
				if (strcno == null || strcno.equals("")) {
					// ��ʾ��
					JOptionPane.showMessageDialog(jPanel, "    �γ̴��벻��Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				if (!(strcno == null || strcno.equals(""))) {
					// ���ò�ѯ�����γ���Ϣ�ķ���
					couOneData(strcno);
					card.show(p, "showCou");
				}
			}
			// ȫ���γ���Ϣ��ѯ
			if (e.getSource() == btnCouAll) {
				new CourseTableDemo();
				Select.this.setVisible(false);

			}

			// �����ɼ���ѯ
			if (e.getSource() == btnGraSe) {

				// �����ѧ��
				String strGraNo = txtGraNo.getText();
				if (strGraNo == null || strGraNo.equals("")) {
					// ��ʾ��
					JOptionPane.showMessageDialog(jPanel, "          ѧ�Ų���Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				// ����ѧ�Ų�ѯѧ��
				if (!(strGraNo == null || strGraNo.equals("")) /* && !(strname == null || strname.equals("")) */) {
					// ���õ����ɼ���ѯ����
					graOneData(strGraNo);
					card.show(p, "showGra");
				}
			}
			// ȫ���ɼ���ѯ
			if (e.getSource() == btnGraAll) {
				new GradeTableDemo();
				Select.this.setVisible(false);
			}

		}

	}

	// ��հ�ť������
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
	 * �����ɼ���ѯ����
	 */
	private void graOneData(String sno) {
		System.out.println("aaa");
		// ��ѯgrades��
		String sql = "select sno as ѧ��, sname as ����,gjava as JAVA,gshuju as ���ݿ�,gcaozuo as ����ϵͳ,gzucheng as ���ԭ��,gsuanfa as �㷨,gEnglish as English,gOrical as Orical,ggailv as ������   from grades where sno=? ";

		// ���ݿ����
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			ResultSet rs = db.executeQuery(sql, new String[] { sno });
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
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

	/*
	 * ����ѧ����Ϣ��ѯ����
	 */
	private void stuOneData(String sno) {
		System.out.println("aaa");
		// ��ѯstudents��
		String sql = "select sno as ѧ��, sname as ����,szy as רҵ,sclass as �༶,scall as �绰  from students where sno=? ";

		// ���ݿ����
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			ResultSet rs = db.executeQuery(sql, new String[] { sno });
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
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
				modelStu.setDataVector(null, title);
			} else {
				modelStu.setDataVector(data, title);
			}
		} catch (Exception ee) {
			System.out.println(ee.toString());
			JOptionPane.showMessageDialog(this, "ϵͳ�����쳣�����������ݿ⡣ϵͳ�����˳�������", "����", 0);
		} finally {
			db.closeAll();
		}
	}

	/*
	 * �����γ���Ϣ��ѯ����
	 */
	private void couOneData(String cno) {
		System.out.println("aaa");
		// ��ѯcourses��
		String sql = "select cno as �γ̺�, cname as �γ���,ccredit as ѧ��,cteacher as ��ʦ  from courses where cno=? ";

		// ���ݿ����
		DBUtil db = new DBUtil();
		try {
			db.getConnection();
			ResultSet rs = db.executeQuery(sql, new String[] { cno });
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
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
				modelCou.setDataVector(null, title);
			} else {
				modelCou.setDataVector(data, title);
			}
		} catch (Exception ee) {
			System.out.println(ee.toString());
			JOptionPane.showMessageDialog(this, "ϵͳ�����쳣�����������ݿ⡣ϵͳ�����˳�������", "����", 0);
		} finally {
			db.closeAll();
		}
	}

	/*
	 * public static void main(String[] args) { new Select(); }
	 */
}