package com.davisanttana.vendas_api.repository;

import com.davisanttana.vendas_api.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendasRepository extends JpaRepository<Venda, Long> {

}
