package com.example.mypet.models;

import com.example.mypet.models.person.Adoptant;
import com.example.mypet.models.person.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @OneToMany(mappedBy = "commentaire", cascade = CascadeType.ALL)
    private List<Reponse> reponses;
    @ManyToOne
    private Post post;


//    @ManyToOne
//    private Adoptant adoptant;
//
//    @ManyToOne
//    private Client client;

}
