package nl.ruudclaassen.movie_list.model.external.tmdb.core;

import com.fasterxml.jackson.annotation.JsonProperty;


public class NamedIdElement extends IdElement {


    @JsonProperty("name")
    private String name;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return getName() + " [" + getId() + "]";
    }
}