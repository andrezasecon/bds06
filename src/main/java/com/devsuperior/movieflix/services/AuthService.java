package com.devsuperior.movieflix.services;


import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
public class AuthService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public User authenticated() {
        try {
            // método spring para setar o usuário corrente
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return repository.findByEmail(username);
        }
        catch (Exception e){
            throw new UnauthorizedException("Invalid user");
        }
    }

    // método para verificar se o usuário é ele próprio ou admin
    public void validateSelf(Long userId){
        User user = authenticated();
        if(!user.getId().equals(userId)){
            throw new ForbiddenException("Access denied");
        }
    }
}

