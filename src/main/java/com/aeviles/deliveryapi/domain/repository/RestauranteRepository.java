package com.aeviles.deliveryapi.domain.repository;

import com.aeviles.deliveryapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    //consultarPorNome
    //localhost:8080/teste/restaurantes/por-nome?nome=v&cozinhaId=1
    //@Query("select u from restaurante u where u.nome like %:nome% and cozinha.id = :id") //:if-: fazer biding
    List<Restaurante> consultarPorNome(@Param("nome") String nome,@Param("id") Long cozinha);//@Param(id) é o nome do pametro que quero fazer o biding
                                                                        //o biding poderia ser automatico no caso do is=d. se eu colocasse :cozinha

   //	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
    Optional<Restaurante> findFirstByNomeContaining(String nome);


    List<Restaurante> findTop2ByNomeContaining(String nome);


    List<Restaurante> findTop3ByNomeContaining(String nome);


    int countByCozinhaId(Long cozinha);

}
