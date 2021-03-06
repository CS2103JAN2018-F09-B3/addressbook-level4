package systemtests;

import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_TARGET;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.ViewCommand.MESSAGE_SELECT_COIN_SUCCESS;
import static seedu.address.testutil.TypicalCoins.KEYWORD_MATCHING_BTC;
import static seedu.address.testutil.TypicalCoins.getTypicalCoins;
import static seedu.address.testutil.TypicalTargets.INDEX_FIRST_COIN;

import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.Model;

public class ViewCommandSystemTest extends CoinBookSystemTest {
    @Test
    public void select() {
        /* ------------------------ Perform select operations on the shown unfiltered list -------------------------- */

        /* Case: select the first card in the coin list, command with leading spaces and trailing spaces
         * -> selected
         */
        String command = "   " + ViewCommand.COMMAND_WORD + " " + INDEX_FIRST_COIN.getOneBased() + "   ";
        assertCommandSuccess(command, INDEX_FIRST_COIN);

        /* Case: select the last card in the coin list -> selected */
        Index coinCount = Index.fromOneBased(getTypicalCoins().size());
        command = ViewCommand.COMMAND_WORD + " " + coinCount.getOneBased();
        assertCommandSuccess(command, coinCount);

        /* Case: undo previous selection -> rejected */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_FAILURE;
        assertCommandFailure(command, expectedResultMessage);

        /* Case: redo selecting last card in the list -> rejected */
        command = RedoCommand.COMMAND_WORD;
        expectedResultMessage = RedoCommand.MESSAGE_FAILURE;
        assertCommandFailure(command, expectedResultMessage);

        /* Case: select the middle card in the coin list -> selected */
        Index middleIndex = Index.fromOneBased(coinCount.getOneBased() / 2);
        command = ViewCommand.COMMAND_WORD + " " + middleIndex.getOneBased();
        assertCommandSuccess(command, middleIndex);

        /* Case: select the current selected card -> selected */
        assertCommandSuccess(command, middleIndex);

        /* ------------------------ Perform select operations on the shown filtered list ---------------------------- */

        /* Case: filtered person list, select index within bounds of coin book but out of bounds of coin list
         * -> rejected
         */
        showCoinsWithName(KEYWORD_MATCHING_BTC);
        int invalidIndex = getModel().getCoinBook().getCoinList().size();
        assertCommandFailure(ViewCommand.COMMAND_WORD + " " + invalidIndex, MESSAGE_INVALID_COMMAND_TARGET);

        /* Case: filtered coin list, select index within bounds of address book and coin list -> selected */
        Index validIndex = Index.fromOneBased(1);
        assertTrue(validIndex.getZeroBased() < getModel().getFilteredCoinList().size());
        command = ViewCommand.COMMAND_WORD + " " + validIndex.getOneBased();
        assertCommandSuccess(command, validIndex);

        /* ----------------------------------- Perform invalid select operations ------------------------------------ */

        /* Case: invalid index (0) -> rejected */
        assertCommandFailure(ViewCommand.COMMAND_WORD + " " + 0,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        /* Case: invalid index (-1) -> rejected */
        assertCommandFailure(ViewCommand.COMMAND_WORD + " " + -1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        /* Case: invalid index (size + 1) -> rejected */
        invalidIndex = getModel().getFilteredCoinList().size() + 1;
        assertCommandFailure(ViewCommand.COMMAND_WORD + " " + invalidIndex, MESSAGE_INVALID_COMMAND_TARGET);

        /* Case: invalid arguments (symbols) -> rejected */
        assertCommandFailure(ViewCommand.COMMAND_WORD + " -#abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        /* Case: invalid arguments (extra argument) -> rejected */
        assertCommandFailure(ViewCommand.COMMAND_WORD + " 1 abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        /* Case: mixed case command word -> rejected */
        assertCommandFailure("SeLeCt 1", MESSAGE_UNKNOWN_COMMAND);

        /* Case: select from empty address book -> rejected */
        deleteAllCoins();
        assertCommandFailure(ViewCommand.COMMAND_WORD + " " + INDEX_FIRST_COIN.getOneBased(),
                MESSAGE_INVALID_COMMAND_TARGET);
    }

    /**
     * Executes {@code command} and asserts that the,<br>
     * 1. Command box displays an empty string.<br>
     * 2. Command box has the default style class.<br>
     * 3. Result display box displays the success message of executing select command with the
     * {@code expectedSelectedCardIndex} of the selected coin.<br>
     * 4. {@code Model}, {@code Storage} and {@code CoinListPanel} remain unchanged.<br>
     * 5. Selected card is at {@code expectedSelectedCardIndex} and the browser url is updated accordingly.<br>
     * 6. Status bar remains unchanged.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code CoinBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see CoinBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     * @see CoinBookSystemTest#assertSelectedCardChanged(Index)
     */
    private void assertCommandSuccess(String command, Index expectedSelectedCardIndex) {
        Model expectedModel = getModel();
        String expectedResultMessage = String.format(
                MESSAGE_SELECT_COIN_SUCCESS, expectedSelectedCardIndex.getOneBased());
        int preExecutionSelectedCardIndex = getCoinListPanel().getSelectedCardIndex();

        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);

        if (preExecutionSelectedCardIndex == expectedSelectedCardIndex.getZeroBased()) {
            assertSelectedCardUnchanged();
        } else {
            assertSelectedCardChanged(expectedSelectedCardIndex);
        }

        assertCommandBoxAndResultDisplayShowsDefaultStyle();
        assertStatusBarUnchanged();
    }

    /**
     * Executes {@code command} and asserts that the,<br>
     * 1. Command box displays {@code command}.<br>
     * 2. Command box has the error style class.<br>
     * 3. Result display box displays {@code expectedResultMessage}.<br>
     * 4. {@code Model}, {@code Storage} and {@code CoinListPanel} remain unchanged.<br>
     * 5. Browser url, selected card and status bar remain unchanged.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code CoinBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see CoinBookSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxAndResultDisplayShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
