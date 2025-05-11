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

import com.bean.Hash;
import com.database.Database;

/**
 * Servlet implementation class Transaction
 */
@WebServlet("/Transaction")
public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction() {
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
		//doGet(request, response);
		
		
		String accono=request.getParameter("accountno");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String mobile =request.getParameter("mobile");
		String aadhar=request.getParameter("adhar");
		String toaccount=request.getParameter("toaccount");
		int tamount=Integer.parseInt(request.getParameter("tamount"));
		HttpSession session=request.getSession();
		String cid=(String)session.getAttribute("cid");
		
		LocalDateTime currentDateTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = currentDateTime.format(formatter);
	
	    PrintWriter o=response.getWriter();
	    double kyc=0.0;
	    if (tamount > 10000) {
			
            kyc = calculateKVAC(tamount);
        }
			try {
				String userhash=Hash.hashString(cid, "SHA-256");
				
				String sql="select Amount from credit where Email='"+cid+"'";
				
				Connection con=Database.getConnection();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()) {
					int amount=rs.getInt(1);
					double totaldebit=tamount-kyc;
					System.out.println("total kyc"+ ""+totaldebit);
					if(amount>=tamount) {
					int balance=amount-tamount;
					System.out.println(balance);
					sql="update credit set Amount='"+balance+"' where Email='"+cid+"'";
					ps=con.prepareStatement(sql);
					int j=ps.executeUpdate();
					if(j>0) {
						
						String sql1="insert into transaction values(?,?,?,?,?,?,?,?,?,?,?)";
						 System.out.println(sql1);
						Connection con1=Database.getConnection();
					PreparedStatement	 ps1=con1.prepareStatement(sql1);
						ps1.setString(1, accono);
						ps1.setString(2, name);
						ps1.setString(3, email);
						ps1.setString(4, mobile);
						ps1.setString(5, aadhar);
						ps1.setString(6, toaccount);
						ps1.setInt(7, tamount);
						ps1.setString(8, formattedDate);
						ps1.setDouble(9, kyc);
						ps1.setDouble(10, totaldebit);
						ps1.setString(11, userhash);
						System.out.println("from data");
						int i=ps1.executeUpdate();
						if(i>0) {
							System.out.println("-----------------------------------------------------");
							String sql5="insert into bank_statement values(?,?,?,?,?,?,?,?,?)";
							 System.out.println(sql1);
							Connection con5=Database.getConnection();
						PreparedStatement	 ps5=con5.prepareStatement(sql5);
							ps5.setString(1, accono);
							ps5.setString(2, name);
							ps5.setString(3, email);
							ps5.setString(4, mobile);
							ps5.setString(5, aadhar);
							ps5.setInt(6, tamount);
							ps5.setString(7, formattedDate);
							ps5.setString(8, "Debited");
							ps5.setString(9, userhash);
							System.out.println("from data");
							ps5.executeUpdate();
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
							ps2.setInt(6, tamount);
							ps2.setString(7, formattedDate);
							ps2.setString(8, "Debited");
							ps2.setString(9, userhash);
							System.out.println("from data");
							ps2.executeUpdate();
						response.sendRedirect("prof.jsp?accountno=" + accono+"&name="+name+"&aadhar="+aadhar);
						}
						
						
						
					 	String sql4="select Amount from credit where Account_No='"+toaccount+ "'";
					 	System.out.println("tacvount number"+""+toaccount);
						con=Database.getConnection();
						System.out.println("from connn-------------------");
					PreparedStatement	ps3=con.prepareStatement(sql4);
					ResultSet rs3=	ps3.executeQuery();					
					
					
					
						while(rs3.next())
						 { 
							System.out.println("from resultset---------------------------");
							int totalamountofreceiver=rs3.getInt(1);
							 int balance1=(int) (totalamountofreceiver+totaldebit);
							  System.out.println(totalamountofreceiver);
							  System.out.println(tamount);
							System.out.println("total amount of to account"+""+totalamountofreceiver);
							Connection con2=Database.getConnection();
						 
						  System.out.println("total balance"+" ---"+balance1);
						 String sql2="update credit set Amount='"+balance1+"' where Account_No='"+toaccount+ "'";
						  
						 PreparedStatement ps2=con2.prepareStatement(sql2); 
						 int k=ps2.executeUpdate();
						 
						 
						 
						 
						 
						 
						 
						 
						 
						 
						 o.println("<script type=\"text/javascript\">");
						 o.println("alert(' Transaction Sucessfully!...');");
						 o.println("window.location='chome.jsp';</script>");
						 }
					
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
			
			e.printStackTrace();
		}
	}
	 private double calculateKVAC(double amount) {
	        // Calculate KVAC at a rate of 2%
		
	        return amount * 0.02;
	    }


}
