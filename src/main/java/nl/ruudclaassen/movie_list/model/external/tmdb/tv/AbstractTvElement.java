package nl.ruudclaassen.movie_list.model.external.tmdb.tv;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import nl.ruudclaassen.movie_list.model.external.tmdb.Credits;
import nl.ruudclaassen.movie_list.model.external.tmdb.ExternalIds;
import nl.ruudclaassen.movie_list.model.external.tmdb.MovieImages;
import nl.ruudclaassen.movie_list.model.external.tmdb.Video;
import nl.ruudclaassen.movie_list.model.external.tmdb.core.NamedIdElement;


public class AbstractTvElement extends NamedIdElement {


    // Appendable responses for all tv elements

    @JsonProperty("credits")
    private Credits credits;

    @JsonProperty("external_ids")
    private ExternalIds externalIds;

    @JsonProperty("images")
    private MovieImages images;

    @JsonProperty("videos")
    private Video.Results videos;


    public Credits getCredits() {
        return credits;
    }


    public ExternalIds getExternalIds() {
        return externalIds;
    }


    public MovieImages getImages() {
        return images;
    }

    public void setExternalIds(ExternalIds e) {
    	externalIds = e;
    }
    
    public void setCredits(Credits c) {
    	credits = c;
    }
    
    public List<Video> getVideos() {
        return videos != null ? videos.getVideos() : null;
    }
}
