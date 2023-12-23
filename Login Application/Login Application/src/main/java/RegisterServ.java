

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.sql.DataSource;

/**
 * Servlet implementation class RegisterServ
 */
public class RegisterServ extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Connection con;
	@Resource(lookup="java:/comp/env/mypool")
	private DataSource ds;
	@Override
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			
			con=ds.getConnection();
			PreparedStatement pst=con.prepareStatement("Insert into register(Name,Address,Email,Login,Password)values(?,?,?,?,?)");
			pst.setString(1,request.getParameter("Name"));
			pst.setString(2,request.getParameter("Address"));
			pst.setString(3,request.getParameter("Email"));
			pst.setString(4,request.getParameter("Login"));
			pst.setString(5,request.getParameter("Password"));
			PrintWriter pw=response.getWriter();
			response.setContentType("text/Html");
			boolean result=pst.execute();		
			if(!result) 
			{
				int val=pst.getUpdateCount();
				pw.println(val+" Records Updated");
				
			}
			else 
			{
				pw.println("No Records Updated");
			}
			pw.println("<br>");
			pw.println("<a href=\"Home.html\">Home</a>");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

}
