package nl.ruudclaassen.movie_list.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserMediaStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	
	private boolean seen;
	private boolean owned;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	public boolean isOwned() {
		return owned;
	}
	public void setOwned(boolean owned) {
		this.owned = owned;
	}
}
