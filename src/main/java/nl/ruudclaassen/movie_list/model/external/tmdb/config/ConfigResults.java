package nl.ruudclaassen.movie_list.model.external.tmdb.config;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import nl.ruudclaassen.movie_list.model.external.tmdb.core.AbstractJsonMapping;


public class ConfigResults extends AbstractJsonMapping implements Serializable {

    @JsonProperty("images")
    private TmdbConfiguration tmdbConfiguration;
    @JsonProperty("change_keys")
    private List<String> changeKeys;


    public TmdbConfiguration getTmdbConfiguration() {
        return tmdbConfiguration;
    }


    public void setTmdbConfiguration(TmdbConfiguration tmdbConfiguration) {
        this.tmdbConfiguration = tmdbConfiguration;
    }


    public List<String> getChangeKeys() {
        return changeKeys;
    }


    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }
}
