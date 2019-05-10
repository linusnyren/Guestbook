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

@Stateless //Stateless innebär att en instans av klassen körs, men sedan slängs utan spår till skillnad från statefull. 
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
		return q.getResultList();
	}

	@Override
	public List<Note> getAll() {
		Query q = em.createQuery("Select note from Note note");
		List<Note> list = q.getResultList();
		return list;
	}

	@Override
	public List<Note> findByDate(Date date) {
		String datum = date.getYear()+"-"+date.getMonth()+"-"+date.getDay();
		Query q = em.createQuery("Select note from Note note where note.date=:datum");
		q.setParameter("datum", datum);
		return q.getResultList();
	}

	@Override
	public void register(Note note) {
		try{
			em.persist(note); //Persist i korta lag är att lagra objektet
		}
		catch(EntityExistsException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void delete(Note note) {
		try {	
//		Hämtar ett värde från databas med matchande id och Castar det till Note
//		Denna Note måste sparas temporärt, för om flera anrop görs samtidigt och den till exempel redigeras.
		Note noteToDelete = em.find(Note.class, note.getId());
//		För att sedan skicka in den nya Note i remove för att ta bort matchande post i tabellen.
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
