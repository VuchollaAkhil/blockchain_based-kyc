package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RespectBinding;
import javax.swing.JOptionPane;

import com.bean.Hash;
import com.database.Database;

/**
 * Servlet implementation class Debit
 */
@WebServlet("/Debit")
public class Debit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Debit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("fromm................");
		String accono=request.getParameter("accountno");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String mobile =request.getParameter("mobile");
		String aadhar=request.getParameter("adhar");
		int damount=Integer.parseInt(request.getParameter("damount"));
		HttpSession session=request.getSession();
		String cid=(String)session.getAttribute("cid");
		PrintWriter o=response.getWriter();
		System.out.println("from Debit");
		
		LocalDateTime currentDateTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = currentDateTime.format(formatter);
	
	    
	    double kyc=0.0;
	    if (damount > 10000) {
			/*
			 * o.println("<script type=\"text/javascript\">");
			 * o.println("alert(' Apply for Kyc for Large transactions!...');");
			 * o.println("window.location='chome.jsp';</script>");
			 */
            kyc = calculateKVAC(damount);
        }
			try {
				String userhash=Hash.hashString(cid, "SHA-256");
				
				String sql="select Amount from credit where Email='"+cid+"'";
				
				Connection con=Database.getConnection();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()) {
					int amount=rs.getInt(1);
					double totaldebit=damount-kyc;
					System.out.println("total kyc"+ ""+totaldebit);
					if(amount>=damount) {
					int balance=amount-damount;
					System.out.println(balance);
					sql="update credit set Amount='"+balance+"' where Email='"+cid+"'";
					ps=con.prepareStatement(sql);
					int j=ps.executeUpdate();
					if(j>0) {
						
						String sql1="insert into debit values(?,?,?,?,?,?,?,?,?,?,?)";
						 System.out.println(sql1);
						Connection con1=Database.getConnection();
					PreparedStatement	 ps1=con1.prepareStatement(sql1);
						ps1.setString(1, accono);
						ps1.setString(2, name);
						ps1.setString(3, email);
						ps1.setString(4, mobile);
						ps1.setString(5, aadhar);
						ps1.setInt(6, damount);
						ps1.setString(7, "Debited");
						ps1.setString(8, formattedDate);
						ps1.setDouble(9, kyc);
						ps1.setDouble(10, totaldebit);
						ps1.setString(11, userhash);
						System.out.println("from data");
						int i=ps1.executeUpdate();
						if(i>0) {
							System.out.println("-----------------------------------------------------");
							String sql2="insert into bank_statement values(?,?,?,?,?,?,?,?,?)";
							 System.out.println(sql1);
							Connection con2=Database.getConnection();
						PreparedStatement	 ps2=con2.prepareStatement(sql2);
							ps2.setString(1, accono);
							ps2.setString(2, name);
							ps2.setString(3, email);
							ps2.setString(4, mobile);
							ps2.setString(5, aadhar);
							ps2.setInt(6, damount);
							ps2.setString(7, formattedDate);
							ps2.setString(8, "Debited");
							ps2.setString(9, userhash);
							System.out.println("from data");
							ps2.executeUpdate();
						}
						if(kyc!=0.00) {
							System.out.println("-----------------------------------------------------");
							String sql2="insert into bank_statement values(?,?,?,?,?,?,?,?,?)";
							 System.out.println(sql1);
							Connection con2=Database.getConnection();
						PreparedStatement	 ps2=con2.prepareStatement(sql2);
							ps2.setString(1, accono);
							ps2.setString(2, name);
							ps2.setString(3, email);
							ps2.setString(4, mobile);
							ps2.setString(5, aadhar);
							ps2.setInt(6, damount);
							ps2.setString(7, formattedDate);
							ps2.setString(8, "Debited");
							ps2.setString(9, userhash);
							System.out.println("from data");
							ps2.executeUpdate();
						response.sendRedirect("prof.jsp?accountno=" + accono+"&name="+name+"&aadhar="+aadhar);
						}o.println("<script type=\"text/javascript\">");
						o.println("alert(' Amount debited Sucessfully!...');");
						o.println("window.location='chome.jsp';</script>");
					
					
					}else {
						o.println("<script type=\"text/javascript\">");
						o.println("alert(' Technical Issue!...');");
						o.println("window.location='chome.jsp';</script>");
						
					}
				}else {
					o.println("<script type=\"text/javascript\">");
					o.println("alert(' withdrawl cannot be proceed due to Insufficient Balance!...');");
					o.println("window.location='chome.jsp';</script>");
				}}
				
			
			}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	 private double calculateKVAC(double amount) {
	        // Calculate KVAC at a rate of 2%
		
	        return amount * 0.02;
	    }

}
