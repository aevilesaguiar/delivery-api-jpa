package com.aeviles.deliveryapi.domain.repository;

import com.aeviles.deliveryapi.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {
    //localhost:8080/teste/restaurantes/por-nome-e-frete?nome=c&taxaFreteInicial=10&taxaFreteFinal=20
    //o nome do método não tem importancia aqui
    List<Restaurante> find(String nome, BigDecimal
            taxaFreteInicial, BigDecimal taxaFreteFinal);
}
