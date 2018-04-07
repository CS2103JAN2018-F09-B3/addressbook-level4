package seedu.address.logic.conditions;

import static seedu.address.logic.parser.TokenType.SOLD_PREFIX;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import seedu.address.logic.parser.TokenType;
import seedu.address.model.coin.Amount;
import seedu.address.model.coin.Coin;

//@@author Eldon-Chung
/**
 * Represents a predicate that evaluates to true when the amount bought of a {@Coin} is either greater than or less than
 * (depending on the amount comparator) the amount specified.
 */
public class DollarsSoldCondition implements Predicate<Coin> {

    public static final TokenType PREFIX = SOLD_PREFIX;

    private BiPredicate<Amount, Amount> amountComparator;
    private Amount amount;

    @Override
    public boolean test(Coin coin) {
        return amountComparator.test(coin.getTotalAmountSold(), amount);
    }
}
