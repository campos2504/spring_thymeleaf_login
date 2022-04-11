package com.lab02.locadora.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.lab02.locadora.dtos.UserRegistrationDto;
import com.lab02.locadora.model.Role;
import com.lab02.locadora.model.User;
import com.lab02.locadora.model.UserType;
import com.lab02.locadora.repositorys.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepository;

    

    @Override
    public User save(UserRegistrationDto registrationDto) {
        
        var user= User.builder()
        .email(registrationDto.getEmail())
        .senha(registrationDto.getSenha())
        .UserType(UserType.CLIENTE)
        .roles(Arrays.asList(Role.builder().name("USER").build()))
        .build();
        var returnUser=userRepository.save(user);
        return returnUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user =userRepository.findByEmail(username).stream().findFirst().orElseThrow(()->new UsernameNotFoundException("Email ou senha inválido."));

        user.getRoles().stream().map(Role::getName).forEach(System.out::println);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
    
}
