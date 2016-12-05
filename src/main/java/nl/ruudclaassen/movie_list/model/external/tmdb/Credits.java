package nl.ruudclaassen.movie_list.model.external.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;

import nl.ruudclaassen.movie_list.model.external.tmdb.core.IdElement;
import nl.ruudclaassen.movie_list.model.external.tmdb.people.Person;
import nl.ruudclaassen.movie_list.model.external.tmdb.people.PersonCast;
import nl.ruudclaassen.movie_list.model.external.tmdb.people.PersonCrew;

import java.util.ArrayList;
import java.util.List;


public class Credits extends IdElement {

    @JsonProperty("crew")
    List<PersonCrew> crew;

    @JsonProperty("cast")
    List<PersonCast> cast;

    @JsonProperty("guest_stars")
    List<PersonCast> guestStars;


    public List<PersonCrew> getCrew() {
        return crew;
    }


    public List<PersonCast> getCast() {
        return cast;
    }


    public List<PersonCast> getGuestStars() {
        return guestStars;
    }


    /**
     * Convenience wrapper to get all people involved in the movie>
     */
    public List<Person> getAll() {
        List<Person> involved = new ArrayList<Person>();

        involved.addAll(Utils.nullAsEmpty(crew));
        involved.addAll(Utils.nullAsEmpty(cast));
        involved.addAll(Utils.nullAsEmpty(guestStars));

        return involved;
    }
}
