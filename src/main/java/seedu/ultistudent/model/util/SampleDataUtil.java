package seedu.ultistudent.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.ultistudent.model.ReadOnlyUltiStudent;
import seedu.ultistudent.model.UltiStudent;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleCredits;
import seedu.ultistudent.model.cap.ModuleGrade;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.homework.Date;
import seedu.ultistudent.model.homework.Homework;
import seedu.ultistudent.model.homework.HomeworkName;
import seedu.ultistudent.model.modulecode.ModuleCode;
import seedu.ultistudent.model.note.Content;
import seedu.ultistudent.model.note.Note;
import seedu.ultistudent.model.note.NoteName;
import seedu.ultistudent.model.person.Address;
import seedu.ultistudent.model.person.Email;
import seedu.ultistudent.model.person.Name;
import seedu.ultistudent.model.person.Person;
import seedu.ultistudent.model.person.Phone;
import seedu.ultistudent.model.tag.Tag;

/**
 * Contains utility methods for populating {@code UltiStudent} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
                new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("friends")),
                new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends")),
                new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("neighbours")),
                new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("family")),
                new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagSet("classmates")),
                new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagSet("colleagues"))
        };
    }
    
    public static CapEntry[] getSampleCapEntryList() {
        return new CapEntry[]{
                new CapEntry(new ModuleCode("CS1010"), new ModuleGrade("B+"), new ModuleCredits("4"),
                        new ModuleSemester("Y1S1")),
                new CapEntry(new ModuleCode("CS1231"), new ModuleGrade("A-"), new ModuleCredits("4"),
                        new ModuleSemester("Y1S1")),
                new CapEntry(new ModuleCode("GER1000"), new ModuleGrade("A-"), new ModuleCredits("4"),
                        new ModuleSemester("Y1S1")),
                new CapEntry(new ModuleCode("MA1521"), new ModuleGrade("B+"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y1S1")),
                new CapEntry(new ModuleCode("MA1101R"), new ModuleGrade("B+"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y1S1")),
                new CapEntry(new ModuleCode("PC1221"), new ModuleGrade("B+"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y1S2")),
                new CapEntry(new ModuleCode("PC1222"), new ModuleGrade("A-"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y1S2")),
                new CapEntry(new ModuleCode("CS2040"), new ModuleGrade("A-"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y1S2")),
                new CapEntry(new ModuleCode("CS2030"), new ModuleGrade("B+"), new ModuleCredits("4"),
                        new ModuleSemester("Y1S2")),
                new CapEntry(new ModuleCode("CS2101"), new ModuleGrade("B+"), new ModuleCredits("4"),
                        new ModuleSemester("Y1S2")),
                new CapEntry(new ModuleCode("CS2103T"), new ModuleGrade("B+"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y1S2")),
                new CapEntry(new ModuleCode("CS2102"), new ModuleGrade("A-"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y2S1")),
                new CapEntry(new ModuleCode("CS2104"), new ModuleGrade("A-"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y2S1")),
                new CapEntry(new ModuleCode("CS2105"), new ModuleGrade("B+"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y2S1")),
                new CapEntry(new ModuleCode("CS2106"), new ModuleGrade("B+"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y2S1")),
                new CapEntry(new ModuleCode("CS2107"), new ModuleGrade("B+"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y2S1")),
                new CapEntry(new ModuleCode("CS3230"), new ModuleGrade("A-"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y2S2")),
                new CapEntry(new ModuleCode("CS3223"), new ModuleGrade("A-"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y2S2")),
                new CapEntry(new ModuleCode("ST2334"), new ModuleGrade("B+"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y2S2")),
                new CapEntry(new ModuleCode("ST2131"), new ModuleGrade("B+"),
                        new ModuleCredits("4"),
                        new ModuleSemester("Y2S2"))
        };
    }
    
    public static Note[] getSampleNotes() {
        return new Note[]{
                new Note(new ModuleCode("CS2103T"), new NoteName("Personal Project Portfolio"), new Content("Things to "
                        + "include:\n1. Overview\n2. Summary of Contribution\n"
                        + "3. Contribution to User Guide\n4. Contribution to Developer Guide")),
                new Note(new ModuleCode("CS2103T"), new NoteName("Feedback"), new Content("Orientate the user during the"
                        + " introduction.\nWin their hearts within the first 30s.\nWhy do the user need UltiStudent?"
                        + "\n\n3mins per presenter")),
                new Note(new ModuleCode("CS2101"), new NoteName("Week 1 Notes"), new Content("Things to "
                        + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2101"), new NoteName("Feedback for " +
                        "Pitch"),
                        new Content("Orientate the user during the"
                                + " introduction.\nWin their hearts within the first 30s.\nWhy do the user need UltiStudent?"
                                + "\n\n3mins per presenter")),
                new Note(new ModuleCode("CS1101S"), new NoteName("Week 1 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS1101S"), new NoteName("Week 2 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS1101S"), new NoteName("Week 3 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS1101S"), new NoteName("Week 4 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2030"), new NoteName("Week 1 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2030"), new NoteName("Week 2 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2030"), new NoteName("Week 3 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2030"), new NoteName("Week 4 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2040"), new NoteName("Week 1 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2040"), new NoteName("Week 2 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2040"), new NoteName("Week 3 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2040"), new NoteName("Week 4 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2102"), new NoteName("Week 1 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2102"), new NoteName("Week 2 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2102"), new NoteName("Week 3 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n")),
                new Note(new ModuleCode("CS2102"), new NoteName("Week 4 Notes"),
                        new Content("Things to "
                                + "include:\n1. Overview\n2. Summary of Class\n"))
        };
    }
    
    public static Homework[] getSampleHomeworkList() {
        return new Homework[]{
                new Homework(new ModuleCode("CS1010"), new HomeworkName("Tutorial 1"), new Date("11/04/2019")),
                new Homework(new ModuleCode("CS1010"), new HomeworkName("Tutorial 2"), new Date("18/04/2019")),
                new Homework(new ModuleCode("CS1010"), new HomeworkName("Tutorial 3"), new Date("18/04/2019")),
                new Homework(new ModuleCode("ES2660"), new HomeworkName("Essay Writing 1"), new Date("10/05/2019")),
                new Homework(new ModuleCode("ES2660"), new HomeworkName("Essay Writing 2"), new Date("17/05/2019")),
                new Homework(new ModuleCode("ES2660"), new HomeworkName("Essay Writing 3"), new Date("24/05/2019")),
                new Homework(new ModuleCode("CS2103T"), new HomeworkName("Tutorial 1"), new Date("11/05/2019")),
                new Homework(new ModuleCode("CS2103T"), new HomeworkName("Tutorial 2"), new Date("18/05/2019")),
                new Homework(new ModuleCode("CS2103T"), new HomeworkName("Tutorial 3"), new Date("25/05/2019")),
                new Homework(new ModuleCode("CS2101"), new HomeworkName("Oral Presentation 1"), new Date("12/05/2019")),
                new Homework(new ModuleCode("CS2101"), new HomeworkName("Oral Presentation 2"), new Date("19/05/2019")),
                new Homework(new ModuleCode("CS2101"), new HomeworkName("Oral " +
                        "Presentation 3"), new Date("26/05/2019")),
                new Homework(new ModuleCode("CS2102"), new HomeworkName("Tutorial" +
                        " 1"), new Date("11/05/2019")),
                new Homework(new ModuleCode("CS2102"), new HomeworkName("Tutorial" +
                        " 2"), new Date("18/05/2019")),
                new Homework(new ModuleCode("CS2102"), new HomeworkName("Tutorial" +
                        " 3"), new Date("25/05/2019")),
                new Homework(new ModuleCode("CS2105"), new HomeworkName("Tutorial" +
                        " 1"), new Date("11/05/2019")),
                new Homework(new ModuleCode("CS2105"), new HomeworkName("Tutorial" +
                        " 2"), new Date("18/05/2019")),
                new Homework(new ModuleCode("CS2105"), new HomeworkName("Tutorial" +
                        " 3"), new Date("25/05/2019")),
                new Homework(new ModuleCode("CS2106"), new HomeworkName("Tutorial" +
                        " 1"), new Date("11/05/2019")),
                new Homework(new ModuleCode("CS2106"), new HomeworkName("Tutorial" +
                        " 2"), new Date("18/05/2019")),
                new Homework(new ModuleCode("CS2106"), new HomeworkName("Tutorial" +
                        " 3"), new Date("25/05/2019"))
        };
    }
    
    public static ReadOnlyUltiStudent getSampleUltiStudent() {
        UltiStudent sampleAb = new UltiStudent();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (CapEntry sampleCapEntry : getSampleCapEntryList()) {
            sampleAb.addCapEntry(sampleCapEntry);
            ModuleSemester sampleCapEntryModuleSemester = sampleCapEntry.getModuleSemester();
            if (!sampleAb.getModuleSemesterList().contains(sampleCapEntryModuleSemester)) {
                sampleAb.addModuleSemester(sampleCapEntryModuleSemester);
            }
        }
        for (Note sampleNote : getSampleNotes()) {
            sampleAb.addNote(sampleNote);
        }
        for (Homework homework : getSampleHomeworkList()) {
            sampleAb.addHomework(homework);
            ModuleCode sampleModuleCode = homework.getModuleCode();
            if (!sampleAb.getModuleCodeList().contains(sampleModuleCode)) {
                sampleAb.addModuleCode(homework.getModuleCode());
            }
        }
        return sampleAb;
    }
    
    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
    
}
