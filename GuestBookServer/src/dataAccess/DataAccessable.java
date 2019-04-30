package dataAccess;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import domain.Note;

public interface DataAccessable {
	public Note findNoteById(int id);
	public List<Note> findByAuthor(String author);
	public List<Note> getAll();
	public List<Note> findByDate(Date date);
	public void register(Note note);
	public void delete(Note note);
	public void update(Note note);
}
