package com.midoApi.mido.services.cliente;

import com.midoApi.mido.VO.CreateClienteVO;
import com.midoApi.mido.models.Cliente;
import org.springframework.stereotype.Service;

@Service
public interface CreateClienteService {

    Cliente create (CreateClienteVO form) throws Exception;
}
