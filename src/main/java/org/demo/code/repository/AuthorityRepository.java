package org.demo.code.repository;

import org.demo.code.model.Authority;
import org.demo.code.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
