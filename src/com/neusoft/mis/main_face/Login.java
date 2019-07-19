package com.neusoft.mis.main_face;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
 * ��½������
 * 
 * @author ASUS
 *
 */
public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7537478672388650187L;
	/**
	 * �����������
	 */
	// ���
	private JPanel p, p0, p1, p2, p3, p4;
	// �����˵������
	private JMenuBar menuBar;
	// �����˵����
	private JMenu menuHelp;
	// �����˵���Ŀ
	private JMenuItem aboutItem;
	// ��½��ע�ᣬ��հ�ť
	private JButton btnOk, btnReg, btnRemove;
	// ��½��ʽ ��ѡ��ť
	private JRadioButton rbtnTeacher, rbtnStudent;
	// �û����ı���
	private JTextField txdName;
	// �����
	private JPasswordField pwd;
	// ��ǩ
	private JLabel lbMain, lbName, lbPwd, lbPow;
	// ͼ��
	private ImageIcon img;
	// ��ʾ�����
	private Component jPanel;
	// ��ѡ��ť��
	private ButtonGroup bg;

	private UserService userService;
	private User user;
	public static String username;
	
	public static int power = 2;

	public Login() {
		// ���û�ҵ���߼�����ʵ����
		userService = new UserService();

		// ʵ�����˵���
		menuBar = new JMenuBar();
		// ʵ�����˵�
		menuHelp = new JMenu("����");
		// ���˵���ӵ��˵���
		menuBar.add(menuHelp);
		// ʵ�����˵���
		aboutItem = new JMenuItem("ע��?");
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "��ϵͳֻ��ѧ������ע�ᣡ", "����", JOptionPane.WARNING_MESSAGE);
			}
		});
		// ���˵���Ŀ��ӵ��˵�
		menuHelp.add(aboutItem);

		/**
		 * ͼ���ʼ��
		 */
		img = new ImageIcon("src\\img\\login.png");

		/**
		 * ���������ʼ��
		 * Flowlayout ���� ���Ҽ��10 ���¼��30
		 */
		p0 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 11, 0));
		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
		//�������GridLayout����  ����һ��
		p = new JPanel(new GridLayout(5, 1));

		/**
		 * ��ǩ��ʼ��
		 */
		Font f = new Font(null, 22, 22);
		lbMain = new JLabel("ѧ������ϵͳ",new ImageIcon("src\\img\\userName.png"),SwingConstants.CENTER);
		lbName = new JLabel("��  ��  ��:",new ImageIcon("src\\img\\userName.png"),SwingConstants.CENTER);
		lbPwd = new JLabel("��        ��:",new ImageIcon("src\\img\\password.png"),SwingConstants.CENTER);
		lbPow = new JLabel("��½��ʽ:",new ImageIcon("src\\img\\power.png"),SwingConstants.CENTER);
		lbMain.setFont(f);

		//���õ�¼ͼ�ν���ı���ͼƬ
		/**
		 * ��ť��ʼ��
		 */
		btnOk = new JButton("��½",new ImageIcon("src\\img\\login.png"));
		btnReg = new JButton("ע��",new ImageIcon("src\\img\\btnRegiste.png"));
		btnRemove = new JButton("���",new ImageIcon("src\\img\\btnReset.png"));

		/**
		 * Ȩ�޵�ѡ��ť
		 */
		rbtnStudent = new JRadioButton("ѧ��");
		rbtnTeacher = new JRadioButton("��ʦ");
		// rbtnVis = new JRadioButton("�ÿ�");
		//ʵ�ֵ�ѡ�߼�
		bg = new ButtonGroup();
		bg.add(rbtnStudent);
		bg.add(rbtnTeacher);
		// bg.add(rbtnVis);

		/**
		 * �ı����������ʼ��
		 */
		txdName = new JTextField(15);
		pwd = new JPasswordField(15);
		//���������ʾΪ*
		pwd.setEchoChar('*');

		/**
		 * ע�ᰴť������
		 */
		btnReg.addActionListener(new BtnRegListener());
		btnOk.addActionListener(new BtnOkListener());
		btnRemove.addActionListener(new BtnRemoveListener());

		/**
		 * ��С ����ֱ���������� �ٽ����������������
		 */
		p0.add(lbMain);
		p.add(p0);
		
		p1.add(lbName);// �û�����ǩ
		p1.add(txdName);// �û����ı���
		p.add(p1);

		p2.add(lbPwd);// �����ǩ
		p2.add(pwd);// �����
		p.add(p2);

		p3.add(lbPow);// Ȩ�ޱ�ǩ
		p3.add(rbtnTeacher);// Ȩ�޵�ѡ��ť
		p3.add(rbtnStudent);// Ȩ�޵�ѡ��ť
		// p3.add(rbtnVis);//Ȩ�޵�ѡ��ť
		p.add(p3);

		p4.add(btnOk);// ��¼��ť
		p4.add(btnReg);// ע�ᰴť
		p4.add(btnRemove);// ��հ�ť
		p.add(p4);
		
		this.add(p);
		// ���˵������õ�������
		this.setJMenuBar(menuBar);
		//���ô��岻�ɸı��С
		this.setResizable(false);
		//����ͼ��
		this.setIconImage(img.getImage());
		//���ñ���
		this.setTitle(Constant.LOGIN_TITLE);
		//���ÿ��
		this.setSize(Constant.LOGIN_WIDTH, Constant.LOGIN_HEIGHT);
		//����λ�þ���
		this.setLocationRelativeTo(null);
		//������ӻ�
		this.setVisible(true);
		//����ر�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * �����࣬�����½��ť�¼�
	 */
	public class BtnOkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// ������û���
			String strName = txdName.getText();
			if (strName == null || strName.equals("")) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "�û�������Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			// ���������
			String strPwd = new String(pwd.getPassword());
			if (!(strName == null || strName.equals("")) && (strPwd == null || strPwd.equals(""))) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "���벻��Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			/**
			 * �жϵ�ѡ��ťѡ��״̬����δѡ�У�����ʾ
			 */
			boolean powStudent = false;
			boolean powTeacher = false;
			powStudent = rbtnStudent.isSelected();
			powTeacher = rbtnTeacher.isSelected();
			// boolean powVisite = rbtnVis.isSelected();
			if (!(strName == null || strName.equals("")) && !(strPwd == null || strPwd.equals(""))
					&& (!powStudent && !powTeacher /* && !powVisite */)) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "��ѡ���½��ʽ��", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			
			if (powStudent) {
				power = 1;
			}
			if (powTeacher) {
				power = 0;
			}
			// �����û�����ѯ�û�
			user = userService.findUserByName(strName.trim());
			
			if ((user != null) && !(strName == null || strName.equals("")) && !(strPwd == null || strPwd.equals("")) && (!powStudent || !powTeacher /* && !powVisite */)) {
				//��������û�������username �Ա�֪�����ĸ��û���¼
				username = user.getUsername();
				// �ж������Ƿ���ȷ
				if (user.getPassword().equals(strPwd)) {
					// �жϵ�½Ȩ���Ƿ�ƥ��
					if (user.getPower() == power) {
						// �ж���ѧ����½������ʦ��½
						if (power == 0) {
							Login.this.setVisible(false);
							//����ʦ�ͻ���
							new TeacherFrame();
						}
						if (power == 1) {
							Login.this.setVisible(false);
							//��ѧ���ͻ���
							new StudentFrame();
						}
					} else if((powStudent || powTeacher) && !(user.getPower() == power)){
						JOptionPane.showMessageDialog(jPanel, "��½Ȩ�޲�ƥ�䣡����", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(jPanel, "������󣡣���", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

	/**
	 * �����࣬����ע�ᰴť
	 */
	public class BtnRegListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//��ע�ᴰ��
			new Registe();
		}
	}

	/**
	 * ������ ��հ�ť
	 */
	public class BtnRemoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//����û����ı���
			txdName.setText("");
			//��������
			pwd.setText("");
			// ��յ�ѡ��ť��
			bg.clearSelection();

		}

	}
}
