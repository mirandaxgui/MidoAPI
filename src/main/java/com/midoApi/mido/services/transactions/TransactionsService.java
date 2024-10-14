package com.midoApi.mido.services.transactions;

import com.midoApi.mido.VO.PixVO;
import com.midoApi.mido.VO.TedVO;
import com.midoApi.mido.VO.TransactionFormVO;
import com.midoApi.mido.VO.WithdrawVO;
import com.midoApi.mido.models.Cliente;
import com.midoApi.mido.models.Transactions;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface TransactionsService {

    void withdraw (WithdrawVO form, Authentication authentication) throws Exception;

    void ted (TedVO form, Authentication auth) throws Exception;


    void saveTransaction (TransactionFormVO transaction, Cliente cliente);

    void pix(PixVO form, Authentication auth) throws Exception;

    List<Transactions> findAll(Authentication auth) throws Exception;


}
