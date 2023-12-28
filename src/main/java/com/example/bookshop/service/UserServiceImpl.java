package com.example.bookshop.service;

import com.example.bookshop.dto.RegisterUserRequestDto;
import com.example.bookshop.dto.UserResponseDto;
import com.example.bookshop.exception.RegistrationException;
import com.example.bookshop.mapper.UserMapper;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(RegisterUserRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findUserByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Can`t register user: email is already used");
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setEmail(requestDto.getEmail());
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setShippingAddress(requestDto.getShippingAddress());
        User userEntity = userRepository.save(user);
        return userMapper.toDto(userEntity);
    }
}
