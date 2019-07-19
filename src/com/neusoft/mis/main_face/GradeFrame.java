package com.neusoft.mis.main_face;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.neusoft.mis.constant.Constant;
import com.neusoft.mis.entity.Grade;
import com.neusoft.mis.service.GradeService;
/**
 * �ɼ�¼�봰����
 * @author ����ɭ
 *
 */
public class GradeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1844401318765078346L;
	/**
	 * �����������
	 */
	// ���������
	private JPanel p, p1, p2, p3, p4, p5, p6;
	// �����˵������
	private JMenuBar menuBar;
	// �����˵����
	private JMenu menuCaoz, menuHelp;
	// �����˵���Ŀ
	private JMenuItem saveItem, exitItem, aboutItem;
	// ������ǩ
	private JLabel lbsname, lbsno, lbjava, lbshuju, lbcaozuo, lbzucheng, lbsuanfa, lbEglish, lbOrical, lbgailv;
	// �����ı���
	private JTextField txtsname, txtsno, txtjava, txtshuju, txtcaozuo, txtzucheng, txtsuanfa, txtEglish, txtOrical,
			txtgailv;
	// ������ť
	private JButton btnOk;
	private JButton btnRemove;
	//����ͼ��
	private ImageIcon img;

	// ����Grade����
	private Grade grade;
	// ����GradeService����
	private GradeService gradeService;
	//����������
	public GradeFrame() {
		// ��ʼ��
		gradeService = new GradeService();
		//��ʼ��ͼ��
		img = new ImageIcon("src\\img\\add2.png");

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
		saveItem.addActionListener(new BtnOkListener());

		exitItem = new JMenuItem("�˳�");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�˳���", "�˳�",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					//���ѡ���˳�����ô��������
					GradeFrame.this.setVisible(false);
				}
			}
		});

		aboutItem = new JMenuItem("����ϵͳ");
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "�汾1.0\n����:����ɭ��\n��Ȩ:Java��ĩ��Ŀ", "����", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// ���˵���Ŀ��ӵ��˵�
		menuCaoz.add(saveItem);
		menuCaoz.add(exitItem);
		menuHelp.add(aboutItem);

		// ��ʼ����ť
		btnOk = new JButton("����",new ImageIcon("src\\img\\ok.png"));
		btnRemove = new JButton("���",new ImageIcon("src\\img\\remove.png"));
		// ��Ӽ�����
		btnOk.addActionListener(new BtnOkListener());
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//����ı���
				removeTxt();
			}
		});

		// ��ʼ����ǩ
		lbsno = new JLabel("ѧ        ��:");
		lbsname = new JLabel("��        ��:");
		lbjava = new JLabel("j  a  v  a  :");
		lbshuju = new JLabel("��  ��  ��:");
		lbcaozuo = new JLabel("����ϵͳ:");
		lbzucheng = new JLabel("���ԭ��:");
		lbsuanfa = new JLabel("��        ��:");
		lbEglish = new JLabel("Ӣ        ��:");
		lbOrical = new JLabel("O r i c a l:");
		lbgailv = new JLabel("��  ��  ��:");

		// �ı����ʼ��
		txtsname = new JTextField(12);
		txtsno = new JTextField(12);
		txtjava = new JTextField(12);
		txtshuju = new JTextField(12);
		txtcaozuo = new JTextField(12);
		txtzucheng = new JTextField(12);
		txtsuanfa = new JTextField(12);
		txtEglish = new JTextField(12);
		txtOrical = new JTextField(12);
		txtgailv = new JTextField(12);

		// �����ʵ����������6��1�в���
		p = new JPanel(new GridLayout(6, 1));

		// ������ʼ���������������ӵ���������У�����ӵ������
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		p1.add(lbsno);
		p1.add(txtsno);
		p1.add(lbsname);
		p1.add(txtsname);
		p.add(p1);

		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		p2.add(lbjava);
		p2.add(txtjava);
		p2.add(lbshuju);
		p2.add(txtshuju);
		p.add(p2);

		p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		p3.add(lbcaozuo);
		p3.add(txtcaozuo);
		p3.add(lbzucheng);
		p3.add(txtzucheng);
		p.add(p3);

		p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		p4.add(lbsuanfa);
		p4.add(txtsuanfa);
		p4.add(lbEglish);
		p4.add(txtEglish);
		p.add(p4);

		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
		p5.add(lbOrical);
		p5.add(txtOrical);
		p5.add(lbgailv);
		p5.add(txtgailv);
		p.add(p5);

		p6 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		p6.add(btnOk);
		p6.add(btnRemove);
		p.add(p6);

		//���������ӵ�����
		this.add(p);
		// ���ô���ͼ��
		this.setIconImage(img.getImage());
		// ���˵������õ�������
		this.setJMenuBar(menuBar);
		//���ñ���
		this.setTitle(Constant.TEACHER_GRADE_TITLE);
		//���ô�С
		this.setSize(Constant.TEACHER_GRADE_WIDTH, Constant.TEACHER_GRADE_HEIGHT);
		//��ʼ��λ�þ���
		this.setLocationRelativeTo(null);
		//���ӻ�
		this.setVisible(true);
		//��С���ɸı�
		this.setResizable(false);
	}

	/**
	 * ����ɼ���ť������
	 */
	public class BtnOkListener implements ActionListener {

		private Component jPanel;

		@Override
		public void actionPerformed(ActionEvent e) {
			String strsno = txtsno.getText();
			String strsname = txtsname.getText();

			// ��ѡ�еĿγ���Ϣ��װ��Course����
			if (strsno == null || strsno.equals("")) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "ѧ�Ų���Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			if (!(strsno == null || strsno.equals("")) && (strsname == null || strsname.equals(""))) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "��������Ϊ�գ�", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			if (!(strsno == null || strsno.equals("")) && !(strsname == null || strsname.equals(""))
					&& ((txtjava.getText() == null || txtjava.getText().equals(""))
							|| (txtshuju.getText() == null || txtshuju.getText().equals(""))
							|| (txtcaozuo.getText() == null || txtcaozuo.getText().equals(""))
							|| (txtzucheng.getText() == null || txtzucheng.getText().equals(""))
							|| (txtsuanfa.getText() == null || txtsuanfa.getText().equals(""))
							|| (txtEglish.getText() == null || txtEglish.getText().equals(""))
							|| (txtOrical.getText() == null || txtOrical.getText().equals(""))
							|| (txtgailv.getText() == null || txtgailv.getText().equals("")))) {
				// ��ʾ��
				JOptionPane.showMessageDialog(jPanel, "������ɼ���", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
			//���������ȫ
			if (!(strsno == null || strsno.equals("")) && !(strsname == null || strsname.equals(""))
					&& !((txtjava.getText() == null || txtjava.getText().equals(""))
							|| (txtshuju.getText() == null || txtshuju.getText().equals(""))
							|| (txtcaozuo.getText() == null || txtcaozuo.getText().equals(""))
							|| (txtzucheng.getText() == null || txtzucheng.getText().equals(""))
							|| (txtsuanfa.getText() == null || txtsuanfa.getText().equals(""))
							|| (txtEglish.getText() == null || txtEglish.getText().equals(""))
							|| (txtOrical.getText() == null || txtOrical.getText().equals(""))
							|| (txtgailv.getText() == null || txtgailv.getText().equals("")))) {
				//���ı���������ַ���ת��������
				int intjava = Integer.parseInt(txtjava.getText());
				int intshuju = Integer.parseInt(txtshuju.getText());
				int intcaozuo = Integer.parseInt(txtcaozuo.getText());
				int intzucheng = Integer.parseInt(txtzucheng.getText());
				int intsuanfa = Integer.parseInt(txtsuanfa.getText());
				int intEnglish = Integer.parseInt(txtEglish.getText());
				int intOrical = Integer.parseInt(txtOrical.getText());
				int intgailv = Integer.parseInt(txtgailv.getText());
				//�����ųɼ���װ���ɼ�ʵ����
				grade = new Grade(strsno, strsname, intjava, intshuju, intcaozuo, intzucheng, intsuanfa, intEnglish,
						intOrical, intgailv);
				// ��������
				if (gradeService.saveGrade(grade)) {
					// ��ʾ��
					JOptionPane.showMessageDialog(jPanel, "����ɹ���", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
					//����ɹ�������ı���
					removeTxt();
				} else {
					JOptionPane.showMessageDialog(jPanel, "ѧ�Ų����ظ���", "��ܰ��ʾ", JOptionPane.WARNING_MESSAGE);
				}

			}

		}

	}

	/**
	 * ����ı���ķ���
	 */
	public void removeTxt() {
		txtcaozuo.setText("");
		txtEglish.setText("");
		txtgailv.setText("");
		txtjava.setText("");
		txtOrical.setText("");
		txtshuju.setText("");
		txtsname.setText("");
		txtsno.setText("");
		txtsuanfa.setText("");
		txtzucheng.setText("");
	}

	/*
	 * public static void main(String[] args) { new GradeFrame(); }
	 */
}
