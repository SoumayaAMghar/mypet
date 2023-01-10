package com.example.mypet.models;

import com.example.mypet.models.person.Client;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import javax.swing.event.CaretListener;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Post implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String titre;
    @NonNull
    private String description;
    private String ville;
    private Integer nbr_jours;
    private Double prix;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post post = (Post) o;
        return id != null && Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;

    @OneToMany(mappedBy = "post")
    private List<Image> images;
}
