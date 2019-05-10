package management;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import dataAccess.DataAccessable;
import domain.Note;

@Stateless
public class Implementation implements ManagementService, ManagementServiceRemote{

	@Inject
	private DataAccessable dao;
	
	@Override
	public List<Note> getAllNotes() {
		return dao.getAll();
	}

	@Override
	public Note searchById(int id) {
		return dao.findNoteById(id);
	}

	@Override
	public List<Note> searchByAuthor(String author) {
		return dao.findByAuthor(author);
	}

	@Override
	public List<Note> searchByDate(Date date) {
		return dao.findByDate(date);
	}

	@Override
	public void insertNewNote(Note note) {
		dao.register(note);
	}

	@Override
	public void delete(Note note) {
		dao.delete(note);
	}

	@Override
	public void update(Note note) {
		dao.update(note);
	}

}
