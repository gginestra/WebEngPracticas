package toDo.json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


import com.google.gson.Gson;


public class AddItem {
	
	public final static String DEFAULT_FILE_NAME = "ToDo.json";

	// This function fills in a Person message based on user input.
	static ToDoItem PromptForToDo(BufferedReader stdin, PrintStream stdout)
			throws IOException {
		ToDoItem item = new ToDoItem();

		stdout.print("Enter ToDo task: ");
		item.setTask(stdin.readLine());

		stdout.print("Enter ToDo context: ");
		item.setContext(stdin.readLine());

		stdout.print("Enter ToDo project: ");
		item.setProject(stdin.readLine());
		
		stdout.print("Enter ToDo priority: ");
		item.setPriority(stdin.readLine());


		return item;
	}

	// Main function: Reads the entire address book from a file,
	// adds one person based on user input, then writes it back out to the same
	// file.
	public static void main(String[] args) throws Exception {
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename=args[0];
		}

		ToDoList toDoList = new ToDoList();
		Gson gson = new Gson();

		// Read the existing address book.
		try {
			toDoList = gson.fromJson(new FileReader(filename), ToDoList.class);
		} catch (FileNotFoundException e) {
			System.out.println(filename
					+ ": File not found.  Creating a new file.");
		}

		// Add an address.
		toDoList.addItem(PromptForToDo(new BufferedReader(
				new InputStreamReader(System.in)), System.out));

		// Write the new address book back to disk.
		FileWriter output = new FileWriter(filename);
		output.write(gson.toJson(toDoList));
		output.close();
	}
}
