package com.personalproject.expenseapptracker.controller;

import com.personalproject.expenseapptracker.model.User;
import com.personalproject.expenseapptracker.model.UserModel;
import com.personalproject.expenseapptracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<User>(userService.getUser(), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody UserModel user) {
        return new ResponseEntity<User>(userService.updateUser(user),HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> delete() {
        userService.delete();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

}
