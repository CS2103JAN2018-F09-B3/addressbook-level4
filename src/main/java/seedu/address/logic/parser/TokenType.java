//@@author Eldon-Chung
package seedu.address.logic.parser;

/**
 * Represents the possible types a token can take, along with the regular expression it is specified by.
 */
public enum TokenType {
    BINARYBOOL(" OR | AND ", "BINARYBOOL", "a boolean operator"),
    UNARYBOOL("NOT ", "UNARYBOOL", "a NOT operator"),
    LEFTPARENTHESES("\\(", "LEFTPARENTHESES", "\"(\""),
    RIGHTPARENTHESES("\\)", "RIGHTPARENTHESES", "\")\""),
    COMPARATOR(">|=|<", "COMPARATOR", "a comparator"),
    PREFIX_AMOUNT("a/", "APREFIX", "a prefix"),
    PREFIX_BOUGHT("b/", "BPREFIX", "a prefix"),
    PREFIX_CODE("c/", "CPREFIX", "a prefix"),
    PREFIX_EARNED("e/", "EPREFIX", "a prefix"),
    PREFIX_HELD("h/", "HPREFIX", "a prefix"),
    PREFIX_MADE("m/", "MPREFIX", "a prefix"),
    PREFIX_NAME("n/", "NPREFIX", "a prefix"),
    PREFIX_PRICE("p/", "PPREFIX", "a prefix"),
    PREFIX_SOLD("s/", "SPREFIX", "a prefix"),
    PREFIX_TAG("t/", "TPREFIX", "a prefix"),
    PREFIX_WORTH("w/", "WPREFIX", "a prefix"),
    DECIMAL("\\-?[0-9]+\\.[0-9]+", "DECIMAL", "a decimal number"),
    NUM("\\-?[0-9]+", "NUM", "an integer"),
    STRING("[A-Za-z\\^\\-\\@\\./]+", "STRING", "a string"),
    SLASH("/", "SLASH", "a slash"),
    WHITESPACE("\\s", "WHITESPACE", "some white space"),
    NEWLINE("\\n", "NEWLINE", "a newline"),
    ELSE(".+", "ELSE", "some unknown character"),
    EOF("[^\\w\\W]", "EOF", "some end of the argument");

    final String typeName;
    final String regex;
    final String description;

    TokenType(final String regex, final String typeName, final String description) {
        this.regex = regex;
        this.typeName = typeName;
        this.description = description;
    }

    public String toString() {
        return this.regex;
    }

    /**
     * Checks if the {@code type} is a prefix type
     * @param type the type to be checked
     */
    public static boolean isPrefixType(TokenType type) {
        return type == PREFIX_AMOUNT
                || type == PREFIX_BOUGHT
                || type == PREFIX_CODE
                || type == PREFIX_EARNED
                || type == PREFIX_HELD
                || type == PREFIX_MADE
                || type == PREFIX_NAME
                || type == PREFIX_PRICE
                || type == PREFIX_SOLD
                || type == PREFIX_TAG
                || type == PREFIX_WORTH;
    }
}
