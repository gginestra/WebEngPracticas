package bigws.todo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.google.gson.Gson;

import bigws.todo.domain.*;

@WebService
public class TodoWebService {
	public final static String DEFAULT_FILE_NAME = "ToDo.json";
	
	@WebMethod()
	public ToDoItem addTodo(String task, String pro, String cntx, String pri) {
		
		ToDoItem item = new ToDoItem(task,pro,cntx,pri);
		
		ToDoList toDoList = new ToDoList();
		Gson gson = new Gson();
		try {
			toDoList = gson.fromJson(new FileReader(DEFAULT_FILE_NAME), ToDoList.class);
		} catch (FileNotFoundException e) {
			System.out.println("**File Not Found");
			File file = new File(DEFAULT_FILE_NAME);
		}
		
		try {
			toDoList.addItem(item);
			FileWriter output = new FileWriter(DEFAULT_FILE_NAME);
			output.write(gson.toJson(toDoList));
			output.close();
		} catch (IOException e) {
			System.out.println("**Error written in file");
		}
		
		return item;
	}
	
	@WebMethod()
	public String removeTodo(String by, String keyword) {
		ToDoList toDoList = new ToDoList();
		ToDoList toDoList_final = new ToDoList();
		Gson gson = new Gson();
		try {
			toDoList = gson.fromJson(new FileReader(DEFAULT_FILE_NAME), ToDoList.class);
			File file = new File(DEFAULT_FILE_NAME);
			if(file.length()!=0){
				for (ToDoItem item : toDoList.getToDoList()) {
					
					if(by.equals("task")){
						if(!item.getTask().contains(keyword)){
							toDoList_final.addItem(item);
						}							
					}else if(by.equals("project")){
						if(!item.getProject().contains(keyword)){
							toDoList_final.addItem(item);
						} 
					}else if(by.equals("context")){
						if(!item.getContext().contains(keyword)){
							toDoList_final.addItem(item);
						} 
					}else if(by.equals("priority")){
						if(!item.getPriority().contains(keyword)){
							toDoList_final.addItem(item);
						} 
					}
					
				}
			}else{
				System.out.println("**Empty File");
			}
			
			FileWriter output = new FileWriter(DEFAULT_FILE_NAME);
			output.write(gson.toJson(toDoList_final));
			output.close();			
			
		} catch (FileNotFoundException e) {
			System.out.println("**File Not Found");
		} catch (IOException e) {
			System.out.println("**File Not Found");
		}
			
		return "Todo whith " + by + "was " + keyword + "has been deleted";
	}
	
	@WebMethod()
	public ToDoList listTodo(String by, String keyword) {
		ToDoList toDoList = new ToDoList();
		ToDoList toDoList_response = new ToDoList();
		Gson gson = new Gson();
		try {
			toDoList = gson.fromJson(new FileReader(DEFAULT_FILE_NAME), ToDoList.class);
			File file = new File(DEFAULT_FILE_NAME);
			if (file.length() != 0){
				for (ToDoItem item : toDoList.getToDoList()) {
					
					if(by.equals("task")){
						if(!item.getTask().contains(keyword)){
							toDoList_response.addItem(item);
						}							
					}else if(by.equals("project")){
						if(!item.getProject().contains(keyword)){
							toDoList_response.addItem(item);
						} 
					}else if(by.equals("context")){
						if(!item.getContext().contains(keyword)){
							toDoList_response.addItem(item);
						} 
					}else if(by.equals("priority")){
						if(!item.getPriority().contains(keyword)){
							toDoList_response.addItem(item);
						} 
					}
				}
				
			}else{
				System.out.println("**File Empty");
			}
			
		}catch (FileNotFoundException e) {
			System.out.println("**File Not Found");			
			
		}
		
		return toDoList_response;
	}
}
