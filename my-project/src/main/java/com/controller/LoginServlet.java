package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.model.UserManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("psw");
		String url = "jdbc:mysql://localhost:3306/project";
		String username = "root";
		String pwd = "";
		Connection con = null;
		PreparedStatement pstmt=null;
		try
		{
			User u=new User();
			UserManager um=new UserManager();
			u.setEmail(email);
			u.setPassword(password);
			con=DriverManager.getConnection(url,username,pwd);
			pstmt= con.prepareStatement("select * from profie where username=? and pssword=?");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			if(pstmt.execute())
			{
				pw.print("success");
				RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
				rd.include(request, response);
			}
			else
			{
				pw.print("error");
			}
		}
		catch(Exception e)
		{
			pw.print("Error Occured");
		}
	}

}
