package domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Note implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(length=25)
	String author;
	@Column(length=240)
	String message;
	
	int id;
	
	Date date;
	
//	Tom konstruktor krävs av JPA
	public Note() {}
	
	public Note(String author, String message, int id, Date date) {
		this.author = author;
		this.message = message;
		this.id = id;
		this.date = date;
	}
	
	public String dateFormatter(Date date) {
		return date.getDay() +"-" +date.getMonth() +"-" +date.getTime();
	}
	@Override
	public String toString() {
		return author +" " +message +" " +id +" " +dateFormatter(date);
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
