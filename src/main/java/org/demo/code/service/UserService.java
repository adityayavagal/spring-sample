package org.demo.code.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.demo.code.model.Authority;
import org.demo.code.model.User;
import org.demo.code.repository.AuthorityRepository;
import org.demo.code.repository.UserRepository;
import org.demo.code.security.AuthoritiesConstants;
import org.demo.code.security.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional
public class UserService {

	@Inject
	private PasswordEncoder passwordEncoder;

	@Inject
	private UserRepository userRepository;

	@Inject
	private AuthorityRepository authorityRepository;
	
	public User createUser(String login, String password, String firstName, String lastName) {

	        User newUser = new User();
	        Authority authority = authorityRepository.findOne(AuthoritiesConstants.USER);
	        Set<Authority> authorities = new HashSet<>();
	        String encryptedPassword = passwordEncoder.encode(password);
	        newUser.setLogin(login);
	        // new user gets initially a generated password
	        newUser.setPassword(encryptedPassword);
	        newUser.setFirstName(firstName);
	        newUser.setLastName(lastName);
	        authorities.add(authority);
	        newUser.setAuthorities(authorities);
	        userRepository.save(newUser);
	        return newUser;
	    }
	
	@Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        Optional<User> optionalUser = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin());
        User user = null;
        if (optionalUser.isPresent()) {
          user = optionalUser.get();
            user.getAuthorities().size(); // eagerly load the association
         }
         return user;
    }

}
