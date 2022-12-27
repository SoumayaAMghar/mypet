package com.example.mypet.person.adoptant;

import com.example.mypet.person.Person;
import jakarta.persistence.Entity;

@Entity
public class Adoptant extends Person {
    private Integer nbr_animaux;
}
