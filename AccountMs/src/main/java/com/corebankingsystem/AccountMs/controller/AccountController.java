package com.corebankingsystem.AccountMs.controller;

import com.corebankingsystem.AccountMs.model.entity.Account;
import com.corebankingsystem.AccountMs.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping ("/cuentas")
    @ResponseStatus(HttpStatus.CREATED) // Respuesta 201 cuando se crea la cuenta
    public Account createAccount(@Valid @RequestBody Account account) {
        // Llamar al servicio para crear la cuenta
        return accountService.createAccount(
                account.getBalance(),
                account.getTypeAccount(),
                account.getCustomerId()
        );
    }

    @GetMapping ("/cuentas")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAccounts();
        return ResponseEntity.status(200).body(accounts);
    }

    @GetMapping("/cuentas/{id}")
    public ResponseEntity<Account> getAccountId(@PathVariable Long id) {
        Optional<Account> account = accountService.getAccountId(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/cuentas/{id}/depositar")
    public ResponseEntity<Object> deposit(@PathVariable Long id, @RequestParam Double amount) {
        Optional<Account> account = accountService.getAccountId(id);
        if (account.isPresent()){
            if(amount>0){
                Account updateAccount = accountService.deposit(id, amount);
                return ResponseEntity.status(200).body(updateAccount);
            }
            String message = "Invalid deposit amount. Amount must be positive";
            return ResponseEntity.status(400).body(message);
        }
        String message = "The account ID does not exist or is invalid";
        return ResponseEntity.status(404).body(message);
    }

    @PutMapping("/cuentas/{id}/retirar")
    public ResponseEntity<Object> withdraw(@PathVariable Long id, @RequestParam Double amount) {
        Optional<Account> account = accountService.getAccountId(id);
        if (account.isPresent()){
            try {
                Account updateAccount = accountService.withdraw(id, amount);
                return ResponseEntity.status(200).body(updateAccount);
            } catch (IllegalArgumentException e){
                return ResponseEntity.status(400).body(e.getMessage());
            }
        }
        String message = "The account ID does not exist or is invalid.";
        return ResponseEntity.status(404).body(message);
    }

    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<Object> deleteAccountId(@PathVariable Long id) {
        Optional<Account> account = accountService.getAccountId(id);
        if (account.isPresent()){
            accountService.deleteAccount(id);
            String message = "Account successfully deleted";
            return ResponseEntity.status(200).body(message);
        }
        String message = "Account not found";
        return ResponseEntity.status(404).body(message);
    }
}
