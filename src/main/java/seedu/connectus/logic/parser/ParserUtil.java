package seedu.connectus.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_INSTAGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_TELEGRAM;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED_WHATSAPP;

import java.util.Collection;
import java.util.HashSet;
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
        if (!Instagram.isValid(telegram)) {
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
}
