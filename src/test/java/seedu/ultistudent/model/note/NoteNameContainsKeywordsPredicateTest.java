package seedu.ultistudent.model.note;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.ultistudent.testutil.NoteBuilder;

public class NoteNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NoteNameContainsKeywordsPredicate firstPredicate =
                new NoteNameContainsKeywordsPredicate(firstPredicateKeywordList);
        NoteNameContainsKeywordsPredicate secondPredicate =
                new NoteNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NoteNameContainsKeywordsPredicate firstPredicateCopy = new
                NoteNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_noteNameContainsKeywords_returnsTrue() {
        // One keyword
        NoteNameContainsKeywordsPredicate predicate = new
                NoteNameContainsKeywordsPredicate(Collections.singletonList
                ("CS2103"));
        assertTrue(predicate.test(new NoteBuilder().withNoteName("CS2103 "
                + "CS2101").build()));

        // Multiple keywords
        predicate = new NoteNameContainsKeywordsPredicate(Arrays.asList
                ("CS2103", "CS2101"));
        assertTrue(predicate.test(new NoteBuilder().withNoteName("CS2103 "
                + "CS2101").build()));

        // Only one matching keyword
        predicate = new NoteNameContainsKeywordsPredicate(Arrays.asList
                ("CS2103", "CS2101"));
        assertTrue(predicate.test(new NoteBuilder().withNoteName("CS2103 "
                + "ES2660").build()));

        // Mixed-case keywords
        predicate = new NoteNameContainsKeywordsPredicate(Arrays.asList
                ("cS2103", "Cs2101"));
        assertTrue(predicate.test(new NoteBuilder().withNoteName("CS2103 "
                + "CS2101").build()));
    }

    @Test
    public void test_noteNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NoteNameContainsKeywordsPredicate predicate =
                new NoteNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new NoteBuilder().withNoteName("CS2103")
                .build()));

        // Non-matching keyword
        predicate = new NoteNameContainsKeywordsPredicate(Arrays.asList
                ("CS2103"));
        assertFalse(predicate.test(new NoteBuilder().withNoteName("CS3230 "
                + "CS1231")
                .build()));
        assertTrue(predicate.test(new NoteBuilder().withNoteName("CS2103").build()));

        // Temporarily removed this as it is not sufficient to match a note
        // based on its other property: content
        // Keywords match module code and UltiStudent, but does not match
        // notename
        //predicate = new NoteNameContainsKeywordsPredicate(Arrays.asList
        //        ("12345", "alice@email.com", "Main", "Street"));
        //assertFalse(predicate.test(new NoteBuilder().withNoteName("Alice")
        //        .withPhone
        //        ("12345")
        //        .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
