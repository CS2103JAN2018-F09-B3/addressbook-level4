package seedu.address.logic.parser;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.conditions.AmountHeldCondition;
import seedu.address.logic.conditions.CodeCondition;
import seedu.address.logic.conditions.DollarsBoughtCondition;
import seedu.address.logic.conditions.DollarsSoldCondition;
import seedu.address.logic.conditions.MadeCondition;
import seedu.address.logic.conditions.PriceCondition;
import seedu.address.logic.conditions.TagCondition;
import seedu.address.logic.conditions.WorthCondition;
import seedu.address.model.coin.Amount;
import seedu.address.model.coin.Code;
import seedu.address.model.coin.Coin;
import seedu.address.model.coin.Price;
import seedu.address.model.tag.Tag;

/**
 * Generates the predicate based ont he tokenized boolean logic statements to verify correctness.
 */
public class ConditionGenerator {

    private TokenStack tokenStack;

    public ConditionGenerator(TokenStack tokenStack) {
        this.tokenStack = tokenStack;
        tokenStack.resetStack();
    }

    /**
     * @return Generates a predicate on {@code Coin} objects based on the argument represented by the token stack.
     * @throws IllegalValueException
     */
    public Predicate<Coin> generate() throws IllegalValueException {
        return expression();
    }

    /**
     * @return Generates a predicate on {@code Coin} objects based on the current EXPRESSION. (see DeveloperGuide.adoc)
     * @throws IllegalValueException
     */
    Predicate<Coin> expression() throws IllegalValueException {
        Predicate<Coin> condition = term();
        while (tokenStack.matchTokenType(TokenType.BINARYBOOL)) {
            Token operatorToken = tokenStack.popToken();
            Predicate<Coin> secondCondition = term();
            switch (operatorToken.getPattern()) {
            case " AND ":
                condition = condition.and(secondCondition);
                break;
            case " OR ":
                condition = condition.or(secondCondition);
                break;
            default:
                break;
            }
        }
        return condition;
    }

    /**
     * @return Generates a predicate on {@code Coin} objects based on the current TERM. (see DeveloperGuide.adoc)
     * @throws IllegalValueException
     */
    Predicate<Coin> term() throws IllegalValueException {
        Predicate<Coin> condition;
        if (tokenStack.matchAndPopTokenType(TokenType.LEFTPARENTHESES)) {
            condition = expression();
            tokenStack.matchAndPopTokenType(TokenType.RIGHTPARENTHESES);
            return condition;
        } else if (tokenStack.matchAndPopTokenType(TokenType.UNARYBOOL)) {
            return term().negate();
        }
        return cond();
    }

    /**
     * @return Generates a predicate on {@code Coin} objects based on the current COND. (see DeveloperGuide.adoc)
     * @throws IllegalValueException
     */
    Predicate<Coin> cond() throws IllegalValueException {
        return getPredicateFromPrefix(tokenStack.popToken().getType());
    }

    /**
     * @param type
     * @return a base predicate based on the prefix that is currently at the top of the stack.
     * @throws IllegalValueException
     */
    Predicate<Coin> getPredicateFromPrefix(TokenType type) throws IllegalValueException {
        BiPredicate<Amount, Amount> amountComparator;
        BiPredicate<Price, Price> priceComparator;
        Double specifiedAmount;
        switch (type) {
        case PREFIX_HELD:
            amountComparator = getAmountComparatorFromToken(tokenStack.popToken());
            specifiedAmount = ParserUtil.parseDouble(tokenStack.popToken().getPattern());
            return new AmountHeldCondition(new Amount(specifiedAmount), amountComparator);

        case PREFIX_SOLD:
            amountComparator = getAmountComparatorFromToken(tokenStack.popToken());
            specifiedAmount = ParserUtil.parseDouble(tokenStack.popToken().getPattern());
            return new DollarsSoldCondition(new Amount(specifiedAmount), amountComparator);

        case PREFIX_BOUGHT:
            amountComparator = getAmountComparatorFromToken(tokenStack.popToken());
            specifiedAmount = ParserUtil.parseDouble(tokenStack.popToken().getPattern());
            return new DollarsBoughtCondition(new Amount(specifiedAmount), amountComparator);

        case PREFIX_MADE:
            amountComparator = getAmountComparatorFromToken(tokenStack.popToken());
            specifiedAmount = ParserUtil.parseDouble(tokenStack.popToken().getPattern());
            return new MadeCondition(new Amount(specifiedAmount), amountComparator);

        case PREFIX_PRICE:
            priceComparator = getPriceComparatorFromToken(tokenStack.popToken());
            specifiedAmount = ParserUtil.parseDouble(tokenStack.popToken().getPattern());
            return new PriceCondition(new Price(specifiedAmount), priceComparator);

        case PREFIX_WORTH:
            amountComparator = getAmountComparatorFromToken(tokenStack.popToken());
            specifiedAmount = ParserUtil.parseDouble(tokenStack.popToken().getPattern());
            return new WorthCondition(new Amount(specifiedAmount), amountComparator);

        case PREFIX_CODE:
            Code code = new Code(tokenStack.popToken().getPattern());
            return new CodeCondition(code);

        case PREFIX_TAG:
            Tag tag = ParserUtil.parseTag(tokenStack.popToken().getPattern());
            return new TagCondition(tag);

        default:
            assert false;
            return null;
        }
    }

    private static BiPredicate<Amount, Amount> getAmountComparatorFromToken(Token token) {
        switch (token.getPattern()) {
        case "=":
            return (amount1, amount2) -> amount1.getValue().equals(amount2.getValue());
        case ">":
            return (amount1, amount2) -> amount1.getValue() > amount2.getValue();
        case "<":
            return (amount1, amount2) -> amount1.getValue() < amount2.getValue();
        default:
            return null;
        }
    }

    private static BiPredicate<Price, Price> getPriceComparatorFromToken(Token token) {
        switch (token.getPattern()) {
        case "=":
            return (amount1, amount2) -> amount1.getValue().equals(amount2.getValue());
        case ">":
            return (amount1, amount2) -> amount1.getValue() > amount2.getValue();
        case "<":
            return (amount1, amount2) -> amount1.getValue() < amount2.getValue();
        default:
            return null;
        }
    }
}
