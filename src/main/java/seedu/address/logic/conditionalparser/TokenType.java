//@@author Eldon-Chung
package seedu.address.logic.conditionalparser;

/**
 * Represents the possible types a token can take, along with the regular expression it is specified by.
 */
public enum TokenType {
    BINARYBOOL("( OR | AND )"),
    UNARYBOOL("NOT "),
    LEFTPARENTHESES("\\("),
    RIGHTPARENTHESES("\\)"),
    COMPARATOR("(>|=|<)+"),
    PREFIX_ADDRESS("a/"),
    PREFIX_NAME("n/"),
    PREFIX_PHONE("p/"),
    PREFIX_EMAIL("e/"),
    PREFIX_TAG("t/"),
    NUM("[1-9][0-9]*"),
    DECIMAL("[0-9]+.?[0-9]+"),
    STRING("[A-Za-z]+"),
    SLASH("/"),
    WHITESPACE("\\s"),
    NEWLINE("\\n"),
    ELSE(".+");

    final String regex;

    TokenType(final String regex) {
        this.regex = regex;
    }

    public String toString() {
        return this.regex;
    }
}
