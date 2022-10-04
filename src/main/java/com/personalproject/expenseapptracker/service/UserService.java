package com.personalproject.expenseapptracker.service;

import com.personalproject.expenseapptracker.exception.ItemAlreadyExistsException;
import com.personalproject.expenseapptracker.exception.ResourceNotFoundException;
import com.personalproject.expenseapptracker.model.User;
import com.personalproject.expenseapptracker.model.UserModel;
import com.personalproject.expenseapptracker.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public User createUser(UserModel user) {
        if(userRepo.existsByEmail(user.getEmail())) {
            throw new ItemAlreadyExistsException("User is already registered with email: "+user.getEmail());
        }

        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepo.save(newUser);
    }

    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found for id "+ id));
    }


    public User updateUser(UserModel user, Long id) {
        User existingUser = getUser(id);
        existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null ? passwordEncoder.encode(user.getPassword()) : existingUser.getPassword());
        existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());

        return userRepo.save(existingUser);
    }

    public void delete(Long id) {
        User user = getUser(id);
        userRepo.delete(user);
    }
}
