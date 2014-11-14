package rest.todolist;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;


/**
 * A service that manipulates contacts in an address book.
 *
 */
@Path("/todo")
public class ToDoService {

	/**
	 * The (shared) address book object. 
	 */
	@Inject
	ToDoList todolist;

	/**
	 * A GET /contacts request should return the address book in JSON.
	 * @return a JSON representation of the address book.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ToDoList getToDoList() {
		return todolist;
	}

	/**
	 * A POST /contacts request should add a new entry to the address book.
	 * @param info the URI information of the request
	 * @param person the posted entity
	 * @return a JSON representation of the new entry that should be available at /contacts/person/{id}.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTask(@Context UriInfo info, ToDoItem item) {
		todolist.getToDoList().add(item);
		item.setId(todolist.nextId());
		item.setHref(info.getAbsolutePathBuilder().path("item/{id}").build(item.getId()));
		return Response.created(item.getHref()).entity(item).build();
	}

	/**
	 * A GET /contacts/person/{id} request should return a entry from the address book
	 * @param id the unique identifier of a person
	 * @return a JSON representation of the new entry or 404
	 */
	@GET
	@Path("/task/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTask(@PathParam("id") int id) {
		for (ToDoItem item : todolist.getToDoList()) {
			if (item.getId() == id) {
				return Response.ok(item).build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * A PUT /contacts/person/{id} should update a entry if exists
	 * @param info the URI information of the request
	 * @param person the posted entity
	 * @param id the unique identifier of a person
	 * @return a JSON representation of the new updated entry or 400 if the id is not a key
	 */
	@PUT
	@Path("/task/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTask(@Context UriInfo info,
			@PathParam("id") int id, ToDoItem item) {
		for (int i = 0; i < todolist.getToDoList().size(); i++) {
			if (todolist.getToDoList().get(i).getId() == id) {
				item.setId(id);
				item.setHref(info.getAbsolutePath());
				todolist.getToDoList().set(i, item);
				return Response.ok(item).build();
			}
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	/**
	 * A DELETE /contacts/person/{id} should delete a entry if exists
	 * @param id the unique identifier of a person
	 * @return 204 if the request is successful, 404 if the id is not a key
	 */
	@DELETE
	@Path("/task/{by}/{keyword}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTask(@PathParam("by") String by,@PathParam("keyword") String keyword ) {
		boolean borrado = false;
		for (ToDoItem item : todolist.getToDoList()) {
			if(by.equals("task")){
				if(item.getTask().equals(keyword)){
					todolist.getToDoList().remove(item);
					borrado = true;
				}							
			}else if(by.equals("project")){
				if(item.getProject().equals(keyword)){
					todolist.getToDoList().remove(item);
					borrado = true;
				} 
			}else if(by.equals("context")){
				if(item.getContext().equals(keyword)){
					todolist.getToDoList().remove(item);
					borrado = true;
				} 
			}else if(by.equals("priority")){
				if(item.getPriority().equals(keyword)){
					todolist.getToDoList().remove(item);
					borrado = true;
				} 
			}
			if( borrado ){
				return Response.status(Status.OK).build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
		
	}

}
