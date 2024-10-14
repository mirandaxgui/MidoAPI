package com.midoApi.mido.controllers;

import com.midoApi.mido.VO.LoginVO;
import com.midoApi.mido.models.Cliente;
import com.midoApi.mido.security.MidoUsernamePwdAuthenticationProvider;
import com.midoApi.mido.services.cliente.ClienteServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class LoginController {

    @Autowired
    ClienteServiceImpl clienteService;

    @Autowired
    MidoUsernamePwdAuthenticationProvider authenticationProvider;

    @RequestMapping(value = "/login")
    public ClienteAuthentication login(@RequestBody LoginVO form) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Authentication authentication = new UsernamePasswordAuthenticationToken(form.getNumeroConta(), form.getPassword(), authorities);

        Authentication authenticatedUser = authenticationProvider.authenticate(authentication);

        Cliente cliente = clienteService.getClienteByNumeroConta(authenticatedUser);

        return new ClienteAuthentication(authentication, cliente);
    }
}


@Getter
@Setter
@AllArgsConstructor
class ClienteAuthentication {
    private Authentication authentication;

    private Cliente cliente;



}