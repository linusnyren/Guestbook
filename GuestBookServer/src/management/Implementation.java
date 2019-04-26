package management;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

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

}
