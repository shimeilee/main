//package seedu.ultistudent.testutil;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import seedu.ultistudent.model.AddressBook;
//import seedu.ultistudent.model.cap.CapEntry;
//
///**
// * A utility class containing a list of {@code Person} objects to be used in tests.
// */
//public class TypicalCapEntry {
//
//    public static final CapEntry CS2101 = new CapEntryBuilder().withModuleCode("CS2100")
//            .withModuleGrade("A+").withModuleCredits("alice@example.com")
//            .withModuleSemester("94351253")
//            .build();
//
//    public static final CapEntry CS2102 = new CapEntryBuilder().withModuleCode("CS2100")
//            .withModuleGrade("A+").withModuleCredits("alice@example.com")
//            .withModuleSemester("94351253")
//            .build();
//
//    public static final CapEntry CS2103 = new CapEntryBuilder().withModuleCode("CS2100")
//            .withModuleGrade("A+").withModuleCredits("alice@example.com")
//            .withModuleSemester("94351253")
//            .build();
//
//    public static final CapEntry CS2104 = new CapEntryBuilder().withModuleCode("CS2100")
//            .withModuleGrade("A+").withModuleCredits("alice@example.com")
//            .withModuleSemester("94351253")
//            .build();
//
//    public static final CapEntry CS2105 = new CapEntryBuilder().withModuleCode("CS2100")
//            .withModuleGrade("A+").withModuleCredits("alice@example.com")
//            .withModuleSemester("94351253")
//            .build();
//
//    public static final CapEntry CS2106 = new CapEntryBuilder().withModuleCode("CS2100")
//            .withModuleGrade("A+").withModuleCredits("alice@example.com")
//            .withModuleSemester("94351253")
//            .build();
//
//    public static final CapEntry CS2107 = new CapEntryBuilder().withModuleCode("CS2100")
//            .withModuleGrade("A+").withModuleCredits("alice@example.com")
//            .withModuleSemester("94351253")
//            .build();
//
//    public static final CapEntry BENSON = new CapEntryBuilder().withModuleCode("Benson Meier")
//            .withModuleGrade("311, Clementi Ave 2, #02-25")
//            .withModuleCredits("johnd@example.com").withModuleSemester("98765432")
//            .build();
//
//    // Manually added - Person's details found in {@code CommandTestUtil}
//    public static final CapEntry AMY = new CapEntryBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
//            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
//    public static final CapEntry BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
//            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
//            .build();
//
//    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
//
//    private TypicalCapEntry() {} // prevents instantiation
//
//    /**
//     * Returns an {@code AddressBook} with all the typical persons.
//     */
//    public static AddressBook getTypicalAddressBook() {
//        AddressBook ab = new AddressBook();
//        for (CapEntry capEntry : getTypicalCapEntryList()) {
//            ab.addCapEntry(capEntry);
//        }
//        return ab;
//    }
//
//    public static List<CapEntry> getTypicalCapEntryList() {
//        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
//    }
//}
