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

    // TODO: 아이디 비밀번호에 대한 정규식 처리, 아이디 중복 -> 예외처리
    public void joinProcess(JoinDTO joinDTO){

        boolean existsUser = userRepository.existsByUsername(joinDTO.getUsername());
        if(existsUser){
            return;
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(joinDTO.getUsername());
        entity.setPassword(passwordEncoder.encode(joinDTO.getPassword()));
        entity.setRole("ROLE_ADMIN");
        userRepository.save(entity);
    }
}
