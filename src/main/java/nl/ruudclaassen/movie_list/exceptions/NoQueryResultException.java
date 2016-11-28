package nl.ruudclaassen.movie_list.exceptions;

public class NoQueryResultException extends RuntimeException {
    public NoQueryResultException(String message){
        super(message);
    }
}