package main;

import java.io.Serializable;

/**
 * Created by Julian on 05/03/14.
 */
public class Author implements Serializable {
	private int id;
	private String surname;
	private String firstname;

	public Author() {

	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
