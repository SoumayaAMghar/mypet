package com.example.mypet.security;

import com.example.mypet.models.person.Client;
import com.example.mypet.repository.AdoptantRepository;
import com.example.mypet.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private ClientRepository clientRepository;
    private AdoptantRepository adoptantRepository;

    @Autowired
    public CustomUserDetailsService(ClientRepository clientRepository, AdoptantRepository adoptantRepository) {
        this.clientRepository = clientRepository;
        this.adoptantRepository = adoptantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.example.mypet.models.person.User user = clientRepository.findByEmail(email).isPresent()
                ? clientRepository.findByEmail(email).get()
                : adoptantRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("no user found with this email: " + email));

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>() {{
            add(new SimpleGrantedAuthority(user.getRole().name()));
        }});
    }
}
