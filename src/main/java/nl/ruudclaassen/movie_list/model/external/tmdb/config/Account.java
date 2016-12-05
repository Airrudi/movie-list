package nl.ruudclaassen.movie_list.model.external.tmdb.config;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import nl.ruudclaassen.movie_list.model.external.tmdb.core.NamedIdElement;


public class Account extends NamedIdElement {

    @JsonProperty("username")
    private String userName;

    @JsonProperty("include_adult")
    private boolean includeAdult;

    @JsonProperty("avatar")
    private HashMap<String, HashMap<String, String>> avatar;
    
    public boolean isIncludeAdult() {
        return includeAdult;
    }


    public void setIncludeAdult(boolean includeAdult) {
        this.includeAdult = includeAdult;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGravatarHash() {
        if (avatar != null && avatar.get("gravatar") != null) {
            return avatar.get("gravatar").get("hash");
        }

        return null;
    }
}
