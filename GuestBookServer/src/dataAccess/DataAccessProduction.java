package src.dataAccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.Note;

@Stateless
public class DataAccessProduction implements DataAccessable {

//	Hanterar DatabasAnropen
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Note findNoteById(int id) {
		Query q = em.createQuery("Select note from Note note where note.id=:id");
		q.setParameter("id", id);
		return (Note)q.getSingleResult();
	}

	@Override
	public List<Note> findByAuthor(String author) {
		Query q = em.createQuery("Select note from Note note where note.author=:author");
		q.setParameter("author", author);
		List<Note> list = q.getResultList();
		return list;
	}

	@Override
	public List<Note> getAll() {
		Query q = em.createQuery("Select note from Note note");
		List<Note> list = q.getResultList();
		return list;
	}

}
