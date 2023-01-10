package com.example.mypet.models.person;

import com.example.mypet.models.Commentaire;
import com.example.mypet.models.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Client extends User {

    public Client(String name, String email, String password, String address, String tel, UserRoles UserRoles){
        super(name,email,password,address,tel,UserRoles.CLIENT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return id != null && Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @OneToMany(mappedBy = "client")
    private List<Post> posts;
}
