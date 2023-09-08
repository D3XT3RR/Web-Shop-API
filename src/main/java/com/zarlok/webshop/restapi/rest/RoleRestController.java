package com.zarlok.webshop.restapi.rest;

import com.zarlok.webshop.restapi.entity.Role;
import com.zarlok.webshop.restapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{username}/roles")
public class RoleRestController {

    private final RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public Role findForUsername(@PathVariable String username){
        return roleService.findByUsername(username);
    }

    @GetMapping("/{roleId}")
    public Role findByUsernameAndId(@PathVariable String username, @PathVariable int roleId){
        return roleService.findByUsernameAndRoleId(username, roleId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role save(@RequestBody Role role, @PathVariable String username){
        return roleService.save(role, username);
    }

    @PutMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public Role update(@RequestBody Role role, @PathVariable String username, @PathVariable int roleId){
        return roleService.update(role, username, roleId);
    }

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int roleId, @PathVariable String username){
        roleService.deleteById(roleId, username);
    }

}
