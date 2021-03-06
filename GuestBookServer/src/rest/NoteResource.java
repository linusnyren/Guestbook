package rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TransactionRequiredException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import domain.Note;
import management.ManagementService;

@Stateless
@Path("/GuestBook") //Localhost/PATH
public class NoteResource {
	 			//Implementerar ett interface med en instans, typ samma som;
	@Inject		//Management service = new ManagementService();
	private ManagementService service;
	
	@GET	//HTML GET anrop svarar denna på.
	@Produces("application/json") //Här kan man även producera XML
	@PermitAll	//PermitAll gör tjänsten tillgänglig för alla och overridear JavaEE säkerhetsfunktioner.
	public List<Note> getAllNotes(){
		return service.getAllNotes();
	}
	@GET
	@Produces("application/json")
	@Path("noteid/{id}") //{id} är Path.Parameter.
	@PermitAll
	public Note getNoteById(@PathParam("id") int id) {
		return service.searchById(id);
	}
	@GET
	@Produces("application/json")
	@Path("author/{author}")
	@PermitAll
	public List<Note> getNotesByAuthor(@PathParam("author") String author){
		return service.searchByAuthor(author);
	}
	@POST
	@Consumes("application/json")
	@PermitAll
	public void insertNewNote(Note note) {
			service.insertNewNote(note);
		
	}
	@PUT
	@Consumes("application/json")
	@PermitAll
	public Response update(Note note) {
		try {
			service.update(note);
			return Response.status(200).build();
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return Response.status(404).build();
		}
	}
	@DELETE
	@Consumes("application/json")
	@PermitAll
	public Response delete(Note note) {
		try {
			service.delete(note);
			return Response.status(200).build();
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return Response.status(404).build();
		}
		catch(TransactionRequiredException e) {
			System.out.println(e.getMessage());
			return Response.status(404).build();
		}
	}
	
}
