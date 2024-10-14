package com.midoApi.mido.services.saldo;

import com.midoApi.mido.models.Cliente;
import com.midoApi.mido.models.Saldo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public interface SaldoService {

    void createSaldo (Cliente cliente);

    void decreaseSaldo(Cliente cliente, BigDecimal value) throws Exception;

    void increaseSaldo(Cliente cliente, BigDecimal value);

    Saldo getSaldo (UUID id);

}
