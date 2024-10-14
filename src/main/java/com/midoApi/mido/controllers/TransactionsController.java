package com.midoApi.mido.controllers;

import com.midoApi.mido.VO.PixVO;
import com.midoApi.mido.VO.TedVO;
import com.midoApi.mido.VO.WithdrawVO;
import com.midoApi.mido.models.Transactions;
import com.midoApi.mido.services.transactions.TransactionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(maxAge = 3600, origins = "*")
public class TransactionsController {

    @Autowired
    TransactionsServiceImpl transactionsService;

    @PostMapping(value = "/withdraw", consumes = "application/json")
    public ResponseEntity<?> withdraw (@RequestBody WithdrawVO form, Authentication authentication) {
        try {
            transactionsService.withdraw(form, authentication);

            return ResponseEntity.
                    ok("O saque no valor de " + form.getValue() + "R$ foi realizado com sucesso");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/ted", consumes = "application/json")
    public ResponseEntity<?> ted (@RequestBody TedVO form, Authentication auth) {
        try {
            transactionsService.ted(form, auth);

            return ResponseEntity
                    .ok("A transferencia no valor de " + form.getValue() + "R$ foi realiza com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping(value = "/pix", consumes = "application/json")
    public ResponseEntity<?> pix (@RequestBody PixVO form, Authentication auth) {
        try {
            transactionsService.pix(form, auth);

            return ResponseEntity.ok("O pix no valor de: " + form.getValue() + "R$ foi enviado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll (Authentication authentication) throws Exception {



        return ResponseEntity.ok().body(transactionsService.findAll(authentication));
    }

}
