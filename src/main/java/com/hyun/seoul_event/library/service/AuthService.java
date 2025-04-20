package com.hyun.seoul_event.library.service;

import com.hyun.seoul_event.library.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;

    public boolean login(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> user.isPasswordMatch(password))
                .orElse(false);
    }

}
