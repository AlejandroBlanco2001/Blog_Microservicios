package com.login.user.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User getUserByID(Long id);

    User getUserByEmail(String email);

    User getUserByUsername(String username);

}
