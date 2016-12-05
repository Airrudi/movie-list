package nl.ruudclaassen.movie_list.model.external.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import nl.ruudclaassen.movie_list.model.external.tmdb.core.AbstractJsonMapping;


@JsonRootName("production_country")
public class ProductionCountry extends AbstractJsonMapping {


    @JsonProperty("iso_3166_1")
    private String isoCode;
    @JsonProperty("name")
    private String name;


    public String getIsoCode() {
        return isoCode;
    }


    public String getName() {
        return name;
    }
}
