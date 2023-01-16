package com.example.mypet.controller;

import com.example.mypet.dto.AuthResponseDto;
import com.example.mypet.dto.AuthRequestDto;
import com.example.mypet.dto.RegisterDto;
import com.example.mypet.models.person.Adoptant;
import com.example.mypet.models.person.Client;
import com.example.mypet.models.person.UserRoles;
import com.example.mypet.repository.AdoptantRepository;
import com.example.mypet.repository.ClientRepository;
import com.example.mypet.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private ClientRepository clientRepository;
    private AdoptantRepository adoptantRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, ClientRepository clientRepository, AdoptantRepository adoptantRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.clientRepository = clientRepository;
        this.adoptantRepository = adoptantRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (clientRepository.existsClientByEmail(registerDto.email()) || adoptantRepository.existsAdoptantByEmail(registerDto.email())) {
            return new ResponseEntity<>("Email is taken! ", HttpStatus.BAD_REQUEST);
        }
        System.out.println(registerDto.role());
        if (registerDto.role().equals(UserRoles.ADOPTANT)) {
            Adoptant adoptant = new Adoptant(registerDto.name(), registerDto.email(), passwordEncoder.encode(registerDto.password()), registerDto.address(), registerDto.tel(), registerDto.nbr_animaux(), registerDto.role());
            adoptantRepository.save(adoptant);
        } else {
            Client client = new Client(registerDto.name(), registerDto.email(), passwordEncoder.encode(registerDto.password()), registerDto.address(), registerDto.tel(), registerDto.role());
            clientRepository.save(client);
        }
        return new ResponseEntity<>("User registred succesfully", HttpStatus.OK);
    }

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.email(),
                        authRequestDto.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }
}