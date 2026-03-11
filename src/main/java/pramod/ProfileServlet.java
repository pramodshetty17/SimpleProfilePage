package pramod;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;


@WebServlet("/register")
public class ProfileServlet extends HttpServlet {
	String sql="insert into profile(name,email,phone,age,gender,skills,city) values(?,?,?,?,?,?,?)";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String age=request.getParameter("age");
		String gender=request.getParameter("gender");
		
		String city=request.getParameter("city");
		
		String[] skills = request.getParameterValues("skills");

		String skillData = "";

		if (skills != null) {
			for (String s : skills) {
				skillData += s + " ";
			}
		}
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/profiledb",
					"root",
					"Pramod17shetty");
            PreparedStatement ps=con.prepareStatement(sql);
            
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, age);
            ps.setString(5, gender);
            ps.setString(6, skillData);
            ps.setString(7, city);
            
            ps.executeUpdate();
            
            response.sendRedirect("index.html?msg=success");

//			response.getWriter().println("Profile Saved Successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}