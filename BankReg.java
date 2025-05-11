package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.RandomKeys;
import com.database.Database;

/**
 * Servlet implementation class BankReg
 */
@WebServlet("/BankReg")
public class BankReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankReg() {
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
		
		
		String bname=request.getParameter("name");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		
		String bankregid=RandomKeys.RandGeneratedStr1(7);
		
		try {
		Connection con=Database.getConnection();
		String sql="insert into banktable values(?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setString(1,bname+""+bankregid);
		ps.setString(2, bname);
		ps.setString(3, email);
		ps.setString(4, mobile);
		ps.setString(5, address);
		ps.setString(6, "Approved");
		PrintWriter o=response.getWriter();
		int i=ps.executeUpdate();
		if(i>0) {
			o.println("<script type=\"text/javascript\">");
			o.println("alert('Your Request Approved Successfully...');");
			o.println("window.location='adminhome.jsp';</script>");
		}else {
			o.println("<script type=\"text/javascript\">");
			o.println("alert('your approvement has been failed...');");
			o.println("window.location='bankReg.jsp';</script>");
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
