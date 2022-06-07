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
        public List<Cozinha> cozinhasPorNome( String nome){
            return cozinhaRepository.findTodasBynome(nome);
        }


        @GetMapping("/cozinhas/por-nome-contain")
        public List<Cozinha> findAllByNomeContaining( String nome){
            return cozinhaRepository.findAllByNomeContaining(nome);
        }


        @GetMapping("/cozinhas/unica-por-nome")
       public Optional<Cozinha> cozinhaPorNome(String nome){
                return cozinhaRepository.findByNome(nome);
        }


        /*Testando novos metodos Restaurantes*/

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(
            BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    //localhost:8080/teste/restaurantes/por-nome?nome=d&cozinhaId=1 -> POSTMAN
    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantesContemCozinha(String nome, Long cozinhaId) {
        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
    }




}
