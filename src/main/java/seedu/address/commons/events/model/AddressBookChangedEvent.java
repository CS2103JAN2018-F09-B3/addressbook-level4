package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.ReadOnlyCoinBook;

/** Indicates the CoinBook in the model has changed*/
public class AddressBookChangedEvent extends BaseEvent {

    public final ReadOnlyCoinBook data;

    public AddressBookChangedEvent(ReadOnlyCoinBook data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of coins " + data.getCoinList().size() + ", number of tags " + data.getTagList().size();
    }
}
