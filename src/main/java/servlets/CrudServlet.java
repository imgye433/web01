package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import models.Product;

/**
 * Servlet implementation class CrudServlet
 */
public class CrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrudServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String pid=request.getParameter("pid");
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "database=Northwind;" + "user=root;"
				+ "password=abcd1234;";
		JSONArray array = new JSONArray();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(connectionUrl);
		    try (PreparedStatement ps = conn.prepareStatement("SELECT ProductID, ProductName, CategoryID, UnitPrice FROM dbo.Products WHERE ProductID=?")) {
		    	ps.setObject(1, pid);
		    	try (ResultSet rs = ps.executeQuery()) {
		            while (rs.next()) {
		                int id = rs.getInt(1); 
		                String name = rs.getString(2);
		                int catId = rs.getInt(3);
		                BigDecimal price = rs.getBigDecimal(4);
		                JSONObject obj = new Product(id, name, catId, price).toJSONObject();
		                array.put(obj);
		            }
		        }
		    }
		    conn.close();
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setHeader("Access-Control_Allow_Origin", "*");
		PrintWriter out = response.getWriter();
		out.print(array);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		StringBuilder sBuilder = new StringBuilder();
//		try (BufferedReader reader = request.getReader()) {
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				sBuilder.append(line);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		String pid=sBuilder.toString();
//		String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "database=Northwind;" + "user=root;"
//				+ "password=abcd1234;";
//		JSONArray array = new JSONArray();
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection conn = DriverManager.getConnection(connectionUrl);
//		    try (PreparedStatement ps = conn.prepareStatement("SELECT ProductID, ProductName, CategoryID, UnitPrice FROM dbo.Products WHERE ProductID=?")) {
//		    	ps.setObject(1, pid);
//		    	try (ResultSet rs = ps.executeQuery()) {
//		            while (rs.next()) {
//		                int id = rs.getInt(1); 
//		                String name = rs.getString(2);
//		                int catId = rs.getInt(3);
//		                BigDecimal price = rs.getBigDecimal(4);
//		                JSONObject obj = new Product(id, name, catId, price).toJSONObject();
//		                array.put(obj);
//		            }
//		        }
//		    }
//		    conn.close();
//		}
//		// Handle any errors that may have occurred.
//		catch (SQLException e) {
//			e.printStackTrace();
//		}catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		response.setHeader("Access-Control_Allow_Origin", "*");
//		PrintWriter out = response.getWriter();
//		out.print(array);
//		out.close();
	}

}
