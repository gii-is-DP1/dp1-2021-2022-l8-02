package org.springframework.samples.distructor;

import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.transaction.Transactional;

import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.Every;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.endofline.card.CardColor;
import org.springframework.samples.endofline.card.Deck;
import org.springframework.samples.endofline.card.DeckService;
import org.springframework.samples.endofline.usuario.Usuario;
import org.springframework.samples.endofline.usuario.UsuarioService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class DeckServiceTest {

    @Autowired
    private DeckService deckService;

    @Autowired
    private UsuarioService userService;

    @Test
    @Transactional
    public void generateDefaultDeckTest() {
        Usuario player = userService.findByUsername("Juangr4").orElse(null);
        assumeNotNull(player);
        
        Deck deck = deckService.generateDefaultDeck(player, CardColor.RED);

        assertThat("The deck must have 25 cards.", deck.getCards().size(), Is.is(25));
        assertThat("The deck owner expected to be Juangr4", deck.getUser(), HasPropertyWithValue.hasProperty("username", Is.is("Juangr4")));
        assertThat("Not the same color.", deck.getCards(), Every.everyItem(HasPropertyWithValue.hasProperty("color", Is.is(CardColor.RED))));
        
    }

    @Test
    @Transactional
    public void getDeckFromPlayerTest() {
        //TODO: replace assertNull and assertNotNull to assertThat (AssertJ)
        Usuario player = userService.findByUsername("Juangr4").orElse(null);
        assumeNotNull(player);

        Deck deck = deckService.getDeckFromPlayer(player);
        // assertThat(deck, isNotNull());
        assertNull(deck);

        deckService.generateDefaultDeck(player, CardColor.BLUE);

        deck = deckService.getDeckFromPlayer(player);

        // assertThat("The player must have a deck now", deck, isNotNull());
        assertNotNull(deck);

    }
    
}
