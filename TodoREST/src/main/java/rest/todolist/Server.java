package rest.todolist;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

public class Server {
	private static final Logger LOGGER = Grizzly.logger(Server.class);
	
	public static void main(String[] args) {
		LOGGER.setLevel(Level.FINER);
		ToDoList todolist = new ToDoList();
		
		// Some dummy data
		ToDoItem task1 = new ToDoItem();
		ToDoItem task2 = new ToDoItem();
		task1.setId(1);
		task1.setContext("cntx");
		task1.setPriority("baja");
		task1.setProject("proyecto1");
		task1.setTask("task 1");
		
		task2.setId(2);
		task2.setContext("cntx");
		task2.setPriority("media");
		task2.setProject("proyecto1");
		task2.setTask("task 2");
		
		todolist.getToDoList().add(task1);
		todolist.getToDoList().add(task2);
		
		URI uri = UriBuilder.fromUri("http://localhost/").port(8080).build();
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri,
				new ApplicationConfig(todolist));
		try {
			server.start();
			LOGGER.info("Press 's' to shutdown now the server...");
			while(true){
				int c = System.in.read();
				if (c == 's')
					break;
			}
		} catch (IOException ioe) {
			LOGGER.log(Level.SEVERE, ioe.toString(), ioe);
		} finally {
			server.stop();
			LOGGER.info("Server stopped");
		}
	}
}
