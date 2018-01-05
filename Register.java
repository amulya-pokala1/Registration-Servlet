import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();
		String UserName=request.getParameter("UserName");
		out.print(UserName);
		try {
			Connection connection=DBConnection.getInstance();
			String query = "select id from registration.registration where name=?";
			PreparedStatement preparedStmt=(connection).prepareStatement(query);
			preparedStmt.setString (1, UserName);
			ResultSet rs=preparedStmt.executeQuery();
			Integer id=new Integer(0);
			while(rs.next())
				id=rs.getInt("id");
			out.println(""+ id);
			request.setAttribute("id", id);
			RequestDispatcher rd = request.getRequestDispatcher("/PassData");
			rd.forward(request,response);
			      
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();
		
		String UserName=request.getParameter("UserName");
		String Password=request.getParameter("Password");
		String confirmPassword=request.getParameter("ConfirmPassword");
		
		if(Password.equals(confirmPassword))
		{
			
			try {
				Connection connection=DBConnection.getInstance();
				String query = " insert into registration.registration (name, password)"
				        + " values (?, ?)";   
				PreparedStatement preparedStmt=(connection).prepareStatement(query);
				preparedStmt.setString (1, UserName);
				preparedStmt.setString (2, Password);
				preparedStmt.execute();
				
				out.println("success");
				connection.close();
				      
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
