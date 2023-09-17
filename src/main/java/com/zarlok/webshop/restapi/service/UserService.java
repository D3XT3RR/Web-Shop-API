package com.zarlok.webshop.restapi.service;

import com.zarlok.webshop.restapi.dao.UserRepository;
import com.zarlok.webshop.restapi.entity.User;
import com.zarlok.webshop.restapi.exception.UserAlreadyExistException;
import com.zarlok.webshop.restapi.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findByUsername(String username){
        Optional<User> optionalUser = userRepository.findById(username);
        if (!optionalUser.isPresent()){
            throw new UserNotFoundException("Could not find user " + username);
        }
        return optionalUser.get();
    }

    public User save(User user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistException("Please use other username");
        }
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public User update(User user, String username){
        User actualUser = findByUsername(username);
        if(!user.getPassword().equals(actualUser.getPassword())){
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
        }
        user.setUsername(username);
        return userRepository.save(user);
    }

    public User updateDetails(User user, String username){
        System.err.println("FormUser: "+user.toString());
        User actualUser = findByUsername(username);
        actualUser.setEmail(user.getEmail());
        actualUser.setFirstName(user.getFirstName());
        actualUser.setLastName(user.getLastName());
        actualUser.setEnabled(user.isEnabled());
        System.err.println("ActualUser: "+actualUser.toString());
        return userRepository.save(actualUser);
    }

    public User updatePassword(String username, String newPassword){
        User actualUser = findByUsername(username);
        String encodedPassword = passwordEncoder.encode(newPassword);
        actualUser.setPassword(encodedPassword);
        return userRepository.save(actualUser);
    }

    @Transactional
    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }
}
