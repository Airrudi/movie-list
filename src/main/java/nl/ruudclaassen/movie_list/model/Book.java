package nl.ruudclaassen.movie_list.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("BOOK")
public class Book extends Media {

	@NotNull
	private String writer; // Or create a person?

	public Book() {}

}
