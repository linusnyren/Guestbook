package management;

import java.util.List;

import javax.ejb.Local;

import domain.Note;

@Local
public interface ManagementService {
	public List<Note> getAllNotes();
	public Note searchById(int id);
	public List<Note> searchByAuthor(String author);
}
