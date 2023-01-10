package com.example.mypet.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.aop.target.LazyInitTargetSource;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String age;

    @OneToMany(mappedBy = "animal")
    private List<Image> images;
}
