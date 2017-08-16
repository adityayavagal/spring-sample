package org.demo.code.controller;

import javax.inject.Inject;

import org.demo.code.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Inject
    private UserRepository userRepository;

}
