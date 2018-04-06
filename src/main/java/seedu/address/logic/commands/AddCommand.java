package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.TokenType.NAME_PREFIX;
import static seedu.address.logic.parser.TokenType.TAG_PREFIX;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.coin.Coin;
import seedu.address.model.coin.exceptions.DuplicateCoinException;

/**
 * Adds a coin to the address book.
 */
public class AddCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_ALIAS = "a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a coin to the coin book. "
            + "Parameters: "
            + NAME_PREFIX + "NAME "
            + "[" + TAG_PREFIX + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + NAME_PREFIX + "BTC "
            + TAG_PREFIX + "fav "
            + TAG_PREFIX + "fastTransfer";

    public static final String MESSAGE_SUCCESS = "New coin added: %1$s";
    public static final String MESSAGE_DUPLICATE_COIN = "This coin already exists in the coin book";

    private final Coin toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Coin}
     */
    public AddCommand(Coin coin) {
        requireNonNull(coin);
        toAdd = coin;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        try {
            model.addCoin(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (DuplicateCoinException e) {
            throw new CommandException(MESSAGE_DUPLICATE_COIN);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
