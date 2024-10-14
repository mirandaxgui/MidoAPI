package com.midoApi.mido.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "Saldo")
@Table(name = "Saldo", schema = "mido")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Saldo {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Cliente cliente;


    private BigDecimal saldo;


    public Saldo (Cliente cliente, BigDecimal saldo) {
        this.cliente = cliente;

        this.saldo = saldo;
    }
}
