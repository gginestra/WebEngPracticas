package bigws.todo.service;

import javax.xml.ws.Endpoint;

public class Server {
	
	//SOAP endpoint for the server to offer the web service
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8081/todo", new TodoWebService());
	}

}