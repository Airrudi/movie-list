package nl.ruudclaassen.movie_list.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@Column(unique = true)
	private String uuid;
	private String name;
	private String email;	
	
	// TODO: Lookup what internally a map is
	//@ManyToMany
	//Map<Movie, UserMovieStatus> movieStatusses;
	
	@OneToMany	 
	private List<Movie> favorites;
	
	@OneToMany	
	private List<User> friends; 
	
	public User() {}
	
	public User(int id, String uuid, String name, String email) {		
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
