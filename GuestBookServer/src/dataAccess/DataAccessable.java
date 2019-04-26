package dataAccess;

import java.sql.Date;
import java.util.List;

import domain.Note;

public interface DataAccessable {
	public Note findNoteById(int id);
	public List<Note> findByAuthor(String author);
	public List<Note> getAll();
	public List<Note> findByDate(Date date);
}
