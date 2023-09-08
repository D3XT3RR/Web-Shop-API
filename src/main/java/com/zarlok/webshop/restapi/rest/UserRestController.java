package com.zarlok.webshop.restapi.rest;


import com.zarlok.webshop.restapi.entity.User;
import com.zarlok.webshop.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable String username, @RequestBody User user){
        return userService.update(user, username);
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUsername(@PathVariable String username){
        userService.deleteByUsername(username);
    }
}
