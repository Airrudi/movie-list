package nl.ruudclaassen.movie_list.service;

import nl.ruudclaassen.movie_list.model.Media;

public interface MediaService {
	byte[] getImageByMediaUUID(String uuid);
	Media getByUUID(String uuid);
	void deleteMedia(String uuid);
}
