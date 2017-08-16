package org.demo.code.repository;

import java.util.Optional;

import org.demo.code.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findOneByLogin(String login);

    @Query(value = "select distinct user from User user left join fetch user.authorities",
        countQuery = "select count(user) from User user")
    Page<User> findAllWithAuthorities(Pageable pageable);

}
