package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String back = null;
		if(request.getParameter("back")==null){
			String r = request.getHeader("referer");
			back = r.substring(r.lastIndexOf("/") + 1, r.lastIndexOf("l") + 1);
		}else{
			back=request.getParameter("back");
		}
		if (request.getSession().getAttribute("cart") == null) {
			response.sendRedirect("index.html");
		} else {
			product p = new product();
			int total = 0;
			List<String> cart = (List<String>) request.getSession().getAttribute("cart");
			Map<String, Integer> products = new HashMap<>();
			for (String product : cart) {
				int num = 1;
				if (products.containsKey(product)) {
					num = products.get(product);
					num++;
				}
				products.put(product, num);
			}
			request.getSession().setAttribute("products", products);
			out.print("");
			out.print("<!DOCTYPE html>");
			out.print("<html>");
			out.print("<head>");
			out.print("<meta charset='UTF-8'>");
			out.print("<title>Insert title here</title>");
			out.print("</head>");
			out.print("<body>");
			out.print("您購買了" + products.size() + "樣商品共" + cart.size() + "個");
			out.print("<table width=70% border=1 solid black>");
			out.print("<tr><td>示意圖</td><td>名稱</td><td>數量</td><td>小計</td></tr>");
			for (String product : products.keySet()) {
				out.print("<tr><td><img width=50 height=50 alt='" + product + "' src='test/" + product + ".jpg'</td>");
				out.print("<td>" + p.getn(product) + "</td>");
				out.print("<td>" + products.get(product) + "</td>");
				out.print("<td>" + (products.get(product) * p.getp(product)) + "</td></tr>");
				total += (products.get(product) * p.getp(product));
			}
			out.print("</table>");
			out.print("總金額:" + total);
			out.print("<a href='Finish'>//結帳//</a>");
			out.print("<a href='"+back+"'>繼續購物</a>");
			out.print("</table>");
			out.print("</body>");
			out.print("</html>");
		}
	}

}
