package org.demo.code.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_authority", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "authority_name", referencedColumnName = "name") })
	private Set<Authority> authorities = new HashSet<>();

	@Size(max = 50)
	@Column(name = "first_name", length = 50)
	private String firstName;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(max = 50)
	@Column(name = "last_name", length = 50)
	private String lastName;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(length = 50, unique = true, nullable = false)
	private String login;

	@JsonIgnore
	@NotNull
	@Size(min = 60, max = 60)
	@Column(name = "password_hash", length = 60)
	private String password;

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public String getFirstName() {
		return firstName;
	}

	public Long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User {id=" + id + ", login=" + login + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", authorities=" + authorities + "}";
	}

}
