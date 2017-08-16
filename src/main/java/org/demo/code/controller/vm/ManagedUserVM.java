package org.demo.code.controller.vm;

import java.util.Set;

import javax.validation.constraints.Size;

import org.demo.code.model.User;
import org.demo.code.service.dto.UserDTO;

public class ManagedUserVM extends UserDTO {

	public static final int PASSWORD_MIN_LENGTH = 4;
	public static final int PASSWORD_MAX_LENGTH = 100;

	private Long id;

	@Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
	private String password;

	public ManagedUserVM() {
	}

	public ManagedUserVM(User user) {
		super(user);
		this.id = user.getId();
		this.password = null;
	}

	public ManagedUserVM(Long id, String login, String password, String firstName, String lastName, Set<String> authorities) {
		super(login, firstName, lastName, authorities);
		this.id = id;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}
	
	@Override
    public String toString() {
        return "ManagedUserVM{" +
            "id=" + id +
            "} " + super.toString();
    }


}
