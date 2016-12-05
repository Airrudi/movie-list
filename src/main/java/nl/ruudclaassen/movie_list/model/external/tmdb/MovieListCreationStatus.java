package nl.ruudclaassen.movie_list.model.external.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;

import nl.ruudclaassen.movie_list.model.external.tmdb.core.ResponseStatus;


public class MovieListCreationStatus extends ResponseStatus {

    @JsonProperty("list_id")
    private String listId;


    public String getListId() {
        return listId;
    }
}
