package nl.ruudclaassen.movie_list.model.external.tmdb.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import nl.ruudclaassen.movie_list.model.external.tmdb.keywords.Keyword;

import java.util.ArrayList;
import java.util.List;


public class MovieKeywords extends AbstractJsonMapping {


    @JsonProperty("keywords")
    private List<Keyword> keywords = new ArrayList<Keyword>();


    public List<Keyword> getKeywords() {
        return keywords;
    }
}
