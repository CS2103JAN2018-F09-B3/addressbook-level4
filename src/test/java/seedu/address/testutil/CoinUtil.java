package seedu.address.testutil;

import static seedu.address.logic.parser.TokenType.NAME_PREFIX;
import static seedu.address.logic.parser.TokenType.TAG_PREFIX;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.coin.Coin;

/**
 * A utility class for Coin.
 */
public class CoinUtil {

    /**
     * Returns an add command string for adding the {@code coin}.
     */
    public static String getAddCommand(Coin coin) {
        return AddCommand.COMMAND_WORD + " " + getCoinDetails(coin);
    }

    /**
     * Returns an add aliased command string for adding the {@code coin}.
     */
    public static String getAddAliasCommand(Coin coin) {
        return AddCommand.COMMAND_ALIAS + " " + getCoinDetails(coin);
    }

    /**
     * Returns the part of command string for the given {@code coin}'s details.
     */
    public static String getCoinDetails(Coin coin) {
        StringBuilder sb = new StringBuilder();
        sb.append(NAME_PREFIX + coin.getCode().fullName + " ");
        coin.getTags().stream().forEach(
            s -> sb.append(TAG_PREFIX + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code coin}'s tags.
     */
    public static String getCoinTags(Coin coin) {
        StringBuilder sb = new StringBuilder();
        coin.getTags().stream().forEach(
            s -> sb.append(TAG_PREFIX + s.tagName + " ")
        );
        return sb.toString();
    }
}
