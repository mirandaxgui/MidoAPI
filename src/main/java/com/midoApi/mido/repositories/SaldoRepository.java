package com.midoApi.mido.repositories;

import com.midoApi.mido.models.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, UUID>, JpaSpecificationExecutor<Saldo> {

    Saldo findByClienteId(UUID cliente_id);

}
