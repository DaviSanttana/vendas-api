package com.davisanttana.vendas_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String nome;


    @NotBlank(message = "O CPF é obrigatório")
    @Column(nullable = false, unique = true)
    private String cpf;

    private String email;

    private String telefone;




}
