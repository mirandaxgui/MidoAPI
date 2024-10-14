package com.midoApi.mido.repositories;

import com.midoApi.mido.models.Saldo;
import com.midoApi.mido.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface TransactionsRepository extends JpaRepository<Transactions, UUID>,
        JpaSpecificationExecutor<Transactions> {

    List<Transactions> findByClienteId(UUID clienteId);
}
