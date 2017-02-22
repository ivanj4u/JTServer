package co.id.aribanilia.jtserver.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

/**
 * @author ivan_j4u
 * Feb 9, 2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	@Id
	public String id;

	@NotNull @NotEmpty
	public String userId;

	@NotNull @NotEmpty
	public String name;

	@NotNull @NotEmpty
	public String password;
	
	public User() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%s, userId='%s', name='%s', password='%s']", id, userId, name, password);
	}
}
