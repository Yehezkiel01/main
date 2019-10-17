package tagline.model.contact;

import tagline.model.contact.exceptions.InvalidIdException;

/**
 * Represent a Contact unique Id.
 */
public class ContactId {

    public static final String MESSAGE_CONSTRAINTS = "id should be a positive integer up to 5 digit";

    private static int contactIdDigits = 5;

    private final int id;

    /**
     * Construct an Id from String.
     * @param id
     */
    public ContactId(String id) {
        this(Integer.valueOf(id));
    }

    /**
     * Construct Id from integer.
     */
    public ContactId(int id) {
        if (id >= Math.pow(10, contactIdDigits)) {
            throw new InvalidIdException("Id too large");
        }
        if (id < 0) {
            throw new InvalidIdException("Id has to be a positive number");
        }
        this.id = id;
    }

    /**
     * Returns true if id is valid.
     */
    public static boolean isValidId(String id) {
        int value;

        try {
            value = Integer.valueOf(id);
        } catch (NumberFormatException ex) {
            return false;
        }

        if (0 <= value && value < (int) Math.pow(10, contactIdDigits)) {
            return true;
        }
        return false;
    }

    /**
     * Increase the number of digit in Id.
     */
    static void incrementDigit() {
        contactIdDigits++;
    }

    static int getDigit() {
        return contactIdDigits;
    }

    public Integer toInteger() {
        return id;
    }

    @Override
    public String toString() {
        String idString = ((Integer) id).toString();
        while (idString.length() < contactIdDigits) {
            idString = "0" + idString;
        }
        return idString;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ContactId)) {
            return false;
        }

        return id == ((ContactId) obj).id;
    }

    @Override
    public int hashCode() {
        return ((Integer) id).hashCode();
    }
}
