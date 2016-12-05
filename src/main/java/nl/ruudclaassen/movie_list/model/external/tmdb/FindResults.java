package nl.ruudclaassen.movie_list.model.external.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;

import nl.ruudclaassen.movie_list.model.external.tmdb.core.AbstractJsonMapping;
import nl.ruudclaassen.movie_list.model.external.tmdb.people.Person;
import nl.ruudclaassen.movie_list.model.external.tmdb.tv.TvSeries;

import java.util.List;


public class FindResults extends AbstractJsonMapping {

    @JsonProperty("movie_results")
    private List<MovieDb> movieResults;

    @JsonProperty("person_results")
    private List<Person> personResults;

    @JsonProperty("tv_results")
    private List<TvSeries> tvResults;


    public List<MovieDb> getMovieResults() {
        return movieResults;
    }


    public List<Person> getPersonResults() {
        return personResults;
    }


    public List<TvSeries> getTvResults() {
        return tvResults;
    }
}
