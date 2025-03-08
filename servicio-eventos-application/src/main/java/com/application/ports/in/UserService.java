package com.application.ports.in;

import com.application.mappers.UserMapper;
import com.domain.models.UserModel;
import com.infrastructure.entities.User;
import com.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    public Optional<UserModel> findByUsername(String username) {
        Optional<User> userEntity = userRepository.findByUsername(username);
        return userEntity.map(userMapper::userToUserModel);
    }
}