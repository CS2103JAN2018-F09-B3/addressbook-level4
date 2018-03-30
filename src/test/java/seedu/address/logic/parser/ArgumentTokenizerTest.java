package seedu.address.logic.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArgumentTokenizerTest {

    private final TokenType aSlash = TokenType.PREFIXAMOUNT;
    private final TokenType pSlash = TokenType.PREFIXPROFIT;
    private final TokenType tSlash = TokenType.PREFIXTAG;

    @Test
    public void tokenizeToArgumentMultimap_emptyArgsString_noValues() {
        String argsString = "  ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeToArgumentMultimap(argsString, aSlash);

        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, aSlash);
    }

    private void assertPreamblePresent(ArgumentMultimap argMultimap, String expectedPreamble) {
        assertEquals(expectedPreamble, argMultimap.getPreamble());
    }

    private void assertPreambleEmpty(ArgumentMultimap argMultimap) {
        assertTrue(argMultimap.getPreamble().isEmpty());
    }

    /**
     * Asserts all the arguments in {@code argMultimap} with {@code prefixTokenType} match the {@code expectedValues}
     * and only the last value is returned upon calling {@code ArgumentMultimap#getValue(Prefix)}.
     */
    private void assertArgumentPresent(ArgumentMultimap argMultimap, TokenType prefixTokenType,
                                       String... expectedValues) {

        // Verify the last value is returned
        assertEquals(expectedValues[expectedValues.length - 1], argMultimap.getValue(prefixTokenType).get());

        // Verify the number of values returned is as expected
        assertEquals(expectedValues.length, argMultimap.getAllValues(prefixTokenType).size());

        // Verify all values returned are as expected and in order
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], argMultimap.getAllValues(prefixTokenType).get(i));
        }
    }

    private void assertArgumentAbsent(ArgumentMultimap argMultimap, TokenType prefix) {
        assertFalse(argMultimap.getValue(prefix).isPresent());
    }

    @Test
    public void tokenizeToArgumentMultimap_noPrefixes_allTakenAsPreamble() {
        String argsString = "  some random string /t tag with leading and trailing spaces ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeToArgumentMultimap(argsString);

        // Same string expected as preamble, but leading/trailing spaces should be trimmed
        assertPreamblePresent(argMultimap, argsString.trim());

    }

    @Test
    public void tokenizeToArgumentMultimap_oneArgument() {
        // Preamble present
        String argsString = "  Some preamble string a/ Argument value ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeToArgumentMultimap(argsString, aSlash);
        assertPreamblePresent(argMultimap, "Some preamble string");
        assertArgumentPresent(argMultimap, aSlash, "Argument value");

        // No preamble
        argsString = " p/   Argument value ";
        argMultimap = ArgumentTokenizer.tokenizeToArgumentMultimap(argsString, pSlash);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, pSlash, "Argument value");

    }

    @Test
    public void tokenizeToArgumentMultimap_multipleArguments() {
        // Only two arguments are present
        String argsString = "SomePreambleString t/ dashp/tValue a/aSlash value";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeToArgumentMultimap(argsString, aSlash, tSlash, pSlash);
        assertPreamblePresent(argMultimap, "SomePreambleString");
        assertArgumentPresent(argMultimap, aSlash, "aSlash value");
        assertArgumentPresent(argMultimap, tSlash, "dashp/tValue");
        assertArgumentAbsent(argMultimap, pSlash);

        // All three arguments are present
        argsString = "Different Preamble String p/111 t/ dashT-Value a/aSlash value";
        argMultimap = ArgumentTokenizer.tokenizeToArgumentMultimap(argsString, aSlash, tSlash, pSlash);
        assertPreamblePresent(argMultimap, "Different Preamble String");
        assertArgumentPresent(argMultimap, aSlash, "aSlash value");
        assertArgumentPresent(argMultimap, tSlash, "dashT-Value");
        assertArgumentPresent(argMultimap, pSlash, "111");

        /* Also covers: Reusing of the tokenizeToArgumentMultimapr multiple times */

        // Reuse tokenizeToArgumentMultimap on an empty string to ensure ArgumentMultimap is correctly reset
        // (i.e. no stale values from the previous tokenizing remain)
        argsString = "";
        argMultimap = ArgumentTokenizer.tokenizeToArgumentMultimap(argsString, aSlash, tSlash, pSlash);
        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, aSlash);
    }

    @Test
    public void tokenizeToArgumentMultimap_multipleArgumentsWithRepeats() {
        // Two arguments repeated, some have empty values
        String argsString = "SomePreambleString t/ dashT-Value p/ p/ t/ another dashT-value a/ aSlash value t/";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeToArgumentMultimap(argsString, aSlash, tSlash, pSlash);
        assertPreamblePresent(argMultimap, "SomePreambleString");
        assertArgumentPresent(argMultimap, aSlash, "aSlash value");
        assertArgumentPresent(argMultimap, tSlash, "dashT-Value", "another dashT-value", "");
        assertArgumentPresent(argMultimap, pSlash, "", "");
    }

    @Test
    public void tokenizeToArgumentMultimap_multipleArgumentsJoined() {
        String argsString = "SomePreambleStringa/ aSlash joinedt/joined t/ not joinedp/joined";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeToArgumentMultimap(argsString, aSlash, tSlash, pSlash);
        assertPreamblePresent(argMultimap, "SomePreambleStringa/ aSlash joinedt/joined");
        assertArgumentAbsent(argMultimap, aSlash);
        assertArgumentPresent(argMultimap, tSlash, "not joinedp/joined");
        assertArgumentAbsent(argMultimap, pSlash);
    }

}
