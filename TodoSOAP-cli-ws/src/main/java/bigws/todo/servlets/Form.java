package bigws.todo.servlets;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.xml.ws.WebServiceRef;

import bigws.todo.service.*;

import com.google.gson.Gson;

@SuppressWarnings("serial")
@WebServlet(name = "Form", urlPatterns = { "/add","/remove","/list" })

public class Form extends HttpServlet  {

	@WebServiceRef(wsdlLocation =  "WEB-INF/wsdl/ToDoWsCliService.wsdl")
	private TodoWebService service;
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		TodoWebServiceService todowss = new TodoWebServiceService();
		TodoWebService todows = todowss.getTodoWebServicePort();
		ToDoList toDoList = new ToDoList();
		
		String res = ""; 
		String URI = req.getRequestURI();
		
		if (URI.contains("add")){
			String task = req.getParameter("task");
			String pro = req.getParameter("project");
			String cntx = req.getParameter("context");
			String pri = req.getParameter("priority");
			todows.addTodo(task, pro, cntx, pri);
			
		}else if (URI.contains("remove")){
			
			
			
		}else if (URI.contains("list")){
			
		}
			
		
	//	ToDoList toDoList = new ToDoList();

		
//		toDoList = gson.fromJson(new FileReader(DEFAULT_FILE_NAME), ToDoList.class);
	//	ToDoItem item = new ToDoItem(task,cntx,pro,pri); 
//		toDoList.addItem(item);
		FileWriter output = new FileWriter(DEFAULT_FILE_NAME);
//		output.write(gson.toJson(toDoList));
		output.close();
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>To Do</title></head>"
				+ "<body>Task: "+ task +
				"<br>Context: "+ cntx + 
				"<br>Project: "+ pro + 
				"<br>Priority: "+ pri +
				"<br><br>Ha sido añadido"+
				"</body></html>");
	}
	

}
