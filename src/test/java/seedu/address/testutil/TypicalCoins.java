package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.CoinBook;
import seedu.address.model.coin.Coin;
import seedu.address.model.coin.exceptions.DuplicateCoinException;

/**
 * A utility class containing a list of {@code Coin} objects to be used in tests.
 */
public class TypicalCoins {

    public static final Coin ALICE = new CoinBuilder().withName("Alice Pauline")
            .withCode("AAA")
            .withTags("friends").build();
    public static final Coin BENSON = new CoinBuilder().withName("Benson Meier")
            .withCode("BBB")
            .withTags("owesMoney", "friends").build();
    public static final Coin CARL = new CoinBuilder().withName("Carl Kurz").withCode("CCC").build();
    public static final Coin DANIEL = new CoinBuilder().withName("Daniel Meier").withCode("DDD").build();
    public static final Coin ELLE = new CoinBuilder().withName("Elle Meyer").withCode("EEE").build();
    public static final Coin FIONA = new CoinBuilder().withName("Fiona Kunz").withCode("FFF").build();
    public static final Coin GEORGE = new CoinBuilder().withName("George Best").withCode("GGG").build();

    // Manually added
    public static final Coin HOON = new CoinBuilder().withName("Hoon Meier").withCode("HHH").build();
    public static final Coin IDA = new CoinBuilder().withName("Ida Mueller").withCode("III").build();

    // Manually added - Coin's details found in {@code CommandTestUtil}
    public static final Coin AMY = new CoinBuilder().withName(VALID_NAME_AMY).withCode(VALID_PHONE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Coin BOB = new CoinBuilder().withName(VALID_NAME_BOB).withCode(VALID_PHONE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCoins() {} // prevents instantiation

    /**
     * Returns an {@code CoinBook} with all the typical coins.
     */
    public static CoinBook getTypicalAddressBook() {
        CoinBook ab = new CoinBook();
        for (Coin coin : getTypicalCoins()) {
            try {
                ab.addCoin(coin);
            } catch (DuplicateCoinException e) {
                throw new AssertionError("not possible");
            }
        }
        return ab;
    }

    public static List<Coin> getTypicalCoins() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
