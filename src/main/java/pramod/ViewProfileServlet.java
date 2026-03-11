package pramod;

import jakarta.servlet.ServletException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/viewProfiles")
public class ViewProfileServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.println("<h2>Profile List</h2>");
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Email</th>");
		out.println("<th>Phone</th>");
		out.println("<th>Age</th>");
		out.println("<th>Gender</th>");
		out.println("<th>Skills</th>");
		out.println("<th>City</th>");
		out.println("</tr>");
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/profiledb",
			"root",
			"Pramod17shetty");

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("select * from profile");

			while(rs.next()){

			out.println("<tr>");

			out.println("<td>"+rs.getString("name")+"</td>");
			out.println("<td>"+rs.getString("email")+"</td>");
			out.println("<td>"+rs.getString("phone")+"</td>");
			out.println("<td>"+rs.getInt("age")+"</td>");
			out.println("<td>"+rs.getString("gender")+"</td>");
			out.println("<td>"+rs.getString("skills")+"</td>");
			out.println("<td>"+rs.getString("city")+"</td>");

			out.println("</tr>");
			}

			out.println("</table>");

			}catch(Exception e){
			out.println(e);
			}

			
			
	}

}
