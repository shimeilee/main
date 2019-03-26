package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.cap.CapEntry;
import seedu.address.model.cap.ModuleCredits;
import seedu.address.model.cap.ModuleGrade;
import seedu.address.model.modulecode.ModuleCode;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link CapEntry}.
 */
public class JsonAdaptedCapEntry {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Cap Entry's %s field is missing!";

    private final String moduleCode;
    private final String moduleGrade;
    private final String moduleCredits;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCapEntry} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedCapEntry(@JsonProperty("modulecode") String moduleCode,
                               @JsonProperty("moduleGrade") String moduleGrade,
                               @JsonProperty("moduleCredits") String moduleCredits,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.moduleCode = moduleCode;
        this.moduleGrade = moduleGrade;
        this.moduleCredits = moduleCredits;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code CapEntry} into this class for Jackson use.
     */
    public JsonAdaptedCapEntry(CapEntry source) {
        moduleCode = source.getModuleCode().value;
        moduleGrade = source.getModuleGrade().value;
        moduleCredits = source.getModuleCredits().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code CapEntry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted cap entry.
     */
    public CapEntry toModelType() throws IllegalValueException {
        final List<Tag> capEntryTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            capEntryTags.add(tag.toModelType());
        }

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        if (moduleGrade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleGrade.class.getSimpleName()));
        }
        if (!ModuleGrade.isValidModuleGrade(moduleGrade)) {
            throw new IllegalValueException(ModuleGrade.MESSAGE_CONSTRAINTS);
        }
        final ModuleGrade modelModuleGrade = new ModuleGrade(moduleGrade);

        if (moduleCredits == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCredits.class.getSimpleName()));
        }
        if (!ModuleCredits.isValidModuleCredits(moduleCredits)) {
            throw new IllegalValueException(ModuleCredits.MESSAGE_CONSTRAINTS);
        }
        final ModuleCredits modelModuleCredits = new ModuleCredits(moduleCredits);

        final Set<Tag> modelTags = new HashSet<>(capEntryTags);
        return new CapEntry(modelModuleCode, modelModuleGrade, modelModuleCredits, modelTags);
    }
}






