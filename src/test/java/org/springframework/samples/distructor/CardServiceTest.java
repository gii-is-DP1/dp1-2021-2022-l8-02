package org.springframework.samples.distructor;

import static org.junit.Assert.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.hamcrest.core.Is;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.endofline.card.Card;
import org.springframework.samples.endofline.card.CardService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CardServiceTest {

    @Autowired
    private CardService cardService;

    @Transactional
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    public void autoColorAssignInitCardsTest(int numPlayers) {

        List<Card> cards = cardService.autoColorAssignInitCards(numPlayers);
        assertThat("The number of initial cards must be " + numPlayers, cards.size(), Is.is(numPlayers));

    }
    
}
