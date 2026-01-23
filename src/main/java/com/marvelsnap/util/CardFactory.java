package com.marvelsnap.util;

import java.util.ArrayList;
import java.util.List;

import com.marvelsnap.model.BasicCard;
import com.marvelsnap.model.Card;

public class CardFactory {
    public List<Card> createDeck(DeckType deckType) {
        return new ArrayList<>();
    }

    public Card createCard(String id) {
        return new BasicCard(0, "Default", 0, 0, "Default Card");
    }
}
