package com.davisanttana.vendas_api.controller;

import java.util.List;

public record VendaRequestDTO (Long clienteId, List<Long> produtosIds) {
}
