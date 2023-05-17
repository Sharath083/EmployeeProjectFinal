package Dao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class List
 *
 */
public class Listner implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext sc=sce.getServletContext();
    	DataBaseUtil baseUtil=new DataBaseUtil();
    	sc.setAttribute("sqlEmployee", baseUtil);
    	     		
    }
	
}
