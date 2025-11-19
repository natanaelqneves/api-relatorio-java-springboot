package com.nqn.apideservico.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email; //

    @Column(unique = true, nullable = false)
    private String matricula; //

    @Column(unique = true, nullable = false)
    private String nomeDeUsuario; // Usaremos 'username' como login

    @Column(nullable = false)
    private String senha; // Senha criptografada

    // Métodos de UserDetails (simplificados para o escopo)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Usuario() {
    }

    public Usuario(Long id, String email, String matricula, String username, String password) {
        this.id = id;
        this.email = email;
        this.matricula = matricula;
        this.nomeDeUsuario = username;
        this.senha = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String getUsername() {
        return nomeDeUsuario;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}