package com.whiz.springbootmongo.service;

import com.whiz.springbootmongo.domain.User;
import com.whiz.springbootmongo.dto.UserDTO;
import com.whiz.springbootmongo.repository.UserRepository;
import com.whiz.springbootmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object could not be found"));
    }

    public User insert(User user){
        return userRepository.insert(user);
    }

    public User fromDto(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

}
