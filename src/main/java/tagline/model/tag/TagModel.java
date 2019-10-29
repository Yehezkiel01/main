package tagline.model.tag;

import java.util.List;
import java.util.Optional;

/**
 * The API of the TagModel component.
 */
public interface TagModel {
    /**
     * Replaces tag book data with the data in {@code tagBook}.
     */
    void setTagBook(ReadOnlyTagBook tagList);

    /**
     * Returns a read-only view of the tag book.
     */
    ReadOnlyTagBook getTagBook();

    /**
     * Returns true if {@code tag} exists in the tag list.
     */
    boolean hasTag(Tag tag);

    /**
     * Adds the given tag.
     * {@code tag} must not already exist in the tag list.
     */
    void addTag(Tag tag);

    /**
     * Finds tag by {@code id}.
     */
    Optional<Tag> findTag(TagId id);

    /**
     * Finds tag by tag object without checking its id.
     * This method will be used for tag which has not been assigned any Id.
     */
    Optional<Tag> findTag(Tag tag);
}
