//@@author ewaldhew
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COINS;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.coin.Coin;
import seedu.address.model.coin.exceptions.CoinNotFoundException;
import seedu.address.model.coin.exceptions.DuplicateCoinException;

/**
 * Removes value from an existing coin in the book.
 */
public class SellCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "sell";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes value from the coin account identified "
            + "by the index number used in the last coin listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "AMOUNT\n"
            + "Example: " + COMMAND_WORD + " 1 " + "50.0";

    public static final String MESSAGE_SELL_COIN_SUCCESS = "Sold: %1$s";
    public static final String MESSAGE_NOT_SOLD = "No amount entered.";

    private final Index index;
    private final double amountToSell;

    private Coin coinToEdit;
    private Coin editedCoin;

    /**
     * @param index of the coin in the filtered coin list to change
     * @param amountToSell of the coin
     */
    public SellCommand(Index index, double amountToSell) {
        requireNonNull(index);

        this.index = index;
        this.amountToSell = amountToSell;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        try {
            model.updateCoin(coinToEdit, editedCoin);
        } catch (DuplicateCoinException dpe) {
            throw new CommandException("Unexpected code path!");
        } catch (CoinNotFoundException pnfe) {
            throw new AssertionError("The target coin cannot be missing");
        }
        model.updateFilteredCoinList(PREDICATE_SHOW_ALL_COINS);
        return new CommandResult(String.format(MESSAGE_SELL_COIN_SUCCESS, coinToEdit));
    }

    @Override
    protected void preprocessUndoableCommand() throws CommandException {
        List<Coin> lastShownList = model.getFilteredCoinList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_TARGET);
        }

        coinToEdit = lastShownList.get(index.getZeroBased());
        editedCoin = createEditedCoin(coinToEdit, amountToSell);
    }

    /**
     * Creates and returns a {@code Coin} with the details of {@code coinToEdit}
     * edited with {@code editCoinDescriptor}.
     */
    private static Coin createEditedCoin(Coin coinToEdit, double amountToSell) {
        assert coinToEdit != null;

        Coin editedCoin = new Coin(coinToEdit);
        editedCoin.addTotalAmountSold(amountToSell);

        return editedCoin;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SellCommand)) {
            return false;
        }

        // state check
        SellCommand e = (SellCommand) other;
        return index.equals(e.index)
                && amountToSell == e.amountToSell
                && Objects.equals(coinToEdit, e.coinToEdit);
    }
}
