package nl.ruudclaassen.movie_list.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SERIES")
public class Series extends Media {	

	private int episodeDuration;
	private int numberOfSeasons;
	
	public Series() {}

	public int getEpisodeDuration() {
		return episodeDuration;
	}
	
	public void setEpisodeDuration(int episodeDuration) {
		this.episodeDuration = episodeDuration;
	}
	public int getNumberOfSeasons() {
		return numberOfSeasons;
	}
	public void setNumberOfSeasons(int numberOfSeasons) {
		this.numberOfSeasons = numberOfSeasons;
	}
	
	

	

}
