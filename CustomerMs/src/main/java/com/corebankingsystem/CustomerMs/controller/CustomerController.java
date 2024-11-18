package com.corebankingsystem.CustomerMs.controller;

import com.corebankingsystem.CustomerMs.model.entity.Customer;
import com.corebankingsystem.CustomerMs.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //Post - Create a new customer
    @PostMapping ("/clientes")
    public ResponseEntity<Object> crateCustomer(@Valid @RequestBody Customer customer) {
        Map<String, String> errors = new HashMap<>();

        // Validación manual de cada campo

        // Validar firstName
        if (customer.getFirstName() == null || customer.getFirstName().isEmpty()) {
            errors.put("firstName", "El nombre es obligatorio");
        }

        // Validar lastName
        if (customer.getLastName() == null || customer.getLastName().isEmpty()) {
            errors.put("lastName", "El apellido es obligatorio");
        }

        // Validar DNI
        if (customer.getDni() == null || customer.getDni().isEmpty()) {
            errors.put("dni", "El DNI es obligatorio");
        } else if (!Pattern.matches("\\d{8}", customer.getDni())) {
            errors.put("dni", "El DNI debe tener 8 dígitos");
        }

        // Validar email
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            errors.put("email", "El email es obligatorio");
        } else if (!customer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.put("email", "Formato de correo electrónico inválido");
        }

        // Si existen errores de validación, devolverlos
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }


        Optional<Customer> dni_already_exists = customerService.getCustomerDni(customer.getDni());
        if (dni_already_exists.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "El DNI ya existe");
            return ResponseEntity.status(409).body(response);
        }
        Customer customerCreated = customerService.savedCustomer(customer);
        return ResponseEntity.status(201).body(customerCreated);
    }

    //Retrieves a list of all customers in the system.
    @GetMapping ("/clientes")
        public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return ResponseEntity.status(200).body(customers);
    }

    // Retrieve a customer details by id
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Customer> getCustomerId(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerId(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());
    }

    //Updates the details of an existing customer identified by the `id` provided in the path.
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer) {
        if (customerService.getCustomerId(id).isPresent()) {
            customer.setId(id);
            Customer customerUpdate = customerService.savedCustomer(customer);
            return ResponseEntity.status(200).body(customerUpdate);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerId(id);
        if (customer.isPresent()) {
            if (!customer.get().getAccounts().isEmpty()) {
                String message = "This customer cannot be deleted, customer has active accounts.";
                return ResponseEntity.status(409).body(message);
            }
            customerService.deleteCustomer(id);
            String message = "Customer successfully deleted";
            return ResponseEntity.status(200).body(message);
        }
        String message = "Customer not found";
        return ResponseEntity.status(404).body(message);
    }

}
