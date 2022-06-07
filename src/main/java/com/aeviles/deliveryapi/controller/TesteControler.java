package com.aeviles.deliveryapi.controller;

import com.aeviles.deliveryapi.domain.model.Cozinha;
import com.aeviles.deliveryapi.domain.model.Restaurante;
import com.aeviles.deliveryapi.domain.repository.CozinhaRepository;
import com.aeviles.deliveryapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste") //a requisição será realizada/teste/cozinhas/por-nome
public class TesteControler {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(String nome) {
        return cozinhaRepository.findTodasBynome(nome);
    }


    @GetMapping("/cozinhas/por-nome-contain")
    public List<Cozinha> findAllByNomeContaining(String nome) {
        return cozinhaRepository.findAllByNomeContaining(nome);
    }


    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> cozinhaPorNome(String nome) {
        return cozinhaRepository.findByNome(nome);
    }


    //geralmente utilizado na regra de negócios , as vezes quer checar se registro com determinado nome existe
    //localhost:8080/teste/cozinhas/exists?nome=Tailandesa
    @GetMapping("/cozinhas/exists")
    public boolean cozinhaExists(String nome) {
        return cozinhaRepository.existsByNome(nome);
    }



    /*Testando novos metodos Restaurantes*/

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(
            BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantesContemCozinha(String nome, Long cozinhaId) {
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }

    //localhost:8080/teste/restaurantes/primeiro-por-nome?nome=t
    //restaurant0_ where restaurant0_.nome like ? escape ? limit

    @GetMapping("/restaurantes/primeiro-por-nome")
    public Optional<Restaurante> restaurantesPrimeiroPorNome(String nome) {
        return restauranteRepository.findFirstByNomeContaining(nome);


    }

    //localhost:8080/teste/restaurantes/top2-por-nome?nome=t  -> 2 nomes
    @GetMapping("/restaurantes/top2-por-nome")
    public List<Restaurante> restaurantesTop2PorNome(String nome) {
        return restauranteRepository.findTop2ByNomeContaining(nome);


    }


    //localhost:8080/teste/restaurantes/top3-por-nome?nome=t  -> 3 nomes
    @GetMapping("/restaurantes/top3-por-nome")
    public List<Restaurante> restaurantesTop3PorNome(String nome) {
        return restauranteRepository.findTop3ByNomeContaining(nome);


    }

    //localhost:8080/teste/restaurantes/count-por-cozinha?cozinhaId=1
    @GetMapping("/restaurantes/count-por-cozinha")
    public int restaurantesCountPorCozinha(Long cozinhaId) {
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }


}