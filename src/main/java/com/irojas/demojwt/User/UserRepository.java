package com.irojas.demojwt.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data genera:
    // SELECT * FROM USERS u WHERE u.USERNAME = ? (respeta @Table y @Column)
    Optional<User> findByUsername(String username);
}
