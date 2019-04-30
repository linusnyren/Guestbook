package dataAccess;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.Response;

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

	@Override
	public List<Note> findByDate(Date date) {
//		2019-04-26
		String datum = date.getYear()+"-"+date.getMonth()+"-"+date.getDay();
		Query q = em.createQuery("Select note from Note note where note.date=:datum");
		q.setParameter("datum", datum);
		return null;
	}

	@Override
	public void register(Note note) {
//		[{"author":"Linus","message":"Funkar detta eller?","id":1,"date":1556229600000}]
		try{
//			"Insert into Note values(note.id, note.author, note.date, note.message);
			em.persist(note);
			
		}
		catch(EntityExistsException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void delete(Note note) {
		try {	
//							"Select note from Note note where note.id=note.getId();"
		Note noteToDelete = em.find(Note.class, note.getId());
//		"Delete from Note where note.id=note.getId()"
		em.remove(noteToDelete);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void update(Note note) {
		try {
			Note noteToUpdate = em.find(Note.class, note.getId());
			noteToUpdate.setMessage(note.getMessage());
			noteToUpdate.setAuthor(note.getAuthor());
//			Lite oklart hur detta funkar, det verkar sparas i ett lokalt objekt som sedan skriver över det gamla
//			Utan att vi behöver anropa någon EntityManager funktion
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

}
