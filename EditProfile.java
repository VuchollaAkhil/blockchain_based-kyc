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
 * Servlet implementation class EditProfile
 */
@WebServlet("/Edit")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
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
		String accono=request.getParameter("accountno");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String mobile =request.getParameter("mobile");
		String aadhar=request.getParameter("adhar");
		String address=request.getParameter("address");
		
		HttpSession session=request.getSession();
		String cid=(String)session.getAttribute("cid");
		
		LocalDateTime currentDateTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = currentDateTime.format(formatter);
	
	    PrintWriter o=response.getWriter();
	    
				try {
				Connection con=Database.getConnection();
				
				String sql="update customer set Address='"+address+"', mobile='"+mobile+"' where Email='"+cid+"'";
				PreparedStatement ps=con.prepareStatement(sql);
				
				/*
				 * ps.setString(1, address); ps.setString(2, mobile);
				 */
			int i=	ps.executeUpdate();
						 
						 
						 
						 
						 if(i>0) {
						 o.println("<script type=\"text/javascript\">");
						 o.println("alert(' Profile Updated Sucessfully!...');");
						 o.println("window.location='chome.jsp';</script>");
						 
					
					}else {
						o.println("<script type=\"text/javascript\">");
						o.println("alert(' Technical Issue!...');");
						o.println("window.location='editprofile.jsp';</script>");
						
					}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			
			
	}

}
