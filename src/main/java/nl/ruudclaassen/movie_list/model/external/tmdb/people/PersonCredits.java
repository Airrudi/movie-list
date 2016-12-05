package nl.ruudclaassen.movie_list.model.external.tmdb.people;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.ruudclaassen.movie_list.model.external.tmdb.core.AbstractJsonMapping;

public class PersonCredits extends AbstractJsonMapping {

    @JsonProperty("cast")
    private List<PersonCredit> cast;
    @JsonProperty("crew")
    private List<PersonCredit> crew;


    public List<PersonCredit> getCast() {
        return cast;
    }


    public List<PersonCredit> getCrew() {
        return crew;
    }
}
