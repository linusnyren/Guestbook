package domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.xml.bind.annotation.XmlRootElement;

@Entity //Entity är det som kommer att lagras i databasen, en typ av instans.
@XmlRootElement //XMLRootElement innebär att denna Klassen utgår som mall när man producerar JSON eller XML
public class Note implements Serializable{
	
	@Id //Id i Databasen
	@GeneratedValue(strategy=GenerationType.AUTO) //Som autogenereras
	int id;
	
	@Column(length=25) //Max längd på tecken i databasen
	String author;
	@Column(name="created") //Namn på kolumn i databasen.
	Timestamp date;
	@Column(length=240)	// Samma som author fast 240 tecken.
	String message;
	
//	Tom konstruktor krävs av JPA
	public Note() {}
	
	public Note(String author, String message) { //En enkel POJO konstrukor för att skapa instanser av klassen
		this.author = author;
		this.message = message;
	}
	@PrePersist //PrePersist körs alltid innan en instans av denna klassen persisteras.
	public void createdAt() {	
		Timestamp datum = new Timestamp(Calendar.getInstance().getTimeInMillis());
	    this.date = datum;
	}
	@Override
	public String toString() {
		return author +" skrev: " +message +" id:" +id +" datum: " +date.toString();
	}
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp timestamp) {
		this.date = timestamp;
	}
}
