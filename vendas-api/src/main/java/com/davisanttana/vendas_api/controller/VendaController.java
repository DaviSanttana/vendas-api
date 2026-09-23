package com.davisanttana.vendas_api.controller;


import com.davisanttana.vendas_api.model.Cliente;
import com.davisanttana.vendas_api.model.Produto;
import com.davisanttana.vendas_api.model.Venda;
import com.davisanttana.vendas_api.repository.ClienteRepository;
import com.davisanttana.vendas_api.repository.ProdutoRepository;
import com.davisanttana.vendas_api.repository.VendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {
    @Autowired
    private VendasRepository vendasRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Venda> listarVendas() {
        return vendasRepository.findAll();
    }


    @PostMapping
    public ResponseEntity<Venda> realizarVenda(@RequestBody VendaRequestDTO request) {
        if (request.clienteId() == null || request.produtosIds() == null || request.produtosIds().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + request.clienteId()));

        List<Produto> produtos = produtoRepository.findAllById(request.produtosIds());

        if (produtos.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        BigDecimal valorTotal = produtos.stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setProdutos(produtos);
        venda.setValorTotal(valorTotal);

        Venda novaVenda = vendasRepository.save(venda);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
    }
}
