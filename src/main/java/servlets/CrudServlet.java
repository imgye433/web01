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
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import db.MysqlConnection;
import models.Product;

/**
 * Servlet implementation class CrudServlet
 */
@WebServlet("/")
public class CrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MysqlConnection conn;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrudServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		conn = new MysqlConnection();
		conn.checkFactory();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		String action = request.getServletPath();

//		try {
		switch (action) {
//			case "/createproduct":
//				createProduct(request, response);
//				break;
		case "/deleteproduct":
			deleteProduct(request, response);
			break;
//			case "/editproduct":
//				editProduct(request, response);
//				break;
		default:
			listProducts(request, response);
			break;
		}
//		} catch (SQLException ex) {
//			throw new ServletException(ex);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

//		try {
		switch (action) {
		case "/createproduct":
			createProduct(request, response);
			break;
//			case "/deleteproduct":
//				deleteProduct(request, response);
//				break;
		case "/editproduct":
			editProduct(request, response);
			break;
		default:
			listProducts(request, response);
			break;
		}
//		} catch (SQLException ex) {
//			throw new ServletException(ex);
//		}
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		JSONArray productList = conn.listProducts();
		response.setHeader("Access-Control_Allow_Origin", "*");
		PrintWriter out = response.getWriter();
		out.print(productList);
		out.close();
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String pid = request.getParameter("pid");
		conn.deleteProduct(Integer.parseInt(pid));
		listProducts(request, response);
	}

	private void createProduct(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		StringBuilder sBuilder = new StringBuilder();
		try (BufferedReader reader = request.getReader()) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				sBuilder.append(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject(sBuilder.toString());
		conn.addProduct(obj.getString("name"), obj.getFloat("price"));
		listProducts(request, response);
	}

	private void editProduct(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		StringBuilder sBuilder = new StringBuilder();
		try (BufferedReader reader = request.getReader()) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				sBuilder.append(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject(sBuilder.toString());
		conn.updateProduct(obj.getInt("pid"), obj.getFloat("price"));
		listProducts(request, response);
	}
}
