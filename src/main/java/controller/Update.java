package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Dao.AllEmployeeDao;
import Dao.DataBaseUtil;
import entity.Employee;

/**
 * Servlet implementation class Update
 */
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ServletContext sc=request.getServletContext();
		DataBaseUtil baseUtil=(DataBaseUtil) sc.getAttribute("sqlEmployee");
		Connection connection=baseUtil.getConnector();
		AllEmployeeDao dao=new AllEmployeeDao();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		Employee emp = gson.fromJson(request.getReader(), Employee.class);
		if(dao.present(emp.getEmployeeId(), connection)) {
			dao.updateDetails(emp, connection);
			String s=gson.toJson(dao.displayJason1(emp.getEmployeeId(), connection));
//			dao.addToSubTables();
			response.setContentType("application/JSON");
			out.println("Details of id "+emp.getEmployeeId()+" are Updated");
			out.print(s);
		}
		else {
			out.println("ID "+emp.getEmployeeId()+" is not present in database the update");
		
		}
		
		baseUtil.dbClose();
		out.close();

	}
	
	
	
//	PrintWriter out=response.getWriter();
//	AllEmployeeDao dao=new AllEmployeeDao();
//	int id=Integer.parseInt(request.getParameter("id"));
//	String name=request.getParameter("name");
//	int salary=Integer.parseInt(request.getParameter("salary"));
//	String type=request.getParameter("type");
//	Employee e=new Employee(id, name, salary, type);



}





//package controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.employee.methods.AllEmployeeDao;
//import com.employee.methods.Employee;
//import com.google.gson.Gson;
//
///**
// * Servlet implementation class Update
// */
//public class Update extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//				
//		AllEmployeeDao dao=new AllEmployeeDao();								
//		Gson gson = new Gson();
//		PrintWriter out = response.getWriter();
//		Employee emp = gson.fromJson(request.getReader(), Employee.class);
//		if(dao.present(emp.getEmployeeId())) {
//			
//			String s=gson.toJson(dao.displayJason1(emp.getEmployeeId()));
//			dao.updateDetails(emp);
////			dao.addToSubTables();
//			response.setContentType("application/JSON");
//			out.println("Details of id "+emp.getEmployeeId()+" are Updated");
//			out.print(s);
//		}
//		else {
//			out.println("ID "+emp.getEmployeeId()+" is not present in database t update");
//		
//		}
//		
//		
//		out.close();
//
//	}
//	}
	
	