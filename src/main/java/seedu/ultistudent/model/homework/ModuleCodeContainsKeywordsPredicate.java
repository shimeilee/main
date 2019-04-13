package seedu.ultistudent.model.homework;

import java.util.List;
import java.util.function.Predicate;

import seedu.ultistudent.commons.util.StringUtil;

/**
 * Tests that a {@code Homework}'s {@code ModuleCode} matches any of the keywords given.
 */
public class ModuleCodeContainsKeywordsPredicate implements Predicate<Homework> {
    private final List<String> keywords;

    public ModuleCodeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Homework homework) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(homework.getModuleCode().getValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCodeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleCodeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
