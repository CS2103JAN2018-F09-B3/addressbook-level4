package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final TokenType[] EXPECTED_TOKEN_TYPES = {
        TokenType.PREFIX_AMOUNT,
        TokenType.PREFIX_NAME,
        TokenType.PREFIX_PRICE,
        TokenType.PREFIX_EARNED,
        TokenType.PREFIX_TAG
    };

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentTokenizer lexicalAnalyzer = new ArgumentTokenizer();
        TokenStack tokenList = ArgumentTokenizer.tokenizeToTokenStack(args, EXPECTED_TOKEN_TYPES);
        ConditionSyntaxParser conditionSyntaxParser = new ConditionSyntaxParser(tokenList);
        if (!conditionSyntaxParser.parse()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand();
    }

}
