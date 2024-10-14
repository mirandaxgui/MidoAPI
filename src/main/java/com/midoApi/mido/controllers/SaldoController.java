package com.midoApi.mido.controllers;

import com.midoApi.mido.models.Cliente;
import com.midoApi.mido.models.Saldo;
import com.midoApi.mido.services.cliente.ClienteService;
import com.midoApi.mido.services.cliente.ClienteServiceImpl;
import com.midoApi.mido.services.saldo.SaldoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/saldo")
@CrossOrigin(maxAge = 3600, origins = "*")
public class SaldoController
{

    @Autowired
    ClienteServiceImpl clienteService;

    @Autowired
    SaldoServiceImpl saldoService;

    @GetMapping(value = "/find")
    public BigDecimal findByCliente (Authentication authentication) throws Exception {

        Cliente cliente = clienteService.getClienteByNumeroConta(authentication);

        return saldoService.getSaldo(cliente.getId()).getSaldo();
    }

}
