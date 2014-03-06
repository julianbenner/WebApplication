/**
 * Created by Julian on 13/02/14.
 */

package main;

public class User {
	String name;
	int id;

	public User(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() { return id; }
}
