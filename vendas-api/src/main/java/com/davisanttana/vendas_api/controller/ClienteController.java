package com.davisanttana.vendas_api.controller;


import com.davisanttana.vendas_api.model.Cliente;
import com.davisanttana.vendas_api.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente criarClientes(@Valid @RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

}
