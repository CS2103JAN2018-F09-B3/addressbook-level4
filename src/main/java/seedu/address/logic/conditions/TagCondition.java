package seedu.address.logic.conditions;

import static seedu.address.logic.parser.TokenType.TAG_PREFIX;

import java.util.function.Predicate;

import seedu.address.logic.parser.TokenType;
import seedu.address.model.coin.Coin;
import seedu.address.model.tag.Tag;

//@@author Eldon-Chung
/**
 * Represents a predicate that evaluates to true when a {@Coin} contains the {@tag} specified.
 */
public class TagCondition implements Predicate<Coin> {

    public static final TokenType PREFIX = TAG_PREFIX;

    private Tag tag;

    public TagCondition(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Coin coin) {
        return coin.getTags().contains(tag);
    }
}
