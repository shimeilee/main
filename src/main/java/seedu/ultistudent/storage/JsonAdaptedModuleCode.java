package seedu.ultistudent.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.ultistudent.commons.exceptions.IllegalValueException;
import seedu.ultistudent.model.modulecode.ModuleCode;

/**
 * Jackson-friendly version of {@link seedu.ultistudent.model.modulecode.ModuleCode}.
 */
public class JsonAdaptedModuleCode {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Cap Entry's %s field is missing!";

    private final String moduleCode;

    /**
     * Constructs a {@code JsonAdaptedCapEntry} with the given ModuleCode details.
     */
    @JsonCreator
    public JsonAdaptedModuleCode(@JsonProperty("moduleCode") String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Converts a given {@code CapEntry} into this class for Jackson use.
     */
    public JsonAdaptedModuleCode(ModuleCode source) {
        moduleCode = source.value;
    }

    /**
     * Converts this Jackson-friendly adapted ModuleCode object into the model's {@code ModuleCode} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module code.
     */
    public ModuleCode toModelType() throws IllegalValueException {

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        return modelModuleCode;
    }
}
