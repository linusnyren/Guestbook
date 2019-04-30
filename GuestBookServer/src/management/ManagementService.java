package management;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ws.rs.core.Response;

import domain.Note;

@Local
public interface ManagementService {
	public List<Note> getAllNotes();
	public Note searchById(int id);
	public List<Note> searchByAuthor(String author);
	public List<Note> searchByDate(Date date);
	public void insertNewNote(Note note);
	public void delete(Note note);
	public void update(Note note);
}
