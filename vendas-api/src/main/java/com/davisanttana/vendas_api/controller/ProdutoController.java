package com.davisanttana.vendas_api.controller;


import com.davisanttana.vendas_api.model.Produto;
import com.davisanttana.vendas_api.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {


    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> criarProdutos(@Valid @RequestBody Produto produto) {
        Produto novoProduto = produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarTotal(
            @PathVariable Long id,
            @Valid @RequestBody Produto produtoAtualizado) {

        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    produtoExistente.setNome(produtoAtualizado.getNome());
                    produtoExistente.setPreco(produtoAtualizado.getPreco());
                    produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
                    Produto salvo = produtoRepository.save(produtoExistente);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Produto> atualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos) {

        return produtoRepository.findById(id)
                .map(produtoExistente -> {
                    if (campos.containsKey("nome")) {
                        produtoExistente.setNome((String) campos.get("nome"));
                    }
                    if (campos.containsKey("preco")) {
                        produtoExistente.setPreco(new BigDecimal(campos.get("preco").toString()));
                    }
                    if (campos.containsKey("quantidadeEstoque")) {
                        produtoExistente.setQuantidadeEstoque((Integer) campos.get("quantidadeEstoque"));
                    }

                    Produto salvo = produtoRepository.save(produtoExistente);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}