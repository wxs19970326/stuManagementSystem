package com.neusoft.mis.main_face;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.neusoft.mis.constant.Constant;
import com.neusoft.mis.entity.User;
import com.neusoft.mis.service.UserService;

/**
 * ע�ᴰ����
 * @author ASUS
 *
 */
public class Registe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7946304615211201131L;
	// �����������������
	private JPanel p, p1, p2, p3, p4, p5;
	// ������ǩ
	private JLabel lName, lPwd1, lPwd2, lPow;
	// ȷ�Ϻ����ð�ť
	private JButton bRegister, bRemove;
	// �û��� �ı���
	private JTextField tName;
	// ͼ��
	private ImageIcon img;
	// �����
	private JPasswordField pwd1, pwd2;
	// ��½Ȩ�� ��ѡ��ť
	private JRadioButton /* rbtnTeacher, */ rbtnStudent/* ,rbtnVis */;
	// ��ʾ���
	private Component jPanel;
	// ע����û�
	private static User user, userfind;
	private UserService userService;

	// ����������
	public Registe() {
		//��������壬���ÿ�Ƭ����
		p = new JPanel(new GridLayout(5, 1));
		//ʵ�����û�ҵ���߼�����
		userService = new UserService();
		// ʵ�������
		lName = new JLabel("��  ��  ����",new ImageIcon("src\\img\\userName.png"),SwingConstants.CENTER);
		lPwd1 = new JLabel("��        �룺",new ImageIcon("src\\img\\password.png"),SwingConstants.CENTER);
		lPwd2 = new JLabel("ȷ�����룺",new ImageIcon("src\\img\\password.png"),SwingConstants.CENTER);
		lPow = new JLabel("ע��Ȩ�ޣ�",new ImageIcon("src\\img\\power.png"),SwingConstants.CENTER);
		//ʵ������ť�齨
		bRegister = new JButton("ע��",new ImageIcon("src\\img\\btnRegiste.png"));
		bRemove = new JButton("���",new ImageIcon("src\\img\\btnReset.png"));
		bRegister.setSize(60, 25);
		bRemove.setSize(60, 25);
		//ʵ�������
		pwd1 = new JPasswordField(20);
		pwd2 = new JPasswordField(20);
		pwd1.setEchoChar('*');
		pwd2.setEchoChar('*');
		//ʵ���ı���
		tName = new JTextField(20);

		/**
		 * ��½Ȩ�޵�ѡ��ť��ʵ������ʵ�ֵ�ѡ
		 */
		rbtnStudent = new JRadioButton("ѧ��");
		// rbtnTeacher = new JRadioButton("��ʦ");
		// rbtnVis = new JRadioButton("�ÿ�");
		// ��ѡ�߼�
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbtnStudent);
		// bg.add(rbtnTeacher);
		// bg.add(rbtnVis);

		// �������ӵ������
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
		p1.add(lName);
		p1.add(tName);
		p.add(p1);

		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 11, 20));
		p2.add(lPwd1);
		p2.add(pwd1);
		p.add(p2);

		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		p3.add(lPwd2);
		p3.add(pwd2);
		p.add(p3);

		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		p4.add(lPow);
		// p4.add(rbtnTeacher);
		p4.add(rbtnStudent);
		// p4.add(rbtnVis);
		p.add(p4);

		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		p5.add(bRegister);
		p5.add(bRemove);
		p.add(p5);

		// ��հ�ť�¼�����
		bRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ����ı���������
				pwd1.setText("");
				pwd2.setText("");
				tName.setText("");
				// ��յ�ѡ��ť��
				bg.clearSelection();
			}
		});
		//ע�ᰴť������
		bRegister.addActionListener(new BregisterListener());

		// ���ô���ͼ��
		img = new ImageIcon("src\\img\\btnRegiste.png");
		this.setIconImage(img.getImage());
		//���ñ���
		this.setTitle(Constant.REGISTER_TITLE);
		// �������ӵ�������
		this.add(p);
		//���ô�����ӻ�
		this.setVisible(true);
		//���ÿ��
		this.setSize(Constant.REGISTER_WIDTH, Constant.REGISTER_HEIGHT);
		//����λ�þ���
		this.setLocationRelativeTo(null);
		//�����С���ɸı�
		this.setResizable(false);

	}

	/**
	 * �����࣬����ע�ᰴť
	 */
	public class BregisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			/**
			 * ��ȡ�û����������
			 */
			String userName = tName.getText().trim();
			String password = new String(pwd1.getPassword());
			String rePassword = new String(pwd2.getPassword());
			// �û�Ȩ�ޣ�0Ϊ��ʦ��1Ϊѧ��
			//Ĭ��Ϊ1
			int power = 1;

			// �ж��û����ı����Ƿ�����
			if (userName == null || userName.equals("")) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "�û�������Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}

			// �ж��������
			if (!(userName == null || userName.equals("")) && (password == null || password.equals(""))) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "���벻��Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}

			// �ж�ȷ���������
			if (!(userName == null || userName.equals("")) && !(password == null || password.equals(""))
					&& (rePassword == null || rePassword.equals(""))) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "ȷ�����벻��Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}

			// �жϵ�½��ʽ�Ƿ���ѡ
			boolean powStudent = rbtnStudent.isSelected();
			// boolean powTeacher = rbtnTeacher.isSelected();
			if (!(userName == null || userName.equals("")) && !(password == null || password.equals(""))
					&& !(rePassword == null || rePassword.equals("")) && password.equals(rePassword)
					&& (!powStudent /* && !powTeacher && !powVisite */)) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "��ѡ���½��ʽ��", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			/*
			 * if(powTeacher) { power = 0; }
			 */
			if (powStudent) {
				power = 1;
			}
			// �ж����������Ƿ���ͬ���ж�ע�������Ƿ���д��ȫ
			if (!(userName == null || userName.equals("")) && !(password == null || password.equals(""))
					&& !(rePassword == null || rePassword.equals("")) && password.equals(rePassword)
					&& password.equals(rePassword) && (powStudent /* || powTeacher */)) {
				// �����ͬ�������ݷ�װ��user������
				user = new User(userName, password, power);

				// ����ע��ʱ����д���û�����ѯ�Ƿ��Ѿ�ʹ�ø��û���ע��
				userfind = userService.findUserByName(userName.trim());
				/**
				 * ����ע����û����߼�
				 */
				if (userService.saveUser(user)) {
					// ��ʾ��
					JOptionPane.showMessageDialog(jPanel, "ע��ɹ���", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
					Registe.this.setVisible(false);
				}
				// �ж��û����Ƿ��ظ�
				if (userfind != null) {
					if (userfind.getUsername().equals(userName)) {
						// ��ʾ��
						JOptionPane.showMessageDialog(jPanel, "�û����ظ���", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
					}
				}
			} else if (!(userName == null || userName.equals("")) && !(password == null || password.equals(""))
					&& !(rePassword == null || rePassword.equals("")) && !(password.equals(rePassword))) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "�������벻һ�£�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}

		}

	}
}
