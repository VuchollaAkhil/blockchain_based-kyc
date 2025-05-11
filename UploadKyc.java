package com.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.database.Database;

/**
 * Servlet implementation class UploadKyc
 */
@WebServlet("/UploadKyc")
@MultipartConfig(maxFileSize = 16177215)
public class UploadKyc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadKyc() {
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
		HttpSession session=request.getSession();
		String cid=(String)session.getAttribute("cid");
		String accountno=request.getParameter("account_no");
		String name=request.getParameter("name");
		String aadhar=request.getParameter("aadhar");
	//	Part filepart=request.getPart("image_proof");
		String idproof=request.getParameter("image_proof");
		/*
		 * InputStream input1=null; input1=filepart.getInputStream();
		 */
		LocalDateTime currentDateTime = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = currentDateTime.format(formatter);
	   
		try {
			PrintWriter o=response.getWriter();
			 if(aadhar.equals(idproof)) {
				 
			 
		String sql="insert into kyc1 values(?,?,?,?,?,?)";
		Connection con=Database.getConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, accountno);
		ps.setString(2, name);
		ps.setString(3, cid);
		ps.setString(4, aadhar);
		ps.setString(5, "aadhar Verified");
		ps.setString(6, formattedDate);
		
		int i=ps.executeUpdate();
		
		
			o.println("<script type=\"text/javascript\">");
			o.println("alert(' Kyc Updated Sucessfully!...');");
			o.println("window.location='chome.jsp';</script>");
		
		}else {
			o.println("<script type=\"text/javascript\">");
			o.println("alert(' authentication failed!...');");
			o.println("window.location='prof.jsp';</script>");
		
		}
		}catch (Exception e) {
			// TODO: handle exception
		}

	}

}
