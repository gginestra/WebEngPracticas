package webSockets.todo;

import java.util.ArrayList;
import java.util.List;


public class ToDoList {

	private List<ToDoItem> toDoList = new ArrayList<ToDoItem>();

	public List<ToDoItem> getToDoList() {
		return toDoList;
	}

	public void setToDoList(List<ToDoItem> items) {
		this.toDoList = items;
	}

	public void addItem(ToDoItem item) {
		toDoList.add(item);
	}
}
