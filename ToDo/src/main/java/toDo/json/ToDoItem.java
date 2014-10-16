package toDo.json;

public class ToDoItem {

	public String task;
	public String context;
	public String project;
	public String priority;
	
	public ToDoItem(String t, String c, String pro, String pri){
		task = t;
		context = c;
		project = pro;
		priority = pri; 
		
	}
	
	public ToDoItem() {
	}

	public String getTask() {
		return task;
	}
	
	public void setTask(String task) {
		this.task = task;
	}
	
	public String getContext() {
		return context;
	}
	
	public void setContext(String context) {
		this.context = context;
	}
	
	public String getProject() {
		return project;
	}
	
	public void setProject(String project) {
		this.project = project;
	}
	
	public String getPriority() {
		return priority;
	}
	
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
}
