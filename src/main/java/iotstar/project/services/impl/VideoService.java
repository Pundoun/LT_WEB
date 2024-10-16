package iotstar.project.services.impl;

import java.util.List;

import iotstar.project.dao.IVideoDao;
import iotstar.project.dao.impl.VideoDao;
import iotstar.project.entity.Video;
import iotstar.project.services.IVideoService;

public class VideoService implements IVideoService{

	IVideoDao videoDao = new VideoDao();
	
	@Override
	public int count() {
		return videoDao.count();
	}

	@Override
	public List<Video> findByTitle(String title) {
		return videoDao.findByTitle(title);
	}

	@Override
	public List<Video> findAll() {
		return videoDao.findAll();
	}

	@Override
	public Video findById(int videoId) {
		return videoDao.findById(videoId);
	}

	@Override
	public void delete(String videoId) throws Exception {
		videoDao.delete(videoId);
	}

	@Override
	public void update(Video video) {
		videoDao.update(video);
	}

	@Override
	public void insert(Video video) {
		videoDao.insert(video);
	}

}
