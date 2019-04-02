package seedu.ultistudent.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.util.AppUtil.checkArgument;

/**
 * Represents content in Note.
 */
public class Content {

    public static final String MESSAGE_CONSTRAINTS = "Content should only "
            + "contain alphanumeric characters and spaces, and it should not be "
            + "blank";

    /*
     * The first character of the UltiStudent must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    //public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}]*";

    public final String content;

    /**
     * Constructs a {@code Content}.
     *
     * @param content A valid content.
     */
    public Content(String content) {
        requireNonNull(content);
        //checkArgument(isValidNoteContent(content), MESSAGE_CONSTRAINTS);
        this.content = content;
    }

    /**
     * Returns true if a given string is a valid content string.
     */
    //public static boolean isValidNoteContent(String test) {
    //    return test.matches(VALIDATION_REGEX);
    //}


    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Content // instanceof handles nulls
                && content.equals(((Content) other).content)); // state check
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

}
