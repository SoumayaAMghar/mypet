package com.example.mypet.dto;

import com.example.mypet.models.person.UserRoles;
import lombok.Data;


public record RegisterDto (String name, String email, String password, String address, String tel,Integer nbr_animaux, UserRoles role){}
