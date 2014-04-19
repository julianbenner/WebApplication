package main;

import java.io.Serializable;

public class User implements Serializable {
	private String name;
	private int group;
	private int id;

	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public boolean isAdmin() {
		return (this.group == 1 || this.group == 2);
	}
}
