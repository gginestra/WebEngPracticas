package bigws.todo.domain;

public class ToDoItem {

	private String task;
	private String context;
	private String project;
	private String priority;
	
	public ToDoItem(String t, String c, String pro, String pri){
		this.task = t;
		this.context = c;
		this.project = pro;
		this.priority = pri; 
		
	}
	
	public ToDoItem() {
	}

	public String getTask() {
		return task;
	}
	
	public void setTask(String intask) {
		this.task = intask;
	}
	
	public String getContext() {
		return this.context;
	}
	
	public void setContext(String incontext) {
		this.context = incontext;
	}
	
	public String getProject() {
		return this.project;
	}
	
	public void setProject(String inproject) {
		this.project = inproject;
	}
	
	public String getPriority() {
		return this.priority;
	}
	
	public void setPriority(String inpriority) {
		this.priority = inpriority;
	}
	
}
