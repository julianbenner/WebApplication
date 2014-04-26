package main;

import java.io.Serializable;

public class Author implements Serializable {
	private int id;
	private String surname;
	private String firstname;

	public Author() {
	}

	public Author(boolean preinitialized) {
		if (preinitialized) {
			surname = "";
			firstname = "";
		}
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
