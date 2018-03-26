package seedu.address.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CoinBook;
import seedu.address.model.ReadOnlyCoinBook;

/**
 * Represents a storage for {@link CoinBook}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    String getAddressBookFilePath();

    /**
     * Returns CoinBook data as a {@link ReadOnlyCoinBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyCoinBook> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyCoinBook> readAddressBook(String filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyCoinBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyCoinBook addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyCoinBook)
     */
    void saveAddressBook(ReadOnlyCoinBook addressBook, String filePath) throws IOException;

    /**
     * Saves the given {@link ReadOnlyCoinBook} to a fixed temporary location.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void backupAddressBook(ReadOnlyCoinBook addressBook) throws IOException;
}
