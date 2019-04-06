package seedu.ultistudent.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.ultistudent.commons.core.index.Index;
import seedu.ultistudent.commons.util.StringUtil;
import seedu.ultistudent.logic.parser.exceptions.ParseException;
import seedu.ultistudent.model.cap.ModuleCredits;
import seedu.ultistudent.model.cap.ModuleGrade;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.homework.Date;
import seedu.ultistudent.model.homework.HomeworkName;
import seedu.ultistudent.model.modulecode.ModuleCode;
import seedu.ultistudent.model.note.NoteName;
import seedu.ultistudent.model.person.Address;
import seedu.ultistudent.model.person.Email;
import seedu.ultistudent.model.person.Name;
import seedu.ultistudent.model.person.Phone;
import seedu.ultistudent.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    //===== CapManager =====//

    /**
     * Parses a {@code String modulecode} into a {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleCode} is invalid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(trimmedModuleCode);
    }

    /**
     * Parses a {@code String moduleGrade} into a {@code moduleGrade}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleGrade} is invalid.
     */
    public static ModuleGrade parseModuleGrade(String moduleGrade) throws ParseException {
        requireNonNull(moduleGrade);
        String trimmedModuleGrade = moduleGrade.trim();
        if (!ModuleGrade.isValidModuleGrade(trimmedModuleGrade)) {
            throw new ParseException(ModuleGrade.MESSAGE_CONSTRAINTS);
        }
        return new ModuleGrade(trimmedModuleGrade);
    }

    /**
     * Parses a {@code String moduleCredits} into a {@code moduleCredits}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleCredits} is invalid.
     */
    public static ModuleCredits parseModuleCredits(String moduleCredits) throws ParseException {
        requireNonNull(moduleCredits);
        String trimmedModuleCredits = moduleCredits.trim();
        if (!ModuleCredits.isValidModuleCredits(trimmedModuleCredits)) {
            throw new ParseException(ModuleCredits.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCredits(trimmedModuleCredits);
    }

    /**
     * Parses a {@code String moduleSemester} into a {@code moduleSemester}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleSemester} is invalid.
     */
    public static ModuleSemester parseModuleSemester(String moduleSemester) throws ParseException {
        requireNonNull(moduleSemester);
        String trimmedModuleSemester = moduleSemester.trim();
        if (!ModuleSemester.isValidModuleSemester(trimmedModuleSemester)) {
            throw new ParseException(ModuleSemester.MESSAGE_CONSTRAINTS);
        }
        return new ModuleSemester(trimmedModuleSemester);
    }

    //===== HomeworkManager =====//

    /**
     * Parses a {@code String homeworkName} into a {@code HomeworkName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code homeworkName} is invalid.
     */
    public static HomeworkName parseHomeworkName(String homeworkName) throws ParseException {
        requireNonNull(homeworkName);
        String trimmedHomeworkName = homeworkName.trim();
        if (!HomeworkName.isValidHomeworkName(trimmedHomeworkName)) {
            throw new ParseException(HomeworkName.MESSAGE_CONSTRAINTS);
        }
        return new HomeworkName(homeworkName);
    }

    /**
     * Parses a {@code String modulecode} into a {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code modulecode} is invalid.
     */
    public static ModuleCode parseHomeworkModuleCode(String moduleCode)
            throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(moduleCode);
    }

    /**
     * Parses a {@code String deadline} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Date parseDate(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        String[] trimmedDeadlineParts = trimmedDeadline.split("/");
        String processed = "";
        for (String parts : trimmedDeadlineParts) {
            if (parts.length() < 2) {
                processed += "0" + parts + "/";
            } else if (parts.length() == 4) {
                processed += parts;
            } else {
                processed += parts + "/";
            }
        }
        if (!Date.isValidDate(processed)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(processed);
    }

    //===== Note ======/

    /**
     * Parses a {@code String modulecode} into a {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code modulecode} is invalid.
     */
    public static ModuleCode parseNoteModuleCode(String moduleCode)
            throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(moduleCode);
    }

    /**
     * Parses a {@code String name} into a {@code note}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static NoteName parseNoteName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!NoteName.isValidNoteName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new NoteName(trimmedName);
    }

    //===== Person (AB4) =====//

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String ultistudent} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ultistudent} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

}
