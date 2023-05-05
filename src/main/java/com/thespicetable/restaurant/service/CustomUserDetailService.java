package com.thespicetable.restaurant.service;

import com.thespicetable.restaurant.model.CustomUserDetail;
import com.thespicetable.restaurant.model.User;
import com.thespicetable.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user= userRepository.findUserByEmail(email);
        user.orElseThrow(()-> new UsernameNotFoundException("User Not Found ☹"));
        return user.map(CustomUserDetail::new).get();
    }
}
