package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Hash;
import com.bean.User;
import com.database.Database;

/**
 * Servlet implementation class URegister
 */
@WebServlet("/URegister")
public class URegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public URegister() {
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
		String uname=request.getParameter("name");
		String email=request.getParameter("email");
		String fname=request.getParameter("fname");
		String adhar=request.getParameter("ano");
		String mobile=request.getParameter("mobile");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String marraige=request.getParameter("mop");
		String address=request.getParameter("address");
		String bank=request.getParameter("bank");
		
		
		
		
		LocalDateTime currentDateTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = currentDateTime.format(formatter);
	    System.out.println("Formatted Date and Time: " + formattedDate);
	    
	    User u=new User();
	    
	    u.setUname(uname);
	    u.setEmail(email);
	    u.setFname(fname);
	    u.setAadhar(adhar);
	    u.setMobile(mobile);
	    u.setGender(gender);
	    u.setDob(dob);
	    u.setMop(marraige);
	    u.setAddress(address);
	    u.setBank(bank);
	    PrintWriter o=response.getWriter();
	    try {
	    	String hashvalue=Hash.hashString(email, "SHA-256");
			int i=Database.Register(u, formattedDate,hashvalue);
			if(i>0) {
				o.println("<script type=\"text/javascript\">");
				o.println("alert(' Logined Successfully...');");
				o.println("window.location='customer_login.jsp';</script>");
			}
			else {
				o.println("<script type=\"text/javascript\">");
				o.println("alert(' Registeration failed...');");
				o.println("window.location='Register.jsp';</script>");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
