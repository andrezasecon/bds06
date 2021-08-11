package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping(value = "/users")
public class UserResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findProfileById(@PathVariable Long id) {
        UserDTO dto = userService.findUserById(id);
        return ResponseEntity.ok().body(dto);

    }

    @GetMapping(value = "/profile")
    public ResponseEntity<UserDTO> findSelfProfile(){
        UserDTO dto = userService.selfValidate();
        return ResponseEntity.ok().body(dto);
    }

}
