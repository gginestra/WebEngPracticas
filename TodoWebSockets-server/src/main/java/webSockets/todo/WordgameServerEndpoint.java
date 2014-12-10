package webSockets.todo;

import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint(value = "/todo")
public class WordgameServerEndpoint {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private final String SEPARADOR = "-"; 
	private ToDoList todolist = new ToDoList(); 

	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected ... " + session.getId());
	}

	@OnMessage
	public String onMessage(String message, Session session) {
		switch (message.substring(0, message.indexOf(SEPARADOR))) {
		case "quit":
			try {
				session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE,
						"Game ended"));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			break;
			
		case "add": 
			return addTodo(message); 
		case "delete": 
			return removeTodo(message);  
		case "list": 
			return listTodo(message);
		}
		return message;
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s closed because of %s",
				session.getId(), closeReason));
	}
	
	@OnError
    public void onError(Session session, Throwable t) {
        logger.severe("***Error: " + t.toString());
    }
	


	private String listTodo(String message) {
		Gson gson = new Gson();
		logger.info("To do listed");
		return gson.toJson(todolist); 
	}

	private String removeTodo(String message) {
		Gson gson = new Gson();
		String[] mensaje = message.split("-");		
		String by = mensaje[1]; 
		String keyword = mensaje[2]; 
		for (ToDoItem item : todolist.getToDoList()) {
			if(by.equals("task")){
				if(item.getTask().equals(keyword)){
					todolist.getToDoList().remove(item);
				}							
			}else if(by.equals("project")){
				if(item.getProject().equals(keyword)){
					todolist.getToDoList().remove(item);
				} 
			}else if(by.equals("context")){
				if(item.getContext().equals(keyword)){
					todolist.getToDoList().remove(item);
				} 
			}else if(by.equals("priority")){
				if(item.getPriority().equals(keyword)){
					todolist.getToDoList().remove(item);
				} 
			}
		}
		logger.info("To do deleted");
		return gson.toJson(todolist);
	}

	private String addTodo(String message) {
		Gson gson = new Gson();
		ToDoItem item = gson.fromJson(message.substring(message.indexOf("{"),message.length()), ToDoItem.class);
		todolist.addItem(item);
		logger.info("To do added");
		ToDoList todolist_temp = new ToDoList(); 
		todolist_temp.addItem(item);
		return gson.toJson(todolist_temp); 
	}
}
