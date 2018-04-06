//@@author Eldon-Chung
package seedu.address.logic.parser;

/**
 * Represents the possible types a token can take, along with the regular expression it is specified by.
 */
public enum TokenType {
    BINARYBOOL("( OR | AND )", "BINARYBOOL"),
    UNARYBOOL("NOT ", "UNARYBOOL"),
    LEFTPARENTHESES("\\(", "LEFTPARENTHESES"),
    RIGHTPARENTHESES("\\)", "RIGHTPARENTHESES"),
    COMPARATOR("(>|=|<)+", "COMPARATOR"),
    AMOUNT_PREFIX("a/", "APREFIX"),
    BOUGHT_PREFIX("b/", "BPREFIX"),
    CODE_PREFIX("c/", "CPREFIX"),
    EARNED_PREFIX("e/", "EPREFIX"),
    HELD_PREFIX("h/", "HPREFIX"),
    MADE_PREFIX("m/", "MPREFIX"),
    NAME_PREFIX("n/", "NPREFIX"),
    PRICE_PREFIX("p/", "PPREFIX"),
    SOLD_PREFIX("s/", "SPREFIX"),
    TAG_PREFIX("t/", "TPREFIX"),
    WORTH_PREFIX("w/", "WPREFIX"),
    DECIMAL("[0-9]+\\.[0-9]+", "DECIMAL"),
    NUM("[1-9][0-9]*", "NUM"),
    STRING("[A-Za-z\\^\\-\\@\\./]+", "STRING"),
    SLASH("/", "SLASH"),
    WHITESPACE("\\s", "WHITESPACE"),
    NEWLINE("\\n", "NEWLINE"),
    ELSE(".+", "ELSE"),
    EOF("[^\\w\\W]", "EOF");

    final String typeName;
    final String regex;

    TokenType(final String regex, final String typeName) {
        this.regex = regex;
        this.typeName = typeName;
    }

    public String toString() {
        return this.regex;
    }

    /**
     * Checks if the {@code type} is a prefix type
     * @param type the type to be checked
     */
    public static boolean isPrefixType(TokenType type) {
        return type == AMOUNT_PREFIX
                || type == NAME_PREFIX
                || type == PRICE_PREFIX
                || type == EARNED_PREFIX
                || type == TAG_PREFIX;
    }
}
