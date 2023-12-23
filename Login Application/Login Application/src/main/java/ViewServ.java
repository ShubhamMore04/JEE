

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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Servlet implementation class ViewServ
 */
public class ViewServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	@Resource(lookup="java:/comp/env/mypool")
	private DataSource ds;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			con=ds.getConnection();
			PreparedStatement pst=con.prepareStatement("Select * from register");
			boolean result=pst.execute();
			PrintWriter pw=response.getWriter();
			response.setContentType("text/Html");
			if(result) 
			{
				ResultSet rs= pst.getResultSet();
				ResultSetMetaData rms=rs.getMetaData();
				int col=rms.getColumnCount();
				for(int i=1;i<=col;i++)
				{
					pw.println(rms.getColumnName(i));
					pw.println("  ");
				}
				pw.println("<br>");
				while(rs.next()) 
				{
				for(int i=1;i<col;i++) 
				{
					pw.println(rs.getObject(i));
				}
				pw.println("<br>");
				}
			}				
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
