package com.example.mypet.repository;

import com.example.mypet.models.person.Adoptant;
import com.example.mypet.models.person.Client;
import com.example.mypet.models.person.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdoptantRepository extends JpaRepository<Adoptant,Long> {
    Optional<Adoptant> findByEmail(String email);
//    Optional<Adoptant> findAdoptantByRole(UserRoles.Adoptant);
    Boolean existsAdoptantByEmail(String email);
}
