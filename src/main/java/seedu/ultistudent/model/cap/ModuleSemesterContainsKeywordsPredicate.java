package seedu.ultistudent.model.cap;

import java.util.List;
import java.util.function.Predicate;

import seedu.ultistudent.commons.util.StringUtil;

/**
 * Tests that a {@code capEntry}'s {@code ModuleSemester} matches any of the keywords given.
 */
public class ModuleSemesterContainsKeywordsPredicate implements Predicate<CapEntry> {
    private final List<String> keywords;

    public ModuleSemesterContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(CapEntry capEntry) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(capEntry.getModuleSemester().getValue(),
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleSemesterContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleSemesterContainsKeywordsPredicate) other).keywords)); // state check
    }

}

