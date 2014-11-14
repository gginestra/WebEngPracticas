package bigws.todo.servlets;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/search" })

public class FormularioSearch extends HttpServlet  {
	public final static String DEFAULT_FILE_NAME = "ToDo.json";
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String searchBy = req.getParameter("searchBy");
		String texto = req.getParameter("text");
		
		//out.println("<html><head><title>To Do</title></head>"
		//		+ "<body><h1>Search by:  " + searchBy + "</h1> "+ texto +"</body></html>");
		String response=""; 
		
		Gson gson = new Gson();
//		ToDoList toDoList = gson.fromJson(new FileReader(
//				DEFAULT_FILE_NAME), ToDoList.class);
		
/*		for (ToDoItem item : toDoList.getToDoList()) {
			
			if(searchBy==null){
				response+=print(item)+ "<br><br>";
			}else if(searchBy.equals("task")){
				if(item.getTask().equals(texto)){
					response+=print(item) + "<br><br>";
				}	
				
			}else if(searchBy.equals("project")){
				if(item.project.equals(texto)){
					response+=print(item)+ "<br><br>";					
				} 
			}else if(searchBy.equals("context")){
				if(item.context.equals(texto)){
					response+=print(item)+ "<br><br>";					
				} 
			}else if(searchBy.equals("priority")){
				if(item.priority.equals(texto)){
					response+=print(item)+ "<br><br>";					
				} 
			}
			
		}*/
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>To Do</title></head>"
				+ "<body>" + response+"</body></html>");
		
		
	}
	
/*	private String  print(ToDoItem item){
		
		return  "Task: "+item.task +
				"<br>Context: "+ item.context+ 
				"<br>Project: "+ item.project+ 
				"<br>Priority: "+ item.priority;
		
	}*/

}