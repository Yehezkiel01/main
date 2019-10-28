package tagline.logic.commands.note;

import static java.util.Objects.requireNonNull;
import static tagline.model.note.NoteModel.PREDICATE_SHOW_ALL_NOTES;

import java.util.List;
import java.util.Optional;

import tagline.commons.core.Messages;
import tagline.logic.commands.CommandResult;
import tagline.logic.commands.exceptions.CommandException;
import tagline.model.Model;
import tagline.model.note.Note;
import tagline.model.note.NoteId;
import tagline.model.tag.Tag;
import tagline.model.tag.TagId;

/**
 * Tags a note with several tags.
 */
public class TagNoteCommand extends NoteCommand {
    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_TAG_NOTE_SUCCESS = "Tagged Note: %1$s";

    private final NoteId noteId;
    private final List<Tag> tags;

    public TagNoteCommand(NoteId noteId, List<Tag> tags) {
        requireNonNull(noteId);
        requireNonNull(tags);

        assert(tags.size() > 0) : "At least one tag to be provided.";

        this.noteId = noteId;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check for invalid note id
        Optional<Note> noteFound = model.findNote(noteId);

        if (noteFound.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_INDEX);
        }

        Note targetNote = noteFound.get();

        for (Tag tag : tags) {
            TagId id = model.createOrFindTag(tag);

            model.tagNote(targetNote, id);
        }

        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
        return new CommandResult(String.format(MESSAGE_TAG_NOTE_SUCCESS, targetNote), CommandResult.ViewType.NOTE);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagNoteCommand)) {
            return false;
        }

        // state check
        TagNoteCommand otherCommand = (TagNoteCommand) other;
        return noteId.equals(otherCommand.noteId) && tags.equals(otherCommand.tags);
    }
}
