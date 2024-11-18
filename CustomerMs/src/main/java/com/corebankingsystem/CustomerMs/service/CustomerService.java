package com.corebankingsystem.CustomerMs.service;

import com.corebankingsystem.CustomerMs.model.entity.Customer;
import com.corebankingsystem.CustomerMs.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerId(Long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> getCustomerDni(String dni) {
        return customerRepository.findByDni(dni);
    }

    public Customer savedCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
