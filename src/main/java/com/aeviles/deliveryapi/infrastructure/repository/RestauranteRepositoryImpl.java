package com.aeviles.deliveryapi.infrastructure.repository;

import com.aeviles.deliveryapi.domain.model.Restaurante;
import com.aeviles.deliveryapi.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl  {

    //Utilizaremos ResponseEntity por que precisaremos tratar a resposta http e fazer alguma lógica
    @PersistenceContext
    private EntityManager manager;

    //o nome do método não tem importancia aqui
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal ){

        //var recurso que veio no java 10 para declarar variavel
        var jpql ="from Restaurante where nome " +
                "like :nome anda taxaFrete :taxaInicial and " +
                ":taxaFinal";

        return manager.createQuery(jpql, Restaurante.class)
                .setParameter("nome","%"+nome+"%")
                .setParameter("taxaInicial",taxaFreteInicial)
                .setParameter("taxaFinal",taxaFreteFinal)
                .getResultList();
    }

}
