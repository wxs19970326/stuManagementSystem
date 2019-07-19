package com.neusoft.mis.main_face;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.neusoft.mis.constant.Constant;
import com.neusoft.mis.entity.Course;
import com.neusoft.mis.entity.Student;
import com.neusoft.mis.entity.User;
import com.neusoft.mis.main_face.StudentFrame.BtnRemoveListener;
import com.neusoft.mis.main_face.StudentFrame.BtnUpdatePwdListener;
//import com.neusoft.mis.main_face.CourseInfor.BtnCouOkListener;
import com.neusoft.mis.service.CourseService;
import com.neusoft.mis.service.StuService;
import com.neusoft.mis.service.UserService;
import com.neusoft.mis.table_demo.UserTableDemo;
import com.neusoft.mis.uitl.DBUtil;

/**
 * ��ʦ��½������
 * 
 * @author ASUS
 *
 */
public class TeacherFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3725713683054780797L;
	// ���������
	private CardLayout card;
	private JPanel pmain, p, p1, p2, p3, p4, p5, p6;
	// ���������
	private JPanel cp, cp1, cp2, cp3, cp4, cp5;
	// �����˵������
	private JMenuBar menuBar;
	// �����˵����
	private JMenu menuCaoz, menuHelp;
	// �����˵���Ŀ
	private JMenuItem loginItem, loginOutItem, exitItem, aboutItem;
	// ����������
	private JToolBar toolBar;
	// ������ť
	private JButton btnAdd1, btnAdd2, btnSelect, btnSave, btnAddGra, btnreSet, btnOk, btnRemove;
	// ������ǩ
	private JLabel lbCname, lbCno, lbCcredit, lbCteacher;
	// ������ť
	private JButton btnCouOk;
	// ������Ͽ�
	private JComboBox cmbCno, cmbCname, cmbCcredit, cmbCteacher;
	// �����γ���Ϣ����
	private Course course;
	private CourseService courseService;

	/**
	 * �޸��������
	 */
	// ���������
	private JPanel rp, rp1, rp2, rp3, rp4, rp5;
	// ������ǩ
	private JLabel lbUser, lbPwd, lbNewPwd, lbRePwd;
	// �����ı���
	private JTextField txtUser;
	// ���������
	private JPasswordField pfPwd, pfNewPwd, pfRePwd;
	// ������ť
	private JButton rbtnIsUpdate, rbtnRemove;
	/**
	 * ������ѧ�š�רҵ���༶���绰��ǩ����
	 */
	private JLabel lbname;
	private JLabel lbno;
	private JLabel lbzy;
	private JLabel lbclass;
	private JLabel lbcall;
	// �����ı���
	private JTextField txtno, txtname, txtzy, txtclass, txtcall;
	// ͼ��
	private ImageIcon img;
	// ����ѧ������
	private Student student;
	private StuService stuService;

	// �����û�����
	private User user;
	private UserService userService;

	public TeacherFrame() {
		card = new CardLayout();
		pmain = new JPanel(card);
		// ʵ����ѧ��ҵ���߼�����
		stuService = new StuService();
		// ��ʼ��CourseService����
		courseService = new CourseService();
		/**
		 * ͼ���ʼ��
		 */
		img = new ImageIcon("src\\img\\client.png");

		// ʵ�����˵���
		menuBar = new JMenuBar();
		// ʵ�����˵�
		menuCaoz = new JMenu("����");
		menuHelp = new JMenu("����");
		// ���˵���ӵ��˵���
		menuBar.add(menuCaoz);
		menuBar.add(menuHelp);
		// ʵ�����˵���Ŀ��ע�����
		loginItem = new JMenuItem("��ǰ��¼",new ImageIcon("src\\img\\login.png"));
		loginItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "��ӭ��" + Login.username + "���û�����", "��ǰ��¼",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		loginOutItem = new JMenuItem("�л��û�",new ImageIcon("src\\img\\btnReset.png"));
		loginOutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TeacherFrame.this.setVisible(false);
				new Login();
			}
		});

		exitItem = new JMenuItem("�˳�",new ImageIcon("src\\img\\exit.png"));
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
				JOptionPane.showMessageDialog(null, "�汾1.0\n����:����ɭ\n��Ȩ:Java��ĩ��Ŀ", "����",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// ���˵���Ŀ��ӵ��˵�
		menuCaoz.add(loginItem);
		menuCaoz.add(loginOutItem);
		menuCaoz.add(exitItem);
		menuHelp.add(aboutItem);

		// ����������
		toolBar = new JToolBar();
		// ������ť
		btnAdd1 = new JButton("���ѧ����Ϣ", new ImageIcon("src\\img\\add1.png"));
		btnAdd2 = new JButton("��ӿγ���Ϣ", new ImageIcon("src\\img\\add2.png"));
		btnAddGra = new JButton("��ӳɼ�", new ImageIcon("src\\img\\addGra.png"));
		btnSelect = new JButton("��ѯ��Ϣ", new ImageIcon("src\\img\\select.png"));
		btnSave = new JButton("�û�����", new ImageIcon("src\\img\\user.png"));
		btnreSet = new JButton("�޸�����", new ImageIcon("src\\img\\reset.png"));
		// ��ӹ�������ť������
		btnAdd1.addActionListener(new BtnListener());
		btnAdd2.addActionListener(new BtnListener());
		btnSelect.addActionListener(new BtnListener());
		btnSave.addActionListener(new BtnListener());
		btnAddGra.addActionListener(new BtnListener());
		btnreSet.addActionListener(new BtnListener());

		// ����ť��ӵ�������
		toolBar.add(btnAdd1);
		toolBar.add(btnAdd2);
		toolBar.add(btnAddGra);
		toolBar.add(btnSelect);
		toolBar.add(btnreSet);
		toolBar.add(btnSave);

		/**
		 * ѧ����Ϣ
		 */
		// ��ǩ��ʼ��
		lbname = new JLabel("������");
		lbno = new JLabel("ѧ�ţ�");
		lbzy = new JLabel("רҵ��");
		lbclass = new JLabel("�༶��");
		lbcall = new JLabel("�绰��");

		// �ı����ʼ��
		txtname = new JTextField(20);
		txtno = new JTextField(20);
		txtzy = new JTextField(20);
		txtclass = new JTextField(20);
		txtcall = new JTextField(20);

		// ��ʼ��6��1�е������
		p = new JPanel(new GridLayout(6, 1));

		// ��ʼ����ť
		btnOk = new JButton("����",new ImageIcon("src\\img\\ok.png"));
		btnRemove = new JButton("���",new ImageIcon("src\\img\\remove.png"));
		// ��Ӱ�ť����
		btnOk.addActionListener(new BtnOkListener());
		btnRemove.addActionListener(new BtnRemoveListener());

		// ����ʼ�����
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 25));
		p1.add(lbno);
		p1.add(txtno);
		p.add(p1);

		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
		p2.add(lbname);
		p2.add(txtname);
		p.add(p2);

		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		p3.add(lbzy);
		p3.add(txtzy);
		p.add(p3);

		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		p4.add(lbclass);
		p4.add(txtclass);
		p.add(p4);

		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		p5.add(lbcall);
		p5.add(txtcall);
		p.add(p5);

		p6 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		p6.add(btnOk);
		p6.add(btnRemove);
		p.add(p6);

		/**
		 * �γ�
		 */
		// ��ʼ����ť
		// ��Ӽ�����
		btnCouOk = new JButton("����",new ImageIcon("src\\img\\ok.png"));
		// btnRemove = new JButton("���");
		btnCouOk.addActionListener(new BtnCouOkListener());

		// ��ʼ����ǩ
		lbCno = new JLabel("�γ̴��� ��");
		lbCname = new JLabel("��  ��  �� ��");
		lbCcredit = new JLabel("ѧ        �� ��");
		lbCteacher = new JLabel("���ν�ʦ ��");

		// �����ʵ����
		cp = new JPanel(new GridLayout(5, 1));

		// ��Ͽ��ʼ��
		cmbCno = new JComboBox(new String[] { "01                                               ", "02", "03", "11",
				"12", "13", "21", "22" });
		cmbCno.setSize(8, 20);
		cmbCname = new JComboBox(new String[] { "JAVA", "���ݿ�ԭ�� ", "����ϵͳ����ԭ��                 ", "��������ԭ��  ", "�㷨",
				"��ѧӢ��", "Orical", "������" });
		cmbCcredit = new JComboBox(new String[] { "2                                                  ", "3", "4" });
		cmbCteacher = new JComboBox(new String[] { "����ʦ", "����ʦ", "����ʦ", "����ʦ", "����ʦ", "����ʦ", "����ʦ",
				"����ʦ                                        " });
		cmbCno.setEditable(true);
		cmbCname.setEditable(true);
		cmbCcredit.setEditable(true);
		cmbCteacher.setEditable(true);

		cp1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		cp1.add(lbCno);
		cp1.add(cmbCno);
		cp.add(cp1);

		cp2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		cp2.add(lbCname);
		cp2.add(cmbCname);
		cp.add(cp2);

		cp3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		cp3.add(lbCcredit);
		cp3.add(cmbCcredit);
		cp.add(cp3);

		cp4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		cp4.add(lbCteacher);
		cp4.add(cmbCteacher);
		cp.add(cp4);

		cp5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		cp5.add(btnCouOk);
		cp.add(cp5);

		/**
		 * �޸�����
		 */

		rp = new JPanel(new GridLayout(5, 1));

		// ��ʼ����ǩ
		lbUser = new JLabel("��  ��  ����");
		lbPwd = new JLabel("��  ��  �룺");
		lbNewPwd = new JLabel("��  ��  �룺");
		lbRePwd = new JLabel("ȷ�����룺");

		// ��ʼ���ı���
		txtUser = new JTextField(20);
		txtUser.setText(Login.username);
		// ��ʼ�������
		pfPwd = new JPasswordField(20);
		pfNewPwd = new JPasswordField(20);
		pfRePwd = new JPasswordField(20);
		pfPwd.setEchoChar('*');
		pfNewPwd.setEchoChar('*');
		pfRePwd.setEchoChar('*');
		// ��ʼ����ť
		rbtnIsUpdate = new JButton("�޸�",new ImageIcon("src\\img\\reset.png"));
		rbtnRemove = new JButton("���",new ImageIcon("src\\img\\remove.png"));
		// ��Ӱ�ť������
		rbtnRemove.addActionListener(new BtnRemoveListener());
		rbtnIsUpdate.addActionListener(new BtnUpdatePwdListener());

		rp1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		rp1.add(lbUser);
		rp1.add(txtUser);
		rp.add(rp1);

		rp2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		rp2.add(lbPwd);
		rp2.add(pfPwd);
		rp.add(rp2);

		rp3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		rp3.add(lbNewPwd);
		rp3.add(pfNewPwd);
		rp.add(rp3);

		rp4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		rp4.add(lbRePwd);
		rp4.add(pfRePwd);
		rp.add(rp4);

		rp5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
		rp5.add(rbtnIsUpdate);
		rp5.add(rbtnRemove);
		rp.add(rp5);

		pmain.add(p, "addStu");
		pmain.add(cp, "addCou");
		pmain.add(rp, "reset");
		// ���������ӵ�������
		this.add(pmain);
		// ���ÿͻ���ͼ��
		this.setIconImage(img.getImage());
		// ���˵������õ�������
		this.setJMenuBar(menuBar);
		// ����������ӵ�������
		this.add(toolBar, BorderLayout.NORTH);
		// ���ñ���
		this.setTitle(Constant.TEACHER_TITLE);
		// ���ÿ��
		this.setSize(Constant.TEACHER_WIDTH, Constant.TEACHER_HEIGHT);
		// ����λ�þ���
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		// �����С���ɸı�
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * ��������ť��������
	 */
	public class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source == btnAdd1) {
				//���ѧ����Ϣ����
				card.show(pmain, "addStu");
			}
			if (source == btnAdd2) {
				// ��ӿγ���Ϣ����
				card.show(pmain, "addCou");
			}
			if (source == btnSelect) {
				//��ѯ����
				new Select();
			}
			if (source == btnSave) {
				//�û�����
				new UserTableDemo();
			}
			if (source == btnAddGra) {
				//��ӳɼ���Ϣ����
				new GradeFrame();
			}
			if (source == btnreSet) {
				// �޸����봰��
				card.show(pmain, "reset");
			}
		}

	}

	/**
	 * ���밴ť������
	 */
	public class BtnOkListener implements ActionListener {
		private Component jPanel;

		@Override
		public void actionPerformed(ActionEvent e) {
			// �����ѧ��
			String strno = txtno.getText();
			if (strno == null || strno.equals("")) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "ѧ�Ų���Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			// ���������
			String strname = txtname.getText();
			if (!(strno == null || strno.equals("")) && (strname == null || strname.equals(""))) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "��������Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			// ����רҵ
			String strzy = txtzy.getText();
			if (!(strno == null || strno.equals("")) && !(strname == null || strname.equals(""))
					&& (strzy == null || strzy.equals(""))) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "רҵ����Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			// ����༶
			String strclass = txtclass.getText();
			if (!(strno == null || strno.equals("")) && !(strname == null || strname.equals(""))
					&& !(strzy == null || strzy.equals("")) && (strclass == null || strclass.equals(""))) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "�༶����Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			// ����绰
			String strcall = txtcall.getText();
			if (!(strno == null || strno.equals("")) && !(strname == null || strname.equals(""))
					&& !(strzy == null || strzy.equals("")) && !(strclass == null || strclass.equals(""))
					&& (strcall == null || strcall.equals(""))) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "�绰����Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}

			/**
			 * ������Ϣ
			 */
			if (!(strno == null || strno.equals("")) && !(strname == null || strname.equals(""))
					&& !(strzy == null || strzy.equals("")) && !(strclass == null || strclass.equals(""))
					&& !(strcall == null || strcall.equals(""))) {
				// ���������Ϣ��װ��ѧ������
				student = new Student(strno, strname, strzy, strclass, strcall);
				if (stuService.saveInformation(student)) {
					// ��ʾ��
					JOptionPane.showMessageDialog(jPanel, "����ɹ���", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
					// ����ɹ��Ժ��������ı���ķ�������ı���
					removeTxt();
				} else {
					JOptionPane.showMessageDialog(jPanel, "ѧ���ظ�������ʧ�ܣ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				/*
				 * userStu = new Student(strno); if (stuService.saveStuUser(userStu)) { // ��ʾ��
				 * JOptionPane.showMessageDialog(jPanel, "ѧ���û���ע�ᣬ��ʼ����Ϊ123456��", "��ܰ��ʾ",
				 * JOptionPane.WARNING_MESSAGE); removeTxt(); } else {
				 * JOptionPane.showMessageDialog(jPanel, "����ʧ�ܣ�", "��ܰ��ʾ",
				 * JOptionPane.WARNING_MESSAGE); }
				 */
			}

		}

	}

	/**
	 * ��հ�ť������
	 */
	public class BtnRemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == rbtnRemove) {
				pfPwd.setText("");
				pfNewPwd.setText("");
				pfRePwd.setText("");
			}
			if (e.getSource() == btnRemove) {
				removeTxt();
			}
		}
	}

	/**
	 * �޸����밴ť������
	 */
	public class BtnUpdatePwdListener implements ActionListener {

		private Component jPanel;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			userService = new UserService();
			String strPwd = String.valueOf(pfPwd.getPassword());

			if (strPwd == null || strPwd.equals("")) {
				JOptionPane.showMessageDialog(jPanel, "���벻��Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			String strNewPwd = String.valueOf(pfNewPwd.getPassword());
			if (!(strPwd == null || strPwd.equals("")) && (strNewPwd == null || strNewPwd.equals(""))) {
				JOptionPane.showMessageDialog(jPanel, "�����벻��Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			String strRePwd = String.valueOf(pfRePwd.getPassword());
			if (!(strPwd == null || strPwd.equals("")) && !(strNewPwd == null || strNewPwd.equals(""))
					&& (strRePwd == null || strRePwd.equals(""))) {
				JOptionPane.showMessageDialog(jPanel, "ȷ�����벻��Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			if (!(strPwd == null || strPwd.equals("")) && !(strNewPwd == null || strNewPwd.equals(""))
					&& !(strRePwd == null || strRePwd.equals("")) && !(strNewPwd.equals(strRePwd))) {
				JOptionPane.showMessageDialog(jPanel, "�����������벻һ�£�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			if (!(strPwd == null || strPwd.equals("")) && !(strNewPwd == null || strNewPwd.equals(""))
					&& !(strRePwd == null || strRePwd.equals("")) && (strNewPwd.equals(strRePwd))) {
				user = userService.findUserByPwd(Login.username, strPwd);
				if (user != null) {
					if ((user.getUsername().equals(Login.username)) && !(user.getPassword().equals(strPwd))) {
						JOptionPane.showMessageDialog(jPanel, "ԭ�����������", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
					}
					if ((user.getUsername().equals(Login.username)) && (user.getPassword().equals(strPwd))) {
						System.out.println(user.getUsername());
						System.out.println(user.getPassword());
							user.setPassword(strRePwd);
							System.out.println(user.getPassword());
							DBUtil db = new DBUtil();
							try {
								db.getConnection();
								db.reSet(user);
							} catch (SQLException e) {
								// TODO �Զ����ɵ� catch ��
								e.printStackTrace();
							}
							JOptionPane.showMessageDialog(jPanel, "�޸ĳɹ���", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
							pfPwd.setText("");
							pfNewPwd.setText("");
							pfRePwd.setText("");
						}
					}
				}
			}

		}

	

	/**
	 * ����γ���Ϣ��ť������
	 */
	public class BtnCouOkListener implements ActionListener {

		private Component jPanel;

		@Override
		public void actionPerformed(ActionEvent e) {
			String strCno = cmbCno.getSelectedItem().toString();
			String strCname = cmbCname.getSelectedItem().toString();
			String strCcredit = cmbCcredit.getSelectedItem().toString();
			String strCteacher = cmbCteacher.getSelectedItem().toString();
			// ��ѡ�еĿγ���Ϣ��װ��Course����
			course = new Course(strCno, strCname, strCcredit, strCteacher);
			// ��������
			if (courseService.saveCourse(course)) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "����ɹ���", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(jPanel, "����ʧ�ܣ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

	/**
	 * ����ı���ķ���
	 */
	public void removeTxt() {
		txtcall.setText("");
		txtclass.setText("");
		txtname.setText("");
		txtno.setText("");
		txtzy.setText("");
	}

	/*
	 * public static void main(String[] args) { new TeacherFrame(); }
	 */

}

