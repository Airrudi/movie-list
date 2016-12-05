package nl.ruudclaassen.movie_list.model.external.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;

import nl.ruudclaassen.movie_list.model.external.tmdb.core.IdElement;

import java.util.List;


public class MovieTranslations extends IdElement {

    @JsonProperty("translations")
    private List<Translation> translations;


    public List<Translation> getTranslations() {
        return translations;
    }
}
