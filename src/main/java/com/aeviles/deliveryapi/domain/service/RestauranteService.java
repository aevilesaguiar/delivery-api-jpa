package com.aeviles.deliveryapi.domain.service;


//regras de negócios de cadastro da cozinha


import com.aeviles.deliveryapi.domain.exception.EntidadeEmUsoException;
import com.aeviles.deliveryapi.domain.exception.EntidadeNaoEncontradaException;
import com.aeviles.deliveryapi.domain.model.Cozinha;
import com.aeviles.deliveryapi.domain.model.Restaurante;
import com.aeviles.deliveryapi.domain.repository.CozinhaRepository;
import com.aeviles.deliveryapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private  CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {

        Long cozinhaId = restaurante.getCozinha().getId();
        //retorna para mim a cozinha que estiver dentro do Optional
        Cozinha cozinha=cozinhaRepository.findById(cozinhaId)
                //sen~~ao tiver nada , lança uma exceção
                .orElseThrow(()-> new EntidadeNaoEncontradaException( //Op
                        String.format("Não existe cadastro de cozinha com código %d",cozinhaId)));

        //atribuir a cozinha ao restaurante
        restaurante.setCozinha(cozinha);//eu quero pegar a cozinha que está dentro do Optional


        return restauranteRepository.save(restaurante);



       /* outra forma de fazer

        Optional <Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);

        //se a cozinha está vazia?
        if(cozinha.isEmpty()){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com código %d",cozinhaId)
            );
        }
        //atribuir a cozinha ao restaurante
        restaurante.setCozinha(cozinha.get());//eu quero pegar a cozinha que está dentro do Optional


        return restauranteRepository.salvar(restaurante);*/
    }


    public void remover(Long restauranteId){

        try {
            restauranteRepository.deleteById(restauranteId);
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurantecom código %d",restauranteId));
        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Restaurante de código %d não pode ser removida, pois está em uso", restauranteId));
        }

    }





    }







