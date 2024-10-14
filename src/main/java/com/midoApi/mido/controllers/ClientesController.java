package com.midoApi.mido.controllers;

import com.midoApi.mido.VO.CreateClienteVO;
import com.midoApi.mido.VO.CreatePixKeyVO;
import com.midoApi.mido.models.Cliente;
import com.midoApi.mido.services.cliente.ClienteServiceImpl;
import com.midoApi.mido.services.cliente.CreateClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(maxAge = 3600, allowedHeaders = "*")
public class ClientesController {
    @Autowired
    CreateClienteServiceImpl createClienteService;

    @Autowired
    ClienteServiceImpl clienteService;

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<?> createCliente (@RequestBody CreateClienteVO form) {
        try {
            Cliente cliente = createClienteService.create(form);

            return ResponseEntity.ok().body(cliente);

        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PostMapping(value = "/createPixKey", consumes = "application/json")
    public ResponseEntity<?> createPixKey (@RequestBody CreatePixKeyVO form, Authentication auth) throws Exception {
            Cliente cliente = clienteService.createChavePix(form, auth);

            return ResponseEntity.ok("Nova chave pix registrada com sucesso \n" + cliente.getChavePix());
    }
}
