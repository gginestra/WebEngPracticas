package webSockets.todo;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class ToDoList {

	private List<ToDoItem> toDoList = new CopyOnWriteArrayList<ToDoItem>();

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
