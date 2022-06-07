package com.aeviles.deliveryapi.controller;

import com.aeviles.deliveryapi.domain.model.Cozinha;
import com.aeviles.deliveryapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste") //a requisição será realizada/teste/cozinhas/por-nome
public class TesteControler {

        @Autowired
        private CozinhaRepository cozinhaRepository;

        @GetMapping("/cozinhas/por-nome")
        public List<Cozinha> cozinhasPorNome( String nome){
            return cozinhaRepository.findTodasBynome(nome);
        }

        @GetMapping("/cozinhas/unica-por-nome")
       public Optional<Cozinha> cozinhaPorNome(String nome){
                return cozinhaRepository.findByNome(nome);
        }



}
