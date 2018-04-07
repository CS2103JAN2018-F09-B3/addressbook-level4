package seedu.address.logic.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TestUtil.CODE_PREFIX_TOKEN;
import static seedu.address.testutil.TestUtil.DECIMAL_TOKEN;
import static seedu.address.testutil.TestUtil.GREATER_TOKEN;
import static seedu.address.testutil.TestUtil.NUM_TOKEN;
import static seedu.address.testutil.TestUtil.PRICE_PREFIX_TOKEN;
import static seedu.address.testutil.TestUtil.STRING_TOKEN;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

//@@author Eldon-Chung
public class ConditionSemanticParserTest {

    private ConditionSemanticParser conditionSemanticParser;
    private TokenStack tokenStack;


    private static ConditionSemanticParser initParser(Token... tokens) {
        return new ConditionSemanticParser(new TokenStack(new ArrayList<Token>(Arrays.asList(tokens))));
    }

    @Test
    public void parse_returnsTrueOnValidInput() throws Exception {
        conditionSemanticParser = initParser(PRICE_PREFIX_TOKEN, GREATER_TOKEN, NUM_TOKEN);
        assertTrue(conditionSemanticParser.parse());
        conditionSemanticParser = initParser(PRICE_PREFIX_TOKEN, GREATER_TOKEN, DECIMAL_TOKEN);
        assertTrue(conditionSemanticParser.parse());
        conditionSemanticParser = initParser(CODE_PREFIX_TOKEN, STRING_TOKEN);
        assertTrue(conditionSemanticParser.parse());
    }

    @Test
    public void parse_returnsFalseOnInvalidInput() throws Exception {
        conditionSemanticParser = initParser(PRICE_PREFIX_TOKEN, NUM_TOKEN);
        assertFalse(conditionSemanticParser.parse());
        conditionSemanticParser = initParser(PRICE_PREFIX_TOKEN, GREATER_TOKEN, STRING_TOKEN);
        assertFalse(conditionSemanticParser.parse());
        conditionSemanticParser = initParser(CODE_PREFIX_TOKEN, STRING_TOKEN);
        assertTrue(conditionSemanticParser.parse());
    }

}
