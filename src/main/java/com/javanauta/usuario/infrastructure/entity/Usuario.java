package com.javanauta.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "usuario")

public class Usuario implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nome", length = 100)
    private String nome;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "senha")
    private String senha;
    //Usar @OneToMany para que possa  colocar um usuário para muitos endereços e @OneToOne 1 usuário para 1 endereço
    @OneToMany(cascade = CascadeType.ALL)
    //JoinColumn tras o nome que vai constar na tabela, e vai Identificar que o endereço pertence a tal usuário
    @JoinColumn (name = "usuario_id", referencedColumnName = "id")

    private List<Endereco> enderecos;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")


    private List<Telefone> telefones;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

}
