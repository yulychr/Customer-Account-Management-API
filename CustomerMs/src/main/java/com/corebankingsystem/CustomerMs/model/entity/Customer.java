package com.corebankingsystem.CustomerMs.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstName", nullable = false)
    @NotEmpty(message = "El nombre es obligatorio")
    private String firstName;

    @Column(name="lastName", nullable = false)
    @NotEmpty(message = "El apellido es obligatorio")
    private String lastName;

    @Column(name="dni", unique = true, nullable = false)
    @NotEmpty(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
    private String dni;

    @Column(name="email", nullable = false)
    @Email(message = "Formato de correo electrónico inválido")
    @NotEmpty(message = "El email es obligatorio")
    private String email;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();

    //Metodo para hace una lita de las id de las cuentas
    public List<Long> getAccountIds() {
        return accounts.stream()
                .map(Account::getId)
                .collect(Collectors.toList());
    }

}
