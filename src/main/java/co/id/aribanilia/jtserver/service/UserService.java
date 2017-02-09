package co.id.aribanilia.jtserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.aribanilia.jtserver.dao.UserDao;
import co.id.aribanilia.jtserver.entity.User;

/**
 * @author ivan_j4u
 * Feb 9, 2017
 */
@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	public List<User> all() {
		return dao.findAll();
	}
	
	public User getById(String userId) {
		return dao.findByUserId(userId);
	}
	
	public User getByName(String name) {
		return dao.findByName(name);
	}
	
	public void save(User user) {
		dao.save(user);
	}
}
