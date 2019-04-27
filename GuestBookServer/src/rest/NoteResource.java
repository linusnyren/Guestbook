package rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import domain.Note;
import management.ManagementService;

@Stateless
@Path("/GuestBook")
public class NoteResource {
	@Inject
	private ManagementService service;
	
	@GET
	@Produces("application/json")
	public List<Note> getAllNotes(){
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
	@POST
	@Consumes("application/json")
	public void insertNewNote(Note note) {
			service.insertNewNote(note);
		
	}
}
