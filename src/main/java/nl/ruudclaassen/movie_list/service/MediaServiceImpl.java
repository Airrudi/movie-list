package nl.ruudclaassen.movie_list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.ruudclaassen.movie_list.data.MediaDao;
import nl.ruudclaassen.movie_list.data.MovieDao;
import nl.ruudclaassen.movie_list.model.Media;

@Service
public class MediaServiceImpl implements MediaService {
	
	@Autowired
	MediaDao mediaDao;
	
	@Override
	public byte[] getImageByMediaUUID(String uuid){
		return mediaDao.getImageByMediaUUID(uuid);  
	}

	@Override
	public Media getByUUID(String uuid) {
		return mediaDao.getByUUID(uuid);
	}

	@Override
	@Transactional
	public void deleteMedia(String uuid) {
		mediaDao.deleteMedia(uuid);		
	};
}
