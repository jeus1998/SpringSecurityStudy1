package com.example.testsecurity.service;

import com.example.testsecurity.dto.JoinDTO;
import com.example.testsecurity.entity.UserEntity;
import com.example.testsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public void joinProcess(JoinDTO joinDTO){
        UserEntity entity = new UserEntity();
        entity.setUsername(joinDTO.getUsername());
        entity.setPassword(passwordEncoder.encode(joinDTO.getPassword()));
        entity.setRole("ROLE_USER");
        userRepository.save(entity);
    }
}
