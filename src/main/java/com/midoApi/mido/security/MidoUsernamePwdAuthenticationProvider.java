package com.midoApi.mido.security;

import com.midoApi.mido.models.Cliente;
import com.midoApi.mido.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class MidoUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Long numeroConta =  Long.valueOf(authentication.getName()) ;
        String password = authentication.getCredentials().toString();
        Cliente cliente = clienteRepository.findByNumeroConta(numeroConta);

        if (!Objects.isNull(cliente)) {
            if (passwordEncoder.matches(password, cliente.getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();

                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

                return new UsernamePasswordAuthenticationToken(String.valueOf(numeroConta), password, authorities);
            } else {
                throw new BadCredentialsException("Invalid password");
            }
        } else {
            throw new BadCredentialsException("User not found");
        }


    }
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
