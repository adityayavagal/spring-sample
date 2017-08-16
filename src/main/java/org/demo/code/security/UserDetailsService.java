package org.demo.code.security;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.demo.code.model.User;
import org.demo.code.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Inject
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        Optional<User> userFromDatabase = userRepository.findOneByLogin(lowercaseLogin);
        return userFromDatabase.map(user -> {
            List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(lowercaseLogin,
                user.getPassword(),
                grantedAuthorities);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " +
        "database"));
    }

}
