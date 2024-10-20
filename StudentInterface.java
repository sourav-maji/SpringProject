package Pkg1;

import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.ArrayList;

public class StudentInterface implements ActionListener{
	
//	JTextField txt1, txt2 , txt3;
	JTextField txt[]=new JTextField[3];
	JButton btn1, btn2, btn3;
	Connect c1;
	JFrame f;
	public StudentInterface()
	{
	c1 =  new Connect();
	f=new JFrame();
	f.setLayout(null);
	Font font=new Font("verdana",1,15);
	
	f.setTitle("Student Management");
	f.setSize(800,700);
	
	// title label
	JLabel lbl1=new JLabel("Add Student");
	lbl1.setBounds(120, 10, 250, 50);
	lbl1.setFont(font);
	f.add(lbl1);
	
	// First label for Roll
	
	JLabel lbl2=new JLabel("Roll");
	lbl2.setBounds(30, 50, 120, 50);
	lbl2.setFont(font);
	f.add(lbl2);
	
	// For Roll text filed
	
	txt[0]=new JTextField();
	txt[0].setFont(new Font("arial",1,14));
	txt[0].setBounds(170,60,100,30);
	f.add(txt[0]);
	
	// Second Label for Name
	JLabel lbl3=new JLabel("Name");
	lbl3.setBounds(30, 70, 120, 50);
	lbl3.setFont(font);
	f.add(lbl3);
	
	// For Name text filed
	
	txt[1]=new JTextField();

	txt[1].setFont(new Font("arial",1,14));
	txt[1].setBounds(170,80,100,30);
	f.add(txt[1]);
	
	// For Marks field
	
	JLabel lbl4=new JLabel("Marks");
	lbl4.setBounds(30, 100, 120, 50);
	lbl4.setFont(font);
	f.add(lbl4);
	
	// For Marks text filed
	
	txt[2]=new JTextField();

	txt[2].setFont(new Font("arial",1,14));
	txt[2].setBounds(170,110,100,30);
	f.add(txt[2]);
	
	// Label for Add Button
	
	btn1=new JButton("ADD");
	btn1.setBounds(80,220,100,40);
	btn1.addActionListener(this);
	f.add(btn1);
	
	// Label for Clear Button
	
	btn2=new JButton("Clear");
	btn2.setBounds(200,220,100,40);
	btn2.addActionListener(this);
	f.add(btn2);
	
	// Show Table button
	
	btn3=new JButton("Table");
	btn3.setBounds(300,220,100,40);
	btn3.addActionListener(this);
	f.add(btn3);
	
	showTable();
	
	
	// Show table in JTable
	
//	String data[][]={ {"101","Amit","670000"},    
//            {"102","Jai","780000"},    
//            {"101","Sachin","700000"}};    
//	String column[]={"ID","NAME","SALARY"};         
//	JTable jt=new JTable(data,column);    
//	jt.setBounds(30,260,200,300);          
//	JScrollPane sp=new JScrollPane(jt);    
//	//jt.add(sp);   
//	f.add(sp);
//		
	// For making the frame visible . By default the frame is invisible
	
	f.setVisible(true);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	
	}
	
	public void clear() {
		txt[0].setText("");
		txt[1].setText("");
		txt[2].setText("");

	
	}
	
	// Show table function
	
	public void showTable() {
		
		ArrayList<Student> studenData =  getStudenData();
		
		
		
//		String data[][]={ {"101","Amit","670000"},    
//                {"102","Jai","780000"},    
//                {"103","Sachin","700000"},
//                {"104","Amitava","720000"},    
//                {"105","Jatin","710000"},    
//               };    
//		
		
		
		
		String column[]={"ROLL","NAME","MARKS"};
		DefaultTableModel dt = new DefaultTableModel(column ,0); 
		
		for (Student std : studenData) {
            dt.addRow(new Object[]{std.getRoll(), std.getName() , std.getMarks()});
        }


		JTable jt=new JTable(dt);  
		dt.fireTableDataChanged();
		jt.setBounds(90,320,200,400);
		f.add(jt);    
		
	}
	
	// function for performing the Convert operation
	
	public void actionPerformed(ActionEvent e)
	{
		String s=e.getActionCommand();
		String errMsg[] = {"Enter Roll No" , "Enter Name" , "Enter Marks" , "Enter Numberic Value"};
		if(s.equalsIgnoreCase("clear")) {
			clear();
		}
		else if(s.equalsIgnoreCase("table")) {
			showTable();
			System.out.println("Table is showed...");
		}
		else {
			String s1[] = new String[3];
			int roll =0;
			int marks =0;
			
			
			for(int i=0; i<3;i++) {
				s1[i]= txt[i].getText();
				if(s1[i].length() == 0) {
					JOptionPane.showMessageDialog(null, errMsg[i]);
					return;
				}
			}
			try {
				 roll = Integer.parseInt(txt[0].getText());
			}catch(Exception ec) {
				JOptionPane.showMessageDialog(null, errMsg[3]);
				txt[0].setText("");
				txt[0].requestFocus();
				
			}
			
			String name = txt[1].getText();
			try {
				 marks = Integer.parseInt(txt[2].getText());
			}catch(Exception ec) {
				JOptionPane.showMessageDialog(null, errMsg[3]);
				txt[2].setText("");
				txt[2].setFocusable(true);
				
				// to control back the focus
				txt[2].requestFocus();

			}
			boolean b=c1.saveRecord(roll, name, marks);
			if(b) {
				JOptionPane.showMessageDialog(null,"Data Saved !!!");
				showTable();
				clear();
			}else {
				JOptionPane.showMessageDialog(null,"Problem in Data Saved !!!");
				
			}
			
		}
		
	}
	
	public  ArrayList<Student> getStudenData(){
		
		
		ArrayList<Student> arr=c1.showRecord();
		for(int i=0;i<arr.size();i++)
		{
			Student stu=(Student)arr.get(i);
			System.out.println(stu.getRoll() + " : " + stu.getName() + " : " + stu.getMarks());
		}
		
		return arr;
		}
	
	
}
