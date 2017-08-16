package org.demo.code.service.dto;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Size;

import org.demo.code.model.Authority;
import org.demo.code.model.User;

public class UserDTO {

	@Size(min = 1, max = 50)
	private String login;

	@Size(max = 50)
	private String firstName;

	@Size(max = 50)
	private String lastName;

	private Set<String> authorities;

	public UserDTO() {
	}
	
	public UserDTO(User user) {
        this(user.getLogin(), user.getFirstName(), user.getLastName(),
            user.getAuthorities().stream().map(Authority::getName)
                .collect(Collectors.toSet()));
    }
	
	public UserDTO(String login, String firstName, String lastName, Set<String> authorities) {

	        this.login = login;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.authorities = authorities;
	    }

	public String getLogin() {
		return login;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}
	
	@Override
    public String toString() {
        return "UserDTO{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", authorities=" + authorities +
            "}";
    }

}
