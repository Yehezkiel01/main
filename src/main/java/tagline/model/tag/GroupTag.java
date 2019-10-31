package tagline.model.tag;

import tagline.model.group.GroupName;

/**
 * Tag a group.
 */
public class GroupTag extends Tag {
    public static final String TAG_PREFIX = "%";

    private GroupName groupName;

    /**
     * Constructs a {@code ContactTag}.
     *
     * @param groupName A valid {@code GroupName}.
     */
    public GroupTag(GroupName groupName) {
        super();
        this.groupName = groupName;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || ((other instanceof GroupTag) // instanceof handles nulls
            && groupName.equals(((GroupTag) other).getGroupName()));
    }

    @Override
    public String toString() {
        return TAG_PREFIX + groupName.value;
    }
}