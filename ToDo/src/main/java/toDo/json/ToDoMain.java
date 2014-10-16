package toDo.json;

import java.io.FileReader;

import com.google.gson.Gson;


public class ToDoMain {
	public final static String DEFAULT_FILE_NAME = "ToDo.json";
	
	
	
	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename = args[0];
		}
		ToDoList toDoList = new ToDoList();
		

		// Read the existing To do list.
		toDoList = gson.fromJson(new FileReader(
				filename), ToDoList.class);
		
//		Add(toDoList);
		Print(toDoList);
	}



	private static void Add(ToDoList toDoList) {
		ToDoItem item1 = new ToDoItem("FOOO","cntx","proyecto 2","Media");
		toDoList.addItem(item1);
		
	}



	private static void Print(ToDoList toDoList) {
		
		for (ToDoItem item : toDoList.getToDoList()) {
			System.out.println("Item task: " + item.getTask());
			System.out.println("Item context: " + item.getContext());
			System.out.println("Item project: " + item.getProject());
			System.out.println("Item priority: "+ item.getPriority());
			System.out.println("===========================");
			
		}
	}
	
}
