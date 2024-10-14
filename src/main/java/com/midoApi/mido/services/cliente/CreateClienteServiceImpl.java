package com.midoApi.mido.services.cliente;

import com.midoApi.mido.VO.CreateClienteVO;
import com.midoApi.mido.models.Cliente;
import com.midoApi.mido.repositories.ClienteRepository;
import com.midoApi.mido.repositories.SaldoRepository;
import com.midoApi.mido.services.saldo.SaldoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class CreateClienteServiceImpl implements CreateClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteServiceImpl clienteService;
    @Autowired
    SaldoServiceImpl saldoService;

    private BCryptPasswordEncoder passwordEncoder()  {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Cliente create(CreateClienteVO cliente) throws Exception {
        Cliente existsCliente = clienteService.getClienteByCpf(cliente.getCpf());


        if (!Objects.isNull(existsCliente)) {
            throw new Exception ("Cliente already exisits");
        }

        cliente.setPassword(passwordEncoder().encode(cliente.getPassword()));

        Cliente newCliente = new Cliente (cliente);

        clienteRepository.save(newCliente);

        saldoService.createSaldo(newCliente);

        return newCliente;

    }
}
