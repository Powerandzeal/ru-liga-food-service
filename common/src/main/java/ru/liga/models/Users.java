package ru.liga.models;

import lombok.*;
import ru.liga.Enum.Role;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id" )
    private Long id;
    @Column(name ="username" )
    private String username;
    @Column(name ="password_hash" )
    private String password;
    @Column(name ="role" )
    @Enumerated(EnumType.STRING)
    private Role roleUser;

}
