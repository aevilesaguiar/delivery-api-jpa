package com.aeviles.deliveryapi.infrastructure.repository;

import com.aeviles.deliveryapi.domain.model.Restaurante;

import com.aeviles.deliveryapi.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    //EntityManager é um serviço responsável por gerenciar as entidades
    @PersistenceContext
    private EntityManager manager;

    //localhost:8080/teste/restaurantes/por-nome-e-frete?nome=c&taxaFreteInicial=10&taxaFreteFinal=20
    //o nome do método não tem importancia aqui
    @Override
    public List<Restaurante> find(String nome, BigDecimal
            taxaFreteInicial, BigDecimal taxaFreteFinal){

        // Implementando uma consulta dinâmica com JPQL

        var jpql = new StringBuilder();
        jpql.append("from restaurante where 0 = 0 ");//0==0 é verdadeiro -retorna todos os restaurantes


        //mapa de String e Objects, usar o mapa para cada if adicionar no mapa qual o nome do parametro e o valor que tenho que atribuir
        //adiciona no mapa os atributos que precisa fazer biding
        var parametros = new HashMap<String, Object>();

        if(StringUtils.hasLength(nome)){//hasLength() ele verifica se está nulo ou vazio
            jpql.append("and nome like :nome "); //sempre termina cm um espaço senão dá erro na querie
            parametros.put("nome", "%"+nome+"%");
        }

        if(taxaFreteInicial!= null){
            jpql.append("and taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial", taxaFreteInicial);
        }

        if(taxaFreteFinal!= null){
            jpql.append("and taxaFrete <= :taxaFinal ");
            parametros.put("taxaFinal", taxaFreteFinal);
        }

        TypedQuery<Restaurante> query=manager.createQuery(jpql.toString(), Restaurante.class);

        //apás passar o mapa dos parametros
        //faremos um loop

        parametros.forEach(
                (chave, valor)-> query.setParameter(chave,valor)
                );

        return query.getResultList();
/*
        //var recurso que veio no java 10 para declarar variavel
        var jpql = "from restaurante where nome like :nome "
                + "and taxaFrete between :taxaInicial and :taxaFinal";

        return manager.createQuery(jpql, Restaurante.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("taxaInicial", taxaFreteInicial)
                .setParameter("taxaFinal", taxaFreteFinal)
                .getResultList();
    }*/

}}