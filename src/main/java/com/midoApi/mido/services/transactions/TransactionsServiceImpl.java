package com.midoApi.mido.services.transactions;

import com.midoApi.mido.VO.PixVO;
import com.midoApi.mido.VO.TedVO;
import com.midoApi.mido.VO.TransactionFormVO;
import com.midoApi.mido.VO.WithdrawVO;
import com.midoApi.mido.models.Cliente;
import com.midoApi.mido.models.Saldo;
import com.midoApi.mido.models.Transactions;
import com.midoApi.mido.repositories.ClienteRepository;
import com.midoApi.mido.repositories.TransactionsRepository;
import com.midoApi.mido.services.saldo.SaldoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    SaldoServiceImpl saldoService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Override
    public void withdraw(WithdrawVO form, Authentication authentication) throws Exception {

        Cliente cliente  = clienteRepository.findByNumeroConta(Long.valueOf(authentication.getName()));

        saldoService.decreaseSaldo(cliente, form.getValue());

        saveTransaction(form, cliente);
    }

    @Override
    public void ted(TedVO form, Authentication auth) throws Exception {
    	
        Cliente recieverCliente = clienteRepository.findByNumeroConta(form.numeroConta);
        Cliente senderCliente = clienteRepository.findByNumeroConta(Long.valueOf(auth.getName()));
        
        if (recieverCliente.getId().equals(senderCliente.getId())) {
        	
        	throw new Exception("Você não pode enviar uma transferencia para você mesmo.");
        }
        
        
        form.setTedType(true);
        saldoService.increaseSaldo(recieverCliente, form.getValue());
        saveTransaction(form, recieverCliente);


        form.setTedType(false);
        saldoService.decreaseSaldo(senderCliente, form.getValue());
        saveTransaction(form, senderCliente);
    }

    @Override
    public void pix(PixVO form, Authentication auth) throws Exception {

        Cliente recieverCliente = clienteRepository.findByChavePix(form.getChavePix());
        Cliente senderCliente = clienteRepository.findByNumeroConta(Long.valueOf(auth.getName()));

		if (recieverCliente.getId().equals(senderCliente.getId())) {

			throw new Exception("Você não pode enviar uma transferencia para você mesmo.");
		}
        
        
        form.setPixType(true);
        saldoService.increaseSaldo(recieverCliente, form.getValue());
        saveTransaction(form, recieverCliente);

        form.setPixType(false);
        saldoService.decreaseSaldo(senderCliente, form.getValue());
        saveTransaction(form, senderCliente);

    }

    @Override
    public List<Transactions> findAll(Authentication auth) throws Exception {
        Cliente cliente = clienteRepository.findByNumeroConta( Long.valueOf(auth.getName()));
        return transactionsRepository.findByClienteId(cliente.getId());
    }

    @Override
    public void saveTransaction(TransactionFormVO transaction, Cliente cliente) {
        Transactions newTransaction = new Transactions(
                    cliente,
                    transaction.getValue(),
                    transaction.getTpMov(),
                    transaction.getInOutFlag()
                );
        transactionsRepository.save(newTransaction);

    }


}
