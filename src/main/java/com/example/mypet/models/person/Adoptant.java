package com.example.mypet.models.person;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Adoptant extends User {
    private Integer nbr_animaux;

    public Adoptant(String name, String email, String password, String address, String tel,Integer nbr_animaux,UserRoles UserRoles){
        super(name,email,password,address,tel, UserRoles.ADOPTANT);
        this.nbr_animaux = nbr_animaux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Adoptant adoptant = (Adoptant) o;
        return id != null && Objects.equals(id, adoptant.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
