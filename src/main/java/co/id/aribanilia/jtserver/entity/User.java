package co.id.aribanilia.jtserver.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ivan_j4u
 * Feb 9, 2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
	private static final long serialVersionUID = 8371047365577044024L;
	@Id
	public String id;
	public String name;
	public String password;
	
	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}
