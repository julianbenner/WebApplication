package main;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Julian on 05/03/14.
 */
public class Book implements Serializable {
	private String title;
	private Author author;
	private Calendar releaseDate;

	public Book(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Calendar getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}
}
