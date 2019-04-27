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

@Entity
@XmlRootElement
public class Note implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column(length=25)
	String author;
	@Column(name="created")
	Timestamp date;
	@Column(length=240)
	String message;
	
//	Tom konstruktor krävs av JPA
	public Note() {}
	
	public Note(String author, String message) {
		this.author = author;
		this.message = message;
	}
	@PrePersist
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
