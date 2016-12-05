package nl.ruudclaassen.movie_list.model.external.tmdb;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import nl.ruudclaassen.movie_list.model.external.tmdb.core.IdElement;

public class ReleaseInfoResults extends IdElement {

    @JsonProperty("countries")
    private List<ReleaseInfo> countries;


    public List<ReleaseInfo> getCountries() {
        return countries;
    }
}