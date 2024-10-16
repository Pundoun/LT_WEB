package iotstar.project.dao.impl;

import java.util.List;

import iotstar.project.configs.JPAConfig;
import iotstar.project.dao.ICategoryDao;
import iotstar.project.dao.IVideoDao;
import iotstar.project.entity.Category;
import iotstar.project.entity.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class VideoDao implements IVideoDao {

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(v) FROM Category v";
		Query query = enma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public List<Video> findByTitle(String title) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT v FROM Video v WHERE v.title like :title";
		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
		query.setParameter("title", "%" + title + "%");
		return query.getResultList();
	}

	@Override
	public List<Video> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public Video findById(int videoId) {
		EntityManager enma = JPAConfig.getEntityManager();
		Video video = enma.find(Video.class, videoId);
		return video;
	}

	@Override
	public void delete(String videoId) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			// gọi phương thức để insert, update, delete
			Category category = enma.find(Category.class, videoId);
			if (category != null) {
				enma.remove(category);
			} else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			// gọi phuong thức để insert, update, delete
			enma.merge(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void insert(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			// gọi phuong thức để insert, update, delete
			enma.persist(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
//	//Test insert video
//	public static void main(String[] args) {
//		// test insert video
//		ICategoryDao cateDao = new CategoryDao();
//		Category cate = cateDao.findById(1);
//		
//		Video video = new Video();
//		video.setActive(0);
//		video.setCategory(cate);
//		video.setDescription("Nothing to say");
//		video.setPoster(null);
//		video.setTitle("Nope video");
//		video.setVideoId("vid01");
//		video.setViews(0);
//		
//		IVideoDao videoDao = new VideoDao();
//		videoDao.insert(video);
//	}

}
