package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.coin.Coin;
import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the coins list.
     * This list will not contain any duplicate coins.
     */
    ObservableList<Coin> getCoinList();

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();

}
