package tagline.model.tag;

import static java.util.Objects.requireNonNull;

/**
 * Tag a contact.
 */
public class HashTag extends Tag {
    public static final String TAG_PREFIX = "#";

    private String value;

    /**
     * Constructs a HashTag.
     */
    public HashTag(String value) {
        super(TagType.HASH_TAG);
        requireNonNull(value);
        this.value = value;
    }

    /**
     * Constructs a {@code HashTag} from storage.
     */
    public HashTag(TagId tagId, String value) {
        super(tagId, TagType.HASH_TAG);
        requireNonNull(value);
        requireNonNull(tagId);
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other)
                && value.equals(((HashTag) other).value);
    }

    @Override
    public String toString() {
        return TAG_PREFIX + value;
    }
}
