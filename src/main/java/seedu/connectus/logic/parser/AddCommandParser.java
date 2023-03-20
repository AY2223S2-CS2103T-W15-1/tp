package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_SOCMED;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.connectus.logic.commands.AddCommand;
import seedu.connectus.logic.parser.exceptions.ParseException;
import seedu.connectus.model.person.Address;
import seedu.connectus.model.person.Birthday;
import seedu.connectus.model.person.Email;
import seedu.connectus.model.person.Name;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.person.Phone;
import seedu.connectus.model.socialmedia.SocialMedia;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_BIRTHDAY, PREFIX_SOCMED, PREFIX_TAG, PREFIX_MODULE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Module> moduleList = ParserUtil.parseModules(argMultimap.getAllValues(PREFIX_MODULE));

        Person person = new Person(name, tagList, moduleList);

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            person.setPhone(phone);
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            person.setEmail(email);
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
            person.setAddress(address);
        }

        if (argMultimap.getValue(PREFIX_SOCMED).isPresent()) {
            SocialMedia socialMedia = ParserUtil.parseSocialMedia(argMultimap.getValue(PREFIX_SOCMED).get());
            if (socialMedia == null || socialMedia.isBlank()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }
            person.setSocialMedia(socialMedia);
        }


        if (argMultimap.getValue(PREFIX_BIRTHDAY).isPresent()) {
            Birthday birthday = ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get());
            person.setBirthday(birthday);
        }

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
