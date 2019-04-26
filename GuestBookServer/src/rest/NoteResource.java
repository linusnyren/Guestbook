package rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import domain.Note;
import management.ManagementService;

@Stateless
@Path("/GuestBook")
@PermitAll
public class NoteResource {
	@Inject
	private ManagementService service;
	
	@GET
	@Produces("application/json")
	@PermitAll
	public List<Note> getAllNotes(){
		System.out.println("Getallnotes()");
		return service.getAllNotes();
	}
	@GET
	@Produces("application/json")
	@Path("noteid/{id}")
	public Note getNoteById(@PathParam("id") int id) {
		return service.searchById(id);
	}
	@GET
	@Produces("application/json")
	@Path("author/{author}")
	public List<Note> getNotesByAuthor(@PathParam("author") String author){
		return service.searchByAuthor(author);
	}
}
