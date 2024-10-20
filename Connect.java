package Pkg1;
import java.sql.*;
import java.util.ArrayList;  


class Connect {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public Connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentMgmt","root","12345678");  
			System.out.println("Conncted...");
		}catch(Exception e) {
			System.out.println(e);
		}

	}
	public boolean saveRecord(int roll,String name,int marks)
	{
		boolean b=false;
		try {
			String sql="insert into studentData(roll,name,marks)values(?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setInt(1, roll);
			ps.setString(2, name);
			ps.setInt(3,marks);
			int n=ps.executeUpdate();
			if(n==1)
				b=true;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}
	
	public ArrayList<Student> showRecord() {
		ArrayList<Student> arr=new ArrayList<Student>();
		try {
			String sql = "select * from studentData";
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				Student s1=new Student();
				s1.setRoll(rs.getInt(1));
				s1.setName(rs.getString(2));
				s1.setMarks(rs.getInt(3));
				arr.add(s1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return arr;
	}
}
