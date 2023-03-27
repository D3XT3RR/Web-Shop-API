package com.zarlok.webshop.restapi.service;

import com.zarlok.webshop.restapi.dao.RoleRepository;
import com.zarlok.webshop.restapi.entity.Role;
import com.zarlok.webshop.restapi.exception.RoleAlreadyExistException;
import com.zarlok.webshop.restapi.exception.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    final private UserService userService;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    public Role findByUsername(String username){
        Optional<Role> optionalRole = roleRepository.findByUser_Username(username);
        if(!optionalRole.isPresent()){
            throw new RoleNotFoundException(
                    "Role not found for username - " + username);
        }
        return optionalRole.get();
    }

    public Role findByUsernameAndRoleId(String username, int roleId){
        Optional<Role> optionalRole = roleRepository.findByUser_UsernameAndId(username, roleId);
        if(!optionalRole.isPresent()){
            throw new RoleNotFoundException(
                    "Role with id - " + roleId + " not found for username - " + username);
        }
        return optionalRole.get();
    }

    public Role save(Role role, String username){
        if(roleRepository.existsByUser_Username(username)){
            throw new RoleAlreadyExistException("Role for a give username exist in a database. " +
                    "Use PUT method to update the existing one.");
        }
        role.setUser(userService.findByUsername(username));
        return roleRepository.save(role);
    }

    public Role update(Role role, String username, int roleId){
        Optional<Role> optionalRole = roleRepository.findByUser_UsernameAndId(username, roleId);
        if(optionalRole.isPresent()){
            role.setId(roleId);
            role.setUser(optionalRole.get().getUser());
            return roleRepository.save(role);
        }
        throw new RoleNotFoundException("Role with id - " + roleId
                                        +" for username - " + username
                                        +" not found. Please create a role first.");
    }

    public void deleteById(int roleId, String username){
        if(roleRepository.existsByIdAndUser_Username(roleId, username)) {
            roleRepository.deleteById(roleId);
        }
    }
}
