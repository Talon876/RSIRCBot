package org.nolat.rsircbot.commands.actions;

import org.nolat.rsircbot.data.ItemSearch;
import org.nolat.rsircbot.data.LookupException;
import org.nolat.rsircbot.data.json.ItemData;

public class AlchAction extends Action {

    private static ItemSearch NATURE_RUNE;

    static {
        try {
            NATURE_RUNE = new ItemSearch("nature rune");
        } catch (LookupException e) {
            e.printStackTrace();
        }
    }

    public AlchAction(String item, ActionResponder responder) {
        super(responder);

        ItemSearch results = null;
        try {
            results = new ItemSearch(item);
        } catch (LookupException e) {
            this.responder.onResponseFailure(e.getMessage());
        }

        ItemData matchedItem = results.getMatchedItem();
        if (matchedItem == null) {
            this.responder.onResponseFailure("Search was too broad, try one of these: " + results.getSuggestionString());
        } else {
            try {
                NATURE_RUNE.refreshData();
            } catch (LookupException e) {
                e.printStackTrace();
            }

            this.responder.onResponseSuccess(
                    new AlchResponse(
                            matchedItem.getName(),
                            NATURE_RUNE.getMatchedItem().getPriceValue(),
                            matchedItem.getPriceValue(),
                            matchedItem.getHighAlchValue()
                    )
            );
        }
    }
}