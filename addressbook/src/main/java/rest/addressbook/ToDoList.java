package rest.addressbook;

import java.util.ArrayList;
import java.util.List;


public class ToDoList {

	private int nextId = 1;
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

	public int getNextId() {
		return nextId;
	}

	public void setNextId(int nextId) {
		this.nextId = nextId;
	}
	
	public int nextId() {
		int oldValue = nextId;
		nextId++;
		return oldValue;
	}
}
