package com.example.mypet.person.adoptant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptantRepository extends JpaRepository<Adoptant,Long> {
}
