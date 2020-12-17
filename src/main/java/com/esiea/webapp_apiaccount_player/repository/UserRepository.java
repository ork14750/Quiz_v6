package com.esiea.webapp_apiaccount_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esiea.webapp_apiaccount_player.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
