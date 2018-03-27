package seedu.address.model.util;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.CoinBook;
import seedu.address.model.ReadOnlyCoinBook;
import seedu.address.model.coin.Address;
import seedu.address.model.coin.Code;
import seedu.address.model.coin.Coin;
import seedu.address.model.coin.Name;
import seedu.address.model.coin.exceptions.DuplicateCoinException;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code CoinBook} with sample data.
 */
public class SampleDataUtil {
    public static Coin[] getSampleCoins() {
        return new Coin[] {
            new Coin(new Name("Alex Yeoh"), new Code("AAA"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Coin(new Name("Bernice Yu"), new Code("BBB"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Coin(new Name("Charlotte Oliveiro"), new Code("CCC"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Coin(new Name("David Li"), new Code("DDD"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Coin(new Name("Irfan Ibrahim"), new Code("EEE"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Coin(new Name("Roy Balakrishnan"), new Code("FFF"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyCoinBook getSampleAddressBook() {
        try {
            CoinBook sampleAb = new CoinBook();
            for (Coin sampleCoin : getSampleCoins()) {
                sampleAb.addCoin(sampleCoin);
            }
            return sampleAb;
        } catch (DuplicateCoinException e) {
            throw new AssertionError("sample data cannot contain duplicate coins", e);
        }
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        HashSet<Tag> tags = new HashSet<>();
        for (String s : strings) {
            tags.add(new Tag(s));
        }

        return tags;
    }

}
