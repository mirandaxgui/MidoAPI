package com.midoApi.mido.services.saldo;

import com.midoApi.mido.models.Cliente;
import com.midoApi.mido.models.Saldo;
import com.midoApi.mido.repositories.SaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class SaldoServiceImpl implements SaldoService {

    @Autowired
    SaldoRepository saldoRepository;
    @Override
    public void createSaldo(Cliente cliente) {

        Saldo saldo = new Saldo(cliente, new BigDecimal(0));

        saldoRepository.save(saldo);
    }
    @Override
    public void decreaseSaldo(Cliente cliente, BigDecimal value) throws Exception {

        Saldo saldo = saldoRepository.findByClienteId(cliente.getId());

        System.out.println(saldo.getSaldo().compareTo(value));

        if (saldo.getSaldo().compareTo(value) < 0) {
            throw new Exception("O saldo é menor que a transação.");

        }

        if (saldo.getSaldo().equals(BigDecimal.valueOf(0))) {
            throw new Exception("O saldo do cliente já é igual a zero.");
        }

        saldo.setSaldo(saldo.getSaldo().subtract(value));

        saldoRepository.save(saldo);
    }
    @Override
    public void increaseSaldo(Cliente cliente, BigDecimal value) {

        Saldo saldo = saldoRepository.findByClienteId(cliente.getId());

        saldo.setSaldo(saldo.getSaldo().add(value));

        saldoRepository.save(saldo);

    }

    @Override
    public Saldo getSaldo(UUID id) {
        return saldoRepository.findByClienteId(id);
    }


}
