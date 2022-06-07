package com.aeviles.deliveryapi.domain.repository;

import com.aeviles.deliveryapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository  extends JpaRepository< Cozinha, Long> {


//Personalizando método
    List<Cozinha> findTodasBynome(String nome);

    //retornando apenas uma cozinha
   Optional <Cozinha> findByNome(String nome);
}
