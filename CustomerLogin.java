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
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.database.Database;

/**
 * Servlet implementation class CustomerLogin
 */
@WebServlet("/CustomerLogin")
public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLogin() {
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
		
		String cid=request.getParameter("cid");
		String email=request.getParameter("email");
		
		User u=new User();
		u.setAccountno(cid);
		u.setEmail(email);
		HttpSession session=request.getSession();
		session.setAttribute("cid", email);
		
		try {
	ResultSet rs=Database.clogin(u);
	PrintWriter o=response.getWriter();
	if(rs.next()) {
		o.println("<script type=\"text/javascript\">");
		o.println("alert(' Logined Successfully...');");
		o.println("window.location='chome.jsp';</script>");
	}
	else {
		o.println("<script type=\"text/javascript\">");
		o.println("alert(' Logined failed...');");
		o.println("window.location='CustomerLogin.jsp';</script>");
	}
	
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
