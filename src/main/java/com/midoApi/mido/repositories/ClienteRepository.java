package com.midoApi.mido.repositories;

import com.midoApi.mido.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID>, JpaSpecificationExecutor<Cliente> {
    Cliente findByCpf (String cpf);

    Cliente findByNumeroConta(Long numeroConta);

    Cliente findByChavePix(String chavePix);
}
