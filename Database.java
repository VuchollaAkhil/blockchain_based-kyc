package com.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Use;

import com.bean.BankReg;
import com.bean.User;

public class Database {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/KYC", "root", "root");
		return conn;
	}

	
	public static int Register(User u,String date, String userhash) throws ClassNotFoundException, SQLException {
		
		
		Connection con=Database.getConnection();
		String sql="insert into Customer values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, u.getUname());
		ps.setString(2, u.getEmail());
		ps.setString(3, u.getFname());
		ps.setString(4, u.getAadhar());
		ps.setString(5, u.getMobile());
		ps.setString(6, u.getGender());
		ps.setString(7, u.getDob());
		ps.setString(8, u.getMop());
		ps.setString(9, u.getAddress());
		ps.setString(10, u.getBank());
		ps.setString(11, "pending");
		ps.setString(12, "pending");
		ps.setString(13, date);
		ps.setString(14, userhash);
		
		int i=ps.executeUpdate();
		return i;
		
		
	}
	
	public static ResultSet bankLogin(BankReg br) throws ClassNotFoundException, SQLException {
		
		
		Connection con=Database.getConnection();
		String sql="select * from banktable where bankid=? and contact=?";
		System.out.println(sql);
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,br.getBankid());
		ps.setString(2, br.getContact());
		System.out.println("data taken");
		ResultSet rs=ps.executeQuery();
		return rs;
	}
	
	public static ResultSet clogin(User u) throws ClassNotFoundException, SQLException {
		
		
		String sql="select * from customer where Account_No=? and Email=? and ifscode!='pending'";
		Connection con=Database.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, u.getAccountno());
		ps.setString(2, u.getEmail());
		ResultSet rs=ps.executeQuery();
		return rs;
	}
	
	public static ArrayList<User> userViewOtherUsersEmails() throws Exception
	{
	Connection con=Database.getConnection();
		ArrayList<User> al = new ArrayList<User>();
		PreparedStatement ps = con.prepareStatement("select Account_No from customer where ifscode!='pending'");
		//ps.setString(1, Account_No);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			String acc=rs.getString(1);
			User b = new User();
			b.setAccountno(acc);
			al.add(b);
		}
		return al;
	}
}
