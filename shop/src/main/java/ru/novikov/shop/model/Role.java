package ru.novikov.shop.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "t_role")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Role implements GrantedAuthority {

    @Id
    private Long id;

    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(Long id){
        this.id = id;
    }
    public Role(Long id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}