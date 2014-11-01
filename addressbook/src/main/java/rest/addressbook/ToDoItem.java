package rest.addressbook;

import java.net.URI;

public class ToDoItem {

	private String task;
	private String context;
	private String project;
	private String priority;
	private URI href;
	
	public URI getHref() {
		return href;
	}

	public void setHref(URI href) {
		this.href = href;
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
