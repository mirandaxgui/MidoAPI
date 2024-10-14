package com.midoApi.mido.services.cliente;

import com.midoApi.mido.VO.CreatePixKeyVO;
import com.midoApi.mido.models.Cliente;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {

    Cliente getClienteByCpf (String cpf);


    Cliente getClienteByNumeroConta( Authentication auth);
    Cliente createChavePix (CreatePixKeyVO form, Authentication auth) throws Exception;
}
