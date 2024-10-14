package com.midoApi.mido.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
@Entity(name = "Transactions")
@Table(name = "Transactions", schema = "mido")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Cliente cliente;

    private BigDecimal vlMov;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dtMov;

    private String inOutFlag;

    private String tpMov;


    public Transactions(Cliente cliente, BigDecimal vlMov, String tpMov, String inOutFlag) {
        this.cliente = cliente;

        this.dtMov = new Timestamp(System.currentTimeMillis());

        this.inOutFlag = inOutFlag;

        this.tpMov = tpMov;

        this.vlMov = vlMov;
    }

}
