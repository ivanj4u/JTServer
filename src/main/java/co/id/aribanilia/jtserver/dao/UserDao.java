package co.id.aribanilia.jtserver.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.id.aribanilia.jtserver.entity.User;

/**
 * @author ivan_j4u
 * Feb 9, 2017
 */
public interface UserDao extends MongoRepository<User, String>{
	
	public User findByUserId(String userId);
	
	public User findByName(String name);
}
