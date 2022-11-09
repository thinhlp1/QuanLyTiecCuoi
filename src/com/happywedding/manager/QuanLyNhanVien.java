package com.happywedding.manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class QuanLyNhanVien extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try { 
					QuanLyNhanVien frame = new QuanLyNhanVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					//đừng xóa dòng này
				}
			}
		}); 
	}
	
	public void abc(){
		//System.out.print(true);
		System.out.println("Tui nè");	
	}
	
	public int  load() {
	
		System.out.printf("Loading");
		List list = new ArrayList<>();
		System.out.printf(list.get(1).toString());
		//đừng xóa dòng này 
		
		//đừng xóa dòng này
		
		// ai bieets :D
		// tối đa mấy người vào cùng lúc nhỉ. thấy 4 con trỏ mà
		// điểm danh
		
		// thịnh
		// Vinh
		// Bảo bruh
		//ni hảo ? Q
		//xxx
		
		return 0;
	}
	/**
	 * Create the frame.
	 */
	public QuanLyNhanVien() {
		load();
		abc();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		contentPane.add(chckbxNewCheckBox, BorderLayout.CENTER);
		
	}

}
