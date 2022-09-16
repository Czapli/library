package org.czaplinski.library.service;

import org.czaplinski.library.model.User;
import org.czaplinski.library.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.save(user);
    }
}
