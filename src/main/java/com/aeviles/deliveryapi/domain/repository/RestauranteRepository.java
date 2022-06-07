package com.aeviles.deliveryapi.domain.repository;

import com.aeviles.deliveryapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    //buscar pela taxaFrete, só que eu quero que nosso metodos receba dois BigDecimal inicial e final, e filtrar apenas
    // por restaurantes que tenham a taxa frete entre a faixa inicial e final
    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);



}
