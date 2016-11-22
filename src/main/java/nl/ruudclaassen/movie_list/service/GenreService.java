package nl.ruudclaassen.movie_list.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.ruudclaassen.movie_list.data.GenreDao;
import nl.ruudclaassen.movie_list.model.Genre;

@Service
public class GenreService {
	
	@Autowired
	GenreDao genreDao;
	
	public List<Genre> getGenres(){
		return genreDao.getGenres();
	}
	
	public Genre getGenreByUuid(String uuid){
		return genreDao.getGenreByUuid(uuid);
	}
}
