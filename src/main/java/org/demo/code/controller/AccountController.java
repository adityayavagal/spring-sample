package org.demo.code.controller;

import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.demo.code.controller.vm.ManagedUserVM;
import org.demo.code.model.User;
import org.demo.code.repository.UserRepository;
import org.demo.code.service.UserService;
import org.demo.code.service.dto.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AccountController {

	@Inject
	private UserRepository userRepository;
	
	@Inject
    private UserService userService;

	@PostMapping(path = "/register", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {

		HttpHeaders textPlainHeaders = new HttpHeaders();
		textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

		return userRepository.findOneByLogin(managedUserVM.getLogin().toLowerCase())
				.map(user -> new ResponseEntity<>("login already in use", textPlainHeaders, HttpStatus.BAD_REQUEST))
				
						.orElseGet(() -> {
							User user = userService.createUser(managedUserVM.getLogin(), managedUserVM.getPassword(),
									managedUserVM.getFirstName(), managedUserVM.getLastName());

							return new ResponseEntity<>(HttpStatus.CREATED);
						});
	}
	
	@GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        return request.getRemoteUser();
    }
	
	@GetMapping("/account")
    public ResponseEntity<UserDTO> getAccount() {
        return Optional.ofNullable(userService.getUserWithAuthorities())
            .map(user -> new ResponseEntity<>(new UserDTO(user), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
