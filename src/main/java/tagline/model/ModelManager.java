package tagline.model;

import static java.util.Objects.requireNonNull;
import static tagline.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import tagline.commons.core.GuiSettings;
import tagline.commons.core.LogsCenter;
import tagline.model.contact.AddressBook;
import tagline.model.contact.Contact;
import tagline.model.contact.ContactManager;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ContactManager contactManager;
    private final UserPrefs userPrefs;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.contactManager = new ContactManager(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        contactManager.setAddressBook(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return contactManager.getAddressBook();
    }

    @Override
    public boolean hasContact(Contact contact) {
        return contactManager.hasContact(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        contactManager.deleteContact(target);
    }

    @Override
    public void addContact(Contact contact) {
        contactManager.addContact(contact);
    }

    @Override
    public void setContact(Contact target, Contact editedContact) {
        contactManager.setContact(target, editedContact);
    }

    @Override
    public Optional<Contact> findContact(int id) {
        return contactManager.findContact(id);
    }

    //=========== Filtered Contact List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return contactManager.getFilteredContactList();
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        contactManager.updateFilteredContactList(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return contactManager.equals(other.contactManager)
                && userPrefs.equals(other.userPrefs);
    }

}
