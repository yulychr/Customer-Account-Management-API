package com.corebankingsystem.AccountMs.service;

import com.corebankingsystem.AccountMs.model.entity.Account;
import com.corebankingsystem.AccountMs.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountId(Long id) {
        return accountRepository.findById(id);
    }


    @Transactional
    public Account deposit(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        account.deposit(amount);
        return accountRepository.save(account);
    }

    @Transactional
    public Account withdraw(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        account.withdraw(amount);
        return account;
    }


    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    //Metodo para generar el numero de cuenta
    private String generateAccountNumber() {
        Random random = new Random();
        int number = 100000 + random.nextInt(900000); // Genera un número entre 100000 y 999999
        return String.valueOf(number);
    }

    // Metodo para crear una nueva cuenta
    @Transactional
    public Account createAccount(double balance, Account.TypeAccount typeAccount, long customerId) {
        // Verificar que no existe una cuenta con el mismo número
        String accountNumber = generateAccountNumber();
        while (accountRepository.existsByAccountNumber(accountNumber)) {
            accountNumber = generateAccountNumber(); // Genera un nuevo número si ya existe
        }
        // Crear la cuenta
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        account.setTypeAccount(typeAccount);
        account.setCustomerId(customerId);
        // Guardar la cuenta en la base de datos
        return accountRepository.save(account);
    }



}
