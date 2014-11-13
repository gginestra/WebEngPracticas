package rest.todolist;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;


public class ApplicationConfig extends ResourceConfig {

	/**
     * Default constructor
     */
    public ApplicationConfig() {
    	this(new ToDoList());
    }


    /**
     * Main constructor
     * @param addressBook a provided address book
     */
    public ApplicationConfig(final ToDoList toDoList) {
    	register(ToDoService.class);
    	register(CrossDomainFilter.class);
    	register(MOXyJsonProvider.class);
    	register(new AbstractBinder() {

			@Override
			protected void configure() {
				bind(toDoList).to(ToDoList.class);
			}});
	}
}
