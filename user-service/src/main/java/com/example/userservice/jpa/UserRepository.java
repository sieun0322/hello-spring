package com.example.userservice.jpa;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    UserEntity findByUserId(String userId);
    UserEntity findByEmail(String username);

}
