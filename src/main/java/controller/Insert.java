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

public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ServletContext sc=request.getServletContext();
		DataBaseUtil baseUtil=(DataBaseUtil) sc.getAttribute("sqlEmployee");
		Connection connection=baseUtil.getConnector();


		AllEmployeeDao dao=new AllEmployeeDao();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		Employee emp = gson.fromJson(request.getReader(), Employee.class);

		if(!(dao.present(emp.getEmployeeId(), connection))) {
			dao.createDetails(emp, connection);
			String s = gson.toJson(dao.displayJason1(emp.getEmployeeId(), connection));
//			dao.addToSubTables();
			response.setContentType("application/json");
			out.println("Details of ID "+emp.getEmployeeId()+" are created");
			out.print(s);

		}else {
			out.println("id "+emp.getEmployeeId()+" already exists");
			out.println("Enter new ID");

		}
//		baseUtil.dbClose();
		out.close();
		ServletContext scd=request.getServletContext();
		DataBaseUtil baseUtil1= (DataBaseUtil) scd.getAttribute("closeConnection");
		baseUtil1.dbClose();

				
	}
}
