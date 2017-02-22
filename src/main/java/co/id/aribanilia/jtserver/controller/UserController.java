package co.id.aribanilia.jtserver.controller;

import co.id.aribanilia.jtserver.entity.User;
import co.id.aribanilia.jtserver.service.UserService;
import co.id.aribanilia.jtserver.util.JTSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ivan_j4u
 * Feb 9, 2017
 */
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService service;
	private final static Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	@GetMapping("/user")
	@ResponseBody
	public List<User> allUser() {
		log.info("Incoming GET /user/");
		return service.all();
	}
	
	@GetMapping("/id/{id}")
	@ResponseBody
	public User getUserById(@PathVariable String id) {
		log.info("Incoming GET /user/" + id);
		return service.getById(id);
	}
	
	@GetMapping("/nama/{name}")
	@ResponseBody
	public User getUserByName(@PathVariable String name) {
		log.info("Incoming GET /user/" + name);
		return service.getByName(name);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody User user, HttpServletResponse response) {
		log.info("Incoming POST user/add/" + user.getName());
		try {
			User userOld = service.getById(user.getUserId());
			if (userOld != null) {
				response.setStatus(HttpStatus.CONFLICT.value());
			} else {
				String password = user.getPassword();
				if (password != null && !password.equals("")) {
					user.setPassword(JTSecurity.encryptToString(password));
					service.save(user);
					response.setStatus(HttpStatus.CREATED.value());
				} else {
					response.setStatus(HttpStatus.BAD_REQUEST.value());
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}
}