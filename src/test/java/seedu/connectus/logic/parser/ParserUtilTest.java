package seedu.connectus.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.connectus.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.connectus.testutil.Assert.assertThrows;
import static seedu.connectus.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.connectus.logic.parser.exceptions.ParseException;
import seedu.connectus.model.person.Address;
import seedu.connectus.model.person.Email;
import seedu.connectus.model.person.Name;
import seedu.connectus.model.person.Phone;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.CcaPosition;
import seedu.connectus.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_MODULE = "CS2!03T";
    private static final String INVALID_CCA = "!NES";
    private static final String INVALID_CCA_POSITION = "*President*";
    private static final String INVALID_BIRTHDAY = "Hello/01/2000";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_MODULE_1 = "CS2103T";
    private static final String VALID_MODULE_2 = "CS2101";
    private static final String VALID_CCA_1 = "NES";
    private static final String VALID_CCA_2 = "ICS";
    private static final String VALID_CCA_POSITION_1 = "PRESIDENT";
    private static final String VALID_CCA_POSITION_2 = "DIRECTOR";
    private static final String VALID_BIRTHDAY = "01/01/2000";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
            ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }


    @Test
    public void parseModule_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModule(null));
    }

    @Test
    public void parseModule_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModule(INVALID_MODULE));
    }

    @Test
    public void parseModule_validValueWithoutWhitespace_returnsModule() throws Exception {
        Module expectedModule = new Module(VALID_MODULE_1);
        assertEquals(expectedModule, ParserUtil.parseModule(VALID_MODULE_1));
    }

    @Test
    public void parseModule_validValueWithWhitespace_returnsTrimmedModule() throws Exception {
        String moduleWithWhitespace = WHITESPACE + VALID_MODULE_1 + WHITESPACE;
        Module expectedModule = new Module(VALID_MODULE_1);
        assertEquals(expectedModule, ParserUtil.parseModule(moduleWithWhitespace));
    }

    @Test
    public void parseModules_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseModules(null));
    }

    @Test
    public void parseModules_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseModules(Arrays.asList(VALID_MODULE_1,
                INVALID_MODULE)));
    }

    @Test
    public void parseModules_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseModules(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseModules_collectionWithValidModules_returnsModuleSet() throws Exception {
        Set<Module> actualModuleSet = ParserUtil.parseModules(Arrays.asList(VALID_MODULE_1, VALID_MODULE_2));
        Set<Module> expectedModuleSet = new HashSet<>(Arrays.asList(new Module(VALID_MODULE_1),
                new Module(VALID_MODULE_2)));

        assertEquals(expectedModuleSet, actualModuleSet);
    }

    @Test
    public void parseCca_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCca(null));
    }

    @Test
    public void parseCca_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCca(INVALID_CCA));
    }

    @Test
    public void parseCca_validValueWithoutWhitespace_returnsCca() throws Exception {
        Cca expectedCca = new Cca(VALID_CCA_1);
        assertEquals(expectedCca, ParserUtil.parseCca(VALID_CCA_1));
    }

    @Test
    public void parseCca_validValueWithWhitespace_returnsTrimmedCca() throws Exception {
        String ccaWithWhitespace = WHITESPACE + VALID_CCA_1 + WHITESPACE;
        Module expectedCca = new Module(VALID_CCA_1);
        assertEquals(expectedCca, ParserUtil.parseCca(ccaWithWhitespace));
    }

    @Test
    public void parseCcas_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCcas(null));
    }

    @Test
    public void parseCcas_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCcas(Arrays.asList(VALID_CCA_1,
                INVALID_CCA)));
    }

    @Test
    public void parseCcas_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseCcas(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseCcas_collectionWithValidCcas_returnsCcaSet() throws Exception {
        Set<Cca> actualCcaSet = ParserUtil.parseCcas(Arrays.asList(VALID_CCA_1, VALID_CCA_2));
        Set<Cca> expectedCcaSet = new HashSet<>(Arrays.asList(new Cca(VALID_CCA_1),
                new Cca(VALID_CCA_2)));

        assertEquals(expectedCcaSet, actualCcaSet);
    }

    @Test
    public void parseCcaPosition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCcaPosition(null));
    }

    @Test
    public void parseCcaPosition_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCcaPosition(INVALID_CCA));
    }

    @Test
    public void parseCcaPosition_validValueWithoutWhitespace_returnsCca() throws Exception {
        CcaPosition expectedCcaPosition = new CcaPosition(VALID_CCA_POSITION_1);
        assertEquals(expectedCcaPosition, ParserUtil.parseCca(VALID_CCA_POSITION_1));
    }

    @Test
    public void parseCcaPosition_validValueWithWhitespace_returnsTrimmedCcaPosition() throws Exception {
        String ccaPositionWithWhitespace = WHITESPACE + VALID_CCA_POSITION_1 + WHITESPACE;
        Module expectedCcaPosition = new Module(VALID_CCA_POSITION_1);
        assertEquals(expectedCcaPosition, ParserUtil.parseCca(ccaPositionWithWhitespace));
    }

    @Test
    public void parseCcaPositions_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCcaPositions(null));
    }

    @Test
    public void parseCcaPositions_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCcaPositions(Arrays.asList(VALID_CCA_POSITION_1,
                INVALID_CCA_POSITION)));
    }

    @Test
    public void parseCcaPositions_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseCcaPositions(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseCcaPositions_collectionWithValidCcaPositions_returnsCcaPositionSet() throws Exception {
        Set<CcaPosition> actualCcaPositionSet = ParserUtil.parseCcaPositions(Arrays
                .asList(VALID_CCA_POSITION_1, VALID_CCA_POSITION_2));
        Set<CcaPosition> expectedCcaPositionSet = new HashSet<>(Arrays.asList(new CcaPosition(VALID_CCA_POSITION_1),
                new CcaPosition(VALID_CCA_POSITION_2)));

        assertEquals(expectedCcaPositionSet, actualCcaPositionSet);
    }


    @Test
    public void parseBirthday_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBirthday((String) null));
    }

    @Test
    public void parseBirthday_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBirthday(INVALID_BIRTHDAY));
    }
}
