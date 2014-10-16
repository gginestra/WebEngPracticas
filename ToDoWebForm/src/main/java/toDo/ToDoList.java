package toDo;

import java.util.ArrayList;
import java.util.List;


public class ToDoList {

	private List<ToDoItem> ToDoList = new ArrayList<ToDoItem>();

	public List<ToDoItem> getToDoList() {
		return ToDoList;
	}

	public void setToDoList(List<ToDoItem> items) {
		this.ToDoList = items;
	}

	public void addItem(ToDoItem item) {
		ToDoList.add(item);
	}
}
