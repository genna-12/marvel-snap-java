package com.marvelsnap.util;

import com.marvelsnap.model.BasicCard;
import com.marvelsnap.model.Card;
import java.util.ArrayList;
import java.util.List;

public class CardFactory {
    public List<Card> createDeck(DeckType deckType) {
        return new ArrayList<>();
    }

    public Card createCard(String id) {
        return new BasicCard(0, "Default", 0, 0, "Default Card");
    }
}
