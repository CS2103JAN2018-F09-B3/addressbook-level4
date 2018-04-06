package seedu.address.logic.commands;

import static seedu.address.logic.parser.TokenType.NAME_PREFIX;
import static seedu.address.logic.parser.TokenType.PRICE_PREFIX;

/**
 * Finds and lists all coins in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_ALIAS = "f";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all coins specified by the conditions.\n"
            + "Parameters: CONDITION "
            + "Example: " + COMMAND_WORD + " " + NAME_PREFIX + "BTC AND" + PRICE_PREFIX + ">50";

    //@@author Eldon-Chung
    public FindCommand() {
        ;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("Command acknowledged! Results are still a WIP");
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof FindCommand); // short circuit if same class
    }
}
