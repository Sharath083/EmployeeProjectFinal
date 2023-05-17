package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import Dao.AllEmployeeDao;
import Dao.DataBaseUtil;
import entity.Employee;

public class DisplayAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc=request.getServletContext();
		DataBaseUtil baseUtil=(DataBaseUtil) sc.getAttribute("sqlEmployee");
		Connection connection=baseUtil.getConnector();
		AllEmployeeDao dao=new AllEmployeeDao();

		Gson gson = new Gson();
		
		Hashtable<Integer,Employee> e = dao.displayJasonHash(connection);
		String jasonFormat = gson.toJson(e);

		
		
//		List<Employee> eJason=dao.displayJason();
//		String jasonFormat=gson.toJson(eJason);
		
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/JSON");
		out.println(jasonFormat);
		baseUtil.dbClose();
		out.close();
		
	}	
	

}
 





//package controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Hashtable;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.employee.methods.AllEmployeeDao;
//import com.google.gson.Gson;
//
//public class DisplayAll extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		AllEmployeeDao dao = new AllEmployeeDao();
//		Gson gson = new Gson();
//		
//		Hashtable<Integer, com.employee.methods.Employee> e = dao.displayJasonHash();
//		String jasonFormat = gson.toJson(e);
//
//		
//		
////		List<Employee> eJason=dao.displayJason();
////		String jasonFormat=gson.toJson(eJason);
//		
//		
//		PrintWriter out = response.getWriter();
//		response.setContentType("application/JSON");
//		out.println(jasonFormat);
//		out.close();
//		
//	}	
//	
//
//}
//   