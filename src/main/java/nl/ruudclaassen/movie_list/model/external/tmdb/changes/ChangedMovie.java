package nl.ruudclaassen.movie_list.model.external.tmdb.changes;

import com.fasterxml.jackson.annotation.JsonProperty;

import nl.ruudclaassen.movie_list.model.external.tmdb.core.IdElement;


public class ChangedMovie extends IdElement {


    @JsonProperty("adult")
    private boolean adult;


    public boolean isAdult() {
        return adult;
    }


    public void setAdult(boolean adult) {
        this.adult = adult;
    }
}
