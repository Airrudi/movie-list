package nl.ruudclaassen.movie_list.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.print.attribute.standard.Media;
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
	
	@Transient
	private Set<Movie> todos;

	@OneToMany
	private List<User> friends;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Map<String, UserMediaStatus> judgedMovies;

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

	public Set<Movie> getTodos() {
		return todos;
	}

	public void setTodos(Set<Movie> todos) {
		this.todos = todos;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public Map<String, UserMediaStatus> getJudgedMovies() {
		return judgedMovies;
	}

	public void setJudgedMovies(Map<String, UserMediaStatus> judgedMovies) {
		this.judgedMovies = judgedMovies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
}
