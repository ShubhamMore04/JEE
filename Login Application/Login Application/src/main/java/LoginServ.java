

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Servlet implementation class LoginServ
 */
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	@Resource(lookup="java:/comp/env/mypool")
	private DataSource ds;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			con=ds.getConnection();
			PreparedStatement pst=con.prepareStatement("Select login,password from register where login=? and password=?");
			pst.setString(1,request.getParameter("Login"));
			pst.setString(2,request.getParameter("Password"));
			boolean result=pst.execute();
			PrintWriter pw=response.getWriter();
			response.setContentType("text/Html");
			ResultSet rs=pst.getResultSet();
			if(rs.next()) 
			{
				pw.println("Your are Logged in Succesfully");
				pw.println("<br>");
				pw.println("<a href=\"Success.html\">Click here for the next page</a>");				
			}			
			else
			{
				pw.println("Incorrect deatails");
				pw.println("<br>");
				pw.println("<a href=\"fail.html\">please try again</a>");
			}
			} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
