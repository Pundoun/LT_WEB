package iotstar.project.services;

import java.util.List;

import iotstar.project.entity.Video;

public interface IVideoService {
	int count();

//	List<Video> findAll(int page, int pagesize);

	List<Video> findByTitle(String title);

	List<Video> findAll();

	Video findById(int videoId);

	void delete(String videoId) throws Exception;

	void update(Video video);

	void insert(Video video);
}
