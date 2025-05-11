package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.BankReg;
import com.database.Database;

/**
 * Servlet implementation class Bank_Login
 */
@WebServlet("/BankLogin")
public class Bank_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bank_Login() {
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
		
		String id=request.getParameter("bankid");
		String contact=request.getParameter("email");
		System.out.println("sucessfully logined");
		
		BankReg br=new BankReg();
		br.setBankid(id);
		br.setContact(contact);
		
		try {
			ResultSet rs=Database.bankLogin(br);
			System.out.println("inside database");
		
		PrintWriter o=response.getWriter();
		if(rs.next()) {
			o.println("<script type=\"text/javascript\">");
			o.println("alert(' Logined Successfully...');");
			o.println("window.location='bankhomehome.jsp';</script>");
			System.out.println("sucessfully logined1");
		}else {
			o.println("<script type=\"text/javascript\">");
			o.println("alert('Logined Failed...');");
			o.println("window.location='bank_login.jsp';</script>");
			System.out.println("sucessfully logined2");
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
