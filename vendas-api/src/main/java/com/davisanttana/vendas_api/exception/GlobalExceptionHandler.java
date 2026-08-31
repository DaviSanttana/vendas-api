package com.davisanttana.vendas_api.exception;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, List<String>> lidarComErrosDeValidacao(MethodArgumentNotValidException ex) {
        Map<String, List<String>> erros = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));

        ex.getBindingResult().getGlobalErrors().forEach(erroGlobal ->
                erros.computeIfAbsent(erroGlobal.getObjectName(), k -> new ArrayList<>())
                        .add(erroGlobal.getDefaultMessage())
        );

        return erros;
    }


    @ExceptionHandler(CpfDuplicadoException.class)
    public ResponseEntity<Map<String, String>> lidarComCpfDuplicado(CpfDuplicadoException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("cpf", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> lidarComViolacaoDeIntegridade(DataIntegrityViolationException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", "Não foi possível salvar: dado duplicado ou restrição do banco violada.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

}
