package nl.ruudclaassen.movie_list.model.external.tmdb.core;

import nl.ruudclaassen.movie_list.model.external.tmdb.MovieDbException;

public class ResponseStatusException extends MovieDbException {

    private final ResponseStatus responseStatus;


    public ResponseStatusException(ResponseStatus responseStatus) {
        super(responseStatus.getStatusCode() + " :: " + responseStatus.getStatusMessage());

        this.responseStatus = responseStatus;
    }


    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }


    @Override
    public String toString() {
        return responseStatus.toString();
    }
}
