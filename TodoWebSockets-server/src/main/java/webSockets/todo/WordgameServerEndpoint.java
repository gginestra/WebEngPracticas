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

@ServerEndpoint(value = "/game")
public class WordgameServerEndpoint {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private final String SEPARADOR = "-"; 
	private ToDoList todo = new ToDoList(); 

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
		case "remove": 
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
        logger.severe("Connection has been closed");
    }
	


	private String listTodo(String message) {
	//	Gson gson = new Gson();
		return ""; 
	}

	private String removeTodo(String message) {
		return null;
	}

	private String addTodo(String message) {
		return null;
	}
}
