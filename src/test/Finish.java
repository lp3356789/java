package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Finish")
public class Finish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Finish() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<String> cart=(List<String>)request.getSession().getAttribute("cart");
		Map<String, Integer> products = (Map<String, Integer>) request.getSession().getAttribute("products");
		if (products == null) {
			response.sendRedirect("index.html");
		} else {
			Connection conn = null;
			PreparedStatement pstmt;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/mydb";
				String user = "root";
				String pass = "root123";
				conn = DriverManager.getConnection(url, user, pass);
				pstmt = conn.prepareStatement("insert into shop set pro_no=?,pro_num=?");
				for (String product : products.keySet()) {
					pstmt.setString(1, product);
					pstmt.setInt(2, products.get(product));
					pstmt.executeUpdate();
				}
				cart.clear();
				products.clear();
				out.print("結帳成功");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
