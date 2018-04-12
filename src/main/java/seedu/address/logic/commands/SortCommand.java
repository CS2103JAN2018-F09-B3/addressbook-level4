package seedu.address.logic.commands;

/**
 * Sorts all coins in Coin List by alphabetical order and displays sorted list to user
 */
//@@author Neil
public class SortCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the coins the user has input into the Coin Book as a list"
            + " sorted by alphabetical order of cryptocurrency coin name\n";

    public static final String MESSAGE_SUCCESS = "Sorted all coins alphabetically";

    private final boolean isSort;

    public SortCommand(boolean isSort){
        this.isSort = isSort;
    }

    @Override
    public CommandResult executeUndoableCommand(){
        model.sortCoinList(isSort);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
//@@author