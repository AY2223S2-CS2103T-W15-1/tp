package seedu.connectus.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_INSTAGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_TELEGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.commons.util.StringUtil;
import seedu.connectus.logic.parser.exceptions.ParseException;
import seedu.connectus.model.person.Address;
import seedu.connectus.model.person.Birthday;
import seedu.connectus.model.person.Email;
import seedu.connectus.model.person.Name;
import seedu.connectus.model.person.Phone;
import seedu.connectus.model.socialmedia.Instagram;
import seedu.connectus.model.socialmedia.SocialMedia;
import seedu.connectus.model.socialmedia.Telegram;
import seedu.connectus.model.socialmedia.WhatsApp;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.CcaPosition;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser
 * classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading
     * and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero
     *                        unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses string containing keywords into an {@code String[]} of keywords and returns it. Leading
     * and trailing whitespaces will be
     * trimmed.
     */
    public static List<String> parseKeywords(String str) {
        String trimmedArgs = str.trim();
        if (trimmedArgs.isEmpty()) {
            return null;
        }
        List<String> fieldKeywords = Arrays.asList(trimmedArgs.split("\\s+"));
        return fieldKeywords;
    }

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
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     * Doesn't check if phone number is valid.
     */
    public static Phone parsePhoneForFilter(String phone) {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
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
     * @throws ParseException
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        if (!Birthday.isValidBirthday(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }
        return new Birthday(trimmedBirthday);
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
     * Parses a {@code String module} into a {@code module}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code module} is invalid.
     */
    public static Module parseModule(String module) throws ParseException {
        requireNonNull(module);
        String trimmedModule = module.trim();
        if (!Module.isValidTagName(trimmedModule)) {
            throw new ParseException(Module.MESSAGE_CONSTRAINTS);
        }
        return new Module(trimmedModule);
    }

    /**
     * Parses a {@code String cca} into a {@code cca}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code cca} is invalid.
     */
    public static Cca parseCca(String cca) throws ParseException {
        requireNonNull(cca);
        String trimmedCca = cca.trim();
        if (!Cca.isValidTagName(trimmedCca)) {
            throw new ParseException(Cca.MESSAGE_CONSTRAINTS);
        }
        return new Cca(trimmedCca);
    }

    /**
     * Parses a {@code String cca} into a {@code cca}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code cca} is invalid.
     */
    public static CcaPosition parseCcaPosition(String ccaPosition) throws ParseException {
        requireNonNull(ccaPosition);
        String trimmedCcaPosition = ccaPosition.trim();
        if (!Cca.isValidTagName(trimmedCcaPosition)) {
            throw new ParseException(CcaPosition.MESSAGE_CONSTRAINTS);
        }
        return new CcaPosition(trimmedCcaPosition);
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

    /**
     * Parses {@code Collection<String> modules} into a {@code Set<Module>}.
     */
    public static Set<Module> parseModules(Collection<String> modules) throws ParseException {
        requireNonNull(modules);
        final Set<Module> moduleSet = new HashSet<>();
        for (String moduleName : modules) {
            moduleSet.add(parseModule(moduleName));
        }
        return moduleSet;
    }

    /**
     * Parses {@code Collection<String> ccas} into a {@code Set<Cca>}.
     */
    public static Set<Cca> parseCcas(Collection<String> ccas) throws ParseException {
        requireNonNull(ccas);
        final Set<Cca> ccaSet = new HashSet<>();
        for (String ccaName : ccas) {
            ccaSet.add(parseCca(ccaName));
        }
        return ccaSet;
    }

    /**
     * Parses {@code Collection<String> ccaPositions} into a {@code Set<CcaPosition>}.
     */
    public static Set<CcaPosition> parseCcaPositions(Collection<String> ccaPositions) throws ParseException {
        requireNonNull(ccaPositions);
        final Set<CcaPosition> ccaPositionSet = new HashSet<>();
        for (String ccaPositionName : ccaPositions) {
            ccaPositionSet.add(parseCcaPosition(ccaPositionName));
        }
        return ccaPositionSet;
    }

    private static Instagram parseInstagram(String instagram) throws ParseException {
        if (instagram == null || instagram.isEmpty()) {
            return null;
        }
        if (!Instagram.isValid(instagram)) {
            throw new ParseException(Instagram.MESSAGE_CONSTRAINTS);
        }
        return Instagram.of(instagram);
    }

    private static Telegram parseTelegram(String telegram) throws ParseException {
        if (telegram == null || telegram.isEmpty()) {
            return null;
        }
        if (!Telegram.isValid(telegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return Telegram.of(telegram);
    }

    private static WhatsApp parseWhatsApp(String whatsApp) throws ParseException {
        if (whatsApp == null || whatsApp.isEmpty()) {
            return null;
        }
        if (!WhatsApp.isValidWhatsApp(whatsApp)) {
            throw new ParseException(WhatsApp.MESSAGE_CONSTRAINTS);
        }
        return WhatsApp.of(whatsApp);
    }

    /**
     * Parses a {@code String socialMedia} into an {@code SocialMedia}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static SocialMedia parseSocialMedia(ArgumentMultimap argMultimap) throws ParseException {
        var instagram = argMultimap.getValue(PREFIX_SOCMED_INSTAGRAM);
        var telegram = argMultimap.getValue(PREFIX_SOCMED_TELEGRAM);
        var whatsapp = argMultimap.getValue(PREFIX_SOCMED_WHATSAPP);

        var socialMedia = new SocialMedia(
            instagram.isPresent() ? ParserUtil.parseInstagram(instagram.get()) : null,
            telegram.isPresent() ? ParserUtil.parseTelegram(telegram.get()) : null,
            whatsapp.isPresent() ? ParserUtil.parseWhatsApp(whatsapp.get()) : null
        );

        return socialMedia.isBlank() ? null : socialMedia;
    }


    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if
     * {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be
     * parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    public static Optional<Set<Tag>> parseTagsOptional(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> modules} into a {@code Set<Module>} if
     * {@code modules} is non-empty.
     * If {@code modules} contain only one element which is an empty string, it will be
     * parsed into a
     * {@code Set<module>} containing zero modules.
     */
    public static Optional<Set<Module>> parseModulesOptional(Collection<String> modules) throws ParseException {
        assert modules != null;

        if (modules.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> moduleSet = modules.size() == 1 && modules.contains("") ? Collections.emptySet() : modules;
        return Optional.of(ParserUtil.parseModules(moduleSet));
    }

    /**
     * Parses {@code Collection<String> ccas} into a {@code Set<Cca>} if
     * {@code ccas} is non-empty.
     * If {@code ccas} contain only one element which is an empty string, it will be
     * parsed into a
     * {@code Set<Cca>} containing zero ccas.
     */
    public static Optional<Set<Cca>> parseCcasOptional(Collection<String> ccas) throws ParseException {
        assert ccas != null;

        if (ccas.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> ccaSet = ccas.size() == 1 && ccas.contains("") ? Collections.emptySet() : ccas;
        return Optional.of(ParserUtil.parseCcas(ccaSet));
    }

    /**
     * Parses {@code Collection<String> ccaPositions} into a {@code Set<CcaPosition>} if
     * {@code ccaPositions} is non-empty.
     * If {@code ccaPositions} contain only one element which is an empty string, it will be
     * parsed into a
     * {@code Set<Cca>} containing zero ccaPositions.
     */
    public static Optional<Set<CcaPosition>> parseCcaPositionsOptional(Collection<String> ccaPositions)
            throws ParseException {
        assert ccaPositions != null;

        if (ccaPositions.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> ccaPositionSet = ccaPositions.size() == 1 && ccaPositions
                .contains("") ? Collections.emptySet() : ccaPositions;
        return Optional.of(ParserUtil.parseCcaPositions(ccaPositionSet));
    }
}
