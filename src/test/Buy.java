package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.nio.cs.ext.DoubleByte.Encoder_EUC_SIM;

@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Buy() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String back = null;
		String r = request.getHeader("referer");
		back = r.substring(r.lastIndexOf("/") + 1, r.lastIndexOf("l") + 1);
		String product = request.getParameter("product");
		List<String> cart;
		HttpSession session = request.getSession();
		if (product == null || product.trim().length() == 0) {
			response.sendRedirect("index.html");
		} else {
			if (session.getAttribute("cart") == null) {
				cart = new ArrayList<>();
				session.setAttribute("cart", cart);
			} else
				cart = (List<String>) session.getAttribute("cart");
			cart.add(product);
		}
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset='UTF-8'>");
		out.print("<title>Insert title here</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1 style='text-algin:center'>購買成功</h1>");
		out.print("<a href='"+back+"'>繼續購物<br></a>");
		out.print("<a href='Cart?back="+back+"'>購物車<br></a>");
		out.print("<a href='Finish'>結帳</a>");
		out.print("</body>");
		out.print("</html>");
	}

}
