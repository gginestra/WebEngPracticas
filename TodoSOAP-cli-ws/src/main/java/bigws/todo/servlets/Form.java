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
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String URI = req.getRequestURI();
		out.println("<html><title>To Do</title><h1>RESULT To Do</h1><body>");
		
		if (URI.contains("add")){
			String task = req.getParameter("task");
			String pro = req.getParameter("project");
			String cntx = req.getParameter("context");
			String pri = req.getParameter("priority");
			todows.addTodo(task, pro, cntx, pri);
			
			
			out.println("Task: "+ task +
					"<br>Context: "+ cntx + 
					"<br>Project: "+ pro + 
					"<br>Priority: "+ pri +
					"<br><br>Ha sido añadido");
			
		}else if (URI.contains("remove")){
			String respuesta; 
			respuesta = todows.removeTodo(req.getParameter("searchBy"), req.getParameter("text"));
			out.println(respuesta); 
			
		}else if (URI.contains("list")){
			ToDoList toDoList = new ToDoList();
			toDoList = todows.listTodo(req.getParameter("searchBy"), req.getParameter("text"));
			
			for (ToDoItem item : toDoList.getToDoList()){
				out.println("Task: "+ item.getTask() +
						"<br>Context: "+ item.getContext() + 
						"<br>Project: "+ item.getProject() + 
						"<br>Priority: "+ item.getPriority() +
						"<br><br>");
			}
		}
		out.println("</body><a href=\"index.html\" target=\"_self\"> <input type=\"button\" name=\"boton\" value=\"HOME\"/></a></html>");
	}
	 

}
