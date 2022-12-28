package com.devsuperior.dscliente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ClientDto {

    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String cpf;
    private String income;
    @PastOrPresent(message = "Birth date cannot be in the future")
    private LocalDate birthDate;
    private Integer children;

    public ClientDto(Long id, String name, String cpf, String income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
