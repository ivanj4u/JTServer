package co.id.aribanilia.jtserver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.id.aribanilia.jtserver.entity.User;
import co.id.aribanilia.jtserver.service.UserService;

/**
 * @author ivan_j4u
 * Feb 9, 2017
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	private final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> allUser() {
		log.info("Incoming GET /user/");
		return service.all();
	}
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable String id) {
		log.info("Incoming GET /user/" + id);
		return service.getById(id);
	}
	
	@RequestMapping(value = "/nama/{name}", method = RequestMethod.GET)
	public User getUserByName(@PathVariable String name) {
		log.info("Incoming GET /user/" + name);
		return service.getByName(name);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void saveUser(@RequestBody User user) {
		log.info("Incoming POST user/add/" + user.getName());
		try {
			service.save(user);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
