package com.esiea.webapp_apiaccount_player.service;

import com.esiea.webapp_apiaccount_player.model.User;

import java.util.Optional;

public interface UserService {
    void save(User user);

    Optional<User> findByUsername(String username);

    void loadDataUsers();
}
