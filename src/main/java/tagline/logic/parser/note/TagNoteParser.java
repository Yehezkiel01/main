package tagline.logic.parser.note;

import java.util.ArrayList;

import tagline.logic.commands.note.TagNoteCommand;
import tagline.logic.parser.Parser;
import tagline.logic.parser.exceptions.ParseException;
import tagline.model.note.NoteId;
import tagline.model.tag.Tag;

public class TagNoteParser implements Parser<TagNoteCommand> {
    public TagNoteCommand parse(String args) throws ParseException {
        return new TagNoteCommand(new NoteId(1), new ArrayList<Tag>());
    }
}
