package com.miage.altea.tp.pokemon_ui.service;


import com.miage.altea.tp.pokemon_ui.bo.PokemonType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonTypeServiceImplTest {

    @Test
    void listPokemonsTypes_shouldCallTheRemoteService() {
        var url = "http://localhost:8080";

        var restTemplate = mock(RestTemplate.class);
        var pokemonServiceImpl = new PokemonTypeServiceImpl();
        pokemonServiceImpl.setRestTemplate(restTemplate);
        pokemonServiceImpl.setPokemonTypeServiceUrl(url);

        var pikachu = new PokemonType();
        pikachu.setName("pikachu");
        pikachu.setId(25);

        var expectedUrl = "http://localhost:8080/pokemon-types";
        when(restTemplate.getForObject(expectedUrl, PokemonType[].class)).thenReturn(new PokemonType[]{pikachu});

        var pokemons = pokemonServiceImpl.listPokemonsTypes();

        assertNotNull(pokemons);
        assertEquals(1, pokemons.size());

        verify(restTemplate).getForObject(expectedUrl, PokemonType[].class);
    }

    @Test
    void pokemonServiceImpl_shouldBeAnnotatedWithService(){
        assertNotNull(PokemonTypeServiceImpl.class.getAnnotation(Service.class));
    }

    @Test
    void setRestTemplate_shouldBeAnnotatedWithAutowired() throws NoSuchMethodException {
        var setRestTemplateMethod = PokemonTypeServiceImpl.class.getDeclaredMethod("setRestTemplate", RestTemplate.class);
        assertNotNull(setRestTemplateMethod.getAnnotation(Autowired.class));
    }

}
