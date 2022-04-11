package com.lab02.locadora.services;

import com.lab02.locadora.dtos.UserRegistrationDto;
import com.lab02.locadora.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface  UserService extends UserDetailsService{

    User save(UserRegistrationDto registrationDto);
    
}
