package com.midoApi.mido.services.cliente;

import com.midoApi.mido.VO.CreatePixKeyVO;
import com.midoApi.mido.models.Cliente;
import com.midoApi.mido.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Cliente getClienteByCpf (String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);

        if (Objects.isNull(cliente)) {
            return null;
        }

        return cliente;
    }

    @Override
    public Cliente getClienteByNumeroConta(Authentication auth) {

        return clienteRepository.findByNumeroConta(Long.valueOf(auth.getName()));
    }

    @Override
    public Cliente createChavePix(CreatePixKeyVO form, Authentication auth) throws Exception {
        Cliente cliente = clienteRepository.findByNumeroConta(Long.valueOf(auth.getName()));
        
        if (!cliente.getChavePix().isEmpty()) {
        	throw new Exception("O cliente já tem uma chave pix cadastrada.");
        }
        
        cliente.setChavePix(form.getChavePix());

        return clienteRepository.save(cliente);
    }

}
