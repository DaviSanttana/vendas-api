package com.davisanttana.vendas_api.repository;

import com.davisanttana.vendas_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
