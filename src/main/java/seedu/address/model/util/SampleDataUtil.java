package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {

        // Sample person 1
        Person p1 = new Person(new Name("Alex Yeoh"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                getTagSet("friends"));
        p1.setAddress(new Address("Blk 30 Geylang Street 29, #06-40"));
        p1.setBirthday(new Birthday("01/01/1990"));

        // Sample person 2
        Person p2 = new Person(new Name("Bernice Yu"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends"));
        p2.setAddress(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"));
        p2.setBirthday(new Birthday("31/05/2000"));

        // Sample person 3
        Person p3 = new Person(new Name("Charlotte Oliveiro"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                        getTagSet("neighbours"));
        p3.setAddress(new Address("Blk 11 Ang Mo Kio Street 74, #11-04"));
        p3.setBirthday(new Birthday("07/07/1993"));

        // Sample person 4
        Person p4 = new Person(new Name("David Li"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
                        getTagSet("family"));
        p4.setAddress(new Address("Blk 436 Serangoon Gardens Street 26, #16-43"));
        p4.setBirthday(new Birthday("18/06/1999"));

        // Sample person 5
        Person p5 = new Person(new Name("Irfan Ibrahim"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
                        getTagSet("classmates"));
        p5.setAddress(new Address("Blk 47 Tampines Street 20, #17-35"));
        p5.setBirthday(new Birthday("29/02/2004"));

        // Sample person 6
        Person p6 = new Person(new Name("Roy Balakrishnan"),
                new Phone("92624417"),
                new Email("royb@example.com"),
                        getTagSet("colleagues"));
        p6.setAddress(new Address("Blk 45 Aljunied Street 85, #11-31"));
        p6.setBirthday(new Birthday("24/09/2004"));

        return new Person[] {
            p1, p2, p3, p4, p5, p6

        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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
