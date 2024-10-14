package com.midoApi.mido.models;


import com.midoApi.mido.VO.CreateClienteVO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenerationTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity(name = "Cliente")
@Table(name = "Cliente", uniqueConstraints = {@UniqueConstraint(columnNames = {"numeroConta"})}, schema = "mido")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente  {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    private String cpf;

    private String email;

    @Column(columnDefinition = "serial")
    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    private Long numeroConta;

    private Date dtNascimento;

    private String telefone;

    private String chavePix;

    private String password;

    private Integer RG;

    public Cliente(CreateClienteVO cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.RG = cliente.getRG();
        this.dtNascimento = cliente.getDtNascimento();
        this.telefone = cliente.getTelefone();
        this.password = cliente.getPassword();
    }

}
